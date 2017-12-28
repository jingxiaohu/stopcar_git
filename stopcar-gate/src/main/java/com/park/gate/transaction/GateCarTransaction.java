/**
 * @Title: TT.java
 * @Package com.intimes.service
 * @Description:
 * @author 敬小虎
 * @date 2015年3月20日 下午1:32:43
 * @version V1.0
 */
package com.park.gate.transaction;

import com.park.bean.Car_in_out;
import com.park.bean.Park_info;
import com.park.bean.Pay_park;
import com.park.bean.ReturnDataNew;
import com.park.bean.User_info;
import com.park.bean.User_park_coupon;
import com.park.exception.QzException;
import com.park.mvc.service.BaseBiz;
import com.park.mvc.service.common.CarPB;
import com.park.mvc.service.common.ParkCouponPB;
import com.park.mvc.service.common.PayParkPB;
import com.park.mvc.service.common.UserPB;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 车辆相关事务类
 *
 * @author jingxiaohu
 */
@Transactional(rollbackFor = QzException.class)
@Service
public class GateCarTransaction extends BaseBiz {

    @Autowired
    private PayParkPB payParkPB;
    @Autowired
    private UserPB userPB;
    @Autowired
    protected ParkCouponPB parkCouponPB;
    @Autowired
    protected CarPB carPB;

    /**
     * 车辆出入的时候 计费 状态设定 等细则进行事物处理
     */
    public void doCar_In_Out(int dtype, String order_id, Car_in_out car_in_out, Park_info park_info,
                             ReturnDataNew returnData, String gov_num)
            throws QzException {

        try {
            int is_enter = car_in_out.getIs_enter();
            //入库或者出库 ：0：   入库   1：出库
            if (is_enter == 0) {
                payParkPB.doCar_In(dtype, order_id, car_in_out, park_info, returnData, gov_num);
            } else {
                //出库
                payParkPB.doCar_Out(order_id, car_in_out, park_info, returnData);
            }
        } catch (Exception e) {
            throw new QzException(e.getMessage(), e);
        }
    }


    /**
     * 扫描到车辆出库扣费
     *
     * @param is_free :0 收费  1：免费
     */
    public void payMent(Pay_park pay_park, User_info userinfo, int total_money,
                        ReturnDataNew returnData, Date date, int is_free) throws QzException {
        try {
            if (is_free == 1) {
                //免费
                pay_park.setScan_time(date);//道闸停车  摄像头扫描到车辆  需要记录扫描时间点
                pay_park.setIs_over(1);//订单是否完成(0:没有完成1：完成)
                int count = pay_parkDao.updateByKey(pay_park);
                if (count != 1) {
                    //更新失败
                    returnData.setReturnData(errorcode_data, "请求失败", "");
                    return;
                }
                return;
            }

            /**
             * 快捷支付分2种 第一种：ETC快捷支付 第二种：钱包自动支付
             * 两者优先级：当开启了快捷支付  则先查看是否钱包中的钱够扣 如果不够扣除则 直接进行ETC扣款
             */
            //检查是否开启自动支付
            if (userinfo.getUi_autopay() == 0) {
                returnData.setReturnData(errorcode_data, "请求失败-没有开启自动支付", "", "5");
                return;
            }

            /**
             * 注意事项  占道停车  暂时不参与自动扣款处理   park_info.getPark_type() != 1
             */
            //第一情况：首先如果用户有吾泊平台未过期的代金券 则检查该代金券是否可以完全抵扣该次停车费用
            //如果可以则完成该次支付开闸  如果不可以则需要判断用户是否开启了自动支付和钱包金额是否够该次支付
            //默认如果有优惠券则 使用代金券最大面值的一张
            //默认选择抵扣最大的优惠券
            User_park_coupon user_park_coupon = parkCouponPB
                    .ReturnMaxMoneyCoupon(pay_park.getUi_id(), total_money);
            if (user_park_coupon != null) {
                total_money = parkCouponPB.doCouponOrder(pay_park, user_park_coupon, total_money);
            }
            /********使用优惠券****start**************/
            //by jxh 2017-2-17
            if (total_money == 0) {
                //int money = pay_park.getMoney();
                //扣款成功
                pay_park.setPp_state(1);//支付状态 0:未支付 1：已经支付
                pay_park.setPay_source(4);//支付类型1:支付宝2：微信3：银联4：钱包5:龙支付
                int count = pay_parkDao.updateByKey(pay_park);
                if (count < 1) {
                    //扣款失败
                    throw new QzException("扣款失败");
                }

                /**
                 * 更新商户账户金额
                 */
                //userPB.upManchentMoney(pay_park.getPu_id(), pay_park.getPu_nd(), pay_park.getMoney(), 1);

                /**
                 * 更新优惠券使用状态
                 */
                if (user_park_coupon != null) {
                    //如果使用了优惠券 那么需要更改优惠券的使用状态
                    User_park_coupon user_park_coupon1 = parkCouponPB
                            .upUserParkCouponState(user_park_coupon.getUpc_id(),pay_park.getUi_id());
                    if (user_park_coupon1 != null) {
                        asyncJpushTask.pushUseCouponMsg(pay_park.getUi_nd(), user_park_coupon1);
                    } else {
                        //更新失败
                        returnData.setReturnData(errorcode_data, "优惠券更新使用状态失败", "");
                        throw new QzException("record_user_vc_act 优惠券更新使用状态失败");
                    }
                }

                //记录该次用户虚拟币更改记录
                //String act_name="优惠券支付";
                //userPB.recordVC( 0, total_money, pay_park.getMy_order(), pay_park.getOrder_type(), pay_park.getUi_id(), returnData,act_name,pay_park.getUi_nd(),pay_park.getUi_tel());
                /**
                 * 这里处理推送---车辆临停扣款
                 */
                String title = "系统消息";
                String message = "亲，你的订单【" + pay_park.getMy_order() + "】，临停扣款成功。";
                payParkPB.pushOrderSate(userinfo.getUuid(), message, title, pay_park);
                return;
            }
            /********使用优惠券****be over**************/
            carPB.doPayMentSuccess(returnData, user_park_coupon, userinfo, total_money, pay_park, date);
        } catch (Exception e) {
            throw new QzException("事物异常 payMent", e);
        }


    }

/*  *//**
     * 扫描到车辆出库扣费
     *
     * @param is_free :0 收费  1：免费
     *//*
  public void payMent(Pay_park pay_park, User_info userinfo, int total_money,
      ReturnDataNew returnData, Date date, int is_free) throws QzException {
    try {
      if (is_free == 1) {
        //免费
        pay_park.setScan_time(date);//道闸停车  摄像头扫描到车辆  需要记录扫描时间点
        pay_park.setIs_over(1);//订单是否完成(0:没有完成1：完成)
        int count = pay_parkDao.updateByKey(pay_park);
        if (count != 1) {
          //更新失败
          returnData.setReturnData(errorcode_data, "请求失败", "");
          return;
        }
        return;
      }

      //检查是否开启自动支付
      if (userinfo.getUi_autopay() == 0) {
        returnData.setReturnData(errorcode_data, "请求失败-没有开启自动支付", "", "5");
        return;
      }

      *//**
     * 注意事项  占道停车  暂时不参与自动扣款处理   park_info.getPark_type() != 1
     *//*
      //第一情况：首先如果用户有吾泊平台未过期的代金券 则检查该代金券是否可以完全抵扣该次停车费用
      //如果可以则完成该次支付开闸  如果不可以则需要判断用户是否开启了自动支付和钱包金额是否够该次支付
      //默认如果有优惠券则 使用代金券最大面值的一张
      //默认选择抵扣最大的优惠券
      User_park_coupon user_park_coupon = parkCouponPB
          .ReturnMaxMoneyCoupon(pay_park.getUi_id(), total_money);
      if (user_park_coupon != null) {
        total_money = parkCouponPB.doCouponOrder(pay_park, user_park_coupon, total_money);
      }
      *//********使用优惠券****start**************//*
      //by jxh 2017-2-17
      if (total_money == 0) {
        //int money = pay_park.getMoney();
        //扣款成功
        pay_park.setPp_state(1);//支付状态 0:未支付 1：已经支付
        pay_park.setPay_source(4);//支付类型1:支付宝2：微信3：银联4：钱包5:龙支付
        int count = pay_parkDao.updateByKey(pay_park);
        if (count < 1) {
          //扣款失败
          throw new QzException("扣款失败");
        }

        *//**
     * 更新商户账户金额
     *//*
        userPB.upManchentMoney(pay_park.getPu_id(), pay_park.getPu_nd(), pay_park.getMoney(), 1);

        *//**
     * 更新优惠券使用状态
     *//*
        if (user_park_coupon != null) {
          //如果使用了优惠券 那么需要更改优惠券的使用状态
          User_park_coupon user_park_coupon1 = parkCouponPB
              .upUserParkCouponState(user_park_coupon.getUpc_id());
          if (user_park_coupon1 != null) {
            asyncJpushTask.pushUseCouponMsg(pay_park.getUi_nd(), user_park_coupon1);
          } else {
            //更新失败
            returnData.setReturnData(errorcode_data, "优惠券更新使用状态失败", "");
            throw new QzException("record_user_vc_act 优惠券更新使用状态失败");
          }
        }

        //记录该次用户虚拟币更改记录
        //String act_name="优惠券支付";
        //userPB.recordVC( 0, total_money, pay_park.getMy_order(), pay_park.getOrder_type(), pay_park.getUi_id(), returnData,act_name,pay_park.getUi_nd(),pay_park.getUi_tel());
        *//**
     * 这里处理推送---车辆临停扣款
     *//*
        String title = "系统消息";
        String message = "亲，你的订单【" + pay_park.getMy_order() + "】，临停扣款成功。";
        payParkPB.pushOrderSate(userinfo.getUuid(), message, title, pay_park);
        return;
      }
      *//********使用优惠券****be over**************//*

      if (userinfo != null && total_money > 0) {

        if (userinfo.getUi_vc() - total_money >= 0) {
          //虚拟币足够 且 是自动扣费
          userinfo.setUi_vc(userinfo.getUi_vc() - total_money); //分
          int count = user_infoDao.updateByKey(userinfo);
          if (count < 1) {
            //更新用户扣款数据失败
            //这里需要抛出异常
            throw new QzException("扣款失败");
          } else {
            //扣款成功
            pay_park.setPp_state(1);//支付状态 0:未支付 1：已经支付
            pay_park.setPay_source(4);//支付类型1:支付宝2：微信3：银联4：钱包5:龙支付
            pay_park.setUtime(date);//交易时间
            count = pay_parkDao.updateByKey(pay_park);
            if (count < 1) {
              //扣款失败
              throw new QzException("扣款失败");
            }
            //扣款记录到用户金额明细中去  *** 后续是否进行异常捕获
            String act_name = "自动支付";
            userPB.recordVC(0, total_money, pay_park.getMy_order(), pay_park.getOrder_type(),
                pay_park.getUi_id(), returnData, act_name, pay_park.getUi_nd(),
                pay_park.getUi_tel(),
                pay_park.getPay_source()
                , pay_park.getUpc_id(), pay_park.getDiscount_type(),
                (int) pay_park.getDiscount_money(), date);
            *//**
     * 更新商户账户金额
     *//*
            userPB
                .upManchentMoney(pay_park.getPu_id(), pay_park.getPu_nd(), pay_park.getMoney(), 1);
            *//**
     * 记录随机立免金额的事件
     * 异步操作
     * @param pay_park
     *//*
            asyncOrderTask.record_random_Event(pay_park, getMySelfService(), activityPB);
            //这里处理订单状态变更推送----车辆临停扣款
            *//**
     * 更新优惠券使用状态
     *//*
            if (user_park_coupon != null) {
              //如果使用了优惠券 那么需要更改优惠券的使用状态
              User_park_coupon user_park_coupon1 = parkCouponPB
                  .upUserParkCouponState(user_park_coupon.getUpc_id());
              if (user_park_coupon1 != null) {
                asyncJpushTask.pushUseCouponMsg(pay_park.getUi_nd(), user_park_coupon1);
              } else {
                //更新失败
                returnData.setReturnData(errorcode_data, "优惠券更新使用状态失败", "");
                throw new QzException("record_user_vc_act 优惠券更新使用状态失败");
              }
            }
            *//**
     * 这里处理推送---车辆临停扣款
     *//*
            String title = "系统消息";
            String message = "亲，你的订单【" + pay_park.getMy_order() + "】，临停扣款成功。";
            payParkPB.pushOrderSate(userinfo.getUuid(), message, title, pay_park);
          }
        } else {
          //开启了自动支付但是钱包金额不足
          *//**
     * 这里处理推送---车辆临停扣款
     *//*
          String title = "系统消息";
          String message = "亲，你的订单【" + pay_park.getMy_order() + "】，因钱包余额不足,自动扣款失败。";
          asyncJpushTask.pushOrderNoMoney(userinfo.getUuid(), message, title, pay_park);
          returnData.setReturnData(errorcode_data, "亲,你钱包余额不足", "", "6");
          return;
        }
      }
    } catch (Exception e) {
      throw new QzException("事物异常 payMent", e);
    }


  }*/

    /**
     * 扫描到车辆出库扣费--租赁产生临停费用
     */
  /*public void payMentRent(Pay_month_park pay_month_park,User_info userinfo ,ReturnDataNew returnData,long pi_id, String area_code,String car_code,Date date,long difftime) throws QzException{

		try{
			*//**
     * 这里租赁分时间端包月产生了临停费用 则要动态创建 临停订单
     *//*
      Pay_park pay_park  = payParkPB.MakeAutoOrder(pi_id, area_code, car_code, userinfo.getUi_id(),difftime);
			if(pay_park == null){
				returnData.setReturnData(errorcode_data, "请求失败", "");
				return;
			}
        	*//**
     * 道闸停车  摄像头扫描到车辆  需要记录扫描时间点
     *//*
          pay_month_park.setScan_time(date);
        	int count = pay_month_parkDao.updateByKey(pay_month_park);
        	if(count != 1){
        		//更新失败
        		returnData.setReturnData(errorcode_data, "请求失败", "");
				return;
        	}
			*//**
     * 注意事项  占道停车  暂时不参与自动扣款处理   park_info.getPark_type() != 1
     *//*
          *//**
     * 获取当前临停车费用
     *//*
          int total_money = payParkPB.returnCarMoney( pay_park, difftime);

			if(      userinfo != null
					 && !isNotSureMoney(userinfo,total_money)
					 && userinfo.getUi_autopay() == 1){
				//虚拟币足够 且 是自动扣费
				userinfo.setUi_vc(userinfo.getUi_vc()-total_money); //分
				count = user_infoDao.updateByKey(userinfo);
				if(count < 1){
					//更新用户扣款数据失败
					//这里需要抛出异常
					throw new QzException("扣款失败");
				}else{
					//扣款成功
					pay_park.setPp_state(1);//支付状态 0:未支付 1：已经支付
					pay_park.setMoney(total_money);//单位 分

					count = pay_parkDao.updateByKey(pay_park);
					if(count < 1){
						//扣款失败
						throw new QzException("扣款失败");
					}
					//扣款记录到用户金额明细中去  *** 后续是否进行异常捕获
    				userPB.recordVC( 0, pay_park.getMoney(), pay_park.getMy_order(), pay_park.getOrder_type(), pay_park.getUi_id(), returnData);
					*//**
     * 更新商户账户金额
     *//*
            userPB.upManchentMoney(pay_park.getPu_id(),pay_park.getPu_nd(),pay_park.getMoney(), 1);
				}
			}
		}catch(Exception e){
			throw new QzException("事物异常 payMent",e);
		}


	}*/

}
