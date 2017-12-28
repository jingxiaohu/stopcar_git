
package com.park.gate.action;

import org.apache.commons.httpclient.methods.PostMethod;
import org.junit.Test;

import com.park.constants.Constants;
import stopcar.test.action.BaseActionTest;

/**
 * 记录停车场信息
 * @author jingxiaohu
 *
 */
public class Write_parkinfoActionTest extends BaseActionTest {
	/**
	 * 登记停车场信息
	 * @throws Exception
	 * {"data":{"address_name":"新光华路","admin_id":0,"allow_expect_time":120,"allow_revoke_time":300,"area_code":"510122","camera_info":"罗技Pro C920","carport_space":0,"carport_total":100,"carport_yet":0,"copy_linkman_name":"李小强","copy_linkman_tel":"15882345447","ctime":1495865272571,"department":"四川乐库斯","enter_camera_num":1,"enter_num":1,"exit_camera_num":1,"exit_num":1,"expect_car_num":0,"expect_money":500,"hlc_enter_num":1,"hlc_exit_num":1,"is_expect":0,"is_fault":0,"is_rent":0,"lat":30.546973,"linkman_name":"敬小虎","linkman_tel":"15882345446","lng":104.065845,"loginname":"","mac":"","money":0,"month_price":0,"moth_car_num":20,"moth_car_num_space":0,"note":"","park_type":0,"password":"","pda_permit_time":"","pi_id":198,"pi_name":"南天立交","pi_phone":"028-85960236","pi_state":0,"pu_id":0,"pu_nd":"","rent_info":"","roadside_type":0,"special_ip":"192.168.1.1","time_car_num":0,"time_car_num_space":0,"timeout_info":"","upload_source":0,"utime":1495865272571},"errorcode":"","errorno":"0"}
	 */
	@Test
	public void parkinfo() throws Exception{
		String url = BaseUrl+"record_parkinfo.php";
		PostMethod post  = new UTF8PostMethod(url);
		//设置公共参数 川A4M99B
		setPublicParam(post);
		
		 String name="南天立交";//场地名称
		 String address_name="新光华路";//停车场地理街道名称
		 double lng=104.065845;//104.067138; //地理经度
		 double lat=30.546973;//30.547776;//地理纬度
		 String linkman_name="敬小虎";//场地联系人姓名
		 String linkman_tel="15882345446";//联系人电话
		 String copy_linkman_name="李小强";//备用联系人姓名
		 String copy_linkman_tel="15882345447";//备用联系人手机
		 String pi_phone="028-85960236";//场地座机号码
		 String department="四川乐库斯";//负责单位 
		 int carport_total=100;//场地车位总数
		 int enter_num=1;//场地入口数量
		 int exit_num=1;//场地出口数量
		 int hlc_enter_num=1;//场地入口道闸数量
		 int hlc_exit_num=1;//场地出口道闸数量
		 int enter_camera_num=1;//场地入口摄像头数量
		 int exit_camera_num=1;//场地出口摄像头数量
		 String camera_info="罗技Pro C920";//场地摄像头信息
		 String park_type="0";//停车场类型 0：地下室停车场 1：露天停车场 2：露天立体车库停车场
		 String area_code="510122";//area_code;//省市县编号 510122
		 
		 int  moth_car_num=20;//租赁时间段包月车位总个数
		 int is_expect=0;//是否开启预约 0:不开启 1：开启
		 int  expect_money=5*100;//每小时预约费用（单位分）
		 int  allow_revoke_time=5*60;//允许预约撤单时间(单位分钟)----*暂时不开启
		 int  allow_expect_time=2*60;//允许预约时间最大时长(单位分钟)----*暂时不开启 
		
		sign = getSignature(Constants.SYSTEM_KEY, dtype,park_type);
		
		
		post.addParameter("name", name);
		post.addParameter("address_name", address_name);
		post.addParameter("lng", lng+"");
		post.addParameter("lat", lat+"");
		post.addParameter("linkman_name", linkman_name);
		post.addParameter("linkman_tel", linkman_tel);
		post.addParameter("copy_linkman_name", copy_linkman_name);
		post.addParameter("copy_linkman_tel", copy_linkman_tel);
		post.addParameter("pi_phone", pi_phone);
		post.addParameter("department", department);
		post.addParameter("carport_total", carport_total+"");
		post.addParameter("enter_num", enter_num+"");
		
		
		post.addParameter("exit_num", exit_num+"");
		post.addParameter("hlc_enter_num", hlc_enter_num+"");
		post.addParameter("hlc_exit_num", hlc_exit_num+"");
		post.addParameter("enter_camera_num", enter_camera_num+"");
		post.addParameter("exit_camera_num", exit_camera_num+"");
		post.addParameter("camera_info", camera_info+"");
		post.addParameter("park_type", park_type+"");
		post.addParameter("area_code", area_code);
		
		
		post.addParameter("moth_car_num", moth_car_num+"");
		post.addParameter("is_expect", is_expect+"");
		post.addParameter("expect_money", expect_money+"");
		post.addParameter("allow_revoke_time", allow_revoke_time+"");
		post.addParameter("allow_expect_time", allow_expect_time+"");
		post.addParameter("special_ip","192.168.1.1");
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
			log.error("parkinfo is error"+e.getMessage(), e); 
		}finally{
			if(post!=null){
				post.releaseConnection();
				//释放链接
				HC.getHttpConnectionManager().closeIdleConnections(0);
			}
		}
	}
}
