package com.park.pda.action.v1.pda.param;

import com.park.mvc.action.v1.param.BaseParam;

/**
 * 用户反馈
 * 
 * @author zyy
 *
 */
public class Param_park_hardware extends BaseParam {
	/******************** 接收参数区 *************************/
	public String ph_mac;// 硬件设备MAC地址
	public String ph_loginname;// 硬件设备登录帐号
	public String ph_password;// 硬件设备登录密码

	/************************ get set 方法区 ****************************/

	public String getPh_mac() {
		return ph_mac;
	}

	public void setPh_mac(String ph_mac) {
		this.ph_mac = ph_mac;
	}

	public String getPh_loginname() {
		return ph_loginname;
	}

	public void setPh_loginname(String ph_loginname) {
		this.ph_loginname = ph_loginname;
	}

	public String getPh_password() {
		return ph_password;
	}

	public void setPh_password(String ph_password) {
		this.ph_password = ph_password;
	}

}
