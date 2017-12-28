package com.park.action;

import org.junit.Test;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 *设备编号管理
 *
 * @author zyy
 */
public class Read_ParkInfoForWebActionTest extends BaseWebTest {
  
  /**
   * 按设备编号查询测试  响应例子
   * <pre>
   * 	jsonp_result({"result":[{"pi_name":"西华大学","carport_total":10,"carport_yet":2,"carport_space":8,"lng":105.45148,"lat":28.88549,"park_state":"warning","pi_state":1,"pu_id":2,"is_fault":0,"park_type":0,"juli":0.0},{"pi_name":"洪洋农贸市场地下车库","carport_total":120,"carport_yet":105,"carport_space":15,"lng":105.45148,"lat":28.88549,"park_state":"free","pi_state":1,"pu_id":1,"is_fault":1,"park_type":0,"juli":0.0},{"pi_name":"b万年场停车场","carport_total":1,"carport_yet":1,"carport_space":0,"lng":105.46148,"lat":28.89549,"park_state":"full","pi_state":1,"pu_id":1,"is_fault":0,"park_type":0,"juli":1480.0}]})
   * </pre>
   */
  @Test
  public void getParkInfoForWeb() throws Exception {
	  MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
	  Double lng=105.44148; //地理经度
	  Double lat=28.87549;//地理纬度
	  Integer distance=3000;//距离 单位米
	  String area_code="510105";//area_code;
	  String jsonpCallback = "jsonp_result";

	  params.add("area_code", area_code);
	  params.add("lng", lng+"");
	  params.add("lat", lat+"");
	  params.add("distance", distance+"");
	  params.add("jsonpCallback", jsonpCallback);
	  
	  MvcResult mvcResult = mockMvc.perform(post("/v1/get_parkinfo_forweb").params(params))
			  .andExpect(status().isOk()).andReturn();
	  System.err.println(mvcResult.getResponse().getContentAsString());

  }

}
