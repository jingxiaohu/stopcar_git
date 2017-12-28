/**
  * @FileName: HttpTool.java
  * @Author Richard
  * @Description:
  * @Date 2015年3月25日 下午10:51:08
  * @CopyRight CNP Corporation
  */
package com.weixin.config;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HttpTool {
	static Logger log = LoggerFactory.getLogger(HttpTool.class);
	protected static HttpClient HC = new HttpClient();
	private HttpTool() {
	}

	private static final int CONNECT_TIMEOUT_LENGTH = 10000;
	
	private static final int READ_TIMEOUT_LENGTH = 5000;

	private static final Logger LOG = LoggerFactory.getLogger(HttpTool.class);

	/**
	 * 向指定URL发送GET方法的请求
	 * 
	 * @param url
	 *            发送请求的URL
	 * @param param
	 *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
	 * @return URL 所代表远程资源的响应结果
	 */
	public static String sendGet(String url, String param) {
		long startTime=System.currentTimeMillis();   //获取开始时间   
		StringBuffer result = new StringBuffer();
		BufferedReader in = null;
		try {
			StringBuffer urlNameString = new StringBuffer(url);
			urlNameString.append("?");
			urlNameString.append(param);
			URL realUrl = new URL(urlNameString.toString());
			// 打开和URL之间的连接
			URLConnection connection = realUrl.openConnection();
			// 设置通用的请求属性
			connection.setRequestProperty("accept", "*/*");
			connection.setRequestProperty("connection", "Keep-Alive");
			connection.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			// 建立实际的连接
			connection.connect();
			// 定义 BufferedReader输入流来读取URL的响应
			in = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
			String line;
			while ((line = in.readLine()) != null) {
				result.append(line);
			}
		} catch (Exception e) {
			LOG.info("发送GET请求出现异常！", e);
		}
		// 使用finally块来关闭输入流
		finally {
			try {
				if (in != null) {
					in.close();
				}
			} catch (Exception e2) {
				LOG.info("发送GET请求出现异常！", e2);
			}
		}
		long endTime=System.currentTimeMillis(); //获取结束时间   
		LOG.info("Invoke method sendGet Total time consuming "+(endTime-startTime)+"ms");
		return result.toString();
	}

	/**
	 * 向指定 URL 发送POST方法的请求
	 * 
	 * @param url
	 *            发送请求的 URL
	 * @param param
	 *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
	 * @return 所代表远程资源的响应结果
	 */
	public static String sendPost(String url, String param) {
		long startTime=System.currentTimeMillis();   //获取开始时间   
		PrintWriter out = null;
		BufferedReader in = null;
		StringBuffer result = new StringBuffer();
		try {
			if (-1 != param.indexOf("{") && -1 != param.indexOf("}")) {
				param = param.replace("{", "");
				param = param.replace("}", "");
				param = param.replace(":", "=");
				param = param.replace(",", "&");
				param = param.replace("\"", "");
			}
			param = new String(param.getBytes(), "UTF-8");
			URL realUrl = new URL(url);
			// 打开和URL之间的连接
			URLConnection conn = realUrl.openConnection();
			// 设置通用的请求属性
			conn.setRequestProperty("accept", "*/*");
			conn.setRequestProperty("connection", "Keep-Alive");
			conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			conn.setConnectTimeout(CONNECT_TIMEOUT_LENGTH);
			conn.setReadTimeout(READ_TIMEOUT_LENGTH);
			// 发送POST请求必须设置如下两行
			conn.setDoOutput(true);
			conn.setDoInput(true);
			// 获取URLConnection对象对应的输出流
			out = new PrintWriter(conn.getOutputStream());
			// 发送请求参数
			out.print(param);
			// flush输出流的缓冲
			out.flush();
			// 定义BufferedReader输入流来读取URL的响应
			in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
			String line;
			while ((line = in.readLine()) != null) {
				result.append(line);
			}
		} catch (Exception e) {
			LOG.info("发送 POST请求出现异常！", e);
		}
		// 使用finally块来关闭输出流、输入流
		finally {
			try {
				if (out != null) {
					out.close();
				}
				if (in != null) {
					in.close();
				}
			} catch (IOException ex) {
				LOG.info("发送 POST请求出现异常！", ex);
			}
		}
		long endTime=System.currentTimeMillis(); //获取结束时间   
		LOG.info("Invoke method sendPost Total time consuming "+(endTime-startTime)+"ms");
		return result.toString();
	}
	/**
	 * 向指定 URL 发送POST方法的请求
	 * 
	 * @param url
	 *            发送请求的 URL
	 * @param param
	 *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
	 * @return 所代表远程资源的响应结果
	 */
	@SuppressWarnings("deprecation")
	public static String sendPost2(String url, String param) {
		PostMethod post  = new UTF8PostMethod(url);
		HC.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, "UTF-8");
		post.setRequestBody(param);
		try {
			int xx = HC.executeMethod(post);
			log.info("*请求状态code="+xx);
			String ds = post.getResponseBodyAsString();
			if(ds == null){
				log.info("无数据返回");
			}else{
				return ds;
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("read_bindcar is error"+e.getMessage(), e); 
		}finally{
			if(post!=null){
				post.releaseConnection();
				//释放链接
				HC.getHttpConnectionManager().closeIdleConnections(0);
			}
		}
		return null;
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
    
    
    /**
     * 获取流中的数据
     */
    public static String readStreamContent(InputStream in){
    	if(in == null)
    		return null;
    	BufferedReader br = null;
    	try {
			//获取微信POST过来反馈信息
			br = new BufferedReader(new  InputStreamReader(in,"UTF-8"));
			StringBuffer sb = new StringBuffer();
			String str = null;
			while((str = br.readLine()) != null){
				sb.append(str);
			}
			return sb.toString();
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			log.error("", e);
		}finally{
			if(br != null){
				try {
					br.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					log.error("", e);
				}
			}
		}
		return null;
    }
    
    
}
