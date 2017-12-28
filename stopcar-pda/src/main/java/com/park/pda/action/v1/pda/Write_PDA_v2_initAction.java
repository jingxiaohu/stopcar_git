
package com.park.pda.action.v1.pda;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.park.bean.ReturnDataNew;
import com.park.constants.Constants;
import com.park.mvc.action.v1.BaseV1Controller;
import com.park.pda.action.v1.pda.param.Param_init_pda;
import com.park.pda.service.PDAParkinfoBiz;
import com.park.util.RequestUtil;

/**
 * 占道停车场PDA MAC的初始化提交
 * @author jingxiaohu
 *
 */
@Controller
@RequestMapping(value = "/v1")
public class Write_PDA_v2_initAction extends BaseV1Controller {

	/**
	* @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	*/ 
	private static final long serialVersionUID = -3599663972160625262L;

	@Autowired
	protected PDAParkinfoBiz parkinfoBiz;

	/**
	 * 占道停车场PDA MAC的初始化提交
	 * @return
	 */
	@RequestMapping(value = "/init_pda_v2")
	@ResponseBody
	public String init_pda_v2(HttpServletRequest request,HttpServletResponse response,Param_init_pda param){
		ReturnDataNew returnData = new ReturnDataNew();
		 try{
			 //参数检查
			 if(param == null){ 
				 //参数传递错误 
				 returnData.setReturnData(errorcode_param, "参数传递错误", "");
				 sendResp(returnData,response);
				 return null; 
			 }
			 //检查是否进行了参数签名认证
			 if(!param.checkRequest()){
				 returnData.setReturnData(errorcode_param, "没有进行参数签名认证", "");
				 sendResp(returnData,response);
				 return null; 
			 }
			 //对封装的参数对象中的属性进行 非空等规则验证
			if(RequestUtil.checkObjectBlank(param.mac)){
				returnData.setReturnData(errorcode_param, " mac is null", null);
				sendResp(returnData,response);
				return null;
			}else{
				//屏蔽非我们公司采购的POS机MAC提交
				if(!param.mac.startsWith("8696")){
					returnData.setReturnData(errorcode_param, " mac is error", "");
					sendResp(returnData,response);
					return null;
				}
			}
			String sign_str = getSignature(Constants.SYSTEM_KEY, param.dtype,param.mac);
			if(!param.sign.equalsIgnoreCase(sign_str)){
				log.warn("sign="+param.sign+"  sign_str="+sign_str);
				returnData.setReturnData(errorcode_param, " sign is not right", null);
				sendResp(returnData,response);
				return null;
			}
			parkinfoBiz.init_pda_v2(returnData,param.mac);
			
			sendResp(returnData,response);
			return null;
			
			} catch (Exception e) {
				log.error("Write_PDA_v2_initAction init_pda_v2  is error(DEVICE-JAVA)- P",e);
				returnData.setReturnData(errorcode_systerm, "system is error", ""); 
			}
			sendResp(returnData,response);
			return null; 
	}
	
	
}
