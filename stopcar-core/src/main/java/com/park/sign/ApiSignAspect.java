package com.park.sign;

import javax.servlet.http.HttpServletRequest;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.util.WebUtils;

/**
 * 签名验证切面
 *
 * @author Peter Wu
 */
@Component
@Aspect
public class ApiSignAspect {

  private Logger log = LoggerFactory.getLogger(ApiSignAspect.class);

  @Autowired(required = false)
  private HttpServletRequest request;
  @Autowired
  private ApiSignAlgorithm apiSignAlgorithm;

  /**
   * 签名参数名
   */
  private String signParameter = "sign";

  public void setSignParameter(String signParameter) {
    this.signParameter = signParameter;
  }

  /**
   * 验证
   */
  @Before(value = "@annotation(com.park.sign.ApiSign)")
  public void verify(JoinPoint jp) {
//    String dtype = request.getParameter("dtype");
//    Assert.notNull(dtype, "dtype不能为空");
//    try {
//      Integer.parseInt(dtype);
//    } catch (NumberFormatException e) {
//      throw new IllegalArgumentException("dtype参数类型错误");
//    }

    //--------------------------------------------
    String sign = request.getParameter(this.signParameter);
    Assert.hasText(sign, "sign不能为空");

    ApiSign annotation = ((MethodSignature) jp.getSignature()).getMethod()
        .getAnnotation(ApiSign.class);
    String[] paramNames = annotation.value();

    if (!apiSignAlgorithm.isSign(request.getParameterMap(), paramNames, sign)) {
      RequestContextHolder.getRequestAttributes()
          .setAttribute(WebUtils.ERROR_STATUS_CODE_ATTRIBUTE, HttpStatus.BAD_REQUEST.value(),
              RequestAttributes.SCOPE_REQUEST);
      String msg = "签名验证失败";
      RequestContextHolder.getRequestAttributes()
          .setAttribute(WebUtils.ERROR_MESSAGE_ATTRIBUTE, msg, RequestAttributes.SCOPE_REQUEST);
      IllegalArgumentException invalid_request = new IllegalArgumentException(msg);
      RequestContextHolder.getRequestAttributes()
          .setAttribute(WebUtils.ERROR_EXCEPTION_ATTRIBUTE, invalid_request,
              RequestAttributes.SCOPE_REQUEST);
      throw invalid_request;
    } else if (log.isDebugEnabled()) {
      log.debug("通过签名验证");
    }
  }
}
