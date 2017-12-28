
package com.park.app.action.v1.park;

import com.park.app.action.v1.park.param.Param_gpspark;
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
 * 通过GPS导航获取 该经纬度范围内的停车场数据列表
 *
 * @author jingxiaohu
 */
@Controller
@RequestMapping(value = "/v1")
public class Read_GpsParkAction extends BaseV1Controller {

  /**
   *
   */
  private static final long serialVersionUID = 4815394451510113939L;

  @Autowired
  private AppParkinfoBiz parkinfoBiz;

  /**
   * 通过GPS导航获取 该经纬度范围内的停车场数据列表
   */
  @RequestMapping(value = "/read_gpspark")
  @ResponseBody
  public String read_gpspark(HttpServletResponse response, Param_gpspark param) {

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
        returnData.setReturnData(errorcode_param, " ui_id is null", "");
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
      String sign_str = getSignature(Constants.SYSTEM_KEY, param.dtype, param.ui_id);
      if (!param.sign.equalsIgnoreCase(sign_str)) {
        log.warn("sign=" + param.sign + "  sign_str=" + sign_str);
        returnData.setReturnData(errorcode_param, " sign is not right", "");
        sendResp(returnData, response);
        return null;
      }

      parkinfoBiz.ReturnRead_gpspark(returnData, param.dtype, param.ui_id, param.lng, param.lat,
          param.distance, param.park_type, param.type, param.area_code);
      sendResp(returnData, response);
      return null;

    } catch (Exception e) {
      log.error(
          "Read_GpsParkAction.read_gpspark is error  2.21	Read-通过GPS导航获取 该经纬度范围内的停车场数据列表 (APPSDK-JAVA)- P",
          e);
      returnData.setReturnData(errorcode_systerm, "system is error", "");
    }
    sendResp(returnData, response);
    return null;
  }


  /**
   * 通过GPS导航获取 该经纬度范围内的停车场数据列表 ---- 车位租赁
   */
  @RequestMapping(value = "/read_gpspark_rent")
  @ResponseBody
  public String read_gpspark_rent(HttpServletResponse response, Param_gpspark param) {

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
        returnData.setReturnData(errorcode_param, " ui_id is smaller than zero", "");
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

      String sign_str = getSignature(Constants.SYSTEM_KEY, param.dtype, param.ui_id);
      if (!param.sign.equalsIgnoreCase(sign_str)) {
        log.warn("sign=" + param.sign + "  sign_str=" + sign_str);
        returnData.setReturnData(errorcode_param, " sign is not right", "");
        sendResp(returnData, response);
        return null;
      }

      parkinfoBiz
          .ReturnRead_gpspark_rent(returnData, param.dtype, param.ui_id, param.lng, param.lat,
              param.distance, param.type, param.park_type, param.area_code);
      sendResp(returnData, response);
      return null;

    } catch (Exception e) {
      log.error(
          "Read_GpsParkAction.read_gpspark_rent is error  2.21	Read-通过GPS导航获取 该经纬度范围内的停车场数据列表--车位租赁 (APPSDK-JAVA)- P",
          e);
      returnData.setReturnData(errorcode_systerm, "system is error", "");
    }
    sendResp(returnData, response);
    return null;
  }


}
