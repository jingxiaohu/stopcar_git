package com.park.pda.action.v1.pda.param;

import com.park.mvc.action.v1.param.BaseParam;

/**
 * 露天停车场PDA 停车场员工上班下班打卡
 * @author jingxiaohu
 *
 */
public class Param_pda_clock extends BaseParam {
	/********************接收参数区*************************/
	public Double lng; //地理经度
	public Double lat;//地理纬度
	public String mac;//pda mac
	public String loginname;//露天停车场的分配的帐号
	public String area_code;//省市县编号 140107  
	public Integer park_type=1;//停车场类型 0：地下室停车场 1：露天停车场 2：露天立体车库停车场
	public Integer pi_id;//停车场主键ID
	public Integer type;//打卡类型  0:上班打卡  1：下班打卡
	public Integer ppc_id;//打卡记录表主键ID
	public String ppc_nd;//唯一标识符
	/************************get set 方法区****************************/
	

	public Integer getPi_id() {
		return pi_id;
	}
	public Integer getPpc_id() {
		return ppc_id;
	}
	public void setPpc_id(Integer ppc_id) {
		this.ppc_id = ppc_id;
	}
	public String getPpc_nd() {
		return ppc_nd;
	}
	public void setPpc_nd(String ppc_nd) {
		this.ppc_nd = ppc_nd;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
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
	public String getArea_code() {
		return area_code;
	}
	public void setArea_code(String area_code) {
		this.area_code = area_code;
	}
	public Integer getPark_type() {
		return park_type;
	}
	public void setPark_type(Integer park_type) {
		this.park_type = park_type;
	}
	public void setPi_id(Integer pi_id) {
		this.pi_id = pi_id;
	}
	
}
