package com.park.mvc.service;

import com.park.bean.Event_schedule;
import com.park.dao.Event_scheduleDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 事件调度记录服务类
 * Created by zzy on 2017/7/4.
 */
@Service("eventScheduleBiz")
public class EventScheduleBiz extends BaseBiz {

    @Autowired
    private Event_scheduleDao eventScheduleDao;


    /**
     * 记录事件
     * @param eventSchedule
     * @return
     */
    public int recordEventSchedule(Event_schedule eventSchedule){
        try{
            Date date = new Date();
            eventSchedule.setCtime(date);
            eventSchedule.setUtime(date);
            eventSchedule.setState(0);  //处理状态（0：未处理 1：处理成功 2：处理失败）
            eventSchedule.setNote("创建事件调度记录");
            return eventScheduleDao.insert(eventSchedule);
        }catch (Exception e){
            log.error("数据库异常",e);
        }
        return -1;
    }

    /**
     * 查询未处理的事件
     * @return
     */
    public List<Event_schedule> queryEventSchedules(){
        List<Event_schedule> list = new ArrayList<Event_schedule>();
        try{
            String sql = "select * from event_schedule t where t.state = 0";
            list = getMySelfService().queryListT(sql,Event_schedule.class,new Object[]{});
            return list;
        }catch (Exception e){
            log.error("数据库异常",e);
        }
        return list;
    }

    /**
     * 更新状态
     * @param es_id
     * @param state
     * @return
     */
    public int updateState(long es_id,int state){
        try{
            String sql = "update event_schedule set state = ?,utime = ? where es_id = ?";
            return getMySelfService().update(sql,new Object[]{state,new Date(),es_id});
        }catch (Exception e){
            log.error("数据库异常",e);
        }
        return -1;
    }

    /**
     * 更新事件信息
     * @param eventSchedule
     * @return
     */
    public int updateInfo(Event_schedule eventSchedule){
        try{
            return eventScheduleDao.updateByKey(eventSchedule);
        }catch (Exception e){
            log.error("数据库异常",e);
        }
        return -1;
    }
}
