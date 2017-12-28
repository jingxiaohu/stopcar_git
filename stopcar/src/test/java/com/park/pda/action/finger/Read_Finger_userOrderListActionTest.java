package com.park.pda.action.finger;

import com.park.action.BaseWebTest;
import org.junit.Test;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 *指纹系统--查询用户已经支付成功的订单流水
 *
 * @author zyy
 */
public class Read_Finger_userOrderListActionTest extends BaseWebTest {


  /**
   * 指纹系统--查询用户已经支付成功的订单流水测试
   *
   * 请求参数：
   * "parameter":{"ui_tel":["15812312312"],"page":["1"], "size":["50"], "dtype":["2"],"sign":["2315125a71f72f392e1f75f8734c8311"] }
   *
   * 响应数据：
   * 	{"data":[{"userOrderList":[{"address_name":"天府软件园C区","ai_effect":0,"ai_id":0,"ai_money":0,"allege_state":0,"area_code":"510107","arrive_time":1486519735000,"cancel_state":0,"car_code":"川A63435","charging":1,"charging_time":60,"ctime":1486519735000,"discount_money":0,"discount_name":"","discount_type":0,"expect_info":"","expect_money":0,"expect_state":0,"expect_time":0,"final_time":0,"free_minute":0,"gov_num":"","id":181,"is_cash":1,"is_del":0,"is_expect_deduct":0,"is_expect_outtime":0,"is_free_minute":0,"is_open":0,"is_over":1,"lat":30.539826,"leave_time":1486519746000,"lng":104.071574,"magnetic_state":0,"money":2,"my_order":"2017020810082676","note":"","open_time":1486519735000,"order_type":0,"other_order":"","park_type":1,"pay_source":8,"pay_type":0,"phone_type":0,"pi_id":29,"pi_name":"软件园c区","pp_state":1,"pp_versioncode":"","pu_id":2,"pu_nd":"20161228091528","scan_time":1486519735000,"start_price":2,"start_time":120,"stime":1486519735000,"ui_id":48,"ui_nd":"2016121910347131","ui_tel":"","up_orderid":"","upc_id":0,"utime":1486519735000}],"car_code":"川A63435"},{"userOrderList":[{"address_name":"成都双流国际机场","ai_effect":0,"ai_id":0,"ai_money":0,"allege_state":0,"area_code":"510116","arrive_time":1501213855000,"cancel_state":0,"car_code":"川ANB817","charging":1,"charging_time":5,"ctime":1501213855000,"discount_money":0,"discount_name":"","discount_type":0,"expect_info":"","expect_money":0,"expect_state":0,"expect_time":0,"final_time":0,"free_minute":6,"gov_num":"","id":150760,"is_cash":0,"is_del":0,"is_expect_deduct":0,"is_expect_outtime":0,"is_free_minute":0,"is_open":0,"is_over":1,"lat":30.559105,"leave_time":1501213903000,"lng":103.951572,"magnetic_state":1,"money":1,"my_order":"510116200000247020170728115054E6","note":"","open_time":1501213855000,"order_type":0,"other_order":"","park_type":1,"pay_source":8,"pay_type":0,"phone_type":0,"pi_id":247,"pi_name":"冰淇淋房","pp_state":1,"pp_versioncode":"","pu_id":38,"pu_nd":"20170629144247","scan_time":1501213855000,"start_price":1,"start_time":1,"stime":1501213855000,"ui_id":0,"ui_nd":"","ui_tel":"","up_orderid":"","upc_id":0,"utime":1501213902000}],"car_code":"川ANB817"},{"userOrderList":[{"address_name":"东郊记忆","ai_effect":0,"ai_id":0,"ai_money":0,"allege_state":0,"area_code":"510100","arrive_time":1499920672000,"cancel_state":3,"car_code":"京A59646","charging":0,"charging_time":0,"ctime":1499920672000,"discount_money":0,"discount_name":"","discount_type":0,"expect_info":"","expect_money":0,"expect_state":0,"expect_time":0,"final_time":0,"free_minute":3,"gov_num":"","id":150678,"is_cash":0,"is_del":0,"is_expect_deduct":0,"is_expect_outtime":0,"is_free_minute":0,"is_open":0,"is_over":4,"lat":30.668776,"leave_time":1499959803000,"lng":104.122892,"magnetic_state":1,"money":1,"my_order":"510100200000253020170713123752DD","note":"","open_time":1499920672000,"order_type":0,"other_order":"","park_type":1,"pay_source":8,"pay_type":0,"phone_type":0,"pi_id":253,"pi_name":"地磁test","pp_state":1,"pp_versioncode":"","pu_id":1,"pu_nd":"20161227141900","scan_time":1499920672000,"start_price":1,"start_time":0,"stime":1499920671000,"ui_id":67,"ui_nd":"2017010317001127","ui_tel":"18113530967","up_orderid":"","upc_id":0,"utime":1501148031000},{"address_name":"成都双流国际机场","ai_effect":0,"ai_id":0,"ai_money":0,"allege_state":0,"area_code":"510116","arrive_time":1501147977000,"cancel_state":0,"car_code":"京A59646","charging":1,"charging_time":5,"ctime":1501147977000,"discount_money":0,"discount_name":"","discount_type":0,"expect_info":"","expect_money":0,"expect_state":0,"expect_time":0,"final_time":0,"free_minute":6,"gov_num":"","id":150744,"is_cash":0,"is_del":0,"is_expect_deduct":0,"is_expect_outtime":0,"is_free_minute":0,"is_open":0,"is_over":1,"lat":30.559105,"leave_time":1501148032000,"lng":103.951572,"magnetic_state":1,"money":1,"my_order":"510116200000247020170727173257AE","note":"","open_time":1501147977000,"order_type":0,"other_order":"","park_type":1,"pay_source":8,"pay_type":0,"phone_type":0,"pi_id":247,"pi_name":"冰淇淋房","pp_state":1,"pp_versioncode":"","pu_id":38,"pu_nd":"20170629144247","scan_time":1501147977000,"start_price":1,"start_time":1,"stime":1501147977000,"ui_id":67,"ui_nd":"2017010317001127","ui_tel":"18113530967","up_orderid":"","upc_id":0,"utime":1501148031000}],"car_code":"京A59646"}],"errorcode":"","errormsg":"获取用户已经支付成功的订单流水成功","errorno":"0"}
   *
   * @throws Exception
   */
  @Test
  public void finger_user_orderList() throws Exception {
    MultiValueMap<String, String> params = new LinkedMultiValueMap<>();

    String ui_tel = "13527385275";
    int page = 1;
    int size = 50;

    params.add("ui_tel", ui_tel);
    params.add("page", page+"");
    params.add("size", size+"");
    params.add("dtype", dtype);
	  
	sign(params,  "dtype","ui_tel");

    MvcResult mvcResult = mockMvc.perform(post("/v1/finger_user_orderList").params(params))
        .andExpect(status().isOk()).andReturn();
    System.err.println(mvcResult.getResponse().getContentAsString());
  }

}
