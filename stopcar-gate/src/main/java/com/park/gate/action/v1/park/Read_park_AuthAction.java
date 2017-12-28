package com.park.gate.action.v1.park;

import com.park.bean.ReturnDataNew;
import com.park.constants.Constants;
import com.park.gate.action.v1.park.param.Param_park_auth;
import com.park.gate.action.v1.park.param.Param_park_rule;
import com.park.gate.service.AuthBiz;
import com.park.gate.service.GateParkruleBiz;
import com.park.mvc.action.v1.BaseV1Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

/**
 * 停车场-获取授权信息
 *
 */
@RestController
@RequestMapping(value = "/v1")
//by jxh 2017-8-21
public class Read_park_AuthAction extends BaseV1Controller {

	private static final long serialVersionUID = 1415728801205620764L;
	
	@Autowired
	private AuthBiz authBiz;
	
	/**
	 * 查询停车场-授权信息
	 * @param response
	 * @param param
	 * @return
	 */
	@RequestMapping(value ="/read_park_auth")
	public String read_park_auth(HttpServletResponse response,Param_park_auth param){
		ReturnDataNew returnData = new ReturnDataNew();
		try {
			//参数检查和签名验证
			if(!checkParam(param,returnData)){
				sendResp(returnData,response);
				return null;
			}
			authBiz.read_park_auth(returnData,param.pi_id,param.area_code);
		} catch (Exception e) {
			log.error("Read_park_AuthAction get_park_auth is error(DEVICE-JAVA)- P",e);
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
	public boolean checkParam(Param_park_auth param,ReturnDataNew returnData) throws Exception{
		//参数检查
	    if (param == null) {
	      returnData.setReturnData(errorcode_param, "参数传递错误", "");
	      return false;
	    }
	    //参数签名认证
	    if (!param.checkRequest()) {
	      returnData.setReturnData(errorcode_param, "没有进行参数签名认证", "");
	      return false;
	    }
	    //对封装的参数对象中的属性进行 非空等规则验证
	    if(param.getPi_id() == null){
	    	returnData.setReturnData(errorcode_param, "pi_id is null", null);
	    	return false;
	    }
	    if(param.getArea_code() == null){
	    	returnData.setReturnData(errorcode_param, "area_code is null", null);
	    	return false;
	    }
	    
	    String sign_str = getSignature(Constants.SYSTEM_KEY,param.dtype,param.pi_id,param.area_code);
	    if (!param.getSign().equalsIgnoreCase(sign_str)) {
	        log.warn("sign=" + param.getSign() + "  sign_str=" + sign_str);
	        returnData.setReturnData(errorcode_param, " 验证签名失败", null);
	        return false;
	    }
	    
		return true;
	}
}
