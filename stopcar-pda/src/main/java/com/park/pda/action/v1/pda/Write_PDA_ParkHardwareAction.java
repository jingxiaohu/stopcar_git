
package com.park.pda.action.v1.pda;

import java.net.URLDecoder;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.park.bean.ReturnDataNew;
import com.park.constants.Constants;
import com.park.mvc.action.v1.BaseV1Controller;
import com.park.pda.action.v1.pda.param.Param_park_hardware;
import com.park.pda.service.PDAParkHardwareBiz;
import com.park.util.RequestUtil;

/**
 * 停车场硬件设备校验登录MAC地址，并返回停车场信息。第一次登录时初始化 MAC
 * @author zyy
 */
@Controller
@RequestMapping(value = "/v1")
public class Write_PDA_ParkHardwareAction extends BaseV1Controller {
		     
  private static final long serialVersionUID = -3599663972160625262L;

  @Autowired
  private PDAParkHardwareBiz parkHardwareBiz;

/**
 * 停车场硬件设备校验登录MAC地址，更新登录token，并返回停车场信息和硬件设备信息
 * @param response
 * @param param
 * @return
 */
@RequestMapping(value = "/getparkinfo_by_hardware_mac")
  @ResponseBody
  public String getparkinfo_by_hardware_mac(HttpServletResponse response, Param_park_hardware param) {
    ReturnDataNew returnData = new ReturnDataNew();
    try {
    	//参数检查和签名验证
		if(!checkParam(param,returnData)){
	        sendResp(returnData, response);
	        return null;
	    }
    	
		parkHardwareBiz.getparkinfo_by_hardware_mac(returnData,param.ph_mac,param.ph_loginname,param.ph_password);

    } catch (Exception e) {
        log.error("Write_PDA_GetParkinfoByMacAction getparkinfo_by_hardware_mac is error(DEVICE-JAVA)- P", e);
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
	 * @throws Exception
	 */
	private boolean checkParam(Param_park_hardware param, ReturnDataNew returnData) throws Exception{
		//参数检查
	    if (param == null) {
	      //参数传递错误
	      returnData.setReturnData(errorcode_param, "参数传递错误", "");
	      return false;
	    }
	    //检查是否进行了参数签名认证
	    if (!param.checkRequest()) {
	      returnData.setReturnData(errorcode_param, "没有进行参数签名认证", "");
	      return false;
	    }
	    //对封装的参数对象中的属性进行 非空等规则验证
	    if (!RequestUtil.checkObjectBlank(param.getPh_mac())) {
	    	//硬件设备MAC地址
	    	param.setPh_mac(URLDecoder.decode(param.getPh_mac(), Constants.SYSTEM_CHARACTER));
	    } else {
	    	returnData.setReturnData(errorcode_param, "ph_mac is null", null);
	    	return false;
	    }
	    
	    if (!RequestUtil.checkObjectBlank(param.getPh_loginname())) {
	    	//硬件设备登录帐号
	    	param.setPh_loginname(URLDecoder.decode(param.getPh_loginname(), Constants.SYSTEM_CHARACTER));
	    } else {
	    	returnData.setReturnData(errorcode_param, "ph_loginname is null", null);
	    	return false;
	    }
	    
	    if (!RequestUtil.checkObjectBlank(param.getPh_password())) {
	    	//硬件设备登录密码
	    	param.setPh_password(URLDecoder.decode(param.getPh_password(), Constants.SYSTEM_CHARACTER));
	    } else {
	    	returnData.setReturnData(errorcode_param, "ph_password is null", null);
	    	return false;
	    }
	    
	    String sign_str = getSignature(Constants.SYSTEM_KEY,param.dtype,param.ph_loginname,param.ph_password);
	    if (!param.getSign().equalsIgnoreCase(sign_str)) {
	        log.warn("sign=" + param.getSign() + "  sign_str=" + sign_str);
	        returnData.setReturnData(errorcode_param, " 验证签名失败", null);
	        return false;
	    }
	    return true;
	}
  
}
