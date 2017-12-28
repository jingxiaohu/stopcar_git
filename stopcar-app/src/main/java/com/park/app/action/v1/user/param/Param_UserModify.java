package com.park.app.action.v1.user.param;

import org.springframework.web.multipart.MultipartFile;

import com.park.mvc.action.v1.param.BaseParam;
/**
 * 用户重置密码 用户修改绑定手机号码   用户修改头像  用户修改昵称
 * @author jingxiaohu
 *
 */
public class Param_UserModify extends BaseParam {
	/********************接收参数区*************************/
	public String tel;//用户手机号码
	public String verify_code;//用户验证码
	public String verify_list;//由发送验证码接口或者重新发送验证码接口返回的verify_list参数的值
	public String password;//用户密码 
	public String repassword;//确认密码
	public String vclass;//固定参数：1：注册 2：重置密码
	public String nickname;//昵称
	public  String sex;//用户性别 : male 男   women 女   no  未知
	public String name;//用户姓名
	public String driving_licence;//用户驾驶证
	public String email;//用户邮箱
	//用户是否自动支付 开关
	public String ui_autopay;//是否自动支付 是否自动支付 ：0 ：不是 1：是
	public int pay_source;//支付类型1:支付宝 2：微信 3：银联 4：钱包
	
	
	
	//用户头像
	public  MultipartFile avatar;
    //提交过来的file的名字
	public String avatarFileName;
    //提交过来的file的MIME类型
	public String avatarContentType;
	
	
	//是否开启ETC自动支付  ：0 ：不开启  1：开启
	public Integer etc_autopay;
	
	/************************get set 方法区****************************/

	public String getTel() {
		return tel;
	}
	public Integer getEtc_autopay() {
		return etc_autopay;
	}
	public void setEtc_autopay(Integer etc_autopay) {
		this.etc_autopay = etc_autopay;
	}
	public MultipartFile getAvatar() {
		return avatar;
	}
	public void setAvatar(MultipartFile avatar) {
		this.avatar = avatar;
	}
	public String getUi_autopay() {
		return ui_autopay;
	}

	public void setUi_autopay(String ui_autopay) {
		this.ui_autopay = ui_autopay;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDriving_licence() {
		return driving_licence;
	}

	public void setDriving_licence(String driving_licence) {
		this.driving_licence = driving_licence;
	}

	public String getSex() {
		return sex;
	}


	public void setSex(String sex) {
		this.sex = sex;
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
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getAvatarFileName() {
		return avatarFileName;
	}
	public void setAvatarFileName(String avatarFileName) {
		this.avatarFileName = avatarFileName;
	}
	public String getAvatarContentType() {
		return avatarContentType;
	}
	public void setAvatarContentType(String avatarContentType) {
		this.avatarContentType = avatarContentType;
	}

	public int getPay_source() {
		return pay_source;
	}

	public void setPay_source(int pay_source) {
		this.pay_source = pay_source;
	}
	
	

	
}
