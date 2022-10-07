package com.julongtech.util.crypto;

import java.security.MessageDigest;

import org.apache.commons.codec.binary.Hex;
import org.apache.log4j.Logger;

/**
 * MD5加密算法
 * @author julong
 * @QQ 330359149 
 * @Email koudailidexiaolong@163.com
 * @date 2016-8-24下午02:58:49
 */
public class MD5Util {

	private static final Logger logger = Logger.getLogger(MD5Util.class);

	private static MessageDigest messageDigest = null;

	private static final String MD5 = "MD5";
	
	/**
	 * MD5加密为byte
	 * @param data
	 * @return byte[]
	 * @author julong
	 * @QQ 330359149 
	 * @Email koudailidexiaolong@163.com
	 * @date 2016-8-24下午03:03:48
	 */
	public static byte[] encodeMode(String data){
		try {
			messageDigest = MessageDigest.getInstance(MD5);
			byte[] bytes = messageDigest.digest(data.getBytes());
			return bytes;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("【MD5Util】加密发生异常",e);
		}
		return null;
	}

	/**
	 * 进行16进制加密转为String
	 * @param bytes md5byte
	 * @return String
	 * @author julong
	 * @QQ 330359149 
	 * @Email koudailidexiaolong@163.com
	 * @date 2016-8-24下午03:05:49
	 */
	public static String encodeHex(byte[] bytes){
		char[] value = Hex.encodeHex(bytes);
		return new String(value);
	}
	
	
	/**
	 * MD5加密
	 * @param data 需要加密的数据
	 * @return String
	 * @author julong
	 * @QQ 330359149 
	 * @Email koudailidexiaolong@163.com
	 * @date 2016-8-24下午03:08:56
	 */
	public static String encode(String data){
		byte[] bytes = encodeMode(data);
		return encodeHex(bytes);
	}
	
	/**
	 * @param args
	 * @author julong
	 * @QQ 330359149 
	 * @Email koudailidexiaolong@163.com
	 * @date 2016-8-24下午02:53:24
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String str = "julong";
		System.out.println(encode(str));

	}

}
