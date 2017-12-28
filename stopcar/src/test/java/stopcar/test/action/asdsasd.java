package stopcar.test.action;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang3.RandomStringUtils;

public class asdsasd {
	/**
	 * 生成32位UUID
	 */
	public static  String returnUUID(){
//		return RequestUtil.getUUID().substring(13)+System.currentTimeMillis();
		return new SimpleDateFormat("yyyyMMddHHmmss").format(new Date())+RandomStringUtils.random(18,false,true);  
	}
	public static void main(String[] args) {
//		String ss = "2.30";
		/*BigDecimal b1 = new BigDecimal(ss);
		BigDecimal b2 = new BigDecimal(100);
		System.out.println(b1.multiply(b2).longValue());*/
//		System.out.println(Math.round(Double.parseDouble(ss)*100));
//		System.out.println(Double.parseDouble(ss)*100);
		System.out.println(returnUUID().length());
		System.out.println(returnUUID());
		System.out.println("2017050212544301468".length());
//		System.out.print(String.valueOf(Long.MAX_VALUE).length());
	}
}
