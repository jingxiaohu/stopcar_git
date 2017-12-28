package com.park.mvc.service.common;

import com.park.bean.Etc_userinfo;
import com.park.bean.Pay_park;
import com.park.bean.ReturnDataNew;
import com.park.bean.User_info;
import com.park.bean.User_park_coupon;
import com.park.exception.QzException;
import com.park.mvc.service.BaseBiz;
import com.park.service.ETCHelper;
import com.park.service.etc.etcapi.ETCResponse;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 车辆相关公用方法
 *
 * @author jingxiaohu
 */
@Service
public class CarPB extends BaseBiz {

  @Autowired
  protected ParkCouponPB parkCouponPB;
  @Autowired
  private UserPB userPB;
  @Autowired
  private ParkInfoPB parkInfoPB;
  @Autowired
  protected ActivityPB activityPB;
  @Autowired
  private PayParkPB payParkPB;
  @Autowired
  private ETCHelper eTCHelper;
  @Autowired
  private EtcPB etcPB;

  /**
   * 获取某车牌还没有支付且没有绑定用户的最后一条某停车场的订单
   *
   * @param car_code 车牌号
   */
  public Pay_park Query_PayParkByCarCode(String car_code) {
    //入库之前先检查该用户对该车辆进行该停车场预约下单没有   如果已经进行了预约下单则直接更新对应得数据
    try {
      String sql = "SELECT *  FROM pay_park WHERE ui_id=0 AND car_code=? AND pp_state=0 AND cancel_state=0  AND is_del=0 ORDER BY  ctime DESC LIMIT 1";
      return getMySelfService().queryUniqueT(sql, Pay_park.class, car_code);
    } catch (Exception e) {
      // TODO Auto-generated catch block
      log.error(
          "获取某车牌还没有支付且没有绑定用户的最后一条某停车场的订单	Query_PayParkByCarCode(String car_code) is error" + e
              .getMessage(), e);
    }
    return null;
  }


  /**
   * 获取某车牌还没有支付且没有绑定用户的某PDA停车场的欠费订单
   *
   * @param car_code 车牌号
   */
  public List<Pay_park> Query_PayParkByCarCodeOwing(String car_code) {
    //入库之前先检查该用户对该车辆进行该停车场预约下单没有   如果已经进行了预约下单则直接更新对应得数据
    try {
      String sql = "SELECT *  FROM pay_park WHERE ui_id=0 AND car_code=? AND pp_state=0 AND cancel_state=0  AND is_del=0 AND (is_over=2 OR is_over=3) ORDER BY  ctime DESC";
      return getMySelfService().queryListT(sql, Pay_park.class, car_code);
    } catch (Exception e) {
      // TODO Auto-generated catch block
      log.error(
          "获取某车牌还没有支付且没有绑定用户的某PDA停车场的欠费订单	Query_PayParkByCarCodeOwing(String car_code) is error"
              + e.getMessage(), e);
    }
    return null;
  }

  /**
   * 道闸自动扣款成功后的处理
   */
  public void doPayMentSuccess(ReturnDataNew returnData, User_park_coupon user_park_coupon,
      User_info userinfo, int total_money, Pay_park pay_park, Date date) throws QzException {
    /**
     * 快捷支付分2种 第一种：ETC快捷支付 第二种：钱包自动支付
     * 两者优先级：当开启了快捷支付  则先查看是否钱包中的钱够扣 如果不够扣除则 直接进行ETC扣款
     */
    if (userinfo != null && total_money > 0) {

//      if (userinfo.getUi_vc() - total_money >= 0) {
      //by zzy 2017-6-22  判断余额是否不足时增加预约锁定金额（（用户余额 - 预约锁定金额 - 支付金额）> 0 ）
      if (!isNotSureMoney(userinfo, total_money)) {
        //虚拟币足够 且 是自动扣费
        userinfo.setUi_vc(userinfo.getUi_vc() - total_money); //分
        int count = user_infoDao.updateByKey(userinfo);
        if (count < 1) {
          //更新用户扣款数据失败
          //这里需要抛出异常
          throw new QzException("扣款失败");
        } else {
          //扣款成功
          //扣款成功之后对订单和商户和用户日志记录的处理
          payMentAllSuccess(returnData, 4, pay_park, date, total_money, user_park_coupon, userinfo);
        }
      } else {
        //开启了自动支付但是钱包金额不足
        //这里需要检查是否绑定了银行卡的ETC 如果有那么则进行ETC扣款处理
        if (!userPB.isETC_Pay(userinfo)) {
          /**
           * 这里处理推送---车辆临停扣款
           */
          String title = "系统消息";
          String message = "亲，你的订单【" + pay_park.getMy_order() + "】，因钱包余额不足,自动扣款失败。";
          asyncJpushTask.pushOrderNoMoney(userinfo.getUuid(), message, title, pay_park);
          returnData.setReturnData(errorcode_data, "亲,你钱包余额不足", "", "6");
          return;
        } else {
          //可以进行ETC调用
          int state = 0;//'是否支付成功(0:未支付 1：支付成功 2：支付失败)'
          int is_over = 0;//订单事件是否完成ETC支付（0：未完成  1：完成）
          String pay_orderid = return16UUID();
          Etc_userinfo etc_userinfo = etc_userinfoDao.selectByKey(userinfo.getEu_id());
          if (etc_userinfo != null
              && etc_userinfo.is_sign == 1
              && etc_userinfo.getIs_del() == 0
              && etc_userinfo.getVerify_sign() == 1) {

            ETCResponse eTCResponse = eTCHelper.torope(pay_orderid,
                userinfo.getUuid(),
                etc_userinfo.getName(),
                etc_userinfo.getSfz_number(),
                etc_userinfo.getBank_card_number(),
                total_money);
            if (eTCResponse == null || !eTCResponse.isSucceed()) {
              //扣款失败
              throw new QzException("扣款失败\n" + String.valueOf(eTCResponse));
            } else {
              /**
               * 扣款成功
               */

              try {
                //扣款成功之后对订单和商户和用户日志记录的处理
                payMentAllSuccess(returnData, 6, pay_park, date, total_money, user_park_coupon,
                    userinfo);
                state = 1;
                is_over = 1;
              } catch (QzException e) {
                //事务失败 交易冲正
                eTCHelper
                    .strikebal(return16UUID(), eTCResponse.getTransDate(), eTCResponse.getSerial(),
                        total_money);
                throw e;
              }
            }
            /**
             * 记录ETC扣款调用数据
             */
            etcPB.recordETC_PayLog(etc_userinfo, pay_orderid, pay_park,
                is_over, state, total_money, date);
          } else {
            //扣款失败
            throw new QzException("扣款失败");
          }
        }
      }
    }
  }


  /**
   * 订单扣款成功 公共方法的处理
   */
  public void public_DoOrderSuccess(ReturnDataNew returnData, Pay_park pay_park,
      User_park_coupon user_park_coupon, User_info userinfo) throws QzException {
    /**
     * 更新商户账户金额
     */
    userPB.upManchentMoney(pay_park.getPu_id(), pay_park.getPu_nd(), pay_park.getMoney(), 1);
    /**
     * 记录随机立免金额的事件
     * 异步操作
     * @param pay_park
     */
    asyncOrderTask.record_random_Event(pay_park, getMySelfService(), activityPB);
    //这里处理订单状态变更推送----车辆临停扣款
    /**
     * 更新优惠券使用状态
     */
    if (user_park_coupon != null) {
      //如果使用了优惠券 那么需要更改优惠券的使用状态
      User_park_coupon user_park_coupon1 = parkCouponPB
          .upUserParkCouponState(user_park_coupon.getUpc_id(), pay_park.getUi_id());
      if (user_park_coupon1 != null) {
        asyncJpushTask.pushUseCouponMsg(pay_park.getUi_nd(), user_park_coupon1);
      } else {
        //更新失败
        returnData.setReturnData(errorcode_data, "优惠券更新使用状态失败", "");
        throw new QzException("record_user_vc_act 优惠券更新使用状态失败");
      }
    }
    /**
     * 这里处理推送---车辆临停扣款
     */
    String title = "系统消息";
    String message = "亲，你的订单【" + pay_park.getMy_order() + "】，临停扣款成功。";
    payParkPB.pushOrderSate(userinfo.getUuid(), message, title, pay_park);
  }


  /**
   * payment扫描扣款成功 方法集合
   */
  public void payMentAllSuccess(ReturnDataNew returnData, int pay_source, Pay_park pay_park,
      Date date, int total_money, User_park_coupon user_park_coupon, User_info userinfo)
      throws QzException {
    //扣款成功
    pay_park.setPp_state(1);//支付状态 0:未支付 1：已经支付
    pay_park.setPay_source(pay_source);//支付类型1:支付宝2：微信3：银联4：钱包5:龙支付 6:ETC快捷支付
    pay_park.setUtime(date);//交易时间
    int count = pay_parkDao.updateByKey(pay_park);
    if (count < 1) {
      //扣款失败
      throw new QzException("扣款失败");
    }
    //扣款记录到用户金额明细中去  *** 后续是否进行异常捕获
    String act_name = "自动支付";
    int order_type = pay_park.getOrder_type();//下单类型 0: 普通下单  1：预约下单 2：租赁包月订单  3:租赁续租订单
    //下单类型0:普通下单1：预约下单2：租赁临停订单3:包月临停4：免费临停
    if (pay_park.getOrder_type() > 1) {
      order_type = 0;
    }

    userPB.recordVC(0, total_money, pay_park.getMy_order(), order_type,
        pay_park.getUi_id(), returnData, act_name, pay_park.getUi_nd(),
        pay_park.getUi_tel(),
        pay_park.getPay_source()
        , pay_park.getUpc_id(), pay_park.getDiscount_type(),
        (int) pay_park.getDiscount_money(), date);
    /**
     * 订单扣款成功 公共方法的处理
     */
    public_DoOrderSuccess(returnData, pay_park, user_park_coupon, userinfo);
  }
/****************************下面是任务调度器处理的工具方法**************************************/
}
