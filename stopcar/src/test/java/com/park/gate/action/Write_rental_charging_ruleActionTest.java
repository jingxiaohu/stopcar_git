
package com.park.gate.action;

import org.apache.commons.httpclient.methods.PostMethod;
import org.junit.Test;

import com.park.constants.Constants;
import stopcar.test.action.BaseActionTest;

/**
 * 记录停车场计费规则信息
 * @author jingxiaohu
 *
 */
public class Write_rental_charging_ruleActionTest extends BaseActionTest {
	/**
	 * 记录停车场计费规则信息
	 * @throws Exception
	 * {"data":{"car_code_color":0,"car_displacement":"1.6T","car_type":2,"charging":120,"charging_time":60,"ctime":1470905801464,"is_default":0,"is_time_bucket":0,"month_price":15000,"month_time":30,"note":"","permit_time":"17:00-08:30","pi_id":1,"rcr_discount":0,"rcr_id":3,"rcr_md5":"","rcr_state":0,"rcr_type":1,"start_price":500,"start_time":180,"time_bucket":"白天 9：00-12：00 每小时2元","timeout_info":"首停2小时3元，之后1元/小时","utime":1470905801464},"errorno":"0"}
	 */
	@Test
	public void charging_rule() throws Exception{
		String url = BaseUrl+"charging_rule.php";
		PostMethod post  = new UTF8PostMethod(url);
		//设置公共参数 川A4M99B
		setPublicParam(post);
		
		 
		long pi_id = 1;//场地主键ID
		int start_price=5*100;//起步价（RMB 单位 分）
		int start_time=3*60;//起步时长（单位 分钟）
		int charging=2*60;//计费价(RMB  单位分)
		int charging_time=1*60;//计费量纲时长（单位 分钟）
		int month_price=150*100;//包月价格(单位分)
		int month_time=30;//包月时长(天)
		String permit_time = "17:00-08:30";//准入时间段17:00-08:30
		String timeout_info="首停2小时3元，之后1元/小时";//超时费率(首停2小时3元，之后1元/小时)
		int rcr_type=1;//停车类型 0：普通车位停车 1：时间段包月停车
		int rcr_state=0;// 是否有效  0：有效 1：无效
		int rcr_discount=0;//是否可以使用优费券 : 0： 可以使用 1：无法使用
		String car_displacement="1.6T";//车辆排量
		int car_type=1;//车牌类型:车牌类型 0：未知车牌:、1：蓝牌小汽车、2：: 黑牌小汽车、3：单排黄牌、4：双排黄牌、 5： 警车车牌、6：武警车牌、7：个性化车牌、8：单 排军车牌、9：双排军车牌、10：使馆车牌、11： 香港进出中国大陆车牌、12：农用车牌、13：教 练车牌、14：澳门进出中国大陆车牌、15：双层 武警车牌、16：武警总队车牌、17：双层武警总 队车牌
		int car_code_color=0;//车牌颜色 0：未知、1：蓝色、2：黄色、3：白色、 4：黑色、5：绿色
		int is_time_bucket=0;//是否按时间段收费 0:不按时间段收费 1：按时间段收费
		String time_bucket="白天 9：00-12：00 每小时2元";//时间段收费  例如：白天 9：00-12：00 每小时2元 
		 String area_code="510122";//area_code;//省市县编号 510122
		 
		 post.addParameter("area_code", area_code);
		 
		sign = getSignature(Constants.SYSTEM_KEY, dtype,pi_id,start_price,charging);
		
		
		post.addParameter("pi_id", pi_id+"");
		post.addParameter("start_price", start_price+"");
		post.addParameter("start_time", start_time+"");
		post.addParameter("charging", charging+"");
		post.addParameter("charging_time", charging_time+"");
		post.addParameter("month_price", month_price+"");
		post.addParameter("month_time", month_time+"");
		post.addParameter("permit_time", permit_time+"");
		post.addParameter("timeout_info", timeout_info);
		post.addParameter("rcr_type", rcr_type+"");
		post.addParameter("rcr_state", rcr_state+"");
		post.addParameter("rcr_discount", rcr_discount+"");
		post.addParameter("car_displacement", car_displacement);
		
		
		post.addParameter("car_type", car_type+"");
		post.addParameter("car_code_color", car_code_color+"");
		post.addParameter("is_time_bucket", is_time_bucket+"");
		post.addParameter("time_bucket", time_bucket+"");
		
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
			log.error("charging_rule is error"+e.getMessage(), e); 
		}finally{
			if(post!=null){
				post.releaseConnection();
				//释放链接
				HC.getHttpConnectionManager().closeIdleConnections(0);
			}
		}
	}
	
	
	
	/**
	 * 更新停车场规则
	 * @throws Exception
	 * {"data":{"car_code_color":0,"car_displacement":"1.6T","car_type":2,"charging":120,"charging_time":60,"ctime":1470906401069,"is_default":0,"is_time_bucket":0,"month_price":15000,"month_time":30,"note":"","permit_time":"16:00-08:30","pi_id":1,"rcr_discount":0,"rcr_id":3,"rcr_md5":"6ef6ea500379f138f8c753bf9b9758ee","rcr_state":0,"rcr_type":1,"start_price":500,"start_time":180,"time_bucket":"白天 9：00-12：00 每小时2元","timeout_info":"首停2小时3元，之后1元/小时","utime":1470906401069},"errormsg":"更新成功","errorno":"0"}
	 */
	@Test
	public void update_charging_rule() throws Exception{
		String url = BaseUrl+"update_charging_rule.php";
		PostMethod post  = new UTF8PostMethod(url);
		//设置公共参数 川A4M99B
		setPublicParam(post);
		
		long rcr_id=3;//规则表主键ID
		long pi_id = 1;//场地主键ID
		int start_price=5*100;//起步价（RMB 单位 分）
		int start_time=3*60;//起步时长（单位 分钟）
		int charging=2*60;//计费价(RMB  单位分)
		int charging_time=1*60;//计费量纲时长（单位 分钟）
		int month_price=150*100;//包月价格(单位分)
		int month_time=30;//包月时长(天)
		String permit_time = "16:00-08:30";//准入时间段17:00-08:30
		String timeout_info="首停2小时3元，之后1元/小时";//超时费率(首停2小时3元，之后1元/小时)
		int rcr_type=1;//停车类型 0：普通车位停车 1：时间段包月停车
		int rcr_state=0;// 是否有效  0：有效 1：无效
		int rcr_discount=0;//是否可以使用优费券 : 0： 可以使用 1：无法使用
		String car_displacement="1.6T";//车辆排量
		int car_type=2;//车牌类型:车牌类型 0：未知车牌:、1：蓝牌小汽车、2：: 黑牌小汽车、3：单排黄牌、4：双排黄牌、 5： 警车车牌、6：武警车牌、7：个性化车牌、8：单 排军车牌、9：双排军车牌、10：使馆车牌、11： 香港进出中国大陆车牌、12：农用车牌、13：教 练车牌、14：澳门进出中国大陆车牌、15：双层 武警车牌、16：武警总队车牌、17：双层武警总 队车牌
		int car_code_color=0;//车牌颜色 0：未知、1：蓝色、2：黄色、3：白色、 4：黑色、5：绿色
		int is_time_bucket=0;//是否按时间段收费 0:不按时间段收费 1：按时间段收费
		String time_bucket="白天 9：00-12：00 每小时2元";//时间段收费  例如：白天 9：00-12：00 每小时2元 
		String area_code="510122";//area_code;//省市县编号 510122
		 
		 post.addParameter("area_code", area_code);
		 
		sign = getSignature(Constants.SYSTEM_KEY, dtype,rcr_id);
		
		post.addParameter("rcr_id", rcr_id+"");
		post.addParameter("pi_id", pi_id+"");
		post.addParameter("start_price", start_price+"");
		post.addParameter("start_time", start_time+"");
		post.addParameter("charging", charging+"");
		post.addParameter("charging_time", charging_time+"");
		post.addParameter("month_price", month_price+"");
		post.addParameter("month_time", month_time+"");
		post.addParameter("permit_time", permit_time+"");
		post.addParameter("timeout_info", timeout_info);
		post.addParameter("rcr_type", rcr_type+"");
		post.addParameter("rcr_state", rcr_state+"");
		post.addParameter("rcr_discount", rcr_discount+"");
		post.addParameter("car_displacement", car_displacement);
		
		
		post.addParameter("car_type", car_type+"");
		post.addParameter("car_code_color", car_code_color+"");
		post.addParameter("is_time_bucket", is_time_bucket+"");
		post.addParameter("time_bucket", time_bucket+"");
		
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
			log.error("update_charging_rule is error"+e.getMessage(), e); 
		}finally{
			if(post!=null){
				post.releaseConnection();
				//释放链接
				HC.getHttpConnectionManager().closeIdleConnections(0);
			}
		}
	}
}
