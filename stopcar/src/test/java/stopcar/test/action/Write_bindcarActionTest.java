
package stopcar.test.action;

import java.io.File;

import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.multipart.FilePart;
import org.apache.commons.httpclient.methods.multipart.MultipartRequestEntity;
import org.apache.commons.httpclient.methods.multipart.Part;
import org.apache.commons.httpclient.methods.multipart.StringPart;
import org.junit.Test;

import com.park.constants.Constants;

/**
 * 用户添加绑定车牌号 用户更新车辆信息
 * @author jingxiaohu
 *
 */
public class Write_bindcarActionTest extends BaseActionTest {
	/**
	 * 用户添加绑定车牌号
	 * @throws Exception
	 * {"data":"","errormsg":"绑定成功","errorno":"0"}
	 */
	@Test
	public void bindcar() throws Exception{
		String url = BaseUrl+"bindcar.php";
		PostMethod post  = new UTF8PostMethod(url);
		//设置公共参数
		setPublicParam(post);
		
		 String car_code = "辽JQQ360";//车牌号 (第一期：车牌号---对应一个人)
		 int act = 1;// 1:添加车牌 2：删除车牌
		
		sign = getSignature(Constants.SYSTEM_KEY, dtype,ui_id,car_code,act);
		
		
		post.addParameter("car_code", car_code);
		post.addParameter("act", act+"");
		
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
			log.error("bindcar is error"+e.getMessage(), e); 
		}finally{
			if(post!=null){
				post.releaseConnection();
				//释放链接
				HC.getHttpConnectionManager().closeIdleConnections(0);
			}
		}
	}
	
	
	
	/**
	 * 用户更新车辆信息
	 * @throws Exception
	 * {"data":{"car_brand":"川A4M99B","car_code":"川A4M99B","ctime":1470710253000,"is_default":0,"note":"","run_url":"http://139.224.29.103/file/img/lience/2016/0fbd30b39ae04e029c3b3a14f0d4b4f0.jpg","uc_color":0,"uc_id":1,"ui_id":1,"utime":1470710253000},"errormsg":"更新用户车辆信息成功","errorno":"0"}
	 */
	@Test
	public void update_car() throws Exception{
		String url = BaseUrl+"update_car.php";
		PostMethod post  = new UTF8PostMethod(url);
		//设置公共参数
		setPublicParam(post);
		
		String car_code = "辽JQQ361";//车牌号 (第一期：车牌号---对应一个人)
		long uc_id=496;//表主键ID
//		File lience= new File("D://temp/lience.png");//行驶证照片
//		String lienceFileName="lience.jpg.";//行驶证照片 文件名称
	    //提交过来的file的MIME类型
//		String lienceContentType="jpg";
//		String car_brand="";//车辆品牌
//		int uc_color=8;//车辆颜色:0 未定  1：黑色 2：白色 3：银色 4：金色 5：红色 6 ：黄色 7：绿色 8：蓝色 9：橙色 10：灰色
		
		sign = getSignature(Constants.SYSTEM_KEY, dtype,ui_id);
		
		
		
		post.addParameter("sign", sign);
		
		
		Part[] parts = {
		    	 //new StringPart("text_content", "大叔大叔大叔大叔的萨达十大萨达十大","utf-8"),
				//new StringPart("status", URLEncoder.encode(status, "utf-8"),
				new StringPart("ui_id", ui_id+""),
				new StringPart("dtype", dtype+""),
				new StringPart("uc_id", uc_id+""),
				new StringPart("car_code",car_code,"utf-8"),
//				new StringPart("car_brand",car_brand,"utf-8"),
//		    	new StringPart("uc_color", uc_color+""),
		    	new StringPart("sign", sign)
//		        new FilePart("lience", lience)
		    }; 
		post.setRequestEntity(new MultipartRequestEntity(parts, post.getParams()));
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
			log.error("bindcar is error"+e.getMessage(), e); 
		}finally{
			if(post!=null){
				post.releaseConnection();
				//释放链接
				HC.getHttpConnectionManager().closeIdleConnections(0);
			}
		}
	}
}
