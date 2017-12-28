package com.park.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.park.constants.Constants;


public class RequestUtil {
	
	private static Logger log = LoggerFactory.getLogger(RequestUtil.class);
	
	
	/**
	 * 带默认值String类型转化
	 * 
	 * @param obj
	 *            需要转换的字段
	 * @param defalut
	 *            默认值
	 * @return 转换后的字符串，转换失败返回默认值
	 */
	public static String valifyStr(Object obj, String defalut) {
		if (obj == null || obj == "") {
			return defalut;
		} else {
			try {
				return obj.toString();
			} catch (Exception e) {
				log.error("valifyStr error",e);
				return defalut;
			}

		}
	}

	/**
	 * 带默认值的整形转换
	 * 
	 * @param obj
	 *            传入参数
	 * @param defalut
	 *            默认值
	 * @return 转换后的整形，转换失败返回默认值
	 */
	public static int valifyInt(Object obj, int defalut) {

		if (obj == null || obj == "") {
			return defalut;
		} else {
			try {
				return Integer.parseInt(obj.toString());
			} catch (Exception e) {
				log.error("valifyInt error",e);
				return defalut;
			}
		}
	}

	/**
	 * @param obj
	 *            需要转换的参数
	 * @param defalut
	 *            默认值
	 * @return 转换以后的double类型
	 */
	public static double valifyDouble(Object obj, double defalut) {

		if (obj == null || obj == "") {

			return defalut;
		} else {
			try {
				return Double.parseDouble(obj.toString());

			} catch (Exception e) {
				log.error("valifyDouble error",e);
				return defalut;
			}
		}
	}

	/**
	 * @param obj
	 *            需要转换的参数
	 * @param defalut
	 *            默认值
	 * @return 转换以后的float类型
	 */
	public static float valifyFloat(Object obj, float defalut) {
		if (obj == null || obj == "") {

			return defalut;
		} else {
			try {
				return Float.parseFloat( obj.toString());

			} catch (Exception e) {
				log.error("valifyFloat error",e);
				return defalut;
			}
		}
	}

	/**
	 * 执行set方法
	 *
	 */
	public static void invokeSet(Object o, String fieldName, Object value) {
		Method method = getSetMethod(o.getClass(), fieldName);
		try {
			method.invoke(o, new Object[] { value });
		} catch (Exception e) {
			log.error("invokeSet error",e);
		}
	}

	/**
	 * java反射bean的set方法
	 * 
	 * @param objectClass
	 *            class对象
	 * @param fieldName
	 *            属性名
	 * @return 属性名对应的set方法
	 */
	@SuppressWarnings("unchecked")
	public static Method getSetMethod(Class objectClass, String fieldName) {
		try {
			Class[] parameterTypes = new Class[1];
			Field field = objectClass.getDeclaredField(fieldName);
			parameterTypes[0] = field.getType();
			StringBuffer sb = new StringBuffer();
			sb.append("set");
			sb.append(fieldName.substring(0, 1).toUpperCase());
			sb.append(fieldName.substring(1));
			Method method = objectClass
					.getMethod(sb.toString(), parameterTypes);
			return method;
		} catch (Exception e) {
			log.error("getSetMethod error",e);
		}
		return null;
	}

	/**
	 * 特殊字符半角替换为全角
	 * 
	 * @param input
	 * @return /~!@#$%^&*()_+}|":?><,.\=[]'`; ／～！＠＃＄％＾＆＊（）_＋｝｜＂：？＞＜，．＼＝［］＇｀；
	 */
	public static String SpecialCharReplice(String input) {
		char[] c = input.toCharArray();
		for (int i = 0; i < c.length; i++) {
			if ((c[i] > 32 && c[i] < 48 && c[i] != 45)
					|| (c[i] > 57 && c[i] < 65)
					|| (c[i] < 97 && c[i] > 90 && c[i] != 95)
					|| (c[i] > 122 && c[i] < 177)) {
				c[i] = (char) (c[i] + 65248);
			}
		}
		return new String(c);
	}

	/**
	 * 生成uuid
	 * 
	 * @return
	 */
	public static String getUUID() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}

	/**
	 * 特殊字符检查
	 * 
	 * @param str
	 * @return
	 */
	public static boolean checkSpecialCharacter(String str) {
		String regex = "[^%!@#/$:?;{'}(~`)_|=+&]+";
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(str);
		if (m.matches()) {
			return true;
		}
		return false;
	}

	/**
	 * 根据传入的时间格式，返回当前时间
	 * 
	 * @param formater
	 *            需要返回时间的格式，如:yyyy-MM-dd kk:mm:ss
	 * @return 当前时间
	 */
	public static String getCurrentTime(String formater) {
		Date d = new Date();// 获取时间
		SimpleDateFormat sdf = new SimpleDateFormat(formater);// 转换格式
		return sdf.format(d);
	}

	/**
	 * 返回一段文字中所有的用户
	 * 
	 * @param content
	 *            消息内容
	 * @return 返回的用户 如：@王五 @张三
	 */
	public static List<String> findUserName(String content) {

		List<String> names = new ArrayList<String>();

		Pattern pattern = Pattern.compile("@[\\u4e00-\\u9fa5\\w\\-]+");
		Matcher matcher = pattern.matcher(content);
		while (matcher.find()) {
			names.add(matcher.group());
		}
		return names;
	}

	/**
	 * 从一段文字中返回所有的话题
	 * 
	 * @param content
	 *            消息内容
	 * @return 返回话题 ,如: #今天星期五#
	 */
	public static List<String> findTopicName(String content) {

		List<String> names = new ArrayList<String>();

		Pattern pattern = Pattern.compile("#([^#|.]+)#");
		Matcher matcher = pattern.matcher(content);
		while (matcher.find()) {
			names.add(matcher.group());
		}
		return names;
	}

	/**
	 * 邮箱验证
	 * 
	 * @param email
	 * @return 验证通过返回true,不通过返回false
	 */
	public static boolean checkEmail(String email) {
		String check = "^([a-z0-9A-Z]+_*?)+[a-z0-9A-Z]@([a-z0-9A-Z]+?\\.)+[a-zA-Z]{2,}$";
		if (!email.matches(check)) {
			return false;
		}
		return true;
	}

	/**
	 * 检查对象是否为空
	 * 
	 * @param obj
	 *            对象
	 * @return 为空返回true
	 */
	public static boolean checkObjectBlank(Object obj) {
		if (obj == null || obj == "" || obj.toString().trim().length() == 0) {
			return true;
		}
		return false;
	}

	/**
	 * 检查对象是否为空
	 * 
	 * @param obj
	 *            对象
	 * @return 为空返回true
	 */
	public static String JSONKey(Object obj) {
		StringBuffer sb = new StringBuffer("");
		if (obj == null || obj == "" || obj.toString().trim().length() == 0) {
			return "";
		}else{
			String key = obj.toString();
			for(int i=0; i<key.length(); i++){
				char c = key.charAt(i);
				if(c>='A'&&c<='Z'){
					sb.append("_"+(char)(c+32));
				}else
					sb.append(c);
			}
		}
		return sb.toString();
	}

	/**
	 * 将字符串md5化
	 * 
	 * @param str
	 *            要转化的字符串
	 * @return 转化以后的字符串
	 */
	public static String MD5(String str) {

		StringBuffer buf = new StringBuffer("");
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(str.getBytes());
			byte b[] = md.digest();

			int i;

			for (int offset = 0; offset < b.length; offset++) {
				i = b[offset];
				if (i < 0)
					i += 256;
				if (i < 16)
					buf.append("0");
				buf.append(Integer.toHexString(i));
			}

			// System.out.println("result: " + buf.toString());// 32位的加密

			// System.out.println("result: " + buf.toString().substring(8,
			// 24));// 16位的加密

		} catch (NoSuchAlgorithmException e) {
			log.error("md5 error",e);
		}
		return buf.toString();
	}
	
	/**
	 * 解析字符中带有子JSON的串
	 * @param obj
	 * @return
	 */
	public static String parseJSON(Object obj){
		if(obj!=null){
			List<Object> list = new ArrayList<Object>();
			list.add(obj);
			JSONArray arry = JSONArray.parseArray(JSON.toJSONString(list, true)); 
			return arry.getJSONObject(0).toString();
		}
		return "";
	}
	
	
	/**
	 * list2JsonArray
	 * @param list
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static JSONArray listToJsonArray(List list){ 
		return JSONArray.parseArray(JSON.toJSONString(list, true));
	}
	
	
	
	/**
	 * 找出一段文字中的所有的表情
	 * 
	 * @param content
	 *            文字内容
	 * @return 所有的表情
	 */
	public static Set<String> findFace(String content) {
		Set<String> names = new HashSet<String>();

//		Pattern pattern = Pattern.compile("\\[[\u4e00-\u9fa5]+\\]");
		Pattern pattern = Pattern.compile("\\[[a-zA-Z0-9\u4e00-\u9fa5]+]");
		Matcher matcher = pattern.matcher(content);
		while (matcher.find()) {
			String str = matcher.group();
			if(str != null){
				names.add(str.replace("[", "").replace("]",""));
			}
		}
		//System.out.println(names);
		return names;
	}
	/**
	 * 找出一段文字中的所有的URL地址
	 * 
	 * @param content
	 *            文字内容
	 * @return 所有的url地址
	 */
	public static Set<String> findUrl(String content) {
		Set<String> urls = new HashSet<String>();
		Pattern p = Pattern.compile("(http:|https:)//[^[A-Za-z0-9._?%&+\\-=/#]]*");
		Matcher m = p.matcher(content);
		while (m.find()) {
			urls.add(m.group());
		}
		return urls;
	}
	/**
	 * 返回GOOGEL地图的访问地址
	 * @param latlng
	 * @return
	 */
	public static String getMap(String latlng){
		StringBuffer url = new StringBuffer("http://maps.google.com/maps/api/staticmap?");
		// 设置中心
		url.append("&center="+latlng);
		// 设置尺寸
		url.append("&size=600x300");
		// 缩放比例
		url.append("&zoom=12");
		// 地图格式
		url.append("&format=jpg");
		// 地图类型
		url.append("&maptype=roadmap");
		// 标记
		url.append("&markers=color:red%7Clabel:S%7C"+latlng);
		// 地图语言
		url.append("&language=zh");
		// 是否使用传感器
		url.append("&sensor=false");
		return url.toString();
	}
	
	/**
	 * 返回GOOGEL地图的访问地址
	 * @param latlng
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static String LatLngToAddress(String latlng){
		StringBuffer url = new StringBuffer("http://maps.googleapis.com/maps/api/geocode/json?");
		// 设置中心
		url.append("&latlng="+latlng);
		// 是否使用传感器
		url.append("&sensor=false");
		// 地图语言
		url.append("&language=zh");
		try{
			HttpPostUtil u = new HttpPostUtil(url.toString());
			byte b [] = u.send();
			String result = new String(b,Constants.SYSTEM_CHARACTER);
			JSONObject res = JSONObject.parseObject(result);
			JSONArray results = res.getJSONArray("results");
			Map map = new HashMap();
			map.put("country", "");
			map.put("province", "");
			map.put("city", "");
			map.put("address", "");
			if(results.size()>0){
				res = results.getJSONObject(0);
				results = res.getJSONArray("address_components");
				for(int i=0;i<results.size();i++){
					res = results.getJSONObject(i);
					String name = res.getString("long_name");
					String types = res.getString("types");
					if(types.indexOf("country")!=-1){map.put("country", name);}
					if(types.indexOf("administrative_area_level_1")!=-1){map.put("province", name);}
					if(types.indexOf("\"locality\"")!=-1){map.put("city", name);}
					if(types.indexOf("sublocality")!=-1){String temp = RequestUtil.valifyStr(map.get("address"), "");map.put("address", temp+name);}
					if(types.indexOf("route")!=-1){String temp = RequestUtil.valifyStr(map.get("address"), "");map.put("address", temp+name);}
				}
				return JSONObject.toJSONString(map).replace("\"", "'");
			}
			return "";
		}catch (Exception e) {
			log.error("LatLngToAddress error",e);
			return "";
		}
	}
	
	/**
	 * 处理字符串入库的操作
	 */
	public static  String returnDoStr(String sql_filed){
		if(sql_filed != null && !"".equals(sql_filed)){
			return sql_filed.replace("'", "\\'");
		}else{
			return sql_filed;
		} 
	}
	
	/**
	 * 
	* @Title: returnAuthCode
	* @Description: TODO( 生成验证码)
	* @param @param count : 生成验证码位数
	* @param @return    设定文件
	* @return String    返回类型
	* @throws
	 */
	public static String returnAuthCode(int count){
		if(count == 0){
			return "";
		}
		StringBuffer sb  = new 	StringBuffer();
		Random ran = new Random();
		for (int i = 0; i < count; i++) {
			sb.append(ran.nextInt(10));
		}
		return sb.toString();
	}
	/**
	 * 
	* @Title: returnAuthCode
	* @Description: TODO( 生成验证码)
	* @param @param count : 生成验证码位数
	* @param @return    设定文件
	* @return String    返回类型
	* @throws
	 */
	public static String returnAuthCode(){
		StringBuffer sb  = new 	StringBuffer();
		Random ran = new Random();
		for (int i = 0; i < 6; i++) {
			sb.append(ran.nextInt(10));
		}
		return sb.toString();
	}
	
	/**
	 * 检查Integer对象是否为空或为负数
	 * @param obj
	 * @return 为空和为负数时返回true
	 */
	public static boolean checkIntegerBlankOrNegative(Integer obj) {
		if (obj == null || obj < 0 || obj.toString().trim().length() == 0) {
			return true;
		}
		return false;
	}
}
