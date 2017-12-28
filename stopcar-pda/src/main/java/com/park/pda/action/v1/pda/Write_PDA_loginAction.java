
package com.park.pda.action.v1.pda;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.park.bean.ReturnDataNew;
import com.park.constants.Constants;
import com.park.mvc.action.v1.BaseV1Controller;
import com.park.pda.action.v1.pda.param.Param_init_pda;
import com.park.pda.service.PDAParkinfoBiz;
import com.park.util.RequestUtil;

/**
 * 占道停车场PDA登录
 * @author jingxiaohu
 *
 */
@Controller
@RequestMapping(value = "/v1")
public class Write_PDA_loginAction extends BaseV1Controller {


	/**
	* @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	*/ 
	private static final long serialVersionUID = -3599663972160625262L;
	
	@Autowired
	protected PDAParkinfoBiz parkinfoBiz;

	/**
	 * 占道停车场PDA登录
	 * ①校验用户名或者密码或者mac是否正确（mac为PDA初始化时提交）
	 * ②校验用户是否绑定停车场（pi_id和area_code是否为空，根据pi_id和area_code是否能查询到停车场信息）
	 * ③设置PDA设备的当前版本号
	 * ④记录硬件设备版本升级日志
	 * ⑤记录停车场硬件设备信息
	 * ⑥登录成功 
	 * @return
	 */
	@RequestMapping(value = "/pda_login")
	@ResponseBody
	public String pda_login(HttpServletRequest request,HttpServletResponse response,Param_init_pda param){
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
			 /*if(param.lng == 0){
				//lng; //地理经度
				returnData.setReturnData(errorcode_param, " lng="+param.lng+"  lng is zero", null);
				sendResp(returnData,response);
				return null;
			}
			if(param.lat == 0){
				//lat;//地理纬度
				returnData.setReturnData(errorcode_param, " lat="+param.lat+"  lat is zero", null);
				sendResp(returnData,response);
				return null;
			}*/
			if(RequestUtil.checkObjectBlank(param.loginname)){
				//帐号
				returnData.setReturnData(errorcode_param, " loginname="+param.loginname+"  loginname is null", null);
				sendResp(returnData,response);
				return null;
			}
			
			if(RequestUtil.checkObjectBlank(param.password)){
				//密码
				returnData.setReturnData(errorcode_param, " password="+param.password+"  password is null", null);
				sendResp(returnData,response);
				return null;
			}
			if(RequestUtil.checkObjectBlank(param.mac)){
				//MAC
				returnData.setReturnData(errorcode_param, " mac="+param.mac+"  mac is null", null);
				sendResp(returnData,response);
				return null;
			}
			if(param.vnum == 0){
				returnData.setReturnData(errorcode_param, "vnum is null", null);
				sendResp(returnData,response);
				return null;
			}
			String sign_str = getSignature(Constants.SYSTEM_KEY, param.dtype,param.loginname,param.password,param.mac);
			if(!param.sign.equalsIgnoreCase(sign_str)){
				log.warn("sign="+param.sign+"  sign_str="+sign_str);
				returnData.setReturnData(errorcode_param, " sign is not right", null);
				sendResp(returnData,response);
				return null;
			}
			parkinfoBiz.pda_login(returnData,param.lng,param.lat,param.loginname,param.password,param.mac,param.vnum);
			
			sendResp(returnData,response);
			return null;
			
			} catch (Exception e) {
				log.error("Write_PDA_v2_loginAction pda_login  is error(DEVICE-JAVA)- P",e);
				returnData.setReturnData(errorcode_systerm, "system is error", ""); 
			}
			sendResp(returnData,response);
			return null; 
	}
	
	
}
