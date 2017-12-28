
package com.park.app.action.v1.user;

import com.park.app.service.SMSBiz;
import com.park.app.action.v1.user.param.Param_sendcode;
import com.park.bean.ReturnDataNew;
import com.park.constants.Constants;
import com.park.mvc.action.v1.BaseV1Controller;
import com.park.util.RequestUtil;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 用户发送验证码
 *
 * @author jingxiaohu
 */
@Controller
@RequestMapping(value = "/v1")
public class Write_SendCodeAction extends BaseV1Controller {


  /**
   * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
   */
  private static final long serialVersionUID = -3599663972160625263L;

  @Autowired
  private SMSBiz smsBiz;

  @RequestMapping(value = "/sendcode")
  @ResponseBody
  public String UserSendCode(HttpServletRequest request, HttpServletResponse response,
      Param_sendcode param) {
    ReturnDataNew returnData = new ReturnDataNew();
    try {
      //检查是否是合法请求
      String ip = getIpAddr(request);
      if (ip.startsWith("192.168") || ip.startsWith("127.0")) {
        ip = null;
      }
      //参数检查
      if (param == null) {
        //参数传递错误
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
      //对封装的参数对象中的属性进行 非空等规则验证
      if (RequestUtil.checkObjectBlank(param.tel)) {
        returnData.setReturnData(errorcode_param, " tel is null", null);
        sendResp(returnData, response);
        return null;
      }
      if (!isMobileNO(param.tel)) {
        returnData.setReturnData(errorcode_param, "亲，请输入正确的手机号", null);
        sendResp(returnData, response);
        return null;
      }
      //1：注册 2：重置密码
      if (RequestUtil.checkObjectBlank(param.vclass)) {
        returnData.setReturnData(errorcode_param, " vclass is null", null);
        sendResp(returnData, response);
        return null;
      }
      String sign_str = getSignature(Constants.SYSTEM_KEY, param.dtype, param.tel, param.vclass);
      if (!param.sign.equalsIgnoreCase(sign_str)) {
        log.warn("sign=" + param.sign + "  sign_str=" + sign_str);
        returnData.setReturnData(errorcode_param, " sign is not right", null);
        sendResp(returnData, response);
        return null;
      }

      smsBiz.ReturnSendSMg(returnData, param.dtype, param.tel, param.vclass);
      sendResp(returnData, response);
      return null;

    } catch (Exception e) {
      log.error("UserSendCode is error  2.21	Read-发送验证码(APPSDK-JAVA)- P", e);
      returnData.setReturnData(errorcode_systerm, "system is error", "");
    }
    sendResp(returnData, response);
    return null;
  }

  /**
   * 重发验证码
   */
  @RequestMapping(value = "/resendcode")
  @ResponseBody
  public String UserReSendCode(HttpServletRequest request, HttpServletResponse response,
      Param_sendcode param) {
    ReturnDataNew returnData = new ReturnDataNew();
    try {
      //检查是否是合法请求
      String ip = getIpAddr(request);
      if (ip.startsWith("192.168") || ip.startsWith("127.0")) {
        ip = null;
      }
      //2.1.4. 用户注册完成
      if (RequestUtil.checkObjectBlank(param.sign)) {
        returnData.setReturnData(errorcode_param, " sign is null", null);
        sendResp(returnData, response);
        return null;
      }
      if (RequestUtil.checkObjectBlank(param.tel)) {
        returnData.setReturnData(errorcode_param, " tel is null", null);
        sendResp(returnData, response);
        return null;
      }
      if (!isMobileNO(param.tel)) {
        returnData.setReturnData(errorcode_param, "亲，请输入正确的手机号", null);
        sendResp(returnData, response);
        return null;
      }
      //1：注册 2：重置密码
      if (RequestUtil.checkObjectBlank(param.vclass)) {
        returnData.setReturnData(errorcode_param, " vclass is null", null);
        sendResp(returnData, response);
        return null;
      }
      if (RequestUtil.checkObjectBlank(param.verify_list)) {
        returnData.setReturnData(errorcode_param, " verify_list is null", null);
        sendResp(returnData, response);
        return null;
      }
      String sign_str = getSignature(Constants.SYSTEM_KEY, param.dtype, param.tel,
          param.verify_list, param.vclass);
      if (!param.sign.equalsIgnoreCase(sign_str)) {
        log.warn("sign=" + param.sign + "  sign_str=" + sign_str);
        returnData.setReturnData(errorcode_param, " sign is not right", null);
        sendResp(returnData, response);
        return null;
      }

      smsBiz.ReturnReSendSMg(returnData, param.dtype, param.tel, param.vclass, param.verify_list);
      sendResp(returnData, response);
      return null;

    } catch (Exception e) {
      log.error("UserSendCode is error  2.21	Read-发送验证码(APPSDK-JAVA)- P", e);
      returnData.setReturnData(errorcode_systerm, "system is error", "");
    }
    sendResp(returnData, response);
    return null;
  }

  /**
   * 找回密码 邮箱
   * @return
   */
  /*@Action(value = "findpass_mail")
  public String findpass_mail(){
	ReturnDataNew returnData = new ReturnDataNew();
	 try{
		//检查是否是合法请求
		String ip = getIpAddr(getRequest());
		if(ip.startsWith("192.168") || ip.startsWith("127.0")){  
			ip  =  null;
		}
		if(RequestUtil.checkObjectBlank(sign)){
			returnData.setReturnData(errorcode_param, " sign is null", null);
			sendResp(returnData,response);
			return null;
		}
		if(RequestUtil.checkObjectBlank(email)){
			returnData.setReturnData(errorcode_param, " email is null", null);
			sendResp(returnData,response);
			return null;
		}
		if(!isEmail(email)){
			returnData.setReturnData(errorcode_param, " email is not a right email", null);
			sendResp(returnData,response);
			return null;
		}
		String sign_str = getSignature(Constants.SYSTEM_KEY, dtype,email);
		if(!sign.equalsIgnoreCase(sign_str)){
			log.warn("sign="+sign+"  sign_str="+sign_str);
			returnData.setReturnData(errorcode_param, " sign is not right", null);
			sendResp(returnData,response);
			return null;
		}
		
		sMSBiz.ReturnFindPassWord_mail(returnData, dtype,email);
		sendResp(returnData,response);
		return null;
		
		} catch (Exception e) {
			log.error("findpass_mail is error  2.21	Read-找回密码 邮箱(APPSDK-JAVA)- P",e);
			returnData.setReturnData(errorcode_systerm, "system is error", ""); 
		}
		sendResp(returnData,response);
		return null; 
	}*/


}
