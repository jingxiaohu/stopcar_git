package com.park.pda.action.pda;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import java.text.SimpleDateFormat;
import org.junit.Test;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import com.park.action.BaseWebTest;

/**
 * 停车场车位总数、已停车位数、空余车位数 流水记录
 *
 * @author zyy
 */
public class Write_PDA_FeedbackActionTest extends BaseWebTest {

	/**
	 * pda用户信息管理测试 响应例子
	 * <pre>
	 * 	{"data":"","errorcode":"","errormsg":"pda用户反馈成功","errorno":"0"}
	 * </pre>
	 */
	@Test
	public void pda_feedback() throws Exception {
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		
		String content = "很多停车场不开通这个网络渠道没法停车";
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
		java.util.Date date=new java.util.Date(); 
		String ctime=sdf.format(date); 
		String utime=sdf.format(date); 
    	long pi_id = 1007;
		String area_code = "510107";
		String pi_name = "环球中心S区停车场";
		long pda_id = 0614001;
		
		params.add("dtype", dtype);
		params.add("content", content);
		params.add("pi_id", pi_id+"");
		params.add("area_code", area_code);
		params.add("ctime", ctime+"");
		params.add("utime", utime+"");
		params.add("pi_name", pi_name);
		params.add("pda_id", pda_id+"");

		sign(params,"dtype", "pi_id", "area_code");

		MvcResult mvcResult = mockMvc.perform(post("/v1/pda_feedback").params(params))
				.andExpect(status().isOk()).andReturn();
		System.err.println(mvcResult.getResponse().getContentAsString());
	}
	
}
