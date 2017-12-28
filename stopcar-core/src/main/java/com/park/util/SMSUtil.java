package com.park.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import com.park.bean.Rent_defer;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.GetMethod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.park.bean.Sms_running;

/**
 * 短信发送接口
 * @author jingxiaohu
 *
 */
public class SMSUtil {
	static Logger log = LoggerFactory.getLogger(SMSUtil.class);
	private static  String ERROR_RESP = "HTTP_GET_ERROR";
	/**
	 * 序号	参数	说明
1	account	必填参数。用户账号
2	pswd	必填参数。用户密码
3	mobile	必填参数。合法的手机号码，号码间用英文逗号分隔
4	msg	必填参数。短信内容，短信内容长度不能超过585个字符。使用URL方式编码为UTF-8格式。短信内容超过70个字符（企信通是60个字符）时，会被拆分成多条，然后以长短信的格式发送。
5	needstatus	必填参数。是否需要状态报告，取值true或false，true，表明需要状态报告；false不需要状态报告
6	product	可选参数。用户订购的产品id，不填写（针对老用户）系统采用用户的默认产品，用户订购多个产品时必填，否则会发生计费错误。
7	extno	可选参数，扩展码，用户定义扩展码，3位
	 */
	//发送短信接口URL
	private static String url ="https://zapi.253.com/msg/HttpBatchSendSM";// "http://222.73.117.169/msg/HttpBatchSendSM";
	private static String account="N6682459";//用户账号
	private static String pswd = "Psab0024";//用户密码
	private static int needstatus = 1;
	private static String formatmsg_reg = "【四川琦彩科技】您验证码是：%s,欢迎您使用手机客户端,我们将竭诚为您服务!"; 
	private static String formatmsg_change_pass = "【四川琦彩科技】尊敬的用户%s,您请求的重置密码的验证码为:%s,我们将竭诚为您服务!";
	private static String formatmsg_change_tel = "【四川琦彩科技】尊敬的用户%s,您请求的修改手机号码的验证码为:%s,我们将竭诚为您服务!";
	private static String formatmsg_fingerprint = "【四川琦彩科技】您验证码是：%s,我们将竭诚为您服务!";



	//	@Test
	public void ss(){
		sendMsg("9527","15882345446","1");
	}
	/**
	 * 发送短信 
	 */
	public static Sms_running sendMsg(String validatecode,String mobile,String vclass){
		String msg = "";
		if("1".equalsIgnoreCase(vclass)){
			msg = String.format(formatmsg_reg, validatecode);
		}else if("2".equalsIgnoreCase(vclass)){
			msg = String.format(formatmsg_change_pass, mobile,validatecode);
		}else if("3".equalsIgnoreCase(vclass)){
			msg = String.format(formatmsg_change_tel, mobile,validatecode);
		}else if("4".equalsIgnoreCase(vclass)){
			msg = String.format(formatmsg_fingerprint,validatecode);
		}else if("5".equalsIgnoreCase(vclass)){
			msg = String.format(formatmsg_fingerprint,validatecode);
		}else{
			return null;
		}
		
		NameValuePair[] params = new NameValuePair[]{
				new NameValuePair("url", url),
				new NameValuePair("account", account),
				new NameValuePair("pswd", pswd),
				new NameValuePair("mobile", mobile),
				new NameValuePair("msg", msg),
				new NameValuePair("needstatus", needstatus+""),
		};
		String str = doGet(url,null,params);
		//20160112113536,0
		if(str == null || "".equalsIgnoreCase(str) || ERROR_RESP.equalsIgnoreCase(str)){
			return null;
		}else{
			Sms_running bsr = new Sms_running();
			String[] arry = str.split(",");
			if(arry != null && arry.length == 2){
				bsr.setServer_state(arry[1]);
				bsr.setSms_content(msg);
				bsr.setSms_date(new Date().getTime()+"");
				bsr.setSms_tel(mobile);
				//1:自定义短信  0:程序员自定义短信
				bsr.setSms_type(0);
				bsr.setSms_user(mobile);
				if("0".equalsIgnoreCase(arry[1])){
					//1-成功，0失败
					bsr.setSms_stat(1);
				}else{
					bsr.setSms_stat(0);
				}
				return bsr;
			}
			log.error("SMSUtil 验证码发送失败:"+str);
		}
		return null;
	}

	/**
	 * 发送短信消息
	 * @param phone
	 * @param message
	 * @return
	 */
	public static void sendMessage(String phone,String message){
		String resultStr = "";
		try{
			NameValuePair[] params = new NameValuePair[]{
					new NameValuePair("url", url),
					new NameValuePair("account", account),
					new NameValuePair("pswd", pswd),
					new NameValuePair("mobile", phone),
					new NameValuePair("msg", message),
					new NameValuePair("needstatus", needstatus+""),
			};
			resultStr = doGet(url,null,params);

			if(resultStr == null || "".equalsIgnoreCase(resultStr) || ERROR_RESP.equalsIgnoreCase(resultStr)){
				log.info("发送短信给用户[{}]失败", phone);
			}

		}catch (Exception e){
			log.error("发送短信异常",e);
		}
		log.info("发送短信结果 {}",resultStr) ;
	}

	/**
	 * 请求头集合
	 * @param url
	 * @param header
	 * @return
	 */
	private static  String doGet(String url,Map<String, String> header,NameValuePair[] params){
		HttpClient  hc = new HttpClient();
		GetMethod get =  null;
		try{
			hc.getParams().setConnectionManagerTimeout(20*1000);
			hc.getParams().setSoTimeout(20*1000);
			get =  new GetMethod(url);
			if(params!= null){
				get.setQueryString(params); 
			}
			get.setRequestHeader("Connection", "close");  
			get.addRequestHeader("Content-Type", "application/json;charset=utf-8");
			get.addRequestHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
			get.addRequestHeader("Accept-Language", "zh-cn,zh;q=0.8,en-us;q=0.5,en;q=0.3");
			if(header!=null){
				for(String h:header.keySet()){
					get.addRequestHeader(h, header.get(h));
				}
			}
			hc.executeMethod(get);
			if(get.getStatusCode() == 200){
				InputStream resStream = get.getResponseBodyAsStream();  
		        BufferedReader br = new BufferedReader(new InputStreamReader(resStream));  
		        StringBuffer resBuffer = new StringBuffer();  
		        String resTemp = "";  
		        while((resTemp = br.readLine()) != null){  
		            resBuffer.append(resTemp);  
		        }  
		        String response = resBuffer.toString();  
				//return get.getResponseBodyAsString();
		        return response;
			}else{
				log.error(url+" req error StatusCode:"+get.getStatusCode());
			}
		}catch(Exception e){
			log.error("doGet error", e);
			return ERROR_RESP;
		}finally{
			if(get!=null){
				get.releaseConnection();
				//释放链接
				hc.getHttpConnectionManager().closeIdleConnections(0);
			}
		}
		return ERROR_RESP;
	}
	
	
	public static void main(String[] args) {
		//Sms_running sms_running  = sendMsg("123456","15882345446","1");
	}
	
}
