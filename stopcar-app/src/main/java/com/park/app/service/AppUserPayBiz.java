package com.park.app.service;

import com.alibaba.fastjson.JSONObject;
import com.park.app.transaction.AppPayTransaction;
import com.park.bean.Pay_park;
import com.park.bean.ReturnDataNew;
import com.park.bean.User_info;
import com.park.bean.User_pay;
import com.park.mvc.service.BaseBiz;
import com.park.mvc.service.common.ActivityPB;
import com.park.mvc.service.common.ParkCouponPB;
import com.park.mvc.service.common.PayMonthParkPB;
import com.park.mvc.service.common.PayParkPB;
import com.park.util.RequestUtil;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AppUserPayBiz extends BaseBiz {

  @Autowired
  protected AppPayTransaction appPayTransaction;
  @Autowired
  private PayParkPB payParkPB;
  @Autowired
  private PayMonthParkPB payMonthParkPB;
  @Autowired
  protected ActivityPB activityPB;
  @Autowired
  protected ParkCouponPB parkCouponPB;

  /**
   * 建设银行龙支付WEB
   */
  public void lzf_charge(ReturnDataNew returnData, int pay_type,
      long ui_id, long money, int version, int system_type,
      String subject, String ip, String token, String pub, int type, String orderid,
      int escape_money, String escape_orderids, String notify_url) {
    // TODO Auto-generated method stub
    try {
      String callbackurl = "";//回调地址
      String uuid = "";
      String tel = "";
      Pay_park pay_park = null;
      if (system_type != 4) {
        //PDA系统除开
        User_info userinfo = user_infoDao.selectByKey(ui_id);
        if (userinfo == null) {
          //用户不存在
          returnData.setReturnData(errorcode_param, "用户不存在", "");
          return;
        }
        if (!userinfo.getUi_token().equalsIgnoreCase(token)) {
          //用户不合法
          returnData.setReturnData(errorcode_param, "用户未授权", "");
          return;
        }
        uuid = userinfo.getUuid();
        tel = userinfo.getUi_tel();
      } else {
        //如果是PDA 进行的当面付   system_type==4
        //获取订单 查看是否关联了对于的用户
        pay_park = payParkPB.selectOnePayPark(orderid);
        if (pay_park == null) {
          returnData.setReturnData(errorcode_data, "本地订单无法在线支付", "");
          return;
        }
        if (pay_park.pp_state == 1 || payParkPB.isCallPay(pay_park.getMy_order()) != null) {
          returnData.setReturnData(errorcode_data, "订单已支付", "");
          return;
        }
        if (pay_park != null && !RequestUtil.checkObjectBlank(pay_park.getUi_tel())) {
          ui_id = pay_park.getUi_id();
          uuid = pay_park.getUi_nd();
          tel = pay_park.getUi_tel();
        }
      }

      User_pay user_pay = null;
      //处理返回数据和业务逻辑
      if (pay_park != null) {
        //支付
        user_pay = appPayTransaction
            .MakeUserReCharge_LZF(ui_id, uuid,
                pay_type, money, version,
                system_type, subject, ip, callbackurl, type, orderid, tel, pay_park, escape_money,
                escape_orderids);
      } else {
        //充值
        user_pay = appPayTransaction
            .MakeUserReCharge(ui_id, uuid,
                pay_type, money, version,
                system_type, subject, ip, callbackurl, type, orderid, tel, escape_money,
                escape_orderids);
      }
      if (user_pay == null) {
        returnData.setReturnData(errorcode_data, "下单失败", "");
        return;
      }
      JSONObject obj = (JSONObject) JSONObject.toJSON(user_pay);
      if (obj != null) {
        obj.put("pub", pub);

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MINUTE, 5);//
        obj.put("timeout", dateFormat.format(calendar.getTime()));
      }
      obj.put("notify_url",notify_url);
      returnData.setReturnData("0", "下单成功", obj);
      return;
    } catch (Exception e) {
      log.error("UserPayBiz.lzf_charge is error", e);
      returnData.setReturnData(errorcode_systerm, "system is error", null);
      return;
    }
  }

  private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");


  /**
   * 支付宝充值下单
   */
  public User_pay zfb_charge(ReturnDataNew returnData, int pay_type,
      long ui_id, long money, int version, int system_type,
      String subject, String ip, String token, int type, String orderid,
      int escape_money, String escape_orderids) {
    // TODO Auto-generated method stub
    try {
      String callbackurl = "";//回调地址
      String uuid = "";
      String tel = "";
      if (system_type != 4) {
        //PDA系统除开
        User_info userinfo = user_infoDao.selectByKey(ui_id);
        if (userinfo == null) {
          //用户不存在
          returnData.setReturnData(errorcode_param, "用户不存在", "");
          return null;
        }
        if (!userinfo.getUi_token().equalsIgnoreCase(token)) {
          //用户不合法
          returnData.setReturnData(errorcode_param, "用户未授权", "");
          return null;
        }
        uuid = userinfo.getUuid();
        tel = userinfo.getUi_tel();
      } else {
        //如果是PDA 进行的当面付   system_type==4
        //获取订单 查看是否关联了对于的用户
        Pay_park pay_park = payParkPB.selectOnePayPark(orderid);
        if (pay_park != null && !RequestUtil.checkObjectBlank(pay_park.getUi_tel())) {
          ui_id = pay_park.getUi_id();
          uuid = pay_park.getUi_nd();
          tel = pay_park.getUi_tel();
          if (ui_id > 0) {
            if (pay_park.getUpc_id() > 0) {//取消代金券设置
              pay_park.setUpc_id(0);
              pay_park.setDiscount_money(0);
              pay_park.setDiscount_type(0);
              pay_park.setDiscount_name("");
              pay_parkDao.updateByKey(pay_park);
            }
            //默认选择抵扣最大的优惠券
//            User_park_coupon user_park_coupon = parkCouponPB.ReturnMaxMoneyCoupon(ui_id);
//            if (user_park_coupon != null) {
//              try {
//                money = parkCouponPB
//                    .doCouponOrder(pay_park, user_park_coupon, Long.valueOf(money).intValue());
//                pay_parkDao.updateByKey(pay_park);
//              } catch (Exception e) {
//                throw new QzException("代金券抵扣设置错误");
//              }
//            }
          }
        }

      }
      //处理返回数据和业务逻辑
      User_pay user_pay = appPayTransaction
          .MakeUserReCharge(ui_id, uuid,
              pay_type, money, version,
              system_type, subject, ip, callbackurl, type, orderid, tel, escape_money,
              escape_orderids);
      if (user_pay == null) {
        returnData.setReturnData(errorcode_data, "下单失败", "");
        return null;
      }
      returnData.setReturnData("0", "下单成功", user_pay);
      return user_pay;
    } catch (Exception e) {
      log.error("UserPayBiz.zfb_charge is error", e);
      returnData.setReturnData(errorcode_data, "下单失败", "");
      return null;
    }
  }


  /**
   * 微信 -- 用户充值
   */
  public User_pay weixin_charge(ReturnDataNew returnData, int pay_type,
      long ui_id, int money, int version, int system_type,
      String subject, String ip, String token, int type, String orderid,
      int escape_money, String escape_orderids) {
    // TODO Auto-generated method stub
    try {
      String callbackurl = "";//回调地址
      String uuid = "";
      String tel = "";
      if (system_type != 4) {
        //PDA系统除开
        User_info userinfo = user_infoDao.selectByKey(ui_id);
        if (userinfo == null) {
          //用户不存在
          returnData.setReturnData(errorcode_param, "用户不存在", "");
          return null;
        }
        if (!userinfo.getUi_token().equalsIgnoreCase(token)) {
          //用户不合法
          returnData.setReturnData(errorcode_param, "用户未授权", "");
          return null;
        }
        uuid = userinfo.getUuid();
        tel = userinfo.getUi_tel();
      } else {
        //如果是PDA 进行的当面付   system_type==4
        //获取订单 查看是否关联了对于的用户
        Pay_park pay_park = payParkPB.selectOnePayPark(orderid);
        if (pay_park != null && !RequestUtil.checkObjectBlank(pay_park.getUi_tel())) {
          ui_id = pay_park.getUi_id();
          uuid = pay_park.getUi_nd();
          tel = pay_park.getUi_tel();
          if (ui_id > 0) {
            if (pay_park.getUpc_id() > 0) {//取消代金券设置
              pay_park.setUpc_id(0);
              pay_park.setDiscount_money(0);
              pay_park.setDiscount_type(0);
              pay_park.setDiscount_name("");
              pay_parkDao.updateByKey(pay_park);
            }
//            //默认选择抵扣最大的优惠券
//            User_park_coupon user_park_coupon = parkCouponPB.ReturnMaxMoneyCoupon(ui_id);
//            if (user_park_coupon != null) {
//              try {
//                money = parkCouponPB
//                    .doCouponOrder(pay_park, user_park_coupon, Long.valueOf(money).intValue());
//                pay_parkDao.updateByKey(pay_park);
//              } catch (Exception e) {
//                throw new QzException("代金券抵扣设置错误");
//              }
//            }
          }
        }

      }
      //处理返回数据和业务逻辑
      User_pay user_pay = appPayTransaction
          .MakeUserReCharge(ui_id, uuid,
              pay_type, money, version,
              system_type, subject, ip, callbackurl, type, orderid, tel, escape_money,
              escape_orderids);
      if (user_pay == null) {
        returnData.setReturnData(errorcode_data, "下单失败", "");
        return null;
      }
      returnData.setReturnData("0", "下单成功", user_pay);
      return user_pay;
    } catch (Exception e) {
      log.error("UserPayBiz.weixin_charge is error", e);
      returnData.setReturnData(errorcode_data, "下单失败", "");
    }
    return null;
  }

}
