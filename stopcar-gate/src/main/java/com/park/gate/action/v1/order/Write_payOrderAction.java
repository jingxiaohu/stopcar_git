
package com.park.gate.action.v1.order;

import javax.servlet.http.HttpServletResponse;

import com.park.gate.action.v1.order.param.Param_payment;
import com.park.gate.service.GateOrderBiz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.park.bean.ReturnDataNew;
import com.park.constants.Constants;
import com.park.mvc.action.v1.BaseV1Controller;

/**
 * 扫描到车辆出库扣费
 * @author jingxiaohu
 *
 */
@Controller
@RequestMapping(value = "/v1")
public class Write_payOrderAction extends BaseV1Controller {


	/**
	* @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	*/ 
	private static final long serialVersionUID = -3599663972160625262L;

	@Autowired
	private GateOrderBiz gateOrderBiz;

	/**
	 *  扫描到车辆出库扣费
	 */
	@RequestMapping(value = "/payment")
	@ResponseBody
	public String payment(HttpServletResponse response,Param_payment param){
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
			String sign_str = getSignature(Constants.SYSTEM_KEY, param.dtype,param.orderid,param.pi_id);
			if(!param.sign.equalsIgnoreCase(sign_str)){
				log.warn("sign="+param.sign+"  sign_str="+sign_str);
				returnData.setReturnData(errorcode_param, " sign is not right", null);
				sendResp(returnData,response);
				return null;
			}
			 gateOrderBiz.payment(returnData,param.dtype,param.orderid,param.is_rent,param.pi_id,param.car_code,param.area_code,param.money,param.final_time);
			
			sendResp(returnData,response);
			return null;
			
			} catch (Exception e) {
				log.error("Write_makeOrderAction pay_order  is error(DEVICE-JAVA)- P",e);
				returnData.setReturnData(errorcode_systerm, "system is error", ""); 
			}
			sendResp(returnData,response);
			return null; 
	}
	
	
	
	

	
}
