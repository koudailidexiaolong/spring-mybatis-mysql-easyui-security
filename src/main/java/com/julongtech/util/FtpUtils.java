package com.julongtech.util;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.SocketException;

import org.apache.commons.io.IOUtils;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.util.Base64;
import org.apache.log4j.Logger;

/**
 * ftp连接工具类
 * @author julong
 * @date 2016-7-16 上午09:41:31
 */
public class FtpUtils{

	/**
	 * 日志
	 * @author julong
	 * @date 2016-7-16 上午10:07:04
	 */
	private static final Logger logger = Logger.getLogger(FtpUtils.class);
	/**
	 * ftp用户名
	 * @author julong
	 * @date 2016-7-16 上午09:41:55
	 */
	private static String username = PropertyPlaceholderUtils.getInstance().getProperty("username");

	/**
	 * ftp用户密码
	 * @author julong
	 * @date 2016-7-16 上午09:42:07
	 */
	private static String password = PropertyPlaceholderUtils.getInstance().getProperty("password");

	/**
	 * 登录地址
	 * @author julong
	 * @date 2016-7-16 上午09:45:37
	 */
	private static String hostname = PropertyPlaceholderUtils.getInstance().getProperty("hostname");
	/**
	 * 连接端口号
	 * @author julong
	 * @date 2016-7-16 上午09:45:51
	 */
	private static String port = PropertyPlaceholderUtils.getInstance().getProperty("port");

	/**
	 * ftp连接对象
	 * @author julong
	 * @date 2016-7-16 上午10:09:17
	 */
	private final static FTPClient ftpClient = new FTPClient();


	/**
	 * byte[]转Base64String
	 * @param bytes
	 * @return String
	 * @author julong
	 * @date 2016-7-16 上午09:47:57
	 */
	public static String encodeBase64String(byte[] bytes){
		logger.debug("【FtpUtil】byte[]转Base64String");
		return Base64.encodeBase64String(bytes);
	}

	/**
	 * 解码-base64String 转byte[]
	 * @param base64String
	 * @return  byte[]
	 * @author julong
	 * @date 2016-7-16 上午09:49:25
	 */
	public static byte[] decodeBase64String(String base64String){
		logger.debug("【FtpUtil】base64String 转byte[]");
		return Base64.decodeBase64(base64String);
	}

	/**
	 * 转码-byte[]转base64Byte[]
	 * @param binaryData
	 * @return
	 * @author julong
	 * @date 2016-7-16 上午09:53:30
	 */
	public static byte[] encodeBase64(byte[] binaryData){
		logger.debug("【FtpUtil】byte[]转base64Byte[]");
		return Base64.encodeBase64(binaryData);
	}


	/**
	 * 建立ftp连接对象
	 * @return 是否连接成功 true /false
	 * @throws NumberFormatException
	 * @throws SocketException
	 * @throws IOException
	 * @author julong
	 * @date 2016-7-16 上午10:12:22
	 */
	public static boolean connection() throws NumberFormatException, SocketException, IOException{
		logger.debug("【FtpUtil】-开始建立ftp连接对象");
		//建立ftp连接
		ftpClient.connect(FtpUtils.hostname, Integer.valueOf(FtpUtils.port));
		return ftpClient.login(FtpUtils.username, FtpUtils.password);
	}
	/**
	 * 建立ftp连接对象
	 * @param hostname 主机IP
	 * @param port 端口
	 * @param username 登录名
	 * @param password 登录密码
	 * @return boolean 是否连接成功
	 * @throws NumberFormatException
	 * @throws SocketException
	 * @throws IOException
	 * @author julong
	 * @date 2016-7-18 下午08:31:09
	 */
	public static boolean connection(String hostname, int port,String username, String password) throws NumberFormatException, SocketException, IOException{
		logger.debug("【FtpUtil】-开始建立ftp连接对象");
		//建立ftp连接
		ftpClient.connect(hostname, Integer.valueOf(port));
		return ftpClient.login(username, password);
	}
	/**
	 * 建立ftp连接对象
	 * @param hostname 主机IP
	 * @param username 登录名
	 * @param password 登录密码
	 * @return boolean 是否连接成功
	 * @throws NumberFormatException
	 * @throws SocketException
	 * @throws IOException
	 * @author julong
	 * @date 2016-7-18 下午08:31:09
	 */
	public static boolean connection(String hostname,String username, String password) throws NumberFormatException, SocketException, IOException{
		logger.debug("【FtpUtil】-开始建立ftp连接对象");
		//建立ftp连接
		ftpClient.connect(hostname, FTP.DEFAULT_PORT);
		return ftpClient.login(username, password);
	}
	/**
	 * ftp是否已连接
	 * @return true/false
	 * @author julong
	 * @date 2016-7-16 上午10:15:01
	 */
	public static boolean isConnected(){
		logger.debug("【FtpUtil】-ftp连接是否已经打开"+ftpClient.isConnected());
		return ftpClient.isConnected();
	}

	/**
	 * 根据文件URL获取OutputStream
	 * @param remote 文件绝对URL
	 * @return OutputStream
	 * @throws IOException
	 * @author julong
	 * @date 2016-7-16 上午10:19:14
	 */
	public static OutputStream getFtpFileOutputStream(String remote) throws IOException{
		logger.debug("【FtpUtil】-根据文件URL获取OutputStream");
		ftpClient.setFileType(FTP.BINARY_FILE_TYPE);//设置以二进制传送
		return ftpClient.storeFileStream(remote);
	}
	/**
	 * 根据文件URL获取InputStream
	 * @param remote 文件绝对URL
	 * @return InputStream
	 * @throws IOException
	 * @author julong
	 * @date 2016-7-16 上午10:19:14
	 */
	public static InputStream getFtpFileInputStream(String remote) throws IOException{
		logger.debug("【FtpUtil】-根据文件URL获取InputStream");
		ftpClient.setFileType(FTP.BINARY_FILE_TYPE);//设置以二进制传送
		return ftpClient.retrieveFileStream(remote);
	}

	/**
	 * InputStream 转byte[]
	 * @param inputStream 输入流
	 * @return byte[]
	 * @throws IOException
	 * @author julong
	 * @date 2016-7-16 上午10:22:50
	 */
	public static byte[] getInputStreamToByte(InputStream inputStream) throws IOException{
		logger.debug("【FtpUtil】-InputStream 转byte[]");
		return IOUtils.toByteArray(inputStream);
	}

	/**
	 * 文件上传
	 * @param remote
	 * @param local
	 * @return
	 * @throws IOException
	 * @author julong
	 * @date 2016-7-20 下午01:35:40
	 */
	public static boolean storeFile(String remote, InputStream local) throws IOException{
		logger.debug("【FtpUtil】-InputStream 文件上传");
		return ftpClient.storeFile(remote, local);
	}
	
	
	
	/**
	 * 关闭ftp
	 * @return boolean
	 * @throws IOException
	 * @author julong
	 * @date 2016-7-16 上午10:30:50
	 */
	public static boolean logout() throws IOException{
		logger.debug("【FtpUtil】-关闭ftp");
		return ftpClient.logout();
	}
	/**
	 * @return boolean
	 * @throws IOException
	 * @author julong
	 * @date 2016-7-16 上午10:30:50
	 */
	public static void disconnect() throws IOException{
		logger.debug("【FtpUtil】-disconnect");
		ftpClient.disconnect();
	}

	/**
	 * 下载文件为BASE64字符串
	 * @param remote 文件路径
	 * @return String BASE64字符串
	 * @throws NumberFormatException
	 * @throws SocketException
	 * @throws IOException
	 * @author julong
	 * @date 2016-7-19 下午12:01:06
	 */
	public static String downloadFileToBase64(String remote) throws NumberFormatException, SocketException, IOException{
		InputStream inputStream = null;
		String str = "";
		boolean result = false;
		try{
			logger.debug("【FtpUtil】-文件下载downloadFileToBase64");
			result = FtpUtils.connection();
			if(FtpUtils.isConnected()){
				//开始下载
				inputStream = FtpUtils.getFtpFileInputStream(remote);
				if (null != inputStream) {
					byte[] bytes = FtpUtils.getInputStreamToByte(inputStream);
					str = FtpUtils.encodeBase64String(bytes);
				}
			}
		}finally{
			if(null != inputStream){
				inputStream.close();
			}
			if(result){
				FtpUtils.logout();
				FtpUtils.disconnect();
			}
		}
		return str;
	}
	
	/** 
	 * 下载文件为BASE64字符串
	 * @param hostname 访问地址IP
	 * @param port 端口
	 * @param username 登录名
	 * @param password 登录密码
	 * @param remote 文件路径
	 * @return String
	 * @throws NumberFormatException
	 * @throws SocketException
	 * @throws IOException
	 * @author julong
	 * @date 2016-7-27 下午02:37:43
	 */
	public static String downloadFileToBase64(String hostname, int port,String username, String password,String remote) throws NumberFormatException, SocketException, IOException{
		InputStream inputStream = null;
		String str = "";
		boolean result = false;
		try{
			logger.debug("【FtpUtil】-文件下载downloadFileToBase64");
			result = FtpUtils.connection(hostname,port,username,password);
			if(FtpUtils.isConnected()){
				//开始下载
				inputStream = FtpUtils.getFtpFileInputStream(remote);
				if (null != inputStream) {
					byte[] bytes = FtpUtils.getInputStreamToByte(inputStream);
					str = FtpUtils.encodeBase64String(bytes);
				}
			}
		}finally{
			if(null != inputStream){
				inputStream.close();
			}
			if(result){
				FtpUtils.logout();
				FtpUtils.disconnect();
			}
		}
		return str;
	}
	
	/**
	 * 下载文件为二进制流
	 * @param remote 文件路径
	 * @return  byte[]
	 * @throws NumberFormatException
	 * @throws SocketException
	 * @throws IOException
	 * @author julong
	 * @date 2016-7-19 下午12:01:43
	 */
	public static byte[] downloadFileToByte(String remote) throws NumberFormatException, SocketException, IOException{
		InputStream inputStream = null;
		byte[] bytes = null;
		boolean result = false;
		try{
			logger.debug("【FtpUtil】-文件下载downloadFileToByte");
			result = FtpUtils.connection();
			if(FtpUtils.isConnected()){
				//开始下载
				inputStream = FtpUtils.getFtpFileInputStream(remote);
				//转字符流
				bytes = FtpUtils.getInputStreamToByte(inputStream);
			}
		}finally{
			if(null != inputStream){
				inputStream.close();
			}
			if(result){
				FtpUtils.logout();
				FtpUtils.disconnect();
			}
		}
		return bytes;
	}
	/**
	 * 下载文件为二进制流
	 * @param hostname IP地址
	 * @param port 端口
	 * @param username 登录名 
	 * @param password 登录密码
	 * @param remote 路径
	 * @return  byte[]
	 * @throws NumberFormatException
	 * @throws SocketException
	 * @throws IOException
	 * @author julong
	 * @date 2016-7-27 下午03:12:27
	 */
	public static byte[] downloadFileToByte(String hostname, int port,String username, String password,String remote) throws NumberFormatException, SocketException, IOException{
		InputStream inputStream = null;
		byte[] bytes = null;
		boolean result = false;
		try{
			logger.debug("【FtpUtil】-文件下载downloadFileToByte");
			result = FtpUtils.connection(hostname,port,username,password);
			if(FtpUtils.isConnected()){
				//开始下载
				inputStream = FtpUtils.getFtpFileInputStream(remote);
				//转字符流
				bytes = FtpUtils.getInputStreamToByte(inputStream);
			}
		}finally{
			if(null != inputStream){
				inputStream.close();
			}
			if(result){
				FtpUtils.logout();
				FtpUtils.disconnect();
			}
		}
		return bytes;
	}
	/**
	 * 根据文件路径上传文件文件上传操作
	 * @param pathname 文件路径
	 * @param remote 上传文件的路径
	 * @return boolean
	 * @throws IOException
	 * @author julong
	 * @date 2016-7-20 下午01:37:57
	 */
	public static boolean uploadFileToFtp(String pathname,String remote) throws IOException{
		boolean result = false;
		boolean upload = false;
		InputStream inputStream = null;
		try {
			logger.debug("【FtpUtil】-根据文件路径上传文件文件上传操作uploadFileToFtp");
			result = FtpUtils.connection();
			File file = new File(pathname);
			inputStream = new FileInputStream(file);
			if(FtpUtils.isConnected()){
				ftpClient.setFileType(FTP.BINARY_FILE_TYPE);//设置以二进制传送
				//开始上传
				upload = ftpClient.storeFile(remote, inputStream);
			}
		} finally{
			if(null != inputStream){
				inputStream.close();
			}
			if(result){
				FtpUtils.logout();
				FtpUtils.disconnect();
			}
		}
		return upload;
	}
	/**
	 * 根据文件Base64上传文件操作
	 * @param base64String Base64字符串
	 * @param remote 上传文件的路径（服务器路径）
	 * @return boolean
	 * @throws IOException
	 * @author julong
	 * @date 2016-7-20 下午01:37:57
	 */
	public static boolean uploadByteToFtp(String base64String,String remote) throws IOException{
		boolean result = false;
		boolean upload = false;
		InputStream inputStream = null;
		logger.debug("【FtpUtil】-根据文件路径上传文件文件上传操作--开始");
		try {
			logger.debug("1.【FtpUtil】-建立ftp连接");
			result = FtpUtils.connection();
			//解码-base64String 转byte[]
			byte[] bytes = FtpUtils.decodeBase64String(base64String);
			inputStream = new ByteArrayInputStream(bytes);
			//判断ftp是否已连接
			if(FtpUtils.isConnected()){
				ftpClient.setFileType(FTP.BINARY_FILE_TYPE);//设置以二进制传送
				//开始上传
				upload = ftpClient.storeFile(remote, inputStream);
			}else{
				logger.debug("2.【FtpUtil】-ftp连接失败");
			}
		} finally{
			if(null != inputStream){
				inputStream.close();
			}
			if(result){
				//【FtpUtil】-关闭ftp
				FtpUtils.logout();
				FtpUtils.disconnect();
			}
		}
		return upload;
	}
	/**
	 * @param args
	 * @author julong
	 * @date 2016-7-15 下午06:03:49
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		InputStream inputStream = null;
		 try {
			 
			 System.out.println(System.currentTimeMillis());
			 FTPClient ftpClient = new FTPClient();
			 ftpClient.connect("192.168.10.132");
			boolean result = ftpClient.login("vicp", "vicp");
			inputStream = new FileInputStream(new File("D:/1.mp4"));
			BufferedInputStream b = new BufferedInputStream(inputStream);
			ftpClient.setFileType(FTP.BINARY_FILE_TYPE);//设置以二进制传送
			String uploadName = "/home/vicp/500000000000/1.mp4";
//			String fileName = "2222_aaa.jpg";
//			String dir[] = uploadName.split("/");
//			
//			 FTPFile[] files = ftpClient.listDirectories();
//			 
//			for (int i = 0; i < files.length; i++) {
//				 FTPFile file = files[i];
//				 System.out.println(file.isDirectory());
//				 System.out.println(file.getName());
//			}
			
			boolean upload = ftpClient.storeFile(uploadName, inputStream);
			System.out.println(upload);
			System.out.println(System.currentTimeMillis());
			ftpClient.disconnect();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(null != inputStream){
				try {
					inputStream.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

}
