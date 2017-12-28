package com.park.action;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Locale;
import org.junit.Test;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

/**
 * 根据车牌读取用户指纹信息
 * @author Peter Wu
 */
public class Read_finger_userinfoTest extends BaseWebTest {


  /**
   * 根据车牌读取用户指纹/指静脉信息
   * 参数：
   * <pre>
   *   {
     "dtype":["2"],
     "car_code":["京A59646"],
     "finger_flag":["0"],
     "sign":["6ae678cfa24dea6c244d71b23cb1890f"]
   }
   * </pre>
   * 响应：
   * <pre>
   *   {"data":["[\"46504D12……00000\"]"],"errorcode":"","errormsg":"读取用户指静脉信息成功","errorno":"0"}
   * </pre>
   * @throws Exception
   */
  @Test
  public void read_finger_userinfo() throws Exception {
    MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
    params.add("dtype", dtype);
    params.add("car_code", "京A59646");
    params.add("finger_flag", 0+"");
    sign(params, "dtype", "car_code", "finger_flag");

    MvcResult mvcResult = mockMvc.perform(post("/v1/read_finger_userinfo").locale(Locale.CHINA).params(params))
        .andExpect(status().isOk()).andReturn();
    System.err.println(mvcResult.getResponse().getContentAsString());
  }
}
