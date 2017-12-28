
package com.park.app.action.v1.user;

import com.park.app.action.v1.user.param.Param_UserModify;
import com.park.app.service.UserBiz;
import com.park.bean.ReturnDataNew;
import com.park.constants.Constants;
import com.park.mvc.action.v1.BaseV1Controller;
import com.park.util.RequestUtil;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

/**
 * 用户重置密码 用户修改绑定手机号码   用户修改头像  用户修改昵称
 *
 * @author jingxiaohu
 */
@Controller
@RequestMapping(value = "/v1")
public class Write_UserModifyAction extends BaseV1Controller {


  /**
   * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
   */
  private static final long serialVersionUID = -3599663972160625263L;
  @Autowired
  private UserBiz userBiz;

  @RequestMapping(value = "/change_pass")
  @ResponseBody
  public String modifyPassword(HttpServletRequest request, HttpServletResponse response,
      Param_UserModify param) {
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

      userBiz.ReturnUserModifyPassword(returnData, param.dtype, param.tel, param.verify_code,
          param.verify_list,
          param.vclass, param.password, param.repassword);
      sendResp(returnData, response);
      return null;

    } catch (Exception e) {
      log.error("modifyPassword is error  2.21	Read-修改密码(APPSDK-JAVA)- P", e);
      returnData.setReturnData(errorcode_systerm, "system is error", "");
    }
    sendResp(returnData, response);
    return null;
  }

  //修改绑定手机号
  @RequestMapping(value = "/change_tel")
  @ResponseBody
  public String change_tel(HttpServletRequest request, HttpServletResponse response,
      Param_UserModify param) {
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
      String sign_str = getSignature(Constants.SYSTEM_KEY, param.dtype, param.tel, param.password,
          param.ui_id);
      if (!param.sign.equalsIgnoreCase(sign_str)) {
        log.warn("sign=" + param.sign + "  sign_str=" + sign_str);
        returnData.setReturnData(errorcode_param, " sign is not right", null);
        sendResp(returnData, response);
        return null;
      }

      userBiz.ReturnUserUpdateTel(returnData, param.dtype, param.tel, param.password, param.ui_id);
      sendResp(returnData, response);
      return null;

    } catch (Exception e) {
      log.error("change_tel is error  修改绑定手机号码 (APPSDK-JAVA)- P", e);
      returnData.setReturnData(errorcode_systerm, "system is error", "");
    }
    sendResp(returnData, response);
    return null;
  }


  //修改用户基本信息
  @RequestMapping(value = "/change_userinfo")
  @ResponseBody
  public String change_userinfo(HttpServletRequest request, HttpServletResponse response,
      Param_UserModify param) {
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
      if (param.ui_id < 1) {
        returnData.setReturnData(errorcode_param, " ui_id is smaller than zero", "");
        sendResp(returnData, response);
        return null;
      }
      if (!RequestUtil.checkObjectBlank(param.nickname)) {
        //避免汉子的问题
        param.nickname = URLDecoder.decode(param.nickname, Constants.SYSTEM_CHARACTER);
        if (param.nickname.length() > 20) {
          returnData.setReturnData(errorcode_param, " nickname'length must be  less than 20", "");
          sendResp(returnData, response);
          return null;
        }
      }
      if (!RequestUtil.checkObjectBlank(param.email)) {
        if (!isEmail(param.email)) {
          returnData.setReturnData(errorcode_param, "邮箱格式不正确", "");
          sendResp(returnData, response);
          return null;
        }

      }

      String sign_str = getSignature(Constants.SYSTEM_KEY, param.dtype, param.ui_id);
      if (!param.sign.equalsIgnoreCase(sign_str)) {
        log.warn("sign=" + param.sign + "  sign_str=" + sign_str);
        returnData.setReturnData(errorcode_param, " sign is not right", "");
        sendResp(returnData, response);
        return null;
      }
      MultipartFile avatar = param.getAvatar();
      String avatarFileName = null;
      if (avatar != null) {
        avatarFileName = avatar.getOriginalFilename();
        param.setAvatarFileName(avatarFileName);
      }
      userBiz
          .change_userinfo(returnData, param.dtype, param.ui_id, param.avatar, param.avatarFileName,
              param.nickname, param.sex, param.name, param.driving_licence,
              param.email, param.ui_autopay, param.pay_source, param.etc_autopay);
      sendResp(returnData, response);
      return null;

    } catch (Exception e) {
      log.error("change_userinfo is error  修改用户基本信息(APPSDK-JAVA)- P", e);
      returnData.setReturnData(errorcode_systerm, "system is error", "");
    }
    sendResp(returnData, response);
    return null;
  }


}
