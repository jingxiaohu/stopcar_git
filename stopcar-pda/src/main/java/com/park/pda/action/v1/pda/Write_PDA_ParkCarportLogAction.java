package com.park.pda.action.v1.pda;

import java.net.URLDecoder;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.park.bean.ReturnDataNew;
import com.park.constants.Constants;
import com.park.mvc.action.v1.BaseV1Controller;
import com.park.pda.action.v1.pda.param.Param_pda_park_carport_log;
import com.park.pda.service.PDAParkCarportLogBiz;
import com.park.util.RequestUtil;

/**
 * 停车场车位总数、已停车位数、空余车位数 流水记录
 * @author zyy
 *
 */
@RestController
@RequestMapping(value = "/v1")
public class Write_PDA_ParkCarportLogAction extends BaseV1Controller {

	private static final long serialVersionUID = -7767400388216597296L;
	
	@Autowired
	protected PDAParkCarportLogBiz parkCarportLogBiz;
	
	/**
	 * 记录停车场车位总数、已停车位数、空余车位数 快照（占道、道闸）
	 * @param response
	 * @param param
	 * @return
	 */
	@RequestMapping(value = "/insert_park_carport_log")
	public String insert_park_carport_log (HttpServletResponse response,Param_pda_park_carport_log param){
		ReturnDataNew returnData = new ReturnDataNew();
		try{
			//参数检查和签名验证
			if(!checkParam(param,returnData)){
		        sendResp(returnData, response);
		        return null;
		    }
	        parkCarportLogBiz.insert_park_carport_log(
	        		returnData,
	        		param.pi_id,
	        		param.area_code, 
	        		param.carport_total,
	        		param.carport_yet,
	        		param.carport_space,
	        		param.park_type,
	        		param.data_flag,
	        		param.ctime);
	        
		}catch (Exception e) {
		      log.error("Write_PDA_ParkCarportAction insert_park_carport_log  is error(DEVICE-JAVA)- P", e);
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
	 * @throws Exception
	 */
	private boolean checkParam(Param_pda_park_carport_log param, ReturnDataNew returnData) throws Exception{
		//参数检查
	    if (param == null) {
	      //参数传递错误
	      returnData.setReturnData(errorcode_param, "参数传递错误", "");
	      return false;
	    }
	    //检查是否进行了参数签名认证
	    if (!param.checkRequest()) {
	      returnData.setReturnData(errorcode_param, "没有进行参数签名认证", "");
	      return false;
	    }
	    //对封装的参数对象中的属性进行 非空等规则验证
	    if (RequestUtil.checkObjectBlank(param.getPi_id())) {
	      //场地主键ID
	      returnData.setReturnData(errorcode_param, " pi_id is null", null);
	      return false;
	    }
	    if (RequestUtil.checkIntegerBlankOrNegative(param.getCarport_total())) {
	      returnData.setReturnData(errorcode_param, " carport_total is null or smaller then zero", null);
	      return false;
	    }
	    if (RequestUtil.checkIntegerBlankOrNegative(param.getCarport_yet())) {
	      returnData.setReturnData(errorcode_param, "carport_yet is null or smaller then zero", null);
	      return false;
	    } 
	    if (RequestUtil.checkIntegerBlankOrNegative(param.getCarport_space())) {
	      returnData.setReturnData(errorcode_param, "carport_space is null or smaller then zero", null);
	      return false;
	    }

	    if (!RequestUtil.checkObjectBlank(param.getArea_code())) {
	      //避免汉字的问题
	      param.setArea_code(URLDecoder.decode(param.getArea_code(), Constants.SYSTEM_CHARACTER));
	    }else{
	    	returnData.setReturnData(errorcode_param, "area_code不能为空", null);
	    	return false;
	    }
	    
	    String sign_str = getSignature(Constants.SYSTEM_KEY,param.dtype, param.pi_id,param.area_code);
	    if (!param.getSign().equalsIgnoreCase(sign_str)) {
	      log.warn("sign=" + param.getSign() + "  sign_str=" + sign_str);
	      returnData.setReturnData(errorcode_param, " 验证签名失败", null);
	      return false;
	    }
	    return true;
	  }
	
}
