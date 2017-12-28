/**  
* @Title: BaseActionTest.java
* @Package intimes.test.action
* @Description: TODO(用一句话描述该文件做什么)
* @author 敬小虎  
* @date 2015年3月17日 上午9:53:04
* @version V1.0  
*/ 
package stopcar.test.action;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * @ClassName: BaseActionTest
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author 敬小虎
 * @date 2015年3月17日 上午9:53:04
 *
 */
public class BaseActionTest {
	//http://139.224.29.103:8091/druid/index.html
	public Logger log = LoggerFactory.getLogger(BaseActionTest.class);
//	protected static String BaseUrl = "http://127.0.0.1:8000";
	protected static String BaseUrl = "http://127.0.0.1:8080/stopcar/v1/";
//	protected static String BaseUrl = "http://139.224.29.103:8091/v1/";
//	protected static String BaseUrl = "http://app.qc-wbo.com/v1/";
//	protected static String BaseUrl = "https://app.qc-wbo.com/v1/";
//	protected static String BaseUrl = "http://smaradio1.changhong.com/javaapp/v1/";
//	protected static String BaseUrl = "http://223.85.163.38:8093/v1/";
//	protected static String BaseUrl = "http://wubo.qc-lohas.com/v1/";
//	protected static String BaseUrl = "http://test.qc-lohas.com/v1/";
//    protected static String BaseUrl = "http://223.85.163.38:88/stopcar/v1/";
	
	/*************************公共参数设置*****************************/
	/**
	 * 系统请求接口公有参数
	 */
	public long ui_id=132;//用户ID
	public String sign;//md5散列后的值
	public int dtype=2;//从什么设备发出的请求 0:android 1:ios  2:web
	
	public void init(long ui_id,String sign,int dtype){
		this.ui_id = ui_id;
		this.dtype = dtype;
	}
	
	public void setPublicParam(PostMethod post ){
		post.addParameter("ui_id", ui_id+"");
		post.addParameter("dtype", dtype+"");
	}
	/***************************************************/
	/**
	 * true：合法请求  false ：非法请求
	 * @return
	 */
	public String getSignature(String dev_server_secret,Object ... params){
		if(params.length == 0 ){
			return null;
		}
		List<String> list = new ArrayList<String>();
		for (Object param : params) {
			if(param != null){
				list.add(param.toString());
			}
		}
		return getSignature(list,dev_server_secret);
	}
	/**
     * 签名生成算法
     *
     * @return 签名
     * @throws IOException
     */
    public static String getSignature(Map<String, String> params, String dev_server_secret,String separator){
        // 先将参数以其参数名的字典序升序进行排序
        Map<String, String> sortedParams = new TreeMap<String, String>(params);
 
        Set<Map.Entry<String, String>> entrys = sortedParams.entrySet();
        // 遍历排序后的字典，将所有参数按"key=value"格式拼接在一起
        StringBuilder basestring = new StringBuilder();
        for (Map.Entry<String, String> param : entrys) {
            basestring.append(param.getKey()).append(param.getValue()).append(separator);
        }
        basestring.append(dev_server_secret);
        System.out.println(basestring.toString());
        //对待签名串求签
        return DigestUtils.md5Hex(basestring.toString());
    }
    /**
     * 签名生成算法
     *
     * @return 签名
     * @throws IOException
     */
    public  String getSignature(List<String> params, String dev_server_secret){
        // 先将参数以其参数名的字典序升序进行排序
    	Collections.sort(params);
        // 遍历排序后的字典，将所有参数按"key=value"格式拼接在一起
        StringBuilder basestring = new StringBuilder();
        for (Object param : params) {
            basestring.append(param);
        }
        basestring.append(dev_server_secret);
        System.out.println(basestring.toString());
        //对待签名串求签
        return DigestUtils.md5Hex(basestring.toString());
    }
	
	 //Inner class for UTF-8 support   
    public static class  UTF8PostMethod  extends  PostMethod {   
        public  UTF8PostMethod ( String url ){   
            super ( url ) ;   
        }   
        @Override   
        public  String getRequestCharSet () {   
            //return super.getRequestCharSet();   
            return  "UTF-8" ;   
        }   
    }
	protected HttpClient HC=new HttpClient();
	public static void main(String[] args) {
//		System.out.println(RandomStringUtils.random(32, true, true));
//		String sign = RequestUtil.MD5("1{\"userid\":178,\"price\":12,\"orderid\":\"456aeacc8732be92cbb1436232968322\",\"time\":\"2015-07-07 09:36:38\",\"cpkeyword\":\"999-androidaibei_178-20150707093901\"}ac09f7fe949a83251ef1433320737087");
//		System.out.println(sign);
//		System.out.println("010100031000200803040174".length());
		double price = 2.0123213;
		DecimalFormat decimalFormat=new DecimalFormat("0.00");//构造方法的字符格式这里如果小数不足2位,会以0补足.
		String p = decimalFormat.format(price);//format 返回的是字符串
		System.out.println(p);
	}
}
