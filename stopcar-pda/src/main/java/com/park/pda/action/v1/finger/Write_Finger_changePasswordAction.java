package com.park.pda.action.v1.finger;

import com.park.bean.ReturnDataNew;
import com.park.constants.Constants;
import com.park.mvc.action.v1.BaseV1Controller;
import com.park.pda.action.v1.finger.param.Param_finger_changePassword;
import com.park.pda.service.Finger_changePasswordBiz;
import com.park.util.RequestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLDecoder;

/**
 * 指纹采集--用户重置密码
 *
 * @author zyy
 */
@RestController
@RequestMapping(value = "/v1")
@Deprecated
//by zyy 20170726 指纹采集目前只做演示版本，不涉及绑定设备等，暂不需要修改密码
public class Write_Finger_changePasswordAction extends BaseV1Controller {

  private static final long serialVersionUID = -3599663972160625463L;
  @Autowired
  protected Finger_changePasswordBiz changePasswordBiz;

  @RequestMapping(value = "/finger_change_password")
  public String changePassword(HttpServletRequest request, HttpServletResponse response,Param_finger_changePassword param) {
    ReturnDataNew returnData = new ReturnDataNew();
    try {
      //参数检查和签名验证
      if(!checkParam(param,returnData)){
        sendResp(returnData, response);
        return null;
      }
      changePasswordBiz.changePassword(returnData, param);
    } catch (Exception e) {
      log.error("Write_Finger_ModifyPasswordAction changePassword is error 修改密码(DEVICE-JAVA)- P", e);
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
  private boolean checkParam(Param_finger_changePassword param, ReturnDataNew returnData) throws Exception{
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
    //登录帐号
    if (!RequestUtil.checkObjectBlank(param.getLoginName())) {
      param.setLoginName(URLDecoder.decode(param.getLoginName(), Constants.SYSTEM_CHARACTER));
    }else{
      returnData.setReturnData(errorcode_param, " loginName is null", null);
      return false;
    }
    //原密码
    if (RequestUtil.checkObjectBlank(param.getPassword())) {
      returnData.setReturnData(errorcode_param, " password is null", null);
      return false;
    }
    //新密码
    if (RequestUtil.checkObjectBlank(param.getRePassword())) {
      returnData.setReturnData(errorcode_param, " rePassword is null", null);
      return false;
    }

    String sign_str = getSignature(Constants.SYSTEM_KEY,param.dtype,param.loginName,param.password,param.rePassword);
    if (!param.getSign().equalsIgnoreCase(sign_str)) {
      log.warn("sign=" + param.getSign() + "  sign_str=" + sign_str);
      returnData.setReturnData(errorcode_param, " 验证签名失败", null);
      return false;
    }
    return true;
  }
}
