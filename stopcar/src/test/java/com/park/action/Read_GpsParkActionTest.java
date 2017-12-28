
package com.park.action;

import com.park.constants.Constants;
import org.apache.commons.httpclient.methods.PostMethod;
import org.junit.Test;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import stopcar.test.action.BaseActionTest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * 通过GPS导航获取 该经纬度范围内的停车场数据列表
 * @author jingxiaohu
 *
 */
public class Read_GpsParkActionTest extends BaseWebTest {
	@Test
	public void getParkRentInfoByCarcode() throws Exception {
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		double lng=104.073234; //地理经度
		double lat=30.548214;//地理纬度
		int distance=500;//距离 单位米
		int park_type=0;//停车场类型 0：地下室停车场 1：露天停车场 2：露天立体车库停车场
		int type=0;//0 :按距离 1：按价格
		String area_code="510122";//area_code;//省市县编号 510122


		params.add("ui_id", 142+"");
		params.add("dtype", dtype);



		params.add("area_code", area_code);
		params.add("lng", lng+"");
		params.add("lat", lat+"");
		params.add("distance", distance+"");
		params.add("park_type", park_type+"");
		params.add("type", type+"");

		sign(params, "dtype","ui_id");

		MvcResult mvcResult = mockMvc.perform(post("/v1/read_gpspark").params(params))
				.andExpect(status().isOk()).andReturn();
		System.err.println(mvcResult.getResponse().getContentAsString());
	}
}
