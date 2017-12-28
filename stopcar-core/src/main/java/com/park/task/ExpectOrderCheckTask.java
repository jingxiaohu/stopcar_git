package com.park.task;

import com.park.mvc.service.common.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ExpectOrderCheckTask {

  private Logger log = LoggerFactory.getLogger(this.getClass());
  @Autowired
  protected PayParkPB payParkPB;
  @Autowired
  protected PayMonthParkPB payMonthParkPB;
  @Autowired
  protected ParkInfoPB parkInfoPB;
  @Autowired
  protected ActivityPB activityPB;
  //	@Autowired
//	AsyncTask asyncTask;

  /**
   * 调度处理预约订单超时的扣款
   */
  @Scheduled(cron = "0 0/3 * * * ?")
  public void doGo() {
    try {
      //INFO  @@@@@@@@@@@@@D:\tool\apache-tomcat-7.0.69\bin  file:/D:/tool/apache-tomcat-7.0.69/webapps/stopcar/WEB-INF/classes/(ExpectOrderCheckTask.java:26)
      log.info("@@@@@@@@@@@@@" + System.getProperty("user.dir") + "  " + this.getClass()
          .getResource("/"));
      log.info("------------ExpectOrderCheckTask----------------is start---");
      //处理预约订单超时的扣款
      payParkPB.upExpectOrderOutTime();
      //调度处理租赁订单 ---还处于租赁中--10分钟-租赁车超时
      payMonthParkPB.upRentOrderOutTime10();
      //处理停车场异常  is_fault 停车场故障 0:无故障 1：发生故障
      parkInfoPB.upFaultParkInfo();
    } catch (Throwable e) {
      log.error("ExpectOrderCheckTask.doGo is error", e);
    }

  }

  /**
   * 调度处理租赁订单超过了结束时间更新
   */
  @Scheduled(cron = "0 0/30 * * * ?")
  public void doRent() {
    try {
      //处理租赁超时的扣款
      log.info("------------RentOrderCheckTask----------------is start---");
      payMonthParkPB.upRentOrderOutTime();
    } catch (Throwable e) {
      log.error("ExpectOrderCheckTask.doRent is error", e);
    }

  }

  @Autowired
  ActivityPB2 activityPB2;


  /**
   * 调度处理活动事件
   */
  @Scheduled(cron = "0 0/1 * * * ?")
  public void doActivity() {
    try {
      //INFO  @@@@@@@@@@@@@D:\tool\apache-tomcat-7.0.69\bin  file:/D:/tool/apache-tomcat-7.0.69/webapps/stopcar/WEB-INF/classes/(ExpectOrderCheckTask.java:26)
      log.info("@@@@@@@@@@@@@" + System.getProperty("user.dir") + "  " + this.getClass()
          .getResource("/"));
      log.info("-------Async-----activityPB.doActivity()----------------is start---");
      activityPB2.doEvent();
    } catch (Throwable e) {
      log.error("ExpectOrderCheckTask.doActivity is error", e);
    }

  }

}
