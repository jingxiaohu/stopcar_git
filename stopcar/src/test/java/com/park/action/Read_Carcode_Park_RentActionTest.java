package com.park.action;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.junit.Test;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

/**
 * 用户根据车牌号查询租赁信息
 *
 * @author zyy
 */
public class Read_Carcode_Park_RentActionTest extends BaseWebTest {
  
  /**
   * 用户根据车牌号查询租赁信息  响应例子
   * <pre>
   * 	{"data":[{"area_code":"510107","car_code":"川EU4535","client_rule_id":"","endtime":1499915431000,"is_del":0,"is_expire":0,"note":"","permit_time":"8：00-23：00","pi_id":1001,"pi_name":"洪洋农贸市场地下车库","rd_id":1,"rent_type":0,"starttime":1499915428000,"stime":1499915433000,"ui_id":40,"ui_nd":"2016121214247768","unit_price":1000,"utime":1499915436000}],"errorcode":"","errormsg":"查询租赁信息成功","errorno":"0"}
   * </pre>
   */
  @Test
  public void getParkRentInfoByCarcode() throws Exception {
	  MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
	  
	  long ui_id = 40;
	  String car_code = "川EU4535";
	  int page  = 1;
	  int size = 10;
	  
	  params.add("ui_id", ui_id+"");
	  params.add("car_code", car_code);
	  params.add("page", page+"");
	  params.add("size", size+"");
	  params.add("dtype", dtype);
	  
	  sign(params, "dtype","ui_id");

	  MvcResult mvcResult = mockMvc.perform(post("/v1/getParkRentInfoByCarcode").params(params))
			  .andExpect(status().isOk()).andReturn();
	  System.err.println(mvcResult.getResponse().getContentAsString());
  }

}
