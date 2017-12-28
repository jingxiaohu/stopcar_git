
package com.park.gate.action.v1.order;

import java.net.URLDecoder;

import javax.servlet.http.HttpServletResponse;

import com.park.gate.action.v1.order.param.Param_upate_order_state;
import com.park.gate.service.GateOrderBiz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.park.bean.ReturnDataNew;
import com.park.constants.Constants;
import com.park.mvc.action.v1.BaseV1Controller;
import com.park.util.RequestUtil;

/**
 * 更新订单的 预约  取消预约  租赁 状态 
 * @author jingxiaohu
 *
 */
@Controller
@RequestMapping(value = "/v1")
public class Write_UpdateOrderStateAction extends BaseV1Controller {


	/**
	* @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	*/ 
	private static final long serialVersionUID = -3599663972160625262L;

	@Autowired
	private GateOrderBiz gateOrderBiz;

	/**
	 * 更新订单的 预约  取消预约  租赁 状态
	 * @return
	 */
	@RequestMapping(value = "/upate_order_state")
	@ResponseBody
	public String upate_order_state(HttpServletResponse response,Param_upate_order_state param){
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
			if(RequestUtil.checkObjectBlank(param.car_code)){
				//car_code 车牌号
				returnData.setReturnData(errorcode_param, " car_code="+param.car_code+"  car_code is null", null);
				sendResp(returnData,response);
				return null;
			}else{
				param.car_code  = URLDecoder.decode(param.car_code, Constants.SYSTEM_CHARACTER);
			}
			if(RequestUtil.checkObjectBlank(param.orderid)){
				returnData.setReturnData(errorcode_param, " orderid="+param.orderid+"  orderid is null", null);
				sendResp(returnData,response);
				return null;
			}
			if(RequestUtil.checkObjectBlank(param.type)){
				returnData.setReturnData(errorcode_param, " type="+param.type+"  type is null", null);
				sendResp(returnData,response);
				return null;
			}
			if(RequestUtil.checkObjectBlank(param.state)){
				returnData.setReturnData(errorcode_param, " state="+param.state+"  state is null", null);
				sendResp(returnData,response);
				return null;
			}
			String sign_str = getSignature(Constants.SYSTEM_KEY, param.dtype,param.pi_id,param.orderid,param.type);
			if(!param.sign.equalsIgnoreCase(sign_str)){
				log.warn("sign="+param.sign+"  sign_str="+sign_str);
				returnData.setReturnData(errorcode_param, " sign is not right", null);
				sendResp(returnData,response);
				return null;
			}
			 gateOrderBiz.upate_order_state(returnData,param.dtype,param.pi_id,param.car_code,param.area_code,
					param.orderid,param.type,param.state);
			
			sendResp(returnData,response);
			return null;
			
			} catch (Exception e) {
				log.error("Write_UpdateOrderStateAction upate_order_state  is error(DEVICE-JAVA)- P",e);
				returnData.setReturnData(errorcode_systerm, "system is error", ""); 
			}
			sendResp(returnData,response);
			return null; 
	}
	

}
