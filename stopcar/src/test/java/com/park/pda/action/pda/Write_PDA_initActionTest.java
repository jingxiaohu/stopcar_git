package com.park.pda.action.pda;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import com.park.action.BaseWebTest;

/**
 * 露天停车场的PDA更新用户自动支付
 * @author jingxiaohu
 *
 */
public class Write_PDA_initActionTest extends BaseWebTest {
	/**
	 * 露天停车场的PDA更新用户自动支付
	 * @throws Exception
	 * {"data":"","errorcode":"","errormsg":"处理成功","errorno":"0"}
	 */
	@Test
	public void init_pda() throws Exception{
		/*String url = BaseUrl+"pda_sure.php";
		PostMethod post  = new UTF8PostMethod(url);
		//设置公共参数 川A4M99B
		setPublicParam(post);
		
		
		long pi_id=1;//预约停车场主键ID
		int pay_type=0;//支付类型 0:自动扣款  1：现金支付
		String car_code="辽JQQ360";//车牌号
		int money=500;//金额（ 单位分）
		String area_code="510112";//省市区区域代码
		String orderid="a33beeb2794b1a5d9401473220073374";//我们的订单号  字符串
		 
		sign = getSignature(Constants.SYSTEM_KEY, dtype,orderid,pi_id);
		
		post.addParameter("money", money+"");
		post.addParameter("pi_id", pi_id+"");
		post.addParameter("pay_type", pay_type+"");
		post.addParameter("car_code", car_code);
		post.addParameter("orderid", orderid);
		post.addParameter("area_code", area_code);
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
			log.error("pda_sure is error"+e.getMessage(), e); 
		}finally{
			if(post!=null){
				post.releaseConnection();
				//释放链接
				HC.getHttpConnectionManager().closeIdleConnections(0);
			}
		}*/
		
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();

		long pi_id=1;//预约停车场主键ID
		int pay_type=0;//支付类型 0:自动扣款  1：现金支付
		String car_code="辽JQQ360";//车牌号
		int money=500;//金额（ 单位分）
		String area_code="510112";//省市区区域代码
		String orderid="a33beeb2794b1a5d9401473220073374";//我们的订单号  字符串
		 
		params.add("money", money+"");
		params.add("pi_id", pi_id+"");
		params.add("pay_type", pay_type+"");
		params.add("car_code", car_code);
		params.add("orderid", orderid);
		params.add("area_code", area_code);
		params.add("dtype", dtype);
		
	    sign(params, "dtype","orderid","pi_id");
	    
	    MvcResult mvcResult = mockMvc.perform(post("/v1/init_pda").params(params))
	        .andExpect(status().isOk()).andReturn();
	    System.err.println(mvcResult.getResponse().getContentAsString());
		
	}
}
