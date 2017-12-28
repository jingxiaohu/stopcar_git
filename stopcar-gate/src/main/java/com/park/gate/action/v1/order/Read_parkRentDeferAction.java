package com.park.gate.action.v1.order;

import com.park.bean.ReturnDataNew;
import com.park.constants.Constants;
import com.park.gate.action.v1.order.param.rentDefer.RentDeferSelect;
import com.park.gate.service.RentDeferByGateBiz;
import com.park.mvc.action.v1.BaseV1Controller;
import com.park.util.RequestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.net.URLDecoder;

@RestController
@RequestMapping(value = "/v1")
public class Read_parkRentDeferAction extends BaseV1Controller{

	private static final long serialVersionUID = 4455961979257798204L;
	
	@Autowired
	private RentDeferByGateBiz rentDeferService;
	
	/**
	 * app获取续约租赁订单
	 * @param response
	 * @param param
	 * @return
	 */
	@RequestMapping(value = "/get_park_rentdefer_info")
	public String get_rentDeferInfo(HttpServletResponse response,RentDeferSelect param){
		ReturnDataNew returnData = new ReturnDataNew();
		try {
			//参数检查和参数签名认证
			if(!checkParam(param,returnData)){
				sendResp(returnData, response);
				return null;
			}
			rentDeferService.getRentDeferInfo(returnData, param);
		} catch (Exception e) {
			log.error("Read_rentDeferAction get_rentDeferInfo is error(DEVICE-JAVA)- P", e);
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
	public boolean checkParam(RentDeferSelect param,ReturnDataNew returnData) throws Exception{
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
		if (param.getPi_id() == null) {
		    //场地主键ID
		    returnData.setReturnData(errorcode_param, " pi_id is null", "");
		    return false;
		}
        if (!RequestUtil.checkObjectBlank(param.getArea_code())) {
	        //地址区域编码
	        param.setArea_code(URLDecoder.decode(param.getArea_code(), Constants.SYSTEM_CHARACTER));
	    }else{
	    	returnData.setReturnData(errorcode_param, "area_code is null", null);
	    	return false;
	    }
   	 
	    //字符串签名
	    String sign_str = getSignature(Constants.SYSTEM_KEY,param.dtype, param.getPi_id(),param.getArea_code());
	    if (!param.sign.equalsIgnoreCase(sign_str)) {
	        log.warn("sign=" + param.sign + "  sign_str=" + sign_str);
	        returnData.setReturnData(errorcode_param, " sign is not right", null);
	        return false;
	    }
   	 
		return true;
	}
}
