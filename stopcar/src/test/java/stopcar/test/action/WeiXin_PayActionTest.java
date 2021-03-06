
package stopcar.test.action;

import org.apache.commons.httpclient.methods.PostMethod;
import org.junit.Test;

import com.park.constants.Constants;

/**
 * 微信支付下单接口
 * @author jingxiaohu
 *
 */
public class WeiXin_PayActionTest extends BaseActionTest {
	/**
	 * 微信充值进行下单接口
{"data":{"utime":1488852301307,"ui_nd":"2017030114385872","car_order_id":"","subject":"吾泊微信充值","tel":"15882345446","state":0,"type":2,"order_id":"2017030710057483","ctime":1488852301307,"transaction_id":"","version_code":1,"ip":"127.0.0.1","return_url":"","sign":"6515280066BDD08B8E5D2ED8950C4933","orderInfo":{"sign":"6515280066BDD08B8E5D2ED8950C4933","timestamp":"2017-03-07 10:05:03","noncestr":"qdAaAZI1f9WtNfcCVhCTMChIXLFoUzp9","partnerid":"1426571202","prepayid":"wx20170307100503580bf3f85f0736305710","package":"Sign=WXPay","appid":"wxa8c52369cd7047b5"},"timestamp":"2017-03-07 10:05:03","id":279,"system_type":1,"etime":1488852301307,"act_type":1,"money":1,"referer":"","ui_id":92,"note":""},"errorcode":"","errormsg":"微信充值成功","errorno":"0"}
	 * @throws Exception
	 */
	@Test
	public void weixin_charge() throws Exception{
		 String url = BaseUrl+"weixin_charge.php";
		 PostMethod post  = new UTF8PostMethod(url);
		 //设置公共参数 川A4M99B
		 setPublicParam(post);
		 int pay_type=2;//支付类型 1:支付宝  2：微信  3：银联  4：钱包 5:龙支付 ',
		 int pay_price=1;//充值金额 单位 分
		 int version=1;//当前版本编号
		 String subject="吾泊微信充值";//商品名称
		 int system_type=1;//操作系统类型（IOS Android web） 1:android 2:IOS 3:web
		 long t=System.currentTimeMillis();//时间戳ms
		 String token="0b5427ae6874b1a3e2dd9f1e30172c16";//令牌
		 int type = 1;////是支付 还是 充值  1：充值  2：普通订单支付  3：租赁订单支付
		//收银台页面上，商品展示的超链接，必填
		 String show_url ="";
		//页面跳转同步通知页面路径
		 String return_url = "";
		
		sign = getSignature(Constants.SYSTEM_KEY, dtype,ui_id,pay_type,pay_price,t,token);
		
		
		post.addParameter("pay_type", pay_type+"");
		post.addParameter("pay_price", pay_price+"");
		post.addParameter("version", version+"");
		post.addParameter("subject", subject+"");
		post.addParameter("system_type", system_type+"");
		post.addParameter("t", t+"");
		post.addParameter("token", token);
		post.addParameter("type", type+"");
		post.addParameter("show_url", show_url);
		post.addParameter("return_url", return_url);
		post.addParameter("sign", sign);
		int xx = HC.executeMethod(post);
		System.out.println("*请求状态code="+xx); 
		try {
			String ds = post.getResponseBodyAsString();
			if(ds == null){
				System.out.println("无数据返回");
			}else{
				System.out.println(new String(post.getResponseBodyAsString().getBytes("utf-8"), "utf-8"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("parkinfo is error"+e.getMessage(), e); 
		}finally{
			if(post!=null){
				post.releaseConnection();
				//释放链接
				HC.getHttpConnectionManager().closeIdleConnections(0);
			}
		}
	}
}
