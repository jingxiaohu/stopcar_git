
package com.park.app.action.v1.user;

import com.park.app.service.CarBiz;
import com.park.app.action.v1.user.param.Param_read_usermoneyback;
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
 * 获取用户申诉退费审核结果
 *
 * @author jingxiaohu
 */
@Controller
@RequestMapping(value = "/v1")
public class Read_userMoneybackAction extends BaseV1Controller {


  /**
   * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
   */
  private static final long serialVersionUID = -3599663972160625262L;
  @Autowired
  private CarBiz carBiz;

  /**
   * 获取用户申诉退费审核结果
   */
  @RequestMapping(value = "/read_usermoneyback")
  @ResponseBody
  public String read_usermoneyback(HttpServletResponse response, Param_read_usermoneyback param) {
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
        //用户ID
        returnData.setReturnData(errorcode_param,
            " ui_id=" + param.ui_id + "  ui_id is smaller than zero", null);
        sendResp(returnData, response);
        return null;
      }
      if (RequestUtil.checkObjectBlank(param.order_id)) {
        // pp_id;//停车下订单表主键ID
        returnData
            .setReturnData(errorcode_param, " order_id=" + param.order_id + "  order_id is null",
                null);
        sendResp(returnData, response);
        return null;
      }

      String sign_str = getSignature(Constants.SYSTEM_KEY, param.dtype, param.ui_id,
          param.order_id);
      if (!param.sign.equalsIgnoreCase(sign_str)) {
        log.warn("sign=" + param.sign + "  sign_str=" + sign_str);
        returnData.setReturnData(errorcode_param, " sign is not right", null);
        sendResp(returnData, response);
        return null;
      }

      carBiz.read_usermoneyback(returnData, param.ui_id, param.order_id, param.is_rent);

      sendResp(returnData, response);
      return null;

    } catch (Exception e) {
      log.error("Read_userMoneybackAction read_usermoneyback  is error(DEVICE-JAVA)- P", e);
      returnData.setReturnData(errorcode_systerm, "system is error", "");
    }
    sendResp(returnData, response);
    return null;
  }


}
