package com.park.mvc.service;

import com.park.bean.ReturnDataNew;
import com.park.bean.User_pay;
import com.park.mvc.service.common.ActivityPB;
import com.park.mvc.service.common.ParkCouponPB;
import com.park.mvc.service.common.PayMonthParkPB;
import com.park.mvc.service.common.PayParkPB;
import com.park.transaction.PayTransaction;
import java.text.SimpleDateFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserPayBiz extends BaseBiz {

  @Autowired
  private PayTransaction payTransaction;
  @Autowired
  private PayParkPB payParkPB;
  @Autowired
  private PayMonthParkPB payMonthParkPB;
  @Autowired
  protected ActivityPB activityPB;
  @Autowired
  protected ParkCouponPB parkCouponPB;


  private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");

  /**
   * 建设银行龙支付通知   验证成功  更新该订单的支付状态 并把对应的金额添加给用户
   */
  public void notify_lzf(ReturnDataNew returnData, String orderid, String type, long money) {
    try {
      // type 是支付 还是 充值  1：充值  2：普通订单支付  3：租赁订单支付
      //首先检查该条订单是否存在
      User_pay user_pay = payParkPB.selectOneUserPay(orderid);
      if (user_pay == null) {
        //订单不存在
        returnData.setReturnData(errorcode_param, "订单不存在", "");
        return;
      }
      //避免第三方多次回调
      if (user_pay.getState() == 1) {
        returnData.setReturnData(errorcode_success, "通知更新成功", user_pay);
        return;
      }
      //验证是否金额一致 如果不一致有可能是被抓包  恶意刷我们的钱包
      if (user_pay.getMoney() != money) {
        returnData.setReturnData(errorcode_param, "金额不匹配", "");
        return;
      }

      try {
        /**
         * 为了防止龙支付 回调多次引起数据重复 这里再次进行检查是否已经处理了
         */
        //首先检查该条订单是否存在
        user_pay = payParkPB.selectOneUserPay(orderid);
        if (user_pay == null) {
          //订单不存在
          returnData.setReturnData(errorcode_param, "订单不存在", "");
          return;
        }
        //避免第三方多次回调
        if (user_pay.getState() == 1) {
          returnData.setReturnData(errorcode_success, "通知更新成功", user_pay);
          return;
        }
        //首先检查该条订单是否存在
        user_pay = payParkPB.selectOneUserPay(orderid);
        if (user_pay == null) {
          //订单不存在
          returnData.setReturnData(errorcode_param, "订单不存在", "");
          return;
        }
        //避免第三方多次回调
        if (user_pay.getState() == 1) {
          returnData.setReturnData(errorcode_success, "通知更新成功", user_pay);
          return;
        }

        //建行龙支付用户充值通知：修改用户钱包金额、更改订单状态
        user_pay = payTransaction.NotifyUserPay(user_pay);
      } catch (Exception e) {
        // TODO Auto-generated catch block
        log.error("通知更新失败 payTransaction.NotifyUserPay(user_pay)", e);
      }
      returnData.setReturnData("0", "通知更新成功", user_pay);
      return;
    } catch (Exception e) {
      log.error("UserPayBiz.lzf_charge is error", e);
      returnData.setReturnData(errorcode_data, "通知更新失败", "");
      return;
    }
  }


  /**
   * 支付宝通知   验证成功  更新该订单的支付状态 并把对应的金额添加给用户
   */
  public void notify_zfb(ReturnDataNew returnData, String orderid, String trade_no, String type,
      long money) {
    // TODO Auto-generated method stub
    try {
      //是支付 还是 充值  1：充值  2：普通订单支付  3：租赁订单支付
      //首先检查该条订单是否存在
      User_pay user_pay = payParkPB.selectOneUserPay(orderid);
      if (user_pay == null) {
        //订单不存在
        returnData.setReturnData(errorcode_param, "订单不存在", "");
        return;
      }
      //避免第三方多次回调
      if (user_pay.getState() == 1) {
        returnData.setReturnData(errorcode_success, "通知更新成功", user_pay);
        return;
      }

      //支付宝用户充值通知：修改用户钱包金额、更改订单状态
      user_pay.setTransaction_id(trade_no);

      if (user_pay.getAct_type() == 1) {
        //充值
        //验证是否金额一致 如果不一致有可能是被抓包  恶意刷我们的钱包
        if (user_pay.getMoney() != money) {
          returnData.setReturnData(errorcode_param, "金额不匹配", "");
          return;
        }
      }
      try {
        user_pay = payTransaction.NotifyUserPay(user_pay);
      } catch (Exception e) {
        // TODO Auto-generated catch block
        log.error("通知更新失败 payTransaction.NotifyUserPay(user_pay)", e);
      }

      returnData.setReturnData(errorcode_success, "通知更新成功", user_pay);
      return;

    } catch (Exception e) {
      log.error("UserPayBiz.notify_zfb is error", e);
      returnData.setReturnData(errorcode_data, "通知更新失败", "");
      return;
    }
  }


  /**
   * 微信通知监听接口
   */
  public void notify_weixin(ReturnDataNew returnData, String orderid,
      String trade_no, String type, long money) {
    // TODO Auto-generated method stub
    try {
      //是支付 还是 充值  1：充值  2：普通订单支付  3：租赁订单支付
      //首先检查该条订单是否存在
      User_pay user_pay = payParkPB.selectOneUserPay(orderid);
      if (user_pay == null) {
        //订单不存在
        returnData.setReturnData(errorcode_param, "订单不存在", "");
        return;
      }
      //避免第三方多次回调
      if (user_pay.getState() == 1) {
        returnData.setReturnData(errorcode_success, "通知更新成功", user_pay);
        return;
      }

      //支付宝用户充值通知：修改用户钱包金额、更改订单状态
      user_pay.setTransaction_id(trade_no);

      if (user_pay.getAct_type() == 1) {
        //充值
        //验证是否金额一致 如果不一致有可能是被抓包  恶意刷我们的钱包
        if (user_pay.getMoney() != money) {
          returnData.setReturnData(errorcode_param, "金额不匹配", "");
          return;
        }
      }
      try {
        user_pay = payTransaction.NotifyUserPay(user_pay);
      } catch (Exception e) {
        // TODO Auto-generated catch block
        log.error("通知更新失败 payTransaction.NotifyUserPay(user_pay)", e);
      }

      returnData.setReturnData(errorcode_success, "通知更新成功", user_pay);
      return;

    } catch (Exception e) {
      log.error("UserPayBiz.notify_weixin is error", e);
      returnData.setReturnData(errorcode_data, "通知更新失败", "");
      return;
    }
  }

}
