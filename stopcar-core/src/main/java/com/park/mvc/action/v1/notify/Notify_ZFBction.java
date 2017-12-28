
package com.park.mvc.action.v1.notify;

import com.alipay.api.internal.util.AlipaySignature;
import com.park.bean.ReturnDataNew;
import com.park.mvc.service.UserPayBiz;
import com.park.mvc.action.v1.BaseV1Controller;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 支付宝通知监听接口
 *
 * @author jingxiaohu
 */
@Controller
@RequestMapping(value = "/v1")
public class Notify_ZFBction extends BaseV1Controller {

  private static String ALIPAY_PUBLIC_KEY = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDDI6d306Q8fIfCOaTXyiUeJHkrIvYISRcc73s3vF1ZT7XN8RNPwJxo8pWaJMmvyTn9N4HQ632qJBVHf8sxHi/fEsraprwCtzvzQETrNRwVxLO5jVmRGi60j8Ue1efIlzPXV9je9mkjzOmdssymZkh2QhUrCmZYI/FCEa3/cNMW0QIDAQAB";
  /**
   * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
   */
  private static final long serialVersionUID = -3599663972160625262L;

  @Autowired
  private UserPayBiz userPayBiz;

  /**
   * 支付宝通知监听接口
   *
   * @return notify_zfb params={subject=吾泊停车, sign_type=RSA, buyer_logon_id=251***@qq.com,
   * auth_app_id=2016112103042572, notify_type=trade_status_sync, out_trade_no=2016112513288860,
   * version=1.0, point_amount=0.00, passback_params=1, fund_bill_list=[{"amount":"0.01","fundChannel":"ALIPAYACCOUNT"}],
   * buyer_id=2088902576563951, total_amount=0.01, trade_no=2016112521001004950262555095,
   * notify_time=2016-11-25 13:28:29, charset=utf-8, invoice_amount=0.01,
   * trade_status=TRADE_SUCCESS, gmt_payment=2016-11-25 13:28:29, sign=E+DTZvspaLyAc+NoOCsO/A7AHM2EfT4qyiPrMcMhiXD+Ht2bRNj5p6RW9ZyK1UAdbfRAIWOBcaLyhCCgWIt1FSQ3/YnDkDsbquUSuh8sUzlPnFd6APw5Sm4pHUOze1ixWxaAMvFKPSHxtpGOvRYg1xQZPoshL5s9RhddzaGzuvk=,
   * gmt_create=2016-11-25 13:28:28, buyer_pay_amount=0.01, receipt_amount=0.01,
   * app_id=2016112103042572, seller_id=2088421595009690, seller_email=scqckj2@126.com,
   * notify_id=ebfb7f30310128df4da36e99d8638c1nby}
   */
  @RequestMapping(value = "/notify_zfb")
  @ResponseBody
  public String notify_zfb(HttpServletRequest request, HttpServletResponse response) {
    ReturnDataNew returnData = new ReturnDataNew();
    //非空验证
    try {
      //获取支付宝POST过来反馈信息
      Map<String, String> params = new HashMap<String, String>();
      Map requestParams = request.getParameterMap();
      for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext(); ) {
        String name = (String) iter.next();
        String[] values = (String[]) requestParams.get(name);
        String valueStr = "";
        for (int i = 0; i < values.length; i++) {
          valueStr = (i == values.length - 1) ? valueStr + values[i]
              : valueStr + values[i] + ",";
        }
        //乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
        //valueStr = new String(valueStr.getBytes("ISO-8859-1"), "gbk");
        params.put(name, valueStr);
      }
      if (params != null) {
        log.info("notify_zfb params=" + params.toString());
      }
      //验签方法（例如异步通知的时候，用户需要用到验签方法）
      /**
       @param params 参数列表(包括待验签参数和签名值sign) key-参数名称 value-参数值
       @param publicKey 验签公钥
       @param charset 验签字符集
       **/
      if (AlipaySignature.rsaCheckV1(params, ALIPAY_PUBLIC_KEY, "UTF-8")) {
        //验签成功
        log.info("验签成功-----------");
        //验证成功  更新该订单的支付状态 并把对应的金额添加给用户
        String orderid = params.get("out_trade_no");
        String trade_no = params.get("trade_no");
        String type = "0";
        if (params.containsKey("passback_params")) {
          type = params.get("passback_params");
        } else {
          type = params.get("body");
        }
        if (params.containsKey("trade_status")) {
          if (!"TRADE_SUCCESS".equalsIgnoreCase(params.get("trade_status"))) {
            sendRespHtml("failure", response);
            return null;
          }
        }
        //金额
        BigDecimal PAYMENT = new BigDecimal(params.get("total_amount"));
        long money = PAYMENT.multiply(new BigDecimal(100)).longValue();
        try {
          userPayBiz.notify_zfb(returnData, orderid, trade_no, type, money);
          if (returnData != null && "0".equalsIgnoreCase(returnData.getErrorno())) {
            sendRespHtml(SUCCESS, response);
            return null;
          }
        } catch (Exception e) {
          // TODO Auto-generated catch block
          log.error("更新该订单的支付状态 并把对应的金额添加给用户 失败", e);
        }
        sendRespHtml("failure", response);
        return null;
      }
      log.info("验签失败------error-----");
      sendRespHtml("failure", response);
      return null;
    } catch (Exception e) {
      log.error("Notify_ZFBction.notify_zfb is error 支付宝通知监听接口（GAMESDK-JAVA）- P", e);
      sendRespHtml("failure", response);
      return null;
    }
  }

/********************接收参数区*************************/

}
