
package com.park.pda.action.v1.pda;

import com.park.bean.ReturnDataNew;
import com.park.constants.Constants;
import com.park.mvc.action.v1.BaseV1Controller;
import com.park.pda.action.v1.pda.param.Param_pda_sure;
import com.park.pda.service.PDAOrderBiz;
import com.park.util.RequestUtil;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

/**
 * 露天停车场的PDA更新用户自动支付
 *
 * @author jingxiaohu
 */
@Controller
@RequestMapping(value = "/v1")
public class Write_PDA_sureOutParkAction extends BaseV1Controller {

  /**
   * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
   */
  private static final long serialVersionUID = -3599663972160625262L;

  @Autowired
  protected PDAOrderBiz orderBiz;

  /**
   * 露天停车场的PDA更新用户自动支付
   * 更新之前先检验订单是否存在，用户是否存在，用户是否开启自动扣款，再根据支付类型进行支付处理。
   * PDA入库/出库检测到用户车辆有欠费信息时调用此接口。
   * PDA出库选择支付方式为现金/钱包时调用此接口，选择龙支付/支付宝/微信支付时先轮询相应支付接口，最后在调用此接口更新订单支付信息。
   */
  @RequestMapping(value = "/pda_sure")
  @ResponseBody
  public String pda_sure(HttpServletResponse response, Param_pda_sure param) {
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

      if (param.getPay_type() == 7) {
        if (param.getFace() == null) {
          returnData.setReturnData(errorcode_param, " 扫脸支付图片不能为空", null);
          sendResp(returnData, response);
          return null;
        } else {
          MultipartFile face = param.getFace();
          String faceFileName = param.getFaceFileName();
          if (!StringUtils.hasText(faceFileName)) {
            faceFileName = face.getOriginalFilename();
            param.setFaceFileName(faceFileName);
          }
        }
      }

      String sign_str = getSignature(Constants.SYSTEM_KEY, param.dtype, param.orderid, param.pi_id);
      if (!param.sign.equalsIgnoreCase(sign_str)) {
        log.warn("sign=" + param.sign + "  sign_str=" + sign_str);
        returnData.setReturnData(errorcode_param, " sign is not right", null);
        sendResp(returnData, response);
        return null;
      }
      orderBiz.pda_sure(returnData, param.dtype, param.orderid, param.pay_type, param.pi_id,
          param.area_code, param.money, param.escape_orderids,
          is_sync, param.getSync_time(), param.type, param.getFace(), param.getFaceFileName(),param.fingerprint,param.finger_veno);

      sendResp(returnData, response);
      return null;

    } catch (Exception e) {
      log.error("Write_PDAsureOutParkAction pda_sure  is error(DEVICE-JAVA)- P", e);
      returnData.setReturnData(errorcode_systerm, "system is error", "");
    }
    sendResp(returnData, response);
    return null;
  }


}
