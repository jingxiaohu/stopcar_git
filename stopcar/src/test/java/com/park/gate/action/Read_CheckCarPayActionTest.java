
package com.park.gate.action;

import org.apache.commons.httpclient.methods.PostMethod;
import org.junit.Test;

import com.park.constants.Constants;
import stopcar.test.action.BaseActionTest;

/**
 * 检查某停车场某车牌号是否已经付款
 * @author jingxiaohu
 *
 */
public class Read_CheckCarPayActionTest extends BaseActionTest {
	/**
	 * 1：检查某停车场某车牌号是否已经付款
	 * {"data":{"money":0,"state":0,"car_code":"川A4M99B"},"errormsg":"查询是否已经付款成功","errorno":"0"}
	 * @throws Exception
	 */
	@Test
	public void read_checkpay() throws Exception{
		String url = BaseUrl+"read_checkpay.php";
		PostMethod post  = new UTF8PostMethod(url);
		//设置公共参数 川A4M99B
		setPublicParam(post);
		
		 
		long pi_id = 25;//场地主键ID
		String car_code = "川EC0091";//车牌号
		String area_code="510502";//省市区区域代码  四川省 成都市 龙泉驿区
		String orderid="2017010315241519";//我们的订单号  字符串
		
		sign = getSignature(Constants.SYSTEM_KEY, dtype,pi_id);
		
		post.addParameter("pi_id", pi_id+"");
		post.addParameter("car_code", car_code);
		post.addParameter("area_code", area_code);
		post.addParameter("orderid", orderid);
		
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
	
	
	
}
