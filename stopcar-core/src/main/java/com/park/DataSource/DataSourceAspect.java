package com.park.DataSource;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.aspectj.MethodInvocationProceedingJoinPoint;
import org.springframework.aop.framework.ReflectiveMethodInvocation;
import org.springframework.stereotype.Component;

/**
 * 定义数据源的AOP切面，通过该Service的方法名判断是应该走读库还是写库
 *
 * @author zhijun
 */
@Component
@Aspect
public class DataSourceAspect {

  /*Logger log = LoggerFactory.getLogger(DataSourceAspect.class);

  @Pointcut("@annotation(com.park.DataSource.TargetDataSource)")
  public void pointCut() {
  }

  @Before("pointCut()")
  public void doBefore(JoinPoint joinPoint) {
    try {
      if (joinPoint == null) {
        return;
      }
      MethodInvocationProceedingJoinPoint methodPoint = (MethodInvocationProceedingJoinPoint) joinPoint;

      Field proxy = methodPoint.getClass().getDeclaredField("methodInvocation");

      proxy.setAccessible(true);

      ReflectiveMethodInvocation j = (ReflectiveMethodInvocation) proxy.get(methodPoint);

      Method method = j.getMethod();
      if (method.isAnnotationPresent(TargetDataSource.class)) {
        TargetDataSource targetDataSource = method.getAnnotation(TargetDataSource.class);
        if (targetDataSource != null) {
          if (isSlave(targetDataSource.value())) {
            // 标记为读库
            DynamicDataSourceHolder.markSlave();
          } else {
            // 标记为写库
            DynamicDataSourceHolder.markMaster();
          }
        }
      }
    } catch (Exception e) {
      // TODO Auto-generated catch block
      log.error("doBefore(JoinPoint joinPoint) is error", e);
    }

  }

  @After("pointCut()")
  public void doAfter(JoinPoint joinPoint) {
    try {
      DynamicDataSourceHolder.clearDataSourceType();
    } catch (Exception e) {
      // TODO Auto-generated catch block
      log.error("doAfter(JoinPoint joinPoint) is error", e);
    }
  }
    
   *//* @AfterReturning(pointcut="pointCut()",returning="returnVal")
    public void afterReturn(JoinPoint joinPoint,Object returnVal){
        System.out.println("AOP AfterReturning Advice:" + returnVal);
    }
    
    @AfterThrowing(pointcut="pointCut()",throwing="error")
    public void afterThrowing(JoinPoint joinPoint,Throwable error){
        System.out.println("AOP AfterThrowing Advice..." + error);
        System.out.println("AfterThrowing...");
    }*//*
    
   *//* @Around("pointCut()")
    public void around(ProceedingJoinPoint pjp){
        System.out.println("AOP Aronud before...");
        try {
            pjp.proceed();
        } catch (Throwable e) {
            e.printStackTrace();
        }
        System.out.println("AOP Aronud after...");
    }*//*

  *//**
   * 判断是否为读库
   *//*
  private Boolean isSlave(String slavename) {
    // 方法名以query、find、get开头的方法名走从库
    return StringUtils.startsWithAny(slavename, "slave");
  }
*/
}  

