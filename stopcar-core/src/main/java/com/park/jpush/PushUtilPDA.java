package com.park.jpush;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

/**
 * 推送PDA等通知
 * @author jingxiaohu
 *
 */
public class PushUtilPDA {
	private static Logger log = LoggerFactory.getLogger(PushUtilPDA.class);
	private static final String appKey ="f12410ca6337d7d2e64e12f5";//吾泊
	private static final String masterSecret = "1f39fe681d1426a0bc57ee7d";//wubo
	
//	@Test
	public void testMessageSent(){
		String message = "你好吾泊";
		String alert = "alert";
		SendPush(message, alert,"869612021322674");
	}
	
	
	public static  void SendPush(String message,String alert,String pda_mac) {

        
       
        try {
    		ClientConfig clientConfig = ClientConfig.getInstance();
            JPushClient jpushClient = new JPushClient(masterSecret, appKey, null, clientConfig);
        	PushPayload payload = buildPushObject_android_and_ios(message,alert,pda_mac);
            PushResult result = jpushClient.sendPush(payload);
            log.info("Got result - " + result);
            
        } catch (APIConnectionException e) {
            log.error("Connection error. Should retry later. ", e);
            
        } catch (APIRequestException e) {
        	e.printStackTrace();
            log.error("Error response from JPush server. Should review and fix it. ", e);
            log.info("HTTP Status: " + e.getStatus());
            log.info("Error Code: " + e.getErrorCode());
            log.info("Error Message: " + e.getErrorMessage());
            log.info("Msg ID: " + e.getMsgId());
        }catch(Exception e){
        	log.error("未知错误JPUSH", e);
        }
	}
	
	
    public static  PushPayload buildPushObject_android_and_ios(String MSG_CONTENT,String ALERT,String pda_mac) {
        return PushPayload.newBuilder()
                .setPlatform(Platform.all())
                .setAudience(Audience.alias(pda_mac))
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
     * @param MSG_CONTENT
     * @return
     */
    public static  PushPayload buildPushMessage(String MSG_CONTENT) {
        return PushPayload.newBuilder()
                .setPlatform(Platform.all())
                .setAudience(Audience.all())
                 .setMessage(Message.content(MSG_CONTENT))
                 .setOptions(Options.newBuilder().setApnsProduction(true).build())
                 .build();
    }
    /**
     * 推送通知
     * @return
     */
    public static  PushPayload buildPushAlert(String ALERT) {
        return PushPayload.newBuilder()
                .setPlatform(Platform.all())
                .setAudience(Audience.all())
                              /*   .setNotification(Notification.newBuilder()
                        .addPlatformNotification(IosNotification.newBuilder()
                                .setAlert(ALERT)
                                .setBadge(5)
                                .setSound("happy")
                                .addExtra("from", "JPush")
                                .build())
                        .build())*/
                 .setMessage(null)       
                 .setOptions(Options.newBuilder().setApnsProduction(true).build())
                 .build();
    }
	
}
