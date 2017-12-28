
package com.park.app.action.v1.pay;

import com.park.app.action.v1.pay.param.Param_lzf_charge;
import com.park.app.service.AppUserPayBiz;
import com.park.bean.ReturnDataNew;
import com.park.constants.Constants;
import com.park.mvc.action.v1.BaseV1Controller;
import com.park.util.RequestUtil;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 建设银行龙支付 -- 用户充值
 *
 * @author jingxiaohu
 */
@Controller
@RequestMapping(value = "/v1")
public class LZF_PayAction extends BaseV1Controller {

  /**
   *
   */
  private static final long serialVersionUID = -4402176190279046970L;
  //	private String notify="http://app.ie-colorful.com/v1/notify_lzf.php";
  private final String pub = "30819d300d06092a864886f70d010101050003818b0030818702818100cd0b7cdab739d49af0ee8bf88f5bcfc8432d8c6818f0821d34e74bf52081977ec2a30cddba61b84fda72b6ec883283a14431410e7ff90449bd6e8fca88d3828a83d47a600e1e33881bcde003a65f9547acb998a0d971c4006e6c25222aed818d11b861381b7f80f8dc6d81303c25a8f17d35f2559a0802790f2b22c3dc5026c3020111";

  @Autowired
  private AppUserPayBiz appUserPayBiz;



  @RequestMapping(value = "/lzf_charge")
  @ResponseBody
  public String lzf_charge(HttpServletRequest request, HttpServletResponse response,
      Param_lzf_charge param) {

    ReturnDataNew returnData = new ReturnDataNew();
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
    if (param.pay_price < 1 && param.escape_money == 0) {
      //pay_price必须大于0
      returnData.setReturnData(errorcode_param, "pay_price必须大于0", "");
      sendResp(returnData, response);
      return null;
    }
    if (RequestUtil.checkObjectBlank(param.token)) {
      //token不能为空
      returnData.setReturnData(errorcode_param, "token不能为空", "");
      sendResp(returnData, response);
      return null;
    }
    //MD5验证
    if (param.sign != null) {
      String sign_str = getSignature(Constants.SYSTEM_KEY, param.dtype, param.ui_id, param.pay_type,
          param.pay_price, param.t, param.token);
      //MD5散列(pay_type+uid+pay_price+t+token+app_key）
      if (!param.sign.equalsIgnoreCase(sign_str)) {
        log.warn("sign=" + param.sign + "  sign_str=" + sign_str);
        returnData.setReturnData(errorcode_param, " sign is not right", null);
        sendResp(returnData, response);
        return null;
      }
    }
    try {
      String ip = "";
//			String ip = getIpAddr(request);
//			if(ip == null || "".equals(ip) || ip.startsWith("192.168")){
//				return null;
//			}
      //元 转变成分
      int pay_price_fen = param.pay_price;
      appUserPayBiz.lzf_charge(returnData, param.pay_type, param.ui_id, pay_price_fen, param.version,
          param.system_type,
          param.subject, ip, param.token, pub.substring(pub.length() - 30, pub.length()),
          param.type, param.orderid, param.escape_money, param.escape_orderids, getLZFNotify_url());
      sendResp(returnData, response);
      return null;

    } catch (Exception e) {
      log.error("LZF_PayAction.lzf_charge is error 2.6	用户充值（GAMESDK-JAVA）- P", e);
      returnData.setReturnData("1", "-1", "");
    }
    sendResp(returnData, response);
    return null;
  }
}
