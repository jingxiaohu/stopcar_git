package com.park.gate.service;

import com.alibaba.fastjson.JSONObject;
import com.park.DataSource.DynamicDataSourceHolder;
import com.park.DataSource.TargetDataSource;
import com.park.bean.*;
import com.park.mvc.service.BaseBiz;
import com.park.mvc.service.common.PayParkPB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 处理车辆信息的业务逻辑管理类
 *
 * @author jingxiaohu
 */
@Service
public class GateCheckCarPayBiz extends BaseBiz {

    @Autowired
    private PayParkPB payParkPB;


    /**
     * 检查某停车场某车牌号是否已经付款
     */
    @TargetDataSource(value = DynamicDataSourceHolder.SLAVE)
    public void read_checkpay(ReturnDataNew returnData, long pi_id,
                              String car_code, String area_code, String orderid) {
        // TODO Auto-generated method stub
        try {
            JSONObject returnobj = new JSONObject();
            long ui_id = 0;
            //从订单表中 获取该用户的停车缴费订单信息
            Pay_park pay_park = payParkPB.selectOnePayPark(orderid);
            if (pay_park == null) {
          /*//检查该车辆是否是 分时段包月车辆
          String sql = "select *  from pay_month_park where pi_id=? and area_code=? and car_code=? and my_order=? order by ctime desc limit 1";
					Pay_month_park pay_month_park = getMySelfService().queryUniqueT(sql, Pay_month_park.class, pi_id,area_code,car_code,orderid);
					if(pay_month_park == null){
						//该车辆未出入
						returnData.setReturnData(errorcode_data, "该车辆未出入", ""); 
						return;
					}else{
						ui_id = pay_month_park.getUi_id();
						Date date = new Date();
						//检查该车辆分时段包月是否已经逾期
						if(pay_month_park.getEnd_time().getTime() < date.getTime()){
							//已经逾期 则进行逾期缴费处理----按临停计费处理
							long outtime_minute = 0;//超时时长（单位分钟）
							//设置逾期金额  且进行更新
							
							
						}
						
					}*/
                //返回结果
                returnData.setReturnData(errorcode_data, "该临停订单不存在", returnobj);
                return;
            } else {
                ui_id = pay_park.getUi_id();
            }
            //订单信息
            returnobj.put("ui_id", ui_id);
            returnobj.put("car_code", car_code);
            //这里检查该订单是否是 占道停车场订单    Park_type停车场类型0：道闸停车场1：占道车场2：露天立体车库停车场
            if (pay_park.getPark_type() == 1) {
                //占道停车场
                //先检查  第三方支付回调表是否有订单且  是否已经支付
                /**
                 * 进行非完全抵扣情况判断
                 */
                User_pay callPay = payParkPB.isCallPay(pay_park.getMy_order());
                if (callPay == null) {
                    //第三方还没有回调成功 或者 没有订单 在 User_pay里面
                    //非占道停车场
                    returnobj.put("state", pay_park.getPp_state());//是否支付  0：未支付 1：已经支付
                    returnobj.put("pay_source", 0);//支付类型
                    returnobj.put("escape_orderids", "");//补交订单集合
                } else {
                    //已经支付 设置
                    returnobj.put("state", 1);//是否支付  0：未支付 1：已经支付
                    returnobj.put("pay_source", callPay.getType());//支付类型
                    returnobj.put("escape_orderids", callPay.getEscape_orderids());//补交订单集合
                }
            } else {
                //非占道停车场
                returnobj.put("state", pay_park.getPp_state());//是否支付  0：未支付 1：已经支付
            }
            returnobj.put("money", pay_park.getMoney());//支付金额 单位 分
            //by jxh 2017-8-17 因为道闸那边需要通过 是否是现金支付进行判断 是否执行接口 pay_sure
            // 定义返回数据 is_cash  是否现金支付0：在线支付1：现金支付
            if(returnobj.getIntValue("state") == 1 && pay_park.getPay_source() == 0){
                //表示现金支付
                returnobj.put("is_cash", 1);//是否现金支付0：在线支付1：现金支付
            }else{
                returnobj.put("is_cash", 0);//是否现金支付0：在线支付1：现金支付
            }
            //返回结果
            returnData.setReturnData(errorcode_success, "查询是否已经付款成功", returnobj);
            return;
        } catch (Exception e) {
            // TODO Auto-generated catch block
            log.error("gateCarBiz.read_checkpay is error" + e.getMessage(), e);
            returnData.setReturnData(errorcode_systerm, "system is error", null);
        }
    }


    /**
     * 停车场心跳表
     */
    public void park_heartbeat(ReturnDataNew returnData, long pi_id,
                               String area_code, int is_rent, long time, int park_type, Integer carport_yet,
                               Integer carport_space, Integer carport_total, Integer moth_car_num,
                               Integer moth_car_num_space, Integer time_car_num, Integer time_car_num_space,
                               Integer expect_car_num) {
        // TODO Auto-generated method stub
        try {
            Park_info park_info = returnParkInfo(pi_id, area_code);
            if (park_info == null) {
                returnData.setReturnData(errorcode_data, "停车场不存在", "");
                return;
            }
            //更新上次心跳状态
            try {
                String sql = "select *  from park_heartbeat where pi_id=? and area_code=? and state=0 order by id desc";
                List<Park_heartbeat> ph_old_list = getMySelfService()
                        .queryListT(sql, Park_heartbeat.class, pi_id, area_code);
                if (ph_old_list != null && ph_old_list.size() > 0) {
                    for (Park_heartbeat park_heartbeat : ph_old_list) {
                        try {
                            park_heartbeat.setState(1);//被标记
                            park_heartbeatDao.updateByKey(park_heartbeat);
                        } catch (Exception e) {
                            // TODO Auto-generated catch block
                            log.error("更新上次心跳标记失败 id=" + park_heartbeat.getId(), e);
                        }
                    }
                }
            } catch (Exception e) {
                // TODO Auto-generated catch block
                log.error("pi_id=" + pi_id + " area_code=" + area_code + " 上次心跳未标记成功", e);
            }
            Date date = new Date();
            Park_heartbeat ph = new Park_heartbeat();
            ph.setArea_code(area_code);
            ph.setCtime(date);
            ph.setCtime_num(date.getTime());
            ph.setIs_rent(is_rent);
            ph.setPi_id(pi_id);
            if (carport_total != null) {
                ph.setCarport_total(carport_total.intValue());
            }
            if (carport_space != null) {
                ph.setCarport_space(carport_space.intValue());
            }
            if (carport_yet != null) {
                ph.setCarport_yet(carport_yet.intValue());
            }
            if (moth_car_num != null) {
                ph.setMoth_car_num(moth_car_num.intValue());
            }
            if (moth_car_num_space != null) {
                ph.setMoth_car_num_space(moth_car_num_space.intValue());
            }
            if (time_car_num != null && time_car_num.intValue() > 0) {
                ph.setTime_car_num(time_car_num.intValue());
                if (time_car_num_space != null && time_car_num_space.intValue() >= 0) {
                    ph.setTime_car_num_space(time_car_num_space.intValue());
                }
            }
            int id = park_heartbeatDao.insert(ph);
            if (id > 0) {

                //校准该停车场的车位数
                if (park_info != null) {
                    if (carport_total != null && carport_total.intValue() >= 0) {
                        park_info.setCarport_total(carport_total.intValue());
                    }
                    if (carport_space != null && carport_space.intValue() >= 0) {
                        park_info.setCarport_space(carport_space.intValue());
                    }
                    if (carport_yet != null && carport_yet.intValue() >= 0) {
                        park_info.setCarport_yet(carport_yet.intValue());
                    }
                    if (moth_car_num != null && moth_car_num.intValue() >= 0) {
                        park_info.setMoth_car_num(moth_car_num.intValue());
                    }
                    if (moth_car_num_space != null && moth_car_num_space.intValue() >= 0) {
                        park_info.setMoth_car_num_space(moth_car_num_space.intValue());
                    }
                    if (time_car_num != null && time_car_num.intValue() > 0) {
                        park_info.setTime_car_num(time_car_num.intValue());
                        if (time_car_num_space != null && time_car_num_space.intValue() >= 0) {
                            park_info.setTime_car_num_space(time_car_num_space.intValue());
                        }
                    }

                    park_info.setIs_fault(0);
                    int count = park_infoDao.updateByKey(park_info, ReturnParkTableName(area_code));
                    if (count < 1) {
                        returnData.setReturnData(errorcode_data, "停车场车位数校准失败", "");
                        return;
                    } else {
                        if (park_info.getPark_type() == 0) {
                            Map<String, Object> map = new HashMap<String, Object>();
                            map.put("park_info", park_info);
                            map.put("time", date.getTime());
                            /**
                             * 获取某停车场未成功的   预约订单  取消预约订单   租赁订单
                             * 			*  "expect":"[{},{}]",预约
                             *	"cancel_expect":"[{},{}]",取消预约
                             *	"rent":"[{},{}]"租赁
                             */
                            QueryParkHeart(map, park_info);

                            returnData.setReturnData(errorcode_success, "停车场心跳发送成功", map);
                            return;
                        }
                        returnData.setReturnData(errorcode_success, "停车场心跳发送成功", "");
                        return;
                    }
                }
                //插入成功
                //返回结果
                returnData.setReturnData(errorcode_data, "停车场不存在", "");
                return;
            } else {
                //插入失败
                //返回结果
                returnData.setReturnData(errorcode_data, "停车场心跳发送失败", "");
                return;
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            log.error("gateCarBiz.park_heartbeat is error" + e.getMessage(), e);
            returnData.setReturnData(errorcode_systerm, "system is error", null);
        }
    }


    /**
     * 获取某停车场未成功的   预约订单  取消预约订单   租赁订单
     */
    public Map<String, Object> QueryParkHeart(Map<String, Object> map, Park_info park_info) {
        //查询该用户所绑定的车牌号码
        try {
            /**
             *  "expect":"[{},{}]",预约
             *	"cancel_expect":"[{},{}]",取消预约
             *	"rent":"[{},{}]"租赁
             */
            //预约
            String sql_expect = "select *  from pay_park where pi_id=? and area_code=? and expect_state=1 and cancel_state=0 and is_del=0 and unix_timestamp()-UNIX_TIMESTAMP(ctime) < expect_time*60 and arrive_time=ctime";
            List<Pay_park> list_expect = getMySelfService()
                    .queryListT(sql_expect, Pay_park.class, park_info.getPi_id(), park_info.getArea_code());
            map.put("expect", list_expect == null ? "" : list_expect);
            //取消预约
            String sql_expect_cancel = "select *  from pay_park where pi_id=? and area_code=? and expect_state=4 and cancel_state=0 and is_del=0 and unix_timestamp()-UNIX_TIMESTAMP(ctime) < expect_time*60 and arrive_time=ctime";
            List<Pay_park> list_expect_cancel = getMySelfService()
                    .queryListT(sql_expect_cancel, Pay_park.class, park_info.getPi_id(),
                            park_info.getArea_code());
            map.put("cancel_expect", list_expect_cancel == null ? "" : list_expect_cancel);
            //租赁
            String sql_rent = "select *  from pay_month_park where pi_id=? and area_code=? and rent_state=1 and cancel_state=0 and is_del=0 and unix_timestamp()-UNIX_TIMESTAMP(end_time) < 0";
            List<Pay_month_park> list_rent = getMySelfService()
                    .queryListT(sql_rent, Pay_month_park.class, park_info.getPi_id(),
                            park_info.getArea_code());
            map.put("rent", list_rent == null ? "" : list_rent);

            return map;
        } catch (Exception e) {
            // TODO Auto-generated catch block
            log.error("QueryParkHeart is error" + e.getMessage(), e);
        }
        return null;
    }

}
