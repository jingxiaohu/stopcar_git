package com.park.util;

import java.math.BigInteger;
import java.security.MessageDigest;

public class MD5 {

	private MD5() {}
	
	public final static String getMessageDigest(byte[] buffer) {
		char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
		try {
			MessageDigest mdTemp = MessageDigest.getInstance("MD5");
			mdTemp.update(buffer);
			byte[] md = mdTemp.digest();
			int j = md.length;
			char str[] = new char[j * 2];
			int k = 0;
			for (int i = 0; i < j; i++) {
				byte byte0 = md[i];
				str[k++] = hexDigits[byte0 >>> 4 & 0xf];
				str[k++] = hexDigits[byte0 & 0xf];
			}
			return new String(str);
		} catch (Exception e) {
			return null;
		}
	}
	
	/**
	 * 对字符串md5加密
	 *
	 * @param str
	 * @return
	 */
	public static String getMD5(String str) {
	    try {
	        // 生成一个MD5加密计算摘要
	        MessageDigest md = MessageDigest.getInstance("MD5");
	        // 计算md5函数
	        md.update(str.getBytes());
	        // digest()最后确定返回md5 hash值，返回值为8为字符串。因为md5 hash值是16位的hex值，实际上就是8位的字符
	        // BigInteger函数则将8位的字符串转换成16位hex值，用字符串来表示；得到字符串形式的hash值
	        return new BigInteger(1, md.digest()).toString(16);
	    } catch (Exception e) {
//	        log.e("MD5加密出现错误");
	        return null;
	    }
	}
	
//	public static void main(String[] args) throws UnsupportedEncodingException {
//		String str = "appid=wxa8c52369cd7047b5&attach=1&body=吾泊微信充值&mch_id=1426571202&nonce_str=LbaDvCBqjZHyqac9kVa53ly9DNaccdmm&notify_url=http://223.85.163.38:8091/v1/notify_weixin.php&out_trade_no=2017030711399128&spbill_create_ip=127.0.0.1&total_fee=1&trade_type=APP&key=BcjkVKJYuI4tHQsc44T9eKOd5jGOj6B6";
//		System.out.println(DigestUtils.md5Hex(str));
//		System.out.println(DigestUtils.md5Hex(str.getBytes("UTF-8")).toUpperCase());
//		System.out.println(getMD5(str));
//		System.out.println(getMessageDigest(str.getBytes("UTF-8")));
//	  }
}
