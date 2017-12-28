package com.park.action;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

public class PDAParkConfirmTest extends BaseWebTest{
	/**
	 * PDA确认车辆已经入库非包月车
	 * 响应数据列子：
	 * {"data":"","errorcode":"","errormsg":"操作成功","errorno":"0"}
	 * @throws Exception
	 */
	@Test
	public void parkConfirm() throws Exception{
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
	    params.add("dtype", dtype);
	    params.add("my_order", "2017051816502549566");
	    params.add("order_type", "1");
	    sign(params, "dtype", "my_order");
	    MvcResult mvcResult = mockMvc.perform(post("/v1/pda_parkconfirmcarout").params(params))
	            .andExpect(status().isOk()).andReturn();
	        System.err.println(mvcResult.getResponse().getContentAsString());
	}
	
	/**
	 * PDA确认车辆已经入库包月车
	 * 响应数据列子：
	 * {"data":"","errorcode":"","errormsg":"操作成功","errorno":"0"}
	 * @throws Exception
	 */
	@Test
	public void parkMonthConfirm() throws Exception{
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
	    params.add("dtype", dtype);
	    params.add("my_order", "2017051517074652221");
	    params.add("order_type", "2");
	    sign(params, "dtype", "my_order");
	    MvcResult mvcResult = mockMvc.perform(post("/v1/pda_parkconfirmcarout").params(params))
	            .andExpect(status().isOk()).andReturn();
	        System.err.println(mvcResult.getResponse().getContentAsString());
	}

}
