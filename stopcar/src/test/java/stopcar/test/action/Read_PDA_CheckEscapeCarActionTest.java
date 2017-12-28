
package stopcar.test.action;

import org.apache.commons.httpclient.methods.PostMethod;
import org.junit.Test;

import com.park.constants.Constants;

/**
 * 获取PDA的对应的车是否是逃逸车辆或者未交费车辆
 * @author jingxiaohu
 *
 */
public class Read_PDA_CheckEscapeCarActionTest extends BaseActionTest {
	/**
	 * {"data":"","errorcode":"","errormsg":"不是未交费车辆","errorno":"0"}
	 * 获取PDA的对应的车是否是逃逸车辆或者未交费车辆
	 * @throws Exception
	 */
	@Test
	public void pda_check_escape_car() throws Exception{
		String url = BaseUrl+"pda_check_escape_car.php";
		PostMethod post  = new UTF8PostMethod(url);
		//设置公共参数
		setPublicParam(post);
		
		
		 long pi_id = 334;//场地主键ID
		 String area_code="510107";//area_code;//省市县编号 510122
		 String car_code = "川LX6997";//车牌号 (第一期：车牌号---对应一个人)
		 Integer type = 1;//是检查 逃逸逃逸车辆 还是 未支付车辆   0：默认检查逃逸车辆  1：检查未支付车辆
		
		post.addParameter("area_code", area_code);
		post.addParameter("pi_id", pi_id+"");
		post.addParameter("car_code", car_code);
		post.addParameter("type", type+"");
		
		
		
		
		sign = getSignature(Constants.SYSTEM_KEY, dtype,pi_id,area_code,type);
		
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
			log.error("check_expectcar is error"+e.getMessage(), e); 
		}finally{
			if(post!=null){
				post.releaseConnection();
				//释放链接
				HC.getHttpConnectionManager().closeIdleConnections(0);
			}
		}
	}
}
