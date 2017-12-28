package com.park.task;

import com.alibaba.fastjson.JSON;
import com.park.bean.Carcode_park_rent;
import com.park.bean.Rent_defer;
import com.park.bean.User_info;
import com.park.constants.Constants;
import com.park.dao.User_infoDao;
import com.park.jpush.bean.JPushMessageBean;
import com.park.service.MySelfService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * 用户全天租赁到期通知，通知方式有短信通知、app极光推送。
 * 通知时间：提前 5 天和最后 1 天即将到期时进行提示，早上 10 点系统自动通过消息中心进 信息提醒
 * 剩余有效期天数不包括当天。
 * Created by zzy on 2017/7/28.
 */
@Component
public class RentExpireNotifyTask {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Resource(name = "MySelfService")
    private MySelfService mySelfService;

    @Autowired
    private AsyncSMSTask asyncSMSTask;

    @Autowired
    private AsyncJpushTask asyncJpushTask;

    @Autowired
    private User_infoDao userInfoDao;

    /**
     * 每天早上十点
     */
    @Scheduled(cron = "0 0 10 * * ?")
    //@Scheduled(cron = "0 0/2 * * * ?")
    //@Scheduled(cron = "0 0 17 * * ?")
    public void excute() {
        log.info("用户全天租赁到期通知定时任务开始");
        long begin = System.currentTimeMillis();
        String msg_5 = "尊敬的用户，您的车牌号{}租赁服务五天后即将到期，请您及时到物管办理或通过吾泊 APP 进行在线续租业务，" +
                "如过期未办理，您将不能享受为您提供的租赁服务，感谢您的支持！【吾泊停车】";
        String msg_1 = "尊敬的用户，您的车牌号{}租赁服务一天后即将到期，请您及时到物管办理或通过吾泊 APP 进行在线续租业务，" +
                "如过期未办理，您将不能享受为您提供的租赁服务，感谢您的支持！【吾泊停车】";

        try {
            String sql5 = "SELECT t.* FROM carcode_park_rent t " +
                    "WHERE " +
                    "t.is_expire = 0 " +
                    "AND t.is_del = 0 " +
                    "AND t.rent_type = 3 " +
                    "AND  TO_DAYS(t.endtime) - TO_DAYS(NOW()) = 5";

            //五天
            List<Carcode_park_rent> list5 = mySelfService.queryListT(sql5,Carcode_park_rent.class);
            log.info("五天到期提醒需要通知的消息共[{}]条",list5.size());
            int i = 0;
            for(Carcode_park_rent parkRent : list5){
                try{
                    msg_5 = msg_5.replace("{}", parkRent.getCar_code());
                    jpushMessage(msg_5,parkRent);
                    User_info userInfo = userInfoDao.selectByKey(parkRent.getUi_id());
                    if(userInfo != null){
                        asyncSMSTask.sendMessage(userInfo.getUi_tel(),msg_5);
                    }
                    log.info("五天到期提醒第"+(++i)+"条通知成功，车牌号 {}",parkRent.getCar_code());
                }catch (Exception e){
                    log.error("异常",e);
                }

            }

            String sql1 = "SELECT t.* FROM carcode_park_rent t " +
                    "WHERE " +
                    "t.is_expire = 0 " +
                    "AND t.is_del = 0 " +
                    "AND t.rent_type = 3 " +
                    "AND  TO_DAYS(t.endtime) - TO_DAYS(NOW()) = 1";

            //一天
            List<Carcode_park_rent> list1 = mySelfService.queryListT(sql1,Carcode_park_rent.class);
            log.info("一天到期提醒需要通知的消息共[{}]条",list1.size());
            int j = 0;
            for(Carcode_park_rent parkRent : list1){
                try{
                    msg_1 = msg_1.replace("{}", parkRent.getCar_code());
                    jpushMessage(msg_1,parkRent);
                    User_info userInfo = userInfoDao.selectByKey(parkRent.getUi_id());
                    if(userInfo != null){
                        asyncSMSTask.sendMessage(userInfo.getUi_tel(),msg_1);
                    }
                    log.info("一天到期提醒第"+(++j)+" 条通知成功，车牌号 {}",parkRent.getCar_code());
                }catch (Exception e){
                    log.error("通知异常",e);
                }
            }
            log.info("用户全天租赁到期通知定时任务结束，耗时"+(System.currentTimeMillis() - begin)+" ms");
        } catch (Exception e) {
            log.error("用户全天租赁到期通知处理失败",e);
        }
    }

    /**
     * 推送消息给APP用户
     *
     * @param parkRent
     */
    public void jpushMessage(String message, Carcode_park_rent parkRent) {
        String title = "系统消息";
        JPushMessageBean jPushMessageBean = new JPushMessageBean();
        jPushMessageBean.setMessage(message);
        jPushMessageBean.setImgurl(Constants.JPUSH_LOGO);
        jPushMessageBean.setTitle(title);
        jPushMessageBean.setDate(new Date());
        jPushMessageBean.setMessageJson((JSON) JSON.toJSON(parkRent));
        asyncJpushTask.doAppJpush(jPushMessageBean, parkRent.getUi_nd());
    }
}
