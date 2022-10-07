package com.julongtech.util.crypto;

import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.net.util.Base64;
import org.apache.log4j.Logger;
/**
 * AES加密算法
 * @author julong
 * @QQ 330359149 
 * @Email koudailidexiaolong@163.com
 * @date 2016-8-24下午05:27:04
 */
public class AESUtil {
	private static final Logger logger = Logger.getLogger(DESUtil.class);
	
	private static KeyGenerator keyGenerator = null;
	
	private static final String AES = "AES";
	
	/**
	 * AES加密
	 * @param data 加密字符串
	 * @param key 密钥 长度为8的倍数
	 * @return byte[]
	 * @author julong
	 * @QQ 330359149 
	 * @Email koudailidexiaolong@163.com
	 * @date 2016-8-24上午11:57:04
	 */
	public static byte[] encryptMode(String data,String key){
		try {
			keyGenerator = KeyGenerator.getInstance(AES);
			keyGenerator.init(128);
			//生成key
			SecretKey secretKey = new SecretKeySpec(key.getBytes(), AES);
			byte[] bytes = secretKey.getEncoded();
			//获取key
			Key AESKey = new SecretKeySpec(bytes, AES);
			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
			cipher.init(Cipher.ENCRYPT_MODE, AESKey);
			return  cipher.doFinal(data.getBytes());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("【AESUtil】加密发生异常",e);
		}
		return null;
	}

	/**
	 * byte[]字符串转base64
	 * @param bytes
	 * @return String
	 * @author julong
	 * @QQ 330359149 
	 * @Email koudailidexiaolong@163.com
	 * @date 2016-8-24下午01:52:36
	 */
	public static String encodeBase64(byte[] bytes){
		return Base64.encodeBase64String(bytes);
	}
	/**
	 * base64 转byte[]
	 * @param base64String
	 * @return byte[]
	 * @author julong
	 * @QQ 330359149 
	 * @Email koudailidexiaolong@163.com
	 * @date 2016-8-24下午02:40:00
	 */
	public static byte[] decodeBase64(String base64String){
		return Base64.decodeBase64(base64String);
	}

	/**
	 * AES解密算法
	 * @param data 加密数据
	 * @param key 密钥 长度为16的倍数
	 * @return byte[]
	 * @author julong
	 * @QQ 330359149 
	 * @Email koudailidexiaolong@163.com
	 * @date 2016-8-24下午02:00:06
	 */
	public static byte[] decryptMode(byte[] data,String key){
		try {
			keyGenerator = KeyGenerator.getInstance(AES);
			keyGenerator.init(128);
			//生成key
			SecretKey secretKey = new SecretKeySpec(key.getBytes(), AES);
			byte[] bytes = secretKey.getEncoded();
			//获取key
			Key AESKey = new SecretKeySpec(bytes, AES);
			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
			cipher.init(Cipher.DECRYPT_MODE, AESKey);
			return cipher.doFinal(data);
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("【AESUtil】解密发生异常",e);
		}
		return null;
	}

	/**
	 * 格式化为byte[] to  String 
	 * @param bytes
	 * @return String
	 * @author julong
	 * @QQ 330359149 
	 * @Email koudailidexiaolong@163.com
	 * @date 2016-8-24下午02:02:29
	 */
	public static String formatByteToString(byte[] bytes){
		return new String(bytes);
	}


	/**
	 * AES 加密
	 * @param data 加密的数据
	 * @param key 密钥
	 * @return String
	 * @author julong
	 * @QQ 330359149 
	 * @Email koudailidexiaolong@163.com
	 * @date 2016-8-24下午02:20:16
	 */
	public static String encrypt(String data,String key){
		byte[]  bytes = encryptMode(data, key);
		return Base64.encodeBase64String(bytes);
	}

	/**
	 * AES 解密
	 * @param data 加密的数据
	 * @param key 密钥
	 * @return String
	 * @author julong
	 * @QQ 330359149 
	 * @Email koudailidexiaolong@163.com
	 * @date 2016-8-24下午02:23:49
	 */
	public static String decrypt(String data,String key){
		byte[] b = decodeBase64(data);
		byte[] bytes = decryptMode(b, key);
		return formatByteToString(bytes);
	}

	/**
	 * @param args
	 * @author julong
	 * @QQ 330359149 
	 * @Email koudailidexiaolong@163.com
	 * @date 2016-8-24下午04:30:04
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//密钥长度16位
		String str1 = AESUtil.encrypt("julong", "1234567890123456");
		System.out.println(str1);
		String str2 = AESUtil.decrypt(str1, "1234567890123456");
		System.out.println(str2);
	}

}
