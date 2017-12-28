
package stopcar.test.action;

import org.apache.commons.httpclient.methods.PostMethod;
import org.junit.Test;

import com.park.constants.Constants;

/**
 * 建行龙支付进行下单接口
 * @author jingxiaohu
 *
 */
public class LZF_PayActionTest extends BaseActionTest {
	/**
	 * 建行龙支付进行下单接口
	 * @throws Exception
	 * {"data":{"act_type":1,"ctime":1479384712688,"etime":1479384712688,"id":2,"ip":"","money":1,"note":"","order_id":"2016111720113718","referer":"","return_url":"","state":0,"subject":"","system_type":0,"transaction_id":"","type":4,"ui_id":4,"ui_nd":"2016090910294823","utime":1479384712688,"version_code":1},"errorcode":"","errormsg":"下单成功","errorno":"0"}
	 */
	@Test
	public void lzf_charge() throws Exception{
		 String url = BaseUrl+"lzf_charge.php";
		 PostMethod post  = new UTF8PostMethod(url);
		 //设置公共参数 川A4M99B
		 setPublicParam(post);
		 int pay_type=5;//支付类型 1:支付宝  2：微信  3：银联  4：钱包 5:龙支付 ',
		 int type =2;//是支付 还是 充值  1：充值  2：普通订单支付  3：租赁订单支付
		 int pay_price=1;//充值金额 单位 分
		 int version=2;//当前版本编号
		 String subject="吾泊91";//商品名称
		 int system_type=4;//操作系统类型（IOS Android web） 1:android 2:IOS 3:web
		 long t=System.currentTimeMillis();//时间戳ms
		 String token="pda";//令牌
		//收银台页面上，商品展示的超链接，必填
		 String show_url ="";
		//页面跳转同步通知页面路径
		 String return_url = "";
		 String orderid="2017041809581123";
		sign = getSignature(Constants.SYSTEM_KEY, dtype,ui_id,pay_type,pay_price,t,token);
		
		
		post.addParameter("pay_type", pay_type+"");
		post.addParameter("pay_price", pay_price+"");
		post.addParameter("version", version+"");
		post.addParameter("subject", subject+"");
		post.addParameter("system_type", system_type+"");
		post.addParameter("t", t+"");
		post.addParameter("token", token);
		post.addParameter("show_url", show_url);
		post.addParameter("return_url", return_url);
		post.addParameter("orderid", orderid);
		post.addParameter("type", type+"");
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
