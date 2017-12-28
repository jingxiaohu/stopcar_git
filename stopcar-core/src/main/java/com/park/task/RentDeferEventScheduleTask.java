package com.park.task;

import com.park.bean.Event_schedule;
import com.park.bean.Rent_defer;
import com.park.mvc.service.EventScheduleBiz;
import com.park.mvc.service.RentDeferCoreBiz;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

/**
 *
 * 1.该任务只处理第三方支付回调后，根据支付结果更新rent_defer 的payState（支付状态）和 ，并进行mq推送，同时修改mq推送状态。
 * 2.第一步执行成功后，修改事件表event_schedule表的执行状态为已经执行成功。
 * 以下内容需要新增。
 * 3.需要新增一个接口给道闸修改rent_defer表的defer_state，确认续约成功，当确认续约成功之后，将续约成功的信息使用极光推送给用户app。
 * 4.新增一个定时任务处理rent_defer表中的长时间（从创建开始算多长时间后）处于续约中并且支付成功的订单，具体的时间需要确认，将支付的金额返回
 * 到用户的钱包中。
 *
 * Created by zzy on 2017/7/4.
 */
@Component
public class RentDeferEventScheduleTask {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private EventScheduleBiz eventScheduleBiz;

    @Autowired
    private RentDeferCoreBiz rentDeferCoreBiz;

    /**
     * 租赁订单续约事件调度任务,
     */
    //@Scheduled(cron = "0 0/1 * * * ?")
    public void excuteEventSchedule(){
        try{
            log.info("租赁订单续约事件调度任务");
            List<Event_schedule> list = eventScheduleBiz.queryEventSchedules();
            for(Event_schedule schedule : list){
                rentDeferCoreBiz.updateRentDeferInfo(schedule.getUp_orderid());

                schedule.setNote("更新执行状态");
                schedule.setState(1);//
                schedule.setUtime(new Date());
                eventScheduleBiz.updateInfo(schedule);
                //eventScheduleBiz.updateState(schedule.getEs_id(),1);   //修改执行状态
            }
        }catch (Exception e){
            log.error("租赁订单续约事件调度任务处理失败",e);
        }
    }

    /**
     * 处理租赁续约订单长时间处于正在续约中的超时订单
     * 更新租赁信息是否到期状态
     * 超时时间定为30分钟
     */
    @Scheduled(cron = "0 0/3 * * * ?")
    public void excuteRentDeferTimeOut(){

        try{
            List<Rent_defer> list = rentDeferCoreBiz.queryTimeOutRentDeferOrders();
            log.info("处理租赁续约订单长时间处于正在续约中的超时订单任务开始,共有[{}]订单",list.size());
            for(Rent_defer rentDefer : list){
                rentDefer.setNote("");
                rentDeferCoreBiz.excuteRentDeferOrderRefund(rentDefer);
                log.info("订单 {} 处理完毕！",rentDefer.getRent_order_id());
            }

            //更新租赁信息是否已经到期状态
            rentDeferCoreBiz.updateExpireState();
            log.info("已更新租赁信息是否到期状态！");

        }catch (Exception e){
            log.error("处理失败",e);
        }
    }
}
