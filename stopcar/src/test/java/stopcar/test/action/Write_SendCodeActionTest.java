
package stopcar.test.action;

import org.apache.commons.httpclient.methods.PostMethod;
import org.junit.Test;

import com.park.constants.Constants;

/**
 * 用户发送验证码
 * @author jingxiaohu
 *
 */
public class Write_SendCodeActionTest extends BaseActionTest {
	/**
	 * 发送验证码
	 * @throws Exception
	 */
	@Test
	public void sendcode() throws Exception{
		String url = BaseUrl+"sendcode.php";
		PostMethod post  = new UTF8PostMethod(url);
		//设置公共参数
		setPublicParam(post);
		//电话号码
		String tel = "15882345446";
		//固定参数：1：注册 2：重置密码 3:重置绑定电话号码
		String vclass = "1";
		sign = getSignature(Constants.SYSTEM_KEY, dtype,tel,vclass);
		
		post.addParameter("tel", tel);
		post.addParameter("vclass", vclass);
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
			log.error("sendcode is error"+e.getMessage(), e); 
		}finally{
			if(post!=null){
				post.releaseConnection();
				//释放链接
				HC.getHttpConnectionManager().closeIdleConnections(0);
			}
		}
	}
	
	/**
	 * 重发验证码
	 * @throws Exception
	 */
	@Test
	public void resendcode() throws Exception{
		String url = BaseUrl+"resendcode.php";
		PostMethod post  = new UTF8PostMethod(url);
		//设置公共参数
		setPublicParam(post);
		//电话号码
		String tel = "15882345446";
		//固定参数：1：注册 2：重置密码 3:重置绑定电话号码
		String vclass = "1";
		String verify_list ="353017442aec9095ba611d4e2673af62";//md5(tel+code)
		sign = getSignature(Constants.SYSTEM_KEY, dtype,tel,verify_list,vclass);
		
		post.addParameter("tel", tel);
		post.addParameter("vclass", vclass);
		post.addParameter("verify_list", verify_list);
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
			log.error("sendcode is error"+e.getMessage(), e); 
		}finally{
			if(post!=null){
				post.releaseConnection();
				//释放链接
				HC.getHttpConnectionManager().closeIdleConnections(0);
			}
		}
	}
	
}
