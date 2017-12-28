
package com.park.gate.action;

import com.park.constants.Constants;
import org.apache.commons.httpclient.methods.PostMethod;
import org.junit.Test;
import stopcar.test.action.BaseActionTest;

/**
 * 车辆入库出库记录
 * @author jingxiaohu
 *
 */
public class Write_carInOutActionTest extends BaseActionTest {
	/**
	 * 车辆入库出库记录
	 * @throws Exception
	 * {"data":{"area_code":"510107","car_code":"沪F00001","car_code_color":1,"car_type":1,"cio_id":73717,"ctime":1495865757449,"gov_num":"000000000000001","in_out":"enter","in_out_code":"A","is_enter":0,"is_local_month":0,"is_rent":0,"is_sync":0,"note":"","order_id":"2017052714161247725","out_type":0,"pd_id":1,"pi_id":36,"rent_remain_time":0,"ui_id":0,"ui_nd":"","ui_tel":"","utime":1495865757449},"errorcode":"","errorno":"0"}
	 */
	@Test
	public void carInOut() throws Exception{
		String url = BaseUrl+"carinout.php";
		PostMethod post  = new UTF8PostMethod(url);
		//设置公共参数
		setPublicParam(post);
		
		 long pi_id = 1;//场地主键ID
		 String area_code="510107";//area_code;//省市县编号 510122
		 long pd_id = 1;//出入口设备主键ID
		 String car_code = "川A12348";//车牌号 (第一期：车牌号---对应一个人)
		 int is_enter=0;//入库或者出库 ：0：   入库   1：出库
		 String in_out="enter";//出口或者入口 入口：enter  出口：exit
		 String in_out_code="A";//出入口编号 A /B/C
		 int car_type = 1;//车牌类型:车牌类型 0：未知车牌:、1：蓝牌小汽车、2：: 黑牌小汽车、3：单排黄牌、4：双排黄牌、 5： 警车车牌、6：武警车牌、7：个性化车牌、8：单 排军车牌、9：双排军车牌、10：使馆车牌、11： 香港进出中国大陆车牌、12：农用车牌、13：教 练车牌、14：澳门进出中国大陆车牌、15：双层 武警车牌、16：武警总队车牌、17：双层武警总 队车牌
		 int car_code_color = 1;//车牌颜色 0：未知、1：蓝色、2：黄色、3：白色、 4：黑色、5：绿色 

		 int out_type= 0;//出库类型 0:正常出库 1：现金支付出库 2：异常出库
		 int is_local_month = 0;//是否是道闸本地包月车辆 0:不是 1：是
		 String order_id = "2017061414360166096";
		
		sign = getSignature(Constants.SYSTEM_KEY, dtype,pi_id,in_out,order_id);
		
		post.addParameter("area_code", area_code);
		post.addParameter("pi_id", pi_id+"");
		post.addParameter("pd_id", pd_id+"");
		post.addParameter("car_code", car_code);
		post.addParameter("is_enter", is_enter+"");
		post.addParameter("in_out", in_out);
		post.addParameter("in_out_code", in_out_code);
		
		post.addParameter("car_type", car_type+"");
		post.addParameter("car_code_color", car_code_color+"");
		
		post.addParameter("out_type", out_type+"");
		post.addParameter("is_local_month", is_local_month+"");

		post.addParameter("create_time","1496994372529");
		post.addParameter("gov_num","000000000000001");
		post.addParameter("order_id",order_id);
		post.addParameter("tarde_type","1");

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

	public static void main(String[] args) {
		System.out.println(System.currentTimeMillis());
	}
}
