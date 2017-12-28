package com.park.pda.action.v1.magnetic;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.park.bean.ReturnDataNew;
import com.park.constants.Constants;
import com.park.mvc.action.v1.BaseV1Controller;
import com.park.pda.action.v1.magnetic.param.Param_change_device_state;
import com.park.pda.service.PDAChangeDeviceBiz;

/**
 * 修改设备状态
 * @ClassName:  ChangeDeviceController   
 * @Description:TODO  
 * @author: xxy 
 * @date:   2017年5月18日 下午4:13:46   
 *
 */
@SuppressWarnings("serial")
@Controller
@RequestMapping(value = "/v1")
public class Write_changeDeviceStateAction extends BaseV1Controller{
	
	@Autowired
	protected PDAChangeDeviceBiz changeDeviceBiz;

	/**
	 * 地磁设备状态变更，同时修正占道停车场车位数量、把占道停车场内车位数量写入到日志表
	 * 地磁设备状态变更时，当设备状态为有车时，再根据订单支付状态更新订单表中magnetic_state（智能磁场入库出库状态）和is_over（订单是否完成或者逃逸）
	 * @param response
	 * @param param
	 * @return
	 */
	@RequestMapping(value = "/change_magnetic_device_state")
	@ResponseBody
	public String updateState(HttpServletResponse response,Param_change_device_state param){
		ReturnDataNew returnData = new ReturnDataNew();
		try {
			//参数检查
		    if (param == null) {
		      returnData.setReturnData(errorcode_param, "参数传递错误", "");
		      sendResp(returnData, response);
		      return null;
		    }
		    //检查是否进行了参数签名认证
		    if (!param.checkRequest()) {
		      returnData.setReturnData(errorcode_param, "没有进行参数签名认证", "");
		      sendResp(returnData, response);
		      return null;
		    }
		    Integer piId = param.getPi_id();
		    if (piId == null) {
		    	returnData.setReturnData(errorcode_param, "pi_id不能为空", null);
		    	sendResp(returnData, response);
		    	return null;
		    }
		    String areaCode = param.getArea_code();
	        if (!StringUtils.hasText(areaCode)) {
	        	returnData.setReturnData(errorcode_param, "area_code不能为空", null);
	        	sendResp(returnData, response);
	        	return null;
	        }
	        Integer carPortYet = param.getCar_port_yet();
	        if (carPortYet == null) {
	        	returnData.setReturnData(errorcode_param, "car_port_yet不能为空", null);
	        	sendResp(returnData, response);
	        	return null;
	        }
	        Integer carPortSpace = param.getCar_port_space();
	        if (carPortSpace == null) {
	        	returnData.setReturnData(errorcode_param, "car_port_space不能为空", null);
	        	sendResp(returnData, response);
	        	return null;
	        }
	        Integer carPortTotal = param.getCar_port_total();
	        if (carPortTotal == null) {
	        	returnData.setReturnData(errorcode_param, "car_port_total不能为空", null);
	        	sendResp(returnData, response);
	        	return null;
	        }
	        String car_dev_num = param.getCar_dev_num();
	        if (!StringUtils.hasText(car_dev_num)) {
	        	returnData.setReturnData(errorcode_param, "car_dev_num不能为空", null);
	        	sendResp(returnData, response);
	        	return null;
	        }
	        Integer state = param.getState();
	        if (state == null) {
	        	returnData.setReturnData(errorcode_param, "state不能为空", null);
	        	sendResp(returnData, response);
	        	return null;
	        }
	        String android_dev_num = param.getAndroid_dev_num();
	        if (!StringUtils.hasText(android_dev_num)) {
	        	returnData.setReturnData(errorcode_param, "android_dev_num不能为空", null);
	        	sendResp(returnData, response);
	        	return null;
	        }
	        Long ctime = param.getCtime();
	        if(null == ctime){
	        	returnData.setReturnData(errorcode_param, "ctime不能为空", null);
	        	sendResp(returnData, response);
	        	return null;
	        }
	        String sign_str = getSignature(Constants.SYSTEM_KEY, param.pi_id,param.area_code,param.car_dev_num,
	        		param.car_port_yet,param.car_port_space,param.car_port_total,param.state,param.android_dev_num);
	        
	        if (!param.sign.equalsIgnoreCase(sign_str)) {
	            log.warn("sign=" + param.sign + "  sign_str=" + sign_str);
	            returnData.setReturnData(errorcode_param, " sign is not right", null);
	            sendResp(returnData, response);
	            return null;
	          }
	        
	        log.info("修改设备状态接口参数："+param.toString());
		    
		    
			
			changeDeviceBiz.updateDevice(returnData, param);
		} catch (Exception e) {
			e.printStackTrace();
			returnData.setReturnData(errorcode_systerm, "system is error", "");
			
		}
		
		sendResp(returnData,response);
		return null;
		
	}
	

}
