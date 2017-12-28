
package com.park.pda.action.pda;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import com.park.action.BaseWebTest;

/**
 * 检查某停车场某车牌号是否已经付款
 * @author jingxiaohu
 *
 */
public class Read_PDA_CheckCarPayActionTest extends BaseWebTest {
	/**
	 * 检查某停车场某车牌号是否已经付款
	 * {"data":{"escape_orderids":"","money":1,"ui_id":0,"car_code":"川E11106","state":0,"pay_source":0,"is_cash":0},"errorcode":"","errormsg":"查询是否已经付款成功","errorno":"0"}
	 * @throws Exception
	 */
	@Test
	public void read_pda_checkpay() throws Exception{
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		long pi_id = 29;//场地主键ID
		String car_code = "川E11106";//车牌号
		String area_code="510107";//省市区区域代码  四川省 成都市 龙泉驿区
		String orderid="2017050214243112742";//我们的订单号  字符串
		
		params.add("dtype", dtype);
		params.add("pi_id", pi_id+"");
		params.add("car_code", car_code);
		params.add("area_code", area_code);
		params.add("orderid", orderid);
		
		sign(params, "dtype","pi_id");
		
		MvcResult mvcResult = mockMvc.perform(post("/v1/read_pda_checkpay").params(params))
				.andExpect(status().isOk()).andReturn();
		System.err.println(mvcResult.getResponse().getContentAsString());
	}
	
	
}
