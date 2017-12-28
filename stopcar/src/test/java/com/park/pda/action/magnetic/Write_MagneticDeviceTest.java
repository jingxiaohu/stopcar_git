package com.park.pda.action.magnetic;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import com.park.action.BaseWebTest;

/**
 *设备编号管理
 *
 * @author zyy
 */
public class Write_MagneticDeviceTest extends BaseWebTest {


  /**
   * 设备编号绑定测试 响应例子
   * <pre>
   * 	{"data":{"android_dev_num":"a3333","area_code":"510107","car_dev_num":"a1111","ctime":1498458241937,"fault_count":0,"gov_num":"asd123","id":0,"is_del":0,"note":"","pi_id":1001,"ptime":1498458241937,"state":0,"utime":1498458241937},"errorcode":"","errormsg":"设备编号绑定成功","errorno":"0"}
   * </pre>
   */
  @Test
  public void bindMagneticDevice() throws Exception {
    MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
    params.add("pi_id", "1001");
    params.add("area_code", "510107");
    params.add("gov_num", "asd123");
    params.add("car_dev_num", "a1111");
    params.add("android_dev_num", "a3333");
	  
	sign(params,  "pi_id","area_code","gov_num","car_dev_num");

    MvcResult mvcResult = mockMvc.perform(post("/v1/bind_magnetic_device").params(params))
        .andExpect(status().isOk()).andReturn();
    System.err.println(mvcResult.getResponse().getContentAsString());
  }

  /**
   * 设备编号修改测试 响应例子
   * <pre>
   * 	{"data":"","errorcode":"","errormsg":"设备编号修改成功","errorno":"0"}
   * </pre>
   */
  @Test
  public void update_magneticDevice() throws Exception {
    MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
    params.add("pi_id", "1001");
    params.add("area_code", "510107");
    params.add("gov_num", "asd123");
    params.add("car_dev_num", "a2222");
	  
	sign(params, "pi_id","area_code","gov_num","car_dev_num");

    MvcResult mvcResult = mockMvc.perform(post("/v1/update_magnetic_device").params(params))
        .andExpect(status().isOk()).andReturn();
    System.err.println(mvcResult.getResponse().getContentAsString());
  }
  
  /**
   * 设备编号逻辑删除测试 响应例子
   * <pre>
   * 	{"data":"","errorcode":"","errormsg":"设备编号删除成功","errorno":"0"}
   * </pre>
   */
  @Test
  public void delete_magneticDevice() throws Exception {
	  MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
	  params.add("pi_id", "1001");
	  params.add("area_code", "510107");
	  params.add("gov_num", "asd123");
	  params.add("car_dev_num", "a2222");
	  params.add("dtype", dtype);
	  
	  sign(params, "dtype","pi_id","area_code","gov_num");
	  
	  MvcResult mvcResult = mockMvc.perform(post("/v1/delete_magnetic_device").params(params))
			  .andExpect(status().isOk()).andReturn();
	  System.err.println(mvcResult.getResponse().getContentAsString());
  }
  
}
