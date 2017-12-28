package com.park.action;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

/**
 * ETC读取测试
 *
 * @author Peter Wu
 */
public class ETCTest extends BaseWebTest {


  /**
   * 获取用户绑定的ETC银行卡 响应例子：
   * <pre>
   *   {"data":[{"bank_card_number":"6228482938412183773","bank_type":0,"ctime":1493102652000,"discard_time":1493102652000,"eu_id":3,"eu_nd":"2017042514445156","is_default":1,"is_sign":0,"name":"王小刚2","note":"","sfz_img_url":"http://app.qc-wbo.com/file/img/card/2017/head15882345446_00693.png","sfz_number":"510324198832497533","sign_ip":"","signtime":1493102652000,"ui_id":142,"ui_nd":"2017022015142561","ui_tel":"15882345446","utime":1493102652000},{"bank_card_number":"6228482938412183773","bank_type":0,"ctime":1493103354000,"discard_time":1493103354000,"eu_id":4,"eu_nd":"2017042514550585","is_default":0,"is_sign":0,"name":"王小刚","note":"","sfz_img_url":"http://app.qc-wbo.com/file/img/card/2017/head15882345446_15665.png","sfz_number":"510324198832497533","sign_ip":"","signtime":1493103354000,"ui_id":142,"ui_nd":"2017022015142561","ui_tel":"15882345446","utime":1493103354000}],"errorcode":"","errormsg":"获取用户绑定的ETC银行卡成功","errorno":"0"}
   * </pre>
   */
  @Test
  public void read_etc_user() throws Exception {
    MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
    params.add("dtype", dtype);
    params.add("ui_id", "402");
    sign(params, "dtype", "ui_id");

    MvcResult mvcResult = mockMvc.perform(post("/v1/read_etc_user").params(params))
        .andExpect(status().isOk()).andReturn();
    System.err.println(mvcResult.getResponse().getContentAsString());
  }

  /**
   * ETC设置默认银行卡
   * 响应例子：
   * <pre>
   * {"data":"","errorcode":"","errormsg":"ETC设置默认银行卡成功","errorno":"0"}
   * </pre>
   */
  @Test
  public void etc_user_set_default() throws Exception {
    MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
    params.add("dtype", dtype);
    params.add("ui_id", "132");
    params.add("eu_id", "4");
    sign(params, "dtype", "ui_id", "eu_id");

    MvcResult mvcResult = mockMvc.perform(post("/v1/etc_user_set_default").params(params))
        .andExpect(status().isOk()).andReturn();
    System.err.println(mvcResult.getResponse().getContentAsString());
  }

  @Test
  public void etc_user_del() throws Exception {

    MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
    params.add("dtype", dtype);
    params.add("ui_id", "132");
    params.add("eu_id", "2");
    sign(params, "dtype", "ui_id", "eu_id");

    MvcResult mvcResult = mockMvc.perform(post("/v1/etc_user_del").params(params))
        .andExpect(status().isOk()).andReturn();
    System.err.println(mvcResult.getResponse().getContentAsString());
  }


  @Test
  public void give_coupon() throws Exception {

    MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
    params.add("dtype", dtype);
    params.add("ui_id", "40");
    params.add("from_ui_id", "401");
    params.add("upc_id", "45385");
    sign(params, "dtype", "ui_id", "upc_id","from_ui_id");

    MvcResult mvcResult = mockMvc.perform(post("/v1/give_coupon").params(params))
        .andExpect(status().isOk()).andReturn();
    System.err.println(mvcResult.getResponse().getContentAsString());
  }
}
