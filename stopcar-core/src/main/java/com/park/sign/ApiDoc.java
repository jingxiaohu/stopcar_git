package com.park.sign;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 接口文档生成工具类
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface ApiDoc {

  /**
   * @return 相关数据表名
   */
  String[] tableNames();

  /**
   * @return 必填参数
   */
  String[] requires();

  /**
   * @return 参与签名的字段，有{@code ApiSign}注解的接口可为空
   */
  String[] signs() default {};
}
