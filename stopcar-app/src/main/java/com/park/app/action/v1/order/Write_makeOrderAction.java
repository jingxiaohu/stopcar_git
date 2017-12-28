
package com.park.app.action.v1.order;

import com.park.app.action.v1.order.param.Param_expect_order;
import com.park.app.service.AppOrderBiz;
import com.park.bean.ReturnDataNew;
import com.park.constants.Constants;
import com.park.mvc.action.v1.BaseV1Controller;
import com.park.util.RequestUtil;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 用户手动下单 （1：预约下单普通车位  2：下单租赁包月车位 3:用户取消预约订单）
 *
 * @author jingxiaohu
 */
@Controller
@RequestMapping(value = "/v1")
public class Write_makeOrderAction extends BaseV1Controller {


  /**
   * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
   */
  private static final long serialVersionUID = -3599663972160625262L;

  @Autowired
  private AppOrderBiz orderBiz;

  /**
   * 用户预约下单普通车位（普通车位-用户预约下单）
   */
  @RequestMapping(value = "/expect_order")
  @ResponseBody
  public String expect_order(HttpServletResponse response, Param_expect_order param) {
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

      if (RequestUtil.checkObjectBlank(param.car_code)) {
        //car_code 车牌号
        returnData
            .setReturnData(errorcode_param, " car_code=" + param.car_code + "  car_code is null",
                null);
        sendResp(returnData, response);
        return null;
      } else {
        param.car_code = URLDecoder.decode(param.car_code, Constants.SYSTEM_CHARACTER);
      }
      if (param.expect_money < 1) {
        //expect_money 预约价格
        returnData.setReturnData(errorcode_param,
            " expect_money=" + param.expect_money + "  expect_money is smaller than zero", null);
        sendResp(returnData, response);
        return null;
      }
      if (param.expect_time < 1) {
        //expect_time 预约时间
        returnData.setReturnData(errorcode_param,
            " expect_time=" + param.expect_time + "  expect_time is smaller than zero", null);
        sendResp(returnData, response);
        return null;
      }

      if (RequestUtil.checkObjectBlank(param.pp_versioncode)) {
        //当前APPSDK版本号 （内部升级版本代号）
        returnData.setReturnData(errorcode_param,
            " pp_versioncode=" + param.pp_versioncode + "  pp_versioncode is null", null);
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
        //area_code;省市区区域代码
        returnData
            .setReturnData(errorcode_param, " area_code=" + param.area_code + "  area_code is null",
                null);
        sendResp(returnData, response);
        return null;
      }

      String sign_str = getSignature(Constants.SYSTEM_KEY, param.dtype, param.ui_id,
          param.car_code);
      if (!param.sign.equalsIgnoreCase(sign_str)) {
        log.warn("sign=" + param.sign + "  sign_str=" + sign_str);
        returnData.setReturnData(errorcode_param, " sign is not right", null);
        sendResp(returnData, response);
        return null;
      }
      orderBiz.expect_order(returnData, param.dtype, param.ui_id, param.pi_id, param.pay_type,
          param.expect_info, param.expect_money, param.expect_time, param.car_code,
          param.pp_versioncode, param.area_code);

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
   * 下单租赁包月车位(车位租赁)
   */
  @RequestMapping(value = "/pay_rent_order")
  @ResponseBody
  public String pay_rent_order(HttpServletResponse response, Param_expect_order param) {
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
      if (param.is_24hours == 0 && param.ui_id < 1) {
        //用户ID
        returnData.setReturnData(errorcode_param,
            " ui_id=" + param.ui_id + "  ui_id is smaller than zero", null);
        sendResp(returnData, response);
        return null;
      }

      if (RequestUtil.checkObjectBlank(param.car_code)) {
        //car_code 车牌号
        returnData
            .setReturnData(errorcode_param, " car_code=" + param.car_code + "  car_code is null",
                null);
        sendResp(returnData, response);
        return null;
      } else {
        param.car_code = URLDecoder.decode(param.car_code, Constants.SYSTEM_CHARACTER);
      }
      if (!RequestUtil.checkObjectBlank(param.month_info)) {
        //month_info;//包月提示信息
        param.month_info = URLDecoder.decode(param.month_info, Constants.SYSTEM_CHARACTER);
      }
      if (param.month_num < 1) {
        //month_num 预约月数
        returnData.setReturnData(errorcode_param,
            " month_num=" + param.month_num + "  month_num is smaller than zero", null);
        sendResp(returnData, response);
        return null;
      }

      if (RequestUtil.checkObjectBlank(param.permit_time)) {
        //permit_time;//准入时间段（17:00-08:30）
        returnData.setReturnData(errorcode_param,
            " permit_time=" + param.permit_time + "  permit_time is null", null);
        sendResp(returnData, response);
        return null;
      }

      if (RequestUtil.checkObjectBlank(param.pp_versioncode)) {
        //当前APPSDK版本号 （内部升级版本代号）
        returnData.setReturnData(errorcode_param,
            " pp_versioncode=" + param.pp_versioncode + "  pp_versioncode is null", null);
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
        //area_code;省市区区域代码
        returnData
            .setReturnData(errorcode_param, " area_code=" + param.area_code + "  area_code is null",
                null);
        sendResp(returnData, response);
        return null;
      }

      String sign_str = getSignature(Constants.SYSTEM_KEY, param.dtype, param.ui_id,
          param.car_code);
      if (!param.sign.equalsIgnoreCase(sign_str)) {
        log.warn("sign=" + param.sign + "  sign_str=" + sign_str);
        returnData.setReturnData(errorcode_param, " sign is not right", null);
        sendResp(returnData, response);
        return null;
      }
      orderBiz.pay_rent_order(returnData, param.dtype, param.ui_id, param.pi_id, param.pay_type,
          param.month_num, param.month_info, param.car_code,
          param.pp_versioncode, param.area_code, param.upc_id,
          param.pay_source, param.permit_time, param.is_24hours,
          param.orderid);

      sendResp(returnData, response);
      return null;

    } catch (Exception e) {
      log.error("Write_makeOrderAction expect_rent_order  is error(DEVICE-JAVA)- P", e);
      returnData.setReturnData(errorcode_systerm, "system is error", "");
    }
    sendResp(returnData, response);
    return null;
  }


  /**
   * 用户普通停车位直接正式付款下单
   */
  @RequestMapping(value = "/pay_order")
  @ResponseBody
  public String pay_order(HttpServletResponse response, Param_expect_order param) {
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

      String sign_str = getSignature(Constants.SYSTEM_KEY, param.dtype, param.ui_id, param.orderid);
      if (!param.sign.equalsIgnoreCase(sign_str)) {
        log.warn("sign=" + param.sign + "  sign_str=" + sign_str);
        returnData.setReturnData(errorcode_param, " sign is not right", null);
        sendResp(returnData, response);
        return null;
      }
      orderBiz.pay_order(returnData, param.dtype, param.ui_id, param.orderid, param.upc_id,
          param.pay_source);

      sendResp(returnData, response);
      return null;

    } catch (Exception e) {
      log.error("Write_makeOrderAction pay_order  is error(DEVICE-JAVA)- P", e);
      returnData.setReturnData(errorcode_systerm, "system is error", "");
    }
    sendResp(returnData, response);
    return null;
  }


  /**
   * 用户取消下单
   */
  @RequestMapping(value = "/cancel_order")
  @ResponseBody
  public String cancel_order(HttpServletResponse response, Param_expect_order param) {
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
        //orderid;//我们的订单号  字符串
        returnData.setReturnData(errorcode_param, " orderid=" + param.orderid + "  orderid is null",
            null);
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
        //area_code;省市区区域代码
        returnData
            .setReturnData(errorcode_param, " area_code=" + param.area_code + "  area_code is null",
                null);
        sendResp(returnData, response);
        return null;
      }

      String sign_str = getSignature(Constants.SYSTEM_KEY, param.dtype, param.ui_id, param.orderid,
          param.type);
      if (!param.sign.equalsIgnoreCase(sign_str)) {
        log.warn("sign=" + param.sign + "  sign_str=" + sign_str);
        returnData.setReturnData(errorcode_param, " sign is not right", null);
        sendResp(returnData, response);
        return null;
      }
      orderBiz.cancel_order(returnData, param.dtype, param.ui_id, param.orderid, param.type,
          param.pi_id, param.area_code);

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
   * 用户删除订单
   */
  @RequestMapping(value = "/delete_order")
  @ResponseBody
  public String delete_order(HttpServletResponse response, Param_expect_order param) {
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
        //orderid;//我们的订单号  字符串
        returnData.setReturnData(errorcode_param, " orderid=" + param.orderid + "  orderid is null",
            null);
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
        //area_code;省市区区域代码
        returnData
            .setReturnData(errorcode_param, " area_code=" + param.area_code + "  area_code is null",
                null);
        sendResp(returnData, response);
        return null;
      }

      String sign_str = getSignature(Constants.SYSTEM_KEY, param.dtype, param.ui_id, param.orderid,
          param.type);
      if (!param.sign.equalsIgnoreCase(sign_str)) {
        log.warn("sign=" + param.sign + "  sign_str=" + sign_str);
        returnData.setReturnData(errorcode_param, " sign is not right", null);
        sendResp(returnData, response);
        return null;
      }
      orderBiz.delete_order(returnData, param.dtype, param.ui_id, param.orderid, param.type,
          param.pi_id, param.area_code);

      sendResp(returnData, response);
      return null;

    } catch (Exception e) {
      log.error("Write_makeOrderAction delete_order  is error(DEVICE-JAVA)- P", e);
      returnData.setReturnData(errorcode_systerm, "system is error", "");
    }
    sendResp(returnData, response);
    return null;
  }


}
