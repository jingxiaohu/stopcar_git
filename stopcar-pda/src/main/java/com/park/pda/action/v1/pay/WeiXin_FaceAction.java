
package com.park.pda.action.v1.pay;

import com.alibaba.fastjson.JSONObject;
import com.park.bean.ReturnDataNew;
import com.park.bean.User_pay;
import com.park.constants.Constants;
import com.park.mvc.action.v1.BaseV1Controller;
import com.park.mvc.action.v1.notify.Notify_WeiXinction;
import com.park.pda.action.v1.pay.param.Param_zfb_charge_face;
import com.park.pda.service.PDAUserPayBiz;
import com.park.util.RequestUtil;
import com.park.util.XMLBeanUtils;
import com.weixin.config.HttpTool;
import com.weixin.config.WeixinPayConstants;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.RandomStringUtils;
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
public class WeiXin_FaceAction extends BaseV1Controller {

  /**
   *
   */
  private static SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
  private static final long serialVersionUID = -4402176190279046970L;

  //商户相关资料
  private static String appid = WeixinPayConstants.appid;
  private static String appsecret = WeixinPayConstants.appsecret;
  private static String partner = WeixinPayConstants.partner;

  @Autowired
  private PDAUserPayBiz userPayBiz;

  private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");

  public String getNotify_url() throws NoSuchMethodException {
    return ControllerLinkBuilder.linkTo(Notify_WeiXinction.class,
        Notify_WeiXinction.class.getMethod("notify_weixin", HttpServletRequest.class, HttpServletResponse.class)).withSelfRel().getHref()+ ".php";
  }

  @RequestMapping(value = "/weixin_charge_face")
  @ResponseBody
  public String weixin_charge_face(HttpServletRequest request, HttpServletResponse response,
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
      String ip = getIpAddr(request);
      if (ip.contains(",")) {
        ip = ip.split(",")[0].trim();
      }
//      if (ip == null || "".equals(ip) || ip.startsWith("192.168")) {
//        return null;
//      }
      //元 转变成分
      int pay_price_fen = param.pay_price;
      param.subject = "吾泊扫码支付";
      User_pay user_pay = userPayBiz
          .weixin_charge(returnData, param.pay_type, param.ui_id, pay_price_fen, param.version,
              param.system_type, param.subject, ip, param.token, param.type, param.orderid,
              param.escape_money, param.escape_orderids);

      if (returnData != null && "0".equalsIgnoreCase(returnData.getErrorno())) {
        long money = user_pay.getMoney();
        JSONObject obj = (JSONObject) JSONObject.toJSON(returnData.getData());
        if (money == 0) {
          returnData.setReturnData(errorcode_success, "微信充值成功", obj);
          sendResp(returnData, response);
          return null;
        }
        String out_trade_no = obj.getString("order_id");
        Map<String, String> params = new HashMap<String, String>();
        params.put("appid", appid);
        params.put("mch_id", partner);
        params.put("attach", String.valueOf(param.type));
        params.put("body", param.subject);
        params.put("nonce_str", RandomStringUtils.random(32, true, true));
        params.put("out_trade_no", out_trade_no);

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MINUTE, 5);
        params.put("time_expire", dateFormat.format(calendar.getTime()));
        params.put("total_fee", String.valueOf(money));
        params.put("spbill_create_ip", ip);
        params.put("notify_url", getNotify_url());
        params.put("trade_type", "NATIVE");
        params.put("product_id", out_trade_no);
        String sign = getSign(params);
        //设置SIGN
        params.put("sign", sign);
        //向微信服务器公共下单接口发送请求
        String resultStr = HttpTool
            .sendPost(WeixinPayConstants.createOrderURL, XMLBeanUtils.map2xml(params));
        if (log.isDebugEnabled()) {
          log.debug("微信下单返回结果：" + resultStr);
        }
        Map<String, String> result = XMLBeanUtils.xml2Map(resultStr);
        if (getSign(result).equals(result.get("sign")) && "SUCCESS"
            .equals(result.get("return_code")) && "SUCCESS".equals(result.get("result_code"))) {
          obj.put("orderInfo", result.get("code_url"));
          obj.put("sign", "");
          obj.put("timestamp", sf.format(new Date()));
          returnData.setReturnData(errorcode_success, "微信充值成功", obj);
          sendResp(returnData, response);
          return null;
        }
      }
      sendResp(returnData, response);
      return null;

    } catch (Throwable e) {
      log.error("ZFB_Pay_faceAction.zfb_charge_face is error 用户微信充值", e);
      returnData.setReturnData("1", "-1", "");
    }
    sendResp(returnData, response);
    return null;
  }


  /**
   * 对支付参数信息进行签名
   *
   * @param params 待签名授权信息
   */
  public String getSign(Map<String, String> params) {
    //获取待签名字符串
    List<String> keys = new ArrayList<>(params.keySet());
    Collections.sort(keys);

    String prestr = "";

    for (int i = 0; i < keys.size(); i++) {
      String key = keys.get(i);
      String value = params.get(key);
      if (value == null || value.equals("") || key.equalsIgnoreCase("sign")) {
        continue;
      }
      if (i == keys.size() - 1) {//拼接时，不包括最后一个&字符
        prestr = prestr + key + "=" + value;
      } else {
        prestr = prestr + key + "=" + value + "&";
      }
    }
    //获得签名验证结果
    String stringSignTemp = prestr + "&key=" + appsecret;
    return org.springframework.util.DigestUtils.md5DigestAsHex(stringSignTemp.getBytes())
        .toUpperCase();
  }

}
