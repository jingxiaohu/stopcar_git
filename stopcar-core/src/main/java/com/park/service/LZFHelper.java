package com.park.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.Base64Utils;
import org.springframework.util.DigestUtils;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

/**
 * 龙支付辅助类
 *
 * @author Peter Wu
 */
@Component
public class LZFHelper {

  private Logger log = LoggerFactory.getLogger(LZFHelper.class);
  /**
   * 银企直联接口主机地址
   */
  @Value("${ebs.domain}")
  private String domain;
  /**
   * 接口签名密钥
   */
  @Value("${ebs.client_secret}")
  private String client_secret;

  private RestTemplate restTemplate = new RestTemplate();

  /**
   * 验证订单支付账号
   *
   * @param account 银行账号
   * @param orderNo 订单号
   * @return 是否匹配
   */
  public boolean accountCheck(String orderNo, String account) {
    try {
      MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
      params.add("orderNo", orderNo);
      params.add("account", account);
      params.add("sign", sign(params));

      String result = restTemplate.getForObject(
          expandUrl("/orders/accountCheck?orderNo={orderNo}&account={account}&sign={sign}"),
          String.class, params.toSingleValueMap());
      return "true".equals(result);
    } catch (Exception e) {
      log.error("验证订单支付账号失败", e);
    }
    return false;
  }

  /**
   * 查询订单信息
   *
   * @param orderNo 订单号
   * @return 订单信息
   */
  public Object info(String orderNo) {
    try {
      MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
      params.add("orderNo", orderNo);
      params.add("sign", sign(params));

      return restTemplate.getForObject(
          expandUrl("/orders/info?orderNo={orderNo}&sign={sign}"),
          Map.class, params.toSingleValueMap());
    } catch (Exception e) {
      log.error("查询订单信息失败", e);
      return "查询订单信息失败:" + e.getMessage();
    }
  }

  /**
   * 补全URL
   *
   * @param apiPath api path
   * @return 完整URL
   */
  private String expandUrl(String apiPath) {
    return domain + apiPath;
  }


  /**
   * 签名
   *
   * @param requestParams 请求参数
   * @return 签名
   */
  private String sign(MultiValueMap<String, String> requestParams) {
    List<String> keys = new ArrayList<>(requestParams.keySet());
    Collections.sort(keys);
    StringBuilder prestr = new StringBuilder("");
    for (String key : keys) {
      List<String> values = requestParams.get(key);
      StringBuilder value = new StringBuilder();
      int length = values.size();
      for (int i = 0; i < length; i++) {
        value.append(values.get(i));
        value.append((i == length - 1) ? "" : ",");
      }
      if (value == null || value.toString().equals("") || key.equalsIgnoreCase("sign")
          || key.equalsIgnoreCase("sign_type")) {
        continue;
      }
      prestr.append(key).append("=").append(value).append("&");
    }
    long timeMillis = System.currentTimeMillis();
    prestr.append(timeMillis);
    if (log.isDebugEnabled()) {
      log.debug("待签名参数字符串：{}", prestr);
    }
    prestr = prestr.append("{").append(client_secret).append("}");
    return Base64Utils.encodeToString(
        (timeMillis + ":" + DigestUtils.md5DigestAsHex(prestr.toString().getBytes())).getBytes());
  }
}
