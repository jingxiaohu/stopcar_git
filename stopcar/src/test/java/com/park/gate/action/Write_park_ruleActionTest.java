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
public class Write_park_ruleActionTest extends BaseWebTest {

	/**
	 * 添加 停车场-特殊规则信息
	 * <pre>
	 * 	{"data":{"area_code":"510107","ctime":1497837687035,"json_array":"park_rule:{key:value}","note":"","pi_id":1001,"pr_id":0,"pr_nd":"97b7e10350204c50a33f286093ae6a24","state":1,"utime":1497837687035},"errorcode":"","errormsg":"添加成功","errorno":"0"}
	 * </pre>
	 */
	@Test
	public void add_parkrule() throws Exception {
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		
    	long pi_id = 1001;
		String area_code = "510107";
		String json_array = "park_rule:{key:value}";
		
		params.add("dtype", dtype);
		params.add("pi_id", pi_id+"");
		params.add("area_code", area_code);
		params.add("json_array", json_array);

		sign(params,"dtype", "pi_id", "area_code");

		MvcResult mvcResult = mockMvc.perform(post("/v1/add_parkrule").params(params))
		        .andExpect(status().isOk()).andReturn();
		    System.err.println(mvcResult.getResponse().getContentAsString());
	}
	
	/**
	 * 修改停车场-特殊规则信息
	 * <pre>
	 * 	{"data":{"area_code":"510107","ctime":1497837687000,"json_array":"park_rule:{key1:value1}","note":"","pi_id":1001,"pr_id":1,"pr_nd":"97b7e10350204c50a33f286093ae6a24","state":1,"utime":1497843383878},"errorcode":"","errormsg":"停车场规则信息修改成功","errorno":"0"}
	 * </pre>
	 */
	@Test
	public void update_parkrule() throws Exception {
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		
    	long pi_id = 1001;
		String area_code = "510107";
		String json_array = "park_rule:{key1:value1}";
		
		params.add("dtype", dtype);
		params.add("pi_id", pi_id+"");
		params.add("area_code", area_code);
		params.add("json_array", json_array);

		sign(params,"dtype", "pi_id", "area_code");

		MvcResult mvcResult = mockMvc.perform(post("/v1/update_parkrule").params(params))
		        .andExpect(status().isOk()).andReturn();
		    System.err.println(mvcResult.getResponse().getContentAsString());
	}
	
}
