
package com.park.gate.action;

import org.apache.commons.httpclient.methods.PostMethod;
import org.junit.Test;

import com.park.constants.Constants;
import stopcar.test.action.BaseActionTest;

/**
 * 道闸停车场的更新用户现金支付状态和金额
 * @author jingxiaohu
 *
 */
public class Write_CarSureOutParkActionTest extends BaseActionTest {
	/**
	 * 1：道闸停车场的更新用户现金支付状态和金额
	 * @throws Exception
	 */
	@Test
	public void pay_sure() throws Exception{
		String url = BaseUrl+"pay_sure.php";
		PostMethod post  = new UTF8PostMethod(url);
		//设置公共参数 川A4M99B
		setPublicParam(post);
		
		 
		long pi_id = 1;//场地主键ID
		String car_code = "川A4M99B";//车牌号
		String area_code="510112";//省市区区域代码  四川省 成都市 龙泉驿区
		String orderid="";//我们的订单号  字符串
		int money=500;//金额（ 单位分）
		sign = getSignature(Constants.SYSTEM_KEY, dtype,orderid,pi_id);
		
		post.addParameter("pi_id", pi_id+"");
		post.addParameter("car_code", car_code);
		post.addParameter("area_code", area_code);
		post.addParameter("orderid", orderid);
		post.addParameter("money", money+"");
		
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
