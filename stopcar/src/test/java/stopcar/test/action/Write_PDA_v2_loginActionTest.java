
package stopcar.test.action;

import org.apache.commons.httpclient.methods.PostMethod;
import org.junit.Test;

import com.park.constants.Constants;

/**
 * 占道停车场PDA登录
 * @author jingxiaohu
 *
 */
public class Write_PDA_v2_loginActionTest extends BaseActionTest {
	/**
	 * 占道停车场PDA登录
	 * @throws Exception
	 * {"data":{"pda_info":{"area_code":"510107","ctime":1490603944000,"is_initialize":1,"lat":0.0,"lng":0.0,"loginname":"20170327163904","mac":"869612023700513","note":"","password":"e10adc3949ba59abbe56e057f20f883e","pda_c_id":0,"pda_id":1,"pda_sn":"2017032716398081","pi_id":2,"pi_name":"","plate_license":"","pu_id":1,"pu_nd":"20161227141900","utime":1490603944000},"park_info":{"address_name":"英郡1期","admin_id":1,"allow_expect_time":60,"allow_revoke_time":5,"area_code":"510107","camera_info":"火眼","carport_space":1,"carport_total":3,"carport_yet":2,"copy_linkman_name":"xxx","copy_linkman_tel":"15808549031","ctime":1482819742000,"department":"希望国际大厦","enter_camera_num":1,"enter_num":1,"exit_camera_num":1,"exit_num":1,"expect_car_num":0,"expect_money":10,"hlc_enter_num":1,"hlc_exit_num":1,"is_expect":1,"is_fault":1,"is_rent":0,"lat":30.544615,"linkman_name":"xxx","linkman_tel":"15808549031","lng":104.072689,"loginname":"510107377013","mac":"869612023700513","money":2,"month_price":0,"moth_car_num":0,"moth_car_num_space":0,"note":"11111111","park_type":1,"password":"e10adc3949ba59abbe56e057f20f883e","pda_permit_time":"07:00-21:00","pi_id":2,"pi_name":"英俊一期","pi_phone":"15808549031","pi_state":1,"pu_id":1,"pu_nd":"20161227141900","rent_info":"","roadside_type":1,"time_car_num":0,"time_car_num_space":0,"timeout_info":"单次0.02元","upload_source":1,"utime":1484554131000}},"errorcode":"","errormsg":"登录成功","errorno":"0"}
	 */
	@Test
	public void pda_login() throws Exception{
		String url = BaseUrl+"pda_login.php";
		PostMethod post  = new UTF8PostMethod(url);
		//设置公共参数 川A4M99B
		setPublicParam(post);
		
		
		String mac="869612023700513";//车牌号
		String loginname="20170327163904";//省市区区域代码
		String password="e10adc3949ba59abbe56e057f20f883e";//我们的订单号  字符串 123456
		int vnum = 1; 
		sign = getSignature(Constants.SYSTEM_KEY, dtype,mac,loginname,password);
		
		post.addParameter("mac", mac);
		post.addParameter("loginname", loginname);
		post.addParameter("password", password);
		post.addParameter("vnum", vnum+""); 
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
			log.error("pda_login is error"+e.getMessage(), e); 
		}finally{
			if(post!=null){
				post.releaseConnection();
				//释放链接
				HC.getHttpConnectionManager().closeIdleConnections(0);
			}
		}
	}
}
