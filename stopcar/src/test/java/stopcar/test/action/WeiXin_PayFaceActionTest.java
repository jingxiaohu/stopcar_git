
package stopcar.test.action;

import com.park.constants.Constants;
import com.park.util.XMLBeanUtils;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.httpclient.methods.PostMethod;
import org.junit.Test;

/**
 * 微信支付下单接口
 *
 * @author jingxiaohu
 */
public class WeiXin_PayFaceActionTest extends BaseActionTest {

  @Test
  public void map2xml() throws Exception {
    Map<String, String> map = new HashMap<>();
    map.put("a", "b");
    map.put("b", "b");
    System.err.println(XMLBeanUtils.map2xml(map));
  }

  @Test
  public void xmlToMap() throws Exception {
    System.err.println(XMLBeanUtils.xml2Map("<xml>\n"
        + "  <appid>wxa8c52369cd7047b5</appid>\n"
        + "  <mch_id>1426571202</mch_id>\n"
        + "  <nonce_str>mmRyLcpeM84dAkdWD5YaaNg6Hr1A4Zp4</nonce_str>\n"
        + "  <body>%E5%90%BE%E6%B3%8A%E5%BE%AE%E4%BF%A1%E5%85%85%E5%80%BC</body>\n"
        + "  <attach>1</attach>\n"
        + "  <out_trade_no>2017030711066872</out_trade_no>\n"
        + "  <total_fee>1</total_fee>\n"
        + "  <spbill_create_ip>127.0.0.1</spbill_create_ip>\n"
        + "  <notify_url>http://223.85.163.38:8091/v1/notify_weixin.php</notify_url>\n"
        + "  <trade_type>NATIVE</trade_type>\n"
        + "  <product_id>2017030711066872</product_id>\n"
        + "  <sign>D4FC80338225AE094340D19745B7D721</sign>\n"
        + "</xml>"));

  }

  /**
   * 微信充值进行下单接口 {"data":{"utime":1488858510908,"ui_nd":"2017030114385872","car_order_id":"","subject":"吾泊微信充值","tel":"15882345446","state":0,"type":2,"order_id":"2017030711487879","ctime":1488858510908,"transaction_id":"","version_code":1,"ip":"127.0.0.1","return_url":"","timestamp":"2017-03-07
   * 11:48:32","sign":"","orderInfo":"weixin://wxpay/bizpayurl?pr=S0I0vQq","id":356,"system_type":1,"etime":1488858510908,"act_type":1,"money":1,"referer":"","ui_id":92,"note":""},"errorcode":"","errormsg":"微信充值成功","errorno":"0"}
   */
  @Test
  public void weixin_charge_face() throws Exception {
    String url = BaseUrl + "weixin_charge_face.php";
    PostMethod post = new UTF8PostMethod(url);
    //设置公共参数 川A4M99B
    setPublicParam(post);
    int pay_type = 2;//支付类型 1:支付宝  2：微信  3：银联  4：钱包 5:龙支付 ',
    int pay_price = 1;//充值金额 单位 分
    int version = 1;//当前版本编号
    String subject = "吾泊微信充值";//商品名称
    int system_type = 1;//操作系统类型（IOS Android web） 1:android 2:IOS 3:web
    long t = System.currentTimeMillis();//时间戳ms
    String token = "0b5427ae6874b1a3e2dd9f1e30172c16";//令牌
    int type = 1;////是支付 还是 充值  1：充值  2：普通订单支付  3：租赁订单支付
    //收银台页面上，商品展示的超链接，必填
    String show_url = "";
    //页面跳转同步通知页面路径
    String return_url = "";

    sign = getSignature(Constants.SYSTEM_KEY, dtype, ui_id, pay_type, pay_price, t, token);

    post.addParameter("pay_type", pay_type + "");
    post.addParameter("pay_price", pay_price + "");
    post.addParameter("version", version + "");
    post.addParameter("subject", subject + "");
    post.addParameter("system_type", system_type + "");
    post.addParameter("t", t + "");
    post.addParameter("token", token);
    post.addParameter("type", type + "");
    post.addParameter("show_url", show_url);
    post.addParameter("return_url", return_url);
    post.addParameter("sign", sign);
    int xx = HC.executeMethod(post);
    System.out.println("*请求状态code=" + xx);
    try {
      String ds = post.getResponseBodyAsString();
      if (ds == null) {
        System.out.println("无数据返回");
      } else {
        System.out.println(new String(post.getResponseBodyAsString().getBytes("utf-8"), "utf-8"));
      }
    } catch (Exception e) {
      e.printStackTrace();
      log.error("parkinfo is error" + e.getMessage(), e);
    } finally {
      if (post != null) {
        post.releaseConnection();
        //释放链接
        HC.getHttpConnectionManager().closeIdleConnections(0);
      }
    }
  }
}
