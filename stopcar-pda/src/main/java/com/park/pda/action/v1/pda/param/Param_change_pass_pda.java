package com.park.pda.action.v1.pda.param;

import com.park.mvc.action.v1.param.BaseParam;

/**
 * 用户重置PDA密码
 * @author jingxiaohu
 *
 */
public class Param_change_pass_pda extends BaseParam {
	/********************接收参数区*************************/
	public String loginname;//帐号
	public String tel;//用户手机号码
	public String verify_code;//用户验证码
	public String verify_list;//由发送验证码接口或者重新发送验证码接口返回的verify_list参数的值
	public String password;//用户密码 
	public String repassword;//确认密码
	public String vclass;//固定参数：1：注册 2：重置密码
	public String area_code;//省市县编号 140107  
	public int park_type;//停车场类型 0：地下室停车场 1：露天停车场 2：露天立体车库停车场
	/************************get set 方法区****************************/

	public String getTel() {
		return tel;
	}

	public String getLoginname() {
		return loginname;
	}

	public void setLoginname(String loginname) {
		this.loginname = loginname;
	}

	public String getVerify_code() {
		return verify_code;
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

	public void setVerify_code(String verify_code) {
		this.verify_code = verify_code;
	}

	public String getVerify_list() {
		return verify_list;
	}

	public void setVerify_list(String verify_list) {
		this.verify_list = verify_list;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRepassword() {
		return repassword;
	}

	public void setRepassword(String repassword) {
		this.repassword = repassword;
	}

	public String getVclass() {
		return vclass;
	}

	public void setVclass(String vclass) {
		this.vclass = vclass;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}


}
