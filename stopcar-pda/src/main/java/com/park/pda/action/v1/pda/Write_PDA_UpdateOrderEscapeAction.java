
package com.park.pda.action.v1.pda;

import com.park.bean.ReturnDataNew;
import com.park.constants.Constants;
import com.park.mvc.action.v1.BaseV1Controller;
import com.park.pda.action.v1.pda.param.Param_upate_order_escape;
import com.park.pda.service.PDAOrderBiz;
import com.park.util.RequestUtil;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 更新订单的 逃逸 状态
 *
 * @author jingxiaohu
 */
@Controller
@RequestMapping(value = "/v1")
public class Write_PDA_UpdateOrderEscapeAction extends BaseV1Controller {
	@Autowired
	protected PDAOrderBiz orderBiz;

  /**
   * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
   */
  private static final long serialVersionUID = -3599663972160625262L;


  /**
   * 更新订单的 逃逸或未交费 状态（占道停车场）
   * 
   */
  @RequestMapping(value = "/upate_order_escape")
  @ResponseBody
  public String upate_order_escape(HttpServletResponse response, Param_upate_order_escape param) {
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
      if (RequestUtil.checkObjectBlank(param.getOrderid())) {
        //car_code 车牌号
        returnData.setReturnData(errorcode_param, " orderid is null", null);
        sendResp(returnData, response);
        return null;
      }
      if (RequestUtil.checkObjectBlank(param.getType())) {
        returnData.setReturnData(errorcode_param, " type is null", null);
        sendResp(returnData, response);
        return null;
      }

      if (RequestUtil.checkObjectBlank(param.getMoney())) {
        returnData.setReturnData(errorcode_param, " money is null", null);
        sendResp(returnData, response);
        return null;
      }
      boolean is_sync = false;//是否异步上传
      if (param.is_sync != null && param.is_sync == 1) {
        if (param.getSync_time() == null || param.getSync_time() == 0) {
          //异步上传必须传时间
          returnData.setReturnData(errorcode_param, "异步上传时sync_time 必须上传", null);
          sendResp(returnData, response);
          return null;
        }
        is_sync = true;
      }

      String sign_str = getSignature(Constants.SYSTEM_KEY, param.dtype,
          param.getOrderid(), param.getType(), param.getMoney());
      if (!param.sign.equalsIgnoreCase(sign_str)) {
        log.warn("sign=" + param.sign + "  sign_str=" + sign_str);
        returnData.setReturnData(errorcode_param, " sign is not right", null);
        sendResp(returnData, response);
        return null;
      }
      orderBiz.upate_order_escape(returnData, param.dtype,
          param.getOrderid(), param.getType(), param.getMoney(),
          is_sync,param.getSync_time());

      sendResp(returnData, response);
      return null;

    } catch (Exception e) {
      log.error("Write_PDA_UpdateOrderEscapeAction upate_order_escape  is error(DEVICE-JAVA)- P",
          e);
      returnData.setReturnData(errorcode_systerm, "system is error", "");
    }
    sendResp(returnData, response);
    return null;
  }


}
