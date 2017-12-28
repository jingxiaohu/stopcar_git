package com.alipay.vo;
/**
 * 吾泊支付宝手机支付参数BEAN
 * @author jingxiaohu
 *
 */
public class AlipayAppBean {
	//超时表达
	private String timeout_express="30m";
	//产品代码
	private String product_code="QUICK_MSECURITY_PAY";
	//商品金额
	private String total_amount;
	//商品名称
	private String subject="吾泊停车";
	//介绍
	private String body="";
	//我们自己的订单
	private String out_trade_no;
	//商户ID
	private String seller_id;
	//是支付 还是 充值  1：充值  2：普通订单支付  3：租赁订单支付
	private int type;
	
	
	
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getSeller_id() {
		return seller_id;
	}
	public void setSeller_id(String seller_id) {
		this.seller_id = seller_id;
	}
	public String getTimeout_express() {
		return timeout_express;
	}
	public void setTimeout_express(String timeout_express) {
		this.timeout_express = timeout_express;
	}
	public String getProduct_code() {
		return product_code;
	}
	public void setProduct_code(String product_code) {
		this.product_code = product_code;
	}

	public String getTotal_amount() {
		return total_amount;
	}

	public void setTotal_amount(String total_amount) {
		this.total_amount = total_amount;
	}

	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public String getOut_trade_no() {
		return out_trade_no;
	}
	public void setOut_trade_no(String out_trade_no) {
		this.out_trade_no = out_trade_no;
	}
	
	
	
	
}
