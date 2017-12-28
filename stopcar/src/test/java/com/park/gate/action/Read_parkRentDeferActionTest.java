package com.park.gate.action;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.junit.Test;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import com.park.action.BaseWebTest;

/**
 * Created by zyy on 2017/7/3.
 */
public class Read_parkRentDeferActionTest extends BaseWebTest {

		  /**
		   * 按设备编号查询测试  响应例子
		   * <pre>
		   * 	{"data":[{"area_code":"510107","car_code":"川A88888","ctime":1499069768000,"defer_state":0,"endtime":1499069760000,"father_order_id":"","flag":0,"is_del":0,"is_expire":1,"money":122,"month_num":3,"mq_state":0,"note":"","pay_source":0,"pay_state":0,"permit_time":"11:00","pi_id":36,"pi_name":"","pu_id":1001,"pu_nd":"","rd_id":2,"rent_order_id":"20170703998181293837","rent_type":1,"son_order_id":"","starttime":1499069757000,"stime":1499069770000,"ui_id":54,"ui_nd":"2016122117450316","unit_price":12,"up_orderid":"","utime":1499069773000},
		   * 			 {"area_code":"510107","car_code":"川A88888","ctime":1499066792000,"defer_state":0,"endtime":1501572369000,"father_order_id":"","flag":0,"is_del":0,"is_expire":1,"money":100,"month_num":10,"mq_state":0,"note":"","pay_source":0,"pay_state":0,"permit_time":"8：00-23：00","pi_id":36,"pi_name":"","pu_id":1001,"pu_nd":"","rd_id":1,"rent_order_id":"20170703998181293836","rent_type":1,"son_order_id":"","starttime":1499066761000,"stime":1499066798000,"ui_id":54,"ui_nd":"2016122117450316","unit_price":10,"up_orderid":"","utime":1499066801000}
		   *    		],"errorcode":"","errormsg":"道闸租赁续约信息获取成功","errorno":"0"}
		   * </pre>
		   */
		  @Test
		  public void get_park_rentdefer_info() throws Exception {
			  MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
			  params.add("pi_id", "36");
			  params.add("area_code", "510107");
//			  params.add("rent_order_id", "20170703998181293836");
			  params.add("pay_state", "0");
			  params.add("is_expire", "1");
			  params.add("page", "1");
			  params.add("size", "5");
			  params.add("dtype", dtype);
			  
			  sign(params, "dtype","pi_id","area_code");

			  MvcResult mvcResult = mockMvc.perform(post("/v1/get_park_rentdefer_info").params(params))
					  .andExpect(status().isOk()).andReturn();
			  System.err.println(mvcResult.getResponse().getContentAsString());
		  }
	
}
