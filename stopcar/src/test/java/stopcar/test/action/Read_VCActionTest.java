
package stopcar.test.action;

import org.apache.commons.httpclient.methods.PostMethod;
import org.junit.Test;

import com.park.constants.Constants;

/**
 * 获取我的账户出入记录
 * @author jingxiaohu
 *
 */
public class Read_VCActionTest extends BaseActionTest {
	/**
	 * 获取我的账户出入记录
	 * @throws Exception
	 * {"data":[{"act_type":0,"ctime":1473302542000,"id":1,"is_add":0,"money":30000,"note":"","order_id":"f52a2b9448744c324df1473302542135","order_type":1,"state":1,"ui_id":1}],"errorcode":"","errormsg":"全部的出入记录","errorno":"0"}
	 */
	@Test
	public void vc_record() throws Exception{
		String url = BaseUrl+"vc_record.php";
		PostMethod post  = new UTF8PostMethod(url);
		//设置公共参数
		setPublicParam(post);
		
		int type=2;// 获取虚拟币行为类型  0:消耗和充值  1：消耗  2：充值
		int page  = 1;
		int size = 20;
		
		sign = getSignature(Constants.SYSTEM_KEY, dtype,ui_id);
		post.addParameter("type", type+"");
		post.addParameter("page", page+"");
		post.addParameter("size", size+"");
		
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
			log.error("vc_record is error"+e.getMessage(), e); 
		}finally{
			if(post!=null){
				post.releaseConnection();
				//释放链接
				HC.getHttpConnectionManager().closeIdleConnections(0);
			}
		}
	}
}
