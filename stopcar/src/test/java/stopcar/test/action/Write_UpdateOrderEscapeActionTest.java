
package stopcar.test.action;

import org.apache.commons.httpclient.methods.PostMethod;
import org.junit.Test;

import com.park.constants.Constants;

/**
 * 更新订单的 逃逸 状态 
 * @author jingxiaohu
 *
 */
public class Write_UpdateOrderEscapeActionTest extends BaseActionTest {
	/**
	 * 扫描到车辆出库扣费
	 */
	@Test
	public void upate_order_escape() throws Exception{
		String url = BaseUrl+"upate_order_escape.php";
		PostMethod post  = new UTF8PostMethod(url);
		//设置公共参数 川A4M99B
		setPublicParam(post);
		
		 
		String orderid="2017012211002724";//我们的订单号  字符串
		int type = 1;//类型 0:正常  1:逃逸   2:已经缴费--编辑逃逸状态为--已经缴费
		int money = 0;//金额
		sign = getSignature(Constants.SYSTEM_KEY, dtype,orderid,type,money);
		
		post.addParameter("orderid", orderid);
		post.addParameter("type", type+"");
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
			log.error("upate_order_escape is error"+e.getMessage(), e); 
		}finally{
			if(post!=null){
				post.releaseConnection();
				//释放链接
				HC.getHttpConnectionManager().closeIdleConnections(0);
			}
		}
	}
	
	
	
}
