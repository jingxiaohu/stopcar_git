
package com.park.gate.action;

import com.park.action.BaseWebTest;
import org.junit.Test;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

/**
 * 道闸停车场的更新用户现金和在线支付的支付状态和金额
 * 处理客户端对订单支付的确认信息记录，同时记录异常订单信息
 * 新增支付类型参数is_cash(0：在线支付  1：现金支付)
 * @author zyy
 *
 */
public class Write_CarSureOutParkNewActionTest extends BaseWebTest {
	/**
	 * 请求参数：
	 * 	"parameter":{ "dtype":["2"], "pi_id":["29"], "car_code":["浙A88888"], "area_code":["510107"], "orderid":["2017012413373916"], "money":["2"],"is_cash":["1"],"is_sync":["1"], "sync_time":["1509674948446"], "type":["0"], "sign":["eb4ec4f75ff90731738a27b0dcf48a52"] }
	 * 响应数据：
	 * 	{"utime":1510017195190,"returnData":{"data":"","errorcode":"","errormsg":"处理成功","errorno":"0"}}
	 * 1：道闸停车场的更新用户现金支付状态和金额
	 * @throws Exception
	 */
	@Test
	public void pay_sure_new() throws Exception{
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();

		long pi_id = 29;//场地主键ID
		String car_code = "浙A88888";//车牌号
		String area_code="510107";//省市区区域代码  四川省 成都市 武侯区
		String orderid="2017012413373916";//我们的订单号  字符串
		int money=2;//金额（ 单位分）
		int is_cash=1;//现金支付
		int is_sync=1;//金额（ 单位分）
		int type=0;//处理类型 0:常规类型 1：免费分钟类型 2:免费车类型 3：包月车类型 4：租赁车类型
		long sync_time = System.currentTimeMillis();
		System.out.print(sync_time);

		params.add("dtype", dtype);
		params.add("pi_id", pi_id+"");
		params.add("car_code", car_code);
		params.add("area_code", area_code);
		params.add("orderid", orderid);
		params.add("money", money+"");
		params.add("is_cash", is_cash+"");
		params.add("is_sync", is_sync+"");
		params.add("sync_time", sync_time+"");
		params.add("type", type+"");
		sign(params, "dtype","orderid","pi_id","is_cash","is_sync");

		MvcResult mvcResult = mockMvc.perform(post("/v1/pay_sure_new").params(params))
				.andExpect(status().isOk()).andReturn();
		System.err.println(mvcResult.getResponse().getContentAsString());
	}
	
	
	
}
