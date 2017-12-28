package com.park.action;

import org.junit.Test;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 *app续约--根据租赁订单编号获取租赁订单
 *
 * @author zyy
 */
public class Read_rentDeferOneOrderActionTest extends BaseWebTest {
  
  /**
   * app续租--根据续租订单编号获取续租订单 测试
   *
   * 参数：
   * "parameter":{"ui_id":["54"],"rent_order_id":["20170703998181293836"],"dtype":["2"],"sign":["20b9f7018f3c0322ec7b4f564ac83404"] }
   *
   * 响应结果：
   * {"data":{"allege_state":0,"area_code":"510107","car_code":"川A00000","client_order_id":"","client_rule_id":"36","ctime":1500967673000,"defer_state":1,"endtime":1504195200000,"father_order_id":"","flag":2,"is_del":0,"is_expire":0,"money":10,"month_num":1,"mq_state":0,"note":"APP续租租赁信息","pay_source":0,"pay_state":0,"permit_time":"8：00-23：00","pi_id":36,"pi_name":"天府三街停车场","pu_id":0,"pu_nd":"","rd_id":4,"rent_order_id":"510107100000036420170725152753XA","rent_type":0,"son_order_id":"","starttime":1501516800000,"stime":1500967673000,"ui_id":54,"ui_nd":"2016122117450316","unit_price":10,"up_orderid":"","utime":1500967673000},"errorcode":"","errormsg":"获取续租订单成功","errorno":"0"}
   *
   */
  @Test
  public void get_rentDeferInfo() throws Exception {
	  MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
	  int ui_id = 54;
	  String rent_order_id = "20170703998181293839";

	  params.add("ui_id", ui_id+"");
	  params.add("rent_order_id", rent_order_id);
	  params.add("dtype", dtype);
	  
	  sign(params, "dtype","ui_id","rent_order_id");

	  MvcResult mvcResult = mockMvc.perform(post("/v1/get_rentdefer_one_order").params(params))
			  .andExpect(status().isOk()).andReturn();
	  System.err.println(mvcResult.getResponse().getContentAsString());
  }

}
