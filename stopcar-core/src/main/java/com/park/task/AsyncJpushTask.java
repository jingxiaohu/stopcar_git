package com.park.task;

import com.alibaba.fastjson.JSON;
import com.park.bean.Pay_park;
import com.park.bean.User_park_coupon;
import com.park.constants.Constants;
import com.park.jpush.PushUtil;
import com.park.jpush.PushUtilPDA;
import com.park.jpush.bean.JPushMessageBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;

@Service
public class AsyncJpushTask {

  static Logger log = LoggerFactory.getLogger(AsyncJpushTask.class);

  /**
   * 推送 使用代金券消息
   */
  @Async
  public void pushUseCouponMsg(String uuid, User_park_coupon user_park_coupon) {
    try {
      if (StringUtils.hasText(uuid)) {
        JPushMessageBean jPushMessageBean = new JPushMessageBean();
        jPushMessageBean
            .setMessage("您已使用了一张" + user_park_coupon.getMoney() / 100 + "元代金券，感谢您的支持！");
        jPushMessageBean.setImgurl(Constants.JPUSH_LOGO);
        jPushMessageBean.setTitle("系统消息");
        jPushMessageBean.setDate(new Date());
        jPushMessageBean.setType(0);
        doAppJpush(jPushMessageBean, uuid);
      }
    } catch (Exception e) {
      log.error(e.getMessage(), e);
    }
  }

  /**
   * 推送余额不足消息
   */
  @Async
  public void pushOrderNoMoney(String uuid, String message, String title, Pay_park pay_park) {
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
      jPushMessageBean.setType(6);
      if (pay_park != null) {
        jPushMessageBean.setMessageJson((JSON) JSON.toJSON(pay_park));
      }
      doAppJpush(jPushMessageBean, uuid);
    } catch (Exception e) {
      // TODO Auto-generated catch block
      log.error("处理订单状态变化推送pushOrderSate-----改进版本", e);
    }
  }

  /**
   * 进行PDA推送
   */
  @Async
  public void doAppJpush(JPushMessageBean jPushMessageBean, String uuid) {
    try {
      if (jPushMessageBean != null && StringUtils.hasText(uuid)) {
        PushUtil.SendPush(JSON.toJSONString(jPushMessageBean), jPushMessageBean.getMessage(), uuid);
      }

    } catch (Exception e) {
      // TODO Auto-generated catch block
      log.error("doPdaJpush(JPushMessageBean jPushMessageBean,String uuid) is error uuid=" + uuid,
          e);
    }

  }

  /**
   * 进行PDA推送
   */
  @Async
  public void doPdaJpushPDA(JPushMessageBean jPushMessageBean, String pda_mac) {
    try {
      if (jPushMessageBean != null && pda_mac != null) {
        PushUtilPDA.SendPush(jPushMessageBean.getMessageJson().toJSONString(),
            jPushMessageBean.getMessage(), pda_mac);
      }
    } catch (Exception e) {
      // TODO Auto-generated catch block
      log.error("点播执行doInsertIntoDataBase(List<By_app_categories> list,int pagesize) is error", e);
    }

  }
}
