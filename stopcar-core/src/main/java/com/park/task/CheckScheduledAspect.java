package com.park.task;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

/**
 * CheckScheduled拦截器
 *
 * @author Peter Wu
 */
@Component
@Aspect
public class CheckScheduledAspect {

  private final Logger log = LoggerFactory.getLogger(CheckScheduledAspect.class);


  /**
   * CheckScheduled拦截器
   * 检查是否开启调度
   * D:\tool\apache-tomcat-7.0.69\bin
   *
   * @param jp jp
   */
  @Around(value = "@annotation(org.springframework.scheduling.annotation.Scheduled)")
  public void checkScheduled(ProceedingJoinPoint jp) throws Throwable {
    String tomcatPath = "";
    try {
      tomcatPath = System.getProperty("user.dir");
//      if (tomcatPath == null || !tomcatPath.contains("bin")) {
//        if (log.isDebugEnabled()) {
//          log.debug("不运行定时任务");
//        }
//        return;
//      }
      tomcatPath = tomcatPath.replace(File.separator + "bin", "") + File.separator
          + "is_scheduled.properties";
      File file = new File(tomcatPath);
      if (file != null && file.exists()) {
        InputStream in = new FileInputStream(file);
        if (in != null) {
          Properties prop = new Properties();
          prop.load(in);
          String is_scheduled = prop.getProperty("is_scheduled").trim();
          if ("true".equalsIgnoreCase(is_scheduled)) {
            if (log.isDebugEnabled()) {
              log.debug("---------运行定时任务---------");
            }
            jp.proceed();
            return;
          }
        }
      }
            /*Properties prop =  new  Properties();
            InputStream in = ClassLoader.getSystemResourceAsStream( tomcatPath+File.separator+"is_scheduled.properties" );
	            prop.load(in);
	            String is_scheduled = prop.getProperty( "is_scheduled" ).trim();
	            log.info("#####is_scheduled==="+is_scheduled);
	            if("true".equalsIgnoreCase(is_scheduled)){
	            	return true;
	            }*/
    } catch (Exception e) {
      log.error("checkScheduled is error tomcatPath=" + tomcatPath, e);
    }
    if (log.isDebugEnabled()) {
      log.debug("不运行定时任务");
    }
  }

  
  /**
 	*记录 每个接口的response
   */
//  @Around(value = "@annotation(com.park.myself.annotation.TargetResponse)")
  public void checkResponse(ProceedingJoinPoint jp) throws Throwable {
    try {
            jp.proceed();
    } catch (Exception e) {
      log.error("checkResponse is error", e);
    }
    if (log.isDebugEnabled()) {
      log.debug("记录 每个接口的response");
    }
  }

}
