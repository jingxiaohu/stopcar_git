package com.park.pda.action.pda;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import com.park.action.BaseWebTest;

/**
 * @author Peter Wu
 */
public class Read_PDA_OrderActionTest extends BaseWebTest {

  /**
   * PDA获取某条订单信息
   *
   * <pre>
   *	{"data":{"address_name":"环球中心","ai_effect":0,"ai_id":0,"ai_money":0,"allege_state":0,"area_code":"510100","arrive_time":1494405989000,"cancel_state":2,"car_code":"蒙HAV911","charging":100,"charging_time":60,"ctime":1494405989000,"discount_money":0,"discount_name":"","discount_type":0,"expect_info":"","expect_money":0,"expect_state":0,"expect_time":0,"final_time":7,"free_minute":0,"gov_num":"","id":59529,"is_cash":0,"is_del":0,"is_expect_deduct":0,"is_expect_outtime":0,"is_free_minute":0,"is_open":0,"is_over":0,"lat":30.568925,"leave_time":1494405989000,"lng":104.062809,"magnetic_state":0,"money":500,"my_order":"2017051016462841713","note":"","open_time":1494405989000,"order_type":0,"other_order":"","park_type":0,"pay_source":0,"pay_type":0,"phone_type":0,"pi_id":183,"pi_name":"琦彩测试10","pp_state":0,"pp_versioncode":"","pu_id":30,"pu_nd":"20170427102807","scan_time":1494406470000,"start_price":500,"start_time":120,"ui_id":41,"ui_nd":"2016121214288162","ui_tel":"17608005981","upc_id":0,"utime":1494405989000},"errorcode":"","errormsg":"普通停车订单","errorno":"0"}
   * </pre>
   */
  @Test
  public void read_pda_order() throws Exception {

    MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
    params.add("dtype", dtype);

    params.add("orderid", "2017051016462841713");//获取订单类型  0:普通停车订单  1：租赁停车订单
    params.add("type", "0");//订单号

    sign(params, "dtype", "type", "orderid");

    MvcResult mvcResult = mockMvc.perform(post("/v1/read_pda_order").params(params))
        .andExpect(status().isOk()).andReturn();
    System.err.println(mvcResult.getResponse().getContentAsString());

  }
}
