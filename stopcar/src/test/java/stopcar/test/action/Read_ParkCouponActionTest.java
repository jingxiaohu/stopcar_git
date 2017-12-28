
package stopcar.test.action;

import org.apache.commons.httpclient.methods.PostMethod;
import org.junit.Test;

import com.park.constants.Constants;

/**
 * 获取用户优惠券列表
 * @author jingxiaohu
 *
 */
public class Read_ParkCouponActionTest extends BaseActionTest {
	/**
	 * 获取用户优惠券列表
	 * @throws Exception
	 */
	@Test
	public void read_mycoupon() throws Exception{
		String url = BaseUrl+"read_mycoupon.php";
		PostMethod post  = new UTF8PostMethod(url);
		//设置公共参数
		setPublicParam(post);
		sign = getSignature(Constants.SYSTEM_KEY, dtype,ui_id);
		
		
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
			log.error("read_mycoupon is error"+e.getMessage(), e); 
		}finally{
			if(post!=null){
				post.releaseConnection();
				//释放链接
				HC.getHttpConnectionManager().closeIdleConnections(0);
			}
		}
	}
}
