
package com.park.gate.action;

import org.apache.commons.httpclient.methods.PostMethod;
import org.junit.Test;

import com.park.constants.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import stopcar.test.action.BaseActionTest;

/**
 * 读取停车场计费规则信息
 * @author jingxiaohu
 *
 */
public class Read_rental_charging_ruleActionTest extends BaseActionTest {
	/**
	 * 读取停车场计费规则信息
	 * @throws Exception
	 * {"data":[{"area_code":"511302","car_code_color":2,"car_displacement":"1.6","car_type":0,"charging":400,"charging_time":60,"ctime":1473389507000,"is_default":0,"is_time_bucket":1,"month_price":60000,"month_time":30,"note":"","permit_time":"0:00-24:00","pi_id":9,"rcr_discount":1,"rcr_id":11,"rcr_md5":"","rcr_state":0,"rcr_type":0,"start_price":600,"start_time":120,"time_bucket":"0:00-24:00","timeout_info":"起步2小时6.00元，之后4.00元/1小时","utime":1473389507000},{"area_code":"511302","car_code_color":1,"car_displacement":"1.6","car_type":0,"charging":400,"charging_time":60,"ctime":1473389599000,"is_default":0,"is_time_bucket":1,"month_price":30000,"month_time":30,"note":"","permit_time":"0:00-24:00","pi_id":9,"rcr_discount":1,"rcr_id":12,"rcr_md5":"","rcr_state":0,"rcr_type":0,"start_price":500,"start_time":120,"time_bucket":"0:00-24:00","timeout_info":"起步2小时5.00元，之后4.00元/1小时","utime":1473389599000}],"errorcode":"","errormsg":"读取停车场计费规则信息成功","errorno":"0"}
	 */
	@Test
	public void read_charging_rule() throws Exception{
		String url = BaseUrl+"read_charging_rule.php";
		PostMethod post  = new UTF8PostMethod(url);
		//设置公共参数
		setPublicParam(post);
		
		int pi_id=9;//停车场主键ID
		 String area_code="511302";//area_code;//省市县编号 510122
		 
		
		
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
			log.error("read_charging_rule is error"+e.getMessage(), e); 
		}finally{
			if(post!=null){
				post.releaseConnection();
				//释放链接
				HC.getHttpConnectionManager().closeIdleConnections(0);
			}
		}
	}
}
