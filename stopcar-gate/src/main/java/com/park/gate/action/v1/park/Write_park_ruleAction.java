package com.park.gate.action.v1.park;

import java.net.URLDecoder;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.park.bean.ReturnDataNew;
import com.park.constants.Constants;
import com.park.gate.action.v1.park.param.Param_park_rule;
import com.park.gate.service.GateParkruleBiz;
import com.park.mvc.action.v1.BaseV1Controller;
import com.park.util.RequestUtil;

/**
 * 停车场-特殊规则信息映射
 * @author zyy
 *
 */
@RestController
@RequestMapping(value = "/v1")
@Deprecated
//by jxh 2017-7-17 该功能后续 也许会终止
public class Write_park_ruleAction extends BaseV1Controller {

	private static final long serialVersionUID = 1415728801205620764L;
	
	@Autowired
	private GateParkruleBiz parkruleBiz;
	
	/**
	 * 添加停车场-特殊规则信息
	 * @param response
	 * @param param(pi_id,area_code,json_array:规则JSONArray数组)
	 * @return
	 */
	@RequestMapping(value ="/add_parkrule")
	public String add_parkrule(HttpServletResponse response,Param_park_rule param){
		ReturnDataNew returnData = new ReturnDataNew();
		try {
			//参数检查和签名验证
			if(!checkParam(param,returnData)){
				sendResp(returnData,response);
				return null;
			}
			parkruleBiz.add_parkrule(returnData,param);
		} catch (Exception e) {
			log.error("Write_parkruleAction add_parkrule is error(DEVICE-JAVA)- P",e);
			returnData.setReturnData(errorcode_systerm, "system is error", "");
		}
		sendResp(returnData, response);
	    return null;	
	}
	
	/**
	 * 修改停车场-特殊规则信息
	 * @param response
	 * @param param(pi_id,area_code,json_array:规则JSONArray数组)
	 * @return
	 */
	@RequestMapping(value = "/update_parkrule")
	public String update_parkrule(HttpServletResponse response,Param_park_rule param){
		ReturnDataNew returnData = new ReturnDataNew();
		try {
			//参数检查和签名验证
			if(!checkParam(param,returnData)){
				sendResp(returnData,response);
				return null;
			}
			parkruleBiz.update_parkrule(returnData,param);
		} catch (Exception e) {
			log.error("Write_parkruleAction update_parkrule is error(DEVICE-JAVA)- P",e);
			returnData.setReturnData(errorcode_systerm, "system is error", "");
		}
		sendResp(returnData, response);
	    return null;
	}

	/**
	 * 参数检查和签名验证
	 * @param param(pi_id,area_code,json_array:规则JSONArray数组)
	 * @param returnData
	 * @return
	 * @throws Exception 
	 */
	public boolean checkParam(Param_park_rule param,ReturnDataNew returnData) throws Exception{
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
	    if (!RequestUtil.checkObjectBlank(param.getJson_array())) {
	    	//规则JSONArray数组
	    	param.setJson_array(URLDecoder.decode(param.getJson_array(), Constants.SYSTEM_CHARACTER));
	    } else {
	    	returnData.setReturnData(errorcode_param, "json_array is null", null);
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
