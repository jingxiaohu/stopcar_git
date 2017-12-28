
package com.park.action;

import java.io.File;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.multipart.FilePart;
import org.apache.commons.httpclient.methods.multipart.MultipartRequestEntity;
import org.apache.commons.httpclient.methods.multipart.Part;
import org.apache.commons.httpclient.methods.multipart.StringPart;
import org.junit.Test;
import com.park.constants.Constants;
import stopcar.test.action.BaseActionTest;

/**
 * 人脸识别图片提取和状态修改  测试
 * @author zyy
 *
 */
public class Write_UserFaceImgActionTest extends BaseActionTest {

	/**
	 * 人脸识别图片提取 测试 响应例子
	 * <pre>
	 * 	{"data":{"bank_no":"","bind_tel":"18583768030","coupon_num":0,"ctime":1481523879000,"driving_licence":"","etc_autopay":0,"eu_id":0,"eu_nd":"","lock_expect_money":0,"lock_money":0,"lock_rent_money":0,"note":"","pay_source":0,"ui_address":"","ui_age":0,"ui_autopay":0,"ui_avatar":"","ui_degree":"","ui_email":"","ui_face_feature":"","ui_face_imgs":"http://app.qc-wbo.com/file/img/avatar/2017/head18583768030_48635.png","ui_face_state":1,"ui_finger_feature":"","ui_finger_imgs":"","ui_finger_state":0,"ui_flag":1,"ui_high":0,"ui_id":40,"ui_integrity":0,"ui_intro":"","ui_level":0,"ui_logo":0,"ui_mood":"","ui_name":"","ui_nickname":"jYVqNHzu","ui_online":0,"ui_password":"a3b3eeb72be2f3cf7d4dbd5cbf9662a8","ui_rmb":0,"ui_score":0,"ui_sex":"no","ui_state":0,"ui_tel":"18583768030","ui_token":"8a6a09aa22083918b54bd978443007ca","ui_vc":0,"utime":1481523879000,"uuid":"2016121214247768"},"errorcode":"","errormsg":"人脸识别图片提取成功","errorno":"0"}
	 * </pre>
	 * @throws Exception
	 */
	@Test
	public void recode_userface_img() throws Exception{
		String url = "http://localhost:8080/v1/recode_userface_img.php";
		PostMethod post  = new UTF8PostMethod(url);
		//设置公共参数
		setPublicParam(post);
		Long ui_id = 40L;
		//脸识别图片
		File file = new File("D://temp/123.jpg");

		sign = getSignature(Constants.SYSTEM_KEY,ui_id);
		
		
		Part[] parts = {
				new StringPart("ui_id", ui_id+""),
				new StringPart("sign", sign),
		        new FilePart("avatar", file)
		    }; 
		post.setRequestEntity(new MultipartRequestEntity(parts, post.getParams()));
		int xx = HC.executeMethod(post);
		System.out.println(xx);
		try {
			String ds = post.getResponseBodyAsString();
			if(ds == null){
				System.out.println("无数据返回");
			}else{
				ds = new String(post.getResponseBodyAsString().getBytes("utf-8"), "utf-8");
				System.out.println(ds);
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("checkLoginname is error"+e.getMessage(), e); 
		}finally{
			if(post!=null){
				post.releaseConnection();
				//释放链接
				HC.getHttpConnectionManager().closeIdleConnections(0);
			}
		}
	}

	/**
	 * 修改用户 人脸识别开关状态 测试 响应例子
	 * <pre>
	 * 	{"data":{"bank_no":"","bind_tel":"18583768030","coupon_num":0,"ctime":1481523879000,"driving_licence":"","etc_autopay":0,"eu_id":0,"eu_nd":"","lock_expect_money":0,"lock_money":0,"lock_rent_money":0,"note":"","pay_source":0,"ui_address":"","ui_age":0,"ui_autopay":0,"ui_avatar":"","ui_degree":"","ui_email":"","ui_face_feature":"","ui_face_imgs":"http://app.qc-wbo.com/file/img/avatar/2017/head18583768030_70493.png","ui_face_state":2,"ui_finger_feature":"","ui_finger_imgs":"","ui_finger_state":0,"ui_flag":1,"ui_high":0,"ui_id":40,"ui_integrity":0,"ui_intro":"","ui_level":0,"ui_logo":0,"ui_mood":"","ui_name":"","ui_nickname":"jYVqNHzu","ui_online":0,"ui_password":"a3b3eeb72be2f3cf7d4dbd5cbf9662a8","ui_rmb":0,"ui_score":0,"ui_sex":"no","ui_state":0,"ui_tel":"18583768030","ui_token":"8a6a09aa22083918b54bd978443007ca","ui_vc":0,"utime":1481523879000,"uuid":"2016121214247768"},"errorcode":"","errormsg":"人脸识别开关状态修改成功","errorno":"0"}
	 * </pre>
	 * @throws Exception
	 */
	@Test
	public void update_userface_state() throws Exception{
		String url = "http://localhost:8080/v1/update_userface_state.php";
		PostMethod post  = new UTF8PostMethod(url);
		//设置公共参数
		setPublicParam(post);
		Long ui_id = 40L;
		Integer ui_face_state = 2;

		sign = getSignature(Constants.SYSTEM_KEY,ui_id,ui_face_state);

		Part[] parts = {
				new StringPart("ui_id", ui_id+""),
				new StringPart("ui_face_state", ui_face_state+""),
				new StringPart("sign", sign)
		    };
		post.setRequestEntity(new MultipartRequestEntity(parts, post.getParams()));
		int xx = HC.executeMethod(post);
		System.out.println(xx);
		try {
			String ds = post.getResponseBodyAsString();
			if(ds == null){
				System.out.println("无数据返回");
			}else{
				ds = new String(post.getResponseBodyAsString().getBytes("utf-8"), "utf-8");
				System.out.println(ds);
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("checkLoginname is error"+e.getMessage(), e);
		}finally{
			if(post!=null){
				post.releaseConnection();
				//释放链接
				HC.getHttpConnectionManager().closeIdleConnections(0);
			}
		}
	}
}
