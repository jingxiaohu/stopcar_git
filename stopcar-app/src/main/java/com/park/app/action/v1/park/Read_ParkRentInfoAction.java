
package com.park.app.action.v1.park;

import com.park.app.service.AppOrderBiz;
import com.park.bean.ReturnDataNew;
import com.park.constants.Constants;
import com.park.mvc.action.v1.BaseV1Controller;
import com.park.mvc.action.v1.park.param.Param_park_info;
import com.park.util.RequestUtil;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 读取停车场租赁详情
 *
 * @author jingxiaohu
 */
@Controller
@RequestMapping(value = "/v1")
public class Read_ParkRentInfoAction extends BaseV1Controller {

  /**
   *
   */
  private static final long serialVersionUID = 6891425545908564737L;

  @Autowired
  private AppOrderBiz orderBiz;

  /**
   * 读取停车场租赁详情
   */
  @RequestMapping(value = "/read_parkrent_info")
  @ResponseBody
  public String read_parkrent_info(HttpServletResponse response, Param_park_info param) {

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
        returnData.setReturnData(errorcode_param, " ui_id is smaller than one", "");
        sendResp(returnData, response);
        return null;
      }
      if (param.pi_id < 1) {
        //停车场主键ID
        returnData
            .setReturnData(errorcode_param, " pi_id=" + param.pi_id + "  pi_id is smaller than 1",
                null);
        sendResp(returnData, response);
        return null;
      }
      if (RequestUtil.checkObjectBlank(param.area_code)) {
        //area_code 省市区区域代码  四川省 成都市 龙泉驿区  510112
        returnData
            .setReturnData(errorcode_param, " area_code=" + param.area_code + "  area_code is null",
                null);
        sendResp(returnData, response);
        return null;
      }
      String sign_str = getSignature(Constants.SYSTEM_KEY, param.dtype, param.ui_id, param.pi_id);
      if (!param.sign.equalsIgnoreCase(sign_str)) {
        log.warn("sign=" + param.sign + "  sign_str=" + sign_str);
        returnData.setReturnData(errorcode_param, " sign is not right", "");
        sendResp(returnData, response);
        return null;
      }

      orderBiz
          .read_parkrent_info(returnData, param.dtype, param.ui_id, param.pi_id, param.area_code);
      sendResp(returnData, response);
      return null;

    } catch (Exception e) {
      log.error(
          "Read_ParkRentInfoAction.read_parkrent_info is error  2.21	Read-读取停车场租赁详情 (APPSDK-JAVA)- P",
          e);
      returnData.setReturnData(errorcode_systerm, "system is error", "");
    }
    sendResp(returnData, response);
    return null;
  }


}
