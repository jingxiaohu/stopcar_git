
package com.park.pda.action.v1.pda;

import com.park.bean.ReturnDataNew;
import com.park.constants.Constants;
import com.park.mvc.action.v1.BaseV1Controller;
import com.park.pda.action.v1.pda.param.Param_init_pda;
import com.park.pda.service.PDAParkinfoBiz;
import com.park.util.RequestUtil;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 露天停车场PDA帐号信息和经纬度 MAC 的初始化
 *
 * @author jingxiaohu
 */
@Controller
@RequestMapping(value = "/v1")
@Deprecated
//by jxh 2017-7-18 新的版本都不使用该接口进行初始化 PDA设备了
public class Write_PDA_initAction extends BaseV1Controller {


  /**
   * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
   */
  private static final long serialVersionUID = -3599663972160625262L;
  @Autowired
  private PDAParkinfoBiz parkinfoBiz;

  /**
   * 露天停车场PDA帐号信息和经纬度 MAC 的初始化
   * 初始化时根据area_code判断地址编码的park_info...表是否存在，mac是否已被其他停车场绑定。
   */
  @RequestMapping(value = "/init_pda")
  @ResponseBody
  @Deprecated
  public String init_pda(HttpServletResponse response, Param_init_pda param) {
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
      if (param.lng == 0) {
        //lng; //地理经度
        returnData.setReturnData(errorcode_param, " lng=" + param.lng + "  lng is zero", null);
        sendResp(returnData, response);
        return null;
      }
      if (param.lat == 0) {
        //lat;//地理纬度
        returnData.setReturnData(errorcode_param, " lat=" + param.lat + "  lat is zero", null);
        sendResp(returnData, response);
        return null;
      }
      if (RequestUtil.checkObjectBlank(param.loginname)) {
        //帐号
        returnData
            .setReturnData(errorcode_param, " loginname=" + param.loginname + "  loginname is null",
                null);
        sendResp(returnData, response);
        return null;
      }

      if (RequestUtil.checkObjectBlank(param.password)) {
        //密码
        returnData
            .setReturnData(errorcode_param, " password=" + param.password + "  password is null",
                null);
        sendResp(returnData, response);
        return null;
      }

      if (RequestUtil.checkObjectBlank(param.area_code)) {
        //省市县编号 140107
        //避免汉子的问题

        returnData
            .setReturnData(errorcode_param, " area_code=" + param.area_code + "  area_code is null",
                null);
        sendResp(returnData, response);
        return null;
      }
      if (RequestUtil.checkObjectBlank(param.park_type)) {
        //park_type;//停车场类型 0：地下室停车场 1：露天停车场 2：露天立体车库停车场
        returnData
            .setReturnData(errorcode_param, " park_type=" + param.park_type + "  park_type is null",
                null);
        sendResp(returnData, response);
        return null;
      }
      String sign_str = getSignature(Constants.SYSTEM_KEY, param.dtype, param.park_type,
          param.loginname, param.password);
      if (!param.sign.equalsIgnoreCase(sign_str)) {
        log.warn("sign=" + param.sign + "  sign_str=" + sign_str);
        returnData.setReturnData(errorcode_param, " sign is not right", null);
        sendResp(returnData, response);
        return null;
      }
      parkinfoBiz.init_pda(returnData, param.lng, param.lat, param.loginname, param.password,
          param.mac, param.park_type, param.area_code);

      sendResp(returnData, response);
      return null;

    } catch (Exception e) {
      log.error("Write_PDA_initAction init_pda  is error(DEVICE-JAVA)- P", e);
      returnData.setReturnData(errorcode_systerm, "system is error", "");
    }
    sendResp(returnData, response);
    return null;
  }


}
