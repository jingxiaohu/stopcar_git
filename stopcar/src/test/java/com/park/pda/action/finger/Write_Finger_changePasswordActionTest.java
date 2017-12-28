package com.park.pda.action.finger;

import com.park.action.BaseWebTest;
import org.junit.Test;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 *指纹采集--用户重置密码
 *
 * @author zyy
 */
public class Write_Finger_changePasswordActionTest extends BaseWebTest {


  /**
   * 指纹采集--用户重置密码测试 响应例子
   *
   * 请求参数：
   * "parameter":{"loginName":["20170720105013"],"password":["098f6bcd4621d373cade4e832627b4f6"], "rePassword":["098f6bcd4621d373cade4e832627b4f6"], "dtype":["2"],"sign":["d8e33e39bdebe5de38140ec514f532c9"] }
   *
   * 响应数据：
   * 	{"data":{"address":"24412","age":18,"area_code":"510107","ctime":1488506998000,"link_tel":"12345678910","loginname":"20170720105013","mac":"4243423432432","name":"test001","note":"434","password":"098f6bcd4621d373cade4e832627b4f6","pda_id":0,"pi_id":423,"pui_id":1,"sex":1,"state":0,"tel":"13980460681","utime":1488506998000},"errorcode":"","errormsg":"密码修改成功","errorno":"0"}
   *
   * @throws Exception
   */
  @Test
  public void changePassword() throws Exception {
    MultiValueMap<String, String> params = new LinkedMultiValueMap<>();

    String loginName = "20170720105013";
    String password = "098f6bcd4621d373cade4e832627b4f6";
    String rePassword = "098f6bcd4621d373cade4e832627b4f6";

    params.add("loginName", loginName);
    params.add("password", password);
    params.add("rePassword", rePassword);
    params.add("dtype", dtype);
	  
	sign(params,  "dtype","loginName","password","rePassword");

    MvcResult mvcResult = mockMvc.perform(post("/v1/finger_change_password").params(params))
        .andExpect(status().isOk()).andReturn();
    System.err.println(mvcResult.getResponse().getContentAsString());
  }

}
