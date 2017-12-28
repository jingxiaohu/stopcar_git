package com.park.pda.action.v1.pda.param;

import com.park.mvc.action.v1.param.BaseParam;
/**
 * 获取PDA的对应的车的欠费记录
 * @author jingxiaohu
 *
 */
public class Param_CheckCarOwe extends BaseParam {
	public Long pi_id;//停车场ID
	public String  area_code;//省市区区域代码
	public String car_code;//车牌号码
	public Integer type;//是否是同省跨商户类型： 0 ：同商户 1：同省跨商户
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
