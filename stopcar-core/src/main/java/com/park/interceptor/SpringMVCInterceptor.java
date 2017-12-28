package com.park.interceptor;

import com.alibaba.fastjson.JSON;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MarkerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

public class SpringMVCInterceptor extends LocaleChangeInterceptor {

  @Autowired
  private RequestBiz requestBiz;
  private Logger log = LoggerFactory.getLogger(SpringMVCInterceptor.class);
  //处理器执行之前调用的方法，可以用来对处理器进行预处理，如果返回布尔值假，则终止处理请求
  private long time = 0;
  public static String RESPONSE_DATA = "RESPONSE_DATA";
  private static final SimpleDateFormat dateFormat = new SimpleDateFormat(
      "yyyy-MM-dd HH:mm:ss.SSSZ");

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
      Object handler) throws ServletException {
    time = System.currentTimeMillis();
    return super.preHandle(request, response, handler);
  }

  /**
   * @return 客户端user-agent
   */
  private String getUserAgent(HttpServletRequest request) {
    Enumeration<String> headers = request.getHeaders("user-agent");
    if (headers.hasMoreElements()) {
      return headers.nextElement();
    } else {
      return null;
    }
  }

  // 处理器执行之后调用的方法，可以用来对处理器进行后置处理
  @Override
  public void postHandle(HttpServletRequest request,
      HttpServletResponse response, Object handler,
      ModelAndView modelAndView) throws Exception {
    long duration = System.currentTimeMillis() - time;
    String requestParam = JSON.toJSONString(request.getParameterMap());
    String RequestURI = request.getRequestURI();
    if (log.isInfoEnabled()) {
      String servletPath = request.getServletPath();

      ServletServerHttpRequest servletServerHttpRequest = new ServletServerHttpRequest(request);
      HttpHeaders headers = servletServerHttpRequest.getHeaders();
      Map<String, Object> requestLog = new HashMap<>();
      requestLog.put("servletPath", servletPath);
      requestLog.put("duration", duration);
      requestLog.put("ip", getIpAddr(request));
      requestLog.put("requestMethod", request.getMethod());
      requestLog.put("userAgent", getUserAgent(request));
      requestLog.put("response", (String) request.getAttribute(RESPONSE_DATA));
      requestLog.put("time", dateFormat.format(new Date()));

      if (log.isDebugEnabled()) {
        requestLog.put("parameter", request.getParameterMap());
        requestLog.put("headers", headers);
        String msg = JSON.toJSONString(requestLog, true);
        log.debug("\n" + msg);
      }

      requestLog.put("headers", JSON.toJSONString(headers));
      requestLog.put("parameter", JSON.toJSONString(request.getParameterMap()));
      String msg = JSON.toJSONString(requestLog).replaceAll("[\r\n]", "");
      log.info(MarkerFactory.getMarker("REQUESTLOG"), msg);
    }
    /**
     * 这里记录该次请求参数
     */
    if (!request.getServletPath().matches("/v1/park_heartbeat.*")) {
      String ip = getIpAddr(request);
      requestBiz.insertRequestParams(duration, RequestURI, requestParam,
          (String) request.getAttribute(RESPONSE_DATA), ip);
    }
    super.postHandle(request, response, handler, modelAndView);

  }


  //当整个请求完成时候（成功或者失败）的方法，如果有任何异常产生，则通过异常参数传入
  @Override
  public void afterCompletion(HttpServletRequest request,
      HttpServletResponse response, Object handler, Exception ex)
      throws Exception {
    super.afterCompletion(request, response, handler, ex);
  }


  /**
   * 获取IP
   */
  public String getIpAddr(HttpServletRequest request) {
    String ip = null;
    try {
      ip = request.getHeader("X-Forwarded-For");
      if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
        ip = request.getHeader("Proxy-Client-IP");
      }
      if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
        ip = request.getHeader("WL-Proxy-Client-IP");
      }
      if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
        ip = request.getHeader("HTTP_CLIENT_IP");
      }
      if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
        ip = request.getHeader("HTTP_X_FORWARDED_FOR");
      }
      if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
        ip = request.getRemoteAddr();
      }
    } catch (Exception ignored) {
    }
    return ip == null ? "" : ip;
  }
}
