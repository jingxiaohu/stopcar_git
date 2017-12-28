package com.park.pda.action.pda;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import com.park.action.BaseWebTest;

/**
 * 停车场硬件设备校验登录MAC地址，并返回停车场信息。第一次登录时初始化 MAC
 * @author zyy
 */
public class Write_PDA_Park_HardwareActionTest extends BaseWebTest {

	/**
	 * 停车场硬件设备校验登录MAC地址，并返回停车场信息   响应例子
	 * <pre>
	 *    {"data":
	 *    	{"park_hardware_info":
	 *    		{"area_code":"500000","ctime":1497582545000,"note":"","park_type":0,"ph_id":4,"ph_licence":"","ph_loginname":"20170613111717","ph_mac":"869449020404324","ph_password":"","ph_state":0,"pi_id":67,"token":"ac0a8e841e184567be6fb340fdc79fba","type":0,"utime":1497582545000},
	 *    	"park_info":{"address_name":"重庆北站","admin_id":3,"allow_expect_time":60,"allow_revoke_time":5,"area_code":"500000","camera_info":"火眼","carport_space":3,"carport_total":3,"carport_yet":0,"copy_linkman_name":"xxx","copy_linkman_tel":"15802854903","ctime":1494842112000,"department":"重庆飞机场","enter_camera_num":1,"enter_num":1,"exit_camera_num":1,"exit_num":1,"expect_car_num":0,"expect_money":0,"hardware_type":0,"hlc_enter_num":1,"hlc_exit_num":1,"is_expect":0,"is_fault":1,"is_rent":0,"lat":29.609594,"linkman_name":"xxx","linkman_tel":"15802854903","lng":106.551166,"loginname":"","mac":"","money":100,"month_price":0,"moth_car_num":2,"moth_car_num_space":2,"note":"","park_type":0,"password":"","pda_permit_time":"","pi_id":67,"pi_name":"重庆飞机场","pi_phone":"15802854903","pi_state":1,"pu_id":0,"pu_nd":"","rent_info":"","roadside_type":0,"special_ip":"","time_car_num":0,"time_car_num_space":0,"timeout_info":"首停2.00小时1.00元，之后每小时1.00元","token":"","upload_source":0,"utime":1494844051000}
	 *    },"errorcode":"","errormsg":"根据硬件设备mac地址获取停车场信息成功","errorno":"0"}
	 * </pre>
	 */
	@Test
	public void getparkinfo_by_hardware_mac() throws Exception {
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		
		String ph_mac = "869449020404324";
		String ph_loginname = "20170613111717";
		String ph_password = "e10adc3949ba59abbe56e057f20f883e";
		
		params.add("dtype", dtype);
		params.add("ph_mac", ph_mac);
		params.add("ph_loginname", ph_loginname);
		params.add("ph_password", ph_password);

		sign(params,"dtype", "ph_loginname", "ph_password");

		MvcResult mvcResult = mockMvc.perform(post("/v1/getparkinfo_by_hardware_mac").params(params))
				.andExpect(status().isOk()).andReturn();
		System.err.println(mvcResult.getResponse().getContentAsString());
	}
	
}
