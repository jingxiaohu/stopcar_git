package com.park.app.action.v1.user.param;

import com.park.mvc.action.v1.param.BaseParam;
/**
 * 用户发送验证码
 * @author jingxiaohu
 *
 */
public class Param_sendcode extends BaseParam {
	/********************接收参数区*************************/
	public String tel;//用户手机号码
	public String vclass;//固定参数：1：注册 2：重置密码 3:重置绑定电话号码  4：绑定银行卡  5：指纹数据采集验证
	public String verify_list;//md5(tel+code)
	// email邮件地址
	public String email;
	/************************get set 方法区****************************/

	public String getTel() {
		return tel;
	}
	public String getVerify_list() {
		return verify_list;
	}

	public void setVerify_list(String verify_list) {
		this.verify_list = verify_list;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getVclass() {
		return vclass;
	}
	public void setVclass(String vclass) {
		this.vclass = vclass;
	}

}
