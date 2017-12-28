
package com.park.app.action.v1.user;

import com.park.app.service.UserBiz;
import com.park.app.action.v1.user.param.Param_login;
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
 * 用户登录  和 外部第三方用户系统授权登录 user_external
 *
 * @author jingxiaohu
 */
@Controller
@RequestMapping(value = "/v1")
public class Write_UserLoginAction extends BaseV1Controller {


  /**
   * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
   */
  private static final long serialVersionUID = -3599663972160625263L;
  @Autowired
  private UserBiz userBiz;

  @RequestMapping(value = "/login")
  @ResponseBody
  public String UserLogin(HttpServletRequest request, HttpServletResponse response,
      Param_login param) {
    ReturnDataNew returnData = new ReturnDataNew();
    try {
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
      String ip = getIpAddr(request);
      if (ip.startsWith("192.168") || ip.startsWith("127.0")) {
        ip = null;
      }
      //普通登陆
      //用户登录
      if (RequestUtil.checkObjectBlank(param.tel)) {
        returnData.setReturnData(errorcode_param, " tel is null", null);
        sendResp(returnData, response);
        return null;
      }
      if (!isMobileNO(param.tel)) {
        returnData.setReturnData(errorcode_param, " tel is not a right 电话号码", null);
        sendResp(returnData, response);
        return null;
      }
      if (RequestUtil.checkObjectBlank(param.password)) {
        returnData.setReturnData(errorcode_param, " password is null", null);
        sendResp(returnData, response);
        return null;
      }
      if (param.password.length() < 5 && param.password.length() > 20) {
        returnData.setReturnData(errorcode_param, "密码必须大于4位小于20位", null);
        sendResp(returnData, response);
        return null;
      }

      String sign_str = getSignature(Constants.SYSTEM_KEY, param.dtype, param.tel, param.password);
      if (!param.sign.equalsIgnoreCase(sign_str)) {
        log.warn("sign=" + param.sign + "  sign_str=" + sign_str);
        returnData.setReturnData(errorcode_param, " sign is not right", null);
        sendResp(returnData, response);
        return null;
      }

      userBiz.ReturnUserLogin(returnData, param.dtype, param.tel, param.password, ip);
      sendResp(returnData, response);
      return null;

    } catch (Exception e) {
      log.error("UserLogin is error  2.21	Read-登录 (APPSDK-JAVA)- P", e);
      returnData.setReturnData(errorcode_systerm, "system is error", "");
    }
    sendResp(returnData, response);
    return null;
  }

  /**
   * 第三方
   * @return
   */
  /*@Action(value = "external_login")
	public String user_external_login(){
	ReturnDataNew returnData = new ReturnDataNew();
	 try{
		//检查是否是合法请求
		String ip = getIpAddr(getRequest());
		if(ip.startsWith("192.168") || ip.startsWith("127.0")){  
			ip  =  null;
		}
		//2.1.4. 用户登录完成
		if(RequestUtil.checkObjectBlank(sign)){
			returnData.setReturnData(errorcode_param, " sign is null", null);
			sendResp(returnData,response);
			return null;
		}
		
		//用户登录
		if(RequestUtil.checkObjectBlank(up_type)){
			returnData.setReturnData(errorcode_param, " up_type is null", null);
			sendResp(returnData,response);
			return null;
		}
		if(RequestUtil.checkObjectBlank(up_key)){
			returnData.setReturnData(errorcode_param, " up_key is null", null);
			sendResp(returnData,response);
			return null;
		}
		if(RequestUtil.checkObjectBlank(up_token)){
			returnData.setReturnData(errorcode_param, " up_token is null", null);
			sendResp(returnData,response);
			return null;
		}
		
		String sign_str = getSignature(Constants.SYSTEM_KEY, dtype,up_type,up_key,up_token);
		if(!sign.equalsIgnoreCase(sign_str)){
			log.warn("sign="+sign+"  sign_str="+sign_str);
			returnData.setReturnData(errorcode_param, " sign is not right", null);
			sendResp(returnData,response);
			return null;
		}
		
		userBiz.ReturnExternalUserLogin(returnData, dtype,up_type,up_key,up_token,imei,tel_version,item,avtar,nickname,sex,ip);
		sendResp(returnData,response);
		return null;
		
		} catch (Exception e) {
			log.error("external_login is error  2.21	Read-外部登录 (APPSDK-JAVA)- P",e);
			returnData.setReturnData(errorcode_systerm, "system is error", ""); 
		}
		sendResp(returnData,response);
		return null; 
	}*/


}
