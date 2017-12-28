package com.park.myself.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 在方法上使用，用于指定使用response处理
 */
@Documented  
@Retention(RetentionPolicy.RUNTIME)  
@Target(ElementType.METHOD)   
public @interface TargetResponse {
}
