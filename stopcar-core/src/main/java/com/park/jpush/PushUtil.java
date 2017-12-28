package com.park.jpush;

import cn.jiguang.common.ClientConfig;
import cn.jiguang.common.resp.APIConnectionException;
import cn.jiguang.common.resp.APIRequestException;
import cn.jpush.api.JPushClient;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Message;
import cn.jpush.api.push.model.Options;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.notification.IosNotification;
import cn.jpush.api.push.model.notification.Notification;
import com.alibaba.fastjson.JSON;
import com.park.constants.Constants;
import com.park.jpush.bean.JPushMessageBean;
import java.util.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 推送PDA等通知
 *
 * @author jingxiaohu
 */
public class PushUtil {

  private static Logger log = LoggerFactory.getLogger(PushUtil.class);
  private static final String appKey = "544d9db9494b2df82c88ca43";//吾泊
  private static final String masterSecret = "ca3dbeb4fdb2b5b2ed7802ed";//wubo

  private static JPushClient jpushClient = null;

  public static JPushClient getJpushClient() {
    if (jpushClient == null) {
      ClientConfig clientConfig = ClientConfig.getInstance();
      jpushClient = new JPushClient(masterSecret, appKey, null, clientConfig);
    }
    return jpushClient;
  }


  /**
   * 处理单例JPUSH
   */

//  @Test
  public void testMessageSent() {
    /**
     * 这里进行推送
     */
    JPushMessageBean jPushMessageBean = new JPushMessageBean();
    jPushMessageBean.setType(0);
    jPushMessageBean.setMessage("您充值" + 1 + "吾泊币已经到账，可以在电子钱包明细处查询。");
    jPushMessageBean.setImgurl(Constants.JPUSH_LOGO);
    jPushMessageBean.setTitle("充值到账");
    jPushMessageBean.setDate(new Date());
    String message = JSON.toJSONString(jPushMessageBean);
    String alert = "alert";
    SendPush(message, alert, "2017022015142561");
  }


  public static PushResult SendPush(String message, String alert, String... uuid) {
    try {
      PushPayload payload = buildPushObject_android_and_ios(message, alert, uuid);
      PushResult result = getJpushClient().sendPush(payload);
      log.info("Got result - " + result);
      return result;
    } catch (APIConnectionException e) {
      log.error("Connection error. Should retry later. ", e);

    } catch (APIRequestException e) {
      if (e.getErrorCode() != 1011) {
        log.error("Error response from JPush server. Should review and fix it. ", e);
      }
      log.info("HTTP Status: " + e.getStatus());
      log.info("Error Code: " + e.getErrorCode());
      log.info("Error Message: " + e.getErrorMessage());
      log.info("Msg ID: " + e.getMsgId());
    } catch (Exception e) {
      log.error("未知错误JPUSH", e);
    }
    return null;
  }

  @Deprecated
  public static PushPayload buildPushObject_android_and_ios(String MSG_CONTENT, String ALERT,
      String... uuid) {
    return PushPayload.newBuilder()
        .setPlatform(Platform.android_ios())
        .setAudience(Audience.alias(uuid))
        /*.setNotification(Notification.newBuilder()
            .addPlatformNotification(IosNotification.newBuilder()
                .setAlert(ALERT)
                .setBadge(5)
                .setSound("happy")
                .addExtra("from", "JPush")
                .build())
            .build())*/
        .setMessage(Message.content(MSG_CONTENT))
        .setOptions(Options.newBuilder()
            .setApnsProduction(true)
            .build())
        .build();
  }

  /**
   * 推送消息
   */
  public static PushPayload buildPushMessage(String MSG_CONTENT) {
    return PushPayload.newBuilder()
        .setPlatform(Platform.all())
        .setAudience(Audience.all())
        .setMessage(Message.content(MSG_CONTENT))
        .setOptions(Options.newBuilder().setApnsProduction(true).build())
        .build();
  }

  /**
   * 推送通知
   */
  public static PushPayload buildPushAlert(String ALERT) {
    return PushPayload.newBuilder()
        .setPlatform(Platform.all())
        .setAudience(Audience.all())
        .setNotification(Notification.newBuilder()
            .addPlatformNotification(IosNotification.newBuilder()
                .setAlert(ALERT)
                .setBadge(5)
                .setSound("happy")
                .addExtra("from", "JPush")
                .build())
            .build())
        .setMessage(null)
        .setOptions(Options.newBuilder().setApnsProduction(true).build())
        .build();
  }

}
