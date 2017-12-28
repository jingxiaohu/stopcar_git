package com.park.pda.action.v1.fingerprintvein;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.park.bean.ReturnData;
import com.park.bean.ReturnDataBase;
import com.park.bean.ReturnDataNew;
import com.park.constants.Constants;
import com.park.interceptor.SpringMVCInterceptor;
import com.park.mvc.action.v1.BaseV1Controller;
import com.park.pda.action.v1.fingerprintvein.param.Param_fingerprint_veno_userOrderList;
import com.park.pda.service.FingerprintVenoUserOrderListBiz;
import com.park.util.RequestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 指纹系统--查询用户已经支付成功的订单流水(指纹/指静脉支付)
 *
 * @author zyy
 */
@RestController
@RequestMapping(value = "/v1")
public class Read_Fingerprint_Veno_userOrderListAction extends BaseV1Controller {

  private static final long serialVersionUID = -3592663972320625463L;
  @Autowired
  protected FingerprintVenoUserOrderListBiz fVUserOrderListBiz;
  @Autowired(required = false)
  private HttpServletRequest request;

  @RequestMapping(value = "/finger_veno_user_orderList")
  public String getUserOrderList(HttpServletRequest request, HttpServletResponse response,Param_fingerprint_veno_userOrderList param) {
    ReturnDataNew returnData = new ReturnDataNew();
    try {
      //参数检查和签名验证
      if(!checkParamT(param,returnData)){
        sendResp(returnData, response);
        return null;
      }
      fVUserOrderListBiz.getUserOrderList(returnData, param.ui_tel,param.page,param.size);
    } catch (Exception e) {
      log.error("Read_Fingerprint_Veno_userOrderListAction getUserOrderList is error (DEVICE-JAVA)- P", e);
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
  private boolean checkParamT(Param_fingerprint_veno_userOrderList param, ReturnDataNew returnData) throws Exception{
    //参数检查
    if (param == null) {
      //参数传递错误
      returnData.setReturnData(errorcode_param, "参数传递错误", "");
      return false;
    }
    //检查是否进行了参数签名认证
    if (!param.checkRequest()) {
      returnData.setReturnData(errorcode_param, "没有进行参数签名认证", "");
      return false;
    }
    //对封装的参数对象中的属性进行 非空等规则验证
    //用户手机号码
    if (RequestUtil.checkObjectBlank(param.ui_tel)) {
      returnData.setReturnData(errorcode_param, " ui_tel is null", null);
      return false;
    }

    String sign_str = getSignature(Constants.SYSTEM_KEY,param.dtype,param.ui_tel);
    if (!param.getSign().equalsIgnoreCase(sign_str)) {
      log.warn("sign=" + param.getSign() + "  sign_str=" + sign_str);
      returnData.setReturnData(errorcode_param, " 验证签名失败", null);
      return false;
    }
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
