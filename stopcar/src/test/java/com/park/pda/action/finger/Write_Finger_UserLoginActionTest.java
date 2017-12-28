package com.park.pda.action.finger;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.junit.Test;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import com.park.action.BaseWebTest;

/**
 * PDA用户登录
 * @author zyy
 *
 */
public class Write_Finger_UserLoginActionTest extends BaseWebTest {
	/**
	 * 指纹采集--用户登录 测试
	 *
	 * 响应结果
	 * 	{"data":{"address":"24412","age":18,"area_code":"510107","ctime":1488506998000,"link_tel":"12345678910","loginname":"20170720105013","mac":"4243423432432","name":"test001","note":"434","password":"098f6bcd4621d373cade4e832627b4f6","pda_id":0,"pi_id":423,"pui_id":1,"sex":1,"state":0,"tel":"13980460681","utime":1488506998000},"errorcode":"","errormsg":"登录成功","errorno":"0"}
	 *
	 * 参数：
	 * "parameter":{"dtype":["2"],"loginname":["20170720105013"],"password":["098f6bcd4621d373cade4e832627b4f6"],"sign":["5a8a895f5a4d7b71b5bb676ee0a1a356"] }
	 */
	@Test
	public void pda_user_login() throws Exception{
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();

		String loginname="20170720105013";
		String password="098f6bcd4621d373cade4e832627b4f6";
		
		params.add("dtype", dtype);
		params.add("loginname", loginname);
		params.add("password", password);
		 
	    sign(params, "dtype","loginname","password");

	    MvcResult mvcResult = mockMvc.perform(post("/v1/pda_user_login").params(params))
	        .andExpect(status().isOk()).andReturn();
	    System.err.println(mvcResult.getResponse().getContentAsString());
	}
}
