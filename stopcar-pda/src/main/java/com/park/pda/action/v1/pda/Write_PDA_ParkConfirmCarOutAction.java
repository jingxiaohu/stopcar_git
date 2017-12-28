package com.park.pda.action.v1.pda;

import com.park.bean.ReturnDataNew;
import com.park.constants.Constants;
import com.park.mvc.action.v1.BaseV1Controller;
import com.park.pda.action.v1.pda.param.Param_parkConfirmCarOut;
import com.park.pda.service.PDAParkConfirmCarOutBiz;
import com.park.util.RequestUtil;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * PDA确认地磁车位车辆已经出库
 */
@Deprecated
@Controller
@RequestMapping(value = "/v1")
public class Write_PDA_ParkConfirmCarOutAction extends BaseV1Controller {

  /**
   *
   */
  private static final long serialVersionUID = 1L;

  private Logger log = LoggerFactory.getLogger(this.getClass());

  @Autowired
  private PDAParkConfirmCarOutBiz pdaParkConfirmCarOutBiz;

  @RequestMapping(value = "/pda_parkconfirmcarout")
  @ResponseBody
  public String parkConfirmCarOut(HttpServletResponse response, Param_parkConfirmCarOut param) {
    ReturnDataNew returnData = new ReturnDataNew();
    try {
      // 参数检查
      if (!checkParam(param, returnData)) {
        sendResp(returnData, response);
        return null;
      }

      // 验证签名
      String signData = getSignature(Constants.SYSTEM_KEY, param.dtype, param.getMy_order());
      if (!param.sign.equalsIgnoreCase(signData)) {
        log.info(" req signData:" + signData + "\n current signData：" + param.sign);
        returnData.setReturnData(errorcode_param, "sign is not right", "");
        sendResp(returnData, response);
        return null;
      }

      pdaParkConfirmCarOutBiz.updatePdaMagneticState(returnData, param);

    } catch (Exception e) {
      log.error("异常-->", e);
      returnData.setReturnData(errorcode_systerm, "system is error", "");

    }
    sendResp(returnData, response);
    return null;
  }

  /**
   * 参数检查 ,检查通过返回true
   */
  private boolean checkParam(Param_parkConfirmCarOut param, ReturnDataNew returnData) {
    if (null == param) {
      returnData.setReturnData(errorcode_param, "参数传递错误", "");
      return false;
    }

    // 检查是否进行了参数签名认证
    if (!param.checkRequest()) {
      returnData.setReturnData(errorcode_param, "没有进行参数签名认证", "");
      return false;
    }

    if (RequestUtil.checkObjectBlank(param.getMy_order())) {
      returnData.setReturnData(errorcode_param, "my_order is null", "");
      return false;
    }

    if (RequestUtil.checkObjectBlank(param.getOrder_type())) {
      returnData.setReturnData(errorcode_param, "order_type is null", "");
      return false;
    }
    return true;
  }
}
