package com.weixin.config;
/**
 * 微信支付配置 
 * @author jingxiaohu
 *
 */
public class WeixinPayConstants {
	public static final String appid = "wxa8c52369cd7047b5";//在微信开发平台登记的app应用  
    public static final String appsecret = "BcjkVKJYuI4tHQsc44T9eKOd5jGOj6B6";  
    public static final String partner = "1426571202";//商户号  
    public static final String partnerkey ="XXXXXXXXXXXXXXXXXXXXXXXXXXXXX";//不是商户登录密码，是商户在微信平台设置的32位长度的api秘钥，  
    public static final String createOrderURL="https://api.mch.weixin.qq.com/pay/unifiedorder";  
    public static final String backUri="http://XXXXXXXX/api/weixin/topay.jhtml";//微信支付下单地址  
    public static final String notify_url="http://XXXXXXXXXX/api/weixin/notify.jhtml";//异步通知地址  
}
