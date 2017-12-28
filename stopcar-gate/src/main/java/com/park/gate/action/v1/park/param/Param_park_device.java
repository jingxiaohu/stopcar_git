package com.park.gate.action.v1.park.param;

import com.park.mvc.action.v1.param.BaseParam;
/**
 * 记录场地出入口设备对应关系信息
 * @author jingxiaohu
 *
 */
public class Param_park_device extends BaseParam {
	/********************接收参数区*************************/
	public String area_code;//省市县编号 140107
	
	public long pi_id;//场地主键ID
	public String  in_out;//出口或者入口 入口：enter  出口：exit
	public String in_out_code;//出入口编号  例如(A出口 B入口)
	public String camera;//摄像头名称
	public String camera_mac;//摄像头MAC
	public String signo_name;//道闸名称
	public String solid_garage_mac;//立体车库设备MAC
	public String solid_garage_sn;//立体车库设备编号
	//更新场地出入口的设备时候使用
	public long pd_id;//场地出入口管理主键ID
	
	/************************get set 方法区****************************/

	public long getPi_id() {
		return pi_id;
	}



	public String getArea_code() {
		return area_code;
	}


	public void setArea_code(String area_code) {
		this.area_code = area_code;
	}


	public void setPi_id(long pi_id) {
		this.pi_id = pi_id;
	}


	public String getIn_out() {
		return in_out;
	}


	public void setIn_out(String in_out) {
		this.in_out = in_out;
	}


	public String getIn_out_code() {
		return in_out_code;
	}


	public void setIn_out_code(String in_out_code) {
		this.in_out_code = in_out_code;
	}


	public String getCamera() {
		return camera;
	}


	public void setCamera(String camera) {
		this.camera = camera;
	}


	public String getCamera_mac() {
		return camera_mac;
	}


	public void setCamera_mac(String camera_mac) {
		this.camera_mac = camera_mac;
	}


	public String getSigno_name() {
		return signo_name;
	}


	public void setSigno_name(String signo_name) {
		this.signo_name = signo_name;
	}




	public String getSolid_garage_mac() {
		return solid_garage_mac;
	}


	public void setSolid_garage_mac(String solid_garage_mac) {
		this.solid_garage_mac = solid_garage_mac;
	}


	public String getSolid_garage_sn() {
		return solid_garage_sn;
	}


	public void setSolid_garage_sn(String solid_garage_sn) {
		this.solid_garage_sn = solid_garage_sn;
	}


	public long getPd_id() {
		return pd_id;
	}


	public void setPd_id(long pd_id) {
		this.pd_id = pd_id;
	}
}
