//
//package stopcar.test.action;
//
//import java.io.InputStreamReader;
//import java.net.URL;
//
//import org.apache.commons.httpclient.methods.GetMethod;
//import org.junit.Test;
//
//import com.sun.net.ssl.HttpsURLConnection;
//
///**
// * @author jingxiaohu
// *
// */
//public class TTTTTest extends BaseActionTest {
//	/**
//	 * 读取用户绑定的车牌号
//	 * @throws Exception
//	 * {"data":"","errormsg":"绑定成功","errorno":"0"}
//	 */
//	@Test
//	public void read_bindcar() throws Exception{
//		String url = "https://ibsbjstar.ccb.com.cn/CCBIS/ccbMain?CCB_IBSVersion=V6&MERCHANTID=105510548160013&POSID=426295203&BRANCHID=510000000&ORDERID=2016123011002651&PAYMENT=0.03&CURCODE=01&TXCODE=530550&REMARK1=&REMARK2=&RETURNTYPE=1&TIMEOUT=&MAC=867e0f800fe2437ec4f52b40ea992baa";
//		/*URL reqURL = new URL(url ); //创建URL对象
//		HttpsURLConnection httpsConn = (HttpsURLConnection)reqURL.openConnection();
//
//
//
//		//取得该连接的输入流，以读取响应内容 
//		InputStreamReader insr = new InputStreamReader(httpsConn.getInputStream());
//
//		//读取服务器的响应内容并显示
//		int respInt = insr.read();
//		while( respInt != -1){
//			System.out.print((char)respInt);
//			respInt = insr.read();
//		}
//		
//	}*/
//		//		PostMethod post  = new UTF8PostMethod(url);
//		GetMethod post = new GetMethod(url);
//		post.set
//		int xx = HC.executeMethod(post);
//		System.out.println("*请求状态code="+xx); 
//		try {
//			String ds = post.getResponseBodyAsString();
//			if(ds == null){
//				System.out.println("无数据返回");
//			}else{
//				System.out.println(new String(post.getResponseBodyAsString().getBytes("utf-8"), "utf-8"));
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//			log.error("read_bindcar is error"+e.getMessage(), e); 
//		}finally{
//			if(post!=null){
//				post.releaseConnection();
//				//释放链接
//				HC.getHttpConnectionManager().closeIdleConnections(0);
//			}
//		}
//	}
//	
//	private HttpClient initHttpClient() {  
//	    if(httpclient == null){  
//	        try {  
//	            X509TrustManager tm = new X509TrustManager() {  
//	                public void checkClientTrusted(X509Certificate[] arg0,  
//	                        String arg1) throws CertificateException {  
//	                }  
//	  
//	                public void checkServerTrusted(X509Certificate[] arg0,  
//	                        String arg1) throws CertificateException {  
//	                }  
//	  
//	                public X509Certificate[] getAcceptedIssuers() {  
//	                    return null;  
//	                }  
//	            };  
//	            SSLContext sslcontext = SSLContext.getInstance("TLS");  
//	            sslcontext.init(null, new TrustManager[] { tm }, null);  
//	            SSLSocketFactory ssf = new SSLSocketFactory(sslcontext);  
//	            ClientConnectionManager ccm = new DefaultHttpClient().getConnectionManager();  
//	            SchemeRegistry sr = ccm.getSchemeRegistry();  
//	            sr.register(new Scheme("https", 8443, ssf));  
//	            HttpParams params = new BasicHttpParams();  
//	            params.setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 1000);  
//	            params.setParameter(CoreConnectionPNames.SO_TIMEOUT, 5000);  
//	            httpclient = new DefaultHttpClient(ccm,params);  
//	        } catch (Exception ex) {  
//	            ex.printStackTrace();  
//	        }  
//	    }  
//	    return httpclient;  
//	}
//}
