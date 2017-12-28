package com.park.pda.action.v1.magnetic.param;

import com.park.mvc.action.v1.param.BaseParam;

public class Param_change_device_state extends BaseParam{
	public Integer pi_id;//停车场主键id
	public String area_code;//地址编码
	
	public Integer car_port_yet;//临停已停车位
	public Integer car_port_space;//临停空车位个数
	public Integer car_port_total;//临停总车位个数
	public String car_dev_num;//车位设备编码
	public Integer state;//车位设备状态（0：无车 1：有车 2：故障）
	public String android_dev_num;//android板子设备编码
	
	//by jxh 2017-6-13 新增设备端本地时间
	public Long ctime;
	
	
	
	
	
	public Long getCtime() {
		return ctime;
	}
	public void setCtime(Long ctime) {
		this.ctime = ctime;
	}
	public Integer getPi_id() {
		return pi_id;
	}
	public void setPi_id(Integer pi_id) {
		this.pi_id = pi_id;
	}
	public String getArea_code() {
		return area_code;
	}
	public void setArea_code(String area_code) {
		this.area_code = area_code;
	}
	public Integer getCar_port_yet() {
		return car_port_yet;
	}
	public void setCar_port_yet(Integer car_port_yet) {
		this.car_port_yet = car_port_yet;
	}
	public Integer getCar_port_space() {
		return car_port_space;
	}
	public void setCar_port_space(Integer car_port_space) {
		this.car_port_space = car_port_space;
	}
	public Integer getCar_port_total() {
		return car_port_total;
	}
	public void setCar_port_total(Integer car_port_total) {
		this.car_port_total = car_port_total;
	}
	
	public String getCar_dev_num() {
		return car_dev_num;
	}
	public void setCar_dev_num(String car_dev_num) {
		this.car_dev_num = car_dev_num;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public String getAndroid_dev_num() {
		return android_dev_num;
	}
	public void setAndroid_dev_num(String android_dev_num) {
		this.android_dev_num = android_dev_num;
	}
	@Override
	public String toString() {
		return "Param_change_device_state [pi_id=" + pi_id + ", area_code=" + area_code + ", car_port_yet="
				+ car_port_yet + ", car_port_space=" + car_port_space + ", car_port_total=" + car_port_total
				+ ", car_dev_num=" + car_dev_num + ", state=" + state + ", android_dev_num=" + android_dev_num + "]";
	}
	
	
	
	

	

}
