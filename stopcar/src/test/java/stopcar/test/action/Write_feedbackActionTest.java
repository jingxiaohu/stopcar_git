
package stopcar.test.action;

import org.apache.commons.httpclient.methods.PostMethod;
import org.junit.Test;

import com.park.constants.Constants;

/**
 * 用户反馈
 * @author jingxiaohu
 *
 */
public class Write_feedbackActionTest extends BaseActionTest {
	/**
	 * 用户反馈
	 * @throws Exception
	 * {"data":"","errormsg":"用户反馈成功","errorno":"0"}
	 */
	@Test
	public void feedback() throws Exception{
		String url = BaseUrl+"feedback.php";
		PostMethod post  = new UTF8PostMethod(url);
		//设置公共参数
		setPublicParam(post);
		
		 String content = "出现一些问题";//反馈时间
		
		sign = getSignature(Constants.SYSTEM_KEY, dtype,ui_id);
		
		
		post.addParameter("content", content);
		
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
			log.error("feedback is error"+e.getMessage(), e); 
		}finally{
			if(post!=null){
				post.releaseConnection();
				//释放链接
				HC.getHttpConnectionManager().closeIdleConnections(0);
			}
		}
	}
	
	
	
}
