package com.park.pda.action.pda;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import com.park.action.BaseWebTest;

public class Write_PDA_ParkConfirmCarOutActionTest extends BaseWebTest {

	/**
	 * PDA确认地磁车位车辆已经出库
	 * 响应数据列子：
	 * {"data":"","errorcode":"","errormsg":"操作成功","errorno":"0"}
	 * @throws Exception
	 */
	@Test
	public void pda_parkconfirmcarout() throws Exception{
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
	    params.add("dtype", dtype);
	    params.add("my_order", "2017020713389949");
	    params.add("order_type", "1");
	    
	    sign(params, "dtype", "my_order");
	    
	    MvcResult mvcResult = mockMvc.perform(post("/v1/pda_parkconfirmcarout").params(params))
				  .andExpect(status().isOk()).andReturn();
			System.err.println(mvcResult.getResponse().getContentAsString());
	}
}
