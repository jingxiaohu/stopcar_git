package com.park.app.service;

import com.alibaba.fastjson.JSONObject;
import com.park.app.action.v1.order.param.CarcodeParkRentDel;
import com.park.app.action.v1.rentdefer.param.CheckRentDeferInfo;
import com.park.app.action.v1.rentdefer.param.RentDeferByAPP;
import com.park.app.transaction.AppPayTransaction;
import com.park.bean.*;
import com.park.dao.Carcode_park_rentDao;
import com.park.dao.Rent_deferDao;
import com.park.exception.QzException;
import com.park.jpush.bean.JPushMessageBean;
import com.park.mvc.service.BaseBiz;
import com.park.mvc.service.RentDeferCoreBiz;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Calendar;
import java.util.Date;

/**
 * 响应参数中errorcode 为以下内容的错误响应描述：
 * ###### 错误码errorcode描述
 * |错误码|错误描述|
 * |---|---|
 * 1001|用户车牌--停车场租赁映射记录不存在|
 * 1002|用户车牌--停车场租赁映射记录已经过期|
 * 1003|用户车牌--停车场租赁映射记录已被删除|
 * 1004|停车场信息不存在|
 * 1005|停车场线上续租功能没有开启|
 * 1006|用户不存在|
 * 1007|车牌绑定用户不一致|
 * 1008|该车牌号没有绑定用户|
 * 1009|停车场规则记录不存在|
 * 1010|停车场规则记录已被删除|
 * 1011|停车场规则记录线上续租功能已被关闭|
 * 1012|用户车牌--停车场租赁信息单价与租赁规则单价不一致|
 * 1013|该租赁信息正在续租|
 * 1014|钱包余额不足|
 * 1015|租赁信息未到期不能删除|
 * 1000|系统异常|
 * <p>
 * Created by zzy on 2017/7/3.
 */
@Service("rentDeferByAPPBiz")
public class RentDeferByAPPBiz extends BaseBiz {

    @Autowired
    private Rent_deferDao rent_deferDao;

    @Autowired
    private Carcode_park_rentDao carcodeParkRentDao;

    @Autowired
    private RentDeferCoreBiz rentDeferCoreBiz;

    @Autowired
    private CarBiz carBiz;

    @Autowired
    private AppPayTransaction appPayTransaction;

    private Logger log = LoggerFactory.getLogger(this.getClass());

    private static final String returnUserMsg = "租赁续租失败，请联系客服！";

    /**
     * 租赁订单续租
     *
     * @param deferByAPP
     * @param returnData
     */
    //@Transactional(rollbackFor = QzException.class)
    public void rentOrderDefer(RentDeferByAPP deferByAPP, ReturnDataNew returnData) throws QzException {
        try {

            Carcode_park_rent carcodeParkRent = carcodeParkRentDao.selectByKey(deferByAPP.getCpr_id());
            if (null == carcodeParkRent) {
                log.info("用户车牌--停车场租赁映射记录不存在，续租失败");
                returnData.setReturnData(errorcode_param, returnUserMsg, "");
                returnData.setErrorcode("1001");
                return;
            }

            //判断是否过期
            //if (1 == carcodeParkRent.getIs_expire() || (System.currentTimeMillis() - carcodeParkRent.getEndtime().getTime() > 0)) {
            if (1 == carcodeParkRent.getIs_expire()) {
                log.info("用户车牌--停车场租赁映射记录已经过期");
                returnData.setReturnData(errorcode_param, "租赁信息已过期", "");
                returnData.setErrorcode("1002");
                return;
            }

            //判断是否已删除
            if (1 == carcodeParkRent.getIs_del()) {
                log.info("用户车牌--停车场租赁映射记录已被删除");
                returnData.setReturnData(errorcode_param, "租赁信息已删除", "");
                returnData.setErrorcode("1003");
                return;
            }

            long piId = carcodeParkRent.getPi_id();
            String areaCode = carcodeParkRent.getArea_code();
            String carCode = carcodeParkRent.getCar_code();
            //检查停车场是否存在
            Park_info parkInfo = returnParkInfo(piId, areaCode);
            if (parkInfo == null) {
                log.info("停车场信息不存在");
                returnData.setReturnData(errorcode_data, returnUserMsg, "");
                returnData.setErrorcode("1004");
                return;
            }

            //判断停车场线上续租功能是否开启
            if (0 == parkInfo.getRelet_state()) {
                log.info("停车场[" + piId + "]线上续租功能没有开启");
                returnData.setReturnData(errorcode_data, "【" + parkInfo.getPi_name() + "】停车场线上续租功能没有开启，请联系停车场管理员！", "");
                returnData.setErrorcode("1005");
                return;
            }

            User_info userInfo = user_infoDao.selectByKey(deferByAPP.getUi_id());
            if (null == userInfo) {
                log.info("用户不存在");
                returnData.setReturnData(errorcode_data, returnUserMsg, "");
                returnData.setErrorcode("1006");
                return;
            }

            String ui_nd = userInfo.getUuid();
            //deferByAPP.setUi_nd(ui_nd);

            //通过车牌号获取用户ui_id,ui_nd
            User_carcode userCarcode = carBiz.queryUserCarBycode(deferByAPP.getCar_code());
            if (null != userCarcode) {
                if (deferByAPP.getUi_id() != userCarcode.getUi_id()) {
                    log.info("车牌绑定用户不一致");
                    returnData.setReturnData(errorcode_data, returnUserMsg, "");
                    returnData.setErrorcode("1007");
                    return;
                }
            } else {
                log.info("该车牌号没有绑定用户");
                returnData.setReturnData(errorcode_param, returnUserMsg, "");
                returnData.setErrorcode("1008");
                return;
            }

            //判断规则记录是否存在
            String sql = "select * from client_gate_rule t where t.client_ruleid = ? and t.pi_id = ? and t.area_code = ? ";//and t.is_del = 0 and t.state = 1";
            Client_gate_rule clientGateRule = getMySelfService().queryUniqueT(sql, Client_gate_rule.class, carcodeParkRent.getClient_rule_id(), piId, areaCode);
            if (null == clientGateRule) {
                log.info("停车场规则记录不存在");
                returnData.setReturnData(errorcode_param, returnUserMsg, "");
                returnData.setErrorcode("1009");
                return;
            } else {
                if (0 != clientGateRule.getIs_del()) {
                    log.info("停车场规则记录已被删除");
                    returnData.setReturnData(errorcode_param, returnUserMsg, "");
                    returnData.setErrorcode("1010");
                    return;
                }
                if (1 != clientGateRule.getState()) {
                    log.info("停车场规则记录已被关闭");
                    returnData.setReturnData(errorcode_param, returnUserMsg, "");
                    returnData.setErrorcode("1011");
                    return;
                }
            }

            if (deferByAPP.getUnit_price() != clientGateRule.getMoney()){
                returnData.setReturnData(errorcode_data, "租赁单价发生变化，请重新刷新页面进行续租", "");
                return;
            }

            //支付金额
            int money = clientGateRule.getMoney() * deferByAPP.getMonth_num();  //单价*月份数

            //支付金额小于等于0不能续租
            if (money <= 0) {
                returnData.setReturnData(errorcode_data, "支付金额不能小于0", "");
                return;
            }

            //判断钱包余额是否足够
            if (4 == deferByAPP.getPay_source()) {
                //钱包金额判断
                if (isNotSureMoney(userInfo, money)) {
                    log.info("钱包余额不足");
                    returnData.setReturnData(errorcode_data, "您的钱包余额不足！", "");
                    returnData.setErrorcode("1014");
                    return;
                }
            }

            String sqlR = "select * from rent_defer t " +
                    "where t.pi_id = ? " +
                    "and t.area_code = ? " +
                    "and t.ui_id = ? " +
                    "and t.car_code = ? " +
                    "order by stime desc " +
                    "limit 1";
            Rent_defer rd = getMySelfService().queryUniqueT(sqlR, Rent_defer.class, piId, areaCode, deferByAPP.getUi_id(), carCode);
            if (null != rd) {
                //续租中的不允许继续续租
                if (1 == rd.getPay_state() && 1 == rd.getDefer_state()) {
                    log.info("该租赁信息正在续租，不能继续续租");
                    returnData.setReturnData(errorcode_data, "该租赁信息正在续租，不能继续续租", "");
                    returnData.setErrorcode("1013");
                    return;
                }
            }

            ///////////////////////////////////////////////////////////////////////
            String fatherRentOrderId = "";
            fatherRentOrderId = carcodeParkRent.getClient_father_orderid();
            Rent_defer rentDefer = new Rent_defer();
            String rent_order_id = rentDeferCoreBiz.makeRentOrderId(areaCode, deferByAPP.getDtype(), piId, 4);
            rentDefer.setRent_order_id(rent_order_id);

            rentDefer.setPi_id(piId);
            rentDefer.setArea_code(areaCode);
            rentDefer.setPi_name(parkInfo.getPi_name());
            rentDefer.setMoney(money);
            rentDefer.setUnit_price(clientGateRule.getMoney());
            rentDefer.setStarttime(carcodeParkRent.getEndtime());

            //根据用户车牌--停车场租赁映射信息的结束时间和月份数计算新的结束时间
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(carcodeParkRent.getEndtime());
            calendar.add(Calendar.MONTH, deferByAPP.getMonth_num());
            Date newEndTime = calendar.getTime();
            rentDefer.setEndtime(newEndTime);

            rentDefer.setMonth_num(deferByAPP.getMonth_num());
            Date date = new Date();
            rentDefer.setCtime(date);
            rentDefer.setStime(date);
            rentDefer.setUtime(date);
            rentDefer.setUi_id(deferByAPP.getUi_id());
            rentDefer.setUi_nd(ui_nd);
            rentDefer.setCar_code(carCode);
            rentDefer.setPu_id(parkInfo.getPu_id());
            rentDefer.setPu_nd(parkInfo.getPu_nd());
            rentDefer.setPermit_time(clientGateRule.getPermit_time());   //允许时间段（8：00-23：00）
            rentDefer.setPay_state(0);   //支付状态（0：未支付 1：支付成功 2：支付失败）
            rentDefer.setDefer_state(1);   //续约状态（0：未续约 1：续约中 2：续约成功 3：续约失败 4：续约超时失败-退款钱包）
            rentDefer.setUp_orderid("");
            rentDefer.setFlag(2);    //续约来源（0：未知 1：线下道闸租赁  2：线上 3：线下道闸续租）
            rentDefer.setPay_source(deferByAPP.getPay_source());  //支付类型0：现金 1:支付宝 2：微信 3：银联 4：钱包 5:龙支付 6:ETC快捷支付

            rentDefer.setMq_state(0);   //是否MQ推送（0：没有 1：推送成功 2：推送失败）
            rentDefer.setRent_type(deferByAPP.getRent_type());
            rentDefer.setIs_del(0);  // 是否逻辑删除(0:正常 1：删除)
            rentDefer.setNote("APP续租租赁信息");
            rentDefer.setClient_order_id(fatherRentOrderId);
            rentDefer.setFather_order_id(fatherRentOrderId);
            rentDefer.setSon_order_id("");
            rentDefer.setIs_expire(0);      //是否已经到期（0：未到期1：已经到期）
            rentDefer.setClient_rule_id(carcodeParkRent.getClient_rule_id());
            //插入租赁订单续约信息
            int id = rent_deferDao.insert(rentDefer);
            if (id < 1) {
                returnData.setReturnData(errorcode_systerm, returnUserMsg, "");
                returnData.setErrorcode("1000");
                return;
            }
            rentDefer.setRd_id(id);

            //如果是钱包支付则直接进行扣款操作
            try {
                if (4 == deferByAPP.getPay_source()) {
                    appPayTransaction.appRentBalancePay(rentDefer, userInfo, returnData);
                    if ("0" == returnData.getErrorno()) {
                        try{
                            returnData.setReturnData(errorcode_success, "租赁续租订单，钱包支付成功！", rentDefer);
                            //插入消息队列
                            JPushMessageBean mqMsgBean = new JPushMessageBean();
                            mqMsgBean.setType(3);  //类型 0：系统消息  1：预约推送 2：租赁推送 3：线上续租
                            mqMsgBean.setDate(new Date());
                            mqMsgBean.setMessage("租赁续租订单续租，钱包支付成功");
                            mqMsgBean.setTitle("租赁续租消息");
                            JSONObject object = new JSONObject();
                            object.put("rent_defer", rentDefer);
                            //object.put("type", 3);// 类型 0：系统消息  1：预约推送 2：租赁推送 3：线上续租
                            mqMsgBean.setMessageJson(object);
                            rabbitPublisher.publish2Gate(rentDefer.getArea_code(), rentDefer.pi_id, mqMsgBean, true);
                        }catch (Exception e){
                            log.error("发送mq消息异常-->",e);
                        }
                    }
                }
            } catch (Exception e) {
                log.error("钱包支付扣款失败", e);
                returnData.setReturnData(errorcode_data, "租赁续租订单，钱包支付失败！", rentDefer);
                return;
            }

            returnData.setReturnData(errorcode_success, "租赁续租正在处理，请等待！", rentDefer);

        } catch (Exception e) {
            log.error("异常", e);
            returnData.setReturnData(errorcode_systerm, "system is error", "");
            returnData.setErrorcode("1000");
            throw new QzException("租赁订单续约新增失败");
        }
    }

    /**
     * 道闸租赁信息删除
     *
     * @return
     */
    @RequestMapping("/rentdeferdel")
    public void rentDeferDel(CarcodeParkRentDel rentDeferDel, ReturnDataNew returnData) throws Exception {
        try{
            Carcode_park_rent carcodeParkRent = carcodeParkRentDao.selectByKey(rentDeferDel.getCpr_id());
            if (null != carcodeParkRent) {
                if (1 == carcodeParkRent.getIs_del()) {
                    log.info("用户车牌--停车场租赁映射记录已被删除");
                    returnData.setReturnData(errorcode_param, "租赁信息已经被删除", "");
                    returnData.setErrorcode("1003");
                    return;
                }
            } else {
                returnData.setReturnData(errorcode_param, "道闸租赁信息不存在", "");
                returnData.setErrorcode("1001");
                return;
            }

            if (0 == carcodeParkRent.getIs_expire()) {
                returnData.setReturnData(errorcode_param, "租赁信息未到期不能删除", "");
                returnData.setErrorcode("1015");
                return;
            }

            carcodeParkRent.setIs_del(1);
            carcodeParkRent.setUtime(new Date());
            carcodeParkRent.setNote("删除租赁信息");
            int result = carcodeParkRentDao.updateByKey(carcodeParkRent);
            if (result >= 1) {
                returnData.setReturnData(errorcode_success, "操作成功", null);
            } else {
                returnData.setReturnData(errorcode_systerm, "操作失败", null);
            }
        }catch (Exception e){
            log.error("系统异常",e);
            returnData.setReturnData(errorcode_systerm,"system is error","");
            returnData.setErrorcode("1000");
        }

    }

    /**
     * 判断是否能够进行续租，能续租：true ，不能续租：false
     * @return
     */
    public boolean isRentDefer(CheckRentDeferInfo deferByAPP, ReturnDataNew returnData) throws Exception{
        Carcode_park_rent carcodeParkRent = carcodeParkRentDao.selectByKey(deferByAPP.getCpr_id());
        if (null == carcodeParkRent) {
            log.info("用户车牌--停车场租赁映射记录不存在，续租失败");
            returnData.setReturnData(errorcode_param, returnUserMsg, "");
            returnData.setErrorcode("1001");
            return false;
        }

        //判断是否过期
        //if (1 == carcodeParkRent.getIs_expire() || (System.currentTimeMillis() - carcodeParkRent.getEndtime().getTime() > 0)) {
        if (1 == carcodeParkRent.getIs_expire()) {
            log.info("用户车牌--停车场租赁映射记录已经过期");
            returnData.setReturnData(errorcode_param, "租赁信息已过期", "");
            returnData.setErrorcode("1002");
            return false;
        }

        //判断是否已删除
        if (1 == carcodeParkRent.getIs_del()) {
            log.info("用户车牌--停车场租赁映射记录已被删除");
            returnData.setReturnData(errorcode_param, "租赁信息已删除", "");
            returnData.setErrorcode("1003");
            return false;
        }

        long piId = carcodeParkRent.getPi_id();
        String areaCode = carcodeParkRent.getArea_code();
        String carCode = carcodeParkRent.getCar_code();
        //检查停车场是否存在
        Park_info parkInfo = returnParkInfo(piId, areaCode);
        if (parkInfo == null) {
            log.info("停车场信息不存在");
            returnData.setReturnData(errorcode_data, returnUserMsg, "");
            returnData.setErrorcode("1004");
            return false;
        }

        //判断停车场线上续租功能是否开启
        if (0 == parkInfo.getRelet_state()) {
            log.info("停车场[" + piId + "]线上续租功能没有开启");
            returnData.setReturnData(errorcode_data, "【" + parkInfo.getPi_name() + "】停车场线上续租功能没有开启，请联系停车场管理员！", "");
            returnData.setErrorcode("1005");
            return false;
        }

        User_info userInfo = user_infoDao.selectByKey(deferByAPP.getUi_id());
        if (null == userInfo) {
            log.info("用户不存在");
            returnData.setReturnData(errorcode_data, returnUserMsg, "");
            returnData.setErrorcode("1006");
            return false;
        }

        String ui_nd = userInfo.getUuid();

        //通过车牌号获取用户ui_id,ui_nd
        User_carcode userCarcode = carBiz.queryUserCarBycode(deferByAPP.getCar_code());
        if (null != userCarcode) {
            if (deferByAPP.getUi_id() != userCarcode.getUi_id()) {
                log.info("车牌绑定用户不一致");
                returnData.setReturnData(errorcode_data, returnUserMsg, "");
                returnData.setErrorcode("1007");
                return false;
            }
        } else {
            log.info("该车牌号没有绑定用户");
            returnData.setReturnData(errorcode_param, returnUserMsg, "");
            returnData.setErrorcode("1008");
            return false;
        }

        //判断规则记录是否存在
        String sql = "select * from client_gate_rule t where t.client_ruleid = ? and t.pi_id = ? and t.area_code = ? ";//and t.is_del = 0 and t.state = 1";
        Client_gate_rule clientGateRule = getMySelfService().queryUniqueT(sql, Client_gate_rule.class, carcodeParkRent.getClient_rule_id(), piId, areaCode);
        if (null == clientGateRule) {
            log.info("停车场规则记录不存在");
            returnData.setReturnData(errorcode_param, returnUserMsg, "");
            returnData.setErrorcode("1009");
            return false;
        } else {
            if (0 != clientGateRule.getIs_del()) {
                log.info("停车场规则记录已被删除");
                returnData.setReturnData(errorcode_param, returnUserMsg, "");
                returnData.setErrorcode("1010");
                return false;
            }
            if (1 != clientGateRule.getState()) {
                log.info("停车场规则记录已被关闭");
                returnData.setReturnData(errorcode_param, returnUserMsg, "");
                returnData.setErrorcode("1011");
                return false;
            }
        }

        if (deferByAPP.getUnit_price() != clientGateRule.getMoney()){
            returnData.setReturnData(errorcode_data, "租赁单价发生变化，请重新刷新页面进行续租", "");
            return false;
        }

        String sqlR = "select * from rent_defer t " +
                "where t.pi_id = ? " +
                "and t.area_code = ? " +
                "and t.ui_id = ? " +
                "and t.car_code = ? " +
                "order by stime desc " +
                "limit 1";
        Rent_defer rd = getMySelfService().queryUniqueT(sqlR, Rent_defer.class, piId, areaCode, deferByAPP.getUi_id(), carCode);
        if (null != rd) {
            //续租中的不允许继续续租
            if (1 == rd.getPay_state() && 1 == rd.getDefer_state()) {
                log.info("该租赁信息正在续租，不能继续续租");
                returnData.setReturnData(errorcode_data, "该租赁信息正在续租，不能继续续租", "");
                returnData.setErrorcode("1013");
                return false;
            }
        }
        return true;
    }
}
