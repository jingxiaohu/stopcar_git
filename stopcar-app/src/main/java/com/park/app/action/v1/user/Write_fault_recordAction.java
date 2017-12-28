
package com.park.app.action.v1.user;

import com.park.app.service.AppParkinfoBiz;
import com.park.app.action.v1.user.param.Param_record_fault_record;
import com.park.bean.ReturnDataNew;
import com.park.constants.Constants;
import com.park.mvc.action.v1.BaseV1Controller;
import com.park.util.RequestUtil;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 故障上报记录
 *
 * @author jingxiaohu
 */
@Controller
@RequestMapping(value = "/v1")
public class Write_fault_recordAction extends BaseV1Controller {


  /**
   * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
   */
  private static final long serialVersionUID = -3599663972160625262L;
  @Autowired
  private AppParkinfoBiz parkinfoBiz;

  /**
   * 故障上报记录
   */
  @RequestMapping(value = "/record_fault_record")
  @ResponseBody
  public String record_fault_record(HttpServletResponse response, Param_record_fault_record param) {
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
      if (param.pi_id < 1) {
        //场地主键ID
        returnData.setReturnData(errorcode_param,
            " pi_id=" + param.pi_id + "  pi_id is smaller than zero", null);
        sendResp(returnData, response);
        return null;
      }

      if (param.pd_id < 1) {
        //pd_id;//出入口设备主键ID
        returnData.setReturnData(errorcode_param,
            " pd_id=" + param.pd_id + "  pd_id is smaller than zero", null);
        sendResp(returnData, response);
        return null;
      }
      if (RequestUtil.checkObjectBlank(param.fr_desc)) {
        //fr_desc;//故障简述
        returnData.setReturnData(errorcode_param, " fr_desc=" + param.fr_desc + "  fr_desc is null",
            null);
        sendResp(returnData, response);
        return null;
      } else {
        param.fr_desc = URLDecoder.decode(param.fr_desc, Constants.SYSTEM_CHARACTER);
      }
      if (!RequestUtil.checkObjectBlank(param.area_code)) {
        //省市县编号 140107
        //避免汉子的问题
        param.area_code = URLDecoder.decode(param.area_code, Constants.SYSTEM_CHARACTER);
      }
      String sign_str = getSignature(Constants.SYSTEM_KEY, param.dtype, param.pi_id, param.pd_id);
      if (!param.sign.equalsIgnoreCase(sign_str)) {
        log.warn("sign=" + param.sign + "  sign_str=" + sign_str);
        returnData.setReturnData(errorcode_param, " sign is not right", null);
        sendResp(returnData, response);
        return null;
      }
      parkinfoBiz
          .record_fault_record(returnData, param.pi_id, param.pd_id, param.fr_type, param.fr_desc,
              param.area_code);

      sendResp(returnData, response);
      return null;

    } catch (Exception e) {
      log.error("Write_fault_recordAction record_fault_record  is error(DEVICE-JAVA)- P", e);
      returnData.setReturnData(errorcode_systerm, "system is error", "");
    }
    sendResp(returnData, response);
    return null;
  }


}
