
package com.park.pda.action.pda;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import com.park.action.BaseWebTest;

/**
 * 获取PDA的预约且未付款的订单
 * @author jingxiaohu
 *
 */
public class Read_PDA_ExpectOrderActionTest extends BaseWebTest {
	/**
	 * 获取PDA的预约且未付款的订单
	 * @throws Exception
	 * {"data":[{"address_name":"zxvc","ai_effect":0,"ai_id":0,"ai_money":0,"allege_state":0,"area_code":"510100","arrive_time":1494573035000,"cancel_state":0,"car_code":"甘Z33333","charging":0,"charging_time":0,"ctime":1494573035000,"discount_money":0,"discount_name":"","discount_type":0,"expect_info":"预约提示信息","expect_money":500,"expect_state":2,"expect_time":60,"final_time":0,"free_minute":0,"gov_num":"","id":59897,"is_cash":0,"is_del":0,"is_expect_deduct":0,"is_expect_outtime":0,"is_free_minute":0,"is_open":0,"is_over":0,"lat":30.628884,"leave_time":1494573035000,"lng":104.141085,"magnetic_state":0,"money":0,"my_order":"2017051215103575922","note":"用户预约下单","open_time":1494573035000,"order_type":1,"other_order":"","park_type":0,"pay_source":0,"pay_type":0,"phone_type":0,"pi_id":140,"pi_name":"20170512zf","pp_state":0,"pp_versioncode":"11","pu_id":4,"pu_nd":"20170111152254","scan_time":1494573035000,"start_price":0,"start_time":0,"ui_id":130,"ui_nd":"2017041016176946","ui_tel":"15802854903","upc_id":0,"utime":1494573040000}],"errorcode":"","errormsg":"获取PDA的预约且未付款和未取消的订单","errorno":"0"}
	 */
	@Test
	public void pda_expect_order() throws Exception{
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		long pi_id=140;//停车场主键ID
		String area_code="510100";//省市区区域代码  四川省 成都市 龙泉驿区  510112
		int page  = 1;
		int size = 20;
		
		params.add("dtype", dtype);
		params.add("pi_id", pi_id+"");
		params.add("area_code", area_code);
		params.add("page", page+"");
		params.add("size", size+"");
		
		sign(params, "dtype","pi_id","area_code");
		
		MvcResult mvcResult = mockMvc.perform(post("/v1/pda_expect_order").params(params))
			  .andExpect(status().isOk()).andReturn();
		System.err.println(mvcResult.getResponse().getContentAsString());
	}
}
