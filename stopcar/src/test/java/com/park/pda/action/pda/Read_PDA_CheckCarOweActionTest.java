package com.park.pda.action.pda;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import com.park.action.BaseWebTest;

/**
 *PDA测试
 *
 * @author Peter Wu
 */
public class Read_PDA_CheckCarOweActionTest extends BaseWebTest {


  /**
   * 获取PDA的对应的车欠费记录 响应例子：
   * <pre>
   * {"data":[{"address_name":"西华大学(西南门)","ai_effect":0,"ai_id":0,"ai_money":0,"allege_state":0,"area_code":"510124","arrive_time":1489115776000,"cancel_state":2,"car_code":"川ASD677","charging":400,"charging_time":60,"ctime":1489115776000,"discount_money":0,"discount_name":"","discount_type":0,"expect_info":"","expect_money":0,"expect_state":0,"expect_time":0,"final_time":0,"free_minute":0,"gov_num":"","id":870,"is_cash":0,"is_del":0,"is_expect_deduct":0,"is_expect_outtime":0,"is_free_minute":0,"is_open":0,"is_over":3,"lat":30.775522,"leave_time":1489115776000,"lng":103.947136,"magnetic_state":0,"money":400,"my_order":"2017031011168836","note":"","open_time":1489115776000,"order_type":0,"other_order":"","park_type":1,"pay_source":0,"pay_type":0,"phone_type":0,"pi_id":66,"pi_name":"西华大学西门","pp_state":0,"pp_versioncode":"","pu_id":2,"pu_nd":"20161228091528","scan_time":1489115776000,"start_price":400,"start_time":60,"ui_id":0,"ui_nd":"","ui_tel":"","upc_id":0,"utime":1489116445000}],"errorcode":"","errormsg":"是欠费车辆","errorno":"0"}
   * </pre>
   */
  @Test
  public void pda_check_car_owe() throws Exception {
    MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
    
	long pi_id = 66;//场地主键ID
	String area_code="510124";//area_code;//省市县编号 510122
	String car_code = "川ASD677";//车牌号 (第一期：车牌号---对应一个人)
	Integer type = 1;//是否是同省跨商户类型： 0 ：同商户 1：同省跨商户
	
	params.add("area_code", area_code);
	params.add("pi_id", pi_id+"");
	params.add("car_code", car_code);
	params.add("type", type+"");
	params.add("dtype", dtype);
	 
//	String sign_str = getSignature(Constants.SYSTEM_KEY, param.dtype,param.pi_id,param.area_code,param.type);
    sign(params, "dtype","pi_id","area_code","type");
    
    MvcResult mvcResult = mockMvc.perform(post("/v1/pda_check_car_owe").params(params))
        .andExpect(status().isOk()).andReturn();
    System.err.println(mvcResult.getResponse().getContentAsString());
  }



}
