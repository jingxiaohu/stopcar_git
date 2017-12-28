
package com.park.gate.action.v1.park;

import java.net.URLDecoder;

import javax.servlet.http.HttpServletResponse;

import com.park.gate.action.v1.park.param.Param_park_device;
import com.park.gate.service.GateParkinfoBiz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.park.bean.ReturnDataNew;
import com.park.constants.Constants;
import com.park.mvc.action.v1.BaseV1Controller;
import com.park.util.RequestUtil;

/**
 * 记录场地出入口设备对应关系信息
 * @author jingxiaohu
 *
 */
@Controller
@RequestMapping(value = "/v1")
public class Write_park_deviceAction extends BaseV1Controller {


	/**
	* @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	*/ 
	private static final long serialVersionUID = -3599663972160625262L;

	@Autowired
	private GateParkinfoBiz gateParkinfoBiz;

	/**
	 * 记录场地出入口设备对应关系信息
	 * @return
	 */
	@RequestMapping(value = "/record_park_device")
	@ResponseBody
	public String record_park_device(HttpServletResponse response,Param_park_device param){
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
			
			if(RequestUtil.checkObjectBlank(param.in_out)){
				//in_out;//出口或者入口 入口：enter  出口：exit
				returnData.setReturnData(errorcode_param, " in_out="+param.in_out+"  in_out is null", null);
				sendResp(returnData,response);
				return null;
			}
			if(RequestUtil.checkObjectBlank(param.in_out_code)){
				//in_out_code;//出入口编号  例如(A出口 B入口)
				returnData.setReturnData(errorcode_param, " in_out_code="+param.in_out_code+"  in_out_code is null", null);
				sendResp(returnData,response);
				return null;
			}
			if(RequestUtil.checkObjectBlank(param.camera)){
				//camera;//摄像头名称
				returnData.setReturnData(errorcode_param, " camera="+param.camera+"  camera is null", null);
				sendResp(returnData,response);
				return null;
			}else{
				param.camera = URLDecoder.decode(param.camera, Constants.SYSTEM_CHARACTER);
			}
			//by jxh 2017-6-28 因为道闸目前拿不到该设备的MAC
			/*if(RequestUtil.checkObjectBlank(param.camera_mac)){
				//camera_mac;//摄像头MAC
				returnData.setReturnData(errorcode_param, " camera_mac="+param.camera_mac+"  camera_mac is null", null);
				sendResp(returnData,response);
				return null;
			}*/
			if(RequestUtil.checkObjectBlank(param.signo_name)){
				//signo_name;//道闸名称
				returnData.setReturnData(errorcode_param, " signo_name="+param.signo_name+"  signo_name is null", null);
				sendResp(returnData,response);
				return null;
			}else{
				param.signo_name = URLDecoder.decode(param.signo_name, Constants.SYSTEM_CHARACTER);
			}
			
			/*if(RequestUtil.checkObjectBlank(solid_garage_mac)){
				//solid_garage;//立体车库设备MAC
				returnData.setReturnData(errorcode_param, " solid_garage="+solid_garage_mac+"  solid_garage is null", null);
				sendResp(returnData,response);
				return null;
			}
			if(RequestUtil.checkObjectBlank(solid_garage_sn)){
				//solid_garage_sn;//立体车库设备编号
				returnData.setReturnData(errorcode_param, " solid_garage_sn="+solid_garage_sn+"  solid_garage_sn is null", null);
				sendResp(returnData,response);
				return null;
			}*/
			if(!RequestUtil.checkObjectBlank(param.area_code)){
				//省市县编号 140107
				//避免汉子的问题
				param.area_code = URLDecoder.decode(param.area_code, Constants.SYSTEM_CHARACTER);
			}
			String sign_str = getSignature(Constants.SYSTEM_KEY, param.dtype,param.pi_id,param.in_out);
			if(!param.sign.equalsIgnoreCase(sign_str)){
				log.warn("sign="+param.sign+"  sign_str="+sign_str);
				returnData.setReturnData(errorcode_param, " sign is not right", null);
				sendResp(returnData,response);
				return null;
			}
			gateParkinfoBiz.record_park_device(returnData,param.pi_id,param.in_out,param.in_out_code,param.camera,
					param.camera_mac,param.signo_name,param.solid_garage_mac,param.solid_garage_sn,
					param.area_code);
			
			sendResp(returnData,response);
			return null;
			
			} catch (Exception e) {
				log.error("Write_rental_charging_ruleAction charging_rule  is error(DEVICE-JAVA)- P",e);
				returnData.setReturnData(errorcode_systerm, "system is error", ""); 
			}
			sendResp(returnData,response);
			return null; 
	}
	
	
	/**
	 * 更新场地出入口设备对应关系信息
	 * @return
	 */
	@RequestMapping(value = "/update_park_device")
	@ResponseBody
	public String update_park_device(HttpServletResponse response,Param_park_device param){
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
			if(param.pd_id < 1){
				//场地出入口管理主键ID
				returnData.setReturnData(errorcode_param, " pd_id="+param.pd_id+"  pd_id is smaller zero", null);
				sendResp(returnData,response);
				return null;
			}
			if(!RequestUtil.checkObjectBlank(param.camera)){
				//camera;//摄像头名称
				param.camera = URLDecoder.decode(param.camera, Constants.SYSTEM_CHARACTER);
			}
			if(!RequestUtil.checkObjectBlank(param.signo_name)){
				param.signo_name = URLDecoder.decode(param.signo_name, Constants.SYSTEM_CHARACTER);
			}
			if(!RequestUtil.checkObjectBlank(param.area_code)){
				//省市县编号 140107
				//避免汉子的问题
				param.area_code = URLDecoder.decode(param.area_code, Constants.SYSTEM_CHARACTER);
			}
			
			String sign_str = getSignature(Constants.SYSTEM_KEY, param.dtype,param.pd_id);
			if(!param.sign.equalsIgnoreCase(sign_str)){
				log.warn("sign="+param.sign+"  sign_str="+sign_str);
				returnData.setReturnData(errorcode_param, " sign is not right", null);
				sendResp(returnData,response);
				return null;
			}

			gateParkinfoBiz.update_park_device(returnData,param.pd_id,param.pi_id,param.in_out,param.in_out_code,
					param.camera,param.camera_mac,param.signo_name,param.solid_garage_mac,
					param.solid_garage_sn,param.area_code);
			
			sendResp(returnData,response);
			return null;
			
			} catch (Exception e) {
				log.error("Write_rental_charging_ruleAction update_charging_rule  is error(DEVICE-JAVA)- P",e);
				returnData.setReturnData(errorcode_systerm, "system is error", ""); 
			}
			sendResp(returnData,response);
			return null; 
	}
	




	
	
	
}
