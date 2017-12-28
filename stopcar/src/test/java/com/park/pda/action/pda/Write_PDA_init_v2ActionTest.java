package com.park.pda.action.pda;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import com.park.action.BaseWebTest;

/**
 * 占道停车场PDA MAC的初始化提交
 * @author jingxiaohu
 *
 */
public class Write_PDA_init_v2ActionTest extends BaseWebTest {
	/**
	 * 占道停车场PDA MAC的初始化提交
	 * @throws Exception
	 *{"data":{"area_code":"","ctime":1499071580975,"is_initialize":1,"lat":0.0,"lng":0.0,"loginname":"20170703164620","mac":"869612023700523","note":"","password":"e10adc3949ba59abbe56e057f20f883e","pda_c_id":0,"pda_id":0,"pda_sn":"2017070316462084749","pi_id":0,"pi_name":"","plate_license":"","pu_id":0,"pu_nd":"","token":"8eb40671a2a64413ba2ddd672879c006","utime":1499071580975,"vnum":0},"errorcode":"","errormsg":"初始化成功","errorno":"0"}
	 */
	@Test
	public void init_pda_v2() throws Exception{
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();

		String mac="869612023700523";//车牌号
		
		params.add("mac", mac);
		params.add("dtype", dtype);
		
	    sign(params, "dtype","mac");

	    MvcResult mvcResult = mockMvc.perform(post("/v1/init_pda_v2").params(params))
	        .andExpect(status().isOk()).andReturn();
	    System.err.println(mvcResult.getResponse().getContentAsString());
	}
}
