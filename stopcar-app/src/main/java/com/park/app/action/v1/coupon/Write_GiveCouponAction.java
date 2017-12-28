
package com.park.app.action.v1.coupon;

import com.park.app.action.v1.coupon.param.Param_GiveCoupon;
import com.park.app.service.CouponBiz;
import com.park.bean.ReturnDataNew;
import com.park.constants.Constants;
import com.park.mvc.action.v1.BaseV1Controller;
import com.park.util.RequestUtil;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 扫码赠送优惠券
 * 在现在吾泊APP优惠劵的基础上增加，优惠劵变成二维码，扫码赠送优惠劵的功能
 *
 * @author jingxiaohu 该次赠送请求 是有被赠送方 使用吾泊APP发起
 */
@Controller
@RequestMapping(value = "/v1")
public class Write_GiveCouponAction extends BaseV1Controller {

  @Autowired
  private CouponBiz couponBiz;
  /**
   * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
   */
  private static final long serialVersionUID = -3599663972160625262L;


  /**
   * 该次赠送请求 是有被赠送方 使用吾泊APP发起
   * 扫码赠送优惠券
   */
  @RequestMapping(value = "/give_coupon")
  @ResponseBody
  public String give_coupon(HttpServletResponse response, Param_GiveCoupon param) {
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
      if (RequestUtil.checkObjectBlank(param.from_ui_id)) {
        returnData.setReturnData(errorcode_param, " from_ui_id is null", null);
        sendResp(returnData, response);
        return null;
      }
      if (RequestUtil.checkObjectBlank(param.upc_id)) {
        returnData.setReturnData(errorcode_param, " upc_idd is null", null);
        sendResp(returnData, response);
        return null;
      }
      if (param.ui_id < 1) {
        returnData.setReturnData(errorcode_param, " ui_id is null", null);
        sendResp(returnData, response);
        return null;
      }

      String sign_str = getSignature(Constants.SYSTEM_KEY, param.dtype, param.ui_id, param.upc_id,
          param.from_ui_id);
      if (!param.sign.equalsIgnoreCase(sign_str)) {
        log.warn("sign=" + param.sign + "  sign_str=" + sign_str);
        returnData.setReturnData(errorcode_param, " sign is not right", null);
        sendResp(returnData, response);
        return null;
      }
      couponBiz.give_coupon(returnData, param.ui_id, param.upc_id, param.from_ui_id);

      sendResp(returnData, response);
      return null;

    } catch (Throwable e) {
      log.error("Write_GiveCouponAction give_coupon  is error(DEVICE-JAVA)- P", e);
      returnData.setReturnData(errorcode_systerm, "system is error", "");
    }
    sendResp(returnData, response);
    return null;
  }


}
