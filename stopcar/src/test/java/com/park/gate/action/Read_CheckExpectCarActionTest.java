
package com.park.gate.action;

import org.apache.commons.httpclient.methods.PostMethod;
import org.junit.Test;

import com.park.constants.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import stopcar.test.action.BaseActionTest;

/**
 * 检查该车牌号是否是预约车
 * @author jingxiaohu
 *
 */
public class Read_CheckExpectCarActionTest extends BaseActionTest {
	/**
	 * 检查该车牌号是否是预约车
	 * @throws Exception
	 * {"data":true,"errorcode":"","errormsg":"有该车辆的预约订单信息","errorno":"0"}
	 */
	@Test
	public void check_expectcar() throws Exception{
		String url = BaseUrl+"check_expectcar.php";
		PostMethod post  = new UTF8PostMethod(url);
		//设置公共参数
		setPublicParam(post);
		
		
		 long pi_id = 57;//场地主键ID
		 String car_code = "辽JQQ360";//车牌号 (第一期：车牌号---对应一个人)
		 int car_type = 1;//车牌类型:车牌类型 0：未知车牌:、1：蓝牌小汽车、2：: 黑牌小汽车、3：单排黄牌、4：双排黄牌、 5： 警车车牌、6：武警车牌、7：个性化车牌、8：单 排军车牌、9：双排军车牌、10：使馆车牌、11： 香港进出中国大陆车牌、12：农用车牌、13：教 练车牌、14：澳门进出中国大陆车牌、15：双层 武警车牌、16：武警总队车牌、17：双层武警总 队车牌
		 int car_code_color = 1;//车牌颜色 0：未知、1：蓝色、2：黄色、3：白色、 4：黑色、5：绿色 
		 String area_code="510101";//area_code;//省市县编号 510122
		
		post.addParameter("area_code", area_code);
		post.addParameter("pi_id", pi_id+"");
		post.addParameter("car_code", car_code);
		post.addParameter("car_type", car_type+"");
		post.addParameter("car_code_color", car_code_color+"");
		
		
		
		
		sign = getSignature(Constants.SYSTEM_KEY, dtype,pi_id,car_code);
		
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
			log.error("check_expectcar is error"+e.getMessage(), e); 
		}finally{
			if(post!=null){
				post.releaseConnection();
				//释放链接
				HC.getHttpConnectionManager().closeIdleConnections(0);
			}
		}
	}
}
