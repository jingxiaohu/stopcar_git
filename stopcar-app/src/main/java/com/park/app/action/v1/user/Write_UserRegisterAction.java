
package com.park.app.action.v1.user;

import com.park.app.action.v1.user.param.Param_reg;
import com.park.app.service.UserBiz;
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
 * 用户注册
 *
 * @author jingxiaohu
 */
@Controller
@RequestMapping(value = "/v1")
public class Write_UserRegisterAction extends BaseV1Controller {

  @Autowired
  private UserBiz userBiz;
  /**
   * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
   */
  private static final long serialVersionUID = -3599663972160625263L;

  @RequestMapping(value = "/reg")
  @ResponseBody
  public String UserRegister(HttpServletRequest request, HttpServletResponse response,
      Param_reg param) {
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
        returnData.setReturnData(errorcode_param, "请输入正确的电话号码,亲", null);
        sendResp(returnData, response);
        return null;
      }
      if (RequestUtil.checkObjectBlank(param.verify_code)) {
        returnData.setReturnData(errorcode_param, " verify_code is null", null);
        sendResp(returnData, response);
        return null;
      }
      if (RequestUtil.checkObjectBlank(param.verify_list)) {
        returnData.setReturnData(errorcode_param, " verify_list is null", null);
        sendResp(returnData, response);
        return null;
      }
      if (RequestUtil.checkObjectBlank(param.vclass)) {
        returnData.setReturnData(errorcode_param, " vclass is null", null);
        sendResp(returnData, response);
        return null;
      }
      if (RequestUtil.checkObjectBlank(param.password)) {
        returnData.setReturnData(errorcode_param, " password is null", null);
        sendResp(returnData, response);
        return null;
      }
      if (RequestUtil.checkObjectBlank(param.repassword)) {
        returnData.setReturnData(errorcode_param, " repassword is null", null);
        sendResp(returnData, response);
        return null;
      }
      if (param.password.length() < 6 && param.password.length() > 16) {
        returnData.setReturnData(errorcode_param, "密码必须大于5位小于17位", null);
        sendResp(returnData, response);
        return null;
      }
      if (!param.password.equalsIgnoreCase(param.repassword)) {
        //2次密码不一致
        returnData.setReturnData(errorcode_param, " repassword is not equal repassword", null);
        sendResp(returnData, response);
        return null;
      }

      String sign_str = getSignature(Constants.SYSTEM_KEY, param.dtype, param.tel,
          param.verify_code,
          param.verify_list, param.vclass, param.password, param.repassword);
      if (!param.sign.equalsIgnoreCase(sign_str)) {
        log.warn("sign=" + param.sign + "  sign_str=" + sign_str);
        returnData.setReturnData(errorcode_param, " sign is not right", null);
        sendResp(returnData, response);
        return null;
      }

      userBiz.ReturnUserRegister(returnData, param.dtype, param.tel, param.verify_code,
          param.verify_list, param.vclass, param.password, param.repassword);
      sendResp(returnData, response);
      return null;

    } catch (Exception e) {
      log.error("UserRegister is error  2.21	Read-注册 (APPSDK-JAVA)- P", e);
      returnData.setReturnData(errorcode_systerm, "system is error", "");
    }
    sendResp(returnData, response);
    return null;
  }


}
