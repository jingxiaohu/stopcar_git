
package stopcar.test.action;

import org.apache.commons.httpclient.methods.PostMethod;
import org.junit.Test;

import com.park.constants.Constants;

/**
 * 露天停车场的PDA更新用户自动支付
 * @author jingxiaohu
 *
 */
public class Write_PDAsureOutParkActionTest extends BaseActionTest {
	/**
	 * 1：检查某停车场某车牌号是否已经付款
	 * {"data":{"money":0,"state":0,"car_code":"川A4M99B"},"errormsg":"查询是否已经付款成功","errorno":"0"}
	 * @throws Exception
	 */
	@Test
	public void pda_sure() throws Exception{
		String url = BaseUrl+"pda_sure.php";
		PostMethod post  = new UTF8PostMethod(url);
		//设置公共参数 川A4M99B
		setPublicParam(post);
		
		long pi_id = 226;//场地主键ID
		String car_code = "川E12345";//车牌号
		String area_code="510108";//省市区区域代码  四川省 成都市 龙泉驿区
		String orderid="2017032418572988";//我们的订单号  字符串
//		String escape_orderids="";
		String pay_type="5";
		int money=1;//金额（ 单位分）
		sign = getSignature(Constants.SYSTEM_KEY, dtype,orderid,pi_id);
		
		post.addParameter("pi_id", pi_id+"");
		post.addParameter("car_code", car_code);
		post.addParameter("area_code", area_code);
		post.addParameter("orderid", orderid);
		post.addParameter("pay_type", pay_type);
//		post.addParameter("escape_orderids", escape_orderids);
		post.addParameter("money", money+"");
		
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
			log.error("read_expect_order is error"+e.getMessage(), e); 
		}finally{
			if(post!=null){
				post.releaseConnection();
				//释放链接
				HC.getHttpConnectionManager().closeIdleConnections(0);
			}
		}
	}
	
	@Test
	public  void main11() {
		String sign = getSignature(Constants.SYSTEM_KEY, "2","2017032419467408",29);
		System.out.println(sign);
	}
	
}
