
package com.park.pda.action.v1.pay;

import com.alibaba.fastjson.JSONObject;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayOpenPublicInfoModifyRequest;
import com.alipay.util.SignUtils;
import com.alipay.vo.AlipayAppBean;
import com.park.bean.ReturnDataNew;
import com.park.constants.Constants;
import com.park.mvc.action.v1.BaseV1Controller;
import com.park.pda.action.v1.pay.param.Param_zfb_charge_face;
import com.park.pda.service.PDAUserPayBiz;
import com.park.util.RequestUtil;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 支付宝 -- 用户充值
 *
 * @author jingxiaohu
 */
@Controller
@RequestMapping(value = "/v1")
public class Test_ZFB_Pay_myselfAction extends BaseV1Controller {

  /**
   *
   */
  private static SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
  private static final long serialVersionUID = -4402176190279046970L;
  //	private String notify="http://app.ie-colorful.com/v1/notify_lzf.php";
  private static String APP_ID = "2016112103042572";
  private static String seller_id = "2088421595009690";
  private static String ALIPAY_PUBLIC_KEY = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDEhzyauJHud3EgMJICw6eEnW4nx2aywzdaYL5l+iGZRyX8rgPoCDQNfMoXL6TombDpay6MrsHROf03myoKFSuB2ygHpE2Y7H00rbYSzKWi4uojgXWf6ZrXhFnkO7oBaxUvjwrRp0l+pX46uRFQJLGkx59U1r1CaTqYolYSkzFEJQIDAQAB";
  private static String APP_PRIVATE_KEY = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAMSHPJq4ke53cSAwkgLDp4SdbifHZrLDN1pgvmX6IZlHJfyuA+gINA18yhcvpOiZsOlrLoyuwdE5/TebKgoVK4HbKAekTZjsfTStthLMpaLi6iOBdZ/pmteEWeQ7ugFrFS+PCtGnSX6lfjq5EVAksaTHn1TWvUJpOpiiVhKTMUQlAgMBAAECgYAQUncLFpcwKgVgOgh2aE+KaRTUAvCZmjMHs488oviVZV5XTRCP/dZs5FdYc6GLm/AggsFb98urF9ja/G0SZ020ltPKAUL9ETftu8ysf7EDQxsLbavfuHy270WHyzDgy9Waw3rTwjTdPuPpenutlCSJqcotZx4a7VgtBv3mEnj9oQJBAPb0r/nmJ+5Fzgm6ko4CFycaCIossWqXt5JeWgPo3eZrsA/IqQTf5OTDNBQEYygtiuET3Ffgxnib4Z+SG7LOZAkCQQDLucUz7bRtrz40fs2ApiKD+G83GCPcikcHvKdkOz7bm2g2PFDAe/gcxDogjS527+jgfC0Kel8WbfipK9tkT349AkA2svPXcjcd+7ArT3vuoF/odUe28zdI2Nn8PZHKk+Wyh9+zX0qwnbbhRKtgU6hy2cONHw0LGepcBIrxATfJXxWhAkEAjgO5AYMBlLhln4iJTtYBF4f2VyyfyxwlebI76fYW0lWaJryS+isxATSU5J4mNsj0yJAngbdeU69jeOJWtK1pbQJBANhlXbvv+8JJPF4lkhfvCL3S5Nitwhuh8T/hYt2B/Ry7Ll5eReH1ryNKni3yzCyNncN/a6z7M4ENN3WUrvYB/FM=";
  @Autowired
  private PDAUserPayBiz pdaUserPayBiz;

  @RequestMapping(value = "/zfb_charge_myself")
  @ResponseBody
  public String zfb_charge_myself(HttpServletRequest request, HttpServletResponse response,
      Param_zfb_charge_face param) {

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
      String ip = getIpAddr(request);
//      if (ip == null || "".equals(ip) || ip.startsWith("192.168")) {
//        return null;
//      }
      //元 转变成分
      int pay_price_fen = param.pay_price;
      param.subject = "吾泊扫码支付";
      pdaUserPayBiz
          .zfb_charge(returnData, param.pay_type, param.ui_id, pay_price_fen, param.version,
              param.system_type, param.subject, ip, param.token, param.type, param.orderid,
              param.escape_money, param.escape_orderids);

      if (returnData != null && "0".equalsIgnoreCase(returnData.getErrorno())) {
        JSONObject obj = (JSONObject) JSONObject.toJSON(returnData.getData());
        String out_trade_no = obj.getString("order_id");
        //实例化客户端
        AlipayClient alipayClient = new DefaultAlipayClient("https://openapi.alipay.com/gateway.do",
            APP_ID, APP_PRIVATE_KEY, "json", "UTF-8", ALIPAY_PUBLIC_KEY, "RSA2");
        //实例化具体API对应的request类,类名称和接口名称对应,当前调用接口名称：alipay.open.public.template.message.industry.modify
        AlipayOpenPublicInfoModifyRequest request2 = new AlipayOpenPublicInfoModifyRequest();
        //SDK已经封装掉了公共参数，这里只需要传入业务参数
        //此次只是参数展示，未进行字符串转义，实际情况下请转义
        /*request2.setBizContent("  {" +
				"    \"primary_industry_name\":\"IT科技/IT软件与服务\"," +
				"    \"primary_industry_code\":\"10001/20102\"," +
				"    \"secondary_industry_code\":\"10001/20102\"," +
				"    \"secondary_industry_name\":\"IT科技/IT软件与服务\"" +
				" }");*/
				/*AlipayObject bean = new AlipayAppBean();
				bean.setBody(body);
				bean.setOut_trade_no(out_trade_no);
				bean.setProduct_code(product_code);
				bean.setSeller_id(out_trade_no);
				bean.setSubject(subject);
				bean.setTimeout_express(timeout_express);
				bean.setTotal_amount(total_amount);
				bean.setType(type);
				request2.setBizModel(bean);*/
//				AlipayOpenPublicTemplateMessageIndustryModifyResponse response2 = alipayClient.execute(request2); 
        //调用成功，则处理业务逻辑
//				if(response2.isSuccess()){
//				    //.....
//					String str = response2.getBody();
//					System.out.println(str);
//				}
				/*String qr_img = AlipayFace.trade_precreate(out_trade_no, param.subject, (pay_price_fen*1.0)/100+"", param.type+"", notify_url);
				String timestamp =sf.format(new Date()); 
				obj.put("orderInfo", qr_img == null ? "":qr_img);
				obj.put("sign", "");
				obj.put("timestamp", timestamp);*/
        returnData.setData(obj);
        sendResp(returnData, response);
        return null;
      }
      returnData.setReturnData(errorcode_data, "支付失败", "");
      sendResp(returnData, response);
      return null;

    } catch (Exception e) {
      log.error("ZFB_Pay_faceAction.zfb_charge_face is error 用户支付宝充值", e);
      returnData.setReturnData("1", "-1", "");
    }
    sendResp(returnData, response);
    return null;
  }

  /**
   * 构造支付订单参数列表
   */
  public static Map<String, String> buildOrderParamMap(String app_id, AlipayAppBean appbean,
      String timestamp, String notify_url, int type, int system_type) {
    Map<String, String> keyValues = new HashMap<String, String>();

    keyValues.put("app_id", app_id);

    keyValues.put("biz_content", JSONObject.toJSONString(appbean));

    keyValues.put("charset", "utf-8");
    if (system_type == 4) {
      keyValues.put("method", "alipay.trade.precreate");
    } else {
      keyValues.put("method", "alipay.trade.app.pay");
    }

    keyValues.put("sign_type", "RSA");

    keyValues.put("timestamp", timestamp);

    keyValues.put("version", "1.0");

    keyValues.put("notify_url", notify_url);
    //设置透传参数
    keyValues.put("passback_params", type + "");
    return keyValues;
  }

  /**
   * 构造支付订单参数信息
   *
   * @param map 支付订单参数
   */
  public static String buildOrderParam(Map<String, String> map) {
    List<String> keys = new ArrayList<String>(map.keySet());
    Collections.sort(keys);
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < keys.size() - 1; i++) {
      String key = keys.get(i);
      String value = map.get(key);
      sb.append(buildKeyValue(key, value, true));
      sb.append("&");
    }

    String tailKey = keys.get(keys.size() - 1);
    String tailValue = map.get(tailKey);
    sb.append(buildKeyValue(tailKey, tailValue, true));

    return sb.toString();
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
  public static String getSign(Map<String, String> map, String rsaKey) {
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

    String oriSign = SignUtils.sign(authInfo.toString(), rsaKey);
    String encodedSign = "";

    try {
      encodedSign = URLEncoder.encode(oriSign, "UTF-8");
    } catch (UnsupportedEncodingException e) {
      e.printStackTrace();
    }
    return "sign=" + encodedSign;
  }
	/*public static void main(String[] args) throws Exception {
		Map<String, String> params = new HashMap<String,String>();
		params.put("a", "123");
		String sign = AlipaySignature.rsaSign(params, APP_PRIVATE_KEY, "UTF-8");
		params.put("sign", sign);
		System.out.println(AlipaySignature.rsaCheckV2(params, APP_PUBLIC_KEY, "UTF-8"));
	}*/
}
