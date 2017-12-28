package com.park.user.action;

import apidoc.jxh.cn.InterfaceUtil;
import com.park.action.BaseWebTest;
import com.park.mvc.action.v1.param.BaseParam;
import org.junit.Test;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * ETC读取测试
 *
 * @author Peter Wu
 */
public class UserTest extends BaseWebTest {


  /**
   * 获取我的基本信息 响应例子：
   * <pre>
   *   {"data":[{"bank_card_number":"6228482938412183773","bank_type":0,"ctime":1493102652000,"discard_time":1493102652000,"eu_id":3,"eu_nd":"2017042514445156","is_default":1,"is_sign":0,"name":"王小刚2","note":"","sfz_img_url":"http://app.qc-wbo.com/file/img/card/2017/head15882345446_00693.png","sfz_number":"510324198832497533","sign_ip":"","signtime":1493102652000,"ui_id":142,"ui_nd":"2017022015142561","ui_tel":"15882345446","utime":1493102652000},{"bank_card_number":"6228482938412183773","bank_type":0,"ctime":1493103354000,"discard_time":1493103354000,"eu_id":4,"eu_nd":"2017042514550585","is_default":0,"is_sign":0,"name":"王小刚","note":"","sfz_img_url":"http://app.qc-wbo.com/file/img/card/2017/head15882345446_15665.png","sfz_number":"510324198832497533","sign_ip":"","signtime":1493103354000,"ui_id":142,"ui_nd":"2017022015142561","ui_tel":"15882345446","utime":1493103354000}],"errorcode":"","errormsg":"获取用户绑定的ETC银行卡成功","errorno":"0"}
   * </pre>
   */
  @Test
  public void read_myinfo() throws Exception {
    MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
    params.add("dtype", dtype);
    params.add("ui_id", "402");
    sign(params, "dtype", "ui_id");

    MvcResult mvcResult = mockMvc.perform(post("/v1/read_myinfo").params(params))
        .andExpect(status().isOk()).andReturn();
    String result = mvcResult.getResponse().getContentAsString();
    System.err.println(result);
    String filepath = this.getClass().getResource(".").getPath()+"user.md";
    InterfaceUtil.AddInterfacePred(
            filepath,
            "用户管理模块",
            "获取我的基本信息",
            "dtype+ui_id",
            "/v1/read_myinfo.php",1,params, BaseParam.class,result);
  }


}
