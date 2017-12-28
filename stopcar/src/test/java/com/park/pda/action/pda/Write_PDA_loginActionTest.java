package com.park.pda.action.pda;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import com.park.action.BaseWebTest;

/**
 * 占道停车场PDA登录
 * @author jingxiaohu
 *
 */
public class Write_PDA_loginActionTest extends BaseWebTest {
	/**
	 * 占道停车场PDA登录
	 * @throws Exception
	 * {"data":{"pda_info":{"area_code":"510107","ctime":1490754633000,"is_initialize":1,"lat":30.560238,"lng":104.049222,"loginname":"510107567270","mac":"869612021322674","note":"zhou","password":"e10adc3949ba59abbe56e057f20f883e","pda_c_id":5,"pda_id":2,"pda_sn":"2017032910302370","pi_id":113,"pi_name":"天府一街","plate_license":"UQ7I1YTSTFQYWI2YY1ZCYYNSE","pu_id":23,"pu_nd":"20170315105816","token":"","utime":1494918539000,"vnum":21},"park_info":{"address_name":"四川省成都市武侯区石羊场街道天府一街铁像寺水街","admin_id":22,"allow_expect_time":0,"allow_revoke_time":0,"area_code":"510107","camera_info":"1111111111111","carport_space":0,"carport_total":0,"carport_yet":0,"copy_linkman_name":"xxx","copy_linkman_tel":"15802854903","ctime":1490929067000,"department":"天府一街","enter_camera_num":1,"enter_num":1,"exit_camera_num":1,"exit_num":1,"expect_car_num":0,"expect_money":0,"hlc_enter_num":1,"hlc_exit_num":1,"is_expect":0,"is_fault":0,"is_rent":0,"lat":30.560238,"linkman_name":"xxx","linkman_tel":"15802854903","lng":104.049222,"loginname":"510107567270","mac":"869612021322674","money":1,"month_price":0,"moth_car_num":0,"moth_car_num_space":0,"note":"11","park_type":1,"password":"e10adc3949ba59abbe56e057f20f883e","pda_permit_time":"09:10-09:40","pi_id":113,"pi_name":"天府一街","pi_phone":"15802854903","pi_state":1,"pu_id":23,"pu_nd":"20170315105816","rent_info":"","roadside_type":1,"special_ip":"","time_car_num":0,"time_car_num_space":0,"timeout_info":"单次0.01元","token":"","upload_source":0,"utime":1494985269000}},"errorcode":"","errormsg":"登录成功","errorno":"0"}
	 */
	@Test
	public void pda_login() throws Exception{
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();

		String mac="869612024544878";
		String loginname="20170331105013";
		String password="e10adc3949ba59abbe56e057f20f883e";
		int vnum = 1; 
		
		params.add("dtype", dtype);
		params.add("mac", mac);
		params.add("loginname", loginname);
		params.add("password", password);
		params.add("vnum", vnum+"");
		 
	    sign(params, "dtype","mac","loginname","password");

	    MvcResult mvcResult = mockMvc.perform(post("/v1/pda_login").params(params))
	        .andExpect(status().isOk()).andReturn();
	    System.err.println(mvcResult.getResponse().getContentAsString());
	}
}
