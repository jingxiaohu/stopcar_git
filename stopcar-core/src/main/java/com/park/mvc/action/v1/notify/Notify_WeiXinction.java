
package com.park.mvc.action.v1.notify;

import com.park.bean.ReturnDataNew;
import com.park.mvc.service.UserPayBiz;
import com.park.mvc.action.v1.BaseV1Controller;
import com.park.util.XMLBeanUtils;
import com.weixin.config.HttpTool;
import com.weixin.config.WeiXinSignUtil;
import com.weixin.config.WeixinNotifyReponse;
import com.weixin.config.WeixinPayConstants;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 微信通知监听接口
 * @author jingxiaohu
 *
 */
@Controller
@RequestMapping(value = "/v1")
public class Notify_WeiXinction extends BaseV1Controller {
	
	private static String ALIPAY_PUBLIC_KEY="MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDDI6d306Q8fIfCOaTXyiUeJHkrIvYISRcc73s3vF1ZT7XN8RNPwJxo8pWaJMmvyTn9N4HQ632qJBVHf8sxHi/fEsraprwCtzvzQETrNRwVxLO5jVmRGi60j8Ue1efIlzPXV9je9mkjzOmdssymZkh2QhUrCmZYI/FCEa3/cNMW0QIDAQAB";
	/**
	* @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	*/ 
	private static final long serialVersionUID = -3599663972160625262L;


	@Autowired
	private UserPayBiz userPayBiz;

	/**
	 * 支付完成后，微信会把相关支付结果和用户信息发送给商户，商户需要接收处理，并返回应答。 

对后台通知交互时，如果微信收到商户的应答不是成功或超时，微信认为通知失败，微信会通过一定的策略定期重新发起通知，尽可能提高通知的成功率，但微信不保证通知最终能成功。 （通知频率为15/15/30/180/1800/1800/1800/1800/3600，单位：秒）

注意：同样的通知可能会多次发送给商户系统。商户系统必须能够正确处理重复的通知。 
推荐的做法是，当收到通知进行处理时，首先检查对应业务数据的状态，判断该通知是否已经处理过，如果没有处理过再进行处理，如果处理过直接返回结果成功。在对业务数据进行状态检查和处理之前，要采用数据锁进行并发控制，以避免函数重入造成的数据混乱。 

特别提醒：商户系统对于支付结果通知的内容一定要做签名验证，防止数据泄漏导致出现“假通知”，造成资金损失。 

	 * 微信通知监听接口
	 * @return
	 */
	@RequestMapping(value = "/notify_weixin")
	@ResponseBody
	public String notify_weixin(HttpServletRequest request,HttpServletResponse response){
		ReturnDataNew returnData = new ReturnDataNew();
		//非空验证
		try {
			//获取微信POST过来反馈信息
			String xmlcontent = HttpTool.readStreamContent(request.getInputStream());
			if(xmlcontent == null){
				return null;
			}
			//打印微信回调数据
			log.debug("weixin------xmlcontent="+xmlcontent);
			Map<String,String> params =  XMLBeanUtils.xml2Map(xmlcontent);
			if(params == null){
				return null;
			}
			log.info("notify_weixin params="+params.toString());
			
			if(!"SUCCESS".equalsIgnoreCase(params.get("return_code"))){
				WeixinNotifyReponse repon = new WeixinNotifyReponse();
				repon.setReturn_msg("签名错误");
				repon.setReturn_code("FAIL");
				Map<String, Class<?>>  cm = new HashMap<String, Class<?>>(); 
				cm.put("xml",WeixinNotifyReponse.class);
				sendRespHtml(XMLBeanUtils.bean2xml(cm, repon),response);
				return null;
			}
			String sign = params.get("sign");
			params.remove("sign");
			//验签方法（例如异步通知的时候，用户需要用到验签方法）
			/**
			@param params 参数列表(包括待验签参数和签名值sign) key-参数名称 value-参数值
			@param publicKey 验签公钥
			@param charset 验签字符集
			**/
			if(sign != null && sign.equalsIgnoreCase(WeiXinSignUtil.getSign(params, WeixinPayConstants.appsecret))){
				
				//验签成功
				log.info("验签成功-----------"); 
				//验证成功  更新该订单的支付状态 并把对应的金额添加给用户
				String orderid = params.get("out_trade_no");
				String trade_no = params.get("transaction_id"); 
				String type = "0";
				if(params.containsKey("attach")){
					type = params.get("attach");
				}else{
					type = params.get("attach");
				}
				//金额
				long money = Long.parseLong(params.get("total_fee")); 
				try {
						userPayBiz.notify_weixin(returnData,orderid,trade_no,type,money);
						if(returnData != null && "0".equalsIgnoreCase(returnData.getErrorno())){
							WeixinNotifyReponse repon = new WeixinNotifyReponse();
							repon.setReturn_msg("OK");
							repon.setReturn_code("SUCCESS");
							Map<String, Class<?>>  cm = new HashMap<String, Class<?>>(); 
							cm.put("xml",WeixinNotifyReponse.class);
							sendRespHtml(XMLBeanUtils.bean2xml(cm, repon),response);
							return null;
						}
				} catch (Exception e) {
						// TODO Auto-generated catch block
						log.error("更新该订单的支付状态 并把对应的金额添加给用户 失败", e); 
				}
			}
			log.info("验签失败------error-----"); 
			WeixinNotifyReponse repon = new WeixinNotifyReponse();
			repon.setReturn_msg("签名错误");
			repon.setReturn_code("FAIL");
			Map<String, Class<?>>  cm = new HashMap<String, Class<?>>(); 
			cm.put("xml",WeixinNotifyReponse.class);
			sendRespHtml(XMLBeanUtils.bean2xml(cm, repon),response);
			return null;
		} catch (Exception e) {
			log.error("Notify_WeiXinction.notify_weixin is error 微信通知监听接口（GAMESDK-JAVA）- P",e);
			WeixinNotifyReponse repon = new WeixinNotifyReponse();
			repon.setReturn_msg("签名错误");
			repon.setReturn_code("FAIL");
			Map<String, Class<?>>  cm = new HashMap<String, Class<?>>(); 
			cm.put("xml",WeixinNotifyReponse.class);
			sendRespHtml(XMLBeanUtils.bean2xml(cm, repon),response);
			return null;
		}
	}
	
	
	
	
/********************接收参数区*************************/

}
