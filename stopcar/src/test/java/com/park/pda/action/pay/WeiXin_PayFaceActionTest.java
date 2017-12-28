
package com.park.pda.action.pay;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import com.park.action.BaseWebTest;

/**
 * 微信 -- 用户充值
 *
 * @author jingxiaohu
 */
public class WeiXin_PayFaceActionTest extends BaseWebTest {

  /**
   * {"data":{"note":"","referer":"","escape_orderids":"","subject":"吾泊扫码支付","ui_id":2,"sign":"","type":2,"ctime":1496805300142,"return_url":"","tel":"","id":1255,"state":0,"timestamp":"2017-06-07 11:15:00","transaction_id":"","ui_nd":"","utime":1496805300142,"ip":"127.0.0.1","version_code":2,"car_order_id":"","money":1,"system_type":4,"etime":1496805300142,"orderInfo":"weixin://wxpay/bizpayurl?pr=3ikXntx","act_type":1,"order_id":"2017060711150033395"},"errorcode":"","errormsg":"微信充值成功","errorno":"0"}
   */
  @Test
  public void weixin_charge_face() throws Exception {
    
    MultiValueMap<String, String> params = new LinkedMultiValueMap<>();

    int pay_type=2;//支付类型 1:支付宝  2：微信  3：银联  4：钱包 5:龙支付 ',
	int type =1;//是支付 还是 充值  1：充值  2：普通订单支付  3：租赁订单支付
	int pay_price=1;//充值金额 单位 分
	int version=2;//当前版本编号
	int ui_id = 2;
	String subject="吾泊充值";//商品名称
	int system_type=4;//操作系统类型（IOS Android web） 1:android 2:IOS 3:web
	long t=System.currentTimeMillis();//时间戳ms
	String token="pda";//令牌
	//收银台页面上，商品展示的超链接，必填
	String show_url ="";
	//页面跳转同步通知页面路径
	String return_url = "";
	
	params.add("ui_id", ui_id + "");
	params.add("pay_type", pay_type + "");
    params.add("pay_price", pay_price + "");
    params.add("version", version + "");
    params.add("subject", subject + "");
    params.add("system_type", system_type + "");
    params.add("t", t + "");
    params.add("token", token);
    params.add("type", type + "");
    params.add("show_url", show_url);
    params.add("return_url", return_url);
    params.add("dtype", dtype);
	
	
    sign(params, "dtype","ui_id","pay_type","pay_price","t","token");
    
    MvcResult mvcResult = mockMvc.perform(post("/v1/weixin_charge_face").params(params))
        .andExpect(status().isOk()).andReturn();
    System.err.println(mvcResult.getResponse().getContentAsString());
    
  }
}
