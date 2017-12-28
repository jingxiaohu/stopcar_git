package com.park.task;

import com.park.bean.Activity_event;
import com.park.bean.Activity_info;
import com.park.bean.Park_coupon;
import com.park.bean.Pay_park;
import com.park.bean.Random_coupon_log;
import com.park.dao.Pay_parkDao;
import com.park.mvc.service.common.ActivityPB;
import com.park.service.MySelfService;
import com.park.type.ActivityEnum;
import com.park.type.ActivityEventMethod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * 订单的异步处理
 */
@Service
public class AsyncOrderTask {

  static Logger log = LoggerFactory.getLogger(AsyncOrderTask.class);

  /**
   * 1.注册成功则查看赠券活动是否结束 如果没有 则进行赠券活动奖励发放
   */
  @Async
  public void recordEvent_coupon_register(String car_code, int type,
      long pi_id, String area_code, String pi_name,
      long ai_id, int order_type, String orderid,
      long ui_id, String ui_nd,
      MySelfService mySelfService, ActivityPB activityPB) {
    try {
      /**
       * 注册成功则查看赠券活动是否结束 如果没有 则进行赠券活动奖励发放
       */
      ai_id = ActivityEnum.activity_register.getValue();//注册
      Activity_info activity_info = activityPB.selectActivityByid(ai_id);
      if (activityPB.isActivityEffect(activity_info)) {
        //记录该事件
        /**
         * 获取优惠券基本信息表数据
         */
        String sql = "select * from park_coupon where money=200 and pc_type=0 limit 1";
        Park_coupon park_coupon = mySelfService.queryUniqueT(sql, Park_coupon.class);
        if (park_coupon == null) {
          log.error("ActivityPB.addUserParkCoupon park_coupon == null ");
          return;
        }
        int act_type = 0;
        int send_unit = 0;
        activityPB.recordActivity(park_coupon.getMoney(), car_code, type, pi_id, area_code, pi_name,
            ai_id, order_type, orderid, ui_id, ui_nd, act_type, send_unit,
            ActivityEventMethod.register_addUserParkCoupon.getValue(), 0);
      }
    } catch (Exception e) {
      // TODO Auto-generated catch block
      log.error("recordEvent is error", e);
    }
  }

  private String[] testTels = new String[]{"18180889270", "13980460681", "15802854903"};

  /**
   * （三）“龙支付充30送30！“专项活动
   * 龙支付首充值送券-- 30 送 30
   */
  @Async
  public void recordEvent_coupon_firstRecharge(
      long ui_id, String ui_nd, String ui_tel, int money,
      MySelfService mySelfService, ActivityPB activityPB) {
    try {
      /*if (!ArrayUtils.contains(testTels, ui_tel)) {//仅测试人员使用
        return;
      }*/
      /**
       * 首充值送券-- 30 送 30是否结束 如果没有 则进行赠券活动奖励发放
       */
      long ai_id = ActivityEnum.activity_recharge.getValue();//活动ID
      Activity_info activity_info = activityPB.selectActivityByid(ai_id);
      if (activityPB.isActivityEffect(activity_info)) {
        int money_base = 3000;//30元;
        //记录该事件
        /**
         * 验证是否是首次充值
         */
//        String sql = "select * from user_pay where ui_id=? and ui_nd=? "
//            + " and   act_type=1 and money>=? and state=1  limit 1";
//        User_pay user_pay = mySelfService
//            .queryUniqueT(sql, User_pay.class, ui_id, ui_nd, money_base);
//        if (user_pay != null) {
//          //不是首次充值
//          return;
//        }
        String sql = "SELECT * FROM activity_event WHERE method_name='recharge_addUserParkCoupon' AND ui_id=? AND ui_nd=? LIMIT 1";
        Activity_event activity_event = mySelfService
            .queryUniqueT(sql, Activity_event.class, ui_id, ui_nd);
        if (activity_event != null) {
          //不是首次充值
          return;
        }

        if (money < money_base) {
          //本次金额不足30元钱 不能享受该活动
          return;
        }

        int act_type = 1;
        int send_unit = 1;
        int type = 1;//减免类型(1：返券 2：随机减免)
        activityPB.recordActivity(
            money_base,
            null,
            type,
            0,
            null,
            null,
            ai_id,
            0,
            null,
            ui_id,
            ui_nd,
            act_type, send_unit,
            ActivityEventMethod.recharge_addUserParkCoupon.getValue(), 0);
      }
    } catch (Exception e) {
      // TODO Auto-generated catch block
      log.error("首充值送券-- 30 送 30是否结束 如果没有 则进行赠券活动奖励发放 recordEvent_coupon_firstRecharge is error",
          e);
    }
  }


  @Autowired
  private Pay_parkDao pay_parkDao;

  /**
   * 记录随机立免金额的事件
   */
  @Async
  @Deprecated
  public void record_random_Event2(Pay_park pay_park, MySelfService mySelfService,
      ActivityPB activityPB) {
    try {
      /**
       * 执行步骤：
       * 1、首次支付活动处理
       * 2、非首次支付活动--则检查龙支付的处理和其它支付的随机减免处理
       */

      //这里首先要验证活动是否开启 如果没有则不记录
      int type = 2;// 减免类型(1：返券2：随机减免)
      /**
       * 首先检查是否开启了首次支付活动
       */
      long ai_id = ActivityEnum.activity_FirstPay.getValue();//活动ID
      //首先判断是否开启了 首次支付赠券活动 【“吾泊首单立返“活动】
      Activity_info activity_info_firstpay = activityPB.selectActivityByid(ai_id);
      if (activityPB.isActivityEffect(activity_info_firstpay)) {
        //如果开启了首单支付活动 则先检查该用户是否能  获得该次活动优惠
        /**
         * 这里处理随机立免 确认处理
         */
        if (pay_park.getMoney() > 0 && pay_park.getUpc_id() == 0) {
          int order_type = 0;//订单类型(0:临停 1：租赁)
          int act_type = 2;//事件行为（1：充值 2：支付）
          int send_unit = 0;//赠送单位(0:吾泊平台 1：龙支付)
          //int(11)    支付类型1:支付宝2：微信3：银联4：钱包5:龙支付
          type = 1;//1：返券
          String event_MethodName = ActivityEventMethod.FirstPay_addUserParkCoupon.getValue();
          //FirstPay_addUserParkCoupon
          //检查是否是首单支付
          String sql = "SELECT *  FROM random_coupon_log WHERE act_type=2 AND ui_id=? AND ui_nd=?  AND ai_id=? LIMIT 1";
          Random_coupon_log random_coupon_log = mySelfService
              .queryUniqueT(sql, Random_coupon_log.class, pay_park.getUi_id(), pay_park.getUi_nd(),
                  ActivityEnum.activity_FirstPay.getValue());
          if (pay_park.getUi_id() > 0 && random_coupon_log == null) {
            //说明 没有进行过支付---为首次支付
            if (pay_park.getPay_source() == 5) {
              //第一次支付 且为 龙支付
              //event_MethodName = ActivityEventMethod.FirstPay_lzf_addUserParkCoupon.getValue();
              //其它平台支付
              event_MethodName = ActivityEventMethod.FirstPay_addUserParkCoupon.getValue();
              //记录事件
              activityPB.recordActivity(pay_park.getAi_money(),
                  pay_park.getCar_code(), type, pay_park.getPi_id(), pay_park.getArea_code()
                  , pay_park.getPi_name(), ai_id, order_type,
                  pay_park.getMy_order(), pay_park.getUi_id(),
                  pay_park.getUi_nd(), act_type, send_unit, event_MethodName,
                  pay_park.getPay_source());
              return;
            } else {
              //其它平台支付
              event_MethodName = ActivityEventMethod.FirstPay_addUserParkCoupon.getValue();
              //记录事件
              activityPB.recordActivity(pay_park.getAi_money(),
                  pay_park.getCar_code(), type, pay_park.getPi_id(), pay_park.getArea_code()
                  , pay_park.getPi_name(), ai_id, order_type,
                  pay_park.getMy_order(), pay_park.getUi_id(),
                  pay_park.getUi_nd(), act_type, send_unit, event_MethodName,
                  pay_park.getPay_source());
              return;
            }
          } else {
            //这里检查该次支付如果为龙支付是否是 第一次龙支付

            if (pay_park.getPay_source() == 5) {
              //龙支付
              sql = "SELECT *  FROM random_coupon_log WHERE act_type=2 AND ui_id=? AND ui_nd=?  AND ai_id=? AND pay_source=5 LIMIT 1";

              random_coupon_log = mySelfService
                  .queryUniqueT(sql, Random_coupon_log.class, pay_park.getUi_id(),
                      pay_park.getUi_nd(), ActivityEnum.activity_FirstPay.getValue());
              if (random_coupon_log == null) {
                //说明 没有进行过龙支付但是却进行过了  其它的支付
                ai_id = ActivityEnum.activity_FirstPay.getValue();
                type = 1;//返券
                //如果没有进行过龙支付 --
                //⑴首次用其他方式缴费，可获取4元优惠券，第二次用龙支付缴费，再获取4元专用停车券
                event_MethodName = ActivityEventMethod.FirstPay_lzf2_addUserParkCoupon.getValue();
                //记录事件
                activityPB.recordActivity(pay_park.getAi_money(),
                    pay_park.getCar_code(), type, pay_park.getPi_id(), pay_park.getArea_code()
                    , pay_park.getPi_name(), ai_id, order_type,
                    pay_park.getMy_order(), pay_park.getUi_id(),
                    pay_park.getUi_nd(), act_type, send_unit, event_MethodName,
                    pay_park.getPay_source());
                return;
              }

            }
          }
        }

      }

      /**
       * 然后进行检查是否开启了随机立免优惠活动
       */
      //如果没有开启 首次支付赠券活动 【“吾泊首单立返“活动】 则检查是否开启了随机立免
      ai_id = ActivityEnum.activity_RandomMoney.getValue();
      activity_info_firstpay = activityPB.selectActivityByid(ai_id);
      if (!activityPB.isActivityEffect(activity_info_firstpay)) {
        //没有开启或者无效 则不做后续工作
        return;
      } else {
        if (pay_park.getPay_source() == 5) {
          if (pay_park.getAi_money() == 0) {
            return;
          }
          //龙支付 0.5-1
          ai_id = ActivityEnum.activity_RandomMoney_lzf.getValue();
        } else {
          if (pay_park.getAi_money() == 0) {
            return;
          }
          //吾泊平台支付 0.1-0.5
          ai_id = ActivityEnum.activity_RandomMoney.getValue();
        }

        /**
         * 这里处理随机立免 确认处理
         */
        if (pay_park.getMoney() > 0 && pay_park.getUpc_id() == 0) {
          type = 2;//随机减免
          int order_type = 0;//订单类型(0:临停 1：租赁)
          int act_type = 2;//事件行为（1：充值 2：支付）
          int send_unit = 0;//赠送单位(0:吾泊平台 1：龙支付)
          //int(11)    支付类型1:支付宝2：微信3：银联4：钱包5:龙支付
          if (pay_park.getPay_source() == 5) {
            //停车场类型0：道闸停车场1：占道车场2：露天立体车库停车场
            if (pay_park.getPark_type() != 1) {
              //扈总说的 龙支付立即减免只针对 占道停车场 2017-3-13 17:00 依据 请开发人员不要删除该行注释
              return;
            }
            send_unit = 1;
            pay_park.setAi_effect(1);
            pay_parkDao.updateByKey(pay_park);
          }
          String event_MethodName = ActivityEventMethod.doRandomMoney.getValue();
          //记录事件
          activityPB.recordActivity(pay_park.getAi_money(),
              pay_park.getCar_code(), type, pay_park.getPi_id(), pay_park.getArea_code()
              , pay_park.getPi_name(), ai_id, order_type,
              pay_park.getMy_order(), pay_park.getUi_id(),
              pay_park.getUi_nd(), act_type, send_unit, event_MethodName, pay_park.getPay_source());

        }
      }

    } catch (Exception e) {
      // TODO Auto-generated catch block
      log.error("record_random is error", e);
    }
  }


  /**
   * 记录随机立免金额的事件---修改版本2017-3-13 by jxh
   */
  @Async
  public void record_random_Event(Pay_park pay_park, MySelfService mySelfService,
      ActivityPB activityPB) {
    try {
      if (pay_park.getMoney() == 0 || pay_park.getUpc_id() > 0) {
        //金额为零 或者 使用了优惠券 不能参与活动
        return;
      }
      /**
       * 执行步骤：
       * 1、首次支付活动处理
       * 2、非首次支付活动--则检查龙支付的处理和其它支付的随机减免处理
       */

      /**
       * 1、首次支付活动处理
       * 首先验证该用户是否已经参与过首单支付活动 如果没有则享有参与该活动的条件
       */
      //这里首先要验证活动是否开启 如果没有则不记录
      int type = 2;// 减免类型(1：返券2：随机减免)
      /**
       * 首先检查是否开启了首次支付活动
       */
      long ai_id = ActivityEnum.activity_FirstPay.getValue();//活动ID
      //首先判断是否开启了 首次支付赠券活动 【“吾泊首单立返“活动】
      Activity_info activity_info_firstpay = activityPB.selectActivityByid(ai_id);
      if (pay_park.getUi_id() > 0 && activityPB.isActivityEffect(activity_info_firstpay)) {
        //如果开启了首单支付活动 则先检查该用户是否能  获得该次活动优惠
        int order_type = 0;//订单类型(0:临停 1：租赁)
        int act_type = 2;//事件行为（1：充值 2：支付）
        int send_unit = 0;//赠送单位(0:吾泊平台 1：龙支付)
        //int(11)    支付类型1:支付宝2：微信3：银联4：钱包5:龙支付
        type = 1;//1：返券
        String event_MethodName = "";
        if (pay_park.getPay_source() == 5) {
          //首次龙支付
          event_MethodName = ActivityEventMethod.FirstPay_lzf_addUserParkCoupon.getValue();
        } else {
          //其它方式首次支付
          event_MethodName = ActivityEventMethod.FirstPay_addUserParkCoupon.getValue();
        }
        String sql = "SELECT * FROM activity_event WHERE ui_id=? AND ui_nd=? AND  ai_id=? AND (method_name=? OR method_name=?)  LIMIT 1";
        Activity_event activity_event = mySelfService.queryUniqueT(sql,
            Activity_event.class, pay_park.getUi_id(), pay_park.getUi_nd()
            , ActivityEnum.activity_FirstPay.getValue()
            , ActivityEventMethod.FirstPay_lzf_addUserParkCoupon.getValue()
            , ActivityEventMethod.FirstPay_addUserParkCoupon.getValue());

        if (activity_event == null) {
          //是首次支付  可以享受首次支付活动
          //记录事件
          activityPB.recordActivity(pay_park.getAi_money(),
              pay_park.getCar_code(), type, pay_park.getPi_id(), pay_park.getArea_code()
              , pay_park.getPi_name(), ai_id, order_type,
              pay_park.getMy_order(), pay_park.getUi_id(),
              pay_park.getUi_nd(), act_type, send_unit, event_MethodName,
              pay_park.getPay_source());
          return;
        } else {
          /**
           * 检查是否是第二次龙支付
           */
          if (pay_park.getPay_source() == 5 && activity_event.getMethod_name()
              .equalsIgnoreCase(ActivityEventMethod.FirstPay_addUserParkCoupon.getValue())) {
            //该次是龙支付 支付方式  则检查之前是否进行过第二次龙支付
            //（/⑴首次用其他方式缴费，可获取4元优惠券，第二次用龙支付缴费，再获取4元专用停车券）FirstPay_lzf2_addUserParkCoupon
            sql = "SELECT * FROM activity_event WHERE ui_id=? AND ui_nd=? AND  ai_id=? AND method_name=? LIMIT 1";
            activity_event = mySelfService.queryUniqueT(sql,
                Activity_event.class, pay_park.getUi_id(), pay_park.getUi_nd()
                , ActivityEnum.activity_FirstPay.getValue()
                , ActivityEventMethod.FirstPay_lzf2_addUserParkCoupon.getValue());
            if (activity_event == null) {
              //可以享受FirstPay_lzf2_addUserParkCoupon
              //记录事件
              event_MethodName = ActivityEventMethod.FirstPay_lzf2_addUserParkCoupon.getValue();
              activityPB.recordActivity(pay_park.getAi_money(),
                  pay_park.getCar_code(), type, pay_park.getPi_id(), pay_park.getArea_code()
                  , pay_park.getPi_name(), ai_id, order_type,
                  pay_park.getMy_order(), pay_park.getUi_id(),
                  pay_park.getUi_nd(), act_type, send_unit
                  , event_MethodName, pay_park.getPay_source());
              return;
            }
          }
        }
      }

      if (pay_park.getAi_money() == 0) {
        return;
      }
      //如果没有开启 首次支付赠券活动 【“吾泊首单立返“活动】 则检查是否开启了随机立免
      if (pay_park.getPay_source() == 5) {
        //龙支付 0.5-1
        ai_id = ActivityEnum.activity_RandomMoney_lzf.getValue();
      } else {
        ai_id = ActivityEnum.activity_RandomMoney.getValue();
      }
      activity_info_firstpay = activityPB.selectActivityByid(ai_id);
      if (!activityPB.isActivityEffect(activity_info_firstpay)) {
        //没有开启或者无效 则不做后续工作
        return;
      }
        /*
         * 这里处理随机立免 确认处理
         */
      type = 2;//随机减免
      int order_type = 0;//订单类型(0:临停 1：租赁)
      int act_type = 2;//事件行为（1：充值 2：支付）
      int send_unit = 0;//赠送单位(0:吾泊平台 1：龙支付)
      //int(11)    支付类型1:支付宝2：微信3：银联4：钱包5:龙支付

      // ---start---已在upEscapseOrderState中处理
//      if (pay_park.getPay_source() == 5) {
//        //停车场类型0：道闸停车场1：占道车场2：露天立体车库停车场
//        if (pay_park.getPark_type() != 1) {
//          //扈总说的 龙支付立即减免只针对 占道停车场 2017-3-13 17:00 依据 请开发人员不要删除该行注释
//          return;
//        }
//        send_unit = 1;
//        pay_park.setAi_effect(1);
//        pay_parkDao.updateByKey(pay_park);
//      }
      //-----end-------

      String event_MethodName = ActivityEventMethod.doRandomMoney.getValue();
      //记录事件
      activityPB.recordActivity(pay_park.getAi_money(),
          pay_park.getCar_code(), type, pay_park.getPi_id(), pay_park.getArea_code()
          , pay_park.getPi_name(), ai_id, order_type,
          pay_park.getMy_order(), pay_park.getUi_id(),
          pay_park.getUi_nd(), act_type, send_unit, event_MethodName, pay_park.getPay_source());


    } catch (Exception e) {
      // TODO Auto-generated catch block
      log.error("record_random_Event2 is error", e);
    }
  }
}

