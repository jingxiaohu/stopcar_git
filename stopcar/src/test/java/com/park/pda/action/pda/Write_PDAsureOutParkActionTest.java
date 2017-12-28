package com.park.pda.action.pda;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import com.park.action.BaseWebTest;

/**
 * 露天停车场的PDA更新用户自动支付
 * @author jingxiaohu
 *
 */
public class Write_PDAsureOutParkActionTest extends BaseWebTest {
	/**
	 * 1：检查某停车场某车牌号是否已经付款
	 * {"data":"{\"address_name\":\"环球中心\",\"ai_effect\":0,\"ai_id\":0,\"ai_money\":0,\"allege_state\":0,\"area_code\":\"510100\",\"arrive_time\":1494993372000,\"cancel_state\":2,\"car_code\":\"鲁HTS521\",\"charging\":2,\"charging_time\":60,\"ctime\":1494993372000,\"discount_money\":0,\"discount_name\":\"\",\"discount_type\":0,\"expect_info\":\"\",\"expect_money\":0,\"expect_state\":0,\"expect_time\":0,\"final_time\":0,\"free_minute\":0,\"gov_num\":\"\",\"id\":60333,\"is_cash\":0,\"is_del\":0,\"is_expect_deduct\":0,\"is_expect_outtime\":0,\"is_free_minute\":0,\"is_open\":0,\"is_over\":1,\"lat\":30.56874,\"leave_time\":1496711411568,\"lng\":104.063401,\"magnetic_state\":0,\"money\":0,\"my_order\":\"2017051711561157205\",\"note\":\"\",\"open_time\":1494993372000,\"order_type\":0,\"other_order\":\"\",\"park_type\":0,\"pay_source\":4,\"pay_type\":0,\"phone_type\":0,\"pi_id\":196,\"pi_name\":\"测试135\",\"pp_state\":1,\"pp_versioncode\":\"\",\"pu_id\":5,\"pu_nd\":\"20170112152254\",\"scan_time\":1494993372000,\"start_price\":2,\"start_time\":60,\"ui_id\":137,\"ui_nd\":\"2017041814497751\",\"ui_tel\":\"18328576809\",\"upc_id\":0,\"utime\":1496711411568}","errorcode":"","errormsg":"处理成功","errorno":"0"}
	 * @throws Exception
	 */
	@Test
	public void pda_sure() throws Exception{
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		
		long pi_id = 196;//场地主键ID
		String car_code = "鲁HTS521";//车牌号
		String area_code="510100";//省市区区域代码
		String orderid="2017051711561157205";//我们的订单号  字符串
		String pay_type="0";
		int money=0;//金额（ 单位分）
		
		params.add("pi_id", pi_id+"");
		params.add("car_code", car_code);
		params.add("area_code", area_code);
		params.add("orderid", orderid);
		params.add("pay_type", pay_type);
		params.add("money", money+"");
		params.add("dtype", dtype);
		
	    sign(params, "dtype","orderid","pi_id");

	    MvcResult mvcResult = mockMvc.perform(post("/v1/pda_sure").params(params))
	        .andExpect(status().isOk()).andReturn();
	    System.err.println(mvcResult.getResponse().getContentAsString());
	}
}
