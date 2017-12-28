
package stopcar.test.action;

import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.junit.Test;

import com.park.constants.Constants;

/**
 * 获取我的订单 （1：临时停车订单  2： 租赁订单）
 * @author jingxiaohu
 *
 */
public class NettyTest extends BaseActionTest {
	@Test
	public void nettytt() throws Exception{
		String url = BaseUrl;
		PostMethod post  = new UTF8PostMethod(url);
		//设置公共参数
		setPublicParam(post);
		
		int type=0;// 获取订单类型  0:普通停车订单  1：租赁停车订单
		String car_code="赣F16712";//车牌号
		String area_code="510107";//省市区区域代码  四川省 成都市 龙泉驿区  510112
		int page  = 1;
		int size = 20;
		
		sign = getSignature(Constants.SYSTEM_KEY, dtype,ui_id);
		post.addParameter("type", type+"");
		post.addParameter("car_code", car_code);
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
			log.error("read_mycoupon is error"+e.getMessage(), e); 
		}finally{
			if(post!=null){
				post.releaseConnection();
				//释放链接
				HC.getHttpConnectionManager().closeIdleConnections(0);
			}
		}
	}
	
	@Test
	public void nettytt2() throws Exception{
		String url = BaseUrl;
		PostMethod post  = new UTF8PostMethod(url);
		//设置公共参数
		setPublicParam(post);
		
		
		sign = getSignature(Constants.SYSTEM_KEY, dtype,ui_id);
		
		NameValuePair ss1 = new NameValuePair("aa","萨达宏卡卡灰色地带");
		NameValuePair ss2 = new NameValuePair("bb","阿斯大大");
		post.addParameter(ss1);
		post.addParameter(ss2);
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
			log.error("read_mycoupon is error"+e.getMessage(), e); 
		}finally{
			if(post!=null){
				post.releaseConnection();
				//释放链接
				HC.getHttpConnectionManager().closeIdleConnections(0);
			}
		}
	}
	
	@Test
	public void nettytt3() throws Exception{
		String url = BaseUrl;
		PostMethod post  = new UTF8PostMethod(url);
		//设置公共参数
		setPublicParam(post);
		
		
		sign = getSignature(Constants.SYSTEM_KEY, dtype,ui_id);
		
		NameValuePair ss1 = new NameValuePair("aa","萨达宏卡卡灰色地带");
		NameValuePair ss2 = new NameValuePair("bb","阿斯大大");
		NameValuePair[] parametersBody = new NameValuePair[2];
		parametersBody[0] = ss1;parametersBody[1] = ss2;
		post.setContentChunked(true);
		post.setRequestBody(parametersBody);
		int xx = HC.executeMethod(post);
		System.out.println("*请求状态code="+xx); 
		try {
			String ds = post.getResponseBodyAsString();
			if(ds == null){
				System.out.println("无数据返回");
			}else{
				System.out.println("返回数据------------------------------------------------");
				System.out.println("返回数据------------------------------------------------");
				System.out.println("返回数据------------------------------------------------");
				System.out.println("返回数据------------------------------------------------");
				System.out.println("返回数据------------------------------------------------");
				System.out.println("返回数据------------------------------------------------");
//				System.out.println(post.getResponseBodyAsString().getBytes("utf-8").length);
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
