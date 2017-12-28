
package stopcar.test.action;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.httpclient.methods.PostMethod;
import org.junit.Test;

import com.park.constants.Constants;

/**
 * 扫码赠送优惠券
 * @author jingxiaohu
 * {
    "data":"",
    "errorcode": "",
    "errormsg": "赠送成功",
    "errorno": "0"
}
 *
 */
public class Write_GiveCouponActionTest extends BaseActionTest {
	/**
	 * 扫码赠送优惠券
	 * @throws Exception
	 */
	@Test
	public void give_coupon() throws Exception{
		String url = BaseUrl+"give_coupon.php";
		PostMethod post  = new UTF8PostMethod(url);
		//设置公共参数
		setPublicParam(post);
		//优惠券原属用户ID
		Long from_ui_id = 142L;
		//用户优惠券主键ID
		Long upc_id = 70L;
		sign = getSignature(Constants.SYSTEM_KEY, dtype,ui_id,upc_id,from_ui_id);
		
		
		post.addParameter("from_ui_id", from_ui_id+"");
		post.addParameter("upc_id", upc_id+"");
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
			log.error("give_coupon is error"+e.getMessage(), e); 
		}finally{
			if(post!=null){
				post.releaseConnection();
				//释放链接
				HC.getHttpConnectionManager().closeIdleConnections(0);
			}
		}
	}
}
