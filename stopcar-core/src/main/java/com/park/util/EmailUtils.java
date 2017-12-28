/***************************************************************************
 * 
 * Copyright (c) by ihiyo.com, Inc. All Rights Reserved
 * 
 **************************************************************************/
package com.park.util;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.SimpleEmail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author  嗨游互动研发部
 */
public class EmailUtils {
	static Logger logger=LoggerFactory.getLogger(EmailUtils.class);
	public static boolean sendEmail(String target,String content,String template){
		boolean success=false;
		try {
			Email email = new SimpleEmail();
			email.setHostName("smtp.exmail.qq.com");
			email.setSmtpPort(465);
			email.setAuthenticator(new DefaultAuthenticator("dev@ihiyo.com", "123@qweasdzxc"));
			email.setSSLOnConnect(true);
			email.setFrom("dev@ihiyo.com");
			email.setSubject("嗨游");
			email.setMsg(String.format(template, content));
			email.addTo(target);
			email.send();
			success=true;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return success;
	}

}
