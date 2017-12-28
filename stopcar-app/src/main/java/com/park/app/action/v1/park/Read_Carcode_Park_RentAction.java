package com.park.app.action.v1.park;

import java.net.URLDecoder;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.park.app.action.v1.park.param.Param_Carcode_Park_Rent;
import com.park.app.service.CarcodeParkRentBiz;
import com.park.bean.ReturnDataNew;
import com.park.constants.Constants;
import com.park.mvc.action.v1.BaseV1Controller;
import com.park.util.RequestUtil;

/**
 * 用户车牌--停车场租赁映射关系查询
 * @author zyy
 */
@RestController
@RequestMapping(value = "/v1")
public class Read_Carcode_Park_RentAction extends BaseV1Controller{

	private static final long serialVersionUID = 3366927675391485832L;
	
	@Autowired
	private CarcodeParkRentBiz carcodeParkRentBiz;
	
	/**
	 * 用户根据车牌号查询租赁信息
	 * @param response
	 * @param param
	 * @return
	 */
	@RequestMapping(value = "/getParkRentInfoByCarcode")
	public String getParkRentInfoByCarcode(HttpServletResponse response,Param_Carcode_Park_Rent param){
		ReturnDataNew returnData = new ReturnDataNew();
		try {
			//参数检查和参数签名认证
			if(!checkParam(param,returnData)){
				sendResp(returnData, response);
				return null;
			}
			carcodeParkRentBiz.getParkRentInfoByCarcode(returnData, param.ui_id,param.car_code,param.page,param.size);
			
		} catch (Exception e) {
			log.error("Read_Carcode_Park_RentAction getParkRentInfoByCarcode is error(DEVICE-JAVA)- P", e);
		    returnData.setReturnData(errorcode_systerm, "system is error", "");
		}
		sendResp(returnData,response);
		return null;
	}

	/**
	 * 参数检查和参数签名认证
	 * @return
	 * @throws Exception 
	 */
	public boolean checkParam(Param_Carcode_Park_Rent param,ReturnDataNew returnData) throws Exception{
		//参数检查
		if(param == null){
			returnData.setReturnData(errorcode_param, "参数传递错误", "");
			return false;
		}
		//参数签名验证
		if(!param.checkRequest()){
			returnData.setReturnData(errorcode_param, "没有进行参数签名认证", "");
		}
		//对封装的参数对象中的属性进行 非空等规则验证
        if (param.getUi_id() <= 0) {
        	//场地主键ID
        	returnData.setReturnData(errorcode_param, " ui_id <= 0", "");
        	return false;
        }
        if (!RequestUtil.checkObjectBlank(param.getCar_code())) {
	        //用户车牌号
	        param.setCar_code(URLDecoder.decode(param.getCar_code(), Constants.SYSTEM_CHARACTER));
	    }else{
	    	returnData.setReturnData(errorcode_param, "car_code is null", null);
	    	return false;
	    }
	    //字符串签名
	    String sign_str = getSignature(Constants.SYSTEM_KEY,param.dtype, param.ui_id);
	    if (!param.sign.equalsIgnoreCase(sign_str)) {
	        log.warn("sign=" + param.sign + "  sign_str=" + sign_str);
	        returnData.setReturnData(errorcode_param, " sign is not right", null);
	        return false;
	    }
   	 
		return true;
	}
}
