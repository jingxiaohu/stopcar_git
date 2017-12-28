package com.park.util;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.Properties;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.crypto.cipher.CryptoCipher;
import org.apache.commons.crypto.utils.Utils;

/**
 * 加密解密工具类
 * @author jingxiaohu
 *
 */
public class MyCipherUtil {
	
	/**
	 * 加密
	 * @throws Exception 
	 */
	public void encrypt(String key_str,String content) throws Exception{
		final SecretKeySpec key = new SecretKeySpec(getUTF8Bytes(key_str), "AES");
		final IvParameterSpec iv = new IvParameterSpec(getUTF8Bytes(key_str));
		Properties properties = new Properties();
		//Creates a CryptoCipher instance with the transformation and properties.
		final String transform = "AES/CBC/PKCS5Padding";
		ByteBuffer outBuffer;
		final int bufferSize = 1024;
		final int updateBytes;
		final int finalBytes;
	    try (CryptoCipher encipher = Utils.getCipherInstance(transform, properties)) {
	           ByteBuffer inBuffer = ByteBuffer.allocateDirect(bufferSize);
	           outBuffer = ByteBuffer.allocateDirect(bufferSize);
	           inBuffer.put(getUTF8Bytes(content));
	           inBuffer.flip(); // ready for the cipher to read it
	           // Show the data is there
	           // Initializes the cipher with ENCRYPT_MODE,key and iv.
	           encipher.init(Cipher.ENCRYPT_MODE, key, iv);
	           // Continues a multiple-part encryption/decryption operation for byte buffer.
	           updateBytes = encipher.update(inBuffer, outBuffer);
	           // We should call do final at the end of encryption/decryption.
	           finalBytes = encipher.doFinal(inBuffer, outBuffer);
	       }
	       outBuffer.flip(); // ready for use as decrypt
	       byte [] encoded = new byte[updateBytes + finalBytes];
	       outBuffer.duplicate().get(encoded);
	}
	
	/**
	 * 解密
	 * @throws Exception 
	 */
	public void decrypt(String key_str,byte [] encoded) throws Exception{
		ByteBuffer outBuffer = null;
		final SecretKeySpec key = new SecretKeySpec(getUTF8Bytes(key_str), "AES");
		final IvParameterSpec iv = new IvParameterSpec(getUTF8Bytes(key_str));
		Properties properties = new Properties();
		//Creates a CryptoCipher instance with the transformation and properties.
		final String transform = "AES/CBC/PKCS5Padding";
		final int bufferSize = 1024;
		// Now reverse the process
	       try (CryptoCipher decipher = Utils.getCipherInstance(transform, properties)) {
	           decipher.init(Cipher.DECRYPT_MODE, key, iv);
	           ByteBuffer decoded = ByteBuffer.allocateDirect(bufferSize);
	           decipher.update(outBuffer, decoded);
	           decipher.doFinal(outBuffer, decoded);
	           decoded.flip(); // ready for use
	       }
	}
	
	
	
	
	
	
	
	
	
	
	public static void main(String[] args) throws Exception {
		   
		   String content = "今天天气不错额阿德斯撒旦撒打算!";

		   String key_str = "jingxiaohu111111";//16位密钥
		   
	       final SecretKeySpec key = new SecretKeySpec(getUTF8Bytes(key_str), "AES");
	       final IvParameterSpec iv = new IvParameterSpec(getUTF8Bytes(key_str));
	       Properties properties = new Properties();
	       //Creates a CryptoCipher instance with the transformation and properties.
	       final String transform = "AES/CBC/PKCS5Padding";
	       final ByteBuffer outBuffer;
	       final int bufferSize = 1024;
	       final int updateBytes;
	       final int finalBytes;
	       try (CryptoCipher encipher = Utils.getCipherInstance(transform, properties)) {

	           ByteBuffer inBuffer = ByteBuffer.allocateDirect(bufferSize);
	           outBuffer = ByteBuffer.allocateDirect(bufferSize);
	           inBuffer.put(getUTF8Bytes(content));

	           inBuffer.flip(); // ready for the cipher to read it
	           // Show the data is there
	           // Initializes the cipher with ENCRYPT_MODE,key and iv.
	           encipher.init(Cipher.ENCRYPT_MODE, key, iv);
	           // Continues a multiple-part encryption/decryption operation for byte buffer.
	           updateBytes = encipher.update(inBuffer, outBuffer);
	           // We should call do final at the end of encryption/decryption.
	           finalBytes = encipher.doFinal(inBuffer, outBuffer);
	       }

	       outBuffer.flip(); // ready for use as decrypt
	       byte [] encoded = new byte[updateBytes + finalBytes];
	       outBuffer.duplicate().get(encoded);

	       // Now reverse the process
	       try (CryptoCipher decipher = Utils.getCipherInstance(transform, properties)) {
	           decipher.init(Cipher.DECRYPT_MODE, key, iv);
	           ByteBuffer decoded = ByteBuffer.allocateDirect(bufferSize);
	           decipher.update(outBuffer, decoded);
	           decipher.doFinal(outBuffer, decoded);
	           decoded.flip(); // ready for use
	       }
	   }

	   /**
	    * Converts String to UTF8 bytes
	    *
	    * @param input the input string
	    * @return UTF8 bytes
	    */
	   private static byte[] getUTF8Bytes(String input) {
	       return input.getBytes(StandardCharsets.UTF_8);
	   }

	   /**
	    * Converts ByteBuffer to String
	    * 
	    * @param buffer input byte buffer
	    * @return the converted string
	    */
	  private static String asString(ByteBuffer buffer) {
	      final ByteBuffer copy = buffer.duplicate();
	      final byte[] bytes = new byte[copy.remaining()];
	      copy.get(bytes);
	      return new String(bytes, StandardCharsets.UTF_8);
	  }
	  
	  /**
	     * 
	     * 创建日期2011-4-25上午10:12:38
	     * 修改日期
	     * 作者：dh *TODO 使用Base64加密算法加密字符串
	     *return
	     */
	    public static String encodeStr(String plainText){
	        byte[] b=plainText.getBytes();
	        Base64 base64=new Base64();
	        b=base64.encode(b);
	        String s=new String(b);
	        return s;
	    }
	    
	    /**
	     * 
	     * 创建日期2011-4-25上午10:15:11
	     * 修改日期
	     * 作者：dh     *TODO 使用Base64加密
	     *return
	     */
	    public static String decodeStr(String encodeStr){
	        byte[] b=encodeStr.getBytes();
	        Base64 base64=new Base64();
	        b=base64.decode(b);
	        String s=new String(b);
	        return s;
	    }
}
