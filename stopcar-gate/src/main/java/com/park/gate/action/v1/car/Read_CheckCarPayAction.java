
package com.park.gate.action.v1.car;

import java.net.URLDecoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.park.gate.action.v1.car.param.Param_read_checkpay;
import com.park.gate.service.GateCheckCarPayBiz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.park.bean.ReturnDataNew;
import com.park.constants.Constants;
import com.park.mvc.action.v1.BaseV1Controller;
import com.park.util.RequestUtil;

/**
 * 检查某停车场某车牌号是否已经付款
 * @author jingxiaohu
 *
 */
@Controller
@RequestMapping(value = "/v1")
public class Read_CheckCarPayAction extends BaseV1Controller {


	/**
	* @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	*/ 
	private static final long serialVersionUID = -3599663972160625262L;

	@Autowired
	private GateCheckCarPayBiz gateCheckCarPayBiz;

	/**
	 * 检查某停车场某车牌号是否已经付款
	 * @return
	 */
	@RequestMapping(value = "/read_checkpay")
	@ResponseBody
	public String read_checkpay(HttpServletRequest request,HttpServletResponse response,Param_read_checkpay param){
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
			if(param.pi_id < 1){
				//停车场主键ID
				returnData.setReturnData(errorcode_param, " pi_id="+param.pi_id+"  pi_id is smaller than zero", null);
				sendResp(returnData,response);
				return null;
			}
			if(RequestUtil.checkObjectBlank(param.orderid)){
				//orderid;//我们的订单号  字符串
				returnData.setReturnData(errorcode_param, " orderid="+param.orderid+"  orderid is null", null);
				sendResp(returnData,response);
				return null;
			}
			if(RequestUtil.checkObjectBlank(param.car_code)){
				//car_code 车牌号
				returnData.setReturnData(errorcode_param, " car_code="+param.car_code+"  car_code is null", null);
				sendResp(returnData,response);
				return null;
			}else{
				param.car_code  = URLDecoder.decode(param.car_code, Constants.SYSTEM_CHARACTER);
			}
			if(RequestUtil.checkObjectBlank(param.area_code)){
				//area_code 省市区区域代码  四川省 成都市 龙泉驿区  510112
				returnData.setReturnData(errorcode_param, " area_code="+param.area_code+"  area_code is null", null);
				sendResp(returnData,response);
				return null;
			}
			
			String sign_str = getSignature(Constants.SYSTEM_KEY, param.dtype,param.pi_id);
			if(!param.sign.equalsIgnoreCase(sign_str)){
				log.warn("sign="+param.sign+"  sign_str="+sign_str);
				returnData.setReturnData(errorcode_param, " sign is not right", null);
				sendResp(returnData,response);
				return null;
			}
			gateCheckCarPayBiz.read_checkpay(returnData,param.pi_id,param.car_code,param.area_code,param.orderid);
			
			sendResp(returnData,response);
			return null;
			
			} catch (Exception e) {
				log.error("Read_CheckCarPayAction read_checkpay  is error(DEVICE-JAVA)- P",e);
				returnData.setReturnData(errorcode_systerm, "system is error", ""); 
			}
			sendResp(returnData,response);
			return null; 
	}
	
	
}
