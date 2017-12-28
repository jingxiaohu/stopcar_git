
package stopcar.test.action;

import org.apache.commons.httpclient.methods.PostMethod;
import org.junit.Test;

import com.park.constants.Constants;

/**
 * 用户充值
 * @author jingxiaohu
 *
 */
public class ReChargeActionTest extends BaseActionTest {
	/**
	 * 用户充值
	 * @throws Exception
	 */
	@Test
	public void recharge() throws Exception{
		String url = BaseUrl+"recharge.php";
		PostMethod post  = new UTF8PostMethod(url);
		//设置公共参数
		setPublicParam(post);
		int pay_type=1;//0:无来源 1：支付宝 2:微信 3：银联
		int pay_price=500;//充值金额 单位 分
		String version="v1.0";//当前版本
		int terminal_type=3;//终端类型：0:web 1:PC方式;wap 2手机WAP 3 mobile：手机客户端应用方式(默认)
		String subject="吾泊币";//商品名称
		String imei="";//手机唯一标识符
		long t=System.currentTimeMillis();//时间戳ms
		String ssid="";//WIFI名称
		String token="0b5427ae6874b1a3e2dd9f1e30172c16";//用户授权码
		//收银台页面上，商品展示的超链接，必填
		String show_url ="";
		// 页面跳转同步通知页面路径
	    String return_url = "";
		
		
		sign = getSignature(Constants.SYSTEM_KEY, dtype,ui_id,pay_type,pay_price,t,token);
		
		post.addParameter("pay_type", pay_type+"");
		post.addParameter("pay_price", pay_price+"");
		post.addParameter("version", version);
		post.addParameter("terminal_type", terminal_type+"");
		post.addParameter("subject", subject);
		post.addParameter("token", token);
		post.addParameter("t",  t+"");
		
		
		post.addParameter("imei", imei);
		post.addParameter("ssid", ssid);
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
			log.error("recharge is error"+e.getMessage(), e); 
		}finally{
			if(post!=null){
				post.releaseConnection();
				//释放链接
				HC.getHttpConnectionManager().closeIdleConnections(0);
			}
		}
	}
}
