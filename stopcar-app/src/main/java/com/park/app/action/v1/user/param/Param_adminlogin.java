package com.park.app.action.v1.user.param;

import com.park.mvc.action.v1.param.BaseParam;
/**
 * 管理后台Android采集停车场数据登录接口
 * @author jingxiaohu
 *
 */
public class Param_adminlogin extends BaseParam {
	/********************接收参数区*************************/
	public String loginname;//用户手机号码
	public String password;//用户密码 
	/************************get set 方法区****************************/

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
