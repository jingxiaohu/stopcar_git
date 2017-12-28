package com.park.app.action.v1.rentdefer;

import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.park.app.action.v1.rentdefer.param.Param_Rent_Defer;
import com.park.app.service.RentDeferBiz;
import com.park.bean.ReturnDataNew;
import com.park.constants.Constants;
import com.park.mvc.action.v1.BaseV1Controller;

/**
 * app续约租赁订单管理
 * @author zyy
 */
@RestController
@RequestMapping(value = "/v1")
public class Read_rentDeferAction extends BaseV1Controller{
	
	private static final long serialVersionUID = -3642765346656083359L;
	
	@Autowired
	private RentDeferBiz rentDeferBiz;

	/**
	 * app获取续约租赁订单
	 * @param response
	 * @param param
	 * @return
	 */
	@RequestMapping(value = "/get_rentdefer_info")
	public String get_rentDeferInfo(HttpServletResponse response,Param_Rent_Defer param){
		ReturnDataNew returnData = new ReturnDataNew();
		try {
			//参数检查和参数签名认证
			if(!checkParam(param,returnData)){
				sendResp(returnData, response);
				return null;
			}
			rentDeferBiz.get_rentDeferInfo(returnData, param.ui_id,param.pi_id,param.area_code,param.page,param.size);
		} catch (Exception e) {
			log.error("Read_RentDeferAction get_rentDeferInfo is error(DEVICE-JAVA)- P", e);
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
	public boolean checkParam(Param_Rent_Defer param,ReturnDataNew returnData) throws Exception{
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
        	//用户ID
        	returnData.setReturnData(errorcode_param, " ui_id <= 0", "");
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
