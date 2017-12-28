package com.park.gate.action;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.junit.Test;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import com.park.action.BaseWebTest;

/**
 * 客户端--道闸--规则记录 查询 测试类
 *
 * @author zyy
 */
public class Read_client_gate_ruleActionTest extends BaseWebTest {

	/**
	 * 查询客户端道闸规则记录
	 * <pre>
	 * 	{"data":{"area_code":"510107","cgr_id":1,"client_loginname":"user02","client_ruleid":"67ab4ebf98cc4d56a3ea21e5af80d28b","ctime":1499926897000,"group_id":"S001","intro":"运行正常1。。。","is_del":0,"money":3000,"note":"","permit_time":"08：00-20：00","pi_id":1001,"state":1,"str_json":"park_rule:{key1:value1}","type":2,"utime":1499932872000},"errorcode":"","errormsg":"客户端道闸规则记录查询成功","errorno":"0"}
	 * </pre>
	 */
	@Test
	public void get_client_gate_rule_byId() throws Exception {
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		String client_ruleid = "1f7518c1099d4733987fc6a6c541cbbc";
		Long pi_id = 1001L;
		String area_code = "510107";
		
		params.add("client_ruleid", client_ruleid);
		params.add("pi_id", pi_id+"");
		params.add("area_code", area_code);
		params.add("dtype", dtype);
		
		sign(params,"dtype", "client_ruleid", "pi_id", "area_code");
		
		MvcResult mvcResult = mockMvc.perform(post("/v1/get_client_gate_rule_byId").params(params))
				.andExpect(status().isOk()).andReturn();
		System.err.println(mvcResult.getResponse().getContentAsString());
	}
	
}
