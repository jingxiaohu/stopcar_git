package com.park.task;

import com.park.util.SMSUtil;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * Created by zzy on 2017/7/14.
 */
@Service
public class AsyncSMSTask {
    /**
     * 异步方式发送短信消息
     * @param phone
     * @param message
     */
    @Async
    public void sendMessage(String phone,String message){
        SMSUtil.sendMessage(phone,message);
    }

}
