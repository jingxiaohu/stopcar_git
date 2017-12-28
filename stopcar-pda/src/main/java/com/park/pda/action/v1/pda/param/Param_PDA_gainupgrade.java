package com.park.pda.action.v1.pda.param;

import com.park.mvc.action.v1.param.BaseParam;

/**
 * 获取设备 Android IOS 升级对应的包URL
 */
public class Param_PDA_gainupgrade extends BaseParam {
	/********************接收参数区*************************/
	public String version;//版本
	public int versioncode;//版本内部编号
	public String uid;//用户ID
	public String mac;//物理地址
	/************************get set 方法区****************************/

	public String getVersion() {
		return version;
	}
	public String getMac() {
		return mac;
	}
	public void setMac(String mac) {
		this.mac = mac;
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
