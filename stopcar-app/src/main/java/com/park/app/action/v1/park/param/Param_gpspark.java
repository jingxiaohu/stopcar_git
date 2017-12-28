package com.park.app.action.v1.park.param;

import com.park.mvc.action.v1.param.BaseParam;
/**
 * 通过GPS导航获取 该经纬度范围内的停车场数据列表
 * @author jingxiaohu
 *
 */
public class Param_gpspark extends BaseParam {
	/********************接收参数区*************************/
	public String area_code;//省市区区域代码  四川省 成都市 龙泉驿区  510112
	public double lng;//场地经度
	public double lat;//场地纬度
	public int park_type;//停车场类型 0：地下室停车场 1：露天停车场 2：露天立体车库停车场
	public int distance;//距离 单位米
	
	//租赁 按距离 按价格 排序
	public int type;//0 :按距离 1：按价格
	/************************get set 方法区****************************/
	
	public double getLng() {
		return lng;
	}
	public String getArea_code() {
		return area_code;
	}


	public void setArea_code(String area_code) {
		this.area_code = area_code;
	}


	public int getPark_type() {
		return park_type;
	}


	public void setPark_type(int park_type) {
		this.park_type = park_type;
	}


	public void setLng(double lng) {
		this.lng = lng;
	}
	public double getLat() {
		return lat;
	}
	public void setLat(double lat) {
		this.lat = lat;
	}
	public int getDistance() {
		return distance;
	}
	public void setDistance(int distance) {
		this.distance = distance;
	}


	public int getType() {
		return type;
	}


	public void setType(int type) {
		this.type = type;
	}

}
