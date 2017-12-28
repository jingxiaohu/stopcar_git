package com.park.pda.action.pda;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import com.park.action.BaseWebTest;

/**
 * 检查PDA版本
 *
 * @author jingxiaohu
 */
public class Read_PDA_VersionActionTest extends BaseWebTest {

  /**
   * 检查PDA版本
   *
   * {"data":{"content":"","is_forced":0,"md5":"095afeb7dcd88976ad93b9c7429317d5","type":"pda","update":1,"url":"http://223.85.163.38:8000/file/upgrade/pda/channel5-vc11-1.11.3.apk","version":"1.11.3","versioncode":11},"errorcode":"","errorno":"0"}
   */
	@Test
	public void pda_gainupgrade() throws Exception {
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		String mac="869612021322674";//省市区区域代码  四川省 成都市 龙泉驿区  510112
		long versioncode=10;//省市区区域代码  四川省 成都市 龙泉驿区  510112
		
		params.add("mac", mac);
		params.add("versioncode", versioncode+"");
		
		sign(params, "mac","versioncode");
		
		MvcResult mvcResult = mockMvc.perform(post("/v1/pda_gainupgrade").params(params))
			  .andExpect(status().isOk()).andReturn();
		System.err.println(mvcResult.getResponse().getContentAsString());
	}

}
