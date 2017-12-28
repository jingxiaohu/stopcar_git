package com.park.pda.action.v1.pda.param;

import com.park.mvc.action.v1.param.BaseParam;

public class Param_parkConfirmCarOut extends BaseParam{
	/**
	 * 订单号
	 */
	private String my_order;
	/**
	 * 订单类型 1：用户支付停车下单表  2：用户支付租赁停车下单表
	 */
	private String order_type;
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

	
	
}
