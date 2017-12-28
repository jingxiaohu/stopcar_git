
package com.park.pda.action.v1.pda;

import com.park.bean.ReturnDataNew;
import com.park.constants.Constants;
import com.park.mvc.action.v1.BaseV1Controller;
import com.park.pda.action.v1.pda.param.Param_change_pass_pda;
import com.park.pda.service.PDAParkinfoBiz;
import com.park.util.RequestUtil;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 用户重置PDA密码
 *
 * @author jingxiaohu
 */
@Controller
@RequestMapping(value = "/v1")
public class Write_PDA_passwordAction extends BaseV1Controller {


  /**
   * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
   */
  private static final long serialVersionUID = -3599663972160625263L;

  @Autowired
  private PDAParkinfoBiz parkinfoBiz;

/**
 * 用户重置PDA密码
 * 重置之前先检验停车场是否存在、验证码是否正确、验证码是否过期
 * @param request
 * @param response
 * @param param
 * @return
 */
@RequestMapping(value = "/change_pass_pda")
  @ResponseBody
  public String change_pass_pda(HttpServletRequest request, HttpServletResponse response,
      Param_change_pass_pda param) {
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

      if (RequestUtil.checkObjectBlank(param.vclass)) {
        returnData.setReturnData(errorcode_param, " vclass is null", null);
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
      if (RequestUtil.checkObjectBlank(param.loginname)) {
        //帐号
        returnData
            .setReturnData(errorcode_param, " loginname=" + param.loginname + "  loginname is null",
                null);
        sendResp(returnData, response);
        return null;
      }
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
      if (RequestUtil.checkObjectBlank(param.repassword)) {
        returnData.setReturnData(errorcode_param, " repassword is null", null);
        sendResp(returnData, response);
        return null;
      }
      if (param.password.length() < 5 && param.password.length() > 20) {
        returnData.setReturnData(errorcode_param, "密码必须大于4位小于20位", null);
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
      if (RequestUtil.checkObjectBlank(param.area_code)) {
        //省市县编号 140107
        //避免汉子的问题

        returnData
            .setReturnData(errorcode_param, " area_code=" + param.area_code + "  area_code is null",
                null);
        sendResp(returnData, response);
        return null;
      }
      if (RequestUtil.checkObjectBlank(param.park_type)) {
        //park_type;//停车场类型 0：地下室停车场 1：露天停车场 2：露天立体车库停车场
        returnData
            .setReturnData(errorcode_param, " park_type=" + param.park_type + "  park_type is null",
                null);
        sendResp(returnData, response);
        return null;
      }
      parkinfoBiz
          .change_pass_pda(returnData, param.dtype, param.tel, param.verify_code, param.verify_list,
              param.vclass, param.password, param.repassword, param.area_code, param.park_type,
              param.loginname);
      sendResp(returnData, response);
      return null;

    } catch (Exception e) {
      log.error("change_pass_pda is error  2.21	Read-修改PDA密码(APPSDK-JAVA)- P", e);
      returnData.setReturnData(errorcode_systerm, "system is error", "");
    }
    sendResp(returnData, response);
    return null;
  }


}
