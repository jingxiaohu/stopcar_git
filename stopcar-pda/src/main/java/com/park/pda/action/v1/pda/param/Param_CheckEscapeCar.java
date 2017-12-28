package com.park.pda.action.v1.pda.param;

import com.park.mvc.action.v1.param.BaseParam;

/**
 * 获取PDA的对应的车是否是逃逸车辆或者未交费车辆
 * @author jingxiaohu
 *
 */
public class Param_CheckEscapeCar extends BaseParam {
	public Long pi_id;//停车场ID
	public String  area_code;//省市区区域代码
	public String car_code;//车牌号码
	public Integer type;//是检查 逃逸逃逸车辆 还是 未支付车辆   0：默认检查逃逸车辆  1：检查未支付车辆
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
	public String getCar_code() {
		return car_code;
	}
	public void setCar_code(String car_code) {
		this.car_code = car_code;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	
	
}
