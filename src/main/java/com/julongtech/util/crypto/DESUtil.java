package com.julongtech.util.crypto;

import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;
import org.apache.log4j.Logger;

/**
 * DES加密算法
 * @author julong
 * @QQ 330359149 
 * @Email koudailidexiaolong@163.com
 * @date 2016-8-24上午11:41:21
 */
public class DESUtil {
	public static final String SPEC = "ACERDVBH2K3M4N6C";
	
	private static final Logger logger = Logger.getLogger(DESUtil.class);
	
	private static KeyGenerator keyGenerator = null;
	
	private static final String DES = "DES";
	
	/**
	 * DES加密
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
			keyGenerator = KeyGenerator.getInstance(DES);
			keyGenerator.init(56);
			//获取key
			DESKeySpec desKey = new DESKeySpec(key.getBytes());
			SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance(DES);
			Key convertKey  = secretKeyFactory.generateSecret(desKey);
			Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
			cipher.init(Cipher.ENCRYPT_MODE, convertKey);
			return  cipher.doFinal(data.getBytes());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("【DESUtil】加密发生异常",e);
		}
		return null;
	}
	
	/**
	 * byte[]字符串转16进制
	 * @param bytes
	 * @return String
	 * @author julong
	 * @QQ 330359149 
	 * @Email koudailidexiaolong@163.com
	 * @date 2016-8-24下午01:52:36
	 */
	public static String encodeHex(byte[] bytes){
		char[] value = Hex.encodeHex(bytes);
		return new String(value);
	}
	/**
	 * 16进制转换为 
	 * @param data
	 * @return byte[]
	 * @author julong
	 * @QQ 330359149 
	 * @Email koudailidexiaolong@163.com
	 * @date 2016-8-24下午02:40:00
	 */
	public static byte[] decodeHex(char[] data){
		try {
			return Hex.decodeHex(data);
		} catch (DecoderException e) {
			// TODO Auto-generated catch block
			logger.error("【DESUtil】16进制发生异常",e);
		}
		return null;
	}
	
	/**
	 * DES解密算法
	 * @param data 加密数据
	 * @param key 密钥 长度为8的倍数
	 * @return byte[]
	 * @author julong
	 * @QQ 330359149 
	 * @Email koudailidexiaolong@163.com
	 * @date 2016-8-24下午02:00:06
	 */
	public static byte[] decryptMode(byte[] data,String key){
		try {
			keyGenerator = KeyGenerator.getInstance(DES);
			keyGenerator.init(56);
			//获取key
			DESKeySpec desKey = new DESKeySpec(key.getBytes());
			SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance(DES);
			Key convertKey  = secretKeyFactory.generateSecret(desKey);
			Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
			cipher.init(Cipher.DECRYPT_MODE, convertKey);
			return cipher.doFinal(data);
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("【DESUtil】解密发生异常",e);
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
	 * DES 加密
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
		return encodeHex(bytes);
	}
	
	/**
	 * DES 解密
	 * @param data 加密的数据
	 * @param key 密钥
	 * @return String
	 * @author julong
	 * @QQ 330359149 
	 * @Email koudailidexiaolong@163.com
	 * @date 2016-8-24下午02:23:49
	 */
	public static String decrypt(String data,String key){
		byte[] b = decodeHex(data.toCharArray());
		byte[] bytes = decryptMode(b, key);
		return formatByteToString(bytes);
	}
	
	/**
	 * @param args
	 * @author julong
	 * @QQ 330359149 
	 * @Email koudailidexiaolong@163.com
	 * @date 2016-8-23下午09:25:02
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//8位密码
		String str = encrypt("julong","12345678");
		System.out.println(str);
		System.out.println(decrypt(str,"12345678"));
	}
}
