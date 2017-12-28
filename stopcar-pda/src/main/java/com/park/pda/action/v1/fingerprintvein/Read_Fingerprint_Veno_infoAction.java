package com.park.pda.action.v1.fingerprintvein;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.park.bean.ReturnData;
import com.park.bean.ReturnDataBase;
import com.park.bean.ReturnDataNew;
import com.park.constants.Constants;
import com.park.interceptor.SpringMVCInterceptor;
import com.park.mvc.action.v1.BaseV1Controller;
import com.park.pda.action.v1.fingerprintvein.param.Param_fingerprint_veno_info;
import com.park.pda.service.FingerprintVenoInfoBiz;
import com.park.util.RequestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLDecoder;

/**
 * 指纹系统-通过车牌查询用户指纹、指静脉信息接口
 *
 * @author zyy
 */
@RestController
@RequestMapping(value = "v1", name = "查询用户指纹、指静脉信息")
public class Read_Fingerprint_Veno_infoAction extends BaseV1Controller {

  private static final long serialVersionUID = -6603509476719369632L;

  @Autowired
  protected FingerprintVenoInfoBiz fingerprintVenoInfoBiz;

  @Autowired(required = false)
  private HttpServletRequest request;

  /**
   * 指纹系统-通过车牌查询用户指纹、指静脉信息
   */
  @RequestMapping(value = "/read_finger_veno_info", name = "通过车牌查询用户指纹、指静脉信息")
  public String getFingerVenoInfo(HttpServletRequest request, HttpServletResponse response, Param_fingerprint_veno_info param) {
    ReturnDataNew returnData = new ReturnDataNew();
    try {
      //参数检查和签名验证
      if(!checkParam(param,returnData)){
        sendResp(returnData, response);
        return null;
      }
      fingerprintVenoInfoBiz.queryFingerVenoInfo(param,returnData);
    } catch (Exception e) {
      log.error("Read_Fingerprint_Veno_infoAction getFingerVenoInfo is error (DEVICE-JAVA)- P", e);
      returnData.setReturnData(errorcode_systerm, "system is error", "");
    }
    sendResp(returnData, response);
    return null;
  }

  /**
   * 参数检查和签名验证
   * @param param
   * @param returnData
   * @return
   * @throws Exception
   */
  private boolean checkParam(Param_fingerprint_veno_info param, ReturnDataNew returnData) throws Exception{
    //参数检查
    if (param == null) {
      //参数传递错误
      returnData.setReturnData(errorcode_param, "参数传递错误", "");
      return false;
    }
    //检查是否进行了参数签名认证
    /*if (!param.checkRequest()) {
      returnData.setReturnData(errorcode_param, "没有进行参数签名认证", "");
      return false;
    }*/
    //对封装的参数对象中的属性进行 非空等规则验证
    //用户车牌号
    if (!RequestUtil.checkObjectBlank(param.getCar_code())) {
      param.setCar_code(URLDecoder.decode(param.getCar_code(), Constants.SYSTEM_CHARACTER));
    }else{
      returnData.setReturnData(errorcode_param, "car_code is null", null);
      return false;
    }

    /*String sign_str = getSignature(Constants.SYSTEM_KEY,param.dtype,param.getCar_code());

    if (!param.getSign().equalsIgnoreCase(sign_str)) {
      log.warn("sign=" + param.getSign() + "  sign_str=" + sign_str);
      returnData.setReturnData(errorcode_param, " 验证签名失败", null);
      return false;
    }*/
    return true;
  }

  /**
   * 封装发送响应
   * BaseV1Controller 中有这个方法，这里JSONObject.toJSONString()的时候添加了SerializerFeature.DisableCircularReferenceDetect参数
   * DisableCircularReferenceDetect:消除循环引用
   * @param @param returnData    设定文件
   * @return void   返回类型
   * @Title: sendResp
   */
  protected void sendResp(ReturnDataBase returnData, HttpServletResponse response) {
    if (returnData == null) {
      returnData = new ReturnData();
    }
    response.setContentType("text/json; charset=utf-8");
    response.setCharacterEncoding("utf-8");
    try {
      String data = JSONObject.toJSONString(returnData, SerializerFeature.DisableCircularReferenceDetect);
      request.setAttribute(SpringMVCInterceptor.RESPONSE_DATA, data);
      response.getWriter().write(data);
    } catch (Exception e) {
      log.error("BaseAction.sendResp is error", e);
    }
  }

}