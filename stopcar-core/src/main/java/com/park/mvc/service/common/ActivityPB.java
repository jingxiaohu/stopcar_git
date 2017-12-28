package com.park.mvc.service.common;

import cn.jpush.api.push.PushResult;
import com.alibaba.fastjson.JSON;
import com.park.bean.Activity_event;
import com.park.bean.Activity_info;
import com.park.bean.Park_coupon;
import com.park.bean.Pay_park;
import com.park.bean.Random_coupon_log;
import com.park.bean.ReturnDataNew;
import com.park.bean.User_carcode;
import com.park.bean.User_park_coupon;
import com.park.constants.Constants;
import com.park.exception.QzException;
import com.park.jpush.PushUtil;
import com.park.jpush.bean.JPushMessageBean;
import com.park.mvc.service.BaseBiz;
import com.park.type.ActivityEnum;
import com.park.util.RequestUtil;
import java.math.BigDecimal;
import java.util.Date;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

/**
 * 活动公用方法
 *
 * @author jingxiaohu
 */
@Service
public class ActivityPB extends BaseBiz {

  @Autowired
  protected ParkCouponPB parkCouponPB;
  @Autowired
  protected PayParkPB payParkPB;
  @Autowired
  private UserPB userPB;


  /**
   * 进行首次安装APP且注册赠送代金券处理
   *
   * @param send_unit:赠送单位(0:吾泊平台 1：龙支付)
   * @param act_type:事件行为（1：充值 2：支付）
   * @param ai_send_unit:活动发起者 赠送单位(0:吾泊平台 1：龙支付)
   */
  public User_park_coupon doGiveCoupon(long ae_id, long ui_id, String ui_nd, String car_code,
      int type, long pi_id, String area_code, String pi_name
      , Activity_info activity_info, Park_coupon park_coupon, int act_type, int send_unit,
      int ai_send_unit, int pay_source) {
    try {
      //首先检查该用户是否已经赠送过首次注册活动代金券
      /*Random_coupon_log random_coupon_log = SelectRandom_coupon_logByCarCode( ui_id, ui_nd, car_code, 1, null, null, activity_info.getAi_id());
      if(random_coupon_log != null){
				//不允许进行赠送
				return null;
			}*/
      //进行赠送
      Date date = new Date();
      User_park_coupon user_park_coupon = new User_park_coupon();
      user_park_coupon.setCtime(date);
      user_park_coupon.setDiscount(0);
//			user_park_coupon.setEnd_time(park_coupon.getEnd_time());
      user_park_coupon.setEnd_time(activity_info.getCoupon_endtime());
      user_park_coupon.setHigh_money((int) park_coupon.getMoney());
      user_park_coupon.setMoney((int) park_coupon.getMoney());
      user_park_coupon.setPc_id(park_coupon.getId());
      user_park_coupon.setUi_id(ui_id);
      user_park_coupon.setUpc_state(0);
      user_park_coupon.setUpc_type(0);
      user_park_coupon.setUtime(date);
      user_park_coupon.setAi_id(activity_info.getAi_id());
      user_park_coupon.setSend_unit(send_unit);
      int id = user_park_couponDao.insert(user_park_coupon);
      if (id > 0) {
        user_park_coupon.setUpc_id(id);
        //新增记录
        insertRandom_coupon_log(ae_id,
            activity_info.getAi_id(), pi_id, area_code,
            pi_name, car_code, (int) activity_info.getMoney(), 0, null,
            activity_info.getPartner(), type, ui_id,
            ui_nd, user_park_coupon.getUpc_id(), act_type, ai_send_unit, pay_source);

        return user_park_coupon;
      }
    } catch (Exception e) {
      // TODO Auto-generated catch block
      log.error("ActivityPB.doGiveCoupon is error", e);
    }
    return null;
  }

  //查询用户和车牌号绑定关系
  public User_carcode queryUserCarBycode(String car_code) {
    try {
      //首先判断是否已经绑定了该量车
      String sql = "SELECT *  FROM user_carcode WHERE car_code=? LIMIT 1";
      User_carcode user_carcode = getMySelfService()
          .queryUniqueT(sql, User_carcode.class, car_code);
      return user_carcode;
    } catch (Exception e) {
      // TODO Auto-generated catch block
      log.error("queryUserCarBycode is error" + e.getMessage(), e);
    }
    return null;
  }


  /**
   * @param type：减免类型(1：返券 2：随机减免)
   * @@@@@@@@@@入口方法@@@@@@@@@@ 处理添加优惠券
   */
  @Deprecated
  @Transactional(propagation = Propagation.REQUIRED)
  public synchronized boolean addUserParkCoupon333(long ae_id, long ui_id, String ui_nd,
      String car_code, int type, long pi_id, String area_code, String pi_name, long ai_id,
      int send_unit) {
    try {
      /**
       * 获取活动基本信息表数据
       */
      String sql = "SELECT * FROM activity_info WHERE ai_id=? LIMIT 1";
      Activity_info activity_info = getMySelfService()
          .queryUniqueT(sql, Activity_info.class, ai_id);
      if (activity_info == null) {
        log.error("ActivityPB.addUserParkCoupon activity_info == null ai_id=" + ai_id);
        return false;
      }
      /**
       * 获取优惠券基本信息表数据
       */
      sql = "SELECT * FROM park_coupon WHERE ai_id=? LIMIT 1";
      Park_coupon park_coupon = getMySelfService()
          .queryUniqueT(sql, Park_coupon.class, activity_info.getAi_id());
      if (park_coupon == null) {
        log.error("ActivityPB.addUserParkCoupon park_coupon == null ai_id=" + ai_id);
        return false;
      }

      //判断送券金额是否还能用
      if (activity_info.getMoney() - park_coupon.getMoney() < 0) {
        //活动立即结束
        activity_info.setState(1);
        activity_info.setUtime(new Date());
        int count = activity_infoDao.updateByKey(activity_info);
        if (count < 1) {
          log.error("活动立即结束 更新失败");
        }
        return false;
      }

      int ai_send_unit = 0;
      /**
       * 赠送优惠券
       */
      User_park_coupon user_park_coupon = doGiveCoupon(ae_id, ui_id, ui_nd, car_code, type, pi_id,
          area_code, pi_name
          , activity_info, park_coupon, 0, send_unit, ai_send_unit, 0);
      if (user_park_coupon != null) {
        //活动经费进行减少
        if (activity_info.getMoney() - park_coupon.getMoney() >= 0) {
          activity_info.setMoney(activity_info.getMoney() - park_coupon.getMoney());
          int count = activity_infoDao.updateByKey(activity_info);
          if (count < 1) {
            log.error("活动经费进行减少失败");
          }
          return false;
        }
      }

      /**
       * 更新事件处理状态和记录活动经费剩余的快照
       */
      if (!upActivity_event(ae_id, (int) activity_info.getMoney())) {
        return false;
      }

      return true;
    } catch (Exception e) {
      // TODO Auto-generated catch block
      log.error("处理添加优惠券失败addUserParkCoupon is error", e);
    }
    return false;
  }


  /**
   * 随机立免活动奖励设置---订单生成的时候把随机优惠金额设置进去
   */
  public void record_random(Pay_park pay_park) {
    try {

      long ai_id = ActivityEnum.activity_RandomMoney.getValue();//活动ID
      if (pay_park.getPay_source() == 5) {
        //龙支付 0.5-1
        ai_id = ActivityEnum.activity_RandomMoney_lzf.getValue();
      } else {
        //吾泊平台支付 0.1-0.5
        ai_id = ActivityEnum.activity_RandomMoney.getValue();
      }
      //这里首先要验证活动是否开启 如果没有则不记录
      Activity_info activity_info = selectActivityByid(ai_id);
      if (!isActivityEffect(activity_info)) {
        //如果活动结束 则不处理
        return;
      }
      //验证是否绑定了车牌号
      if (pay_park.getUi_id() == 0 || RequestUtil.checkObjectBlank(pay_park.getUi_nd())) {
        //用户不存在 该车牌号没有被绑定
        return;
      }
      //进行金额随机 1-100分 之间
      int money_random = 0;
      if (pay_park.getPay_source() == 5) {
        //龙支付 0.4-0.8
        money_random = RandomUtils.nextInt(4, 9) * 10;
      } else {
        //吾泊平台支付 0.1-0.5
        money_random = RandomUtils.nextInt(1, 6) * 10;
      }
      /**
       * 查看是否含有优惠券和绑定了车牌号   如果没有优惠券且已经绑定了车牌号  则进行随机立免
       */
      if (isActivityEffect(activity_info)) {
        //验证是否含有未过期的优惠券
        User_park_coupon user_park_coupon = parkCouponPB
            .ReturnMaxMoneyCoupon(pay_park.getUi_id(), pay_park.getMoney());
        if (user_park_coupon != null) {
          //有优惠券则不能享有随机立免待遇
          return;
        }
        //设置随机立免优惠金额
        pay_park.setAi_id(ai_id);
        pay_park.setAi_money(money_random);
      } else {
        //活动结束或者活动金额耗尽
        //活动立即结束
        activity_info.setState(1);
        activity_info.setUtime(new Date());
        activity_info.setMoney(0);
        int count = activity_infoDao.updateByKey(activity_info);
        if (count < 1) {
          log.error("活动立即结束 更新失败");
        }
      }

    } catch (Exception e) {
      // TODO Auto-generated catch block
      log.error("record_random is error", e);
    }
  }

  /**
   * @param type：：减免类型(1：返券 2：随机减免)
   * @param act_type: 1：充值 2：支付
   * @param send_unit:赠送单位（0：吾泊 1：龙支付建行）
   * @@@@@@@@@@入口方法@@@@@@@@@@ 处理随机立减处理----订单完成支付后 进行对于的记录和活动总金额的减少
   */
  @Transactional(propagation = Propagation.REQUIRED, rollbackFor = QzException.class)
  public synchronized String doRandomMoney(long ae_id, int money, String car_code,
      int type, long pi_id, String area_code, String pi_name,
      long ai_id, int order_type, String orderid, int act_type, int send_unit, int pay_source)
      throws QzException {
    try {
      /**
       * 获取活动基本信息表数据
       */
      String sql = "SELECT * FROM activity_info WHERE ai_id=? LIMIT 1";
      Activity_info activity_info = getMySelfService()
          .queryUniqueT(sql, Activity_info.class, ai_id);
      if (activity_info == null) {
        log.error("ActivityPB.addUserParkCoupon activity_info == null ai_id=" + ai_id);
        return null;
      }
      //判断送券金额是否还能用
      if (activity_info.getMoney() - money < 0) {
        //活动立即结束
        activity_info.setState(1);
        activity_info.setUtime(new Date());
        activity_info.setMoney(0);
        int count = activity_infoDao.updateByKey(activity_info);
        if (count < 1) {
          log.error("活动立即结束 更新失败");
          throw new QzException("活动立即结束 更新失败");
        }
        return null;
      }

      Pay_park pay_park = payParkPB.selectOnePayPark(orderid);
      if (pay_park == null) {
        //订单不存在
        throw new QzException("活动立即结束 更新失败");
      }
      //新增记录
      Random_coupon_log random_coupon_log = insertRandom_coupon_log(ae_id,
          pay_park.getAi_id(), pi_id, area_code,
          pi_name, car_code, money, order_type, orderid,
          activity_info.getPartner(), type, pay_park.getUi_id(),
          pay_park.getUi_nd(), 0, act_type, send_unit, pay_source);
      //活动经费进行减少
      if (random_coupon_log != null) {
        if (activity_info.getMoney() - money >= 0) {
          activity_info.setMoney(activity_info.getMoney() - money);
          activity_info.setPeople_num(activity_info.getPeople_num() + 1);
          int count = activity_infoDao.updateByKey(activity_info);
          if (count != 1) {
            log.error("活动经费进行减少失败");
            throw new QzException("活动更新失败");
          } else {
            /**
             * 更新事件处理状态和记录活动经费剩余的快照
             */
            if (!upActivity_event(ae_id, (int) activity_info.getMoney())) {
              log.error("更新事件处理状态和记录活动经费剩余的快照 upActivity_event error ae_id=" + ae_id);
              throw new QzException("更新事件处理状态和记录活动经费剩余的快照 upActivity_event error ae_id=" + ae_id);
            } else {
              //事件完成  用户钱包新增随机金额
              //扣款记录到用户金额明细中去  *** 后续是否进行异常捕获
              ReturnDataNew returnData = new ReturnDataNew();
              String act_name = "支付随机返钱活动";
              userPB.recordVC(2, pay_park.getAi_money(), pay_park.getMy_order(),
                  pay_park.getOrder_type(), pay_park.getUi_id(), returnData, act_name,
                  pay_park.getUi_nd(), pay_park.getUi_tel(), 4
                  , pay_park.getUpc_id(), pay_park.getDiscount_type(),
                  (int) pay_park.getDiscount_money(), new Date());
              //更新用户资金
              userPB.updateUserMoney(pay_park.getUi_id(), pay_park.getUi_nd(), 0,
                  pay_park.getAi_money());
              //更新订单的处理状态
              pay_park.setAi_effect(1);
              pay_parkDao.updateByKey(pay_park);
              return "恭喜亲的订单【" + pay_park.getMy_order() + "】获得随机立减" + new BigDecimal(money)
                  .divide(new BigDecimal(100), 2, BigDecimal.ROUND_HALF_UP) + "元优惠";
            }
          }
        }
      }
      return null;
    } catch (Exception e) {
      // TODO Auto-generated catch block
      log.error("处理随机立减处理失败doRandomMoney is error", e);
      throw new QzException("活动更新失败");
    }
  }


  /**
   * 更新事件处理状态和记录活动经费剩余的快照
   */
  public boolean upActivity_event(long ae_id, int money) {
    try {
      /**
       * 更新事件处理状态和记录活动经费剩余的快照
       */
      Activity_event activity_event = activity_eventDao.selectByKey(ae_id);
      if (activity_event != null) {
        activity_event.setState(1);
        activity_event.setAi_money(money);
        activity_event.setUtime(new Date());
        int count = activity_eventDao.updateByKey(activity_event);
        if (count == 1) {
          return true;
        }
      }
    } catch (Exception e) {
      // TODO Auto-generated catch block
      log.error("upActivity_event is error ae_id=" + ae_id, e);
    }
    return false;
  }


  /**
   * 记录活动事件
   *
   * @param money:随机立减金额 单位分
   * @param type：：减免类型(1：返券 2：随机减免)
   * @param act_type: 1：充值 2：支付
   * @param send_unit:赠送单位（0：吾泊 1：龙支付建行）
   * @param method_name :执行事件对应的方法名字
   */
  public void recordActivity(int money, String car_code, int type,
      long pi_id, String area_code, String pi_name,
      long ai_id, int order_type,
      String orderid, long ui_id,
      String ui_nd, int act_type,
      int send_unit, String method_name, int pay_source) {
    try {
      String md5 = DigestUtils
          .md5Hex(type + ai_id + ui_id + ui_nd + orderid + order_type + pi_id + car_code);
      String sql = "SELECT * FROM activity_event WHERE  type=? AND ai_id=? AND md5=? LIMIT 1";
      Activity_event activity_event = getMySelfService()
          .queryUniqueT(sql, Activity_event.class, type, ai_id, md5);
      if (activity_event == null) {
        activity_event = new Activity_event();
        Date date = new Date();
        activity_event.setAi_id(ai_id);
        activity_event.setAi_money(0);
        activity_event.setArea_code(area_code);
        activity_event.setCar_code(car_code);
        activity_event.setCtime(date);
        activity_event.setMoney(money);
        activity_event.setOrder_type(order_type);
        activity_event.setOrderid(orderid);
        activity_event.setPi_id(pi_id);
        activity_event.setPi_name(pi_name);

        if (ai_id == ActivityEnum.activity_RandomMoney_lzf.getValue()) {
          activity_event.setState(1);
        } else {
          activity_event.setState(0);
        }
        activity_event.setType(type);
        activity_event.setUi_id(ui_id);
        activity_event.setUi_nd(ui_nd);
        activity_event.setUtime(date);

        activity_event.setAct_type(act_type);
        activity_event.setSend_unit(send_unit);
        activity_event.setMethod_name(method_name);
        activity_event.setMd5(md5);

        activity_event.setPay_source(pay_source);
        int id = activity_eventDao.insert(activity_event);
        if (id < 1) {
          //该事件未被记录
          log.error("该事件未被记录--写入数据库的时候出现错误");
        }
      }


    } catch (Exception e) {
      // TODO Auto-generated catch block
      log.error("记录活动事件失败recordActivity is error", e);
    }

  }


  //  @Transactional(rollbackFor = QzException.class)
  public void doActivityEvent(Activity_event activity_event) throws QzException {
    String message = null;
    //减免类型(1：返券 2：随机减免)
    switch (activity_event.getType()) {
      case 0:
        break;
      case 1: {//赠券
        //1.注册
        if ("register_addUserParkCoupon".equalsIgnoreCase(activity_event.getMethod_name())) {
          message = register_addUserParkCoupon(
              activity_event.getId(),
              activity_event.getUi_id(),
              activity_event.getUi_nd(),
              activity_event.getCar_code(),
              activity_event.getType(),
              activity_event.getPi_id(),
              activity_event.getArea_code(),
              activity_event.getPi_name(),
              activity_event.getAi_id()
          );
        }
        //2.吾泊首单立返
        if ("FirstPay_addUserParkCoupon".equalsIgnoreCase(activity_event.getMethod_name())) {
          message = FirstPay_addUserParkCoupon(
              activity_event.getId(),
              activity_event.getUi_id(),
              activity_event.getUi_nd(),
              activity_event.getCar_code(),
              activity_event.getType(),
              activity_event.getPi_id(),
              activity_event.getArea_code(),
              activity_event.getPi_name(),
              activity_event.getAi_id(),
              activity_event.getPay_source()
          );
        }
        if ("FirstPay_lzf_addUserParkCoupon"
            .equalsIgnoreCase(activity_event.getMethod_name())) {
          message = FirstPay_lzf_addUserParkCoupon(
              activity_event.getId(),
              activity_event.getUi_id(),
              activity_event.getUi_nd(),
              activity_event.getCar_code(),
              activity_event.getType(),
              activity_event.getPi_id(),
              activity_event.getArea_code(),
              activity_event.getPi_name(),
              activity_event.getAi_id(),
              activity_event.getPay_source()
          );
        }
        if ("FirstPay_lzf2_addUserParkCoupon"
            .equalsIgnoreCase(activity_event.getMethod_name())) {
          message = FirstPay_lzf2_addUserParkCoupon(
              activity_event.getId(),
              activity_event.getUi_id(),
              activity_event.getUi_nd(),
              activity_event.getCar_code(),
              activity_event.getType(),
              activity_event.getPi_id(),
              activity_event.getArea_code(),
              activity_event.getPi_name(),
              activity_event.getAi_id(),
              activity_event.getPay_source()
          );
        }

        //3.龙支付充30送30！
        if ("recharge_addUserParkCoupon".equalsIgnoreCase(activity_event.getMethod_name())) {
          message = recharge_addUserParkCoupon(
              activity_event.getId(),
              activity_event.getUi_id(),
              activity_event.getUi_nd(),
              activity_event.getCar_code(),
              activity_event.getType(),
              activity_event.getPi_id(),
              activity_event.getArea_code(),
              activity_event.getPi_name(),
              activity_event.getAi_id()
          );
        }
      }
      break;
      case 2: {//随机立免
        if ("doRandomMoney".equalsIgnoreCase(activity_event.getMethod_name())) {
          //doRandomMoney(long ae_id,int money,String car_code,int type,long pi_id,String area_code,String pi_name,long ai_id,int order_type,String orderid);
          doRandomMoney(
              activity_event.getId(),
              activity_event.getMoney(),
              activity_event.getCar_code(),
              activity_event.getType(),
              activity_event.getPi_id(),
              activity_event.getArea_code(),
              activity_event.getPi_name(),
              activity_event.getAi_id(),
              activity_event.getOrder_type(),
              activity_event.getOrderid(),
              activity_event.getAct_type(),
              activity_event.getSend_unit(),
              activity_event.getPay_source()
          );
        }

      }
      break;
      default:
        break;
    }
    pushMessage(activity_event, message);
  }

  /**
   * 活动推送消息
   *
   * @param activity_event 活动事件
   * @param message 消息内容
   */
  private void pushMessage(Activity_event activity_event, String message) throws QzException {
    String ui_nd = activity_event.getUi_nd();
    if (StringUtils.hasText(ui_nd) && StringUtils.hasText(message)) {
      JPushMessageBean jPushMessageBean = new JPushMessageBean();
      jPushMessageBean.setMessage(message);
      jPushMessageBean.setImgurl(Constants.JPUSH_LOGO);
      jPushMessageBean.setTitle("系统消息");
      jPushMessageBean.setDate(new Date());
      jPushMessageBean.setType(5);
      PushResult pushResult = PushUtil
          .SendPush(JSON.toJSONString(jPushMessageBean), jPushMessageBean.getMessage(), ui_nd);
      if (pushResult == null || !pushResult.isResultOK()) {
        throw new QzException("推送消息失败");
      }
    }

  }

/*****************************************************************************/
/*******************************下面是活动****************************************/
  /**
   * 1.注册送券活动
   * 客户在任何渠道下载吾泊APP，均可获取2+2+2元优惠券，客户在使用吾泊进行停车缴费时，可以用优惠券抵扣停车费用。
   * 1.	优惠券分为2张2元的吾泊停车优惠券（任何支付方式可用，包括龙支付），1张2元吾泊停车龙支付优惠券（龙支付专用）
   * 2.	优惠名额共计10000个，先到先得，送完即止。
   * 3.优惠券每次只可使用1张，不可兑换现金，不与其他优惠同时享受
   *
   * @param type：减免类型(1：返券 2：随机减免)
   */
  @Transactional(propagation = Propagation.REQUIRED)
  public synchronized String register_addUserParkCoupon(long ae_id, long ui_id, String ui_nd,
      String car_code, int type, long pi_id, String area_code, String pi_name, long ai_id)
      throws QzException {
    try {
      /**
       * 获取活动基本信息表数据
       */
      Activity_info activity_info = selectActivityByid2(ai_id);
      if (activity_info == null) {
        log.error("ActivityPB.addUserParkCoupon activity_info == null ai_id=" + ai_id);
        throw new QzException("活动更新失败");
      }
      /**
       * 获取优惠券基本信息表数据
       * 均可获取2+2+2元优惠券
       */
      Park_coupon park_coupon = selectCouponByMoney(200);
      if (park_coupon == null) {
        log.error("ActivityPB.addUserParkCoupon park_coupon == null ai_id=" + ai_id);
        throw new QzException("活动更新失败");
      }

      //判断送券金额是否还能用
//			int money = park_coupon.getMoney()*3;
      int money = park_coupon.getMoney() * 2;
      if (activity_info.getMoney() - money < 0) {
        //活动立即结束
        activity_info.setState(1);
        activity_info.setUtime(new Date());
        int count = activity_infoDao.updateByKey(activity_info);
        if (count < 1) {
          log.error("活动立即结束 更新失败");
          throw new QzException("活动更新失败");
        }
        return null;
      }

      int act_type = 0;//事件行为（1：充值 2：支付）
      int ai_send_unit = 0;//send_unit:赠送单位(0:吾泊平台 1：龙支付)

      /**
       * 赠送优惠券3张
       * send_unit:赠送单位(0:吾泊平台 1：龙支付)
       * 优惠券分为2张2元的吾泊停车优惠券（任何支付方式可用，包括龙支付），1张2元吾泊停车龙支付优惠券（龙支付专用）
       */
      for (int i = 0; i < 2; i++) {
        User_park_coupon user_park_coupon = doGiveCoupon(
            ae_id, ui_id, ui_nd, car_code,
            type, pi_id, area_code, pi_name
            , activity_info, park_coupon, act_type, 0, ai_send_unit, 0);
        if (user_park_coupon != null) {
          //活动经费进行减少
          if (activity_info.getMoney() - park_coupon.getMoney() >= 0) {
            activity_info.setMoney(activity_info.getMoney() - park_coupon.getMoney());
            activity_info.setPeople_num(activity_info.getPeople_num() + 1);
            int count = activity_infoDao.updateByKey(activity_info);
            if (count < 1) {
              log.error("活动经费进行减少失败");
              throw new QzException("活动更新失败");
            }

          }
        }
      }
      //龙之付送券1张
      //优惠券分为2张2元的吾泊停车优惠券（任何支付方式可用，包括龙支付），1张2元吾泊停车龙支付优惠券（龙支付专用）
      User_park_coupon user_park_coupon = doGiveCoupon(ae_id, ui_id, ui_nd, car_code, type, pi_id,
          area_code, pi_name
          , activity_info, park_coupon, 0, 1, ai_send_unit, 0);
      if (user_park_coupon != null) {
        //活动经费进行减少
        if (activity_info.getMoney() - park_coupon.getMoney() >= 0) {
          activity_info.setMoney(activity_info.getMoney() - park_coupon.getMoney());
          activity_info.setPeople_num(activity_info.getPeople_num() + 1);
          int count = activity_infoDao.updateByKey(activity_info);
          if (count < 1) {
            log.error("活动经费进行减少失败");
            throw new QzException("活动更新失败");
          }

        }
      }

      /**
       * 更新事件处理状态和记录活动经费剩余的快照
       */
      if (!upActivity_event(ae_id, (int) activity_info.getMoney())) {
        throw new QzException("活动更新失败");
      }
      return getPushMessage(2, park_coupon.getMoney(), 1, park_coupon.getMoney());
    } catch (Exception e) {
      log.error("处理添加优惠券失败register_addUserParkCoupon is error", e);
      throw new QzException("活动更新失败");
    }
  }

  public static String getPushMessage(int count, int coupon_money, int lzf_count,
      int lzf_coupon_money) {
    String message = "吾泊平台赠送的";
    if (count > 0) {
      message += String.format("%s张%s元吾泊停车代金券", count, coupon_money / 100);
      if (lzf_count > 0) {
        message += "和";
      }
    }
    if (lzf_count > 0) {
      message += String.format("%s张%s元龙支付代金券", lzf_count, lzf_coupon_money / 100);
    }
    return message + "已放入账户，请在APP-我的代金券中查收！";
  }

  public static String getPushMessage2(int count, int coupon_money, int lzf_count,
      int lzf_coupon_money) {
    String message = "吾泊平台赠送的";
    if (count > 0) {
      message += String.format("%s张%s元龙支付代金券", count, coupon_money / 100);
      if (lzf_count > 0) {
        message += "和";
      }
    }
    if (lzf_count > 0) {
      message += String.format("%s张%s元龙支付代金券", lzf_count, lzf_coupon_money / 100);
    }
    return message + "已放入账户，请在APP-我的代金券中查收！";
  }

  /**
   * 2.吾泊首单立返“活动
   * 活动规则：
   * 客户首次在吾泊客户端进行停车缴费时，在完成缴费动作后，将收到价格4元的优惠停车券。
   * 客户首次在吾泊客户端用龙支付进行缴费，除吾泊优惠券后，另外获取价格4元的专用停车券。
   * ⑴首次用其他方式缴费，可获取4元优惠券，第二次用龙支付缴费，再获取4元专用停车券
   * ⑵首次就用龙支付缴费，可一次性获取8元停车券。
   *
   * 1.	吾泊2张2元的吾泊停车龙支付优惠券，龙支付专用停车券为1张4元。
   * 2.	吾泊优惠名额共计20000个，龙支付优惠名额5000个，先到先得，送完即止。
   * 3.	优惠券每次只可使用1张，不可兑换现金，不与其他优惠同时享受。
   *
   * @param type：减免类型(1：返券 2：随机减免)
   */
  @Transactional(propagation = Propagation.REQUIRED, rollbackFor = QzException.class)
  public synchronized String FirstPay_addUserParkCoupon(long ae_id, long ui_id, String ui_nd,
      String car_code, int type, long pi_id,
      String area_code, String pi_name, long ai_id, int pay_source) throws QzException {
    try {
      /**
       * 获取活动基本信息表数据
       */
      Activity_info activity_info = selectActivityByid2(ai_id);
      if (activity_info == null) {
        log.error("ActivityPB.addUserParkCoupon activity_info == null ai_id=" + ai_id);
        throw new QzException("活动更新失败");
      }
      /**
       * 获取优惠券基本信息表数据
       * 均可获取2元优惠券
       */
      Park_coupon park_coupon_2 = selectCouponByMoney(200);
      if (park_coupon_2 == null) {
        log.error("ActivityPB.addUserParkCoupon park_coupon_2 == null ai_id=" + ai_id);
        throw new QzException("活动更新失败");
      }

      //判断送券金额是否还能用  1.	吾泊2张2元的吾泊停车龙支付优惠券
      int money = park_coupon_2.getMoney() * 2;
      if (activity_info.getMoney() - money < 0) {
        //活动立即结束
        activity_info.setState(1);
        activity_info.setUtime(new Date());
        int count = activity_infoDao.updateByKey(activity_info);
        if (count < 1) {
          log.error("活动立即结束 更新失败");
          throw new QzException("活动更新失败");
        }
        return null;
      }

      int act_type = 2;//事件行为（1：充值 2：支付）
      int ai_send_unit = 0;//send_unit:赠送单位(0:吾泊平台 1：龙支付)

      /**
       * 赠送优惠券2张2元
       * send_unit:赠送单位(0:吾泊平台 1：龙支付)
       */
      for (int i = 0; i < 2; i++) {
        User_park_coupon user_park_coupon = doGiveCoupon(ae_id, ui_id, ui_nd, car_code, type, pi_id,
            area_code, pi_name
            , activity_info, park_coupon_2, act_type, 0, ai_send_unit, pay_source);
        if (user_park_coupon != null) {
          //活动经费进行减少
          if (activity_info.getMoney() - park_coupon_2.getMoney() >= 0) {
            activity_info.setMoney(activity_info.getMoney() - park_coupon_2.getMoney());
            activity_info.setPeople_num(activity_info.getPeople_num() + 1);
            int count = activity_infoDao.updateByKey(activity_info);
            if (count < 1) {
              log.error("活动经费进行减少失败");
              throw new QzException("活动更新失败");
            }

          }
        }
      }
      /**
       * 更新事件处理状态和记录活动经费剩余的快照
       */
      if (!upActivity_event(ae_id, (int) activity_info.getMoney())) {
        throw new QzException("活动更新失败");
      }
      return getPushMessage(2, park_coupon_2.getMoney(), 0, 0);
    } catch (Exception e) {
      // TODO Auto-generated catch block
      log.error("处理添加优惠券失败 FirstPay_addUserParkCoupon is error", e);
      throw new QzException("活动更新失败");
    }
  }

  /**
   *
   */
  /**
   * 2.龙支付-吾泊首单立返“活动
   * 活动规则：
   * 客户首次在吾泊客户端进行停车缴费时，在完成缴费动作后，将收到价格4元的优惠停车券。
   * 客户首次在吾泊客户端用龙支付进行缴费，除吾泊优惠券后，另外获取价格4元的专用停车券。
   * ⑴首次用其他方式缴费，可获取4元优惠券，第二次用龙支付缴费，再获取4元专用停车券
   * ⑵首次就用龙支付缴费，可一次性获取8元停车券。
   *
   * 1.	吾泊2张2元的吾泊停车龙支付优惠券，龙支付专用停车券为1张4元。
   * 2.	吾泊优惠名额共计20000个，龙支付优惠名额5000个，先到先得，送完即止。
   * 3.	优惠券每次只可使用1张，不可兑换现金，不与其他优惠同时享受。
   *
   * 2017/04/25 龙支付代金券1张4元改为2张2元  modifiedBy 吴永平
   *
   * @param type：减免类型(1：返券 2：随机减免)
   */
  @Transactional(propagation = Propagation.REQUIRED, rollbackFor = QzException.class)
  public synchronized String FirstPay_lzf_addUserParkCoupon(long ae_id, long ui_id, String ui_nd,
      String car_code, int type, long pi_id,
      String area_code, String pi_name, long ai_id, int pay_source) throws QzException {
    try {
      /**
       * 获取活动基本信息表数据
       */
      Activity_info activity_info = selectActivityByid2(ai_id);
      if (activity_info == null) {
        log.error("ActivityPB.addUserParkCoupon activity_info == null ai_id=" + ai_id);
        throw new QzException("活动更新失败");
      }
      /**
       * 获取优惠券基本信息表数据
       * 均可获取2元优惠券
       */
      Park_coupon park_coupon_2 = selectCouponByMoney(200);
      if (park_coupon_2 == null) {
        log.error("ActivityPB.addUserParkCoupon park_coupon_2 == null ai_id=" + ai_id);
        throw new QzException("活动更新失败");
      }

      /**
       * 获取优惠券基本信息表数据
       * 均可获取4元优惠券
       */
//      Park_coupon park_coupon_4 = selectCouponByMoney(400);
//      if (park_coupon_4 == null) {
//        log.error("ActivityPB.addUserParkCoupon park_coupon_4 == null ai_id=" + ai_id);
//        throw new QzException("活动更新失败");
//      }
      //判断送券金额是否还能用  1.	吾泊2张2元的吾泊停车龙支付优惠券，龙支付专用停车券为2张2元
      int money = park_coupon_2.getMoney() * 4;
      if (activity_info.getMoney() - money < 0) {
        //活动立即结束
        activity_info.setState(1);
        activity_info.setUtime(new Date());
        int count = activity_infoDao.updateByKey(activity_info);
        if (count < 1) {
          log.error("活动立即结束 更新失败");
          throw new QzException("活动更新失败");
        }
        return null;
      }

      int act_type = 2;//事件行为（1：充值 2：支付）
      int ai_send_unit = 0;//send_unit:赠送单位(0:吾泊平台 1：龙支付)

      /**
       * 赠送优惠券2张2元
       * send_unit:赠送单位(0:吾泊平台 1：龙支付)
       */
      for (int i = 0; i < 2; i++) {
        User_park_coupon user_park_coupon = doGiveCoupon(ae_id, ui_id, ui_nd, car_code, type, pi_id,
            area_code, pi_name
            , activity_info, park_coupon_2, act_type, 0, ai_send_unit, pay_source);
        if (user_park_coupon != null) {
          //活动经费进行减少
          if (activity_info.getMoney() - park_coupon_2.getMoney() >= 0) {
            activity_info.setMoney(activity_info.getMoney() - park_coupon_2.getMoney());
            activity_info.setPeople_num(activity_info.getPeople_num() + 1);
            int count = activity_infoDao.updateByKey(activity_info);
            if (count < 1) {
              log.error("活动经费进行减少失败");
              throw new QzException("活动更新失败");
            }

          }
        }
      }
      //龙之付送券2张2元
      for (int i = 0; i < 2; i++) {
        User_park_coupon user_park_coupon = doGiveCoupon(ae_id, ui_id, ui_nd, car_code, type, pi_id,
            area_code, pi_name
            , activity_info, park_coupon_2, act_type, 1, ai_send_unit, pay_source);
        if (user_park_coupon != null) {
          //活动经费进行减少
          if (activity_info.getMoney() - park_coupon_2.getMoney() >= 0) {
            activity_info.setMoney(activity_info.getMoney() - park_coupon_2.getMoney());
            activity_info.setPeople_num(activity_info.getPeople_num() + 1);
            int count = activity_infoDao.updateByKey(activity_info);
            if (count < 1) {
              log.error("活动经费进行减少失败");
              throw new QzException("活动更新失败");
            }

          }
        }
      }
      /**
       * 更新事件处理状态和记录活动经费剩余的快照
       */
      if (!upActivity_event(ae_id, (int) activity_info.getMoney())) {
        throw new QzException("活动更新失败");
      }
      return getPushMessage(2, park_coupon_2.getMoney(), 2, park_coupon_2.getMoney());
    } catch (Exception e) {
      // TODO Auto-generated catch block
      log.error("处理添加优惠券失败 FirstPay_lzf_addUserParkCoupon is error", e);
      throw new QzException("活动更新失败");
    }
  }

  /**
   * 2.龙支付-2-.吾泊首单立返“活动
   * 活动规则：
   * 客户首次在吾泊客户端进行停车缴费时，在完成缴费动作后，将收到价格4元的优惠停车券。
   * 客户首次在吾泊客户端用龙支付进行缴费，除吾泊优惠券后，另外获取价格4元的专用停车券。
   * ⑴首次用其他方式缴费，可获取4元优惠券，第二次用龙支付缴费，再获取4元专用停车券
   * ⑵首次就用龙支付缴费，可一次性获取8元停车券。
   *
   * 1.	吾泊2张2元的吾泊停车龙支付优惠券，龙支付专用停车券为1张4元。
   * 2.	吾泊优惠名额共计20000个，龙支付优惠名额5000个，先到先得，送完即止。
   * 3.	优惠券每次只可使用1张，不可兑换现金，不与其他优惠同时享受。
   *
   * 2017/04/25 代金券1张4元改为2张2元  modifiedBy 吴永平
   *
   * @param type：减免类型(1：返券 2：随机减免)
   */
  @Transactional(propagation = Propagation.REQUIRED, rollbackFor = QzException.class)
  public synchronized String FirstPay_lzf2_addUserParkCoupon(long ae_id, long ui_id, String ui_nd,
      String car_code, int type, long pi_id,
      String area_code, String pi_name, long ai_id, int pay_source) throws QzException {
    try {
      /**
       * 获取活动基本信息表数据
       */
      Activity_info activity_info = selectActivityByid2(ai_id);
      if (activity_info == null) {
        log.error("ActivityPB.addUserParkCoupon activity_info == null ai_id=" + ai_id);
        throw new QzException("活动更新失败");
      }
      /**
       * 获取优惠券基本信息表数据
       * 均可获取2元优惠券
       */
      Park_coupon park_coupon_2 = selectCouponByMoney(200);
      if (park_coupon_2 == null) {
        log.error("ActivityPB.addUserParkCoupon park_coupon_4 == null ai_id=" + ai_id);
        throw new QzException("活动更新失败");
      }
      //判断送券金额是否还能用  龙支付专用停车券为2张2元
      int money = park_coupon_2.getMoney() * 2;
      if (activity_info.getMoney() - money < 0) {
        //活动立即结束
        activity_info.setState(1);
        activity_info.setUtime(new Date());
        int count = activity_infoDao.updateByKey(activity_info);
        if (count < 1) {
          log.error("活动立即结束 更新失败");
          throw new QzException("活动更新失败");
        }
        return null;
      }
      int act_type = 2;//事件行为（1：充值 2：支付）
      int ai_send_unit = 0;//send_unit:赠送单位(0:吾泊平台 1：龙支付)
      //龙之付送券2张2元
      for (int i = 0; i < 2; i++) {
        User_park_coupon user_park_coupon = doGiveCoupon(ae_id, ui_id, ui_nd, car_code, type, pi_id,
            area_code, pi_name
            , activity_info, park_coupon_2, act_type, 1, ai_send_unit, pay_source);
        if (user_park_coupon != null) {
          //活动经费进行减少
          if (activity_info.getMoney() - park_coupon_2.getMoney() >= 0) {
            activity_info.setMoney(activity_info.getMoney() - park_coupon_2.getMoney());
            activity_info.setPeople_num(activity_info.getPeople_num() + 1);
            int count = activity_infoDao.updateByKey(activity_info);
            if (count < 1) {
              log.error("活动经费进行减少失败");
              throw new QzException("活动更新失败");
            }
          }
        }
      }
      /**
       * 更新事件处理状态和记录活动经费剩余的快照
       */
      if (!upActivity_event(ae_id, (int) activity_info.getMoney())) {
        throw new QzException("活动更新失败");
      }
      return getPushMessage(0, 0, 2, park_coupon_2.getMoney());
    } catch (Exception e) {
      // TODO Auto-generated catch block
      log.error("处理添加优惠券失败 FirstPay_lzf2_addUserParkCoupon is error", e);
      throw new QzException("活动更新失败");
    }
  }


  /**
   * 3 .龙支付充30送30！“专项活动
   * 活动规则：
   * 建行客户经理，在营业网点向客户推荐使用龙支付在吾泊客户端进行充值，一次性充满30元，赠送价值30元的吾泊专用停车券。
   * 1.	建行客户经理需要经过产品培训，引导客户完成一系列下载安装。
   * 2.	优惠券面值多样化，最高为5元，最低为2元，一共为10张。(5*4+2*5=30  5*2+2*10=30)
   * 3.	优惠名额共计2000个，先到先得，送完即止。
   * 4.	优惠券每次只可使用1张，不可兑换现金，不与其他优惠同时享受。
   *
   * @param type：减免类型(1：返券 2：随机减免)
   */
  @Transactional(propagation = Propagation.REQUIRED, rollbackFor = QzException.class)
  public synchronized String recharge_addUserParkCoupon(long ae_id, long ui_id, String ui_nd,
      String car_code, int type, long pi_id,
      String area_code, String pi_name, long ai_id) throws QzException {
    try {
      /**
       * 获取活动基本信息表数据
       */
      Activity_info activity_info = selectActivityByid2(ai_id);
      if (activity_info == null) {
        log.error("ActivityPB.addUserParkCoupon activity_info == null ai_id=" + ai_id);
        throw new QzException("活动更新失败");
      }
      /**
       * 获取优惠券基本信息表数据
       * 均可获取2元优惠券
       */
      Park_coupon park_coupon_2 = selectCouponByMoney(200);
      if (park_coupon_2 == null) {
        log.error("ActivityPB.addUserParkCoupon park_coupon_2 == null ai_id=" + ai_id);
        throw new QzException("活动更新失败");
      }

      /**
       * 获取优惠券基本信息表数据
       * 均可获取5元优惠券
       */
      Park_coupon park_coupon_5 = selectCouponByMoney(500);
      if (park_coupon_5 == null) {
        log.error("ActivityPB.addUserParkCoupon park_coupon_5 == null ai_id=" + ai_id);
        throw new QzException("活动更新失败");
      }
      //判断送券金额是否还能用  优惠券面值多样化，最高为5元，最低为2元，一共为10张。(5*4+2*5=30  5*2+2*10=30)
      int money = 3000;//30元
      if (activity_info.getMoney() - money < 0) {
        //活动立即结束
        activity_info.setState(1);
        activity_info.setUtime(new Date());
        int count = activity_infoDao.updateByKey(activity_info);
        if (count < 1) {
          log.error("活动立即结束 更新失败");
          throw new QzException("活动更新失败");
        }
      }
      int num_2 = 0;//两元的个数
      int num_5 = 0;//五元的个数
      int act_type = 1;//事件行为（1：充值 2：支付）
      int ai_send_unit = 1;//send_unit:赠送单位(0:吾泊平台 1：建行龙支付)
//      int random = RandomUtils.nextInt(0, 1);
      int random = 0;
      if (random == 0) {
        //5*4+2*5=30
        num_2 = 5;
        num_5 = 4;
      } else {
        //5*2+2*10=30
        num_2 = 10;
        num_5 = 2;
      }
      /**
       * 赠送优惠券2张2元
       */

      for (int i = 0; i < num_2; i++) {
        User_park_coupon user_park_coupon = doGiveCoupon(ae_id, ui_id, ui_nd, car_code, type, pi_id,
            area_code, pi_name
            , activity_info, park_coupon_2, act_type, 1, ai_send_unit, 0);
        if (user_park_coupon != null) {
          //活动经费进行减少
          if (activity_info.getMoney() - park_coupon_2.getMoney() >= 0) {
            activity_info.setMoney(activity_info.getMoney() - park_coupon_2.getMoney());
            activity_info.setPeople_num(activity_info.getPeople_num() + 1);
            int count = activity_infoDao.updateByKey(activity_info);
            if (count < 1) {
              log.error("活动经费进行减少失败");
              throw new QzException("活动更新失败");
            }
          }
        }
      }
      //龙之付送券1张5元
      for (int i = 0; i < num_5; i++) {
        User_park_coupon user_park_coupon = doGiveCoupon(ae_id, ui_id, ui_nd, car_code, type, pi_id,
            area_code, pi_name
            , activity_info, park_coupon_5, act_type, 1, ai_send_unit, 0);
        if (user_park_coupon != null) {
          //活动经费进行减少
          if (activity_info.getMoney() - park_coupon_5.getMoney() >= 0) {
            activity_info.setMoney(activity_info.getMoney() - park_coupon_5.getMoney());
            activity_info.setPeople_num(activity_info.getPeople_num() + 1);
            int count = activity_infoDao.updateByKey(activity_info);
            if (count < 1) {
              log.error("活动经费进行减少失败");
              throw new QzException("活动更新失败");
            }
          }
        }
      }
      /**
       * 更新事件处理状态和记录活动经费剩余的快照
       */
      if (!upActivity_event(ae_id, (int) activity_info.getMoney())) {
        throw new QzException("活动更新失败");
      }
      return getPushMessage2(num_2, park_coupon_2.getMoney(), num_5, park_coupon_5.getMoney());
    } catch (Exception e) {
      // TODO Auto-generated catch block
      log.error("处理添加优惠券失败 recharge_addUserParkCoupon is error", e);
      throw new QzException("活动更新失败");
    }
  }

  /*******************下面是分解方法****************************************************/
  /**
   * 验证活动是否有效
   *
   * @return true 有效: false：无效
   */
  public boolean isActivityEffect(Activity_info activity_info) {
    if (activity_info == null
        || activity_info.getState() == 1
        || activity_info.getEndtime().getTime() < System.currentTimeMillis()
        || activity_info.getMoney() < 1) {
      return false;
    }
    return true;
  }

  /**
   * 获取赠送代金券的活动基本信息--通过活动ID查询
   */
  public Activity_info selectActivityByid(long ai_id) {
    try {
      return activity_infoDao.selectByKey(ai_id);
    } catch (Exception e) {
      // TODO Auto-generated catch block
      log.error("ActivityPB.selectActivityByid is error", e);
    }
    return null;
  }

  /**
   * 获取活动基本信息表数据
   */
  public Activity_info selectActivityByid2(long ai_id) {
    try {
      String sql = "SELECT * FROM activity_info WHERE ai_id=? LIMIT 1";
      Activity_info activity_info = getMySelfService()
          .queryUniqueT(sql, Activity_info.class, ai_id);
      if (activity_info == null) {
        log.error("ActivityPB.selectActivityByid2  == null ai_id=" + ai_id);
      }
      return activity_info;
    } catch (Exception e) {
      // TODO Auto-generated catch block
      log.error("ActivityPB.selectActivityByid2 is error", e);
    }
    return null;
  }


  /**
   * 获取代金券基本信息
   */
  public Park_coupon selectCouponByMoney(long money) {
    try {
      /**
       * 获取优惠券基本信息表数据
       * 均可获取2元优惠券
       */
      String sql = "SELECT * FROM park_coupon WHERE money=? AND pc_type=0 LIMIT 1";
      return getMySelfService().queryUniqueT(sql, Park_coupon.class, money);
    } catch (Exception e) {
      // TODO Auto-generated catch block
      log.error("ActivityPB.selectCouponByMoney is error", e);
    }
    return null;
  }

  /**
   * 获取用户的车牌是否已经赠送过首次注册活动代金券
   */
  public Random_coupon_log SelectRandom_coupon_logByCarCode(Long ui_id, String ui_nd,
      String car_code, Integer type, Long pi_id, String area_code, Long ai_id) {
    try {
      if (pi_id == null && area_code == null) {
        String sql = "SELECT * FROM  random_coupon_log WHERE ui_id=? AND ui_nd=? AND  car_code=? AND type=?  AND ai_id=?";
        return getMySelfService()
            .queryUniqueT(sql, Random_coupon_log.class, ui_id, ui_nd, car_code, type, ai_id);
      } else {
        String sql = "SELECT * FROM  random_coupon_log WHERE ui_id=? AND ui_nd=? AND  car_code=? AND type=? AND pi_id=? AND area_code=? AND ai_id=?";
        return getMySelfService()
            .queryUniqueT(sql, Random_coupon_log.class, ui_id, ui_nd, car_code, type, pi_id,
                area_code, ai_id);
      }
    } catch (Exception e) {
      // TODO Auto-generated catch block
      log.error("ActivityPB.doGiveCoupon is error", e);
    }
    return null;
  }

  /**
   * 记录送券或者随机立免记录
   */
  public Random_coupon_log insertRandom_coupon_log(long ae_id, long ai_id, long pi_id,
      String area_code, String pi_name, String car_code, int money
      , int order_type, String orderid, String partner, int type,
      long ui_id, String ui_nd, long upc_id, int act_type, int send_unit, int pay_source) {
    try {
      Date date = new Date();
      Random_coupon_log random_coupon_log = new Random_coupon_log();
      random_coupon_log.setAe_id(ae_id);
      random_coupon_log.setAi_id(ai_id);
      random_coupon_log.setArea_code(area_code);
      random_coupon_log.setCar_code(car_code);
      random_coupon_log.setCtime(date);
      random_coupon_log.setMoney(money);
      random_coupon_log.setNd(returnUUID());
      random_coupon_log.setOrder_type(order_type);
      random_coupon_log.setOrderid(orderid);
      random_coupon_log.setPartner(partner);
      random_coupon_log.setPi_id(pi_id);
      random_coupon_log.setPi_name(pi_name);
      random_coupon_log.setType(type);
      random_coupon_log.setUi_id(ui_id);
      random_coupon_log.setUi_nd(ui_nd);
      random_coupon_log.setUpc_id(upc_id);

      random_coupon_log.setAct_type(act_type);
      random_coupon_log.setSend_unit(send_unit);
      random_coupon_log.setPay_source(pay_source);
      int id = random_coupon_logDao.insert(random_coupon_log);
      if (id > 0) {
        return random_coupon_log;
      }
    } catch (Exception e) {
      // TODO Auto-generated catch block
      log.error("insertRandom_coupon_log is error", e);
    }

    return null;
  }
}
