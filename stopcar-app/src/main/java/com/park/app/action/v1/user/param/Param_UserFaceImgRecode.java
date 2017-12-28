package com.park.app.action.v1.user.param;

import org.springframework.web.multipart.MultipartFile;
import com.park.mvc.action.v1.param.BaseParam;

/**
 * 用户人脸识别图片提取和开关状态修改
 *
 * @author zyy
 */
public class Param_UserFaceImgRecode extends BaseParam {
	
	public MultipartFile avatar; //用户人脸图片
    
	public String avatarFileName; //人脸图片文件名
    
	public MultipartFile getAvatar() {
		return avatar;
	}

	public void setAvatar(MultipartFile avatar) {
		this.avatar = avatar;
	}

	public String getAvatarFileName() {
		return avatarFileName;
	}

	public void setAvatarFileName(String avatarFileName) {
		this.avatarFileName = avatarFileName;
	}

}
