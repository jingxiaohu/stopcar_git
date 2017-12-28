
package stopcar.test.action;

import java.io.File;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.multipart.FilePart;
import org.apache.commons.httpclient.methods.multipart.MultipartRequestEntity;
import org.apache.commons.httpclient.methods.multipart.Part;
import org.apache.commons.httpclient.methods.multipart.StringPart;
import org.junit.Test;

import com.park.constants.Constants;

/**
 * 用户重置密码 用户修改绑定手机号码   修改用户基本信息
 * @author jingxiaohu
 *
 */
public class Write_UserModifyActionTest extends BaseActionTest {
	/**
	 * 用户重置密码
	 * @throws Exception
	 */
	@Test
	public void change_pass() throws Exception{
		String url = BaseUrl+"change_pass.php";
		PostMethod post  = new UTF8PostMethod(url);
		//设置公共参数
		setPublicParam(post);
		//电话号码
		String tel = "15882345446";
		//固定参数：1：注册 2：重置密码 3:重置绑定电话号码
		String vclass = "2";
		//用户验证码
		String verify_code="340435"; 
		String verify_list="353017442aec9095ba611d4e2673af62";//由发送验证码接口或者重新发送验证码接口返回的verify_list参数的值 //md5(tel+code)
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
			log.error("change_pass is error"+e.getMessage(), e); 
		}finally{
			if(post!=null){
				post.releaseConnection();
				//释放链接
				HC.getHttpConnectionManager().closeIdleConnections(0);
			}
		}
	}
	
	
	
	
	
	/**
	 * 用户修改绑定手机号码
	 * @throws Exception
	 */
	@Test
	public void change_tel() throws Exception{
		String url = BaseUrl+"change_tel.php";
		PostMethod post  = new UTF8PostMethod(url);
		//设置公共参数
		setPublicParam(post);
		//电话号码
		String tel = "15882345446";
		//固定参数：1：注册 2：重置密码
		String password=DigestUtils.md5Hex("123456");//123456用户密码 已经进行MD5后的值 
		
		
		sign = getSignature(Constants.SYSTEM_KEY, dtype,tel,password,ui_id);
		
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
			log.error("change_tel is error"+e.getMessage(), e); 
		}finally{
			if(post!=null){
				post.releaseConnection();
				//释放链接
				HC.getHttpConnectionManager().closeIdleConnections(0);
			}
		}
	}
	
	
	
	
	/**
	 * 修改用户基本信息
	 * @throws Exception
	 */
	@Test
	public void change_userinfo() throws Exception{
		String url = BaseUrl+"change_userinfo.php";
		PostMethod post  = new UTF8PostMethod(url);
		//设置公共参数
		setPublicParam(post);
		//用户昵称
		String nickname = "浩瀚星空";
		//用户头像
		File file = new File("D://temp/11.jpg");
		//用户性别
		String sex="male";//用户性别 : male 男   women 女   no  未知
		//姓名
//		String name = "敬小虎";
		//用户驾驶证
		String driving_licence="110107194011241250";
		//邮箱
		String email = "251878350@qq.com";
		
		
		
		sign = getSignature(Constants.SYSTEM_KEY, dtype,ui_id);
		
		
		Part[] parts = {
		    	 //new StringPart("text_content", "大叔大叔大叔大叔的萨达十大萨达十大","utf-8"),
				//new StringPart("status", URLEncoder.encode(status, "utf-8"),
				new StringPart("ui_id", ui_id+""),
				new StringPart("dtype", dtype+""),
//				new StringPart("name",name,"utf-8"),
				new StringPart("nickname",nickname,"utf-8"),
		    	new StringPart("sex", sex),
		    	new StringPart("driving_licence", driving_licence),
		    	new StringPart("email",email),
		    	new StringPart("sign", sign),
		        new FilePart("avatar", file)
		    }; 
		post.setRequestEntity(new MultipartRequestEntity(parts, post.getParams()));
		int xx = HC.executeMethod(post);
		System.out.println(xx);
		try {
			String ds = post.getResponseBodyAsString();
			if(ds == null){
				System.out.println("无数据返回");
			}else{
				ds = new String(post.getResponseBodyAsString().getBytes("utf-8"), "utf-8");
				System.out.println(ds);
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("checkLoginname is error"+e.getMessage(), e); 
		}finally{
			if(post!=null){
				post.releaseConnection();
				//释放链接
				HC.getHttpConnectionManager().closeIdleConnections(0);
			}
		}
	}
}
