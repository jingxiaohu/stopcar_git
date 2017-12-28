
package com.park.app.action.v1.user;

import com.park.app.service.CarBiz;
import com.park.app.action.v1.user.param.Param_usermoneyback;
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

/**
 * 用户申诉退费
 *
 * @author jingxiaohu
 */
@Controller
@RequestMapping(value = "/v1")
public class Write_userMoneybackAction extends BaseV1Controller {


  /**
   * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
   */
  private static final long serialVersionUID = -3599663972160625262L;
  @Autowired
  private CarBiz carBiz;

  /**
   * 用户申诉退费
   */
  @RequestMapping(value = "/usermoneyback")
  @ResponseBody
  public String usermoneyback(HttpServletRequest request, HttpServletResponse response,
      Param_usermoneyback param) {
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
      if (param.pi_id < 1) {
        // pi_id;//停车场主键ID
        returnData.setReturnData(errorcode_param,
            " pi_id=" + param.pi_id + "  pi_id is smaller than zero", null);
        sendResp(returnData, response);
        return null;
      }
      if (param.um_money < 1) {
        //um_money;//退款金额(单位 分)
        returnData.setReturnData(errorcode_param,
            " um_money=" + param.um_money + "  um_money is smaller than zero", null);
        sendResp(returnData, response);
        return null;
      }
      if (RequestUtil.checkObjectBlank(param.car_code)) {
        //car_code 退款车牌号
        returnData
            .setReturnData(errorcode_param, " car_code=" + param.car_code + "  car_code is null",
                null);
        sendResp(returnData, response);
        return null;
      } else {
        param.car_code = URLDecoder.decode(param.car_code, Constants.SYSTEM_CHARACTER);
      }

      if (!RequestUtil.checkObjectBlank(param.content)) {
        //content 申诉内容详情
        param.content = URLDecoder.decode(param.content, Constants.SYSTEM_CHARACTER);
      }

      String sign_str = getSignature(Constants.SYSTEM_KEY, param.dtype, param.ui_id,
          param.car_code);
      if (!param.sign.equalsIgnoreCase(sign_str)) {
        log.warn("sign=" + param.sign + "  sign_str=" + sign_str);
        returnData.setReturnData(errorcode_param, " sign is not right", null);
        sendResp(returnData, response);
        return null;
      }
      carBiz.usermoneyback(returnData, param.ui_id, param.order_id, param.pi_id, param.um_money,
          param.car_code, param.um_state, param.check_state, param.admin_userid,
          param.is_rent, param.area_code, param.type, param.content);

      sendResp(returnData, response);
      return null;

    } catch (Exception e) {
      log.error("Write_userMoneybackAction usermoneyback  is error(DEVICE-JAVA)- P", e);
      returnData.setReturnData(errorcode_systerm, "system is error", "");
    }
    sendResp(returnData, response);
    return null;
  }


}
