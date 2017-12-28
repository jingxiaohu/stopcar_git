
package stopcar.test.action;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.httpclient.methods.PostMethod;
import org.junit.Test;

import com.alibaba.fastjson.JSONObject;
import com.park.util.XMLBeanUtils;
import com.weixin.config.HttpTool;
import com.weixin.config.WeixinPayConstants;
import com.weixin.config.WeixinResult;

/**
 * 微信通知测试
 * @author jingxiaohu
 *
 */
public class Notify_WeiXinctionTest extends BaseActionTest {
	/**
	 * 微信通知测试
	 * @throws Exception
	 */
	@Test
	public void notify_weixin() throws Exception{
		 String url = BaseUrl+"notify_weixin.php";
		 //设置公共参数 川A4M99B
		 WeixinResult rs = new WeixinResult();
		 rs.setReturn_msg("失败");
		 rs.setReturn_code("FALL");
		 Map<String, Class<?>>  cm = new HashMap<String, Class<?>>(); 
		 cm.put("xml",WeixinResult.class);
		 String result = HttpTool.sendPost(url,XMLBeanUtils.bean2xml(cm, rs) ); 
		 System.out.println(result);
	}
}
