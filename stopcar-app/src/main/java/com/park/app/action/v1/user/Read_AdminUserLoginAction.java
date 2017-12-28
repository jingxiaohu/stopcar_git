
package com.park.app.action.v1.user;

import com.park.app.service.AdminUserBiz;
import com.park.app.action.v1.user.param.Param_adminlogin;
import com.park.bean.ReturnDataNew;
import com.park.constants.Constants;
import com.park.mvc.action.v1.BaseV1Controller;
import com.park.util.RequestUtil;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 管理后台Android采集停车场数据登录接口
 *
 * @author jingxiaohu
 */
@Controller
@RequestMapping(value = "/v1")
public class Read_AdminUserLoginAction extends BaseV1Controller {


  /**
   * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
   */
  private static final long serialVersionUID = -3599663972160625263L;

  @Autowired
  private AdminUserBiz adminUserBiz;

  @RequestMapping(value = "/adminlogin")
  @ResponseBody
  public String adminlogin(HttpServletResponse response, Param_adminlogin param) {
    ReturnDataNew returnData = new ReturnDataNew();
    try {
      //检查是否是合法请求
      //2.1.4. 用户登录完成
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

      String sign_str = getSignature(Constants.SYSTEM_KEY, param.dtype, param.loginname);
      if (!param.sign.equalsIgnoreCase(sign_str)) {
        log.warn("sign=" + param.sign + "  sign_str=" + sign_str);
        returnData.setReturnData(errorcode_param, " sign is not right", null);
        sendResp(returnData, response);
        return null;
      }

      adminUserBiz.ReturnAdminlogin(returnData, param.dtype, param.loginname, param.password);
      sendResp(returnData, response);
      return null;

    } catch (Exception e) {
      log.error("Read_AdminUserLoginAction is error  2.21	Read-登录 (APPSDK-JAVA)- P", e);
      returnData.setReturnData(errorcode_systerm, "system is error", "");
    }
    sendResp(returnData, response);
    return null;
  }


}
