package com.park.app.action.v1.user.param;

import com.park.mvc.action.v1.param.BaseParam;

/**
 * 用户人脸识别图片提取和开关状态修改
 *
 * @author zyy
 */
public class Param_UserFaceStateUpdate extends BaseParam {
	
	public Integer ui_face_state; //人脸识别状态（0:未采集到人脸  1：已经采集到人脸且关闭人脸支付   2：开启人脸支付  ）

	public Integer getUi_face_state() {
		return ui_face_state;
	}

	public void setUi_face_state(Integer ui_face_state) {
		this.ui_face_state = ui_face_state;
	}
    
}
