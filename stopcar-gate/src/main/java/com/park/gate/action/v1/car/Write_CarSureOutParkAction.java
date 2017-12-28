
package com.park.gate.action.v1.car;

import com.alibaba.fastjson.JSONObject;
import com.park.bean.ReturnDataNew;
import com.park.constants.Constants;
import com.park.gate.action.v1.car.param.Param_pay_sure;
import com.park.gate.action.v1.car.param.Param_pay_sure_new;
import com.park.gate.service.GateOrderBiz;
import com.park.mvc.action.v1.BaseV1Controller;
import com.park.util.RequestUtil;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.net.URLDecoder;

/**
 * 道闸停车场的更新用户现金支付状态和金额
 *
 * @author jingxiaohu
 */
@Controller
@RequestMapping(value = "/v1")
public class Write_CarSureOutParkAction extends BaseV1Controller {


  /**
   * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
   */
  private static final long serialVersionUID = -3599663972160625262L;


  @Autowired
  private GateOrderBiz gateOrderBiz;
  /**
   * 道闸停车场的更新用户现金支付状态和金额
   */
  @RequestMapping(value = "/pay_sure")
  @ResponseBody
  public String pay_sure(HttpServletRequest request, HttpServletResponse response,
      Param_pay_sure param) {
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

      String sign_str = getSignature(Constants.SYSTEM_KEY, param.dtype, param.orderid, param.pi_id);
      if (!param.sign.equalsIgnoreCase(sign_str)) {
        log.warn("sign=" + param.sign + "  sign_str=" + sign_str);
        returnData.setReturnData(errorcode_param, " sign is not right", null);
        sendResp(returnData, response);
        return null;
      }
      gateOrderBiz.pay_sure(returnData, param.dtype, param.orderid, param.pi_id, param.area_code,
          param.money, param.type, is_sync, param.getSync_time());

      sendResp(returnData, response);
      return null;

    } catch (Exception e) {
      log.error("Write_CarSureOutParkAction pay_sure  is error(DEVICE-JAVA)- P", e);
      returnData.setReturnData(errorcode_systerm, "system is error", "");
    }
    sendResp(returnData, response);
    return null;
  }

  /**
   * 道闸停车场的更新用户现金和在线支付的支付状态和金额
   * 处理客户端对订单支付的确认信息记录，同时记录异常订单信息
   * 新增支付类型参数is_cash(0：在线支付  1：现金支付)
   */
  @RequestMapping(value = "/pay_sure_new")
  @ResponseBody
  public String pay_sure_new(HttpServletRequest request, HttpServletResponse response,
      Param_pay_sure_new param) {
    ReturnDataNew returnData = new ReturnDataNew();
    JSONObject returnJson = new JSONObject();
    try {
//      boolean is_sync = false;//是否异步上传
      //参数检查和参数签名认证
      if(!checkParam(param,returnData)){
        sendResp(returnData, response);
        return null;
      }

      returnJson = gateOrderBiz.pay_sure_new(returnData, param.dtype, param.orderid, param.pi_id, param.area_code,
          param.money, param.type, param.is_sync, param.sync_time,param.is_cash);

    } catch (Exception e) {
      log.error("Write_CarSureOutParkAction pay_sure_new  is error(DEVICE-JAVA)- P", e);
      returnData.setReturnData(errorcode_systerm, "system is error", "");
    }
    sendResp(returnJson, response);
    return null;
  }

  /**
   * 参数检查和签名验证
   * @param param
   * @param returnData
   * @return
   */
  public boolean checkParam(Param_pay_sure_new param,ReturnDataNew returnData) throws Exception{
    //参数检查
    if(param == null){
      returnData.setReturnData(errorcode_param, "参数传递错误","");
      return false;
    }
    //参数签名验证
    if(!param.checkRequest()){
      returnData.setReturnData(errorcode_param, "没有进行参数签名认证", "");
    }
    //对封装的参数对象中的属性进行 非空等规则验证
    if (RequestUtil.checkObjectBlank(param.orderid)) {
      //orderid;//订单号  字符串
      returnData.setReturnData(errorcode_param, " orderid=" + param.orderid + "  orderid is null",null);
      return false;
    }
    if (RequestUtil.checkObjectBlank(param.getPi_id())) {
      //停车场主键ID
      returnData.setReturnData(errorcode_param, " pi_id=" + param.pi_id + "  pi_id is null",null);
      return false;
    }
    if (!RequestUtil.checkObjectBlank(param.getArea_code())) {
      //地址区域编码
      param.setArea_code(URLDecoder.decode(param.getArea_code(), Constants.SYSTEM_CHARACTER));
    }else{
      returnData.setReturnData(errorcode_param, " area_code=" + param.area_code + "  area_code is null",null);
      return false;
    }
    if(RequestUtil.checkObjectBlank(param.getIs_sync())){
      //是否异步上传（is_sync是否异步上传  0：否 1：是）
      returnData.setReturnData(errorcode_param, " is_sync=" + param.is_sync + "  is_sync is null",null);
      return false;
    }
    if (param.getIs_sync() == 1 && (RequestUtil.checkObjectBlank(param.getSync_time()) || param.getSync_time() == 0)) {
        //异步上传必须传时间
        returnData.setReturnData(errorcode_param, "异步上传时sync_time 必须上传", null);
        return false;
    }
    if (RequestUtil.checkObjectBlank(param.getIs_cash())) {
        //支付类型(is_cash:  0：在线支付  1：现金支付)
        returnData.setReturnData(errorcode_param, "is_cash=" + param.is_cash + "  is_cash is null", null);
        return false;
    }
    //字符串签名
    String sign_str = getSignature(Constants.SYSTEM_KEY, param.dtype, param.orderid, param.pi_id,param.is_cash,param.is_sync);
    if (!param.getSign().equalsIgnoreCase(sign_str)) {
      log.warn("sign=" + param.getSign() + "  sign_str=" + sign_str);
      returnData.setReturnData(errorcode_param, " sign is not right", null);
      return false;
    }

    return true;
  }
}