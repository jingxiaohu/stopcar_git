
package stopcar.test.action;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.httpclient.methods.PostMethod;
import org.junit.Test;

import com.park.constants.Constants;

/**
 * 用户登陆
 * @author jingxiaohu
 *
 */
public class Read_UserLoginActionTest extends BaseActionTest {
	/**
	 * 用户登陆
	 * @throws Exception
	 */
	@Test
	public void login() throws Exception{
		String url = BaseUrl+"login.php";
		PostMethod post  = new UTF8PostMethod(url);
		//设置公共参数
		setPublicParam(post);
		//电话号码
		String tel = "15882345446";
		//密码
		String password=DigestUtils.md5Hex("123456");//123456用户密码 已经进行MD5后的值 
		
		
		sign = getSignature(Constants.SYSTEM_KEY, dtype,tel,password);
		System.out.println("sign===="+sign);
		
		post.addParameter("tel", tel);
		post.addParameter("password", password);
		
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
