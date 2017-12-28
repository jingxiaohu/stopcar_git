
package com.park.app.action.v1.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.park.app.action.v1.user.param.Param_UserFaceImgRecode;
import com.park.app.action.v1.user.param.Param_UserFaceStateUpdate;
import com.park.app.service.UserBiz;
import com.park.bean.ReturnDataNew;
import com.park.constants.Constants;
import com.park.mvc.action.v1.BaseV1Controller;
import com.park.util.RequestUtil;

/**
 * 人脸识别图片提取和开关状态修改
 * @author zyy
 */
@RestController
@RequestMapping(value = "/v1")
public class Write_UserFaceImgAction extends BaseV1Controller {

	private static final long serialVersionUID = -4578784456305860384L;

	@Autowired
	private UserBiz userBiz;

	/**
	 * 人脸识别图片提取
	 * @param request
	 * @param response
	 * @param param
	 * @return
	 */
	@RequestMapping(value = "/recode_userface_img")
	public String recodeUserFaceImg(HttpServletRequest request, HttpServletResponse response, Param_UserFaceImgRecode param) {
		ReturnDataNew returnData = new ReturnDataNew();
		try {
			//参数检查和参数签名认证
	        if(!checkParam(param,returnData)){
	        	sendResp(returnData, response);
		        return null;
	        }
	        //获取文件名
	        MultipartFile avatar = param.getAvatar();
	        String avatarFileName = null;
	        if (avatar != null) {
	            avatarFileName = avatar.getOriginalFilename();
	            param.setAvatarFileName(avatarFileName);
	        }
	        
	        userBiz.recodeUserFaceImg(returnData,param.ui_id,param.avatar, avatarFileName);
		}catch (Exception e) {
		      log.error("Write_UserFaceImgAction recodeUserFaceImg  is error(DEVICE-JAVA)- P", e);
		      returnData.setReturnData(errorcode_systerm, "system is error", "");
		 }
		sendResp(returnData, response);
		return null;
	}
	
	/**
	 * 修改用户 人脸识别开关状态
	 * @param request
	 * @param response
	 * @param param
	 * @return
	 */
	@RequestMapping(value = "/update_userface_state")
	public String updateUserFaceState(HttpServletRequest request, HttpServletResponse response, Param_UserFaceStateUpdate param) {
		ReturnDataNew returnData = new ReturnDataNew();
		try {
			//参数检查和参数签名认证
			if(!checkParamT(param,returnData)){
				sendResp(returnData, response);
				return null;
			}
			
			userBiz.updateUserFaceState(returnData,param.ui_id,param.ui_face_state);
		}catch (Exception e) {
			log.error("Write_UserFaceImgAction updateUserFaceState  is error(DEVICE-JAVA)- P", e);
			returnData.setReturnData(errorcode_systerm, "system is error", "");
		}
		sendResp(returnData, response);
		return null;
	}
	
	/**
	 * 参数检查和签名验证
	 * @param param
	 * @param returnData
	 * @return
	 */
	public boolean checkParam(Param_UserFaceImgRecode param,ReturnDataNew returnData) throws Exception{
		//参数检查
		if(RequestUtil.checkObjectBlank(param)){
			returnData.setReturnData(errorcode_param, "参数传递错误","");
			return false;
		}
		//参数签名验证
		if(!param.checkRequest()){
			returnData.setReturnData(errorcode_param, "没有进行参数签名认证", "");
		}
		//对封装的参数对象中的属性进行 非空等规则验证
		if (param.getUi_id() <= 0) {
		      //用户ID
		      returnData.setReturnData(errorcode_param, " ui_id <= 0", null);
		      return false;
		}
        if (RequestUtil.checkObjectBlank(param.getAvatar())) {
	    	returnData.setReturnData(errorcode_param, "提交的人脸图片为空", null);
	    	return false;
	    }
        
        //字符串签名
        String sign_str = getSignature(Constants.SYSTEM_KEY, param.ui_id);
        if (!param.sign.equalsIgnoreCase(sign_str)) {
            log.warn("sign=" + param.sign + "  sign_str=" + sign_str);
            returnData.setReturnData(errorcode_param, " sign is not right", null);
            return false;
        }
		
		return true;
	}
	
	/**
	 * 参数检查和签名验证
	 * @param param
	 * @param returnData
	 * @return
	 */
	public boolean checkParamT(Param_UserFaceStateUpdate param,ReturnDataNew returnData) throws Exception{
		//参数检查
		if(RequestUtil.checkObjectBlank(param)){
			returnData.setReturnData(errorcode_param, "参数传递错误","");
			return false;
		}
		//参数签名验证
		if(!param.checkRequest()){
			returnData.setReturnData(errorcode_param, "没有进行参数签名认证", "");
		}
		//对封装的参数对象中的属性进行 非空等规则验证
		if (param.getUi_id() <= 0) {
			//用户ID
			returnData.setReturnData(errorcode_param, " ui_id <= 0", null);
			return false;
		}
		//人脸识别状态
		if(RequestUtil.checkIntegerBlankOrNegative(param.getUi_face_state())){
			returnData.setReturnData(errorcode_param, " ui_face_state is null or smaller then zero", null);
			return false;
		}
		
		//字符串签名
		String sign_str = getSignature(Constants.SYSTEM_KEY, param.ui_id,param.ui_face_state);
		if (!param.sign.equalsIgnoreCase(sign_str)) {
			log.warn("sign=" + param.sign + "  sign_str=" + sign_str);
			returnData.setReturnData(errorcode_param, " sign is not right", null);
			return false;
		}
		
		return true;
	}

}
