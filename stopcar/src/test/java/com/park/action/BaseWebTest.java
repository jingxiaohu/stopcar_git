package com.park.action;

import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import com.park.constants.Constants;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.apache.commons.lang3.ArrayUtils;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.DigestUtils;
import org.springframework.util.MultiValueMap;
import org.springframework.web.context.WebApplicationContext;

/**
 * web容器基础测试类
 *
 * @author Peter Wu
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration({"file:src/main/webapp/WEB-INF/config/spring/spring.xml","file:src/main/webapp/WEB-INF/config/spring/dispatcher-servlet.xml"})
public abstract class BaseWebTest {

  private Logger log = LoggerFactory.getLogger(this.getClass());

  @Autowired
  private WebApplicationContext context;

  protected MockMvc mockMvc;

  @Before
  public void setup() throws Exception {
    mockMvc = webAppContextSetup(context).build();
  }


  /**
   * 从什么设备发出的请求 0:android 1:ios  2:web
   */
  protected final String dtype = "2";

  /**
   * @param requestParams 请求参数
   * @param paramNames 参与签名的参数名，为空表示所有非空字段
   */
  protected void sign(MultiValueMap<String, String> requestParams, String... paramNames) {
    List<String> values = new ArrayList<>();
    for (String key : requestParams.keySet()) {
      if ((ArrayUtils.isNotEmpty(paramNames) && !ArrayUtils.contains(paramNames, key))) {
        continue;
      }
      List<String> objects = requestParams.get(key);
      if (key.equalsIgnoreCase("sign")
          || key.equalsIgnoreCase("sign_type")) {
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
    String sign = DigestUtils.md5DigestAsHex(paramStr.getBytes());
    requestParams.add("sign", sign);
  }

}
