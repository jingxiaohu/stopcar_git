
package com.park.app.action.v1.user;

import com.park.app.service.VersionBiz;
import com.park.app.action.v1.user.param.Param_gainupgrade;
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
 * 获取设备 Android IOS 升级对应的包URL
 *
 * @author jingxiaohu
 */
@Controller
@RequestMapping(value = "/v1")
public class Read_VersionAction extends BaseV1Controller {


  /**
   * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
   */
  private static final long serialVersionUID = -3599663972160625262L;

  @Autowired
  private VersionBiz versionBiz;

  @RequestMapping(value = "/gainupgrade")
  @ResponseBody
  public String Read_Version(HttpServletResponse response, Param_gainupgrade param) {

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
      log.info(
          "uid=" + param.uid + ",version=" + param.version + ",versioncode=" + param.versioncode
              + ",param.dtype=" + param.dtype);
      //检查是否是合法请求
      if (RequestUtil.checkObjectBlank(param.version)) {
        returnData.setReturnData(errorcode_param, " version is null", null);
        sendResp(returnData, response);
        return null;
      }
      if (param.versioncode < 1) {
        returnData.setReturnData(errorcode_param,
            " versioncode=" + param.versioncode + "  versioncode is not right", null);
        sendResp(returnData, response);
        return null;
      }

      String sign_str = getSignature(Constants.SYSTEM_KEY, param.dtype, param.versioncode);
      if (!param.sign.equalsIgnoreCase(sign_str)) {
        log.warn("sign=" + param.sign + "  sign_str=" + sign_str);
        returnData.setReturnData(errorcode_param, " sign is not right", null);
        sendResp(returnData, response);
        return null;
      }

      versionBiz.ReturnVsersionUpgrade(returnData, param.version, param.versioncode, param.dtype);
      sendResp(returnData, response);
      return null;


    } catch (Exception e) {
      log.error("Read_VersionAction is error  获取设备 Android IOS 升级对应的包URL (JAVA)- P", e);
      returnData.setReturnData(errorcode_systerm, "system is error", "");
    }
    sendResp(returnData, response);
    return null;
  }


}
