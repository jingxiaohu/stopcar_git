
package com.park.pda.action.v1.pda;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.park.bean.ReturnDataNew;
import com.park.constants.Constants;
import com.park.mvc.action.v1.BaseV1Controller;
import com.park.pda.action.v1.pda.param.Param_pda_expect_order;
import com.park.pda.service.PDAOrderBiz;

/**
 * 获取PDA的预约且未付款和未取消的订单
 * @author jingxiaohu
 *
 */
@Controller
@RequestMapping(value = "/v1")
public class Read_PDA_ExpectOrderAction extends BaseV1Controller {

	@Autowired
	protected PDAOrderBiz orderBiz;

	/**
	* @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	*/ 
	private static final long serialVersionUID = -3599663972160625262L;
	
	/**
	 * 获取PDA的预约且未付款和未取消的订单
	 * @return
	 */
	@RequestMapping(value = "/pda_expect_order")
	@ResponseBody
	public String pda_expect_order(HttpServletResponse response,Param_pda_expect_order param){
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
			String sign_str = getSignature(Constants.SYSTEM_KEY, param.dtype,param.pi_id,param.area_code);
			if(!param.sign.equalsIgnoreCase(sign_str)){
				log.warn("sign="+param.sign+"  sign_str="+sign_str);
				returnData.setReturnData(errorcode_param, " sign is not right", null);
				sendResp(returnData,response);
				return null;
			}
			orderBiz.pda_expect_order(returnData,param.dtype,param.area_code,param.pi_id,param.page,param.size);
			
			sendResp(returnData,response);
			return null;
			
			} catch (Exception e) {
				log.error("Read_PdaExpectOrderAction pda_expect_order  is error(DEVICE-JAVA)- P",e);
				returnData.setReturnData(errorcode_systerm, "system is error", ""); 
			}
			sendResp(returnData,response);
			return null; 
	}

}
