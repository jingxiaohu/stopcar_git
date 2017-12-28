
package com.park.pda.action.pda;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.junit.Test;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import com.park.action.BaseWebTest;
/**
 * 获取PDA的对应的车是否是逃逸车辆或者未交费车辆
 * @author jingxiaohu
 *
 */
public class Read_PDA_CheckEscapeCarActionTest extends BaseWebTest {
	/**
	 * {"data":[{"address_name":"四川省成都市武侯区桂溪街道天府四街189号","ai_effect":0,"ai_id":0,"ai_money":0,"allege_state":0,"area_code":"510107","arrive_time":1492496520000,"cancel_state":0,"car_code":"川A00003","charging":1,"charging_time":1,"ctime":1492496520000,"discount_money":0,"discount_name":"","discount_type":0,"expect_info":"","expect_money":0,"expect_state":0,"expect_time":0,"final_time":0,"free_minute":2,"gov_num":"","id":6668,"is_cash":0,"is_del":0,"is_expect_deduct":0,"is_expect_outtime":0,"is_free_minute":0,"is_open":0,"is_over":3,"lat":30.541333,"leave_time":1492507208000,"lng":104.064674,"magnetic_state":0,"money":200,"my_order":"2017041814269313","note":"","open_time":1492496520000,"order_type":0,"other_order":"","park_type":1,"pay_source":0,"pay_type":0,"phone_type":0,"pi_id":114,"pi_name":"长虹科技大厦","pp_state":0,"pp_versioncode":"","pu_id":21,"pu_nd":"20170313164242","scan_time":1492496520000,"start_price":1,"start_time":1,"ui_id":94,"ui_nd":"2017030311410801","ui_tel":"18180889270","upc_id":0,"utime":1492507208000}],"errorcode":"","errormsg":"是未交费车辆","errorno":"0"}
	 * 获取PDA的对应的车是否是逃逸车辆或者未交费车辆
	 * @throws Exception
	 */
	@Test
	public void pda_check_escape_car() throws Exception {
		  MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		  long pi_id=64;//停车场主键ID
			String area_code="500112";//省市区区域代码  四川省 成都市 龙泉驿区  510112
		  String car_code = "川A00003";//车牌号 (第一期：车牌号---对应一个人)
		  Integer type = 1;//是检查 逃逸逃逸车辆 还是 未支付车辆   0：默认检查逃逸车辆  1：检查未支付车辆
		  
		  params.add("dtype", dtype);
		  params.add("pi_id", pi_id+"");     
		  params.add("area_code", area_code);
		  params.add("car_code", car_code);  
		  params.add("type", type+"");
		  
		  sign(params,"dtype", "pi_id","area_code","type");
		  
		  MvcResult mvcResult = mockMvc.perform(post("/v1/pda_check_escape_car").params(params))
				  .andExpect(status().isOk()).andReturn();
		  System.err.println(mvcResult.getResponse().getContentAsString());
	}
	
}
