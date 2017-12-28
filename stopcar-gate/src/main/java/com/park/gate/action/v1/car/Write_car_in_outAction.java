package com.park.gate.action.v1.car;


import com.park.bean.ReturnDataNew;
import com.park.constants.Constants;
import com.park.gate.action.v1.car.param.Param_Car_in_out;
import com.park.gate.action.v1.car.param.Param_OpenSigno;
import com.park.gate.service.GateParkinfoBiz;
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
 * 车辆入库出库记录
 *
 * @author jingxiaohu
 */
@Controller
@RequestMapping(value = "/v1")
public class Write_car_in_outAction extends BaseV1Controller {

  /**
   * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
   */
  private static final long serialVersionUID = -3599663972160625262L;

  @Autowired
  private GateParkinfoBiz gateParkinfoBiz;

  /**
   * 车辆入库出库记录
   */
  @RequestMapping(value = "/record_car_in_out")
  @ResponseBody
  public String record_car_in_out(HttpServletRequest request, HttpServletResponse response,
      Param_Car_in_out param) {

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
      if (RequestUtil.checkObjectBlank(param.getPi_id())) {
        //场地主键ID
        returnData.setReturnData(errorcode_param, " pi_id is null", "");
        sendResp(returnData, response);
        return null;
      }
      if (RequestUtil.checkObjectBlank(param.in_out)) {
        returnData.setReturnData(errorcode_param, " in_out is null", "");
        sendResp(returnData, response);
        return null;
      }
      if (RequestUtil.checkObjectBlank(param.getCar_code())) {
        //car_code;//车牌号
        returnData.setReturnData(errorcode_param, "car_code is null", null);
        sendResp(returnData, response);
        return null;
      } else {
        param.setCar_code(URLDecoder.decode(param.getCar_code(), Constants.SYSTEM_CHARACTER));
      }
      if (RequestUtil.checkObjectBlank(param.getIs_enter())) {
        //is_enter;//入库或者出库 ：0：   入库 1：出库
        returnData.setReturnData(errorcode_param, "is_enter is null", null);
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
      if (!RequestUtil.checkObjectBlank(param.getArea_code())) {
        //省市县编号 140107
        //避免汉子的问题
        param.setArea_code(URLDecoder.decode(param.getArea_code(), Constants.SYSTEM_CHARACTER));
      }
      String sign_str = getSignature(Constants.SYSTEM_KEY, param.dtype, param.pi_id, param.in_out);
      if (!param.getSign().equalsIgnoreCase(sign_str)) {
        log.warn("sign=" + param.getSign() + "  sign_str=" + sign_str);
        returnData.setReturnData(errorcode_param, " sign is not right", null);
        sendResp(returnData, response);
        return null;
      }
    /*parkinfoBiz.record_car_in_out(returnData,param.pi_id,param.pd_id,param.car_code,param.is_enter,
        param.in_out,param.in_out_code,param.car_type,param.car_code_color,param.area_code,
				param.out_type,param.is_local_month);*/
      gateParkinfoBiz.record_car_in_out(returnData,
          param.dtype,
          param.getPi_id(),
          param.getPd_id(),
          param.getCar_code(),
          param.getIs_enter(),
          param.getIn_out(),
          param.getIn_out_code(),
          param.getCar_type(),
          param.getCar_code_color(),
          param.getArea_code(),
          param.getOut_type(),
          param.getIs_local_month(),
          param.order_id,
          is_sync,
          param.getSync_time(),
          param.gov_num,
          param.magnetic_force
      );

      sendResp(returnData, response);
      return null;

    } catch (Exception e) {
      log.error("Write_car_in_outAction.record_car_in_out is error (JAVA)- P", e);
      returnData.setReturnData(errorcode_systerm, "system is error", "");
    }

    sendResp(returnData, response);
    return null;
  }

  /**
   * 开闸----该接口是否废弃需要  郑虎确认  暂时废弃掉
   */
  @Deprecated
  @RequestMapping(value = "/open_signo")
  @ResponseBody
  public String open_signo(HttpServletRequest request, HttpServletResponse response,
                           Param_OpenSigno param) {

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
      if (RequestUtil.checkObjectBlank(param.getPi_id())) {
        //场地主键ID
        returnData.setReturnData(errorcode_param, " pi_id is null", "");
        sendResp(returnData, response);
        return null;
      }
      if (RequestUtil.checkObjectBlank(param.getIs_cash())) {
        returnData.setReturnData(errorcode_param, " is_cash is null", "");
        sendResp(returnData, response);
        return null;
      }
      if (!RequestUtil.checkObjectBlank(param.getArea_code())) {
        //省市县编号 140107
        //避免汉子的问题
        param.setArea_code(URLDecoder.decode(param.getArea_code(), Constants.SYSTEM_CHARACTER));
      }
      if (RequestUtil.checkObjectBlank(param.getOrder_id())) {
        returnData.setReturnData(errorcode_param, " order_id is null", "");
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
      String sign_str = getSignature(Constants.SYSTEM_KEY, param.dtype, param.pi_id, param.is_cash);
      if (!param.getSign().equalsIgnoreCase(sign_str)) {
        log.warn("sign=" + param.getSign() + "  sign_str=" + sign_str);
        returnData.setReturnData(errorcode_param, " sign is not right", null);
        sendResp(returnData, response);
        return null;
      }
      gateParkinfoBiz.open_signo(returnData, param.getPi_id(),
          param.getArea_code(),
          param.getIs_cash(), param.getOrder_id(),
          is_sync, param.getSync_time(),param.getIs_open()
      );

      sendResp(returnData, response);
      return null;

    } catch (Exception e) {
      log.error("Write_car_in_outAction.open_signo is error (JAVA)- P", e);
      returnData.setReturnData(errorcode_systerm, "system is error", "");
    }

    sendResp(returnData, response);
    return null;
  }
}
