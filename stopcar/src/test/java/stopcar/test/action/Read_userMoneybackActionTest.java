
package stopcar.test.action;

import org.apache.commons.httpclient.methods.PostMethod;
import org.junit.Test;

import com.park.constants.Constants;

/**
 * 获取用户申诉退费
 * @author jingxiaohu
 *
 */
public class Read_userMoneybackActionTest extends BaseActionTest {
	/**
	 * 获取用户申诉退费
	 * {"data":{"admin_userid":0,"area_code":"510101","car_code":"川AQQ123","check_content":"","check_state":0,"content":"","ctime":1479368332000,"is_rent":1,"note":"","order_id":"2016111709558435","pi_id":17,"run_url":"","type":2,"ui_id":4,"um_id":28,"um_money":400,"um_state":0,"utime":1479368332000},"errorcode":"","errormsg":"获取成功","errorno":"0"}

	 * @throws Exception
	 */
	@Test
	public void read_usermoneyback() throws Exception{
		String url = BaseUrl+"read_usermoneyback.php";
		PostMethod post  = new UTF8PostMethod(url);
		//设置公共参数
		setPublicParam(post);
		
		 
		 
		String order_id="2016111709558435";//停车下订单ID
		int is_rent=1;//是否是租赁订单 0：临停订单  1：预约临停订单  2：租赁包月订单
		
		sign = getSignature(Constants.SYSTEM_KEY, dtype,ui_id,order_id);
		
		post.addParameter("order_id", order_id);
		post.addParameter("is_rent", is_rent+"");
		
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
			log.error("read_myinfo is error"+e.getMessage(), e); 
		}finally{
			if(post!=null){
				post.releaseConnection();
				//释放链接
				HC.getHttpConnectionManager().closeIdleConnections(0);
			}
		}
	}
}
