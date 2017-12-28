package com.park.app.action.v1.web.param;

import com.park.mvc.action.v1.param.BaseParam;

/**
 * web停车场基本信息管理
 * @author zyy
 *
 */
public class Param_park_info_web extends BaseParam {

	public String area_code;//省市区区域代码

	public Double lng;//场地经度

	public Double lat;//场地纬度

	public Integer distance;//距离 单位米

//	public String pi_name;//停车场场地名称

	public String getArea_code() {
		return area_code;
	}

	public void setArea_code(String area_code) {
		this.area_code = area_code;
	}

	public Double getLng() {
		return lng;
	}

	public void setLng(Double lng) {
		this.lng = lng;
	}

	public Double getLat() {
		return lat;
	}

	public void setLat(Double lat) {
		this.lat = lat;
	}

	public Integer getDistance() {
		return distance;
	}

	public void setDistance(Integer distance) {
		this.distance = distance;
	}
	
}
