
package com.park.gate.action.v1.park;

import com.park.bean.ReturnDataNew;
import com.park.constants.Constants;
import com.park.gate.action.v1.park.param.Param_charging_rule;
import com.park.gate.service.GateParkinfoBiz;
import com.park.mvc.action.v1.BaseV1Controller;
import com.park.util.RequestUtil;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 记录停车场计费规则信息
 *
 * @author jingxiaohu
 */
@Controller
@RequestMapping(value = "/v1")
public class Write_rental_charging_ruleAction extends BaseV1Controller {


  /**
   * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
   */
  private static final long serialVersionUID = -3599663972160625262L;

  @Autowired
  private GateParkinfoBiz gateParkinfoBiz;

  /**
   * 记录停车场计费规则信息
   */
  @RequestMapping(value = "/charging_rule")
  @ResponseBody
  public String charging_rule(HttpServletResponse response, Param_charging_rule param) {
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
      if (param.pi_id == null) {
        //场地主键ID
        returnData
            .setReturnData(errorcode_param, " pi_id=" + param.pi_id + "  pi_id is null", null);
        sendResp(returnData, response);
        return null;
      }


      if (param.rcr_type == null) {
        //场地主键ID
        returnData
            .setReturnData(errorcode_param, " rcr_type=" + param.rcr_type + "  rcr_type is null",
                null);
        sendResp(returnData, response);
        return null;
      }

      if (param.car_type == null) {
        //场地主键ID
        returnData
            .setReturnData(errorcode_param, " car_type=" + param.car_type + "  car_type is null",
                null);
        sendResp(returnData, response);
        return null;
      }

      if (param.car_code_color == null) {
        //场地主键ID
        returnData.setReturnData(errorcode_param,
            " car_code_color=" + param.car_code_color + "  car_code_color is null", null);
        sendResp(returnData, response);
        return null;
      }
      if (param.area_code == null) {
        //起步价（RMB 单位 分）
        returnData
            .setReturnData(errorcode_param, " area_code=" + param.area_code + "  area_code is null",
                null);
        sendResp(returnData, response);
        return null;
      } else {
        //省市县编号 140107
        //避免汉子的问题
        param.area_code = URLDecoder.decode(param.area_code, Constants.SYSTEM_CHARACTER);
      }
      if (!RequestUtil.checkObjectBlank(param.timeout_info)) {
        param.timeout_info = URLDecoder.decode(param.timeout_info, Constants.SYSTEM_CHARACTER);
      }
      if (!RequestUtil.checkObjectBlank(param.time_bucket)) {
        param.time_bucket = URLDecoder.decode(param.time_bucket, Constants.SYSTEM_CHARACTER);
      }

      String sign_str = getSignature(Constants.SYSTEM_KEY, param.dtype, param.pi_id,
          param.start_price, param.charging);
      if (!param.sign.equalsIgnoreCase(sign_str)) {
        log.warn("sign=" + param.sign + "  sign_str=" + sign_str);
        returnData.setReturnData(errorcode_param, " sign is not right", null);
        sendResp(returnData, response);
        return null;
      }
      gateParkinfoBiz.record_charging_rule(returnData, param.pi_id, param.start_price, param.start_time,
          param.charging, param.charging_time, param.month_price, param.month_time,
          param.permit_time, param.timeout_info, param.rcr_type, param.rcr_state,
          param.rcr_discount,
          param.car_displacement, param.car_type, param.car_code_color, param.is_time_bucket,
          param.time_bucket, param.area_code, param.roadside_type);

      sendResp(returnData, response);
      return null;

    } catch (Exception e) {
      log.error("Write_rental_charging_ruleAction charging_rule  is error(DEVICE-JAVA)- P", e);
      returnData.setReturnData(errorcode_systerm, "system is error", "");
    }
    sendResp(returnData, response);
    return null;
  }


  /**
   * 更新停车场规则
   */
  @RequestMapping(value = "/update_charging_rule")
  @ResponseBody
  public String update_charging_rule(HttpServletResponse response, Param_charging_rule param) {
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
      if (param.rcr_id < 1) {
        //rcr_id;//规则表主键ID
        returnData
            .setReturnData(errorcode_param, " rcr_id=" + param.rcr_id + "  rcr_id is smaller zero",
                null);
        sendResp(returnData, response);
        return null;
      }
      if (!RequestUtil.checkObjectBlank(param.timeout_info)) {
        param.timeout_info = URLDecoder.decode(param.timeout_info, Constants.SYSTEM_CHARACTER);
      }

      if (!RequestUtil.checkObjectBlank(param.time_bucket)) {
        param.time_bucket = URLDecoder.decode(param.time_bucket, Constants.SYSTEM_CHARACTER);
      }

      if (!RequestUtil.checkObjectBlank(param.area_code)) {
        //省市县编号 140107
        //避免汉子的问题
        param.area_code = URLDecoder.decode(param.area_code, Constants.SYSTEM_CHARACTER);
      }
      String sign_str = getSignature(Constants.SYSTEM_KEY, param.dtype, param.rcr_id);
      if (!param.sign.equalsIgnoreCase(sign_str)) {
        log.warn("sign=" + param.sign + "  sign_str=" + sign_str);
        returnData.setReturnData(errorcode_param, " sign is not right", null);
        sendResp(returnData, response);
        return null;
      }
      gateParkinfoBiz.update_charging_rule(returnData, param.rcr_id, param.pi_id, param.start_price,
          param.start_time, param.charging, param.charging_time, param.month_price,
          param.month_time, param.permit_time, param.timeout_info, param.rcr_type,
          param.rcr_state, param.rcr_discount, param.car_displacement, param.car_type,
          param.car_code_color, param.is_time_bucket, param.time_bucket, param.area_code,
          param.roadside_type);

      sendResp(returnData, response);
      return null;

    } catch (Exception e) {
      log.error("Write_rental_charging_ruleAction update_charging_rule  is error(DEVICE-JAVA)- P",
          e);
      returnData.setReturnData(errorcode_systerm, "system is error", "");
    }
    sendResp(returnData, response);
    return null;
  }


}
