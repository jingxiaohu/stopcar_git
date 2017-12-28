
package stopcar.test.action;

import org.apache.commons.httpclient.methods.PostMethod;
import org.junit.Test;

import com.park.constants.Constants;

/**
 * 获取我的订单 （1：临时停车订单  2： 租赁订单）
 * @author jingxiaohu
 *
 */
public class Read_MyOrderActionTest extends BaseActionTest {
	/**
	 * 获取我的订单 （1：临时停车订单  2： 租赁订单）
	 * @throws Exception
	 * {"data":[{"address_name":"","allege_state":0,"area_code":"510112","arrive_time":1470972770000,"cancel_state":0,"car_code":"辽JQQ360","charging":0,"charging_time":0,"ctime":1470972770000,"discount_money":500,"discount_name":"停车优惠券2元","discount_type":0,"expect_info":"","expect_money":500,"expect_time":120,"final_time":271912,"id":1,"is_del":0,"is_expect_deduct":0,"is_expect_outtime":0,"leave_time":1470972770000,"money":500,"my_order":"8a1ace9c0a9fc18de521470972770074","note":"用户预约下单","order_type":0,"other_order":"","pay_source":1,"pay_type":0,"phone_type":0,"pi_id":1,"pp_state":0,"pp_versioncode":"1","start_price":0,"start_time":0,"ui_id":1,"upc_id":0,"utime":1470972770000}],"errormsg":"普通停车订单","errorno":"0"}
	 */
	@Test
	public void my_order() throws Exception{
		String url = BaseUrl+"my_order.php";
		PostMethod post  = new UTF8PostMethod(url);
		//设置公共参数
		setPublicParam(post);
		
		int type=0;// 获取订单类型  0:普通停车订单  1：租赁停车订单
		String car_code="赣F16712";//车牌号
		String area_code="510107";//省市区区域代码  四川省 成都市 龙泉驿区  510112
		int page  = 1;
		int size = 20;
		
		sign = getSignature(Constants.SYSTEM_KEY, dtype,ui_id);
		post.addParameter("type", type+"");
		post.addParameter("car_code", car_code);
		post.addParameter("area_code", area_code);
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
				System.out.println(post.getResponseBodyAsString().getBytes("utf-8").length);
				System.out.println(new String(post.getResponseBodyAsString().getBytes("utf-8"), "utf-8"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("read_mycoupon is error"+e.getMessage(), e); 
		}finally{
			if(post!=null){
				post.releaseConnection();
				//释放链接
				HC.getHttpConnectionManager().closeIdleConnections(0);
			}
		}
	}
}
