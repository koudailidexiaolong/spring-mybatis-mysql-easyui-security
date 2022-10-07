package com.julongtech.util;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.Properties;

import org.apache.commons.io.IOUtils;
import org.apache.commons.net.util.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;

/**
 * sftp连接工具类
 * @author julong
 * @date 2016-7-16 上午09:41:31
 */
public class SftpUtils{

	/**
	 * 日志
	 * @author julong
	 * @date 2016-7-16 上午10:07:04
	 */
	private static final Logger logger = LoggerFactory.getLogger(SftpUtils.class);
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
	private static String port = PropertyPlaceholderUtils.getInstance().getProperty("sftp_port");

	/**
	 * ftp连接对象
	 * @author julong
	 * @date 2016-7-16 上午10:09:17
	 */
	private final static JSch jsch = new JSch();

	/**
	 * byte[]转Base64String
	 * @param bytes
	 * @return String
	 * @author julong
	 * @date 2016-7-16 上午09:47:57
	 */
	public static String encodeBase64String(byte[] bytes){
		logger.info("【FtpUtils】byte[]转Base64String");
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
		logger.info("【FtpUtils】base64String 转byte[]");
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
		logger.info("【FtpUtils】byte[]转base64Byte[]");
		return Base64.encodeBase64(binaryData);
	}

	/**
	 * 获取sftp session连接对象
	 * @return Session
	 * @throws NumberFormatException
	 * @throws JSchException
	 * @author julong
	 * @throws Exception 
	 * @date 2018-5-21 下午2:57:20
	 */
	public static Session connect() throws NumberFormatException, JSchException{
		logger.info("【SftpUtils】-开始建立Sftp连接对象");
		Session session = SftpUtils.jsch.getSession(SftpUtils.username, SftpUtils.hostname, Integer.valueOf(SftpUtils.port));
		session.setPassword(SftpUtils.password);
		session.setConfig("StrictHostKeyChecking", "no");  
		session.connect();  
		return session;
	}

	/**
	 * 获取sftp session 连接对象
	 * @param username 登录名
	 * @param password 密码
	 * @param hostname IP
	 * @param port 端口 默认22
	 * @return Session
	 * @throws NumberFormatException
	 * @throws JSchException
	 * @author julong
	 * @throws Exception 
	 * @date 2018-5-21 下午2:57:41
	 */
	public static Session connect(String username,String password,String hostname,String port) throws NumberFormatException, JSchException{
		logger.info("【SftpUtils】-开始建立Sftp连接对象");
		Session session = jsch.getSession(username, hostname, Integer.valueOf(port));
		session.setPassword(password);
		session.setConfig("StrictHostKeyChecking", "no");  
		session.connect();  
		return session;
	}

	/**
	 * 根据文件路径上传文件文件上传操作
	 * @param pathname 文件路径
	 * @param remote 上传文件的路径
	 * @return boolean
	 * @author julong
	 * @throws JSchException 
	 * @throws NumberFormatException 
	 * @throws SftpException 
	 * @throws IOException 
	 * @date 2018-5-21 下午3:02:34
	 */
	public static boolean uploadFileToSftp(String pathname,String remote) throws NumberFormatException, JSchException, SftpException, IOException{
		logger.info("【SftpUtils】-根据文件路径上传文件文件上传操作uploadFileToSftp");
		boolean upload = false;
		InputStream inputStream = null;
		Session session = null;
		Channel channel = null;
		ChannelSftp channelSftp = null;
		try {
			session = SftpUtils.connect();
			if(session.isConnected()){
				channel = session.openChannel("sftp");
				channel.connect();  
				if(channel.isConnected()){
					channelSftp = (ChannelSftp) channel;  
					File file = new File(pathname);
					inputStream = new FileInputStream(file);
					channelSftp.put(inputStream, remote);
					upload = true;
					logger.info("上传文件成功{}",remote);
				}
			}
		} finally {
			if(null != inputStream){
				inputStream.close();
			}
			if(null != channelSftp && channelSftp.isConnected()){
				channelSftp.disconnect();
			}
			if(null != channel && channelSftp.isConnected()){
				channel.disconnect();
			}
			if(null != session && session.isConnected()){
				session.disconnect();
			}

		}
		return upload;
	}

	/**
	 * 上传文件-byte数组上传文件
	 * @param bytes byte[]
	 * @param remote 上传路径
	 * @return boolean
	 * @throws NumberFormatException
	 * @throws JSchException
	 * @throws SftpException
	 * @throws IOException
	 * @author julong
	 * @date 2018-5-21 下午4:11:36
	 */
	public static boolean uploadByteToSftp(byte[] bytes,String remote) throws NumberFormatException, JSchException, SftpException, IOException{
		logger.info("【SftpUtils】-根据文件路径上传文件文件上传操作uploadFileToSftp");
		boolean upload = false;
		InputStream inputStream = null;
		Session session = null;
		Channel channel = null;
		ChannelSftp channelSftp = null;
		try {
			session = SftpUtils.connect();
			if(session.isConnected()){
				channel = session.openChannel("sftp");
				channel.connect();  
				if(channel.isConnected()){
					channelSftp = (ChannelSftp) channel;  
					inputStream = new ByteArrayInputStream(bytes);
					channelSftp.put(inputStream, remote);
					upload = true;
					logger.info("上传文件成功{}",remote);
				}
			}
		} finally {
			if(null != inputStream){
				inputStream.close();
			}
			if(null != channelSftp && channelSftp.isConnected()){
				channelSftp.disconnect();
			}
			if(null != channel && channelSftp.isConnected()){
				channel.disconnect();
			}
			if(null != session && session.isConnected()){
				session.disconnect();
			}

		}
		return upload;
	}

	/**
	 * 上传base64文件
	 * @param base64String
	 * @param remote
	 * @return
	 * @throws NumberFormatException
	 * @throws JSchException
	 * @throws SftpException
	 * @throws IOException
	 * @author julong
	 * @date 2018-5-21 下午4:34:16
	 */
	public static boolean uploadBase64(String base64String,String remote) throws NumberFormatException, JSchException, SftpException, IOException{
		logger.info("【SftpUtils】-根据文件路径上传文件文件上传操作uploadByteToSftp");
		boolean upload = false;
		InputStream inputStream = null;
		Session session = null;
		Channel channel = null;
		ChannelSftp channelSftp = null;
		try {
			session = SftpUtils.connect();
			if(session.isConnected()){
				channel = session.openChannel("sftp");
				channel.connect();  
				if(channel.isConnected()){
					channelSftp = (ChannelSftp) channel;  
					byte[] bytes = SftpUtils.decodeBase64String(base64String);
					inputStream = new ByteArrayInputStream(bytes);
					channelSftp.put(inputStream, remote);
					upload = true;
					logger.info("上传文件成功{}",remote);
				}
			}
		} finally {
			if(null != inputStream){
				inputStream.close();
			}
			if(null != channelSftp && channelSftp.isConnected()){
				channelSftp.disconnect();
			}
			if(null != channel && channelSftp.isConnected()){
				channel.disconnect();
			}
			if(null != session && session.isConnected()){
				session.disconnect();
			}
		}
		return upload;
	}




	/**
	 * 下载文件-根据路径下载为string
	 * @param remote 文件路径
	 * @return String
	 * @author julong
	 * @throws JSchException 
	 * @throws NumberFormatException 
	 * @throws IOException 
	 * @throws SftpException 
	 * @date 2018-5-21 下午3:45:00
	 */
	public static String downloadFileToString(String remote) throws NumberFormatException, JSchException, IOException, SftpException{
		InputStream inputStream = null;
		Session session = null;
		Channel channel = null;
		ChannelSftp channelSftp = null;
		String str = "";
		try {
			session = SftpUtils.connect();
			if(session.isConnected()){
				channel = session.openChannel("sftp");
				channel.connect();  
				if(channel.isConnected()){
					channelSftp = (ChannelSftp) channel;  
					inputStream = channelSftp.get(remote);
					str = IOUtils.toString(inputStream);
				}
			}
		} finally {
			if(null != inputStream){
				inputStream.close();
			}
			if(null != channelSftp && channelSftp.isConnected()){
				channelSftp.disconnect();
			}
			if(null != channel && channelSftp.isConnected()){
				channel.disconnect();
			}
			if(null != session && session.isConnected()){
				session.disconnect();
			}
		}
		return str;
	}

	/**
	 * 下载文件为byte数组
	 * @param remote
	 * @return byte[]
	 * @throws NumberFormatException
	 * @throws JSchException
	 * @throws SftpException
	 * @throws IOException
	 * @author julong
	 * @date 2018-5-21 下午4:16:14
	 */
	public static byte[] downloadFileToByte(String remote) throws NumberFormatException, JSchException, SftpException, IOException{
		byte[] bytes = null;
		InputStream inputStream = null;
		Session session = null;
		Channel channel = null;
		ChannelSftp channelSftp = null;
		try {
			session = SftpUtils.connect();
			if(session.isConnected()){
				channel = session.openChannel("sftp");
				channel.connect();  
				if(channel.isConnected()){
					channelSftp = (ChannelSftp) channel;  
					inputStream = channelSftp.get(remote);
					bytes = IOUtils.toByteArray(inputStream);
				}
			}
		} finally {
			if(null != inputStream){
				inputStream.close();
			}
			if(null != channelSftp && channelSftp.isConnected()){
				channelSftp.disconnect();
			}
			if(null != channel && channelSftp.isConnected()){
				channel.disconnect();
			}
			if(null != session && session.isConnected()){
				session.disconnect();
			}
		}
		return bytes;
	}

	/**
	 * @param args
	 * @author julong
	 * @date 2016-7-15 下午06:03:49
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("FtpUtil-->connect--ftp连接开始>>>>>>host=192.168.1.222>>>port" + port + ">>>username=" + username);  
//		JSch jsch = new JSch();  
		try {  
			//jsch.getSession("ftpdata", "192.168.1.222", 22);  
			//			Session  session = jsch.getSession("julong", "192.168.1.222", 22);  
			//			System.out.println("ftp---Session created.");  
			//			session.setPassword("julong");  
			//			session.setConfig("StrictHostKeyChecking", "no");  
			//			session.connect();  
			//			System.out.println("session.isConnected():"+session.isConnected());
			//			System.out.println("ftp---Session connected.");  
			//			Channel channel = session.openChannel("sftp");  
			//			channel.connect();  
			//			System.out.println("Opening Channel.");  
			//			ChannelSftp channelSftp = (ChannelSftp) channel;  
			//			System.out.println("ftp---Connected to " + "192.168.1.222");  
			//			//上传文件
			//			channelSftp.put(new FileInputStream(new File("D:/file-read-68.txt")), "/home/julong/ftpdata/news/AAA.txt");
			//			System.out.println("文件上传成功！");
			//
			//			//下载文件
			//			InputStream  inputStream  = channelSftp.get("/home/julong/ftpdata/news/AAA.txt");
			//			byte[] bytes = IOUtils.toByteArray(inputStream);
			//			File file = new File("D:/BBB.txt");
			//			FileUtils.writeByteArrayToFile(file , bytes);
			//			System.out.println("下载文件成功！");

//			String pathname = "D:/AAA.txt";
//			String remote = "/home/julong/ftpdata/news/XXX.txt";
//			boolean upload = SftpUtils.uploadFileToSftp(pathname, remote);
//			System.out.println(upload);
//			String src = "/home/julong/ftpdata/message/send/WSO2018052206510324601020180522065106254.jpg";
//			String inputStream = SftpUtils.downloadFileToString(src);
//			System.out.println(inputStream);
			///home/julong/ftpdata/message/send/WSO2018052206510324601020180522065106254.jpg
//			System.out.println("ASDSA.TXT".substring("ASDSA.TXT".lastIndexOf(".")));
			
			Properties  properties  = PropertiesLoaderUtils.loadAllProperties("config.properties");
			Iterator<Object> iterator = properties.keySet().iterator();
			while (iterator.hasNext()) {
				System.out.println(iterator.next());
			}
			
		}catch (Exception e) {  
			e.printStackTrace();
		}  


	}

}
