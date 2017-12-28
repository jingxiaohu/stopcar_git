package com.park.app.action.v1.rentdefer.param;

import com.park.mvc.action.v1.param.BaseParam;

/**
 * app续租--根据续租订单编号获取续租订单
 * @author zyy
 *
 */
public class Param_rentDefer_one_order extends BaseParam {
	public String rent_order_id;//租赁订单号

	public String getRent_order_id() {
		return rent_order_id;
	}

	public void setRent_order_id(String rent_order_id) {
		this.rent_order_id = rent_order_id;
	}
}
