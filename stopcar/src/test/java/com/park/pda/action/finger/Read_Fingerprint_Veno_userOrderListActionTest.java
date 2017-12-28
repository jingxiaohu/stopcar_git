package com.park.pda.action.finger;

import com.park.action.BaseWebTest;
import org.junit.Test;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 *指纹系统--查询用户已经支付成功的订单流水(指纹/指静脉)
 *
 * @author zyy
 */
public class Read_Fingerprint_Veno_userOrderListActionTest extends BaseWebTest {


  /**
   * 指纹系统--查询用户已经支付成功的订单流水测试
   *
   * 请求参数：
   * "parameter":{
   *    "ui_tel":["15828670000"],
   *    "page":["1"],
   *    "size":["50"],
   *   "dtype":["2"],
   *   "sign":["659d984c9c050f3c009327bfaa7b1947"]
   * }
   *
   * 响应数据：
   * 	{"data":[{"userOrderList":[{"address_name":"天府软件园C区","ai_effect":0,"ai_id":0,"ai_money":0,"allege_state":0,"area_code":"510107","arrive_time":1485230287000,"cancel_state":0,"car_code":"京A26666","charging":1,"charging_time":60,"ctime":1485230287000,"discount_money":0,"discount_name":"","discount_type":0,"expect_info":"","expect_money":0,"expect_state":0,"expect_time":0,"final_time":0,"free_minute":0,"gov_num":"","id":32,"is_cash":0,"is_del":0,"is_expect_deduct":0,"is_expect_outtime":0,"is_free_minute":0,"is_open":0,"is_over":3,"lat":30.539826,"leave_time":1485230456000,"lng":104.071574,"magnetic_state":0,"money":2,"my_order":"2017012411585796","note":"","open_time":1485230287000,"order_type":0,"other_order":"","park_type":1,"pay_source":9,"pay_type":0,"phone_type":0,"pi_id":29,"pi_name":"软件园c区","pp_state":1,"pp_versioncode":"","pu_id":2,"pu_nd":"20161228091528","scan_time":1485230287000,"start_price":2,"start_time":120,"stime":1485230287000,"ui_id":51,"ui_nd":"2016121910525321","ui_tel":"","up_orderid":"","upc_id":0,"utime":1485230309000},{"address_name":"天府软件园C区","ai_effect":0,"ai_id":0,"ai_money":0,"allege_state":0,"area_code":"510107","arrive_time":1485225574000,"cancel_state":0,"car_code":"京A26666","charging":1,"charging_time":60,"ctime":1485225574000,"discount_money":0,"discount_name":"","discount_type":0,"expect_info":"","expect_money":0,"expect_state":0,"expect_time":0,"final_time":0,"free_minute":0,"gov_num":"","id":30,"is_cash":0,"is_del":0,"is_expect_deduct":0,"is_expect_outtime":0,"is_free_minute":0,"is_open":0,"is_over":3,"lat":30.539826,"leave_time":1485225696000,"lng":104.071574,"magnetic_state":0,"money":2,"my_order":"2017012410393161","note":"","open_time":1485225574000,"order_type":0,"other_order":"","park_type":1,"pay_source":8,"pay_type":0,"phone_type":0,"pi_id":29,"pi_name":"软件园c区","pp_state":1,"pp_versioncode":"","pu_id":2,"pu_nd":"20161228091528","scan_time":1485225574000,"start_price":2,"start_time":120,"stime":1485225574000,"ui_id":51,"ui_nd":"2016121910525321","ui_tel":"","up_orderid":"","upc_id":0,"utime":1485229610000}],"car_code":"京A26666"},{"userOrderList":[{"address_name":"天府软件园C区","ai_effect":0,"ai_id":0,"ai_money":0,"allege_state":0,"area_code":"510107","arrive_time":1485225696000,"cancel_state":2,"car_code":"京A36666","charging":1,"charging_time":60,"ctime":1485225696000,"discount_money":0,"discount_name":"","discount_type":0,"expect_info":"","expect_money":0,"expect_state":0,"expect_time":0,"final_time":0,"free_minute":0,"gov_num":"","id":31,"is_cash":0,"is_del":0,"is_expect_deduct":0,"is_expect_outtime":0,"is_free_minute":0,"is_open":0,"is_over":0,"lat":30.539826,"leave_time":1485225696000,"lng":104.071574,"magnetic_state":0,"money":1,"my_order":"2017012410412561","note":"","open_time":1485225696000,"order_type":0,"other_order":"","park_type":1,"pay_source":9,"pay_type":0,"phone_type":0,"pi_id":29,"pi_name":"软件园c区","pp_state":1,"pp_versioncode":"","pu_id":2,"pu_nd":"20161228091528","scan_time":1485225696000,"start_price":2,"start_time":120,"stime":1485225696000,"ui_id":51,"ui_nd":"2016121910525321","ui_tel":"","up_orderid":"","upc_id":0,"utime":1485225696000}],"car_code":"京A36666"}],"errorcode":"","errormsg":"获取用户已经支付成功的订单流水成功","errorno":"0"}
   *
   * @throws Exception
   */
  @Test
  public void finger_veno_user_orderList() throws Exception {
    MultiValueMap<String, String> params = new LinkedMultiValueMap<>();

    String ui_tel = "15828671698";
    int page = 1;
    int size = 50;

    params.add("ui_tel", ui_tel);
    params.add("page", page+"");
    params.add("size", size+"");
    params.add("dtype", dtype);
	  
	sign(params,  "dtype","ui_tel");

    MvcResult mvcResult = mockMvc.perform(post("/v1/finger_veno_user_orderList").params(params))
        .andExpect(status().isOk()).andReturn();
    System.err.println(mvcResult.getResponse().getContentAsString());
  }

}
