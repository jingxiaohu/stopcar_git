package com.park.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.park.exception.QzException;
import com.wintone.Adaptor.CipherAdaptor;
import java.io.StringReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.apache.commons.io.FilenameUtils;
import org.dom4j.Document;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.GsonHttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

/**
 * 文通图像识别辅助类
 *
 * @author Peter Wu
 */
@Component
public class CxfServerXHelper {

  private Logger log = LoggerFactory.getLogger(CxfServerXHelper.class);
  /**
   * 接口地址,示例：http://192.168.0.157:8080/cxfServerX
   */
  @Value("${cxf.url}")
  private String url;

  private RestTemplate restTemplate = new RestTemplate();
  private CipherAdaptor clientAdaptor = new CipherAdaptor();

  public CxfServerXHelper() {
    List<HttpMessageConverter<?>> messageConverters = new ArrayList<>();
    messageConverters.add(new GsonHttpMessageConverter() {
      @Override
      public boolean canWrite(Class<?> clazz, MediaType mediaType) {
        return true;
      }
    });
    messageConverters.add(new StringHttpMessageConverter(Charset.forName("UTF-8")));
    restTemplate.setMessageConverters(messageConverters);

    faceplusplusRestTemplate.setErrorHandler(new DefaultResponseErrorHandler() {
      @Override
      public void handleError(ClientHttpResponse response) {
      }
    });
  }

  /**
   * 人脸识别
   *
   * @param uuid 用户名uuid,仅起标识作用
   * @param img1 图像1 绝对路径
   * @param img2 图像2 绝对路径
   * @return 是否匹配
   * @throws QzException 识别失败
   */
  public boolean doFaceRecon(String uuid, String img1, String img2) throws QzException {
    try {
      JSONObject json = new JSONObject();

      String type = "21";//人脸识别类型编码
      String strsrc1 = clientAdaptor.setRecgnPlainParam(img1, type, "", null);
      String strsrc2 = clientAdaptor.setRecgnPlainParam(img2, type, "", null);
      String strsrc = strsrc1 + "faceRecon" + strsrc2; // 将两个参数以faceRecon分割组成一个串
      String imgtype1 = FilenameUtils.getExtension(img1);
      String imgtype2 = FilenameUtils.getExtension(img2);

      json.put("username", uuid);
      json.put("paramdata", strsrc);
      json.put("signdata", "NULL");
      json.put("imgtype", imgtype1 + "faceRecon" + imgtype2);//人脸组装的图片类型

      if (log.isDebugEnabled()) {
        log.debug("人脸识别请求参数:{}", json.toJSONString());
      }

      String result = restTemplate.postForObject(expandUrl("/doAllCardRecon"), json, String.class);
      if (log.isDebugEnabled()) {
        log.debug("人脸识别结果:{}", JSON.toJSONString(result, true));
      }
      Document document = new SAXReader().read(new StringReader(result.split("==@@")[0]));
      if (document != null) {
        String status = document.selectSingleNode(
            "//status").getText();
        if ("0".equals(status)) {
          String item1 = document.selectSingleNode(
              "//item[1]").getText();//
          if (log.isInfoEnabled()) {
            log.info("图像匹配率：{}", item1);
          }
          String item2 = document.selectSingleNode(
              "//item[2]").getText();
          return "是".equals(item2);
        }
      }
    } catch (Exception e) {
      log.error("识别失败", e);
    }
    return false;
  }

  /**
   * 补全URL
   *
   * @param apiPath api path
   * @return 完整URL
   */
  private String expandUrl(String apiPath) {
    if (log.isDebugEnabled()) {
      log.debug("接口：{}", url + apiPath);
    }
    return url + apiPath;
  }

  @Value("${faceplusplus.compare_url}")
  private String faceplusplus_compare_url;
  @Value("${faceplusplus.api_key}")
  private String faceplusplus_api_key;
  @Value("${faceplusplus.api_secret}")
  private String faceplusplus_api_secret;
  private RestTemplate faceplusplusRestTemplate = new RestTemplate();

  /**
   * Face++ 人脸识别
   *
   * @param img1 图像1 绝对路径
   * @param img2 图像2 绝对路径
   * @return 是否匹配
   * @throws QzException 识别失败
   */
  public boolean faceplusplusCompare(String img1, String img2) throws QzException {
    try {
      MultiValueMap<String, Object> params = new LinkedMultiValueMap<>();
      params.add("api_key", faceplusplus_api_key);
      params.add("api_secret", faceplusplus_api_secret);
      params.add("image_file1", new FileSystemResource(img1));
      params.add("image_file2", new FileSystemResource(img2));

      if (log.isDebugEnabled()) {
        log.debug("Face++人脸识别请求参数:{},{}", img1, img2);
      }

      Map result = faceplusplusRestTemplate
          .postForObject(faceplusplus_compare_url, params, Map.class);
      if (log.isDebugEnabled()) {
        log.debug("人脸识别结果:{}", JSON.toJSONString(result, true));
      }
      Double confidence = (Double) result.get("confidence");
      if (log.isInfoEnabled()) {
        log.info("图像匹配率：{}", confidence);
      }
      if (confidence != null && confidence > 80) {
        return true;
      } else if (confidence == null) {
        log.error("识别失败:{}", JSON.toJSONString(result, true));
      }
    } catch (Exception e) {
      log.error("识别失败", e);
    }
    return false;
  }


}
