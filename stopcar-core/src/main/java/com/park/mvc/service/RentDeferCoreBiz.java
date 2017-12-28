package com.park.mvc.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.park.bean.*;
import com.park.constants.Constants;
import com.park.dao.Carcode_park_rentDao;
import com.park.dao.Rent_deferDao;
import com.park.exception.QzException;
import com.park.jpush.bean.JPushMessageBean;
import com.park.mvc.service.common.PayParkPB;
import com.park.mvc.service.common.UserPB;
import com.park.task.AsyncSMSTask;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by zzy on 2017/7/3.
 */
@Service("rentDeferCoreBiz")
public class RentDeferCoreBiz extends BaseBiz {

    @Autowired
    private Rent_deferDao rentDeferDao;

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private EventScheduleBiz eventScheduleBiz;

    @Autowired
    private UserPB userPB;

    @Autowired
    private PayParkPB payParkPB;

    @Autowired
    private AsyncSMSTask asyncSMSTask;

    @Autowired
    private Carcode_park_rentDao carcode_park_rentDao;

    //超时时间，单位分
    private static final String TIME_OUT_MINUTES = "30";

    /**
     * 根据rentOrderId 查询
     * 没有过期，没有被删除的租赁订单
     *
     * @param rentOrderId
     * @return
     */
    public Rent_defer getRentDeferByRentOrderId(String rentOrderId) {
        try {
            String sql = "select * from rent_defer t where t.rent_order_id = ? and t.is_expire = 0 and t.is_del = 0 limit 1";
            return getMySelfService().queryUniqueT(sql, Rent_defer.class, new Object[]{rentOrderId});
        } catch (Exception e) {
            log.error("数据库异常-->", e);
        }
        return null;
    }

    /**
     * 获取用户车牌--停车场租赁
     *
     * @param carCode
     * @param pi_id
     * @param areaCode
     * @return
     */
    public Carcode_park_rent getCarcodeParkRent(String carCode, long pi_id, String areaCode) {
        try {
            String sql = "select * from carcode_park_rent t where t.pi_id = ? and t.car_code = ? and t.area_code = ? ";
            Carcode_park_rent carcodeParkRent = getMySelfService().queryUniqueT(sql, Carcode_park_rent.class, pi_id,carCode, areaCode);
            return carcodeParkRent;
        } catch (Exception e) {
            log.info("数据库异常", e);
        }
        return null;
    }

    /**
     * 根据rentOrderId 修改mq推送状态
     *
     * @param rentOrderId
     */
    public int updateMqState(int mqState, String rentOrderId) {
        try {
            String sql = "update rent_defer t set t.mq_state = ? where t.rent_order_id = ?";
            return getMySelfService().update(sql, new Object[]{mqState, rentOrderId});
        } catch (Exception e) {
            log.error("数据库异常-->", e);
        }
        return -1;
    }


    /**
     * 第三方支付回调更新续租租赁定单支付状态，支付类型，更新时间，mq推送状态；mq消息推送
     *
     * @param orderId 第三方订单号(平台支付流水单号)
     * @throws QzException
     */
    //@Transactional(rollbackFor = QzException.class)
    public void updateRentDeferInfo(String orderId) throws QzException {
        try {
            int payState = 0;   //支付状态（0：未支付 1：支付成功  2：支付失败）
            User_pay userPay = payParkPB.selectOneUserPay(orderId);
            if (null == userPay) {
                throw new QzException("订单流水不存在");
            }
            int userPayState = userPay.getState();  //交易状态(0:未支付 1：已支付  2：支付失败)
            if (1 == userPayState) {
                payState = 1;
            } else if (2 == userPayState) {
                payState = 2;
            }

            String rentOrderId = userPay.getCar_order_id();//续租租赁订单号
            Rent_defer rentDefer = getRentDeferByRentOrderId(rentOrderId);

            if (null == rentDefer) {
                log.info("租赁订单不存在{}", userPay.getCar_order_id());
                throw new QzException("租赁订单不存在");
            }

            if (1 == rentDefer.getPay_state()) {
                log.info("租赁续租订单已经支付{}", rentDefer.getRent_order_id());
                throw new QzException("租赁续租订单已经支付");
            }

//            Client_gate_rule clientGateRule = getGateRule(rentDefer.getClient_rule_id(),rentDefer.getPi_id(),rentDefer.getArea_code());
//            if(clientGateRule != null){
//                rentDefer.setPermit_time(clientGateRule.getPermit_time());
//            }

            rentDefer.setPay_state(payState);   //支付状态（0：未支付 1：支付成功  2：支付失败）
            rentDefer.setPay_source(userPay.getType());  //支付类型
            rentDefer.setUp_orderid(orderId);
            //支付失败时，修改续租状态为失败
            if (2 == payState) {
                rentDefer.setDefer_state(3);  //续租状态（0：未续租  1：续租中  2：续租成功 3：续租失败   4：续租超时失败-退款钱包）
            }
            rentDefer.setUtime(new Date());

            int result = rentDeferDao.updateByKey(rentDefer);

            if (result < 1) {
                log.info("更新租赁续租订单信息失败");
                throw new QzException("更新租赁续租订单信息失败");
            }

            int mqState = 1;
            try {
                //插入消息队列
                JPushMessageBean mqMsgBean = new JPushMessageBean();
                mqMsgBean.setType(3);  //类型 0：系统消息  1：预约推送 2：租赁推送 3：线上续租
                mqMsgBean.setDate(new Date());
                mqMsgBean.setMessage("租赁订单续租成功");
                mqMsgBean.setTitle("租赁续租消息");
                JSONObject object = new JSONObject();
                object.put("rent_defer", rentDefer);
                //object.put("type", 3);// 类型 0：系统消息  1：预约推送 2：租赁推送 3：线上续租
                mqMsgBean.setMessageJson(object);
                rabbitPublisher.publish2Gate(rentDefer.getArea_code(), rentDefer.pi_id, mqMsgBean, true);
            } catch (Exception e) {
                log.error("mq推送失败", e);
                mqState = 2;
            }
            updateMqState(mqState, userPay.getCar_order_id());  //修改推送状态

        } catch (Exception e) {
            log.error("更新租赁续租订单信息异常", e);
            throw new QzException("更新租赁续租订单信息异常");
        }
    }

    /**
     * 新增租赁定单续租事件
     *
     * @param userPay
     */
    public void addEventSchedule(User_pay userPay) {
        try {
            Rent_defer rentDefer = getRentDeferByRentOrderId(userPay.getCar_order_id());

            String area_code = "";
            long pi_id = -1;
            if (null != rentDefer) {
                pi_id = rentDefer.getPi_id();
                area_code = rentDefer.getArea_code();
            }
            String eventName = "租赁定单续租事件";
            int eventType = 1;  //事件类型（0：未指定 1：续租租赁订单 ）
            Event_schedule eventSchedule = new Event_schedule();
            eventSchedule.setEvent_name(eventName);
            eventSchedule.setEvent_type(eventType);
            eventSchedule.setUp_orderid(userPay.getOrder_id());
            eventSchedule.setOrder_id(userPay.getCar_order_id());
            eventSchedule.setOrder_type(3);  //业务订单类型（0:未指定  1：普通临停订单  2：预约订单  3：租赁订单 4：包月订单）
            eventSchedule.setUi_id(userPay.getUi_id());
            eventSchedule.setUi_nd(userPay.getUi_nd());
            eventSchedule.setPi_id(pi_id);
            eventSchedule.setArea_code(area_code);
            eventScheduleBiz.recordEventSchedule(eventSchedule);
        } catch (Exception e) {
            log.error("新增租赁定单续租事件失败", e);
        }

    }


    /**
     * 获取支付成功的且在续租中的续租超时订单
     *
     * @return
     */
    public List<Rent_defer> queryTimeOutRentDeferOrders() throws QzException {

        try {
            String sql = "select t.* from rent_defer t " +
                    "where t.defer_state = 1 " +
                    "and t.pay_state = 1 " +
                    "and UNIX_TIMESTAMP() - UNIX_TIMESTAMP(t.ctime) > {} * 60";
            sql = sql.replace("{}", TIME_OUT_MINUTES);
            List<Rent_defer> list = getMySelfService().queryListT(sql, Rent_defer.class);
            if (null == list || list.size() == 0) {
                log.info("没有需要处理的租赁续租超时的订单");
                return new ArrayList<Rent_defer>();
            }
            return list;

        } catch (Exception e) {
            throw new QzException("获取支付成功的且在续租中的续租超时订单信息失败");
        }
    }

    /**
     * 处理支付成功的且在续租中的续租订单，将支付金额返还到用户钱包
     *
     * @param rentDefer
     * @throws QzException
     */
    @Transactional(rollbackFor = QzException.class)
    public void excuteRentDeferOrderRefund(Rent_defer rentDefer) throws QzException {
        try {
            User_info userInfo = null;
            String jgmessage = "";
            String sms = "";
            if(rentDefer.getMoney() > 0){
                int type = 0; //增加金额还是减少金额 0:新增 1:减少
                //将租赁订单支付金额返换到用户钱包
                userInfo = userPB.updateUserMoney(rentDefer.getUi_id(), rentDefer.getUi_nd(), type, rentDefer.getMoney());
                if (null == userInfo) {
                    log.info("更新用户金额失败，处理支付成功的且在续租中的续租订单超时失败");
                    throw new QzException("更新用户金额失败");
                }
                rentDefer.setDefer_state(4);  //续租超时失败-退款钱包
                if(null == rentDefer.getNote() || "".equals(rentDefer.getNote())){
                    rentDefer.setNote("续租失败-退款钱包,"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
                }

                //rentDefer.setUtime(new Date());
                int result = rentDeferDao.updateByKey(rentDefer);
                if (result < 1) {
                    throw new QzException("处理支付成功的且在续租中的续租订单超时失败");
                }

                //记录钱包余额返还明细
                recordVc(rentDefer, userInfo.getUi_tel(), new ReturnDataNew());

                jgmessage = "亲，您的租赁订单【" + rentDefer.getRent_order_id() + "】租赁续租失败，钱已退回至您的钱包。";
                sms = "亲，您的租赁订单【" + rentDefer.getRent_order_id() + "】租赁续租失败，钱已退回至您的钱包，请登录吾泊APP查看【四川琦彩科技】";
                log.info("返还到用户[{}]钱包余额[{}]", userInfo.getUuid(), rentDefer.getMoney());

            }else{
                //支付金额为0的情况
                userInfo = user_infoDao.selectByKey(rentDefer.getUi_id());
                if(null == userInfo){
                    log.info("用户不存在");
                    throw new QzException("用户不存在");
                }

                jgmessage = "亲，您的租赁订单【" + rentDefer.getRent_order_id() + "】租赁续租失败，请联系客服。";
                sms = "亲，您的租赁订单【" + rentDefer.getRent_order_id() + "】租赁续租失败，请联系客服【四川琦彩科技】";

                rentDefer.setDefer_state(4);  //续租超时失败-退款钱包
                rentDefer.setNote("续租超时失败-退款钱包");
                rentDefer.setUtime(new Date());
                int result = rentDeferDao.updateByKey(rentDefer);
                if (result < 1) {
                    throw new QzException("处理支付成功的且在续租中的续租订单超时失败");
                }
            }

            //推送消息和发送短信
            try {
                //jgmessage = "亲，您的租赁订单【" + rentDefer.getRent_order_id() + "】租赁续租失败，钱已退回至您的钱包。";
                jpushMessage(jgmessage, rentDefer);

                String phone = userInfo.getUi_tel();
                //String sms = "亲，您的租赁订单【" + rentDefer.getRent_order_id() + "】租赁续租失败，钱已退回至您的钱包，请登录吾泊APP查看【四川琦彩科技】";
                asyncSMSTask.sendMessage(phone, sms);
            } catch (Exception e) {
                log.info("发送短信消息异常",e);
            }

        } catch (QzException e){
            log.info("处理支付成功的且在续租中的续租订单超时失败", e);
            throw new QzException("处理支付成功的且在续租中的续租订单超时失败",e);
        }catch (Exception e) {
            log.error("处理支付成功的且在续租中的续租订单超时失败", e);
            throw new QzException("处理支付成功的且在续租中的续租订单超时失败",e);
        }
    }

    /**
     * 创建订单号
     *
     * @param area_code
     * @param product_type
     * @param pi_id
     * @param order_type   订单类型：0 临停订单 1 本地包月订单 2 本地免费订单 3  预约订单 4 租赁订单 （其中预约订单和租赁订单生成都由服务器端生成）
     * @return
     */
    public String makeRentOrderId(String area_code, int product_type, long pi_id, int order_type) {
        switch (product_type) {
            case 0:
                product_type = 0;
                break;
            case 1:
                product_type = 1;
                break;
        }
        StringBuilder order_id = new StringBuilder();
        order_id.append(area_code).append(product_type);
        String pi_idStr = String.valueOf(pi_id);
        if (pi_idStr.length() > 8) {
            pi_idStr = pi_idStr.substring(0, 8);
        } else {
            char[] chars = new char[8 - pi_idStr.length()];
            Arrays.fill(chars, '0');
            pi_idStr = new String(chars) + pi_idStr;
        }
        order_id.append(pi_idStr).append(order_type)
                .append(new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()))
                .append(RandomStringUtils.random(2, "abcdefghigklmnopqrstuvwxyzABCDEFGHIGKLMNOPQRSTUVWXYZ0123456789"))
        ;
        return order_id.toString();
    }

    /**
     * 更新租赁信息是否已经到期状态
     */
    public void updateExpireState(){
        try{
            String sql = "SELECT * FROM carcode_park_rent WHERE UNIX_TIMESTAMP(endtime) < UNIX_TIMESTAMP() ";
            List<Carcode_park_rent> list = carcode_park_rentDao.queryListT(sql,Carcode_park_rent.class);
            if(list == null || list.size()==0){
                return;
            }
            for (Carcode_park_rent carcode_park_rent : list){
                if(carcode_park_rent.getIs_expire() == 0){
                    //获取结束时间
                    Date endtime = carcode_park_rent.getEndtime();
                    if(null != endtime){
                        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                        String currentTimeString = formatter.format(new Date());
                        String endTimeString = formatter.format(endtime);
                        try {
                            Date currentTime = formatter.parse(currentTimeString);
                            Date endTime = formatter.parse(endTimeString);
                            int i = endTime.compareTo(currentTime);//i=1/0/-1
                            if(i < 0){
                                //结束日期（精确到天）小于当前日期
                                carcode_park_rent.setIs_expire(1);
                                carcode_park_rent.setNote("租赁信息已到期");
                                carcode_park_rentDao.updateByKey(carcode_park_rent);
                            }
                        }catch(Exception e){
                            log.error("更新租赁信息到期状态失败",e);
                        }
                    }
                }
            }
        }catch(Exception e){
            log.error("RentDeferCoreBiz updateExpireState is error", e);
        }
    }

    /**
     * 推送消息给APP用户
     *
     * @param rentDefer
     */
    public void jpushMessage(String message, Rent_defer rentDefer) {
        String title = "系统消息";
        JPushMessageBean jPushMessageBean = new JPushMessageBean();
        jPushMessageBean.setMessage(message);
        jPushMessageBean.setImgurl(Constants.JPUSH_LOGO);
        jPushMessageBean.setTitle(title);
        jPushMessageBean.setDate(new Date());
        jPushMessageBean.setMessageJson((JSON) JSON.toJSON(rentDefer));
        asyncJpushTask.doAppJpush(jPushMessageBean, rentDefer.getUi_nd());
    }

    /**
     * 记录退款明细
     * @param rentDefer
     * @param tel
     * @param returnData
     * @throws QzException
     */
    private void recordVc(Rent_defer rentDefer, String tel,ReturnDataNew returnData) throws QzException{
        userPB.recordVC(2,rentDefer.getMoney(),rentDefer.getRent_order_id(), 3,
                rentDefer.getUi_id(), returnData,"app租赁续租失败钱包余额返还",rentDefer.getUi_nd(),tel,
                rentDefer.getPay_source(),0L,0,0,new Date());
    }

    /**
     * 检查是否跨天
     * @return
     */
//    public boolean isKuaDay(String permitTime){
//        String[] times = permitTime.split("\\-",-1);
//
//        Calendar calendar = Calendar.getInstance();
//        String begin = times[0];
//        String end = times[1];
//
//        DateFormat df;
//        try {
//            df = new SimpleDateFormat("yyyyMMdd");
//            //df.setLenient(false); //是否严格检查日期格式
//            Date date = df.parse(begin);
//
//            calendar.setTime(date);
//            calendar.add(Calendar.DATE, day);
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//
//        calendar.setTime(carcodeParkRent.getEndtime());
//        calendar.add(Calendar.MONTH, deferByAPP.getMonth_num());
//        Date newEndTime = calendar.getTime();
//        return false;
//    }

    /**
     * 获取租赁规则  by
     * @param clientRuleid
     * @param piId
     * @param areaCode
     * @return
     */
    public Client_gate_rule getGateRule(String clientRuleid,long piId,String areaCode) {
        try{
            String sql = "select * from client_gate_rule t where t.client_ruleid = ? and t.pi_id = ? and t.area_code = ? ";

            Client_gate_rule clientGateRule = getMySelfService().queryUniqueT(sql,
                    Client_gate_rule.class,
                    clientRuleid,
                    piId, areaCode);
            return clientGateRule;
        }catch (Exception e){
            log.error("获取租赁规则错误-->",e);
            return null;
        }
    }
}
