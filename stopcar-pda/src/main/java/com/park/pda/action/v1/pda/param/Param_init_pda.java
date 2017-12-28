package com.park.pda.action.v1.pda.param;

import com.park.mvc.action.v1.param.BaseParam;
/**
 * 露天停车场PDA帐号信息和经纬度 MAC 的初始化
 * @author jingxiaohu
 *
 */
public class Param_init_pda extends BaseParam {
	/********************接收参数区*************************/
	public double lng; //地理经度
	public double lat;//地理纬度
	public String mac;//pda mac
	public String loginname;//占道停车场的分配的帐号
	public String password;//占道停车场的密码
	public String area_code;//省市县编号 140107  
	public int park_type;//停车场类型 0：地下室停车场 1：露天停车场 2：露天立体车库停车场
	/************************get set 方法区****************************/


	public double getLng() {
		return lng;
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
	public String getMac() {
		return mac;
	}
	public void setMac(String mac) {
		this.mac = mac;
	}
	public String getLoginname() {
		return loginname;
	}
	public void setLoginname(String loginname) {
		this.loginname = loginname;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getArea_code() {
		return area_code;
	}
	public void setArea_code(String area_code) {
		this.area_code = area_code;
	}
	
}
