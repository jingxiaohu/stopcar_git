
package com.park.app.action.v1.order;

import com.park.app.action.v1.order.param.Param_Read_makeOrder;
import com.park.app.service.AppOrderBiz;
import com.park.app.service.AppParkinfoBiz;
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
 * 准备用户手动下单的数据 （1：读取预约下单普通车位  2：读取下单租赁包月车位  3:  读取用户停车缴费读取订单）
 *
 * @author jingxiaohu
 */
@Controller
@RequestMapping(value = "/v1")
public class Read_makeOrderAction extends BaseV1Controller {


  /**
   * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
   */
  private static final long serialVersionUID = -3599663972160625262L;

  @Autowired
  private AppParkinfoBiz parkinfoBiz;
  @Autowired
  private AppOrderBiz orderBiz;

  /**
   * 读取用户预约下单普通车位 需要的订单准备数据
   */
  @RequestMapping(value = "/read_expect_order")
  @ResponseBody
  public String read_expect_order(HttpServletResponse response, Param_Read_makeOrder param) {
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
      if (param.pi_id < 1) {
        //用户ID
        returnData.setReturnData(errorcode_param,
            " pi_id=" + param.pi_id + "  pi_id is smaller than zero", null);
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
        returnData.setReturnData(errorcode_param, " sign is not right", null);
        sendResp(returnData, response);
        return null;
      }
      parkinfoBiz
          .read_expect_order(returnData, param.dtype, param.ui_id, param.pi_id, param.area_code);

      sendResp(returnData, response);
      return null;

    } catch (Exception e) {
      log.error("Write_makeOrderAction expect_order  is error(DEVICE-JAVA)- P", e);
      returnData.setReturnData(errorcode_systerm, "system is error", "");
    }
    sendResp(returnData, response);
    return null;
  }


  /**
   * 读取用户停车场租赁规则信息(车位租赁)详情页
   */
  @RequestMapping(value = "/read_rent_order")
  @ResponseBody
  public String read_rent_order(HttpServletResponse response, Param_Read_makeOrder param) {
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
      if (param.pi_id < 1) {
        //用户ID
        returnData.setReturnData(errorcode_param,
            " pi_id=" + param.pi_id + "  pi_id is smaller than zero", null);
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
        returnData.setReturnData(errorcode_param, " sign is not right", null);
        sendResp(returnData, response);
        return null;
      }
      parkinfoBiz
          .read_rent_order(returnData, param.dtype, param.ui_id, param.pi_id, param.area_code);

      sendResp(returnData, response);
      return null;

    } catch (Exception e) {
      log.error("Write_makeOrderAction read_rent_order  is error(DEVICE-JAVA)- P", e);
      returnData.setReturnData(errorcode_systerm, "system is error", "");
    }
    sendResp(returnData, response);
    return null;
  }


  /**
   * 用户停车缴费读取订单
   */
  @RequestMapping(value = "/read_pay_order")
  @ResponseBody
  public String read_pay_order(HttpServletResponse response, Param_Read_makeOrder param) {
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
      if (RequestUtil.checkObjectBlank(param.orderid)) {
        returnData.setReturnData(errorcode_param, " orderid is null", null);
        sendResp(returnData, response);
        return null;
      }
      String sign_str = getSignature(Constants.SYSTEM_KEY, param.dtype, param.ui_id, param.orderid);
      if (!param.sign.equalsIgnoreCase(sign_str)) {
        log.warn("sign=" + param.sign + "  sign_str=" + sign_str);
        returnData.setReturnData(errorcode_param, " sign is not right", null);
        sendResp(returnData, response);
        return null;
      }
      orderBiz.read_pay_order(returnData, param.dtype, param.ui_id, param.orderid);

      sendResp(returnData, response);
      return null;

    } catch (Exception e) {
      log.error("Read_makeOrderAction read_pay_order  is error(DEVICE-JAVA)- P", e);
      returnData.setReturnData(errorcode_systerm, "system is error", "");
    }
    sendResp(returnData, response);
    return null;

  }


}
