package com.park.task;

import com.park.service.MySelfService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 心跳任务
 *
 * @author Peter Wu
 */
@Component
public class ParkHeartbeatTask {

  private Logger log = LoggerFactory.getLogger(ParkHeartbeatTask.class);

  @Resource(name = "MySelfService")
  private MySelfService mySelfService;

  /**
   * 每天凌晨2点清除上一天以前标记过的心跳记录
   */
  @Scheduled(cron = "0 0 2 * * ?")
  public void clean() {
    String sql = "DELETE FROM park_heartbeat WHERE state = 1 AND TO_DAYS(ctime)<TO_DAYS(NOW())";
    try {
      mySelfService.execute(sql);
      log.info("清除过期心跳记录成功");
    } catch (Exception e) {
      log.error("清除过期心跳记录失败", e);
    }
  }
}
