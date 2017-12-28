package com.park.pda.action.v1.fingerprintvein;

import com.park.bean.ReturnDataNew;
import com.park.constants.Constants;
import com.park.mvc.action.v1.BaseV1Controller;
import com.park.pda.action.v1.fingerprintvein.param.Param_fingerprint_veno_userinfo;
import com.park.pda.service.FingerprintVenoUserinfoBiz;
import com.park.util.RequestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 指纹系统-通过手机号码查询用户采集的信息接口
 *
 * @author zyy
 */
@RestController
@RequestMapping(value = "v1", name = "查询用户采集的信息")
public class Read_Fingerprint_Veno_userinfoAction extends BaseV1Controller {

  private static final long serialVersionUID = -6603509476719362632L;

  @Autowired
  protected FingerprintVenoUserinfoBiz fingerprintVenoUserinfoBiz;

  /**
   * 指纹系统-通过手机号码查询用户采集的信息
   */
  @RequestMapping(value = "/read_finger_veno_userinfo", name = "根据手机号读取用户采集的信息")
  public String getFingerVenoUserinfo(HttpServletRequest request, HttpServletResponse response, Param_fingerprint_veno_userinfo param) {
    ReturnDataNew returnData = new ReturnDataNew();
    try {
      //参数检查和签名验证
      if(!checkParam(param,returnData)){
        sendResp(returnData, response);
        return null;
      }
      fingerprintVenoUserinfoBiz.queryFingerVenoUserinfo(param,returnData);
    } catch (Exception e) {
      log.error("Read_Fingerprint_Veno_userinfoAction queryFingerVenoUserinfo is error (DEVICE-JAVA)- P", e);
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
  private boolean checkParam(Param_fingerprint_veno_userinfo param, ReturnDataNew returnData) throws Exception{
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
    if (RequestUtil.checkObjectBlank(param.getUi_tel())) {
      returnData.setReturnData(errorcode_param, " ui_tel is null", null);
      return false;
    }

    String sign_str = getSignature(Constants.SYSTEM_KEY,param.dtype,param.getUi_tel());
    if (!param.getSign().equalsIgnoreCase(sign_str)) {
      log.warn("sign=" + param.getSign() + "  sign_str=" + sign_str);
      returnData.setReturnData(errorcode_param, " 验证签名失败", null);
      return false;
    }
    return true;
  }

}