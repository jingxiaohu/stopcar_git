
package com.park.mvc.action.v1.notify;

import CCBSign.RSASig;
import com.alibaba.fastjson.JSONObject;
import com.park.bean.ReturnDataNew;
import com.park.mvc.action.v1.BaseV1Controller;
import com.park.mvc.service.UserPayBiz;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

/**
 * 建行龙支付WEB通知监听接口
 *
 * @author jingxiaohu
 */
@Controller
@RequestMapping(value = "/v1")
public class Notify_LZFction extends BaseV1Controller {

  private static String pub = "30819d300d06092a864886f70d010101050003818b0030818702818100cd0b7cdab739d49af0ee8bf88f5bcfc8432d8c6818f0821d34e74bf52081977ec2a30cddba61b84fda72b6ec883283a14431410e7ff90449bd6e8fca88d3828a83d47a600e1e33881bcde003a65f9547acb998a0d971c4006e6c25222aed818d11b861381b7f80f8dc6d81303c25a8f17d35f2559a0802790f2b22c3dc5026c3020111";

  @Autowired
  private UserPayBiz userPayBiz;
  /**
   * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
   */
  private static final long serialVersionUID = -3599663972160625262L;
  private RestTemplate restTemplate = new RestTemplate();

  /**
   * 建行龙支付WEB通知监听接口
   *
   * @return QueryString=POSID=426295203&BRANCHID=510000000&ORDERID=2016112513104344&PAYMENT=0.01&CURCODE=01&REMARK1=1&REMARK2=&ACC_TYPE=02&SUCCESS=Y&TYPE=1&REFERER=&CLIENTIP=118.116.108.94&SIGN=8e92947d12655728b70f655048e9a15c0d868ff809786b4ccdb14c3f42c82f4980d91e8023b5eb8b4a2b8e51b515f1134dad0a29c09d1c57241f60cff521190970503e713daa4fb1df9bac4784161ab89928f8d909cd36bfde74375e10cad76669c0bdd46db3df705bf765c69a394a7a89b310f61c11f4e452bf6f2abd415f57
   * 2016-11-25 13:10:39 [com.park.mvc.v1.action.BaseV1Action] INFO (Notify_LZFction.java:57) -
   * notify_lzf params={SIGN=8e92947d12655728b70f655048e9a15c0d868ff809786b4ccdb14c3f42c82f4980d91e8023b5eb8b4a2b8e51b515f1134dad0a29c09d1c57241f60cff521190970503e713daa4fb1df9bac4784161ab89928f8d909cd36bfde74375e10cad76669c0bdd46db3df705bf765c69a394a7a89b310f61c11f4e452bf6f2abd415f57,
   * REMARK1=1, CLIENTIP=118.116.108.94, SUCCESS=Y, REMARK2=, BRANCHID=510000000, ACC_TYPE=02,
   * ORDERID=2016112513104344, POSID=426295203, REFERER=, PAYMENT=0.01, CURCODE=01, TYPE=1}
   */
  @RequestMapping(value = "/notify_lzf")
  @ResponseBody
  public String notify_lzf(HttpServletRequest request, HttpServletResponse response)
      throws NoSuchMethodException, IOException {
    String notify_url = request.getParameter("REMARK2");
    if (StringUtils.hasText(notify_url) && !notify_url.equals(getLZFNotify_url())) {
      sendRespHtml(restTemplate.getForObject(notify_url +"?"+ request.getQueryString(), String.class), response);
      return null;
    }
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
        log.info("referer=" + request.getHeader("referer"));
        log.info("QueryString=" + request.getQueryString());
        log.info("notify_lzf params=" + params.toString());
      }
      RSASig rsaSig = new RSASig();
      rsaSig.setPublicKey(pub);//设置公钥
      String src = request.getQueryString();
      List<String> list = Arrays.asList(src.split("&"));
      StringBuffer sb = new StringBuffer();
      for (int i = 0; i < list.size(); i++) {
        String str = list.get(i);
        if (!str.contains("SIGN")) {
          sb.append(str).append("&");
        }
      }
      if (sb.length() > 0) {
        src = sb.substring(0, sb.length() - 1);
      }
      if (rsaSig.verifySigature(params.get("SIGN"), src)) {
        //验签成功
        log.info("验签成功-----------");

        if ("Y".equalsIgnoreCase(params.get("SUCCESS"))) {
          //是支付 还是 充值  1：充值  2：普通订单支付  3：租赁订单支付
          String REMARK1 = params.get("REMARK1");

          //验证成功  更新该订单的支付状态 并把对应的金额添加给用户
          String orderid = params.get("ORDERID");
          //金额
          BigDecimal PAYMENT = new BigDecimal(params.get("PAYMENT"));
          long money = PAYMENT.multiply(new BigDecimal(100)).longValue();
          try {
            userPayBiz.notify_lzf(returnData, orderid, REMARK1, money);
            if (returnData != null && "0".equalsIgnoreCase(returnData.getErrorno())) {
              sendRespHtml(SUCCESS, response);
              return null;
            } else {
              sendRespHtml(JSONObject.toJSONString(returnData), response);
              return null;
            }
          } catch (Exception e) {
            // TODO Auto-generated catch block
            log.error("更新该订单的支付状态 并把对应的金额添加给用户 失败", e);
          }
          sendRespHtml(ERROR, response);
          return null;
        }
      }
      log.info("验签失败------error-----");
      sendRespHtml(ERROR, response);
      return null;
    } catch (Exception e) {
      log.error("Notify_LZFction.notify_lzf is error 龙支付通知监听接口（GAMESDK-JAVA）- P", e);
      sendRespHtml(ERROR, response);
      return null;
    }
  }

/********************接收参数区*************************/



/*public static void main(String[] args) {
  String sign = "b37b19f17aacafca0c8a78d6caf14efc3969deff34cf9b36a31643ae9b31bd85e4b31e460db220c5c2e75260a568453604b8f37f81a66acf5e81b5ba5c8b49179eaac4c412dc0070fb7d45946ee714f0e147e19c137de72fc49d6e69ddfeeb12772666af0efa616434ba47bc9d570325d97941e899954659b5e2f5a298cc914f";
	RSASig rsaSig = new RSASig();
	rsaSig.setPublicKey(pub);//设置公钥
	String src = "POSID=426295203&BRANCHID=510000000&ORDERID=2016111817363972&PAYMENT=0.01&CURCODE=01&REMARK1=&REMARK2=&ACC_TYPE=02&SUCCESS=Y&TYPE=1&REFERER=&CLIENTIP=218.88.30.86";
	System.out.println(rsaSig.generateSigature(src));
	
	boolean flag = rsaSig.verifySigature(sign, src);
	System.out.println("flag="+flag);
} */


}
