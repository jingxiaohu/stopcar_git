package com.park.gate.service;

import com.park.bean.*;
import com.park.dao.Carcode_park_rentDao;
import com.park.dao.Rent_deferDao;
import com.park.exception.QzException;
import com.park.gate.action.v1.order.param.rentDefer.*;
import com.park.mvc.service.BaseBiz;
import com.park.mvc.service.RentDeferCoreBiz;
import com.park.task.AsyncSMSTask;
import com.park.util.MoneyUtil;
import com.park.util.RequestUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by zzy on 2017/6/30.
 */
@Service("rentDeferByGateBiz")
public class RentDeferByGateBiz extends BaseBiz {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private Rent_deferDao rent_deferDao;

    @Autowired
    private Carcode_park_rentDao carcodeParkRentDao;

    @Autowired
    private GateCarBiz gateCarBiz;

    @Autowired
    private RentDeferCoreBiz rentDeferCoreBiz;

    @Autowired
    private AsyncSMSTask asyncSMSTask;

    private static final String PAY_MENT_MSG = "尊敬的用户%s,您于%s成功续租%s停车场，并付款%s元【吾泊停车】";

    /**
     * 道闸租赁订单续约新增
     *
     * @param rentDeferAdd
     * @param returnDataNew
     */
    @Transactional(rollbackFor = QzException.class)
    public void rentDeferAdd(RentDeferAdd rentDeferAdd, ReturnDataNew returnDataNew) throws QzException {
        try{
            long pi_id = rentDeferAdd.getPi_id();
            String area_code = rentDeferAdd.getArea_code();
            String client_order_id = rentDeferAdd.getClient_order_id();
            String carCode = rentDeferAdd.getCar_code();
            //检查停车场是否存在
            Park_info parkInfo = returnParkInfo(pi_id, area_code);
            if (parkInfo == null) {
                returnDataNew.setReturnData(errorcode_data, "停车场不存在", "001");
                return;
            }

            String sql = "select * from rent_defer t where t.pi_id = ? and t.area_code = ? and t.client_order_id = ?";
            Rent_defer qDefer = getMySelfService().queryUniqueT(sql, Rent_defer.class, pi_id, area_code, rentDeferAdd.getClient_order_id());
            if(null != qDefer){
                log.info("区域代码{} 停车场{}客户端租赁订单{}已经存在",area_code,pi_id,client_order_id);
                returnDataNew.setReturnData(errorcode_data,
                        "区域代码"+area_code+"停车场"+pi_id+"客户端租赁订单"+client_order_id+"已经存在", "002");
                return;
            }

            long ui_id = 0l;
            String ui_nd = "";
            //
            User_carcode userCarcode = gateCarBiz.queryUserCarBycode(rentDeferAdd.getCar_code());
            if (null != userCarcode) {
                ui_id = userCarcode.getUi_id();
                ui_nd = userCarcode.getUi_nd();
            }

            String sqlRule = "select * from client_gate_rule t where t.pi_id = ? and t.area_code = ? and t.client_ruleid = ?";
            Client_gate_rule clientGateRule = getMySelfService().queryUniqueT(sqlRule, Client_gate_rule.class, pi_id, area_code, rentDeferAdd.getClient_rule_id());
            if(null == clientGateRule){
                returnDataNew.setReturnData(errorcode_data,"区域代码"+area_code+"停车场"+pi_id+"规则"+rentDeferAdd.getClient_rule_id()+"不存在", "002");
                return;
            }else{
                if(1 == clientGateRule.getIs_del()){
                    returnDataNew.setReturnData(errorcode_data,"区域代码"+area_code+"停车场"+pi_id+"规则"+rentDeferAdd.getClient_rule_id()+"已删除", "002");
                    return;
                }
                //只是表示线上
//                if (1 != clientGateRule.getState()) {
//                    log.info("停车场规则记录已被关闭");
//                    returnDataNew.setReturnData(errorcode_data,"区域代码"+area_code+"停车场"+pi_id+"规则"+rentDeferAdd.getClient_rule_id()+"已关闭","");
//                    return;
//                }
            }

            String rentOrderId = rentDeferCoreBiz.makeRentOrderId(area_code,rentDeferAdd.getDtype(),pi_id,4);
            Date date = new Date();
            Rent_defer rentDefer = new Rent_defer();
            rentDefer.setRent_order_id(rentOrderId);
            rentDefer.setPi_id(pi_id);
            rentDefer.setArea_code(area_code);
            rentDefer.setPi_name(parkInfo.getPi_name());
            rentDefer.setMoney(rentDeferAdd.getMoney());
            rentDefer.setUnit_price(rentDeferAdd.getUnit_price());
            rentDefer.setStarttime(new Date(rentDeferAdd.getStarttime()));
            rentDefer.setEndtime(new Date(rentDeferAdd.getEndtime()));
            rentDefer.setMonth_num(rentDeferAdd.getMonth_num());
            rentDefer.setCtime(date);
            rentDefer.setStime(date);
            rentDefer.setUtime(date);
            rentDefer.setUi_id(ui_id);
            rentDefer.setUi_nd(ui_nd);
            rentDefer.setCar_code(rentDeferAdd.getCar_code());
            rentDefer.setPu_id(parkInfo.getPu_id());
            rentDefer.setPu_nd(parkInfo.getPu_nd());
            rentDefer.setPermit_time(rentDeferAdd.getPermit_time());   //允许时间段（8：00-23：00）
            rentDefer.setPay_state(1);   //支付状态（0：未支付1：支付成功2：支付失败）
            rentDefer.setDefer_state(2);   //续约状态（0：未续约1：续约中2：续约成功3：续约失败4：续约超时失败-退款钱包）
            rentDefer.setUp_orderid("");

            int flag = 0; //续约来源（0：未知 1：线下道闸租赁  2：线上 3：线下道闸续租）
            String clientFatherOrderId = rentDeferAdd.getClient_father_orderid();
            if(RequestUtil.checkObjectBlank(clientFatherOrderId)){
                flag = 1;
            }else{
                flag = 3;
            }
            rentDefer.setFlag(flag);

            rentDefer.setPay_source(rentDeferAdd.getPay_source());    //支付类型0：现金 1:支付宝 2：微信 3：银联 4：钱包 5:龙支付 6:ETC快捷支付
            rentDefer.setMq_state(0);   //是否MQ推送（0：没有 1：推送成功 2：推送失败）
            rentDefer.setRent_type(rentDeferAdd.getRent_type());
            rentDefer.setIs_del(0);  // 是否逻辑删除(0:正常 1：删除)   暂时没有使用
            //rentDefer.setFather_order_id(RequestUtil.checkObjectBlank(rentDeferAdd.getClient_father_orderid()) ? "" : rentDeferAdd.getClient_father_orderid());
            rentDefer.setFather_order_id(rentDeferAdd.getClient_order_id());
            rentDefer.setSon_order_id("");
            rentDefer.setIs_expire(0);      //是否已经到期（0：未到期1：已经到期）    暂时没有使用
            rentDefer.setClient_order_id(client_order_id);
            rentDefer.setClient_rule_id(rentDeferAdd.getClient_rule_id());
            rentDefer.setNote("创建租赁订单流水");

            int id = rent_deferDao.insert(rentDefer);  //返回生成的主键id
            if (id < 1) {
                returnDataNew.setReturnData(errorcode_systerm, "道闸租赁续约新增失败", "003");
                return;
            }
            rentDefer.setRd_id(id);

            //
            Carcode_park_rent carcodeParkRent = rentDeferCoreBiz.getCarcodeParkRent(carCode,pi_id,area_code);
            if(null == carcodeParkRent){
                //新增用户车牌--停车场租赁数据
                carcodeParkRent = new Carcode_park_rent();
                carcodeParkRent.setPi_id(rentDefer.getPi_id());
                carcodeParkRent.setArea_code(rentDefer.getArea_code());
                carcodeParkRent.setPi_name(rentDefer.getPi_name());
                carcodeParkRent.setUnit_price(rentDefer.getUnit_price());
                carcodeParkRent.setStarttime(rentDefer.getStarttime());
                carcodeParkRent.setEndtime(rentDefer.getEndtime());
                Date cdate = new Date();
                carcodeParkRent.setStime(cdate);
                carcodeParkRent.setUtime(cdate);
                carcodeParkRent.setUi_id(rentDefer.getUi_id());
                carcodeParkRent.setUi_nd(rentDefer.getUi_nd());
                carcodeParkRent.setCar_code(rentDefer.getCar_code());
                carcodeParkRent.setPermit_time(rentDefer.getPermit_time());
                carcodeParkRent.setRent_type(rentDefer.getRent_type());
                carcodeParkRent.setIs_del(0);
                carcodeParkRent.setIs_expire(0);
                carcodeParkRent.setNote("创建道闸租赁信息");
                carcodeParkRent.setClient_rule_id(rentDefer.getClient_rule_id());
                carcodeParkRent.setAddress_name(parkInfo.getAddress_name());
                carcodeParkRent.setRd_id(rentDefer.getRd_id());//rent_defer 表主键ID（最后一次修改carcode_park_rent表对应的ID）
                carcodeParkRent.setClient_father_orderid(rentDefer.getClient_order_id());
                
                int rd_id = carcodeParkRentDao.insert(carcodeParkRent);
                if( rd_id < 1){
                    log.info("用户车牌--停车场租赁新增失败");
                    throw new QzException("用户车牌--停车场租赁新增失败");
                }
            }else{

                //已经删除或者过期
                if(carcodeParkRent.getIs_del() != 0 || carcodeParkRent.getIs_expire() != 0){
                    //新增用户车牌--停车场租赁数据
                    //carcodeParkRent = new Carcode_park_rent();
                    carcodeParkRent.setClient_father_orderid(rentDefer.getClient_order_id());
                    carcodeParkRent.setPi_id(rentDefer.getPi_id());
                    carcodeParkRent.setArea_code(rentDefer.getArea_code());
                    carcodeParkRent.setPi_name(rentDefer.getPi_name());
                    carcodeParkRent.setUnit_price(rentDefer.getUnit_price());
                    carcodeParkRent.setStarttime(rentDefer.getStarttime());
                    carcodeParkRent.setEndtime(rentDefer.getEndtime());
                    Date cdate = new Date();
                    carcodeParkRent.setStime(cdate);
                    carcodeParkRent.setUtime(cdate);
                    carcodeParkRent.setUi_id(rentDefer.getUi_id());
                    carcodeParkRent.setUi_nd(rentDefer.getUi_nd());
                    carcodeParkRent.setCar_code(rentDefer.getCar_code());
                    carcodeParkRent.setPermit_time(rentDefer.getPermit_time());
                    carcodeParkRent.setRent_type(rentDefer.getRent_type());
                    carcodeParkRent.setIs_del(0);
                    carcodeParkRent.setIs_expire(0);
                    carcodeParkRent.setNote("创建道闸租赁信息");
                    carcodeParkRent.setClient_rule_id(rentDefer.getClient_rule_id());
                    carcodeParkRent.setAddress_name(parkInfo.getAddress_name());
                    carcodeParkRent.setRd_id(rentDefer.getRd_id());//rent_defer 表主键ID（最后一次修改carcode_park_rent表对应的ID）
                }else{
                    carcodeParkRent.setEndtime(rentDefer.getEndtime());
                    Date t = new Date();
                    carcodeParkRent.setUtime(t);
                    carcodeParkRent.setUnit_price(rentDefer.getUnit_price());
                    carcodeParkRent.setPermit_time(rentDefer.getPermit_time());
                    carcodeParkRent.setClient_rule_id(rentDefer.getClient_rule_id());
                    carcodeParkRent.setIs_del(0);
                    carcodeParkRent.setIs_expire(0);
                    carcodeParkRent.setNote("道闸租赁续租");
                }

                int up = carcodeParkRentDao.updateByKey(carcodeParkRent);
                if(up < 1){
                    log.info("用户车牌--停车场租赁更新失败");
                    throw new QzException("用户车牌--停车场租赁更新失败");
                }
            }

            returnDataNew.setReturnData(errorcode_success, "道闸租赁续约新增成功", rentDefer);

        }catch (Exception e){
            log.error("数据库错误",e);
            throw new QzException("道闸租赁续约新增失败");
        }

    }

    /**
     * 道闸租赁续约是否到期状态修改
     *
     * @param rentDeferState
     * @return
     */
    public void updateIsExpireState(RentDeferExpireState rentDeferState, ReturnDataNew returnDataNew) throws Exception {
        String carCode = rentDeferState.getCar_code();
        Long piId = rentDeferState.getPi_id();
        String areaCode = rentDeferState.getArea_code();
        Carcode_park_rent carcodeParkRent = rentDeferCoreBiz.getCarcodeParkRent(carCode,piId,areaCode);

        if (null == carcodeParkRent || carcodeParkRent.getIs_del() != 0) {
            returnDataNew.setReturnData(errorcode_param, "租赁信息不存在或已被删除", null);
            return;
        }

        carcodeParkRent.setIs_expire(rentDeferState.getExpire_state());
        carcodeParkRent.setUtime(new Date());
        carcodeParkRent.setNote("修改是否到期状态");
        int result = carcodeParkRentDao.updateByKey(carcodeParkRent);
        if (result >= 1) {
            returnDataNew.setReturnData(errorcode_success, "操作成功", null);
        } else {
            returnDataNew.setReturnData(errorcode_systerm, "操作失败", null);
        }
    }

    /**
     * 支付成功--道闸租赁续租成功确认
     * 续约状态（0：未续约  1：续约中  2：续约成功 3：续约失败  4：续约超时失败-退款钱包）
     * @throws Exception
     */
    @Transactional(rollbackFor = QzException.class)
    public void rentDeferSure(RentDeferSure rentDeferSure,ReturnDataNew returnDataNew) throws QzException{
        String rent_order_id = rentDeferSure.getRent_order_id();
        Rent_defer rentDefer = rentDeferCoreBiz.getRentDeferByRentOrderId(rent_order_id);
        if (null == rentDefer) {
            log.info("道闸租赁订单不存在{}",rent_order_id);
            returnDataNew.setReturnData(errorcode_param, "租赁订单不存在", null);
            return;
        }

        //不是支付成功、续租中
        if(!(1 == rentDefer.getPay_state() && 1 == rentDefer.getDefer_state())){
            returnDataNew.setReturnData(errorcode_param, "租赁续租订单已经超时，续租失败", null);
            return;
        }

        rentDefer.setDefer_state(2);   //续约状态（0：未续约1：续约中2：续约成功3：续约失败4：续约超时失败-退款钱包）
        rentDefer.setUtime(new Date());
        rentDefer.setEndtime(new Date(rentDeferSure.getEndtime()));
        rentDefer.setNote("APP租赁续租成功");
        int result = rent_deferDao.updateByKey(rentDefer);
        if(result < 1){
            returnDataNew.setReturnData(errorcode_success,"道闸租赁续租确认失败",null);
            return;
        }

        String carCode  = rentDeferSure.getCar_code();
        long piId = rentDeferSure.getPi_id();
        String areaCode = rentDeferSure.getArea_code();
        Carcode_park_rent carcodeParkRent = rentDeferCoreBiz.getCarcodeParkRent(carCode,piId,areaCode);
        if(null == carcodeParkRent || carcodeParkRent.getIs_del() != 0 || carcodeParkRent.getIs_expire() != 0){
            log.info("用户车辆用户车牌--停车场租赁信息不存在或已到期或被删除");
            throw new QzException("租赁订单续租新增失败");
        }

        //更新用户车牌--停车场租赁映射表
        Date newEndTime = new Date(rentDeferSure.getEndtime());
        carcodeParkRent.setEndtime(newEndTime);
        carcodeParkRent.setUtime(new Date());
        carcodeParkRent.setUnit_price(rentDefer.getUnit_price());
        carcodeParkRent.setPermit_time(rentDefer.getPermit_time());
        carcodeParkRent.setRd_id(rentDefer.getRd_id());
        carcodeParkRent.setNote("APP用户续租成功");
        int id = carcodeParkRentDao.updateByKey(carcodeParkRent);
        if(id < 1){
            log.error("租赁订单续租新增失败");
            throw new QzException("租赁订单续租新增失败");
        }

        returnDataNew.setReturnData(errorcode_success,"道闸租赁续租成功确认成功",null);

        try{
            //极光推送和发送短信
            String message = "亲，您的续租订单【"+rentDefer.getRent_order_id()+"】续租成功！";
            rentDeferCoreBiz.jpushMessage(message,rentDefer);
            String DATE_FORMAT = "yyyy年MM日 HH时mm分";
            //这里使用异步的，与业务逻辑分开  //TODO
            User_info userinfo = user_infoDao.selectByKey(rentDefer.getUi_id());
            if(userinfo != null){
                String phone = userinfo.getUi_tel();
                long amt = rentDefer.getMoney();
                Date payDate = rentDefer.getUtime();
                String dateStr = new SimpleDateFormat(DATE_FORMAT).format(payDate);
                String amount = MoneyUtil.toYun(amt).toPlainString();
                String sms = String.format(PAY_MENT_MSG, phone, dateStr,rentDefer.getPi_name(),amount);
                asyncSMSTask.sendMessage(phone,sms);
            }
        }catch (Exception e){
            log.error("发送短信消息异常",e);
        }
    }

    /**
     * 支付成功--道闸续租失败
     */
    public void rentDeferFail(RentDeferSure rentDeferSure,ReturnDataNew returnDataNew){
        log.info("支付成功--道闸续租失败订单-->"+rentDeferSure.getRent_order_id());
        String rent_order_id = rentDeferSure.getRent_order_id();
        Rent_defer rentDefer = rentDeferCoreBiz.getRentDeferByRentOrderId(rent_order_id);
        if (null == rentDefer) {
            log.info("道闸租赁订单不存在{}",rent_order_id);
            returnDataNew.setReturnData(errorcode_param, "租赁订单不存在", null);
            return;
        }

        rentDefer.setDefer_state(4);
        rentDefer.setNote(rentDeferSure.getNote());
        try{
            rentDeferCoreBiz.excuteRentDeferOrderRefund(rentDefer);
            returnDataNew.setReturnData(errorcode_success,"道闸租赁续租失败确认成功",null);
        }catch (Exception e){
            log.error("续租失败处理异常-->",e);
            returnDataNew.setReturnData(errorcode_data,"道闸租赁续租失败确认失败",null);
        }

    }

    /**
     * 道闸租赁续约信息获取
     *
     * @param returnData
     * @return
     */
    public void getRentDeferInfo(ReturnDataNew returnData, RentDeferSelect param) {
        try {
            //检查停车场信息是否存在
            Park_info parkInfo = returnParkInfo(param.getPi_id(), param.getArea_code());
            if (parkInfo == null) {
                returnData.setReturnData(errorcode_data, "该停车场信息不存在", "");
                return;
            }

            int page = param.page;
            int size = param.size;
            if (page < 1) {
                page = 1;
            }
            int start = (page - 1) * size;

            StringBuffer sql = new StringBuffer();
            sql.append("select * from rent_defer t where t.pi_id = " + param.getPi_id() + " and t.area_code= " + param.getArea_code());
            if (param.getRent_order_id() != null || param.getRent_order_id() != "") {
                sql.append(" and t.rent_order_id= " + param.getRent_order_id());
            }
            if (param.getPay_state() != null) {
                sql.append(" and t.pay_state= " + param.getPay_state());
            }
            if (param.getIs_expire() != null) {
                sql.append(" and t.is_expire= " + param.getIs_expire());
            }
            sql.append(" order by t.stime desc limit " + start + "," + size);

            List<Rent_defer> rent_defer_list = getMySelfService().queryListT(sql.toString(), Rent_defer.class);
            if (rent_defer_list == null || rent_defer_list.size() == 0) {
                returnData.setReturnData(errorcode_success, "未获取到数据", rent_defer_list);
                return;
            }
            //返回数据
            returnData.setReturnData(errorcode_success, "道闸租赁续约信息获取成功", rent_defer_list);
            return;
        } catch (Exception e) {
            log.error("RentDeferByGateBiz getRentDeferInfo is error", e);
            returnData.setReturnData(errorcode_systerm, "system is error", null);
            return;
        }
    }

}
