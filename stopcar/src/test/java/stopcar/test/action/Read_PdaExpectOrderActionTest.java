
package stopcar.test.action;

import org.apache.commons.httpclient.methods.PostMethod;
import org.junit.Test;

import com.park.constants.Constants;

/**
 * 获取PDA的预约且未付款的订单
 * @author jingxiaohu
 *
 */
public class Read_PdaExpectOrderActionTest extends BaseActionTest {
	/**
	 * 获取PDA的预约且未付款的订单
	 * @throws Exception
	 * {"data":[{"address_name":"","allege_state":0,"area_code":"510101","arrive_time":1479110650000,"cancel_state":0,"car_code":"川A11111","charging":0,"charging_time":0,"ctime":1479110650000,"discount_money":0,"discount_name":"","discount_type":0,"expect_info":"预约提示信息","expect_money":400,"expect_time":60,"final_time":0,"id":415,"is_cash":0,"is_del":0,"is_expect_deduct":0,"is_expect_outtime":0,"is_open":0,"leave_time":1479110650000,"money":0,"my_order":"2016111416040443","note":"用户预约下单","open_time":1479110650000,"order_type":1,"other_order":"","park_type":1,"pay_source":0,"pay_type":0,"phone_type":0,"pi_id":17,"pp_state":0,"pp_versioncode":"1.0","start_price":0,"start_time":0,"ui_id":7,"upc_id":0,"utime":1479110650000}],"errorcode":"","errormsg":"获取PDA的预约且未付款和未取消的订单","errorno":"0"}
	 */
	@Test
	public void pda_expect_order() throws Exception{
		String url = BaseUrl+"pda_expect_order.php";
		PostMethod post  = new UTF8PostMethod(url);
		//设置公共参数
		setPublicParam(post);
		
		long pi_id=17;//停车场主键ID
		String area_code="510101";//省市区区域代码  四川省 成都市 龙泉驿区  510112
		int page  = 1;
		int size = 20;
		
		sign = getSignature(Constants.SYSTEM_KEY, dtype,pi_id,area_code);
		post.addParameter("pi_id", pi_id+"");
		post.addParameter("area_code", area_code);
		post.addParameter("page", page+"");
		post.addParameter("size", size+"");
		
		post.addParameter("sign", sign);
		int xx = HC.executeMethod(post);
		System.out.println("*请求状态code="+xx); 
		try {
			String ds = post.getResponseBodyAsString();
			if(ds == null){
				System.out.println("无数据返回");
			}else{
				System.out.println(post.getResponseBodyAsString().getBytes("utf-8").length);
				System.out.println(new String(post.getResponseBodyAsString().getBytes("utf-8"), "utf-8"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("pda_expect_order is error"+e.getMessage(), e); 
		}finally{
			if(post!=null){
				post.releaseConnection();
				//释放链接
				HC.getHttpConnectionManager().closeIdleConnections(0);
			}
		}
	}
}
