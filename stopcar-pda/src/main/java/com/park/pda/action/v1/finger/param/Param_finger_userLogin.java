package com.park.pda.action.v1.finger.param;

import com.park.mvc.action.v1.param.BaseParam;
/**
 * 指纹采集--用户登录
 * @author zyy
 *
 */
public class Param_finger_userLogin extends BaseParam {
	
	public String loginname;//登录帐号
	
	public String password;//密码
	
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
	
}
