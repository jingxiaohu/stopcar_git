package com.park.gate.action.v1.park;

import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.park.bean.ReturnDataNew;
import com.park.constants.Constants;
import com.park.gate.action.v1.park.param.Param_client_gate_ruleT;
import com.park.gate.service.ClientGateRuleBiz;
import com.park.mvc.action.v1.BaseV1Controller;
import com.park.util.RequestUtil;

/**
 * 客户端--道闸--规则记录 查询
 * @author zyy
 *
 */
@RestController
@RequestMapping(value = "/v1")
public class Read_client_gate_ruleAction extends BaseV1Controller {

	private static final long serialVersionUID = 1415728801205620765L;
	
	@Autowired
	private ClientGateRuleBiz clientGateRuleBiz;
	
	/**
	 * 查询客户端道闸规则记录
	 * @param response
	 * @param param
	 * @return
	 */
	@RequestMapping(value ="/get_client_gate_rule_byId")
	public String get_client_gate_rule_byId(HttpServletResponse response,Param_client_gate_ruleT param){
		ReturnDataNew returnData = new ReturnDataNew();
		try {
			//参数检查和签名验证
			if(!checkParam(param,returnData)){
				sendResp(returnData,response);
				return null;
			}
			clientGateRuleBiz.get_client_gate_rule_byId(returnData,param);
		} catch (Exception e) {
			log.error("Read_client_gate_ruleAction get_client_gate_rule_byId is error(DEVICE-JAVA)- P",e);
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
	public boolean checkParam(Param_client_gate_ruleT param,ReturnDataNew returnData) throws Exception{
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
	    
	    String sign_str = getSignature(Constants.SYSTEM_KEY,param.dtype,param.client_ruleid,param.pi_id,param.area_code);
	    if (!param.getSign().equalsIgnoreCase(sign_str)) {
	        log.warn("sign=" + param.getSign() + "  sign_str=" + sign_str);
	        returnData.setReturnData(errorcode_param, " 验证签名失败", null);
	        return false;
	    }
	    
		return true;
	}
}
