package com.park.constants;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * app 属性
 *
 * @author Peter Wu
 */
@Component
public class AppProperties {


  private static String baseUrl;
  private static String baseDir;

  @Value("${app.baseUrl}")
  public void setDomain(String baseUrl) {
    AppProperties.baseUrl = baseUrl;
  }

  @Value("${app.baseDir}")
  public void setBaseDir(String baseDir) {
    AppProperties.baseDir = baseDir;
  }

  /**
   * @return 访问根地址
   */
  public static String getBaseUrl() {
    return baseUrl;
  }

  /**
   * @return 文件存放的根地址
   */
  public static String getBaseDir() {
    return baseDir;
  }
}
