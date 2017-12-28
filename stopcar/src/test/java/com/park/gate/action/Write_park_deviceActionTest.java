
package com.park.gate.action;

import org.apache.commons.httpclient.methods.PostMethod;
import org.junit.Test;

import com.park.constants.Constants;
import stopcar.test.action.BaseActionTest;

/**
 * 记录场地出入口设备对应关系信息
 * @author jingxiaohu
 *
 */
public class Write_park_deviceActionTest extends BaseActionTest {
	/**
	 * 记录场地出入口设备对应关系信息
	 * @throws Exception
	 * {"data":{"camera":"罗技Pro C920","camera_mac":"00:b7:00:00:54:33","ctime":1470297705114,"in_out":"enter","in_out_code":"A","note":"","pd_id":1,"pd_md5":"1d767d7b28df5fc8ba3ec05743081857","pi_id":1,"signo_name":"乐库斯闸道","solid_garage_mac":"00:b7:00:c2:5b:33","solid_garage_sn":"00000000000001","utime":1470297705114},"errorno":"0"}
	 */
	@Test
	public void record_park_device() throws Exception{
		String url = BaseUrl+"record_park_device.php";
		PostMethod post  = new UTF8PostMethod(url);
		//设置公共参数 川A4M99B
		setPublicParam(post);
		
		 long pi_id=1;//场地主键ID
		 String  in_out="enter";//出口或者入口 入口：enter  出口：exit
		 String in_out_code="A";//出入口编号  例如(A出口 B入口)
		 String camera="罗技Pro C920";//摄像头名称
		 String camera_mac="00:b7:00:00:54:33";//摄像头MAC
		 String signo_name="乐库斯闸道";//道闸名称
		 String solid_garage_mac="00:b7:00:c2:5b:33";//立体车库设备MAC
		 String solid_garage_sn="00000000000001";//立体车库设备编号
		 String area_code="510122";//area_code;//省市县编号 510122
		 
		
		
		sign = getSignature(Constants.SYSTEM_KEY, dtype,pi_id,in_out);
		
		 post.addParameter("area_code", area_code);
		post.addParameter("pi_id", pi_id+"");
		post.addParameter("in_out", in_out+"");
		post.addParameter("in_out_code", in_out_code);
		post.addParameter("camera", camera);
		post.addParameter("camera_mac", camera_mac);
		post.addParameter("signo_name", signo_name);
		post.addParameter("solid_garage_mac", solid_garage_mac);
		post.addParameter("solid_garage_sn", solid_garage_sn);

		
		
		
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
			log.error("record_park_device is error"+e.getMessage(), e); 
		}finally{
			if(post!=null){
				post.releaseConnection();
				//释放链接
				HC.getHttpConnectionManager().closeIdleConnections(0);
			}
		}
	}
}
