
package stopcar.test.action;

import org.apache.commons.httpclient.methods.PostMethod;
import org.junit.Test;

import com.park.constants.Constants;

/**
 * 占道停车场PDA MAC的初始化提交
 * @author jingxiaohu
 *
 */
public class Write_PDA_init_v2ActionTest extends BaseActionTest {
	/**
	 * 占道停车场PDA MAC的初始化提交
	 * @throws Exception
	 *{"data":{"area_code":"","ctime":1490603944122,"is_initialize":1,"lat":0.0,"lng":0.0,"loginname":"20170327163904","mac":"869612023700513","note":"","password":"e10adc3949ba59abbe56e057f20f883e","pda_c_id":0,"pda_id":0,"pda_sn":"2017032716398081","pi_id":0,"pi_name":"","plate_license":"","pu_id":0,"pu_nd":"","utime":1490603944122},"errorcode":"","errormsg":"初始化成功","errorno":"0"}
	 */
	@Test
	public void init_pda_v2() throws Exception{
		String url = BaseUrl+"init_pda_v2.php";
		PostMethod post  = new UTF8PostMethod(url);
		//设置公共参数 川A4M99B
		setPublicParam(post);
		
		
		String mac="869612023700513";//车牌号
		 
		sign = getSignature(Constants.SYSTEM_KEY, dtype,mac);
		
		post.addParameter("mac", mac);
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
			log.error("init_pda_v2 is error"+e.getMessage(), e); 
		}finally{
			if(post!=null){
				post.releaseConnection();
				//释放链接
				HC.getHttpConnectionManager().closeIdleConnections(0);
			}
		}
	}
}
