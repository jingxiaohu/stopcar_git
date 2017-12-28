package com.weixin.config;

/**
 * 微信下单返回数据BEAN
 *
 * @author jingxiaohu
 */
public class WeixinResult extends WeiXinBean {

  /**
   *
   */
  private static final long serialVersionUID = -590896542690348561L;
  public String prepay_id;//
  public String result_code;
  public String return_msg;
  public String return_code;

  public String getPrepay_id() {
    return prepay_id;
  }

  public void setPrepay_id(String prepay_id) {
    this.prepay_id = prepay_id;
  }

  public String getResult_code() {
    return result_code;
  }

  public void setResult_code(String result_code) {
    this.result_code = result_code;
  }

  public String getReturn_msg() {
    return return_msg;
  }

  public void setReturn_msg(String return_msg) {
    this.return_msg = return_msg;
  }

  public String getReturn_code() {
    return return_code;
  }

  public void setReturn_code(String return_code) {
    this.return_code = return_code;
  }


}
