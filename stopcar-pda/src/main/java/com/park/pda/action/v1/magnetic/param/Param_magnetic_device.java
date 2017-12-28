package com.park.pda.action.v1.magnetic.param;

import com.park.mvc.action.v1.param.BaseParam;

/**
 * 设备参数
 * @author zyy
 *
 */
public class Param_magnetic_device extends BaseParam {

	public Long pi_id;	//停车场主键ID
	
	public String area_code;	//地址编码
	
	public String gov_num;	//政府拟定的车位编码
	
	public String car_dev_num;	//车位设备编码
	
	public String android_dev_num;	//android板子设备编码
	
	public Integer is_del;	//是否删除（0：正常 1：删除（逻辑删除））
	
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

	public String getGov_num() {
		return gov_num;
	}

	public void setGov_num(String gov_num) {
		this.gov_num = gov_num;
	}

	public String getCar_dev_num() {
		return car_dev_num;
	}

	public void setCar_dev_num(String car_dev_num) {
		this.car_dev_num = car_dev_num;
	}

	public String getAndroid_dev_num() {
		return android_dev_num;
	}

	public void setAndroid_dev_num(String android_dev_num) {
		this.android_dev_num = android_dev_num;
	}

	public Integer getIs_del() {
		return is_del;
	}

	public void setIs_del(Integer is_del) {
		this.is_del = is_del;
	}
	
}
