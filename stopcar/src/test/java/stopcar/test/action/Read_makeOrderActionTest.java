
package stopcar.test.action;

import org.apache.commons.httpclient.methods.PostMethod;
import org.junit.Test;

import com.park.constants.Constants;

/**
 * 准备用户手动下单的数据 （1：读取预约下单普通车位  2：读取用户停车场租赁规则信息(车位租赁)详情页  3:  用户停车缴费读取订单）
 * @author jingxiaohu
 *
 */
public class Read_makeOrderActionTest extends BaseActionTest {
	/**
	 * 1：读取用户预约下单普通车位 需要的订单准备数据
	 * @throws Exception
	 * {"data":{"area_code":"510112","car_code_color":1,"car_displacement":"1.6T","car_type":1,"charging":120,"charging_time":60,"ctime":1470710308000,"is_default":0,"is_time_bucket":0,"month_price":15000,"month_time":30,"note":"","permit_time":"17:00-08:30","pi_id":1,"rcr_discount":0,"rcr_id":1,"rcr_md5":"","rcr_state":0,"rcr_type":0,"start_price":500,"start_time":180,"time_bucket":"白天 9：00-12：00 每小时2元","timeout_info":"首停2小时3元，之后1元/小时","utime":1470710308000},"errormsg":"停车场收费规则信息成功","errorno":"0"}
	 */
	@Test
	public void read_expect_order() throws Exception{
		String url = BaseUrl+"read_expect_order.php";
		PostMethod post  = new UTF8PostMethod(url);
		//设置公共参数 川A4M99B
		setPublicParam(post);
		
		 
		long pi_id = 71;//场地主键ID
		String car_code = "辽JQQ360";//车牌号
		String area_code="510107";//省市区区域代码  四川省 成都市 龙泉驿区
		
		sign = getSignature(Constants.SYSTEM_KEY, dtype,ui_id,pi_id);
		
		post.addParameter("pi_id", pi_id+"");
		post.addParameter("car_code", car_code);
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
			log.error("read_expect_order is error"+e.getMessage(), e); 
		}finally{
			if(post!=null){
				post.releaseConnection();
				//释放链接
				HC.getHttpConnectionManager().closeIdleConnections(0);
			}
		}
	}
	
	
	
	/**
	 *  2：读取用户停车场租赁规则信息(车位租赁)详情页 
	 * @throws Exception
	 */
	@Test
	public void read_expect_rent_order() throws Exception{
		String url = BaseUrl+"read_expect_rent_order.php";
		PostMethod post  = new UTF8PostMethod(url);
		//设置公共参数 川A4M99B
		setPublicParam(post);
		
		 
		long pi_id = 26;//场地主键ID
		int pay_type = 0;//支付类型 0:手动输入密码支付 1：快捷支付（服务器可以请求第三方直接扣款）
		String car_code = "辽JQQ360";//车牌号
		String pp_versioncode="1";//当前APPSDK版本号 （内部升级版本代号）
		int money=300*100;//支付金额（单位 分）
		int month_num=2;//包月租凭月数
		String month_info="准入时段 18:00-08:00，300元/月";//包月提示信息
		String area_code="510112";//省市区区域代码  四川省 成都市 龙泉驿区
		
		sign = getSignature(Constants.SYSTEM_KEY, dtype,ui_id,pi_id);
		
		post.addParameter("pi_id", pi_id+"");
		post.addParameter("pay_type", pay_type+"");
		post.addParameter("car_code", car_code+"");
		post.addParameter("pp_versioncode", pp_versioncode+"");
		post.addParameter("money", money+"");
		post.addParameter("month_num", month_num+"");
		post.addParameter("month_info", month_info);
		post.addParameter("area_code", area_code+"");
		
		
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
			log.error("expect_rent_order is error"+e.getMessage(), e); 
		}finally{
			if(post!=null){
				post.releaseConnection();
				//释放链接
				HC.getHttpConnectionManager().closeIdleConnections(0);
			}
		}
	}
	/**
	 *  3 用户停车缴费读取订单
	 * @throws Exception
	 * {"data":{"is_show":0,"coupon":"","pay_park":{"address_name":"泸州市江阳区慈善路139号","allege_state":0,"area_code":"510502","arrive_time":1486448943000,"cancel_state":2,"car_code":"川EX6G33","charging":100,"charging_time":60,"ctime":1486448943000,"discount_money":0,"discount_name":"","discount_type":0,"expect_info":"","expect_money":0,"expect_state":0,"expect_time":0,"final_time":154,"free_minute":0,"id":7533,"is_cash":1,"is_del":0,"is_expect_deduct":0,"is_expect_outtime":0,"is_free_minute":0,"is_open":0,"is_over":0,"lat":28.89002,"leave_time":1486448943000,"lng":105.453094,"money":800,"my_order":"2017020714290427","note":"","open_time":1486448943000,"order_type":0,"other_order":"","park_type":0,"pay_source":0,"pay_type":0,"phone_type":0,"pi_id":161,"pi_name":"泸州重百地下停车场","pp_state":1,"pp_versioncode":"","pu_id":3,"pu_nd":"20170117150118","scan_time":1486448943000,"start_price":600,"start_time":60,"ui_id":0,"ui_nd":"","upc_id":0,"utime":1486457652000}},"errorcode":"","errormsg":"用户停车缴费读取订单成功","errorno":"0"}

	 */
	@Test
	public void read_pay_order() throws Exception{
		String url = BaseUrl+"read_pay_order.php";
		PostMethod post  = new UTF8PostMethod(url);
		//设置公共参数 川A4M99B
		setPublicParam(post);
		
		 
		String orderid="2017021611269526";//省市区区域代码  四川省 成都市 龙泉驿区
		
		sign = getSignature(Constants.SYSTEM_KEY, dtype,ui_id,orderid);
		
		post.addParameter("orderid", orderid+"");
		
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
			log.error("read_pay_order is error"+e.getMessage(), e); 
		}finally{
			if(post!=null){
				post.releaseConnection();
				//释放链接
				HC.getHttpConnectionManager().closeIdleConnections(0);
			}
		}
	}
	
	/**
	 *  3:用户取消预约订单
	 * @throws Exception
	 */
	@Test
	public void cancel_order() throws Exception{
		String url = BaseUrl+"cancel_order.php";
		PostMethod post  = new UTF8PostMethod(url);
		//设置公共参数 川A4M99B
		setPublicParam(post);
		
		 
		long pi_id = 1;//场地主键ID
		String area_code="510112";//省市区区域代码  四川省 成都市 龙泉驿区
		int type=1;//0：普通车位预约  1：租赁车位预约
		String orderid="b5ebc2136eaad6f8d861470970340989";//我们的订单号  字符串
		sign = getSignature(Constants.SYSTEM_KEY,dtype,ui_id,orderid,type);
		
		post.addParameter("pi_id", pi_id+"");
		post.addParameter("type", type+"");
		post.addParameter("orderid", orderid);
		post.addParameter("area_code", area_code+"");
		
		
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
			log.error("cancel_order is error"+e.getMessage(), e); 
		}finally{
			if(post!=null){
				post.releaseConnection();
				//释放链接
				HC.getHttpConnectionManager().closeIdleConnections(0);
			}
		}
	}
}
