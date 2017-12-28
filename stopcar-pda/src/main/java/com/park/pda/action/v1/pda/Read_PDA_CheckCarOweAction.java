
package com.park.pda.action.v1.pda;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.park.bean.ReturnDataNew;
import com.park.constants.Constants;
import com.park.mvc.action.v1.BaseV1Controller;
import com.park.pda.action.v1.pda.param.Param_CheckCarOwe;
import com.park.pda.service.PDAOrderBiz;
import com.park.util.RequestUtil;

/**
 * 获取PDA的对应的车欠费记录
 * @author jingxiaohu
 *
 */
@Controller
@RequestMapping(value = "/v1")
public class Read_PDA_CheckCarOweAction extends BaseV1Controller {

	/**
	* @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	*/ 
	private static final long serialVersionUID = -3599663972160625262L;
	
	@Autowired
	protected PDAOrderBiz orderBiz;
	
	/**
	 * 获取PDA的对应的车欠费记录
	 * PDA 入库/出库 时先获取对应车辆的车欠费记录，如有欠费记录调用 /pda_sure接口进行支付处理
	 * @return
	 */
	@RequestMapping(value = "/pda_check_car_owe")
	@ResponseBody
	public String pda_check_car_owe(HttpServletResponse response,Param_CheckCarOwe param){
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
			 if(RequestUtil.checkObjectBlank(param.car_code)){
					//车牌号
					returnData.setReturnData(errorcode_param, " car_code is null", null);
					sendResp(returnData,response);
					return null;
			 }
			 if(RequestUtil.checkObjectBlank(param.pi_id)){
					//停车场主键ID
					returnData.setReturnData(errorcode_param, " pi_id is null", null);
					sendResp(returnData,response);
					return null;
			 }
			 
			if(RequestUtil.checkObjectBlank(param.area_code)){
				//省市县编号 140107
				returnData.setReturnData(errorcode_param, " area_code is null", null);
				sendResp(returnData,response);
				return null;
			}
			if(RequestUtil.checkObjectBlank(param.type)){
				//类型
				returnData.setReturnData(errorcode_param, " type is null", null);
				sendResp(returnData,response);
				return null;
			}
			 //对封装的参数对象中的属性进行 非空等规则验证
			String sign_str = getSignature(Constants.SYSTEM_KEY, param.dtype,param.pi_id,param.area_code,param.type);
			if(!param.sign.equalsIgnoreCase(sign_str)){
				log.warn("sign="+param.sign+"  sign_str="+sign_str);
				returnData.setReturnData(errorcode_param, " sign is not right", null);
				sendResp(returnData,response);
				return null;
			}
			orderBiz.pda_check_car_Owe(returnData,param.pi_id,param.area_code,param.type,param.car_code);
			
			sendResp(returnData,response);
			return null;
			
			} catch (Exception e) {
				log.error("Read_PDA_CheckEscapeCarAction pda_check_escape_car  is error(DEVICE-JAVA)- P",e);
				returnData.setReturnData(errorcode_systerm, "system is error", ""); 
			}
			sendResp(returnData,response);
			return null; 
	}

    
	
	
}
