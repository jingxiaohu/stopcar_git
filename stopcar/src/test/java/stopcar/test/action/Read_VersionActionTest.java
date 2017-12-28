
package stopcar.test.action;

import org.apache.commons.httpclient.methods.PostMethod;
import org.junit.Test;

import com.park.constants.Constants;

/**
 * 检查版本
 * @author jingxiaohu
 *
 */
public class Read_VersionActionTest extends BaseActionTest {
	/**
	 * 1：检查某停车场某车牌号是否已经付款
	 * {"data":{"money":0,"state":0,"car_code":"川A4M99B"},"errormsg":"查询是否已经付款成功","errorno":"0"}
	 * @throws Exception
	 */
	@Test
	public void gainupgrade() throws Exception{
		String url = BaseUrl+"gainupgrade.php";
		PostMethod post  = new UTF8PostMethod(url);
		//设置公共参数 川A4M99B
		setPublicParam(post);
		
		 
		 String version="v1.0";//版本
		 int versioncode=1;//版本内部编号
		 String uid="48";//用户ID
		
		sign = getSignature(Constants.SYSTEM_KEY, dtype,versioncode);
		
		post.addParameter("version", version);
		post.addParameter("versioncode", versioncode+"");
		post.addParameter("uid", uid);
		
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
			log.error("read_expect_order is error"+e.getMessage(), e); 
		}finally{
			if(post!=null){
				post.releaseConnection();
				//释放链接
				HC.getHttpConnectionManager().closeIdleConnections(0);
			}
		}
	}
	
	
	
}
