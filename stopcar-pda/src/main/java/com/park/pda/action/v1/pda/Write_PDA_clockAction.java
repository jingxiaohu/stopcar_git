
package com.park.pda.action.v1.pda;

import com.park.bean.ReturnDataNew;
import com.park.constants.Constants;
import com.park.mvc.action.v1.BaseV1Controller;
import com.park.pda.action.v1.pda.param.Param_pda_clock;
import com.park.pda.service.PDAParkinfoBiz;
import com.park.util.RequestUtil;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 露天停车场PDA 停车场员工上班下班打卡
 *
 * @author jingxiaohu
 */
@Controller
@RequestMapping(value = "/v1")
public class Write_PDA_clockAction extends BaseV1Controller {


  /**
   * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
   */
  private static final long serialVersionUID = -3599663972160625262L;

  @Autowired
  private PDAParkinfoBiz parkinfoBiz;

  /**
   * 露天停车场PDA 停车场员工上班下班打卡
   */
  @RequestMapping(value = "/pda_clock")
  @ResponseBody
  public String pda_clock(HttpServletResponse response, Param_pda_clock param) {
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
      if (param.lng == null) {
        //lng; //地理经度
        returnData.setReturnData(errorcode_param, " lng is null", null);
        sendResp(returnData, response);
        return null;
      }
      if (param.lat == null) {
        //lat;//地理纬度
        returnData.setReturnData(errorcode_param, " lat is null", null);
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

      if (RequestUtil.checkObjectBlank(param.pi_id)) {
        //密码
        returnData.setReturnData(errorcode_param, " pi_id is null", null);
        sendResp(returnData, response);
        return null;
      }
      if (RequestUtil.checkObjectBlank(param.type)) {
        //密码
        returnData.setReturnData(errorcode_param, " type is null", null);
        sendResp(returnData, response);
        return null;
      }
      if (RequestUtil.checkObjectBlank(param.area_code)) {
        //省市县编号 140107
        returnData.setReturnData(errorcode_param, " area_code is null", null);
        sendResp(returnData, response);
        return null;
      }
      if (RequestUtil.checkObjectBlank(param.park_type)) {
        //park_type;//停车场类型 0：地下室停车场 1：露天停车场 2：露天立体车库停车场
        returnData.setReturnData(errorcode_param, "  park_type is null", null);
        sendResp(returnData, response);
        return null;
      }
      String sign_str = getSignature(Constants.SYSTEM_KEY, param.dtype, param.park_type,
          param.loginname, param.pi_id, param.area_code, param.mac, param.lat, param.lng);
      if (!param.sign.equalsIgnoreCase(sign_str)) {
        log.warn("sign=" + param.sign + "  sign_str=" + sign_str);
        returnData.setReturnData(errorcode_param, " sign is not right", null);
        sendResp(returnData, response);
        return null;
      }
      parkinfoBiz.pda_clock(returnData, param.lng, param.lat, param.loginname, param.pi_id,
          param.mac, param.park_type, param.area_code, param.type);

      sendResp(returnData, response);
      return null;

    } catch (Exception e) {
      log.error("Write_PDA_clockAction pda_clock  is error(DEVICE-JAVA)- P", e);
      returnData.setReturnData(errorcode_systerm, "system is error", "");
    }
    sendResp(returnData, response);
    return null;
  }

  /**
   * 露天停车场PDA 更新打卡记录对应的  清算状态
   */
  @RequestMapping(value = "/pda_clock_update")
  @ResponseBody
  public String pda_clock_update(HttpServletResponse response, Param_pda_clock param) {
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
      if (param.lng == null) {
        //lng; //地理经度
        returnData.setReturnData(errorcode_param, " lng is null", null);
        sendResp(returnData, response);
        return null;
      }
      if (param.lat == null) {
        //lat;//地理纬度
        returnData.setReturnData(errorcode_param, " lat is null", null);
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

      if (RequestUtil.checkObjectBlank(param.pi_id)) {
        //密码
        returnData.setReturnData(errorcode_param, " pi_id is null", null);
        sendResp(returnData, response);
        return null;
      }
      if (RequestUtil.checkObjectBlank(param.type)) {
        returnData.setReturnData(errorcode_param, " type is null", null);
        sendResp(returnData, response);
        return null;
      }
      if (RequestUtil.checkObjectBlank(param.area_code)) {
        //省市县编号 140107
        returnData.setReturnData(errorcode_param, " area_code is null", null);
        sendResp(returnData, response);
        return null;
      }
      if (RequestUtil.checkObjectBlank(param.park_type)) {
        //park_type;//停车场类型 0：地下室停车场 1：露天停车场 2：露天立体车库停车场
        returnData.setReturnData(errorcode_param, "  park_type is null", null);
        sendResp(returnData, response);
        return null;
      }
      if (RequestUtil.checkObjectBlank(param.ppc_id)) {
        returnData.setReturnData(errorcode_param, " ppc_id is null", null);
        sendResp(returnData, response);
        return null;
      }

      String sign_str = getSignature(Constants.SYSTEM_KEY, param.dtype, param.park_type,
          param.loginname, param.pi_id, param.area_code, param.mac, param.lat, param.lng,
          param.ppc_id);
      if (!param.sign.equalsIgnoreCase(sign_str)) {
        log.warn("sign=" + param.sign + "  sign_str=" + sign_str);
        returnData.setReturnData(errorcode_param, " sign is not right", null);
        sendResp(returnData, response);
        return null;
      }
      parkinfoBiz.pda_clock_update(returnData, param.lng, param.lat, param.loginname, param.pi_id,
          param.mac, param.park_type, param.area_code, param.type, param.ppc_id, param.ppc_nd);

      sendResp(returnData, response);
      return null;

    } catch (Exception e) {
      log.error("Write_PDA_clockAction pda_clock_update  is error(DEVICE-JAVA)- P", e);
      returnData.setReturnData(errorcode_systerm, "system is error", "");
    }
    sendResp(returnData, response);
    return null;
  }
}
