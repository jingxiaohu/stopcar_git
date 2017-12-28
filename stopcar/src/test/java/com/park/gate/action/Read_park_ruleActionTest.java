package com.park.gate.action;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.junit.Test;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import com.park.action.BaseWebTest;

/**
 * 停车场-特殊规则信息映射
 *
 * @author zyy
 */
public class Read_park_ruleActionTest extends BaseWebTest {

	/**
	 * 获取停车场-特殊规则信息
	 * <pre>
	 * 	{"data":{"area_code":"510107","ctime":1497926909000,"json_array":"park_rule:{key:value}","note":"","pi_id":1001,"pr_id":2,"pr_nd":"832e78c724ea45e19945dcd38befcb3e","state":1,"utime":1497926909000},"errorcode":"","errormsg":"读取停车场规则信息成功","errorno":"0"}
	 * </pre>
	 */
	@Test
	public void get_parkrule() throws Exception {
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		
		long pi_id = 1001;
		String area_code = "510107";
		
		params.add("dtype", dtype);
		params.add("pi_id", pi_id+"");
		params.add("area_code", area_code);
		
		sign(params,"dtype", "pi_id", "area_code");
		
		MvcResult mvcResult = mockMvc.perform(post("/v1/get_parkrule").params(params))
				.andExpect(status().isOk()).andReturn();
		System.err.println(mvcResult.getResponse().getContentAsString());
	}
	
}
