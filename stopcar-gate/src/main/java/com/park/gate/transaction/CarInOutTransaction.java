package com.park.gate.transaction;

import com.park.bean.*;
import com.park.exception.QzException;
import com.park.gate.action.v1.car.param.Param_CarInOut;
import com.park.mvc.service.BaseBiz;
import com.park.mvc.service.common.ActivityPB;
import com.park.mvc.service.common.PayParkPB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * 车辆出入库事务处理
 *
 * 预约订单不存在 ---1
 * 临停订单不存在 ---2
 * Created by zzy on 2017/6/14.
 */
@Service
@Transactional(rollbackFor = QzException.class)
public class CarInOutTransaction extends BaseBiz {
    @Autowired
    private PayParkPB payParkPB;

    @Autowired
    private ActivityPB activityPB;

    /**
     * 车辆入库
     * @param carInOut
     * @param car_in_out
     * @param park_info
     * @param returnData
     * @throws QzException
     */
    public void doCarIn(Param_CarInOut carInOut, Car_in_out car_in_out, Park_info park_info, ReturnDataNew returnData)
            throws QzException {
        try {
            String tradeType = carInOut.getTarde_type();
            String gov_num = car_in_out.getGov_num();

            boolean isJGPush = true;  //是否推送
            //非租赁
            if ("1".equals(tradeType) || "2".equals(tradeType)) {
                long ui_id = car_in_out.getUi_id();
                Date date = car_in_out.getCtime();

                //已注册用户
                if (ui_id > 0) {
                    User_info user_info = user_infoDao.selectByKey(ui_id);
                    Pay_park pay_park = null;
                    //预约
                    if ("1".equals(tradeType)) {

                        pay_park = payParkPB.selectOnePayPark(carInOut.getOrder_id());
                        if(null == pay_park){
                            log.info("预约订单不存在--{}",carInOut.getOrder_id());
                            returnData.setReturnData(errorcode_data,"预约订单不存在","1");
                            return;
                        }

                        //对于预约订单，客户端和服务端检查超时不一致情况目前暂不处理,即不生成新的临停订单。 2017-06-14
                        //预约下单  判断当前为止   当前时间减去预约时间 是否已经超过了60分钟  如果超过则预约扣款  如果没有超过则不进行扣款
                        //if (!isTimeOut(date,pay_park)) {
                        // 预约未超时

                        //获取该停车场的计费规则
                        Rental_charging_rule charging_rule = queryChargingRule(car_in_out);

                        //预约到场时间在规定时间内 则不扣预约费
                        if (car_in_out.is_sync == 1) {
                            pay_park.setCancel_state(3);
                        }
                        pay_park.setGov_num(gov_num);
                        pay_park.setMagnetic_state(1);
                        pay_park.setArrive_time(date);//预约到场时间
                        pay_park.setStart_price(charging_rule.getStart_price());//起步价格（单位 分）
                        pay_park.setStart_time(charging_rule.getStart_time());//起步时长（分钟）
                        pay_park.setCharging(charging_rule.getCharging());//计费价格(单位 分)
                        pay_park.setCharging_time(charging_rule.getCharging_time());//计费时长(分钟)

                        pay_park.setFree_minute(charging_rule.getFree_minute());//多少分钟之内进出免费
                        pay_park.setIs_free_minute(charging_rule.getIs_free_minute());//多少分钟之内进出免费是否开启  0:不开启  1：开启
                        if (pay_park.getExpect_state() == 1) {
                            //设置预约状态
                            pay_park.setExpect_state(2);//预约状态  1：预约中  2：预约成功  3：预约失败 4：取消预约中 5：取消预约成功 6：取消预约失败
                        }

                        //by zzy 2017-6-26 设置 是否已经扣除预约超时钱
                        pay_park.setIs_expect_deduct(2); //0：已锁定扣款金额1：已经扣款成功2：已解绑锁定金额


                        //解除锁定预约金额
                        if (null != user_info) {
                            log.info("解除锁定预约金额");
                            //解除锁定
                            int money = user_info.getLock_expect_money() - pay_park.getExpect_money() >= 0 ?
                                    user_info.getLock_expect_money() - pay_park.getExpect_money() : 0;
                            user_info.setLock_expect_money(money);
                            int count = user_infoDao.updateByKey(user_info);
                            if (count != 1) {
                                throw new QzException("预约成功车辆未超时到场，接触锁定失败");
                            }
                        }

                        //}
//                        else {
//                            //预约超时且扣款后  新生成一个临停订单
//                            cameraCarOrder(car_in_out, park_info,carInOut.getDtype(),true);
//                        }

                        //这里处理随机立减免活动事件
                        activityPB.record_random(pay_park);

                        int count = pay_parkDao.updateByKey(pay_park);
                        if (count < 1) {
                            //扣款失败
                            throw new QzException("扣款失败");
                        }
                        //注册用户非预约下单
                    } else {
                        pay_park = cameraCarOrder(car_in_out, park_info,carInOut.getDtype(),isJGPush);
                    }
                    //这里处理绑定了用户的车辆--当用户钱包余额不足10元钱的时候给予推送提醒
                    //这里处理订单状态变更推送----车辆临停到达
                    // by zzy
                    if(isJGPush){
                        pushOrderState(user_info, pay_park);
                    }


                    //非注册用户下单
                } else {
                    log.info("非注册用户下单");
                    cameraCarOrder(car_in_out, park_info,carInOut.getDtype(),isJGPush);
                }

                //租赁订单,更新pay_month_park 表信息
            } else if ("3".equals(tradeType) || "4".equals(tradeType) || "5".equals(tradeType)) {

                log.info(" 3：租赁  4：本地免费  5：本地包月车辆出入库只做出入库记录，不做订单处理");
//                Pay_month_park pay_month_park = payParkPB.selectOnePayMonthPark(car_in_out.getOrder_id());
//                if (pay_month_park != null) {
//                    car_in_out.setIs_rent(1);
//                    car_in_out.setRent_remain_time(pay_month_park.getEnd_time().getTime() - car_in_out.getCtime().getTime());
//
//                    pay_month_park.setGov_num(car_in_out.getGov_num());
//                    pay_month_park.setMagnetic_state(1);
//                    pay_month_park.setArrive_time(car_in_out.getCtime());
//                    int count = pay_month_parkDao.updateByKey(pay_month_park);
//                    if (count != 1) {
//                        //更新失败
//                        //returnData.setReturnData(errorcode_data, "更新租赁分时间段包月当天入库时间失败", "2");
//                        throw new QzException("更新租赁分时间段包月当天入库时间失败");
//                    }
//                }else{
//                    log.info("租赁订单[{}]不存在，入库失败",car_in_out.getOrder_id());
//                    returnData.setReturnData(errorcode_data,"租赁订单不存在,入库失败","2");
//                    return;
//                }
            }

        } catch (Exception e) {
            throw new QzException("事物异常 doCar_In", e);
        }
    }

    /**
     * 车辆出库
     * @param order_id
     * @param car_in_out
     * @throws QzException
     */
    public void doCarOut(String order_id, Car_in_out car_in_out)
            throws QzException {

        try {
            //出库
            //获取用户已经支付的最后一条某停车场的订单
            Pay_park pay_park = payParkPB.selectOnePayPark(order_id);
            if (pay_park != null) {
                Date date = car_in_out.getCtime();
                pay_park.setLeave_time(date);
                if (pay_park.getIs_over() == 0) {
                    pay_park.setIs_over(1);//订单是否完成(0:没有完成1：完成)
                }
                //pay_park.setMagnetic_state(2);    //by zzy 2017-6-23  出库时无需将智能磁场入库出库状态更新为 已出库
                //更新离开时间
                int count = pay_parkDao.updateByKey(pay_park);
                if (count < 1) {
                    //更新离开时间失败
                    throw new QzException("更新离开时间失败");
                }
            }
        } catch (Exception e) {
            throw new QzException("事物异常 doCar_Out", e);
        }
    }

    /**
     * 摄像头扫描到下单
     * @param car_in_out
     * @param park_info
     * @param dtype
     * @param isJGPush true：推送；false：不推送
     * @return
     * @throws QzException
     */
    public Pay_park cameraCarOrder(Car_in_out car_in_out, Park_info park_info,int dtype ,boolean isJGPush) throws QzException {
        try {
            String gov_num = car_in_out.getGov_num();

            String area_code = park_info.getArea_code();
            long ui_id = car_in_out.getUi_id();
            //车牌号
            String car_code = car_in_out.getCar_code();

            //获取该停车场的计费规则
            Rental_charging_rule charging_rule = queryChargingRule(car_in_out);

            Date date = car_in_out.getCtime();//时间依入库时间

            //by zzy 处理PDA或道闸异常未收到生成订单的结果响应时（服务端已经响应）进行异步上传，如果订单已经存在，直接返回pay_park  2017-07-04
            Pay_park pay_park = payParkPB.selectOnePayPark(car_in_out.getOrder_id());
            if(pay_park != null){
                log.info("处理PDA或道闸异常未收到生成订单的结果响应时（服务端已经响应）进行异步上传，订单已经存在 {}",car_in_out.getOrder_id());
                isJGPush = false;
                return pay_park;
            }else{
                pay_park = new Pay_park();
            }

            if (car_in_out.is_sync == 1) {
                pay_park.setCancel_state(3);
            }
            pay_park.setGov_num(gov_num);
            pay_park.setMagnetic_state(1);
            pay_park.setStart_price(charging_rule.getStart_price());//起步价格（单位 分）
            pay_park.setStart_time(charging_rule.getStart_time());//起步时长（分钟）
            pay_park.setCharging(charging_rule.getCharging());//计费价格(单位 分)
            pay_park.setCharging_time(charging_rule.getCharging_time());//计费时长(分钟)

            pay_park.setAllege_state(0);//申述状态 0:未申述 1：已申述
            pay_park.setArrive_time(date);//到场时间
            pay_park.setLeave_time(date);//离场时间
            pay_park.setOpen_time(date);
            pay_park.setScan_time(date);
            pay_park.setCar_code(car_code);//车牌号
            pay_park.setCtime(date);//创建时间
            String orderId = car_in_out.getOrder_id();

            pay_park.setMy_order(orderId);

            pay_park.setOrder_type(0);//下单类型 0: 普通下单  1：预约下单 2：租赁包月订单 3:本地包月转临停   4：本地免费转临停

            pay_park.setPi_id(car_in_out.getPi_id());//支付停车场主键ID
            pay_park.setPp_state(0);//支付状态 0:未支付 1：已经支付
            pay_park.setUi_id(ui_id);//用户ID
            if (car_in_out.getUi_nd() != null) {
                pay_park.setUi_nd(car_in_out.getUi_nd());//用户唯一标识符
            }
            if (car_in_out.getUi_tel() != null) {
                pay_park.setUi_tel(car_in_out.getUi_tel());
            }
            pay_park.setUtime(date);//更新时间
            pay_park.setAddress_name(park_info.getAddress_name());//停车场地址
            pay_park.setPi_name(park_info.getPi_name());//停车场名称
            pay_park.setArea_code(area_code);//省市县编号
            pay_park.setPark_type(park_info.getPark_type());

            //省市县区域代码
            pay_park.setArea_code(car_in_out.getArea_code());

            pay_park.setFree_minute(charging_rule.getFree_minute());//多少分钟之内进出免费
            pay_park.setIs_free_minute(charging_rule.getIs_free_minute());//多少分钟之内进出免费是否开启  0:不开启  1：开启

            //设置商户唯一标识
            pay_park.setPu_id(park_info.getPu_id());
            pay_park.setPu_nd(park_info.getPu_nd());

            //设置经纬度
            pay_park.setLng(park_info.getLng());
            pay_park.setLat(park_info.getLat());

            //这里处理随机立减免活动事件
            activityPB.record_random(pay_park);

            int id = pay_parkDao.insert(pay_park);
            if (id < 1) {
                //下单失败
                log.error("ParkinfoBiz.cameraCarOrder is error 下单插入的时候失败");
                throw new QzException("ParkinfoBiz.cameraCarOrder is error 下单插入的时候失败");
            }

            return pay_park;
        } catch (Exception e) {
            // TODO Auto-generated catch block
            log.error("ParkinfoBiz.cameraCarOrder is error" + e.getMessage(), e);
            throw new QzException("摄像头扫描到下单 失败", e);
        }
    }
    /**
     * //获取该停车场的计费规则 rcr_type ：停车类型 0：普通车位停车 1：时间段包月停车
     * @param carInOut
     * @return
     * @throws QzException
     */
    public Rental_charging_rule queryChargingRule(Car_in_out carInOut) throws QzException{
        int rcr_type = 0;
        Rental_charging_rule charging_rule = payParkPB.queryChargeRule(carInOut.getPi_id(), rcr_type,
                carInOut.getCar_type(), carInOut.getArea_code(), carInOut.getCar_code_color());
        if (charging_rule == null) {
            //没有获取到停车规则，获取默认规则，如果默认规则还没有获取到，抛出异常   by zzy 2017-06-29
            charging_rule = payParkPB.queryChargeDefaultRule(carInOut.getPi_id(),rcr_type,carInOut.getArea_code());
            if(null == charging_rule){
                //下单失败
                log.info("没有获取到停车场规则，获取停车场默认规则，获取默认规则失败  pi_id:{}，area_code:{}",carInOut.getPi_id(),carInOut.getArea_code());
                throw new QzException("预约未超时--无对应的停车场规则信息");
            }
        }
        return charging_rule;
    }
    /**
     * 生成订单号
     * @param carInOut
     * @return
     */
    public String getOrderId(int dType,Car_in_out carInOut){
        int product_type = 0;//产品标号1位：0 道闸 1 PDA  2 地磁PDA  3 mepos道闸
        switch (dType) {//（0:android 1:ios 2:PDA 3: web 4:道闸 5：MEPOS 6：地磁设备android板）
            case 2:
                product_type = 1;
                break;
            case 4:
                product_type = 0;
                break;
            case 5:
                product_type = 3;
                break;
            case 6:
                product_type = 2;
                break;
        }
        int order_type = 0;//订单类型：0 临停订单 1 本地包月订单 2 本地免费订单 3  预约订单 4 租赁订单 （其中预约订单和租赁订单生成都由服务器端生成）
        switch (carInOut.getOut_type()) {//;//入库/出库类型:
            // (0:正常出入库 1：道闸本地临停出入库  2：道闸本地包月出入库   3：异常出入库   4：道闸本地免费车出入库   5:预约车辆出入库  6:租赁车辆出入库)
//          case 0:
            case 1:
                order_type = 0;
                break;
            case 2:
                order_type = 1;
                break;
            case 4:
                order_type = 2;
                break;
            case 5:
                order_type = 3;
                break;
            case 6:
                order_type = 4;
                break;
        }
        return returnNewOrderId(carInOut.getArea_code(), product_type, carInOut.getPi_id(), order_type);//我们自己生成的订单号
    }
    /**
     * 这里处理绑定了用户的车辆--当用户钱包余额不足10元钱的时候给予推送提醒
     * 这里处理订单状态变更推送----车辆临停到达
     * 这里处理推送---车辆临停到达
     * 金额单位为 分
     */
    private void pushOrderState(User_info user_info, Pay_park pay_park) {
        if (null != user_info) {
            String title = "系统消息";
            String message = "亲，你的车牌号为【" + pay_park.getCar_code() + "】的车辆已到达【" + pay_park.getPi_name() + "】停车场";
            //if (user_info.getUi_vc() < 1000) {
            //by zzy 2017-6-23 判断用户钱包余额不足10元钱 需要扣除用户预约锁定金额
            if(user_info.getUi_vc() - user_info.getLock_expect_money() < 1000){
                //如果钱包金额小于10元
                message += "，您钱包余额已经不足10元钱";
                if (user_info.getUi_autopay() == 0) {
                    message += "，且没有开启自动支付功能。";
                }
            } else {
                if (user_info.getUi_autopay() == 0) {
                    message += "，您还没有开启自动支付功能哦！";
                }
            }
            payParkPB.pushOrderSate(pay_park.getUi_nd(), message, title, pay_park);
        }
    }
}
