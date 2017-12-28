package com.park.sign;

import com.park.constants.Constants;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;

/**
 * 签名适配 <p> 默认签名算法为MD5 <p> SIGN_TYPE = "MD5";
 *
 * @author Peter Wu
 */
@Component
public class ApiSignAlgorithm {

  private Logger log = LoggerFactory.getLogger(ApiSignAlgorithm.class);

  @Autowired(required = false)
  private HttpServletRequest request;

  /**
   * @param requestParams 请求参数
   * @param paramNames 参与签名的参数名，为空表示所有非空字段
   * @return 签名
   */
  public String sign(Map<String, String[]> requestParams, String... paramNames) {
    List<String> values = new ArrayList<>();
    for (String key : requestParams.keySet()) {
      if ((ArrayUtils.isNotEmpty(paramNames) && !ArrayUtils.contains(paramNames, key))) {
        continue;
      }
      String[] objects = requestParams.get(key);
      if (key.equalsIgnoreCase("sign")) {
        continue;
      }

      for (String object : objects) {
        String str = String.valueOf(object);
        if (str != null && !str.equals("")) {
          try {
            values.add(URLEncoder.encode(str, "UTF-8"));
          } catch (UnsupportedEncodingException ignored) {
            //			never throw
          }
        }
      }
    }
    Collections.sort(values);
    StringBuilder paramValue = new StringBuilder();
    for (Object param : values) {
      paramValue.append(param);
    }
    paramValue.append(Constants.SYSTEM_KEY);
    String paramStr = paramValue.toString();
    log.info("待签名字符串：{}", paramStr);
    return DigestUtils.md5DigestAsHex(paramStr.getBytes());
  }

  /**
   * @param requestParams 请求参数
   * @param paramNames 参与签名的参数名
   * @param sign 签名  @return 签名是否正确
   */
  public boolean isSign(Map<String, String[]> requestParams, String[] paramNames, String sign) {
    if (!StringUtils.hasText(sign)) {
      return false;
    }
    String serviceSign = sign(requestParams, paramNames);
    if (log.isDebugEnabled()) {
      log.debug("服务端签名：{}", serviceSign);
    }
    return sign.equals(serviceSign);
  }

}
