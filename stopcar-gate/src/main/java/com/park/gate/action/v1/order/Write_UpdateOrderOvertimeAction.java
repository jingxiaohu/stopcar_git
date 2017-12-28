
package com.park.gate.action.v1.order;

import com.park.bean.ReturnDataNew;
import com.park.constants.Constants;
import com.park.gate.action.v1.order.param.Param_upate_order_overtime;
import com.park.gate.service.GateOrderBiz;
import com.park.mvc.action.v1.BaseV1Controller;
import com.park.util.RequestUtil;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 更新预约/租赁超时状态
 *
 * @author Peter Wu
 */
@Controller
@RequestMapping(value = "/v1")
public class Write_UpdateOrderOvertimeAction extends BaseV1Controller {

  private static final long serialVersionUID = 975166755215385600L;

  @Autowired
  private GateOrderBiz gateOrderBiz;

  /**
   * 更新预约/租赁超时状态
   */
  @RequestMapping(value = "/upate_order_overtime")
  @ResponseBody
  public String upate_order_overtime(HttpServletResponse response,
      Param_upate_order_overtime param) {
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

      if (RequestUtil.checkObjectBlank(param.orderid)) {
        returnData.setReturnData(errorcode_param, " orderid=" + param.orderid + "  orderid is null",
            null);
        sendResp(returnData, response);
        return null;
      }
      if (RequestUtil.checkObjectBlank(param.type)) {
        returnData.setReturnData(errorcode_param, " type=" + param.type + "  type is null", null);
        sendResp(returnData, response);
        return null;
      }
      String sign_str = getSignature(Constants.SYSTEM_KEY, param.dtype, param.orderid,
          param.type);
      if (!param.sign.equalsIgnoreCase(sign_str)) {
        log.warn("sign=" + param.sign + "  sign_str=" + sign_str);
        returnData.setReturnData(errorcode_param, " sign is not right", null);
        sendResp(returnData, response);
        return null;
      }
      gateOrderBiz.upate_order_overtime(returnData, param.dtype, param.orderid, param.type);

      sendResp(returnData, response);
      return null;

    } catch (Exception e) {
      log.error("Write_UpdateOrderStateAction upate_order_overtime  is error(DEVICE-JAVA)- P", e);
      returnData.setReturnData(errorcode_systerm, "system is error", "");
    }
    sendResp(returnData, response);
    return null;
  }


}
