package com.park.pda.action.pda;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import com.park.action.BaseWebTest;

/**
 * 更新订单的 逃逸 状态 
 * @author jingxiaohu
 *
 */
public class Write_UpdateOrderEscapeActionTest extends BaseWebTest {
	
	/**
	 * 更新订单的 逃逸 状态 
	 * {"data":"","errorcode":"","errormsg":"更新成功","errorno":"0"}
	 * @throws Exception
	 */
	@Test
	public void upate_order_escape() throws Exception{
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		
		String orderid="2017041309223615";//我们的订单号  字符串
		int type = 1;//类型 0:正常  1:逃逸   2:已经缴费--编辑逃逸状态为--已经缴费
		int money = 0;//金额
		
		params.add("orderid", orderid);
		params.add("type", type+"");
		params.add("money", money+"");
		params.add("dtype", dtype);
		
	    sign(params, "dtype","orderid","type","money");

	    MvcResult mvcResult = mockMvc.perform(post("/v1/upate_order_escape").params(params))
	        .andExpect(status().isOk()).andReturn();
	    System.err.println(mvcResult.getResponse().getContentAsString());
		
	}
	
	
	
}
