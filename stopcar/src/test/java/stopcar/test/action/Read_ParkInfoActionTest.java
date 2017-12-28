
package stopcar.test.action;

import org.apache.commons.httpclient.methods.PostMethod;
import org.junit.Test;

import com.park.constants.Constants;

/**
 * 读取停车场详情
 * @author jingxiaohu
 *
 */
public class Read_ParkInfoActionTest extends BaseActionTest {
	/**
	 * 读取停车场详情
	 * @throws Exception
	 * {"data":{"address_name":"天府三街地铁A出口","allow_expect_time":60,"allow_revoke_time":0,"area_code":"510112","camera_info":"罗技Pro C920","carport_space":89,"carport_total":100,"carport_yet":11,"copy_linkman_name":"张小强","copy_linkman_tel":"15882345447","ctime":1470296969000,"department":"四川乐库斯","enter_camera_num":1,"enter_num":1,"exit_camera_num":1,"exit_num":1,"expect_money":5,"hlc_enter_num":1,"hlc_exit_num":1,"is_rent":1,"lat":30.547776,"linkman_name":"敬小虎","linkman_tel":"15882345446","lng":104.067138,"month_price":30000,"moth_car_num":0,"note":"","park_type":0,"pi_id":1,"pi_name":"四川乐库斯","pi_phone":"028-85960236","rent_info":"准入时段 17:00-08:30，150元/月","time_car_num":0,"timeout_info":"首停3小时5元，之后每小时1元","utime":1470296969000},"errorcode":"","errormsg":"获取停车场详情成功","errorno":"0"}
	 */
	@Test
	public void read_park_info() throws Exception{
		String url = BaseUrl+"read_park_info.php";
		PostMethod post  = new UTF8PostMethod(url);
		//设置公共参数
		setPublicParam(post);
		
		int pi_id=1;//停车场主键ID
		 String area_code="510107";//area_code;//省市县编号 510122
		 
		
		
		sign = getSignature(Constants.SYSTEM_KEY, dtype,pi_id);
		
		post.addParameter("pi_id", pi_id+""); 
		 post.addParameter("area_code", area_code);
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
			log.error("read_park_info is error"+e.getMessage(), e); 
		}finally{
			if(post!=null){
				post.releaseConnection();
				//释放链接
				HC.getHttpConnectionManager().closeIdleConnections(0);
			}
		}
	}
}
