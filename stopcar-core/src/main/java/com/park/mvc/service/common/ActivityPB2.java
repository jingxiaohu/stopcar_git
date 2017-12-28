package com.park.mvc.service.common;

import com.park.bean.Activity_event;
import com.park.exception.QzException;
import com.park.mvc.service.BaseBiz;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Peter Wu
 */
@Service
public class ActivityPB2 extends BaseBiz {

  @Autowired
  private ActivityPB activityPB;
  private Lock lock = new ReentrantLock();

  /**
   * 调度器处理活动事件
   */
  public void doEvent() {
    lock.lock();
    try {
      String sql = "select * from activity_event where state=0";
      List<Activity_event> list = getMySelfService().queryListT(sql, Activity_event.class);
      if (list == null || list.size() == 0) {
        return;
      }
      for (Activity_event activity_event : list) {
        try {
          activityPB.doActivityEvent(activity_event);
        } catch (QzException e) {
          log.error(e.getMessage() + " ae_id=" + activity_event.getId());
        }
      }
    } catch (Exception e) {
      // TODO Auto-generated catch block
      log.error("调度器处理活动事件失败doEvent is error", e);
    } finally {
      lock.unlock();// 释放锁
    }
  }
}
