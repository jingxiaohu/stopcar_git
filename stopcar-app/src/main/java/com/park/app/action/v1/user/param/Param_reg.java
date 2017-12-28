package com.park.app.action.v1.user.param;

import com.park.mvc.action.v1.param.BaseParam;
/**
 * 用户注册
 * @author jingxiaohu
 *
 */
public class Param_reg extends BaseParam {
	
	/********************接收参数区*************************/
	public String tel;//用户手机号码
	public String verify_code;//用户验证码
	public String verify_list;//由发送验证码接口或者重新发送验证码接口返回的verify_list参数的值
	public String password;//用户密码 
	public String repassword;//确认密码
	public String vclass;//固定参数：1：注册 2：重置密码 3:重置绑定电话号码  4：绑定银行卡
	public String uid;//用户ID
	
	
	
	/************************get set 方法区****************************/

	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getVerify_code() {
		return verify_code;
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
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
}
