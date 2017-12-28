
package stopcar.test.action;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.park.constants.Constants;
import com.park.util.QEncodeUtil;
import org.apache.commons.httpclient.methods.PostMethod;
import org.junit.Test;

/**
 * @author jingxiaohu
 *
 */
public class Read_ParkAuthActionTest extends BaseActionTest {
	/**
	 * 获取停车场授权信息
	 * @throws Exception
	 * {"data":"","errormsg":"绑定成功","errorno":"0"}
	 */
	@Test
	public void read_park_auth() throws Exception{
		String url = BaseUrl+"read_park_auth.php";
		PostMethod post  = new UTF8PostMethod(url);
		//设置公共参数
		setPublicParam(post);
		long pi_id = 1;
		String area_code = "510107";
		post.addParameter("pi_id", pi_id+"");
		post.addParameter("area_code", area_code+"");
		sign = getSignature(Constants.SYSTEM_KEY, dtype,pi_id,area_code);
		
		post.addParameter("sign", sign);
		int xx = HC.executeMethod(post);
		System.out.println("*请求状态code="+xx); 
		try {
			String ds = post.getResponseBodyAsString();
			if(ds == null){
				System.out.println("无数据返回");
			}else{
//				System.out.println(new String(post.getResponseBodyAsString().getBytes("utf-8"), "utf-8"));
				String result = new String(post.getResponseBodyAsString().getBytes("utf-8"), "utf-8");
				JSONObject obj = JSON.parseObject(result);
				System.err.println(QEncodeUtil.aesDecrypt(obj.getJSONObject("data").getString("park_authorize"), "AFfsfhMFAFAS$#@%^&*&^%$!as79123fnDa"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("read_park_auth is error"+e.getMessage(), e);
		}finally{
			if(post!=null){
				post.releaseConnection();
				//释放链接
				HC.getHttpConnectionManager().closeIdleConnections(0);
			}
		}
	}
}
