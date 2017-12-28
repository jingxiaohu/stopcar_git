package com.park.gate.action;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.park.action.BaseWebTest;
import org.junit.Test;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

public class ParkConfirmTest extends BaseWebTest {
	/**
	 * 道闸确认车辆已经入库非包月车
	 * 响应数据列子：
	 * {"data":"","errorcode":"","errormsg":"操作成功","errorno":"0"}
	 * @throws Exception
	 */
	@Test
	public void parkConfirm() throws Exception{
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
	    params.add("dtype", dtype);
	    params.add("my_order", "2017012215082000");
	    params.add("order_type", "1");
	    sign(params, "dtype", "my_order");
	    MvcResult mvcResult = mockMvc.perform(post("/v1/parkconfirmcarout").params(params))
	            .andExpect(status().isOk()).andReturn();
	        System.err.println(mvcResult.getResponse().getContentAsString());
	}
	
	/**
	 * 道闸确认车辆已经入库包月车
	 * 响应数据列子：
	 * {"data":"","errorcode":"","errormsg":"操作成功","errorno":"0"}
	 * @throws Exception
	 */
	@Test
	public void parkMonthConfirm() throws Exception{
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
	    params.add("dtype", dtype);
	    params.add("my_order", "2017021414065617");
	    params.add("order_type", "2");
	    sign(params, "dtype", "my_order");
	    MvcResult mvcResult = mockMvc.perform(post("/v1/parkconfirmcarout").params(params))
	            .andExpect(status().isOk()).andReturn();
	        System.err.println(mvcResult.getResponse().getContentAsString());
	}

}
