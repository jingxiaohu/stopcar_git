package com.park.gate.action.v1.order.param;

import com.park.mvc.action.v1.param.BaseParam;

public class Param_order_fake_del extends BaseParam{
	/**
	 * 订单号
	 */
	private String my_order;
	/**
	 * 订单类型 1：用户支付停车下单表  2：用户支付租赁停车下单表
	 */
	private String order_type;
	/**
	 * 支付停车场主键ID
	 */
	private String pi_id;
	/**
	 * 省市县编号
	 */
	private String area_code;
	
	public String getMy_order() {
		return my_order;
	}
	public void setMy_order(String my_order) {
		this.my_order = my_order;
	}
	public String getOrder_type() {
		return order_type;
	}
	public void setOrder_type(String order_type) {
		this.order_type = order_type;
	}
	public String getPi_id() {
		return pi_id;
	}
	public void setPi_id(String pi_id) {
		this.pi_id = pi_id;
	}
	public String getArea_code() {
		return area_code;
	}
	public void setArea_code(String area_code) {
		this.area_code = area_code;
	}
	
}
