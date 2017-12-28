package com.park.gate.action;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import com.park.action.BaseWebTest;

/**
 * 停车场车位总数、已停车位数、空余车位数
 *
 * @author zyy
 */
public class Write_park_carport_logActionTest extends BaseWebTest {

	/**
	 * 添加 停车场车位总数、已停车位数、空余车位数测试 响应例子
	 * <pre>
	 * 	{"data":{"area_code":"510107","carport_space":1,"carport_total":2,"carport_yet":1,"ctime":1497251988734,"data_flag":2,"note":"","park_type":1,"pcl_id":0,"pi_id":1008,"stime":1497261582272},"errorcode":"","errormsg":"添加成功","errorno":"0"}
	 * </pre>
	 */
	@Test
	public void gate_insert_park_carport_log() throws Exception {
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		
    	Long pi_id = 1007L;
		String area_code = "510107";
		Integer carport_total = 2;
		Integer carport_yet = 1;
		Integer carport_space = 1;
		Integer park_type = 1;
		Integer data_flag = 2;
		Long ctime = 1497251988734L;
		
		params.add("dtype", dtype);
		params.add("pi_id", pi_id+"");
		params.add("area_code", area_code);
		params.add("carport_total", carport_total+"");
		params.add("carport_yet", carport_yet+"");
		params.add("carport_space", carport_space+"");
		params.add("park_type", park_type+"");
		params.add("data_flag", data_flag+"");
		params.add("ctime", ctime+"");

		sign(params,"dtype", "pi_id", "area_code");

		MvcResult mvcResult = mockMvc.perform(post("/v1/gate_insert_park_carport_log").params(params))
				.andExpect(status().isOk()).andReturn();
		System.err.println(mvcResult.getResponse().getContentAsString());
	}
	
}
