package com.park.init;

import com.park.constants.Constants;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

public class CharacterFilter implements Filter {

  Logger log = LoggerFactory.getLogger(CharacterFilter.class);
  public static FilterConfig config = null;

  public void destroy() {
  }

  /**
   * 内容过滤
   */
  public void doFilter(ServletRequest request, ServletResponse response,
      FilterChain chain) throws IOException, ServletException {
    request.setCharacterEncoding(Constants.SYSTEM_CHARACTER);
    response.setCharacterEncoding(Constants.SYSTEM_CHARACTER);
    chain.doFilter(request, response);
  }

  /**
   * 初始化参数
   */
  public void init(FilterConfig config) throws ServletException {
    CharacterFilter.config = config;
    initSpring(config);
    //初始化ACP的配置
    //SDKConfig.getConfig().loadPropertiesFromSrc();// 从classpath加载acp_sdk.properties文件
    //初始化基础访问域名
//		initDomain(config);

  }

  public void initSpring(FilterConfig config) {
    if (!StringUtils.hasText(Constants.SYSTEM_ROOT_PATH)) {
      Constants.SYSTEM_ROOT_PATH = config.getServletContext().getRealPath("/");
    }
  }


}
