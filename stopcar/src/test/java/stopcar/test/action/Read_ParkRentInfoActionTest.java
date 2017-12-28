
package stopcar.test.action;

import org.apache.commons.httpclient.methods.PostMethod;
import org.junit.Test;

import com.park.constants.Constants;

/**
 * 读取停车场租赁详情
 * @author jingxiaohu
 *
 */
public class Read_ParkRentInfoActionTest extends BaseActionTest {
	/**
	 * 读取停车场租赁详情
	 * @throws Exception
	 * {"data":{"rule":{"area_code":"510122","car_code_color":1,"car_displacement":"1.6T","car_type":1,"charging":120,"charging_time":60,"ctime":1470971498000,"is_default":0,"is_time_bucket":0,"month_price":15000,"month_time":30,"note":"","permit_time":"17:00-08:30","pi_id":1,"rcr_discount":0,"rcr_id":2,"rcr_md5":"","rcr_state":0,"rcr_type":1,"start_price":500,"start_time":180,"time_bucket":"白天 9：00-12：00 每小时2元","timeout_info":"首停2小时3元，之后1元/小时","utime":1470971498000},"park_info":{"address_name":"天府三街地铁A出口","allow_expect_time":0,"allow_revoke_time":0,"area_code":"0","camera_info":"罗技Pro C920","carport_space":93,"carport_total":100,"carport_yet":7,"copy_linkman_name":"张小强","copy_linkman_tel":"15882345447","ctime":1470296969000,"department":"四川乐库斯","enter_camera_num":1,"enter_num":1,"exit_camera_num":1,"exit_num":1,"expect_money":0,"hlc_enter_num":1,"hlc_exit_num":1,"lat":30.547776,"linkman_name":"敬小虎","linkman_tel":"15882345446","lng":104.067138,"moth_car_num":0,"note":"","park_type":0,"pi_id":1,"pi_name":"四川乐库斯","pi_phone":"028-85960236","rent_info":"准入时段 17:00-08:30，150元/月","time_car_num":0,"timeout_info":"首停3小时5元，之后每小时1元","utime":1470296969000},"coupon":[{"upc_id":1,"ui_id":1,"pc_id":1,"money":2,"discount":0,"high_money":5,"upc_type":0,"end_time":1472188317000,"upc_state":0,"ctime":1470805921000,"utime":1470805924000}]},"errormsg":"读取停车场租赁详情成功","errorno":"0"}
	 */
	@Test
	public void read_parkrent_info() throws Exception{
		String url = BaseUrl+"read_parkrent_info.php";
		PostMethod post  = new UTF8PostMethod(url);
		//设置公共参数
		setPublicParam(post);
		
		int pi_id=1;//停车场主键ID
		 String area_code="510112";//area_code;//省市县编号 510122
		 
		
		
		sign = getSignature(Constants.SYSTEM_KEY, dtype,ui_id,pi_id);
		
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
			log.error("read_parkrent_info is error"+e.getMessage(), e); 
		}finally{
			if(post!=null){
				post.releaseConnection();
				//释放链接
				HC.getHttpConnectionManager().closeIdleConnections(0);
			}
		}
	}
}
