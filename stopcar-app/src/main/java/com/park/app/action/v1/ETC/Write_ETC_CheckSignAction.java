
package com.park.app.action.v1.ETC;

import com.park.app.service.EtcBiz;
import com.park.app.action.v1.ETC.param.Param_etc_user_reg;
import com.park.bean.ReturnDataNew;
import com.park.constants.Constants;
import com.park.mvc.action.v1.BaseV1Controller;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 检查ETC用户 是否签约成功
 *
 * @author PeterWu
 */
@Controller
@RequestMapping(value = "/v1")
public class Write_ETC_CheckSignAction extends BaseV1Controller {


  private static final long serialVersionUID = 1L;
  @Autowired
  private EtcBiz etcBiz;

  /**
   * 检查ETC用户 是否签约成功
   */
  @RequestMapping(value = "/etc_checksign")
  @ResponseBody
  public String etc_checksign(HttpServletRequest request, HttpServletResponse response,
      Param_etc_user_reg param) {
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

      String sign_str = getSignature(Constants.SYSTEM_KEY, param.dtype, param.ui_id, param.eu_id);
      if (!param.sign.equalsIgnoreCase(sign_str)) {
        log.warn("sign=" + param.sign + "  sign_str=" + sign_str);
        returnData.setReturnData(errorcode_param, " sign is not right", null);
        sendResp(returnData, response);
        return null;
      }

      etcBiz.etc_checksign(returnData, param.dtype, param.eu_id, param.ui_id);
      sendResp(returnData, response);
      return null;

    } catch (Throwable e) {
      log.error(
          "检查ETC用户 是否签约成功出错", e);
      returnData.setReturnData(errorcode_systerm, "system is error", "");
    }
    sendResp(returnData, response);
    return null;
  }


}
