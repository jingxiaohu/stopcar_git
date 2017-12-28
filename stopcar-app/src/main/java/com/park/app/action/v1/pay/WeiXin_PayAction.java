
package com.park.app.action.v1.pay;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.park.app.action.v1.pay.param.Param_wx_charge;
import com.park.app.service.AppUserPayBiz;
import com.park.bean.ReturnDataNew;
import com.park.constants.Constants;
import com.park.mvc.action.v1.BaseV1Controller;
import com.park.mvc.action.v1.notify.Notify_WeiXinction;
import com.park.util.MD5;
import com.park.util.RequestUtil;
import com.park.util.XMLBeanUtils;
import com.weixin.config.HttpTool;
import com.weixin.config.WeiXinBean;
import com.weixin.config.WeixinPayConstants;
import com.weixin.config.WeixinResult;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 微信 -- 用户充值
 *
 * @author jingxiaohu
 */
@Controller
@RequestMapping(value = "/v1")
public class WeiXin_PayAction extends BaseV1Controller {

  final Logger log = LoggerFactory.getLogger(WeiXin_PayAction.class);

  /**
   *
   */
  private static final SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
  private static final long serialVersionUID = -4402176190279046970L;
  @Autowired
  private AppUserPayBiz appUserPayBiz;
  //商户相关资料
  private static final String appid = WeixinPayConstants.appid;
  private static final String appsecret = WeixinPayConstants.appsecret;

  public String getNotify_url() throws NoSuchMethodException {
    return ControllerLinkBuilder.linkTo(Notify_WeiXinction.class,
        Notify_WeiXinction.class.getMethod("notify_weixin", HttpServletRequest.class, HttpServletResponse.class)).withSelfRel().getHref()+ ".php";
  }

  @RequestMapping(value = "/weixin_charge")
  @ResponseBody
  public String weixin_charge(HttpServletRequest request, HttpServletResponse response,
      Param_wx_charge param) {

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
    //非空验证
    if (System.currentTimeMillis() - param.t > 3 * 60 * 1000) {
      //如果请求超出120秒则认为是 废弃请求
      returnData.setReturnData(errorcode_param, "是 废弃请求", "");
      sendResp(returnData, response);
      return null;
    }
    if (param.type < 1) {
      //type必须大于0
      returnData.setReturnData(errorcode_param, "type必须大于0", "");
      sendResp(returnData, response);
      return null;
    }
    if (param.pay_price < 1) {
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
          param.pay_price,
          param.t, param.token);
      //MD5散列(pay_type+uid+pay_price+t+token+app_key）
      if (!param.sign.equalsIgnoreCase(sign_str)) {
        log.warn("sign=" + param.sign + "  sign_str=" + sign_str);
        returnData.setReturnData(errorcode_param, " sign is not right", null);
        sendResp(returnData, response);
        return null;
      }
    }
    try {
      String ip = getIpAddr(request);
      if (ip.contains(",")) {
        ip = ip.split(",")[0].trim();
      }
//      if (ip == null || "".equals(ip) || ip.startsWith("192.168")) {
//        return null;
//      }
      if (param.type != 1) {
        param.subject = "吾泊微信支付";
      } else {
        param.subject = "吾泊微信充值";
      }
      //元 转变成分
      int pay_price_fen = param.pay_price;
      appUserPayBiz
          .weixin_charge(returnData, param.pay_type, param.ui_id, pay_price_fen, param.version,
              param.system_type, param.subject, ip, param.token, param.type, param.orderid,
              0, "");
      /*JSONObject obj_2 = new JSONObject();
      obj_2.put("order_id", RandomStringUtils.random(32, true, true));
			returnData.setReturnData("0", "", obj_2);*/
      if (returnData != null && "0".equalsIgnoreCase(returnData.getErrorno())) {
        JSONObject obj = (JSONObject) JSONObject.toJSON(returnData.getData());
        String out_trade_no = obj.getString("order_id");
        WeiXinBean wxbean = new WeiXinBean();
        wxbean.setAppid(appid);
        wxbean.setAttach(param.type + "");
        //type;//是支付 还是 充值  1：充值  2：普通订单支付  3：租赁订单支付
        wxbean.setBody(param.subject);
        String partner = WeixinPayConstants.partner;
        wxbean.setMch_id(partner);
        wxbean.setNonce_str(RandomStringUtils.random(32, true, true));
        wxbean.setNotify_url(getNotify_url());
        wxbean.setOut_trade_no(out_trade_no);
        wxbean.setSpbill_create_ip(ip);
        wxbean.setTotal_fee(pay_price_fen);
        wxbean.setTrade_type("APP");
        //wxbean.setSign_type("MD5");

        Map<String, String> packageParams = buildOrderParamMap(wxbean);
        String sign = getSign(packageParams, appsecret);
        //设置SIGN
        wxbean.setSign(sign);
        //向微信服务器公共下单接口发送请求
        Map<String, Class<?>> cm = new HashMap<String, Class<?>>();
        cm.put("xml", WeiXinBean.class);
        String result = HttpTool
            .sendPost2(WeixinPayConstants.createOrderURL, XMLBeanUtils.bean2xml(cm, wxbean));
//				System.out.println(result);
        //Document document = DocumentHelper.parseText(result);
        cm = new HashMap<String, Class<?>>();
        cm.put("xml", WeixinResult.class);
        WeixinResult wxR = (WeixinResult) XMLBeanUtils.xml2Bean(cm, result);
        if (wxR != null) {
          if ("FAIL".equalsIgnoreCase(wxR.getReturn_code())) {
            log.info(JSON.toJSONString(wxR, false));
            returnData.setReturnData(errorcode_data, "微信充值失败", "");
            sendResp(returnData, response);
            return null;
          }
          //返回给客户端
          obj.put("timestamp", sf.format(new Date()));
          Map<String, String> payInfo = getPayInfo(wxR);
          obj.put("orderInfo", JSONObject.toJSON(payInfo));
          obj.put("sign", "");
          returnData.setReturnData(errorcode_success, "微信充值成功", obj);
          sendResp(returnData, response);
          return null;
        }
        log.info(JSON.toJSONString(wxR, false));
      }
      log.info(JSON.toJSONString(returnData, false));
      returnData.setReturnData(errorcode_data, "微信充值失败", "");
      sendResp(returnData, response);
      return null;
    } catch (Exception e) {
      log.error("WeiXin_PayAction.weixin_charge is error 用户微信充值", e);
      returnData.setReturnData(errorcode_systerm, "systerm is error", "");
    }
    sendResp(returnData, response);
    return null;
  }

  private Map<String, String> getPayInfo(WeixinResult weixinResult) {
    Map<String, String> result = new HashMap<>();
    result.put("appid", weixinResult.getAppid());
    result.put("partnerid", weixinResult.getMch_id());
    result.put("prepayid", weixinResult.getPrepay_id());
    result.put("package", "Sign=WXPay");
    result.put("noncestr", RandomStringUtils.random(32, true, true));
    result.put("timestamp", String.valueOf(System.currentTimeMillis() / 1000));

    result.put("sign", getSign(result, appsecret));
    return result;
  }

  /**
   * 构造支付订单参数列表
   */
  public static Map<String, String> buildOrderParamMap(WeiXinBean wxbean) {
    Map<String, String> packageParams = new HashMap<String, String>();
    packageParams.put("appid", appid);
    packageParams.put("mch_id", wxbean.getMch_id());
    packageParams.put("attach", wxbean.getAttach());
    packageParams.put("body", wxbean.getBody());
    packageParams.put("nonce_str", wxbean.getNonce_str());
    packageParams.put("out_trade_no", wxbean.getOut_trade_no());
    packageParams.put("total_fee", wxbean.getTotal_fee() + "");
    packageParams.put("spbill_create_ip", wxbean.getSpbill_create_ip());
    packageParams.put("notify_url", wxbean.getNotify_url());
    packageParams.put("trade_type", wxbean.getTrade_type());
    return packageParams;
  }

  /**
   * 构造支付订单参数信息
   *
   * @param map 支付订单参数
   */
  public static String buildOrderParam(Map<String, String> map, String sign) {
    List<String> keys = new ArrayList<String>(map.keySet());
    Collections.sort(keys);
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < keys.size() - 1; i++) {
      String key = keys.get(i);
      String value = map.get(key);
      sb.append(buildKeyValue(key, value, false));
      sb.append("&");
    }

    String tailKey = keys.get(keys.size() - 1);
    String tailValue = map.get(tailKey);
    sb.append(buildKeyValue(tailKey, tailValue, false));

    return sb.toString() + "&sign=" + sign;
  }

  /**
   * 拼接键值对
   */
  private static String buildKeyValue(String key, String value, boolean isEncode) {
    StringBuilder sb = new StringBuilder();
    sb.append(key);
    sb.append("=");
    if (isEncode) {
      try {
        sb.append(URLEncoder.encode(value, "UTF-8"));
      } catch (UnsupportedEncodingException e) {
        sb.append(value);
      }
    } else {
      sb.append(value);
    }
    return sb.toString();
  }

  /**
   * 对支付参数信息进行签名
   *
   * @param map 待签名授权信息
   */
  public String getSign(Map<String, String> map, String MD5Key) {
    try {
      List<String> keys = new ArrayList<String>(map.keySet());
      // key排序
      Collections.sort(keys);

      StringBuilder authInfo = new StringBuilder();
      for (int i = 0; i < keys.size() - 1; i++) {
        String key = keys.get(i);
        String value = map.get(key);
        authInfo.append(buildKeyValue(key, value, false));
        authInfo.append("&");
      }

      String tailKey = keys.get(keys.size() - 1);
      String tailValue = map.get(tailKey);
      authInfo.append(buildKeyValue(tailKey, tailValue, false));
      String str = authInfo.toString() + "&key=" + MD5Key;
      log.info(str);
//		return DigestUtils.md5Hex(str.getBytes("UTF-8")).toUpperCase();
      return MD5.getMessageDigest(str.getBytes("UTF-8")).toUpperCase();
    } catch (Exception e) {
      // TODO Auto-generated catch block
      log.error("签名编码异常", e);
    }
    return "";
  }

//  public static void main(String[] args) throws UnsupportedEncodingException {
//	String str = "appid=wxa8c52369cd7047b5&attach=1&body=吾泊微信充值&mch_id=1426571202&nonce_str=LbaDvCBqjZHyqac9kVa53ly9DNaccdmm&notify_url=http://223.85.163.38:8091/v1/notify_weixin.php&out_trade_no=2017030711399128&spbill_create_ip=127.0.0.1&total_fee=1&trade_type=APP&key=BcjkVKJYuI4tHQsc44T9eKOd5jGOj6B6";
//	System.out.println(DigestUtils.md5Hex(str));
//	System.out.println(DigestUtils.md5Hex(str.getBytes("UTF-8")).toUpperCase());
//  }
}
