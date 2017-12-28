package com.park.app.action.v1.rentdefer.param;

import com.park.mvc.action.v1.param.BaseParam;

/**
 * app续约租赁订单管理
 * @author zyy
 *
 */
public class Param_Rent_Defer extends BaseParam {
	public Long pi_id;//停车场主键ID
	
	public String area_code;//停车场地址编码

	public Long getPi_id() {
		return pi_id;
	}

	public void setPi_id(Long pi_id) {
		this.pi_id = pi_id;
	}

	public String getArea_code() {
		return area_code;
	}

	public void setArea_code(String area_code) {
		this.area_code = area_code;
	}
	
	
}
