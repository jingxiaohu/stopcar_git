package com.park.app.action.v1.user.param;

import com.park.mvc.action.v1.param.BaseParam;
/**
 * 获取设备 Android IOS 升级对应的包URL
 */
public class Param_gainupgrade extends BaseParam {
	/********************接收参数区*************************/
	public String version;//版本
	public int versioncode;//版本内部编号
	public String uid;//用户ID
	/************************get set 方法区****************************/

	public String getVersion() {
		return version;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getSign() {
		return sign;
	}
	public void setSign(String sign) {
		this.sign = sign;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public int getVersioncode() {
		return versioncode;
	}
	public void setVersioncode(int versioncode) {
		this.versioncode = versioncode;
	}

	
}
