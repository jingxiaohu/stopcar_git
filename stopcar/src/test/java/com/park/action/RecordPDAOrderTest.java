package com.park.action;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

/**
 * @author Peter Wu
 */
public class RecordPDAOrderTest extends BaseWebTest {

  /**
   * PDA获取某条订单信息
   *
   * <pre>
   *
   * </pre>
   */
  @Test
  public void read_pda_order() throws Exception {

    MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
    params.add("dtype", dtype);

    params.add("orderid", "2016122716104002");//获取订单类型  0:普通停车订单  1：租赁停车订单
    params.add("type", "0");//订单号

    sign(params, "dtype", "type", "orderid");

    MvcResult mvcResult = mockMvc.perform(post("/v1/read_pda_order").params(params))
        .andExpect(status().isOk()).andReturn();
    System.err.println(mvcResult.getResponse().getContentAsString());

  }
}
