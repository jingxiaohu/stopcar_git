package com.park.app.action.v1.user.param;

import com.park.mvc.action.v1.param.BaseParam;
/**
 * 
 * @author jingxiaohu
 *
 */
public class Param_login extends BaseParam {
	/********************接收参数区*************************/
	public String tel;//用户手机号码
	public String password;//用户密码 
	public String uid;//用户ID
	public String avtar;//头像 
	public String nickname;//昵称
	public String sex;//性别
	//第三方登录
	public String up_type;//用户账户类型 0本地用户 1新浪账户 2腾讯账户 3人人账户 4开心账户 5天涯账户 6FACEBOOK',
	public String up_token;//外部TOKEN
	public String up_key;//外部KEY
	/************************get set 方法区****************************/

	public String getTel() {
		return tel;
	}
	public String getAvtar() {
		return avtar;
	}



	public void setAvtar(String avtar) {
		this.avtar = avtar;
	}



	public String getNickname() {
		return nickname;
	}



	public void setNickname(String nickname) {
		this.nickname = nickname;
	}



	public String getSex() {
		return sex;
	}



	public void setSex(String sex) {
		this.sex = sex;
	}






	public String getUp_token() {
		return up_token;
	}



	public void setUp_token(String up_token) {
		this.up_token = up_token;
	}



	public String getUp_key() {
		return up_key;
	}



	public void setUp_key(String up_key) {
		this.up_key = up_key;
	}



	public String getUid() {
		return uid;
	}



	public void setUid(String uid) {
		this.uid = uid;
	}



	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public String getUp_type() {
		return up_type;
	}
	public void setUp_type(String up_type) {
		this.up_type = up_type;
	}

	
}
