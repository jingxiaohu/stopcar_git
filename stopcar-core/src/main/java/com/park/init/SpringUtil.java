package com.park.init;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.park.exception.QzException;

/**
 * Title:spring工具类
 * Description:用于在运行中获得bean
 * Copyright: Copyright (c) 2013
 * Company: rumtel Technology Chengdu Co. Ltd.
 * @author 敬小虎
 * @version 1.0 2013-5-13
 */
public class SpringUtil {
	
	/**
	 * 获得bean
	 * @param bean 
	 * @return
	 * @throws QzException
	 */
	@SuppressWarnings("unchecked")
	public static <T>T getBean(String bean)throws QzException {
		Object obj = WebApplicationContextUtils.getRequiredWebApplicationContext(CharacterFilter.config.getServletContext()).getBean(bean);
		if(obj==null){
			throw new QzException("根据bean="+bean+"没有找到bean");
		}
		return (T)obj;
	}
	
	@SuppressWarnings("unchecked")
	public static <T>T getBeanByTestLocal(String bean)throws Exception{
		 AbstractApplicationContext  ctx = new FileSystemXmlApplicationContext("webapp/WEB-INF/config/spring/spring-test.xml");
		 return (T) ctx.getBean(bean);
	}

}
