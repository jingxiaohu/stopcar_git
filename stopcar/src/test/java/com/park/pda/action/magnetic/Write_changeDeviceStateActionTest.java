package com.park.pda.action.magnetic;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import com.park.action.BaseWebTest;
/**
 * 修改设备状态
 * @ClassName:  ChangeDeviceActionTest   
 * @Description:TODO  
 * @author: xxy 
 * @date:   2017-06-13 17:19:41   
 *{"data":"","errorcode":"","errormsg":"修改成功","errorno":"0"}
 */
public class Write_changeDeviceStateActionTest extends BaseWebTest {
	
	@Test
	public void change_magnetic_device_state() throws Exception{
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();

		long pi_id = 6;//场地主键ID
		String area_code="510107";//省市区区域代码
		
		int car_port_yet = 50;//临停已停车位
		int car_port_space = 50;//临停空车位个数
		int car_port_total = 100;//临停总车位个数
		
		String car_dev_num = "a235";//车位设备编码
		String android_dev_num = "a333";////android板子设备编码
		Integer state =0;//车位设备状态（0：无车 1：有车 2：故障）
		
		Long ctime = 1197598924897L;

		params.add("pi_id", pi_id+"");
		params.add("area_code", area_code);
		params.add("car_port_yet", car_port_yet+"");
		params.add("car_port_space", car_port_space+"");
		params.add("car_port_total", car_port_total+"");
		params.add("car_dev_num", car_dev_num+"");
		params.add("state", state+"");
		params.add("android_dev_num", android_dev_num+"");
		params.add("ctime", ctime+"");

		sign(params, "pi_id","area_code","car_dev_num","car_port_yet","car_port_space","car_port_total","state","android_dev_num");

	    MvcResult mvcResult = mockMvc.perform(post("/v1/change_magnetic_device_state").params(params))
	        .andExpect(status().isOk()).andReturn();
	    System.err.println(mvcResult.getResponse().getContentAsString());
	}
		
}
