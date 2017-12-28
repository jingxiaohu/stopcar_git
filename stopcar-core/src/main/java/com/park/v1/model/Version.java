package com.park.v1.model;

import java.io.Serializable;

/**
 * 版本升级的MODEL
 * @author jingxiaohu
 *
 */
public class Version implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4190099148552678377L;
	
	private String version;
	private int versioncode;
	private String content;
	private String url;
	private String md5;
	private String type;
	private int update;//是否有升级 是否有更新  1 –是 0 –否 
	private int is_forced;//INT    是否强制更新0：不强制更新1：强制更新
	
	
	
	
	public int getIs_forced() {
		return is_forced;
	}
	public void setIs_forced(int is_forced) {
		this.is_forced = is_forced;
	}
	public int getUpdate() {
		return update;
	}
	public void setUpdate(int update) {
		this.update = update;
	}
	public String getVersion() {
		return version;
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
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getMd5() {
		return md5;
	}
	public void setMd5(String md5) {
		this.md5 = md5;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	
	

}
