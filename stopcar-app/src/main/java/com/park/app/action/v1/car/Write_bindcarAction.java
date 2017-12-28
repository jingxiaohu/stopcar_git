package com.park.app.action.v1.car;


import com.park.app.service.CarBiz;
import com.park.app.action.v1.car.param.Param_bindcar;
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
import org.springframework.web.multipart.MultipartFile;

/**
 * 用户添加或者删除绑定车牌号
 *
 * @author jingxiaohu
 */
@Controller
@RequestMapping(value = "/v1")
public class Write_bindcarAction extends BaseV1Controller {

  private static final long serialVersionUID = -3599663972160625262L;

  @Autowired
  private CarBiz carBiz;

  /**
   * 用户添加或者删除绑定车牌号
   */
  @RequestMapping(value = "/bindcar")
  @ResponseBody
  public String bindcar(HttpServletRequest request, HttpServletResponse response,
      Param_bindcar param) {

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

      if (param.act < 1) {
        //用户行为动作
        returnData
            .setReturnData(errorcode_param, " act=" + param.act + "  act is smaller than zero",
                null);
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
      String sign_str = getSignature(Constants.SYSTEM_KEY, param.dtype, param.ui_id, param.car_code,
          param.act);
      if (!param.sign.equalsIgnoreCase(sign_str)) {
        log.warn("sign=" + param.sign + "  sign_str=" + sign_str);
        returnData.setReturnData(errorcode_param, " sign is not right", null);
        sendResp(returnData, response);
        return null;
      }
      carBiz.bindcar(returnData, param.ui_id, param.car_code, param.act);

      sendResp(returnData, response);
      return null;

    } catch (Exception e) {
      log.error("Write_bindcarAction bindcar  is error(DEVICE-JAVA)- P", e);
      returnData.setReturnData(errorcode_systerm, "system is error", "");
    }
    sendResp(returnData, response);
    return null;
  }

  /**
   * 用户更新车辆信息
   */
  @RequestMapping(value = "/update_car")
  @ResponseBody
  public String update_car(HttpServletRequest request, HttpServletResponse response,
      Param_bindcar param) {
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
      if (param.ui_id < 1) {
        //用户ID
        returnData
            .setReturnData(errorcode_param, " ui_id=" + param.ui_id + "  ui_id is smaller than one",
                null);
        sendResp(returnData, response);
        return null;
      }

      if (param.uc_id < 1) {
        //主键ID
        returnData
            .setReturnData(errorcode_param, " uc_id=" + param.uc_id + "  uc_id is smaller than one",
                null);
        sendResp(returnData, response);
        return null;
      }

      if (!RequestUtil.checkObjectBlank(param.car_code)) {
        param.car_code = URLDecoder.decode(param.car_code, Constants.SYSTEM_CHARACTER);
      }

      if (!RequestUtil.checkObjectBlank(param.car_brand)) {
        //车辆品牌
        param.car_brand = URLDecoder.decode(param.car_brand, Constants.SYSTEM_CHARACTER);
      }

      MultipartFile lience = param.getLience();
      String lienceFileName = null;
      if (lience != null) {
        lienceFileName = lience.getOriginalFilename();
        param.setLienceFileName(lienceFileName);
      }
      String sign_str = getSignature(Constants.SYSTEM_KEY, param.dtype, param.ui_id);
      if (!param.sign.equalsIgnoreCase(sign_str)) {
        log.warn("sign=" + param.sign + "  sign_str=" + sign_str);
        returnData.setReturnData(errorcode_param, " sign is not right", null);
        sendResp(returnData, response);
        return null;
      }
      carBiz.update_car(returnData, param.ui_id, param.car_code, param.uc_id, param.lience,
          param.lienceFileName, param.lienceContentType, param.car_brand, param.uc_color,
          param.run_code);

      sendResp(returnData, response);
      return null;

    } catch (Exception e) {
      log.error("Write_bindcarAction update_car  is error(DEVICE-JAVA)- P", e);
      returnData.setReturnData(errorcode_systerm, "system is error", "");
    }
    sendResp(returnData, response);
    return null;
  }
}
