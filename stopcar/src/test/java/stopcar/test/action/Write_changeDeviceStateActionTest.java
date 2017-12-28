package stopcar.test.action;

import org.apache.commons.httpclient.methods.PostMethod;
import org.junit.Test;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.park.constants.Constants;
/**
 * 修改设备状态
 * @ClassName:  ChangeDeviceActionTest   
 * @Description:TODO  
 * @author: xxy 
 * @date:   2017年5月19日 上午10:53:20   
 *{"data":"","errorcode":"","errormsg":"修改成功","errorno":"0"}
 */
public class Write_changeDeviceStateActionTest extends BaseActionTest {
	 Gson gson = new GsonBuilder().enableComplexMapKeySerialization() .create();  
	
	@Test
	public void updateState() throws Exception{
		String url = BaseUrl+"change_magnetic_device_state.php";
		PostMethod post  = new UTF8PostMethod(url);
		//设置公共参数 川A4M99B
		setPublicParam(post);
		
		 
		long piId = 130;//场地主键ID
		String areaCode="510107";//省市区区域代码  四川省 成都市 龙泉驿区
		
		int carPortYet = 50;//临停已停车位
		int carPortSpace = 50;//临停空车位个数
		int carPortTotal = 100;//临停总车位个数
		
		String car_dev_num = "2";//车位设备编码
		String android_dev_num = "abcd";////android板子设备编码
		Integer state =1;//车位设备状态（0：无车 1：有车 2：故障）
		
		
		sign = getSignature(Constants.SYSTEM_KEY, piId,areaCode,car_dev_num,carPortYet,carPortSpace,carPortTotal,state,android_dev_num);
		
		post.addParameter("pi_id", piId+"");
		post.addParameter("area_code", areaCode);
		post.addParameter("car_port_yet", carPortYet+"");
		post.addParameter("car_port_space", carPortSpace+"");
		post.addParameter("car_port_total", carPortTotal+"");
		post.addParameter("car_dev_num", car_dev_num+"");
		post.addParameter("state", state+"");
		post.addParameter("android_dev_num", android_dev_num+"");
		
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
