
package stopcar.test.action;

import org.apache.commons.httpclient.methods.PostMethod;
import org.junit.Test;

/**
 * 建行龙支付进行下单接口
 * @author jingxiaohu
 *
 */
public class Notify_ZFBctionTest extends BaseActionTest {
	/**
	 * 建行龙支付进行下单接口
	 * @throws Exception
	 * {"data":{"act_type":1,"ctime":1479384712688,"etime":1479384712688,"id":2,"ip":"","money":1,"note":"","order_id":"2016111720113718","referer":"","return_url":"","state":0,"subject":"","system_type":0,"transaction_id":"","type":4,"ui_id":4,"ui_nd":"2016090910294823","utime":1479384712688,"version_code":1},"errorcode":"","errormsg":"下单成功","errorno":"0"}
	 */
	@Test
	public void zfb_charge_face() throws Exception{
		 String url = BaseUrl+"notify_zfb.php";
		 PostMethod post  = new UTF8PostMethod(url);
		 //设置公共参数 川A4M99B
//		 setPublicParam(post);
		post.addParameter("buyer_id", "2088902576563951");
		post.addParameter("total_amount", "0.02");
		post.addParameter("trade_no", "2016122821001004950222648077");
		post.addParameter("body", "2");
		post.addParameter("notify_time", "2016-12-28 15:56:18");
		post.addParameter("open_id", "20880033688280983062037053116995");
		post.addParameter("subject", "吾泊扫码支付");
		post.addParameter("sign_type", "RSA");
		post.addParameter("buyer_logon_id", "251***@qq.com");
		post.addParameter("auth_app_id", "2016112103042572");
		
		post.addParameter("charset", "utf-8");
		post.addParameter("notify_type", "trade_status_sync");
		post.addParameter("out_trade_no", "2016122815568560");
		
		post.addParameter("trade_status", "WAIT_BUYER_PAY");
		post.addParameter("version", "1.0");
		post.addParameter("gmt_create", "2016-12-28 15:56:18");
		
		post.addParameter("seller_id", "2088421595009690");
		post.addParameter("app_id", "2016112103042572");
		post.addParameter("seller_email", "scqckj2@126.com");
		post.addParameter("notify_id", "697f4fbb97231c9f2c6ca0c36605f42nby");
		post.addParameter("sign", "q+lYHavp73TE7twGrCXyUj0bRGz1MtJgfWiCTNhv5/iynhmHiYqiUwogUtnnhe3fYzvhtU+J5eoBLn9KkKCtgi+jShOMOq8++QzL0CAL8DS3G9VuwNPjYb7iH9IjZcSFDIZJb/UFbQN8u0n5VK2elVXYsmyJNlvpSVCmgpGAZ7g=");
		
		
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
