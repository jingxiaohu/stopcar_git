
package com.park.gate.action.v1.car;

import java.net.URLDecoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.park.gate.action.v1.car.param.Param_check_expectcar;
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
 * 检查该车牌号是否是预约车或者租赁车
 * @author jingxiaohu
 *
 */
@Controller
@RequestMapping(value = "/v1")
public class Read_CheckExpectCarAction extends BaseV1Controller {


	/**
	* @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	*/ 
	private static final long serialVersionUID = -3599663972160625262L;

	@Autowired
	private GateOrderBiz gateOrderBiz;

	/**
	 * 检查该车牌号是否是预约车或者租赁车
	 * @return
	 */
	@Deprecated
	@RequestMapping(value = "/check_expectcar")
	@ResponseBody
	public String check_expectcar(HttpServletRequest request,HttpServletResponse response,Param_check_expectcar param){
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
				//场地主键ID
				returnData.setReturnData(errorcode_param, " pi_id="+param.pi_id+"  pi_id is smaller than zero", null);
				sendResp(returnData,response);
				return null;
			}
			
			if(RequestUtil.checkObjectBlank(param.car_code)){
				//car_code;//车牌号
				returnData.setReturnData(errorcode_param, " car_code="+param.car_code+"  car_code is null", null);
				sendResp(returnData,response);
				return null;
			}else{
				param.car_code  = URLDecoder.decode(param.car_code, Constants.SYSTEM_CHARACTER);
			}
			
			if(!RequestUtil.checkObjectBlank(param.area_code)){
				//省市县编号 140107
				//避免汉子的问题
				param.area_code = URLDecoder.decode(param.area_code, Constants.SYSTEM_CHARACTER);
			}
			String sign_str = getSignature(Constants.SYSTEM_KEY, param.dtype,param.pi_id);
			if(!param.sign.equalsIgnoreCase(sign_str)){
				log.warn("sign="+param.sign+"  sign_str="+sign_str);
				returnData.setReturnData(errorcode_param, " sign is not right", null);
				sendResp(returnData,response);
				return null;
			}
			 gateOrderBiz.check_expectcar(returnData,param.area_code,param.pi_id,param.car_code,
					param.car_type,param.car_code_color);
			
			sendResp(returnData,response);
			return null;
			
			} catch (Exception e) {
				log.error("Read_bindcarAction read_bindcar  is error(DEVICE-JAVA)- P",e);
				returnData.setReturnData(errorcode_systerm, "system is error", ""); 
			}
			sendResp(returnData,response);
			return null; 
	}
	

}
