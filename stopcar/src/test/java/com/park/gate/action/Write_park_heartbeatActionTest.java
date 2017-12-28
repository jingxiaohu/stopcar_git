
package com.park.gate.action;

import java.util.Date;

import org.apache.commons.httpclient.methods.PostMethod;
import org.junit.Test;

import com.park.constants.Constants;
import stopcar.test.action.BaseActionTest;

/**
 * 停车场心跳记录
 * @author jingxiaohu
 *
 */
public class Write_park_heartbeatActionTest extends BaseActionTest {

	/**
	 * 停车场心跳记录
	 * @throws Exception
	 * {"data":{"expect_num":1,"cancel_num":29},"errorcode":"","errormsg":"停车场心跳发送成功","errorno":"0"}
	 */
	@Test
	public void park_heartbeat() throws Exception{
		String url = BaseUrl+"park_heartbeat.php";
		PostMethod post  = new UTF8PostMethod(url);
		//设置公共参数
		setPublicParam(post);
		
		 long pi_id = 38;//场地主键ID
		 int is_rent=0;//是否有租赁包月业务 0：没有 1：有
		 String area_code="510101";//area_code;//省市县编号 510122
		 int num=50;//库存车辆数
		 int total=100;//停车位总数
		 Date time=new Date();//上次心跳时间  
		 int  park_type=0;//停车场类型 0：地下室停车场 1：露天停车场 2：露天立体车库停车场',
		 
		 
		
		sign = getSignature(Constants.SYSTEM_KEY, dtype,pi_id);
		
		post.addParameter("area_code", area_code);
		post.addParameter("pi_id", pi_id+"");
		post.addParameter("is_rent", is_rent+"");
		post.addParameter("num", num+"");
		post.addParameter("total", total+"");
		
		post.addParameter("time", time.getTime()+"");
		post.addParameter("park_type", park_type+"");
		
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
			log.error("park_heartbeat is error"+e.getMessage(), e); 
		}finally{
			if(post!=null){
				post.releaseConnection();
				//释放链接
				HC.getHttpConnectionManager().closeIdleConnections(0);
			}
		}
	}
}
