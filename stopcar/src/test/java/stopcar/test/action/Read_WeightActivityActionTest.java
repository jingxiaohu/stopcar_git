
package stopcar.test.action;

import org.apache.commons.httpclient.methods.PostMethod;
import org.junit.Test;

import com.park.constants.Constants;

/**
 * 获取优先的活动
 * @author jingxiaohu
 *
 */
public class Read_WeightActivityActionTest extends BaseActionTest {
	/**
	 * 获取优先的活动
	 * @throws Exception
	 * {"data":{"admin_id":1,"admin_loginname":"admin","ai_id":1,"area_code":"","ctime":1486436032000,"endtime":1527821613000,"img":"","intro":"用户首次安装APP且注册成功赠送5元代金券一张","money":500,"note":"","partner":"琦彩科技","pc_id":0,"people_num":0,"pi_id":0,"pi_name":"","starttime":1486349606000,"state":0,"title":"安装一个APP送5元代金券","type":0,"utime":1486436036000,"weight":0},"errorcode":"","errormsg":"获取成功","errorno":"0"}
	 */
	@Test
	public void weight_activity() throws Exception{
		String url = BaseUrl+"weight_activity.php";
		PostMethod post  = new UTF8PostMethod(url);
		//设置公共参数
		setPublicParam(post);
		
		
		sign = getSignature(Constants.SYSTEM_KEY, dtype,ui_id);
		
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
			log.error("weight_activity is error"+e.getMessage(), e); 
		}finally{
			if(post!=null){
				post.releaseConnection();
				//释放链接
				HC.getHttpConnectionManager().closeIdleConnections(0);
			}
		}
	}
}
