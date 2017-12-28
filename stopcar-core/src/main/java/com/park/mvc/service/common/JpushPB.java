package com.park.mvc.service.common;

import com.alibaba.fastjson.JSON;
import com.park.bean.Pay_park;
import com.park.constants.Constants;
import com.park.jpush.bean.JPushMessageBean;
import com.park.mvc.service.BaseBiz;
import java.util.Date;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * 普通订单公用方法
 *
 * @author jingxiaohu
 */
@Service
public class JpushPB extends BaseBiz {


  /**
   * 推送系统消息
   */
  public void pushSystem(String uuid, String message, String title) {
    try {
      if (uuid == null) {
        return;
      }
      /**
       * 这里进行推送
       */
      //订单预约状态变更 进行JPUSH推送
      JPushMessageBean jPushMessageBean = new JPushMessageBean();
      jPushMessageBean.setMessage(message);
      jPushMessageBean.setImgurl(Constants.JPUSH_LOGO);
      jPushMessageBean.setTitle(title);
      jPushMessageBean.setDate(new Date());
      jPushMessageBean.setType(0);
      asyncJpushTask.doAppJpush(jPushMessageBean, uuid);
    } catch (Exception e) {
      // TODO Auto-generated catch block
      log.error("推送系统消息失败", e);
    }
  }

  /**
   * 推送PDA 用户支付钱包不足
   */
  public void pushPDAOrderNoMoney(String mac, String message, String title, Pay_park pay_park) {
    try {
      if (StringUtils.hasText(mac) && StringUtils.hasText(message)) {
        /*
         * 这里进行推送
         */
        //订单预约状态变更 进行JPUSH推送
        JPushMessageBean jPushMessageBean = new JPushMessageBean();
        jPushMessageBean.setMessage(message);
        jPushMessageBean.setImgurl(Constants.JPUSH_LOGO);
        jPushMessageBean.setTitle(title);
        jPushMessageBean.setDate(new Date());
        jPushMessageBean.setType(6);
        if (pay_park != null) {
          jPushMessageBean.setMessageJson((JSON) JSON.toJSON(pay_park));
        }
        asyncJpushTask.doPdaJpushPDA(jPushMessageBean, mac);
      }
    } catch (Exception e) {
      log.error("处理推送PDA 用户支付钱包不足出错", e);
    }
  }
}
