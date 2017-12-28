/**
 * @Title: TT.java
 * @Package com.intimes.biz
 * @Description: TODO(用一句话描述该文件做什么)
 * @author 敬小虎
 * @date 2015年3月20日 下午1:32:43
 * @version V1.0
 */
package com.park.transaction;

import com.park.bean.Pay_month_park;
import com.park.bean.Pay_park;
import com.park.bean.ReturnDataNew;
import com.park.bean.User_info;
import com.park.bean.User_park_coupon;
import com.park.exception.QzException;
import com.park.mvc.service.BaseBiz;
import com.park.mvc.service.common.ActivityPB;
import com.park.mvc.service.common.ParkCouponPB;
import com.park.mvc.service.common.ParkInfoPB;
import com.park.mvc.service.common.PayMonthParkPB;
import com.park.mvc.service.common.PayParkPB;
import com.park.mvc.service.common.UserPB;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author 敬小虎
 * @ClassName: AppSdkTransaction
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @date 2015年3月20日 下午2:11:51
 */
@Transactional(rollbackFor = QzException.class)
@Service
public class UserTransaction extends BaseBiz {


  @Autowired
  protected ParkCouponPB parkCouponPB;
  @Autowired
  private ParkInfoPB parkInfoPB;
  @Autowired
  private UserPB userPB;
  @Autowired
  protected PayParkPB payParkPB;
  @Autowired
  protected PayMonthParkPB payMonthParkPB;
  @Autowired
  protected ActivityPB activityPB;

  /**
   * 租赁记录
   * 记录用户虚拟币的 变更详情 --- pay_order  pay_rent
   *
   * @param type : 0 ：自动扣款   1：第三方支付回调
   */
  public void record_rent_user_vc_act(ReturnDataNew returnData, User_info user_info, int money,
      String order_id, int act_type, int type, User_park_coupon user_park_coupon)
      throws QzException {
    try {
      if (money < 0) {
        return;
      }
      Date date = new Date();
      int pay_source = 0;//支付类型  1:支付宝 2：微信  3：银联  4：钱包   5:龙支付
      int discount_money = 0;//int(11)    抵扣优惠金额（单位分）
      int discount_type = 0;//int(11)    优惠券类型0:金额券1：折扣券
      long upc_id = 0;//bigint(20)    用户优惠券表主键ID
      //订单类型 0:普通订单 1：租赁订单
      //租赁订单
      String sql = "SELECT * FROM pay_month_park WHERE my_order=? LIMIT 1";
      Pay_month_park pay_month_park = getMySelfService()
          .queryUniqueT(sql, Pay_month_park.class, order_id);
      if (pay_month_park == null) {
        //更新失败
        returnData.setReturnData(errorcode_data, "订单不存在", "");
        throw new QzException("record_user_vc_act 订单不存在");
      } else {
        //设置优惠券数据
        upc_id = pay_month_park.getUpc_id();//bigint(20)
        discount_type = pay_month_park.getDiscount_type();//int(11)    优惠券类型0:金额券1：折扣券
        discount_money = (int) pay_month_park.getDiscount_money();//int(11)    抵扣优惠金额（单位分）
        pay_source = pay_month_park.getPay_source();
      }

      if (money == 0) {
        //扣款成功
        pay_month_park.setPp_state(1);//支付状态 0:未支付 1：已经支付
        pay_month_park.setUtime(date);//更新时间

        int count = pay_month_parkDao.updateByKey(pay_month_park);
        if (count < 1) {
          //更新失败
          //该车辆未出入
          //更新失败
          returnData.setReturnData(errorcode_data, "停车缴费失败", "");
          throw new QzException("record_user_vc_act 停车缴费失败");
        }

        /**
         * 更新商户账户金额
         */
        userPB.upManchentMoney(pay_month_park.getPu_id(), pay_month_park.getPu_nd(),
            pay_month_park.getMoney(), 1);
        /**
         * 这里处理推送---车辆临停扣款
         */
        String title = "系统消息";
        String message = "亲，你的租赁订单【" + pay_month_park.getMy_order() + "】，扣款成功。";
        payParkPB.pushRentOrderSate(user_info.getUuid(), message, title, pay_month_park);
      } else {
        //进行用户虚拟币和订单的缴费状态
        //这里查看用户是自动扣费还是手动扣费
        //如果是选择自己钱包扣款，更改用户账户的 虚拟币
        if (type == 0) {//钱包扣款---开始
          //钱包扣款
          //虚拟币比例1元比100 分
          if (!isNotSureMoney(user_info, money)) {
            //虚拟币足够 且 是自动扣费
            user_info.setUi_vc(user_info.getUi_vc() - money); //分
            int count = user_infoDao.updateByKey(user_info);
            if (count < 1) {
              //更新用户扣款数据失败
              //这里需要抛出异常
              //返回结果
              //更新失败
              returnData.setReturnData(errorcode_data, "停车包月缴费失败", "");
              throw new QzException("record_user_vc_act 停车包月缴费失败");
            } else {
              //扣款成功
              pay_month_park.setPp_state(1);//支付状态 0:未支付 1：已经支付
              count = pay_month_parkDao.updateByKey(pay_month_park);
              if (count < 1) {
                //更新失败
                //该车辆未出入
                //更新失败
                returnData.setReturnData(errorcode_data, "停车包月缴费失败", "");
                throw new QzException("record_user_vc_act 停车包月缴费失败");
              }
              //这里设置租赁锁定金额
              userPB.doUnLockMoney(2, 1, null, pay_month_park.getMoney(), user_info
                  , pay_month_park.getMy_order(), pay_month_park.getPi_id(),
                  pay_month_park.getArea_code(), pay_month_park, null, returnData);
              /**
               * 更新商户账户金额
               */
              userPB.upManchentMoney(pay_month_park.getPu_id(), pay_month_park.getPu_nd(),
                  pay_month_park.getMoney(), 1);
              //这里处理订单状态变更推送----车辆租赁扣款
              /**
               * 这里处理推送---车辆临停扣款
               */
              String title = "系统消息";
              String message = "亲，你的租赁订单【" + pay_month_park.getMy_order() + "】，扣款成功。";
              payParkPB.pushRentOrderSate(user_info.getUuid(), message, title, pay_month_park);
            }
          } else {
            //账户余额不足
            returnData.setReturnData(errorcode_data, "账户余额不足", "", "1");
            throw new QzException("账户余额不足");
          }
        }
      }

      //记录该次用户虚拟币更改记录
      String act_name = "钱包支付";
      userPB.recordVC(act_type, money, order_id, 2, user_info.getUi_id(), returnData,
          act_name, user_info.getUuid(), user_info.getUi_tel(), pay_source, upc_id, discount_type,
          discount_money, date);

      if (user_park_coupon != null) {
        //如果使用了优惠券 那么需要更改优惠券的使用状态
        User_park_coupon user_park_coupon1 = parkCouponPB
            .upUserParkCouponState(user_park_coupon.getUpc_id(),user_info.getUi_id());
        if (user_park_coupon1 == null) {
          //更新失败
          returnData.setReturnData(errorcode_data, "优惠券更新使用状态失败", "");
          throw new QzException("record_user_vc_act 优惠券更新使用状态失败");
        } else {
          asyncJpushTask.pushUseCouponMsg(user_info.getUuid(), user_park_coupon1);
        }
      }
    } catch (Exception e) {
      if (!e.getMessage().contains("付款")) {
        log.error("VcBiz record_user_vc_act is error", e);
      }
      throw new QzException("事物异常 record_user_vc_act", e);
    }
  }

  /**
   * 记录用户虚拟币的 变更详情 --- pay_order  pay_rent
   *
   * @param type : 0 ：自动扣款   1：第三方支付回调
   */
  public void record_user_vc_act(ReturnDataNew returnData, User_info user_info, int money,
      Pay_park pay_park, int act_type, int type, User_park_coupon user_park_coupon)
      throws QzException {
    try {
      String order_id = pay_park.getMy_order();
      if (money < 0) {
        return;
      }
      Date date = new Date();
      int pay_source = 0;//支付类型  1:支付宝 2：微信  3：银联  4：钱包   5:龙支付
      int discount_money = 0;//int(11)    抵扣优惠金额（单位分）
      int discount_type = 0;//int(11)    优惠券类型0:金额券1：折扣券
      long upc_id = 0;//bigint(20)    用户优惠券表主键ID
      //订单类型 0:普通订单 1：租赁订单
      /********使用优惠券****start**************/
      //by jxh 2017-2-17
      //设置优惠券数据
      upc_id = pay_park.getUpc_id();//bigint(20)
      discount_type = pay_park.getDiscount_type();//int(11)    优惠券类型0:金额券1：折扣券
      discount_money = (int) pay_park.getDiscount_money();//int(11)    抵扣优惠金额（单位分）
      pay_source = pay_park.getPay_source();
      /********使用优惠券****be over**************/
      if (money == 0) {
        //扣款成功
        pay_park.setPp_state(1);//支付状态 0:未支付 1：已经支付
        pay_park.setUtime(date);//更新时间
        pay_park.setLeave_time(date);//离开时间

        int count = pay_parkDao.updateByKey(pay_park);
        if (count < 1) {
          //更新失败
          //该车辆未出入
          //更新失败
          returnData.setReturnData(errorcode_data, "停车缴费失败", "");
          throw new QzException("record_user_vc_act 停车缴费失败");
        }

        /**
         * 更新商户账户金额
         */
        userPB.upManchentMoney(pay_park.getPu_id(), pay_park.getPu_nd(), pay_park.getMoney(),
            1);
        /**
         * 这里处理推送---车辆临停扣款
         */
        String title = "系统消息";
        String message = "亲，你的订单【" + pay_park.getMy_order() + "】，临停扣款成功。";
        payParkPB.pushOrderSate(user_info.getUuid(), message, title, pay_park);
      } else {
        //进行用户虚拟币和订单的缴费状态
        //这里查看用户是自动扣费还是手动扣费
        //如果是选择自己钱包扣款，更改用户账户的 虚拟币
        if (type == 0) {//钱包扣款---开始
          //钱包扣款
          //虚拟币比例1元比100 分
          if (!isNotSureMoney(user_info, money)) {
            //虚拟币足够 且 是自动扣费
            user_info.setUi_vc(user_info.getUi_vc() - money); //分
            int count = user_infoDao.updateByKey(user_info);
            if (count < 1) {
              //更新用户扣款数据失败
              //这里需要抛出异常
              //返回结果
              //更新失败
              returnData.setReturnData(errorcode_data, "停车缴费失败", "");
              throw new QzException("record_user_vc_act 停车缴费失败");
            } else {
              //扣款成功
              pay_park.setPp_state(1);//支付状态 0:未支付 1：已经支付
              pay_park.setUtime(date);//更新时间
              pay_park.setLeave_time(date);//离开时间

              count = pay_parkDao.updateByKey(pay_park);
              if (count < 1) {
                //更新失败
                //该车辆未出入
                //更新失败
                returnData.setReturnData(errorcode_data, "停车缴费失败", "");
                throw new QzException("record_user_vc_act 停车缴费失败");
              }

              /**
               * 更新商户账户金额
               */
              userPB.upManchentMoney(pay_park.getPu_id(), pay_park.getPu_nd(), pay_park.getMoney(),
                  1);
              //这里处理订单状态变更推送----车辆临停扣款

              /**
               * 记录随机立免金额的事件
               * 异步操作
               * @param pay_park
               */
              if (pay_park.getUpc_id() == 0) {
                //没有使用优惠券
                asyncOrderTask.record_random_Event(pay_park, getMySelfService(), activityPB);
              }

              /**
               * 这里处理推送---车辆临停扣款
               */
              String title = "系统消息";
              String message = "亲，你的订单【" + pay_park.getMy_order() + "】，临停扣款成功。";
              payParkPB.pushOrderSate(user_info.getUuid(), message, title, pay_park);
            }
          }
        }
      }

      //记录该次用户虚拟币更改记录
      String act_name = "钱包支付";
      userPB.recordVC(act_type, money, order_id, 0, user_info.getUi_id(), returnData,
          act_name, user_info.getUuid(), user_info.getUi_tel(), pay_source, upc_id, discount_type,
          discount_money, date);
      if (user_park_coupon != null) {
        //如果使用了优惠券 那么需要更改优惠券的使用状态
        User_park_coupon user_park_coupon1 = parkCouponPB
            .upUserParkCouponState(user_park_coupon.getUpc_id(),pay_park.getUi_id());
        if (user_park_coupon1 == null) {
          //更新失败
          returnData.setReturnData(errorcode_data, "优惠券更新使用状态失败", "");
          throw new QzException("record_user_vc_act 优惠券更新使用状态失败");
        } else {
          asyncJpushTask.pushUseCouponMsg(pay_park.getUi_nd(), user_park_coupon1);
        }
      }
    } catch (Exception e) {
      log.error("VcBiz record_user_vc_act is error", e);
      throw new QzException("事物异常 record_user_vc_act", e);
    }
  }


  /**
   * 处理用户被锁定的金额----情况1：锁定金额  情况2：解除锁定金额 3:解除锁定金额和返还金额
   *
   * @param type : 0：预约   1：取消预约  2：租赁
   * @param order_type 0:预约  1：租赁
   * @param state 处理结果状态 0:成功 1：失败
   */
  public void doUnLockMoney(int type, int order_type, Integer state, int money, User_info user_info,
      String orderid, long pi_id, String area_code, Pay_month_park pay_month_park,
      Pay_park pay_park, ReturnDataNew returnData) throws QzException {
    userPB.doUnLockMoney(type, order_type, state, money, user_info, orderid, pi_id, area_code,
        pay_month_park, pay_park, returnData);
  }

  /*******************************下面是分出的方法*************************************/


}
