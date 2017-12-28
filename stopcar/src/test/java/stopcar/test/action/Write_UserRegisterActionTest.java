
package stopcar.test.action;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.httpclient.methods.PostMethod;
import org.junit.Test;

import com.park.constants.Constants;

/**
 * 用户注册
 * @author jingxiaohu
 *
 */
public class Write_UserRegisterActionTest extends BaseActionTest {
	/**
	 * 发送验证码
	 * @throws Exception
	 */
	@Test
	public void reg() throws Exception{
		String url = BaseUrl+"reg.php";
		PostMethod post  = new UTF8PostMethod(url);
		//设置公共参数
		setPublicParam(post);
		//电话号码
		String tel = "15882345446";
		//固定参数：1：注册 2：重置密码 3:重置绑定电话号码
		String vclass = "1";
		//用户验证码
		String verify_code="042165"; 
		String verify_list="5315e9a07061496ac702d7303d8f02f5";//由发送验证码接口或者重新发送验证码接口返回的verify_list参数的值 //md5(tel+code)
		String password=DigestUtils.md5Hex("123456");//123456用户密码 已经进行MD5后的值 
		String repassword=DigestUtils.md5Hex("123456");//123456确认密码
		
		
		sign = getSignature(Constants.SYSTEM_KEY, dtype,tel,verify_code,verify_list,vclass,password,repassword);
		
		post.addParameter("tel", tel);
		post.addParameter("vclass", vclass);
		post.addParameter("verify_code", verify_code);
		post.addParameter("verify_list", verify_list);
		post.addParameter("password", password);
		post.addParameter("repassword", repassword);
		
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
			log.error("reg is error"+e.getMessage(), e); 
		}finally{
			if(post!=null){
				post.releaseConnection();
				//释放链接
				HC.getHttpConnectionManager().closeIdleConnections(0);
			}
		}
	}
}
