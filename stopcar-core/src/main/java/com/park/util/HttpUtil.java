package com.park.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.SimpleHttpConnectionManager;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpClientParams;

public class HttpUtil {

	/**
	 * 执行post或者get方法
	 * 
	 * @param method
	 *            post/get方法
	 * @throws IOException
	 * @throws HttpException
	 */
	public static void executeHttpClient(HttpMethod method)
			throws HttpException, IOException {
		HttpClient httpclient = new HttpClient(new HttpClientParams(),
				new SimpleHttpConnectionManager(true));
		httpclient.executeMethod(method);

	}

	/**
	 * 无返回值执行post方法
	 * 
	 * @param url
	 *            url地址
	 * @throws IOException
	 * @throws HttpException
	 */
	public static void executePost(String url, String eventName,
			String value1, String value2, String value3, String value4)
			throws HttpException, IOException {
		PostMethod post = new PostMethod(url);
		post.addParameter("miName", eventName);
		post.addParameter("make","353179ABC");

		if (!RequestUtil.checkObjectBlank(value1)) {
			post.addParameter("miParmOne", value1);
		}

		if (!RequestUtil.checkObjectBlank(value1)) {
			post.addParameter("miParmTwo", value2);
		}

		if (!RequestUtil.checkObjectBlank(value1)) {
			post.addParameter("miParmThe", value3);
		}

		if (!RequestUtil.checkObjectBlank(value4)) {
			post.addParameter("miParmFor", value1);
		}

		HttpUtil.executeHttpClient(post);

		post.releaseConnection();

	}

	/**
	 * 将文件流的转换为指定字符流
	 * 
	 * @param ins
	 *            inputstream
	 * @param charset
	 *            编码
	 * @return 字符流
	 * @throws IOException
	 */
	public static String getStream2String(InputStream ins, String charset)
			throws IOException {
		String line = null;

		StringBuilder sb = new StringBuilder();
		BufferedReader isr = new BufferedReader(new InputStreamReader(ins,
				charset));

		while ((line = isr.readLine()) != null) {
			sb.append(line);
		}
		line = sb.toString();

		return line;
	}
}
