package com.park.gate.action.v1.park;

import java.net.URLDecoder;

import javax.servlet.http.HttpServletResponse;

import com.park.gate.action.v1.park.param.Param_heartbeat;
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
 * 停车场心跳表
 * @author jingxiaohu
 *
 */
@Controller
@RequestMapping(value = "/v1")
public class Write_park_heartbeatAction extends BaseV1Controller {


	/**
	* @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	*/ 
	private static final long serialVersionUID = -3599663972160625262L;

	@Autowired
	private GateCheckCarPayBiz gateCheckCarPayBiz;

	/**
	 * 停车场心跳表
	 * @return
	 */
	@RequestMapping(value = "/park_heartbeat")
	@ResponseBody
	public String park_heartbeat(HttpServletResponse response,Param_heartbeat param){
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

			gateCheckCarPayBiz.park_heartbeat(returnData,param.pi_id,param.area_code,param.is_rent,
					param.time,param.park_type,param.carport_yet,param.carport_space,
					param.carport_total,param.moth_car_num,param.moth_car_num_space,param.time_car_num,
					param.time_car_num_space,param.expect_car_num);
			
			sendResp(returnData,response);
			return null;
			
			} catch (Exception e) {
				log.error("Write_park_heartbeatAction park_heartbeat  is error(DEVICE-JAVA)- P",e);
				returnData.setReturnData(errorcode_systerm, "system is error", ""); 
			}
			sendResp(returnData,response);
			return null; 
	}

}
