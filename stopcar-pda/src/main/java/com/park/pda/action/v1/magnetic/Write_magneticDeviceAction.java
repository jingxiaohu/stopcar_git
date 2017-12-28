package com.park.pda.action.v1.magnetic;

import com.park.bean.ReturnDataNew;
import com.park.constants.Constants;
import com.park.mvc.action.v1.BaseV1Controller;
import com.park.pda.action.v1.magnetic.param.Param_magnetic_device;
import com.park.pda.service.PDAMagneticDeviceBiz;
import com.park.util.RequestUtil;

import java.net.URLDecoder;

import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 设备编号管理
 * @author zyy
 *
 */
@RestController
@RequestMapping(value = "/v1")
public class Write_magneticDeviceAction extends BaseV1Controller {

	private static final long serialVersionUID = -6384944410028965425L;
	
	@Autowired
	protected PDAMagneticDeviceBiz magneticDeviceBiz;
	
	/**
	 * 地磁车位号和地磁设备编号绑定关系
	 * 车位设备编码（car_dev_num = pi_id + 下划线 + area_code + 下划线 + magnetic_device_id）
	 * @param response
	 * @param param
	 * @return
	 */
	@RequestMapping(value = "/bind_magnetic_device")
	public String bindMagneticDevice (HttpServletResponse response,Param_magnetic_device param){
		ReturnDataNew returnData = new ReturnDataNew();
		try{
			//参数检查和参数签名认证
	        if(!checkParamT(param,returnData)){
	        	sendResp(returnData, response);
		        return null;
	        }
	        
	        magneticDeviceBiz.bindMagneticDevice(returnData,param.pi_id,param.area_code, param.gov_num,param.car_dev_num,param.android_dev_num);
		}catch (Exception e) {
		      log.error("Write_magneticDeviceAction bindMagneticDevice  is error(DEVICE-JAVA)- P", e);
		      returnData.setReturnData(errorcode_systerm, "system is error", "");
		 }
		sendResp(returnData, response);
		return null;
	}

	/**
	 * 更新地磁车位号和设备号绑定关系，pi_id、area_code和gov_num条件组合作为主键
	 * @param response
	 * @param param
	 * @return
	 */
	@RequestMapping(value = "/update_magnetic_device")
	public String update_magneticDevice (HttpServletResponse response,Param_magnetic_device param){
		ReturnDataNew returnData = new ReturnDataNew();
		try{
			//参数检查和参数签名认证
	        if(!checkParamT(param,returnData)){
	        	sendResp(returnData, response);
		        return null;
	        }
	        
	        magneticDeviceBiz.update_magneticDevice(returnData,param.pi_id,param.area_code, param.gov_num,param.car_dev_num);
	        
		}catch (Exception e) {
		      log.error("Write_magneticDeviceAction update_magneticDevice  is error(DEVICE-JAVA)- P", e);
		      returnData.setReturnData(errorcode_systerm, "system is error", "");
		 }
		sendResp(returnData, response);
		return null;
	}
	
	/**
	 * 地磁车位号和设备号绑定关系逻辑删除，pi_id、area_code和gov_num条件组合作为主键
	 * @param response
	 * @param param
	 * @return
	 */
	@RequestMapping(value = "/delete_magnetic_device")
	public String delete_magneticDevice(HttpServletResponse response,Param_magnetic_device param){
		ReturnDataNew returnData = new ReturnDataNew();
		try{
			//参数检查和参数签名认证
	        if(!checkParam(param,returnData)){
	        	sendResp(returnData, response);
		        return null;
	        }
	        
	        magneticDeviceBiz.delete_magneticDevice(returnData,param.pi_id,param.area_code, param.gov_num);
	        
		}catch (Exception e) {
		      log.error("Write_magneticDeviceAction delete_magneticDevice  is error(DEVICE-JAVA)- P", e);
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
	 */
	public boolean checkParam(Param_magnetic_device param,ReturnDataNew returnData) throws Exception{
		//参数检查
		if(param == null){
			returnData.setReturnData(errorcode_param, "参数传递错误","");
			return false;
		}
		//参数签名验证
		if(!param.checkRequest()){
			returnData.setReturnData(errorcode_param, "没有进行参数签名认证", "");
		}
		//对封装的参数对象中的属性进行 非空等规则验证
		if (param.getPi_id() == null) {
		      //场地主键ID
		      returnData.setReturnData(errorcode_param, " pi_id is null", null);
		      return false;
		    }
        if (!RequestUtil.checkObjectBlank(param.getArea_code())) {
	        //地址区域编码
	        param.setArea_code(URLDecoder.decode(param.getArea_code(), Constants.SYSTEM_CHARACTER));
	    }else{
	    	returnData.setReturnData(errorcode_param, "area_code is null", null);
	    	return false;
	    }
        if (!StringUtils.hasText(param.getGov_num())) {
        	returnData.setReturnData(errorcode_param, "gov_num is null", null);
        	return false;
        }
        
        //字符串签名
        String sign_str = getSignature(Constants.SYSTEM_KEY,param.dtype, param.pi_id,param.area_code,param.gov_num);
        if (!param.sign.equalsIgnoreCase(sign_str)) {
            log.warn("sign=" + param.sign + "  sign_str=" + sign_str);
            returnData.setReturnData(errorcode_param, " sign is not right", null);
            return false;
        }
		
		return true;
	}
	
	/**
	 * 新增和修改参数检查和签名验证
	 * @param param
	 * @param returnData
	 * @return
	 */
	public boolean checkParamT(Param_magnetic_device param,ReturnDataNew returnData) throws Exception{
		//参数检查
		if(param == null){
			returnData.setReturnData(errorcode_param, "参数传递错误","");
			return false;
		}
		//参数签名验证
		if(!param.checkRequest()){
			returnData.setReturnData(errorcode_param, "没有进行参数签名认证", "");
		}
		//对封装的参数对象中的属性进行 非空等规则验证
		if (param.getPi_id() == null ) {
			//场地主键ID
			returnData.setReturnData(errorcode_param, " pi_id is null", null);
			return false;
		}
		if (!RequestUtil.checkObjectBlank(param.getArea_code())) {
			//地址区域编码
			param.setArea_code(URLDecoder.decode(param.getArea_code(), Constants.SYSTEM_CHARACTER));
		}else{
			returnData.setReturnData(errorcode_param, "area_code is null", null);
			return false;
		}
		if (!StringUtils.hasText(param.getGov_num())) {
			returnData.setReturnData(errorcode_param, "gov_num is null", null);
			return false;
		}
		if (!StringUtils.hasText(param.getCar_dev_num())) {
			returnData.setReturnData(errorcode_param, "car_dev_num is null", null);
			return false;
		}
		//字符串签名
		String sign_str = getSignature(Constants.SYSTEM_KEY, param.pi_id,param.area_code,param.gov_num,param.car_dev_num);
		if (!param.sign.equalsIgnoreCase(sign_str)) {
			log.warn("sign=" + param.sign + "  sign_str=" + sign_str);
			returnData.setReturnData(errorcode_param, " sign is not right", null);
			return false;
		}
		
		return true;
	}
}
