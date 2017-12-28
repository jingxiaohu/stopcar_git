/***************************************************************************
 * 
 * Copyright (c) by ihiyo.com, Inc. All Rights Reserved
 * 
 **************************************************************************/
package com.park.util;

import java.io.IOException;
import java.net.URLEncoder;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 短信工具
 * 
 * @author 嗨游互动研发部
 */
public class SMSUtils {
	static Logger logger=LoggerFactory.getLogger(SMSUtils.class);
	/**
	 * 一个号码短信发送间隔时间
	 */
	static int intervalMinute=1;
	
	static String CACHE_SMS = "sms";
	/**
	 * 默认的短信api接口
	 */
	static String defaultSMSAPI = "http://smsapi.c123.cn/OpenPlatform/OpenApi?";
	/**
	 * 默认的短信内容格式
	 */
	public static String REGISTER_FORMART = "您正在进行账号注册，请勿将验证码%s提供给他人";
	/**
	 * 
	 */
	public static String REGISTER_OR_BANDLE_FORMART = "您的验证码是%s,请勿将验证码提供给他人";
	/**
	 * 找回密码短信内容格式
	 */
	public static String findPassWordSMSContentFormat = "您正在通过手机找回密码，请勿将验证码%s提供给他人";
	/**
	 * 找回密码短信内容格式
	 */
	public static String reBindMobileSMSContentFormat = "您正在修改绑定手机，请勿将验证码%s提供给他人";
	/**
	 * 默认短信发送参数 action
	 */
	static String defaultAction = "sendOnce";
	/**
	 * 默认短信发送参数 ac
	 */
	static String defaultAc = "1001@500896750001";
	/**
	 * 默认短信发送参数authkey
	 */
	static String defaultAuthkey = "18930E3C19F6CB910692C106C2DCB1FA";
	/**
	 * 默认短信发送参数 cgid
	 */
	static String defaultCgid = "933";
	/**
	 * 默认短信发送参数csid
	 */
	static String defaultCsid = "";

	/**
	 * 调用短信服务商接口发送短信
	 * 
	 * @param phone
	 *            手机
	 * @param content
	 *            内容
	 * @return
	 */
	public static boolean sendSMS(String phone, String content,
			String template) {
		boolean result = false;
		try {
			StringBuilder sb = new StringBuilder();
			String smsApi = defaultSMSAPI;
			String action = defaultAction;
			String ac = defaultAc;
			String authkey = defaultAuthkey;
			String cgid = defaultCgid;
			String csid = defaultCsid;
			sb.append(smsApi);
			sb.append("action=").append(action);
			sb.append("&ac=").append(ac);
			sb.append("&authkey=").append(authkey);
			sb.append("&cgid=").append(cgid);
			if (!csid.isEmpty()) {
				sb.append("&csid=").append(csid);
			}
			sb.append("&c=").append(
					URLEncoder.encode(String.format(template, content),
							"utf-8"));
			sb.append("&m=").append(phone);
			String request = sb.toString();
			logger.info("短信请求："+request);
			Document doc = Jsoup.connect(request).timeout(60000).get();
			Elements elements = doc.getElementsByAttributeValue("result", "1");
			if (!elements.isEmpty()) {
				result = true;// 短信发送成功
			}else{
				logger.error("短信发送失败，请检查短信发送");
			}
		} catch (IOException e) {
			logger.error(e.getMessage(),e);
		}
		return result;
	}
}
