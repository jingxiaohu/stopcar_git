
package stopcar.test.action;

import org.apache.commons.httpclient.methods.PostMethod;
import org.junit.Test;

import com.park.constants.Constants;

/**
 * 用户申诉退费
 * @author jingxiaohu
 *
 */
public class Write_userMoneybackActionTest extends BaseActionTest {
	/**
	 * 用户申诉退费
	 * @throws Exception
	 * {"data":"","errorcode":"","errormsg":"用户申述成功","errorno":"0"}
	 */
	@Test
	public void usermoneyback() throws Exception{
		String url = BaseUrl+"usermoneyback.php";
		PostMethod post  = new UTF8PostMethod(url);
		//设置公共参数
		setPublicParam(post);
		
		 
		 
		String order_id="2016120614254572";//停车下订单ID
		long pi_id = 10;//停车场主键ID
		int um_money=400;//退款金额(单位 分)
		String car_code= "川A4M99B";//车牌号 (第一期：车牌号---对应一个人);//退款车牌号
		int um_state=0;//退款状态 0：未退款 1：已退款
		int check_state=0;//审核状态 0：未审核 1：已审核
		long admin_userid=0;//审核人后台管理表主键ID
		int is_rent=2;//是否是租赁订单 0：临停订单  1：预约临停订单  2：租赁包月订单
		String area_code="510122";//area_code;//省市县编号 510122
		int type=2;//申诉类型 0：临停扣款问题 1：预约超时扣费问题  2：其它
		String content="我下单付款后，还是显示我没有包月成功";//申诉原因
		
		sign = getSignature(Constants.SYSTEM_KEY, dtype,ui_id,car_code);
		
		post.addParameter("area_code", area_code);
		post.addParameter("pi_id", pi_id+"");
		post.addParameter("order_id", order_id);
		post.addParameter("car_code", car_code);
		post.addParameter("um_state", um_state+"");
		post.addParameter("admin_userid", admin_userid+"");
		post.addParameter("is_rent", is_rent+"");
		
		post.addParameter("type", type+"");
		post.addParameter("content", content+"");
		
		post.addParameter("um_money", um_money+"");
		post.addParameter("check_state", check_state+"");
		
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
			log.error("read_myinfo is error"+e.getMessage(), e); 
		}finally{
			if(post!=null){
				post.releaseConnection();
				//释放链接
				HC.getHttpConnectionManager().closeIdleConnections(0);
			}
		}
	}
}
