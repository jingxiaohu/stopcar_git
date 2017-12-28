
package stopcar.test.action;

import org.apache.commons.httpclient.methods.PostMethod;
import org.junit.Test;

import com.park.constants.Constants;

/**
 * 检查某停车场某车牌号是否已经付款
 * @author jingxiaohu
 *
 */
public class Read_PDA_CheckCarPayActionTest extends BaseActionTest {
	/**
	 * 检查某停车场某车牌号是否已经付款
	 * {"data":{"escape_orderids":"","money":1,"ui_id":0,"car_code":"川E11106","state":0,"pay_source":0,"is_cash":0},"errorcode":"","errormsg":"查询是否已经付款成功","errorno":"0"}
	 * @throws Exception
	 */
	@Test
	public void read_pda_checkpay() throws Exception{
		String url = BaseUrl+"read_pda_checkpay.php";
		PostMethod post  = new UTF8PostMethod(url);
		//设置公共参数
		setPublicParam(post);
		long pi_id = 465;//场地主键ID
		String car_code = "川EW9611";//车牌号
		String area_code="510502";//省市区区域代码  四川省 成都市 龙泉驿区
		String orderid="2017061309255781663";//我们的订单号  字符串
		
		post.addParameter("dtype", dtype+"");
		post.addParameter("pi_id", pi_id+"");
		post.addParameter("car_code", car_code);
		post.addParameter("area_code", area_code);
		post.addParameter("orderid", orderid);
		
		sign = getSignature(Constants.SYSTEM_KEY, dtype,pi_id);
		System.out.println("sign===="+sign);
		
		
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
			log.error("login is error"+e.getMessage(), e); 
		}finally{
			if(post!=null){
				post.releaseConnection();
				//释放链接
				HC.getHttpConnectionManager().closeIdleConnections(0);
			}
		}
	}
	
	
}
