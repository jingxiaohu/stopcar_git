package com.park.action;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

/**
 * app续约租赁订单管理
 *
 * @author zyy
 */
public class Read_RentDeferActionTest extends BaseWebTest {
  
  /**
   * app获取续约租赁订单测试  响应例子
   * <pre>
   * {"data":[
   * 	    {"allege_state":0,"area_code":"510107","car_code":"川A88888","client_order_id":"","client_rule_id":"","ctime":1499066792000,"defer_state":0,"endtime":1501572369000,"father_order_id":"","flag":0,"is_del":0,"is_expire":1,"money":100,"month_num":10,"mq_state":0,"note":"","pay_source":0,"pay_state":0,"permit_time":"8：00-23：00","pi_id":36,"pi_name":"","pu_id":1001,"pu_nd":"","rd_id":1,"rent_order_id":"20170703998181293836","rent_type":1,"son_order_id":"","starttime":1499066761000,"stime":1499066798000,"ui_id":54,"ui_nd":"2016122117450316","unit_price":10,"up_orderid":"","utime":1499066801000},
   * 	    {"allege_state":0,"area_code":"510107","car_code":"川A88888","client_order_id":"","client_rule_id":"","ctime":1499069768000,"defer_state":0,"endtime":1499069760000,"father_order_id":"","flag":0,"is_del":0,"is_expire":1,"money":122,"month_num":3,"mq_state":0,"note":"","pay_source":0,"pay_state":0,"permit_time":"11:00","pi_id":36,"pi_name":"","pu_id":1001,"pu_nd":"","rd_id":2,"rent_order_id":"20170703998181293837","rent_type":1,"son_order_id":"","starttime":1499069757000,"stime":1499069770000,"ui_id":54,"ui_nd":"2016122117450316","unit_price":12,"up_orderid":"","utime":1499069773000}
   * 	],"errorcode":"","errormsg":"app获取续约租赁订单成功","errorno":"0"}
   * </pre>
   *
   * 相应参数：
   * "parameter":{   "ui_id":["54"],   "pi_id":["36"],   "area_code":["510107"],   "dtype":["2"],   "page":["1"],   "size":["10"],   "sign":["194b8f89ba6098ef57ff42a8eda44fd6"]   }
   */
  @Test
  public void get_rentDeferInfo() throws Exception {
	  MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
	  params.add("ui_id", "54");
//	  params.add("pi_id", "36");
//	  params.add("area_code", "510107");
	  params.add("dtype", dtype);
	  params.add("page", 1+"");
	  params.add("size", 10+"");

	  sign(params, "dtype","ui_id");

	  MvcResult mvcResult = mockMvc.perform(post("/v1/get_rentdefer_info").params(params))
			  .andExpect(status().isOk()).andReturn();
	  System.err.println(mvcResult.getResponse().getContentAsString());
  }

}
