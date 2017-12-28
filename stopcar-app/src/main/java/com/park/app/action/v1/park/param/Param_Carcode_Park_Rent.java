package com.park.app.action.v1.park.param;

import com.park.mvc.action.v1.param.BaseParam;

/**
 * 根据用户车牌查询租赁信息
 * @author zyy
 */
public class Param_Carcode_Park_Rent extends BaseParam{
	
	public String car_code;	//用户车牌号

	public String getCar_code() {
		return car_code;
	}

	public void setCar_code(String car_code) {
		this.car_code = car_code;
	}
	
}