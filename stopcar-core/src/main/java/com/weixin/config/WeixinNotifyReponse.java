package com.weixin.config;

import java.io.Serializable;

/**
 * 微信下单返回数据BEAN
 * @author jingxiaohu
 *
 */
public class WeixinNotifyReponse implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2396533929831983137L;
	/**
	 * 
	 */
	public String return_msg;
	public String return_code;
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
