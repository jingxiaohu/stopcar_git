package com.park.gate.action;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.junit.Test;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import com.park.action.BaseWebTest;
import com.park.util.RequestUtil;

/**
 * 客户端--道闸--规则记录 管理 测试类
 *
 * @author zyy
 */
public class Write_client_gate_ruleActionTest extends BaseWebTest {

	/**
	 * 添加客户端道闸规则记录
	 * <pre>
	 * 	{"data":{"area_code":"510107","cgr_id":0,"client_loginname":"user01","client_ruleid":"67ab4ebf98cc4d56a3ea21e5af80d28b","ctime":1499926897242,"group_id":"A001","intro":"测试。。。","money":2000,"note":"","permit_time":"08：00-19：00","pi_id":1001,"state":1,"str_json":"park_rule:{key:value}","type":2,"utime":1499926897242},"errorcode":"","errormsg":"客户端道闸规则记录添加成功","errorno":"0"}
	 * </pre>
	 */
	@Test
	public void add_client_gate_rule() throws Exception {
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		
		String client_ruleid = "0c27170930b74a17bd07cbc16f8afdae";
    	Long pi_id = 1001L;
		String area_code = "510107";
		String group_id = "A002";
		Integer type = 2;
		Integer money = 2000;
		Integer state = 1;
		String str_json = "park_rule:{key:value}";
		String intro = "测试。。。";
		String permit_time = "08：00-19：00";
		String client_loginname = "user01";
		
		params.add("client_ruleid", client_ruleid);
		params.add("pi_id", pi_id+"");
		params.add("area_code", area_code);
		params.add("group_id", group_id);
		params.add("type", type+"");
		params.add("money", money+"");
		params.add("state", state+"");
		params.add("str_json", str_json);
		params.add("intro", intro);
		params.add("permit_time", permit_time);
		params.add("client_loginname", client_loginname);
		params.add("dtype", dtype);

		sign(params,"dtype", "pi_id", "area_code");

		MvcResult mvcResult = mockMvc.perform(post("/v1/add_client_gate_rule").params(params))
		        .andExpect(status().isOk()).andReturn();
		System.err.println(mvcResult.getResponse().getContentAsString());
	}
	
	/**
	 * 添加客户端道闸规则记录
	 * <pre>
	 * 	{"data":{"area_code":"510107","cgr_id":1,"client_loginname":"user02","client_ruleid":"67ab4ebf98cc4d56a3ea21e5af80d28b","ctime":1499926897000,"group_id":"S001","intro":"测试1。。。","is_del":0,"money":3000,"note":"","permit_time":"08：00-20：00","pi_id":1001,"state":1,"str_json":"park_rule:{key1:value1}","type":2,"utime":1499931345075},"errorcode":"","errormsg":"客户端道闸规则记录修改成功","errorno":"0"}
	 * </pre>
	 */
	@Test
	public void update_client_gate_rule() throws Exception {
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		
		String client_ruleid = "0c27170930b74a17bd07cbc16f8afdae";
		Long pi_id = 1001L;
		String area_code = "510107";
		String group_id = "SAD001";
//		Integer type = 2;
		Integer money = 10000;
		Integer state = 1;
		String str_json = "park_rule:{key1:value1}";
		String intro = "测试3。。。";
//		String permit_time = "08：00-20：00";
		String client_loginname = "user003";
		
		params.add("client_ruleid", client_ruleid);
		params.add("pi_id", pi_id+"");
		params.add("area_code", area_code);
		params.add("group_id", group_id);
//		params.add("type", type+"");
		params.add("money", money+"");
		params.add("state", state+"");
		params.add("str_json", str_json);
		params.add("intro", intro);
//		params.add("permit_time", permit_time);
		params.add("client_loginname", client_loginname);
		params.add("dtype", dtype);
		
		sign(params,"dtype", "client_ruleid", "pi_id", "area_code");
		
		MvcResult mvcResult = mockMvc.perform(post("/v1/update_client_gate_rule").params(params))
				.andExpect(status().isOk()).andReturn();
		System.err.println(mvcResult.getResponse().getContentAsString());
	}
	
	/**
	 * 删除客户端道闸规则记录
	 * <pre>
	 * 	{"data":"","errorcode":"","errormsg":"客户端道闸规则记录删除成功","errorno":"0"}
	 * </pre>
	 */
	@Test
	public void delete_client_gate_rule() throws Exception {
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		
		String client_ruleid = "67ab4ebf98cc4d56a3ea21e5af80d28b";
		Long pi_id = 1001L;
		String area_code = "510107";
		
		params.add("client_ruleid", client_ruleid);
		params.add("pi_id", pi_id+"");
		params.add("area_code", area_code);
		params.add("dtype", dtype);
		
		sign(params,"dtype", "client_ruleid", "pi_id", "area_code");
		
		MvcResult mvcResult = mockMvc.perform(post("/v1/delete_client_gate_rule").params(params))
				.andExpect(status().isOk()).andReturn();
		System.err.println(mvcResult.getResponse().getContentAsString());
	}
	
}
