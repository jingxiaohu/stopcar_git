
package com.park.gate.action;

import org.apache.commons.httpclient.methods.PostMethod;
import org.junit.Test;

import com.park.constants.Constants;
import stopcar.test.action.BaseActionTest;

/**
 * 扫描到车辆出库扣费
 * @author jingxiaohu
 *
 */
public class Write_UpdateOrderStateActionTest extends BaseActionTest {
	/**
	 * 扫描到车辆出库扣费
	 */
	@Test
	public void upate_order_state() throws Exception{
		String url = BaseUrl+"upate_order_state.php";
		PostMethod post  = new UTF8PostMethod(url);
		//设置公共参数 川A4M99B
		setPublicParam(post);
		
		 
		long pi_id = 39;//场地主键ID
		String car_code="京A59646";
		String area_code="510108";//省市区区域代码  四川省 成都市 龙泉驿区
		String orderid="2017010916533474";//我们的订单号  字符串
		int type = 0;//0：预约   1：取消预约  2：租赁
		int state=0;//处理结果状态 0:成功 1：失败
		sign = getSignature(Constants.SYSTEM_KEY, dtype,pi_id,orderid,type);
		
		post.addParameter("pi_id", pi_id+"");
		post.addParameter("car_code", car_code+"");
		post.addParameter("orderid", orderid);
		post.addParameter("type", type+"");
		post.addParameter("state", state+"");
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
	
	
	
}
