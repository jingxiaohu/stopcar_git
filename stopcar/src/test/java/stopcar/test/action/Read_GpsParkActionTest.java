
package stopcar.test.action;

import org.apache.commons.httpclient.methods.PostMethod;
import org.junit.Test;

import com.park.constants.Constants;

/**
 * 通过GPS导航获取 该经纬度范围内的停车场数据列表
 * @author jingxiaohu
 *
 */
public class Read_GpsParkActionTest extends BaseActionTest {
	/**
	 * 通过GPS导航获取 该经纬度范围内的停车场数据列表
	 * @throws Exception
	 * {"data":[{"address_name":"天府三街地铁A出口","allow_expect_time":0,"allow_revoke_time":0,"area_code":"0","camera_info":"罗技Pro C920","carport_space":0,"carport_total":100,"carport_yet":0,"copy_linkman_name":"张小强","copy_linkman_tel":"15882345447","ctime":1470296969000,"department":"四川乐库斯","enter_camera_num":1,"enter_num":1,"exit_camera_num":1,"exit_num":1,"expect_money":0,"hlc_enter_num":1,"hlc_exit_num":1,"lat":33.43144,"linkman_name":"敬小虎","linkman_tel":"15882345446","lng":109.77539000000002,"moth_car_num":0,"note":"","park_type":0,"pi_id":1,"pi_name":"四川乐库斯","pi_phone":"028-85960236","rent_info":"","time_car_num":0,"timeout_info":"","utime":1470296969000}],"errormsg":"查询附近的停车场信息成功","errorno":"0"}
	 */
	@Test
	public void read_gpspark() throws Exception{
		String url = BaseUrl+"read_gpspark.php";
		PostMethod post  = new UTF8PostMethod(url);
		//设置公共参数
		setPublicParam(post);
		
		 double lng=104.067138; //地理经度
		 double lat=30.547776;//地理纬度
		 int distance=500;//距离 单位米
		 int park_type=0;//停车场类型 0：地下室停车场 1：露天停车场 2：露天立体车库停车场
		 int type=0;//0 :按距离 1：按价格
		 String area_code="510122";//area_code;//省市县编号 510122
		 
		 post.addParameter("area_code", area_code);
		 post.addParameter("lng", lng+"");
		 post.addParameter("lat", lat+"");
		 post.addParameter("distance", distance+"");
		 post.addParameter("park_type", park_type+"");
		 post.addParameter("type", type+"");
		 
		
		sign = getSignature(Constants.SYSTEM_KEY, dtype,ui_id);
		
		
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
			log.error("read_gpspark is error"+e.getMessage(), e); 
		}finally{
			if(post!=null){
				post.releaseConnection();
				//释放链接
				HC.getHttpConnectionManager().closeIdleConnections(0);
			}
		}
	}
	
	/**
	 * 通过GPS导航获取 该经纬度范围内的停车场数据列表 ---- 车位租赁
	 * @throws Exception
	 * {"data":[{"address_name":"天府三街地铁A出口","allow_expect_time":0,"allow_revoke_time":0,"area_code":"0","camera_info":"罗技Pro C920","carport_space":0,"carport_total":100,"carport_yet":0,"copy_linkman_name":"张小强","copy_linkman_tel":"15882345447","ctime":1470296969000,"department":"四川乐库斯","enter_camera_num":1,"enter_num":1,"exit_camera_num":1,"exit_num":1,"expect_money":0,"hlc_enter_num":1,"hlc_exit_num":1,"lat":33.43144,"linkman_name":"敬小虎","linkman_tel":"15882345446","lng":109.77539000000002,"moth_car_num":0,"note":"","park_type":0,"pi_id":1,"pi_name":"四川乐库斯","pi_phone":"028-85960236","rent_info":"","time_car_num":0,"timeout_info":"","utime":1470296969000}],"errormsg":"查询附近的停车场信息成功","errorno":"0"}
	 */
	@Test
	public void read_gpspark_rent() throws Exception{
		String url = BaseUrl+"read_gpspark_rent.php";
		PostMethod post  = new UTF8PostMethod(url);
		//设置公共参数
		setPublicParam(post);
		
		 double lng=104.067138; //地理经度
		 double lat=30.547776;//地理纬度
		 int distance=1000;//距离 单位米
		 int park_type=0;//停车场类型 0：地下室停车场 1：露天停车场 2：露天立体车库停车场
		 int type=0;//0 :按距离 1：按价格
		 String area_code="510122";//area_code;//省市县编号 510122
		 
		 post.addParameter("area_code", area_code);
		 post.addParameter("lng", lng+"");
		 post.addParameter("lat", lat+"");
		 post.addParameter("distance", distance+"");
		 post.addParameter("park_type", park_type+"");
		 post.addParameter("type", type+"");
		
		sign = getSignature(Constants.SYSTEM_KEY, dtype,ui_id);
		
		
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
			log.error("read_gpspark_rent is error"+e.getMessage(), e); 
		}finally{
			if(post!=null){
				post.releaseConnection();
				//释放链接
				HC.getHttpConnectionManager().closeIdleConnections(0);
			}
		}
	}
}
