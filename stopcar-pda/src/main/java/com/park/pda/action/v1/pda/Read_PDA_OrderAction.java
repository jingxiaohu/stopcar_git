
package com.park.pda.action.v1.pda;

import com.park.bean.ReturnDataNew;
import com.park.constants.Constants;
import com.park.mvc.action.v1.BaseV1Controller;
import com.park.pda.action.v1.pda.param.Param_my_one_order;
import com.park.pda.service.PDAOrderBiz;
import com.park.util.RequestUtil;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * PDA获取某条订单信息 （1：临时停车订单  2： 租赁订单）
 *
 * @author Peter Wu
 */
@Controller
@RequestMapping(value = "/v1")
public class Read_PDA_OrderAction extends BaseV1Controller {


  private static final long serialVersionUID = 3590306084774307646L;
  @Autowired
  private PDAOrderBiz pdaOrderBiz;

  /**
   * PDA获取某条订单信息
   * 返回普通停车订单或租赁停车订单信息
   * @param response
   * @param param
   * @return
   */
@RequestMapping(value = "/read_pda_order")
  @ResponseBody
  public String pda_read_order(HttpServletResponse response, Param_my_one_order param) {
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
      if (RequestUtil.checkObjectBlank(param.orderid)) {
        returnData.setReturnData(errorcode_param, " orderid is null", null);
        sendResp(returnData, response);
        return null;
      }
      if (RequestUtil.checkObjectBlank(param.type)) {
        returnData.setReturnData(errorcode_param, " type is null", null);
        sendResp(returnData, response);
        return null;
      }
      String sign_str = getSignature(Constants.SYSTEM_KEY, param.dtype, param.type,
          param.orderid);
      if (!param.sign.equalsIgnoreCase(sign_str)) {
        log.warn("sign=" + param.sign + "  sign_str=" + sign_str);
        returnData.setReturnData(errorcode_param, " sign is not right", null);
        sendResp(returnData, response);
        return null;
      }
      pdaOrderBiz.pda_read_order(returnData, param.dtype, param.type, param.orderid);

      sendResp(returnData, response);
      return null;

    } catch (Exception e) {
      log.error("Read_MyOneOrderAction my_one_order  is error(DEVICE-JAVA)- P", e);
      returnData.setReturnData(errorcode_systerm, "system is error", "");
    }
    sendResp(returnData, response);
    return null;
  }


}
