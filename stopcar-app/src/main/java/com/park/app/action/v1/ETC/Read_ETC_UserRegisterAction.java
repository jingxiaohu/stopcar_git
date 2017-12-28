
package com.park.app.action.v1.ETC;

import com.park.app.service.EtcBiz;
import com.park.bean.ReturnDataNew;
import com.park.constants.Constants;
import com.park.mvc.action.v1.BaseV1Controller;
import com.park.mvc.action.v1.param.BaseParam;
import com.park.sign.ApiDoc;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 获取用户绑定的ETC银行卡
 *
 * @author PeterWu
 */
@Controller
@RequestMapping(value = "/v1",name = "ETC")
public class Read_ETC_UserRegisterAction extends BaseV1Controller {


  private static final long serialVersionUID = 1L;
  @Autowired
  private EtcBiz etcBiz;

  /**
   * 获取用户绑定的ETC银行卡
   */
  @RequestMapping(value = "/read_etc_user",name = "获取用户绑定的ETC银行卡")
  @ResponseBody
  @ApiDoc(tableNames = {"etc_userinfo"}, requires = {"dtype", "ui_id"}, signs = {"dtype", "ui_id"})
  public String read_etc_user(HttpServletRequest request, HttpServletResponse response,
      BaseParam param) {
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
      if (param.ui_id < 1) {
        returnData.setReturnData(errorcode_param, "ui_id is null", "");
        sendResp(returnData, response);
        return null;
      }

      String sign_str = getSignature(Constants.SYSTEM_KEY, param.dtype, param.ui_id);
      if (!param.sign.equalsIgnoreCase(sign_str)) {
        log.warn("sign=" + param.sign + "  sign_str=" + sign_str);
        returnData.setReturnData(errorcode_param, " sign is not right", null);
        sendResp(returnData, response);
        return null;
      }

      etcBiz.read_etc_user(returnData, param.dtype, param.ui_id);
      sendResp(returnData, response);
      return null;

    } catch (Throwable e) {
      log.error(
          "获取用户绑定的ETC银行卡出错", e);
      returnData.setReturnData(errorcode_systerm, "system is error", "");
    }
    sendResp(returnData, response);
    return null;
  }


}
