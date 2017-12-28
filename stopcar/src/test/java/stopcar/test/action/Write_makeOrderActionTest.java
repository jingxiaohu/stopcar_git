
package stopcar.test.action;

import org.apache.commons.httpclient.methods.PostMethod;
import org.junit.Test;

import com.park.constants.Constants;

/**
 * 用户手动下单 （1：预约下单普通车位  2：下单租赁包月车位 3:用户取消预约订单）
 * @author jingxiaohu
 *
 */
public class Write_makeOrderActionTest extends BaseActionTest {
	/**
	 * 1：预约下单普通车位
	 * @throws Exception
	 * {"data":{"address_name":"","allege_state":0,"area_code":"510112","arrive_time":1470972770073,"cancel_state":0,"car_code":"辽JQQ360","charging":0,"charging_time":0,"ctime":1470972770073,"discount_money":0,"discount_name":"","discount_type":0,"expect_info":"","expect_money":500,"expect_time":120,"final_time":0,"id":0,"is_del":0,"is_expect_deduct":0,"is_expect_outtime":0,"leave_time":1470972770073,"money":0,"my_order":"8a1ace9c0a9fc18de521470972770074","note":"用户预约下单","order_type":0,"other_order":"","pay_source":0,"pay_type":0,"phone_type":0,"pi_id":1,"pp_state":0,"pp_versioncode":"1","start_price":0,"start_time":0,"ui_id":1,"upc_id":0,"utime":1470972770073},"errorno":"0"}
	 */
	@Test
	public void expect_order() throws Exception{
		String url = BaseUrl+"expect_order.php";
		PostMethod post  = new UTF8PostMethod(url);
		//设置公共参数 川A4M99B
		setPublicParam(post);
		
		 
		long pi_id = 130;//场地主键ID
		int pay_type = 1;//支付类型 0:手动输入密码支付 1：快捷支付（服务器可以请求第三方直接扣款）
		String car_code = "沪B78B03";//车牌号
		String pp_versioncode="1";//当前APPSDK版本号 （内部升级版本代号）
		String expect_info="";//预约提示信息
		int expect_money=100;//预约价格（ 单位分）
		int expect_time=15;//预约时间 单位分钟
		String area_code="510107";//省市区区域代码  四川省 成都市 龙泉驿区
		
		sign = getSignature(Constants.SYSTEM_KEY, dtype,ui_id,car_code);
		
		post.addParameter("pi_id", pi_id+"");
		post.addParameter("pay_type", pay_type+"");
		post.addParameter("car_code", car_code+"");
		post.addParameter("pp_versioncode", pp_versioncode+"");
		post.addParameter("expect_info", expect_info+"");
		post.addParameter("expect_money", expect_money+"");
		post.addParameter("expect_time", expect_time+"");
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
			log.error("expect_order is error"+e.getMessage(), e); 
		}finally{
			if(post!=null){
				post.releaseConnection();
				//释放链接
				HC.getHttpConnectionManager().closeIdleConnections(0);
			}
		}
	}
	
	
	
	/**
	 *  2：下单租赁包月车位 
	 * @throws Exception
	 * {"data":{"address_name":"","allege_state":0,"area_code":"510112","base_rate":"","car_code":"辽JQQ360","ctime":1470992737824,"discount_money":0,"discount_name":"","discount_type":0,"end_time":1476176737824,"final_time":0,"id":0,"is_del":0,"money":30000,"month_info":"准入时段 18:00-08:00，300元/月","month_num":2,"my_order":"734882b6ac8cc7c5fe71470992737825","note":"用户预约下单","order_type":0,"other_order":"","outtime_charge_minute":0,"outtime_charge_money":0,"outtime_minute":0,"outtime_money":0,"outtime_other_order":"","outtime_paystate":0,"outtime_paytime":1470992737824,"outtime_rate":"","outtime_start_minute":0,"outtime_start_price":0,"outtime_time":1470992737824,"pay_source":0,"pay_type":0,"phone_type":0,"pi_id":1,"pp_state":0,"pp_versioncode":"1","start_time":1470992737824,"ui_id":1,"upc_id":0,"utime":1470992737824},"errorno":"0"}
	 */
	@Test
	public void pay_rent_order() throws Exception{
		String url = BaseUrl+"pay_rent_order.php";
		PostMethod post  = new UTF8PostMethod(url);
		//设置公共参数 川A4M99B
		setPublicParam(post);
		
		 
		long pi_id = 1;//场地主键ID
		int pay_source=4;//支付类型 1:支付l宝 2：微信 3：银联  4：钱包
		int pay_type = 0;//支付类型 0:手动输入密码支付 1：快捷支付（服务器可以请求第三方直接扣款）
		String car_code = "辽JQQ360";//车牌号
		String pp_versioncode="1";//当前APPSDK版本号 （内部升级版本代号）
		int money=300*100;//支付金额（单位 分）
		int month_num=2;//包月租凭月数
		String month_info="准入时段 18:00-08:00，300元/月";//包月提示信息
		String area_code="510112";//省市区区域代码  四川省 成都市 龙泉驿区
		
		String permit_time="17:00-08:30";//准入时间段（17:00-08:30）
		int is_24hours=1;//是否是24小时包月  0:不是24小时包月  1：是24小时包月
		
		
		
		sign = getSignature(Constants.SYSTEM_KEY, dtype,ui_id,car_code);
		
		post.addParameter("pi_id", pi_id+"");
		post.addParameter("pay_source", pay_source+"");
		post.addParameter("pay_type", pay_type+"");
		post.addParameter("car_code", car_code+"");
		post.addParameter("pp_versioncode", pp_versioncode+"");
		post.addParameter("money", money+"");
		post.addParameter("month_num", month_num+"");
		post.addParameter("month_info", month_info);
		
		post.addParameter("permit_time", permit_time+"");
		post.addParameter("is_24hours", is_24hours+"");
		
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
	 * 3：用户普通停车位直接正式付款下单(对应E泊 停车缴费)
	 * @throws Exception
	 * {"data":"","errormsg":"用户停车缴费下单信息更新成功","errorno":"0"}
	 */
	@Test
	public void pay_order() throws Exception{
		String url = BaseUrl+"pay_order.php";
		PostMethod post  = new UTF8PostMethod(url);
		//设置公共参数 川A4M99B
		setPublicParam(post);
		
		 //dtype => [ 0 ] orderid => [ 2016120209492908 ] pay_source => [ 4 ] sign => [ b48beda4567b5343b82049d21e21b24f ] ui_id => [ 4 ] upc_id => [ 0 ] 
		String orderid="2017021614286529";//我们的订单号  字符串
		long upc_id=5;//用户优惠券表主键ID
//		int discount_money=5*100;//抵扣金额
//		int discount_type=0;//优惠券类型 0:金额券 1：折扣券
//		String discount_name="停车优惠券2元";//抵扣优惠券名称
		
		int pay_source=4;//支付类型 1:支付宝  2：微信  3：银联  4：钱包 5:龙支付
//		int money=500;//支付金额（单位 分）

		
		sign = getSignature(Constants.SYSTEM_KEY, dtype,ui_id,orderid);
		
		post.addParameter("orderid", orderid+"");
		post.addParameter("upc_id", upc_id+"");
//		post.addParameter("discount_money", discount_money+"");
//		post.addParameter("discount_type", discount_type+"");
//		post.addParameter("discount_name", discount_name+"");
		post.addParameter("pay_source", pay_source+"");
//		post.addParameter("money", money+"");
		post.addParameter("money", "未知");
		
		
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
			log.error("pay_order is error"+e.getMessage(), e); 
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
	 * {"data":"","errormsg":"取消预约订单成功","errorno":"0"}
	 */
	@Test
	public void cancel_order() throws Exception{
		String url = BaseUrl+"cancel_order.php";
		PostMethod post  = new UTF8PostMethod(url);
		//设置公共参数 川A4M99B
		setPublicParam(post);
		
		 
		long pi_id = 1;//场地主键ID
		String area_code="510112";//省市区区域代码  四川省 成都市 龙泉驿区
		int type=0;//0：普通车位预约  1：租赁车位预约
		String orderid="8a1ace9c0a9fc18de521470972770074";//我们的订单号  字符串
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
	
	
	/**
	 *  3:用户删除订单
	 * @throws Exception
	 */
	@Test
	public void delete_order() throws Exception{
		String url = BaseUrl+"delete_order.php";
		PostMethod post  = new UTF8PostMethod(url);
		//设置公共参数 川A4M99B
		setPublicParam(post);
		
		 
		long pi_id = 1;//场地主键ID
		String area_code="510112";//省市区区域代码  四川省 成都市 龙泉驿区
		int type=0;//0：普通车位  1：租赁车位
		String orderid="c5c89f00c76bdca9b7e1470712132761";//我们的订单号  字符串
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
			log.error("delete_order is error"+e.getMessage(), e); 
		}finally{
			if(post!=null){
				post.releaseConnection();
				//释放链接
				HC.getHttpConnectionManager().closeIdleConnections(0);
			}
		}
	}
}
