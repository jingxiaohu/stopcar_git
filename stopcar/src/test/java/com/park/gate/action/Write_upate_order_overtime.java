package com.park.gate.action;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.park.action.BaseWebTest;
import org.junit.Test;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

/**
 * 更新预约/租赁超时状态
 *
 * @author Peter Wu
 */
public class Write_upate_order_overtime extends BaseWebTest {


  /**
   * 更新预约/租赁超时状态
   * 响应示例：
   * <pre>
   *   {"data":"","errorcode":"","errormsg":"更新预约/租赁超时状态成功","errorno":"0"}
   * </pre>
   */
  @Test
  public void upate_order_overtime() throws Exception {
    MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
    params.add("dtype", dtype);
    params.add("orderid", "2017021514214953");
    params.add("type", "0");
    sign(params, "dtype", "orderid", "type");

    MvcResult mvcResult = mockMvc.perform(post("/v1/upate_order_overtime").params(params))
        .andExpect(status().isOk()).andReturn();
    System.err.println(mvcResult.getResponse().getContentAsString());
  }
}
