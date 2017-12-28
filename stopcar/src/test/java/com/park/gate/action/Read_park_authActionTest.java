package com.park.gate.action;

import apidoc.jxh.cn.InterfaceUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.park.action.BaseWebTest;
import com.park.gate.action.v1.park.param.Param_park_auth;
import com.park.util.QEncodeUtil;
import org.junit.Test;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * 停车场-特殊规则信息映射
 *
 * @author zyy
 */
public class Read_park_authActionTest extends BaseWebTest {

	/**
	 * 停车场-获取授权信息
	 * <pre>
	 * </pre>
	 */
	@Test
	public void read_park_auth() throws Exception {
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		
		long pi_id = 1;
		String area_code = "510107";
		
		params.add("dtype", dtype);
		params.add("pi_id", pi_id+"");
		params.add("area_code", area_code);
		
		sign(params,"dtype", "pi_id", "area_code");
		
		MvcResult mvcResult = mockMvc.perform(post("/v1/read_park_auth").params(params))
				.andExpect(status().isOk()).andReturn();
		String result = mvcResult.getResponse().getContentAsString();
		JSONObject obj = JSON.parseObject(result);
		System.err.println(QEncodeUtil.aesDecrypt(obj.getJSONObject("data").getString("park_authorize"), "AFfsfhMFAFAS$#@%^&*&^%$!as79123fnDa"));
		String path = this.getClass().getResource(".").getPath();
		path = path + "Parkinfo.md";
		InterfaceUtil.AddInterfacePred(path, "停车场管理",
				"读取频道信息列表",
				"dtype+pi_id+area_code",
				"/v1/read_park_auth",
				1,
				params,
				Param_park_auth.class,
				result);
	}
	
}
