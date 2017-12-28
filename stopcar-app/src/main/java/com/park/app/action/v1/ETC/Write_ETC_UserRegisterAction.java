
package com.park.app.action.v1.ETC;

import com.park.app.action.v1.ETC.param.Param_etc_user_reg;
import com.park.app.service.EtcBiz;
import com.park.bean.ReturnDataNew;
import com.park.constants.Constants;
import com.park.mvc.action.v1.BaseV1Controller;
import com.park.util.IDCardValidator;
import com.park.util.RequestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLDecoder;

/**
 * 用户 ETC注册
 *
 * @author jingxiaohu
 */
@Controller
@RequestMapping(value = "/v1")
public class Write_ETC_UserRegisterAction extends BaseV1Controller {

  @Autowired
  private EtcBiz etcBiz;
  /**
   * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
   */
  private static final long serialVersionUID = -3599663972160625263L;

  @RequestMapping(value = "/etc_user_reg")
  @ResponseBody
  public String etc_user_reg(HttpServletRequest request, HttpServletResponse response,
      Param_etc_user_reg param) {
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
        returnData.setReturnData(errorcode_param, "ui_id is null", "");
        sendResp(returnData, response);
        return null;
      }
//      if (RequestUtil.checkObjectBlank(param.tel)) {
//        returnData.setReturnData(errorcode_param, " tel is null", null);
//        sendResp(returnData, response);
//        return null;
//      }
//      if (!isMobileNO(param.tel)) {
//        returnData.setReturnData(errorcode_param, "请输入正确的电话号码,亲", null);
//        sendResp(returnData, response);
//        return null;
//      }
      if (RequestUtil.checkObjectBlank(param.name)) {
        returnData.setReturnData(errorcode_param, " name is null", null);
        sendResp(returnData, response);
        return null;
      } else {
        param.setName(URLDecoder.decode(param.getName(), "UTF-8"));
      }
      if (RequestUtil.checkObjectBlank(param.sfz_number)) {
        returnData.setReturnData(errorcode_param, " sfz_number is null", null);
        sendResp(returnData, response);
        return null;
      }

      if (!IDCardValidator.validate(param.sfz_number)) {
        returnData.setReturnData(errorcode_param, "请输入正确的身份证号码", null);
        sendResp(returnData, response);
        return null;
      }

      if (RequestUtil.checkObjectBlank(param.bank_type)) {
        param.bank_type = 0;
      }
      if (RequestUtil.checkObjectBlank(param.bank_card_number)) {
        returnData.setReturnData(errorcode_param, " bank_card_number is null", null);
        sendResp(returnData, response);
        return null;
      }
      if (!isBankNO(param.bank_card_number)) {
        returnData.setReturnData(errorcode_param, "请输入正确的银行卡号码,亲", null);
        sendResp(returnData, response);
        return null;
      }
      MultipartFile cardimg = param.getCardimg();
      String cardimgFileName = null;
      if (cardimg != null) {
        cardimgFileName = cardimg.getOriginalFilename();
        param.setCardimgFileName(cardimgFileName);
      }
      if (param.pay_price < 1) {
        //pay_price必须大于0
        returnData.setReturnData(errorcode_param, "pay_price必须大于0", "");
        sendResp(returnData, response);
        return null;
      }
      if (RequestUtil.checkObjectBlank(param.token)) {
        //token不能为空
        returnData.setReturnData(errorcode_param, "token不能为空", "");
        sendResp(returnData, response);
        return null;
      }
      String sign_str = getSignature(Constants.SYSTEM_KEY, param.dtype, param.ui_id
          , param.sfz_number, param.bank_card_number);
      if (!param.sign.equalsIgnoreCase(sign_str)) {
        log.warn("sign=" + param.sign + "  sign_str=" + sign_str);
        returnData.setReturnData(errorcode_param, " sign is not right", null);
        sendResp(returnData, response);
        return null;
      }

      /*etcBiz.ReturnETCUserRegister(returnData, param.dtype, param.tel, param.name,
          param.bank_card_number, param.bank_type, param.sfz_number, param.ui_id, param.cardimg,
          param.cardimgFileName, param.cardimgContentType, param.verify_code, param.verify_list,
          param.vclass);*/
      //元 转变成分
      int pay_price_fen = param.pay_price;
      String pub = "30819d300d06092a864886f70d010101050003818b0030818702818100cd0b7cdab739d49af0ee8bf88f5bcfc8432d8c6818f0821d34e74bf52081977ec2a30cddba61b84fda72b6ec883283a14431410e7ff90449bd6e8fca88d3828a83d47a600e1e33881bcde003a65f9547acb998a0d971c4006e6c25222aed818d11b861381b7f80f8dc6d81303c25a8f17d35f2559a0802790f2b22c3dc5026c3020111";

      etcBiz.ReturnETCUserRegister(returnData, param.dtype, param.tel, param.name,
          param.bank_card_number, param.bank_type, param.sfz_number, param.ui_id, param.cardimg,
          param.cardimgFileName, param.cardimgContentType,
          param.pay_type, pay_price_fen, param.version,
          param.system_type,
          param.subject, ip, param.token, pub.substring(pub.length() - 30, pub.length()),
          param.type);
      sendResp(returnData, response);
      return null;

    } catch (Throwable e) {
      log.error(
          "Write_ETC_UserRegisterAction.etc_user_reg is error", e);
      returnData.setReturnData(errorcode_systerm, "system is error", "");
    }
    sendResp(returnData, response);
    return null;
  }


  /**
   * 修改ETC用户信息
   */
  @RequestMapping(value = "/etc_user_update")
  @ResponseBody
  public String etc_user_update(HttpServletRequest request, HttpServletResponse response,
      Param_etc_user_reg param) {
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
      if (RequestUtil.checkObjectBlank(param.eu_id)) {
        returnData.setReturnData(errorcode_param, "eu_id is null", "");
        sendResp(returnData, response);
        return null;
      }

      if (StringUtils.hasText(param.tel) && !isMobileNO(param.tel)) {
        returnData.setReturnData(errorcode_param, "请输入正确的电话号码,亲", null);
        sendResp(returnData, response);
        return null;
      }
      if (!RequestUtil.checkObjectBlank(param.name)) {
        param.setName(URLDecoder.decode(param.getName(), "UTF-8"));
      }
      MultipartFile cardimg = param.getCardimg();
      String cardimgFileName = null;
      if (cardimg != null) {
        cardimgFileName = cardimg.getOriginalFilename();
        param.setCardimgFileName(cardimgFileName);
      }

      String sign_str = getSignature(Constants.SYSTEM_KEY, param.dtype, param.ui_id, param.eu_id);
      if (!param.sign.equalsIgnoreCase(sign_str)) {
        log.warn("sign=" + param.sign + "  sign_str=" + sign_str);
        returnData.setReturnData(errorcode_param, " sign is not right", null);
        sendResp(returnData, response);
        return null;
      }

      etcBiz.ReturnETCUserUpdate(returnData, param.dtype, param.eu_id, param.tel, param.name,
          param.bank_card_number, param.bank_type, param.sfz_number, param.ui_id, param.cardimg,
          param.cardimgFileName, param.cardimgContentType);
      sendResp(returnData, response);
      return null;

    } catch (Throwable e) {
      log.error(
          "Write_ETC_UserRegisterAction.etc_user_update is error",
          e);
      returnData.setReturnData(errorcode_systerm, "system is error", "");
    }
    sendResp(returnData, response);
    return null;
  }


  /**
   * 用户退签ETC
   */
  @RequestMapping(value = "/etc_user_del")
  @ResponseBody
  public String etc_user_del(HttpServletRequest request, HttpServletResponse response,
      Param_etc_user_reg param) {
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
        returnData.setReturnData(errorcode_param, "ui_id is null", "");
        sendResp(returnData, response);
        return null;
      }
      if (RequestUtil.checkObjectBlank(param.eu_id)) {
        returnData.setReturnData(errorcode_param, "eu_id is null", "");
        sendResp(returnData, response);
        return null;
      }

      String sign_str = getSignature(Constants.SYSTEM_KEY, param.dtype, param.ui_id, param.eu_id);
      if (!param.sign.equalsIgnoreCase(sign_str)) {
        log.warn("sign=" + param.sign + "  sign_str=" + sign_str);
        returnData.setReturnData(errorcode_param, " sign is not right", null);
        sendResp(returnData, response);
        return null;
      }

      etcBiz.ReturnETCUserDel(returnData, param.dtype, param.eu_id, param.ui_id);
      sendResp(returnData, response);
      return null;

    } catch (Throwable e) {
      log.error(
          "Write_ETC_UserRegisterAction.etc_user_del is error",
          e);
      returnData.setReturnData(errorcode_systerm, "system is error", "");
    }
    sendResp(returnData, response);
    return null;
  }

  /**
   * ETC设置默认银行卡
   */
  @RequestMapping(value = "/etc_user_set_default")
  @ResponseBody
  public String etc_user_set_default(HttpServletRequest request, HttpServletResponse response,
      Param_etc_user_reg param) {
    ReturnDataNew returnData = new ReturnDataNew();
    try {
      //检查是否是合法请求
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
      if (param.ui_id < 1) {
        returnData.setReturnData(errorcode_param, "ui_id is null", "");
        sendResp(returnData, response);
        return null;
      }
      //对封装的参数对象中的属性进行 非空等规则验证
      if (RequestUtil.checkObjectBlank(param.eu_id)) {
        returnData.setReturnData(errorcode_param, "eu_id is null", "");
        sendResp(returnData, response);
        return null;
      }

      String sign_str = getSignature(Constants.SYSTEM_KEY, param.dtype, param.ui_id, param.eu_id);
      if (!param.sign.equalsIgnoreCase(sign_str)) {
        log.warn("sign=" + param.sign + "  sign_str=" + sign_str);
        returnData.setReturnData(errorcode_param, " sign is not right", null);
        sendResp(returnData, response);
        return null;
      }

      etcBiz.etc_user_set_default(returnData, param.dtype, param.ui_id, param.eu_id);
      sendResp(returnData, response);
      return null;

    } catch (Throwable e) {
      log.error(
          "com.park.mvc.v1.controller.ETC.Write_ETC_UserRegisterAction.etc_user_set_default is error",
          e);
      returnData.setReturnData(errorcode_systerm, "system is error", "");
    }
    sendResp(returnData, response);
    return null;
  }
}
