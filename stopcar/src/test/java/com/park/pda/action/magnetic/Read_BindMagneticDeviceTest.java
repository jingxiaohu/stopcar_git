package com.park.pda.action.magnetic;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.park.action.BaseWebTest;
import org.junit.Test;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

/**
 *设备编号管理
 *
 * @author zyy
 */
public class Read_BindMagneticDeviceTest extends BaseWebTest {
  
  /**
   * 按设备编号查询测试  响应例子
   * <pre>
   * 	{"data":[{"android_dev_num":"a333","area_code":"510107","car_dev_num":"a234","ctime":1498446286000,"fault_count":0,"gov_num":"a123","id":4,"is_del":0,"note":"","pi_id":1001,"ptime":1498446286000,"state":0,"utime":1498447931000},{"android_dev_num":"a3333","area_code":"510107","car_dev_num":"a2222","ctime":1498458241000,"fault_count":0,"gov_num":"asd123","id":7,"is_del":0,"note":"","pi_id":1001,"ptime":1498458241000,"state":0,"utime":1498465291000},{"android_dev_num":"a3333","area_code":"510107","car_dev_num":"a2222","ctime":1498459759000,"fault_count":0,"gov_num":"asd123","id":8,"is_del":0,"note":"","pi_id":1001,"ptime":1498459759000,"state":0,"utime":1498460519000},{"android_dev_num":"a3333","area_code":"510107","car_dev_num":"a1111","ctime":1498464344000,"fault_count":0,"gov_num":"asd123","id":9,"is_del":0,"note":"","pi_id":1001,"ptime":1498464344000,"state":0,"utime":1498464344000}],"errorcode":"","errormsg":"读取绑定的设备编号成功","errorno":"0"}
   * </pre>
   */
  @Test
  public void read_bindMagneticDevice() throws Exception {
	  MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
	  params.add("pi_id", "1001");
	  params.add("area_code", "510107");
	  params.add("dtype", dtype);
	  
	  sign(params,  "pi_id","area_code");

	  MvcResult mvcResult = mockMvc.perform(post("/v1/read_bind_magnetic_device").params(params))
			  .andExpect(status().isOk()).andReturn();
	  System.err.println(mvcResult.getResponse().getContentAsString());
  }

}
