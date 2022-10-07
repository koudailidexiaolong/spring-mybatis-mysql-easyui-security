package com.julongtech.util.crypto;

import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.apache.commons.net.util.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 非对称加密算法 rsa
 * @author julong
 * @date 2018-6-16 上午11:45:26
 */
public class RSAUtil {
	private static final Logger logger = LoggerFactory.getLogger(RSAUtil.class);

	private static  KeyPairGenerator keyPairGenerator = null;
	
	private static  KeyPair keyPair = null;
	
	private static  Cipher cipher = null;
	
	private static  RSAUtil instance = null;
	
	private static final String RSA = "RSA";
	
	/**
	 * 公钥
	 * @author julong
	 * @date 2020年9月13日 下午2:33:34
	 */
	public static final String  publicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCkMSbf6VugeQ/lvDJzwFC0k2bCbyUoCdI0pSi2" +
											"6fZY7Fd6We2tQPfe4mx0pZifNG7+HH/wXiZTgLxYNa4vluo5MhvBgfblbirPYjcgGlPs6D04akkE" +
											"D2uxSXupVamBbFAxbo6mYeczHDrU2JlDAyW7DChlnbRRxJV2IVs10vU5JwIDAQAB";
	/**
	 * 私钥
	 * @author julong
	 * @date 2020年9月13日 下午2:33:42
	 */
	public static final String  privateKey = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAKQxJt/pW6B5D+W8MnPAULSTZsJv" +
											 "JSgJ0jSlKLbp9ljsV3pZ7a1A997ibHSlmJ80bv4cf/BeJlOAvFg1ri+W6jkyG8GB9uVuKs9iNyAa" +
											 "U+zoPThqSQQPa7FJe6lVqYFsUDFujqZh5zMcOtTYmUMDJbsMKGWdtFHElXYhWzXS9TknAgMBAAEC" +
											 "gYAb1ZFHiCHWVG+TfJH4+XM1CX/0Gi9sWC3gatc6GvN+I6K4xSH7qvXaPPwx0hjLdHIUdfRiZTOO" +
											 "GFbVhVQgES9neUT2dtWFvzVIMuzqQpSBswQxM5QPu7IGI4TVoAzjBZ6woTvUzJ1R4AJvUnzkTr4j" +
											 "A20Ld8lpzKiW3sN9PzADMQJBANH9gj7qnmJ9yXQqOTlzdeT0ufxi5uXh4WynlFfLJuusL8bE/vdW" +
											 "DArP2gAgotNlAW7crmbnCNszu+l3JfrYtisCQQDIKsnMAwBJ9WPA3J+8RJmt794+lkPhAbpaBJUt" +
											 "vRoQhf/n7UbQCuWY5kWHCOX3kMGwdzLunPObfWOYG3SRzab1AkEAjMfZpxaiQsOz6JZaMQMQWgQc" +
											 "IHGp/Rp3h+G9mTJQkJWqnib6DJX6ktEQhR9cKj9AO7PllIV1sYe4hmfj5Y2eawJBAINQnsY6Ccx7" +
											 "MY/qrNgj4PXc8N36UlmgepJngz5YWmsQnYuXe+cr3ufp2R73VXaN/qM376/eQ2p0XgwBpv2n/qkC" +
											 "QDNjAxc/V5NFBQgLa8D4PCCD4/UCfAT/O9ek5+z0s1cSA5PUPbn8N2l9ChvNKTFwrLf35/XHc4eko5Qn3JR42Rc=";
			
	
	
	/**
	 * 单例化对象
	 * @return
	 * @author julong
	 * @date 2016年5月11日 上午11:50:48
	 */
	public static synchronized RSAUtil getInstance() {
		if (null == instance) {
			instance = new RSAUtil();
		}
		return instance;
	}
	
	
	private RSAUtil() {
		initKeyPair();
	}

	/**
	 * 初始化密钥信息
	 * @return
	 * @author julong
	 * @date 2018-6-9 上午11:20:51
	 */
	public static void initKeyPair(){
		logger.trace("【RSAUtil加密】-初始化rsa");
		try {
			keyPairGenerator= KeyPairGenerator.getInstance(RSA);
			cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("【RSAUtil加密】-获取rsa发生异常：{}",e);
		}
		keyPairGenerator.initialize(1024);
		keyPair =keyPairGenerator.generateKeyPair();
		
	}
	
	/**
	 * 获取公钥密钥
	 * @return PublicKey
	 * @author julong
	 * @throws NoSuchAlgorithmException 
	 * @throws InvalidKeySpecException 
	 * @date 2018-6-9 上午11:26:26
	 */
	public PublicKey initPublic() throws NoSuchAlgorithmException, InvalidKeySpecException{
		byte[] keyBytes=Base64.decodeBase64(publicKey); 
        X509EncodedKeySpec keySpec=new X509EncodedKeySpec(keyBytes);  
        KeyFactory keyFactory=KeyFactory.getInstance(RSA);  
		return keyFactory.generatePublic(keySpec);
	}
	
	
	/**
	 * 获取私钥密钥
	 * @return PrivateKey
	 * @author julong
	 * @throws NoSuchAlgorithmException 
	 * @throws InvalidKeySpecException 
	 * @date 2018-6-9 上午11:26:40
	 */
	public PrivateKey initPrivate() throws NoSuchAlgorithmException, InvalidKeySpecException{
		byte[] keyBytes=Base64.decodeBase64(privateKey);  
        PKCS8EncodedKeySpec keySpec=new PKCS8EncodedKeySpec(keyBytes);  
        KeyFactory keyFactory=KeyFactory.getInstance(RSA);  
		return keyFactory.generatePrivate(keySpec);
	}
	
	
	
	/**
	 * 公钥加密字符串
	 * @param data String
	 * @return String
	 * @throws IllegalBlockSizeException
	 * @throws BadPaddingException
	 * @throws NoSuchAlgorithmException
	 * @throws NoSuchPaddingException
	 * @throws InvalidKeyException
	 * @throws InvalidKeySpecException 
	 * @author julong
	 * @date 2018-6-9 下午12:08:35
	 */
	public String publicEncrypt(String data) throws IllegalBlockSizeException, BadPaddingException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidKeySpecException{
		logger.trace("【RSAUtil加密】-公钥加密{}",data);
		cipher.init(Cipher.ENCRYPT_MODE, initPublic());
		byte[] publicKeys = cipher.doFinal(data.getBytes());
		return Base64.encodeBase64String(publicKeys);
	}
	
	/**
	 * 私钥解密
	 * @param data byte[]
	 * @return String
	 * @throws NoSuchAlgorithmException
	 * @throws NoSuchPaddingException
	 * @throws InvalidKeyException
	 * @throws IllegalBlockSizeException
	 * @throws BadPaddingException
	 * @throws InvalidKeySpecException 
	 * @author julong
	 * @date 2018-6-9 下午12:10:57
	 */
	public String privateDecrypt(byte[] data) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, InvalidKeySpecException{
		logger.trace("【RSAUtil加密】-私钥加密{}",data);
		cipher.init(Cipher.DECRYPT_MODE, initPrivate());
		byte[] privateKeys = cipher.doFinal(data);
		return new String(privateKeys);
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
		
		 try {
			// String data = "julong11111";
			 RSAUtil.getInstance();
			 System.out.println(Base64.encodeBase64String(RSAUtil.keyPair.getPublic().getEncoded()));
			 System.out.println(Base64.encodeBase64String(RSAUtil.keyPair.getPublic().getEncoded()));
			 
			 
//			 String encrypt =  RSAUtil.getInstance().publicEncrypt(data);
//			 System.out.println("公钥加密："+encrypt);
//			 String aaa = "oDZPsrBVWfwZ04PEuwfy3iWEiSLCyTm6Bh9gl8uoPHjy6HROvs/F9AGdfWBwkmvamUIUObb1FLfZfi++Bxx3nA==";
//			 String decrypt =  RSAUtil.getInstance().privateDecrypt(Base64.decodeBase64(aaa));
//			 System.out.println("私钥解密："+decrypt);
			 
//			KeyPair keyPair =RSAUtil.getInstance().keyPair;
//			
//			PublicKey publicKey = keyPair.getPublic();
//			byte[] pub = publicKey.getEncoded();
//			System.out.println("获取公钥："+Base64.encodeBase64String(keyPair.getPublic().getEncoded()));
//			
//			PrivateKey  privateKey = keyPair.getPrivate();
//			byte[] pri = privateKey.getEncoded();
//			System.out.println("获取私钥："+Base64.encodeBase64String(pri));
//			
//			//公钥加密
//			Cipher cipher1 = Cipher.getInstance("RSA");
//			cipher1.init(Cipher.ENCRYPT_MODE, publicKey);
//			byte[] publicKeys = cipher1.doFinal(data.getBytes());
//			System.out.println("加密："+Base64.encodeBase64String(publicKeys));
//			System.out.println("加密："+Base64.encodeBase64String(publicKeys));
//			System.out.println("加密："+Base64.encodeBase64String(publicKeys));
//			
//			Cipher cipher2 = Cipher.getInstance("RSA");
//			cipher2.init(Cipher.DECRYPT_MODE, privateKey);
//			byte[] privateKeys = cipher2.doFinal(publicKeys);
//			System.out.println("解密："+new String(privateKeys));
			///////////////////////////////////////////////
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
