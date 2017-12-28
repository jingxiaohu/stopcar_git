package com.park.gate.action.v1.park;

import java.net.URLDecoder;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.park.bean.ReturnDataNew;
import com.park.constants.Constants;
import com.park.gate.action.v1.park.param.Param_client_gate_rule;
import com.park.gate.action.v1.park.param.Param_client_gate_ruleT;
import com.park.gate.service.ClientGateRuleBiz;
import com.park.mvc.action.v1.BaseV1Controller;
import com.park.util.RequestUtil;

/**
 * 客户端--道闸--规则记录 管理
 * @author zyy
 *
 */
@RestController
@RequestMapping(value = "/v1")
public class Write_client_gate_ruleAction extends BaseV1Controller {

	private static final long serialVersionUID = 1415728801205620765L;
	
	@Autowired
	private ClientGateRuleBiz clientGateRuleBiz;
	
	/**
	 * 添加客户端道闸规则记录
	 * @param response
	 * @param param
	 * @return
	 */
	@RequestMapping(value ="/add_client_gate_rule")
	public String add_client_gate_rule(HttpServletResponse response,Param_client_gate_rule param){
		ReturnDataNew returnData = new ReturnDataNew();
		try {
			//参数检查和签名验证
			if(!checkParam(param,returnData)){
				sendResp(returnData,response);
				return null;
			}
			clientGateRuleBiz.add_client_gate_rule(returnData,param);
		} catch (Exception e) {
			log.error("Write_client_gate_ruleAction add_client_gate_rule is error(DEVICE-JAVA)- P",e);
			returnData.setReturnData(errorcode_systerm, "system is error", "");
		}
		sendResp(returnData, response);
	    return null;	
	}
	
	/**
	 * 修改客户端道闸规则记录
	 * @param response
	 * @param param
	 * @return
	 */
	@RequestMapping(value ="/update_client_gate_rule")
	public String update_client_gate_rule(HttpServletResponse response,Param_client_gate_rule param){
		ReturnDataNew returnData = new ReturnDataNew();
		try {
			//参数检查和签名验证
			if(!checkParamU(param,returnData)){
				sendResp(returnData,response);
				return null;
			}
			
			clientGateRuleBiz.update_client_gate_rule(returnData,param);
		} catch (Exception e) {
			log.error("Write_client_gate_ruleAction update_client_gate_rule is error(DEVICE-JAVA)- P",e);
			returnData.setReturnData(errorcode_systerm, "system is error", "");
		}
		sendResp(returnData, response);
		return null;	
	}
	
	/**
	 * 删除客户端道闸规则记录
	 * @param response
	 * @param param
	 * @return
	 */
	@RequestMapping(value ="/delete_client_gate_rule")
	public String delete_client_gate_rule(HttpServletResponse response,Param_client_gate_ruleT param){
		ReturnDataNew returnData = new ReturnDataNew();
		try {
			//参数检查和签名验证
			if(!checkParamForDel(param,returnData)){
				sendResp(returnData,response);
				return null;
			}
			clientGateRuleBiz.delete_client_gate_rule(returnData,param);
		} catch (Exception e) {
			log.error("Write_client_gate_ruleAction delete_client_gate_rule is error(DEVICE-JAVA)- P",e);
			returnData.setReturnData(errorcode_systerm, "system is error", "");
		}
		sendResp(returnData, response);
		return null;	
	}
	
	/**
	 * 参数检查和签名验证 - 添加规则
	 * @param param
	 * @param returnData
	 * @return
	 * @throws Exception 
	 */
	public boolean checkParam(Param_client_gate_rule param,ReturnDataNew returnData) throws Exception{
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
	    if(RequestUtil.checkObjectBlank(param.getClient_ruleid())){
	    	returnData.setReturnData(errorcode_param, "client_ruleid is null", null);
	    	return false;
	    }
	    if(RequestUtil.checkObjectBlank(param.getPi_id())){
	    	returnData.setReturnData(errorcode_param, "pi_id is null", null);
	    	return false;
	    }
	    if(RequestUtil.checkObjectBlank(param.getArea_code())){
	    	returnData.setReturnData(errorcode_param, "area_code is null", null);
	    	return false;
	    }
	    if(RequestUtil.checkObjectBlank(param.getGroup_id())){
	    	returnData.setReturnData(errorcode_param, "group_id is null", null);
	    	return false;
	    }
	    if(RequestUtil.checkObjectBlank(param.getType())){
	    	returnData.setReturnData(errorcode_param, "type is null", null);
	    	return false;
	    }
	    if(RequestUtil.checkObjectBlank(param.getMoney())){
	    	returnData.setReturnData(errorcode_param, "money is null", null);
	    	return false;
	    }
	    if(RequestUtil.checkObjectBlank(param.getState())){
	    	returnData.setReturnData(errorcode_param, "state is null", null);
	    	return false;
	    }
	    if (!RequestUtil.checkObjectBlank(param.getStr_json())) {
	    	param.setStr_json(URLDecoder.decode(param.getStr_json(), Constants.SYSTEM_CHARACTER));
	    } else {
	    	returnData.setReturnData(errorcode_param, "str_json is null", null);
	    	return false;
	    }
	    if (!RequestUtil.checkObjectBlank(param.getIntro())) {
	    	param.setIntro(URLDecoder.decode(param.getIntro(), Constants.SYSTEM_CHARACTER));
	    } else {
	    	returnData.setReturnData(errorcode_param, "intro is null", null);
	    	return false;
	    }
	    if(RequestUtil.checkObjectBlank(param.getPermit_time())){
	    	returnData.setReturnData(errorcode_param, "permit_time is null", null);
	    	return false;
	    }
	    if(RequestUtil.checkObjectBlank(param.getClient_loginname())){
	    	returnData.setReturnData(errorcode_param, "client_loginname is null", null);
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
	
	/**
	 * 参数检查和签名验证 - 修改规则
	 * @param param
	 * @param returnData
	 * @return
	 * @throws Exception 
	 */
	public boolean checkParamU(Param_client_gate_rule param,ReturnDataNew returnData) throws Exception{
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
		if(RequestUtil.checkObjectBlank(param.getClient_ruleid())){
			returnData.setReturnData(errorcode_param, "client_ruleid is null", null);
			return false;
		}
		if(RequestUtil.checkObjectBlank(param.getPi_id())){
			returnData.setReturnData(errorcode_param, "pi_id is null", null);
			return false;
		}
		if(RequestUtil.checkObjectBlank(param.getArea_code())){
			returnData.setReturnData(errorcode_param, "area_code is null", null);
			return false;
		}
		String sign_str = getSignature(Constants.SYSTEM_KEY,param.dtype,param.client_ruleid,param.pi_id,param.area_code);
		if (!param.getSign().equalsIgnoreCase(sign_str)) {
			log.warn("sign=" + param.getSign() + "  sign_str=" + sign_str);
			returnData.setReturnData(errorcode_param, " 验证签名失败", null);
			return false;
		}
		
		return true;
	}
	
	/**
	 * 参数检查和签名验证 - 删除规则
	 * @param param
	 * @param returnData
	 * @return
	 * @throws Exception 
	 */
	public boolean checkParamForDel(Param_client_gate_ruleT param,ReturnDataNew returnData) throws Exception{
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
		if(RequestUtil.checkObjectBlank(param.getClient_ruleid())){
			returnData.setReturnData(errorcode_param, "client_ruleid is null", null);
			return false;
		}
		if(RequestUtil.checkObjectBlank(param.getPi_id())){
	    	returnData.setReturnData(errorcode_param, "pi_id is null", null);
	    	return false;
	    }
	    if(RequestUtil.checkObjectBlank(param.getArea_code())){
	    	returnData.setReturnData(errorcode_param, "area_code is null", null);
	    	return false;
	    }
		String sign_str = getSignature(Constants.SYSTEM_KEY,param.dtype,param.client_ruleid,param.pi_id,param.area_code);
		if (!param.getSign().equalsIgnoreCase(sign_str)) {
			log.warn("sign=" + param.getSign() + "  sign_str=" + sign_str);
			returnData.setReturnData(errorcode_param, " 验证签名失败", null);
			return false;
		}
		
		return true;
	}
	
}
