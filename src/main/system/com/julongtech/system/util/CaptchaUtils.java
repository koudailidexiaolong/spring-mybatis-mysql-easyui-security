package com.julongtech.system.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 生成验证码的公用类
 * @author julong
 * @date 2018-5-31 下午5:33:24
 */
public class CaptchaUtils {

	//日志
	private static final Logger logger = LoggerFactory.getLogger(CaptchaUtils.class);
	/**
	 * @author julong
	 * @date 2018-5-31 下午5:33:21
	 */
	private static final String[] CODE = {
										  "1","2","3","4","5","6","7","8","9","0",
										  "A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z",
										  "1","2","3","4","5","6","7","8","9","0",
										  "a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z",
										  "1","2","3","4","5","6","7","8","9","0"									  
	};

	
	public String captcha = "";
	
	
	//当前对象
	private static CaptchaUtils instance = null;
	
	/**
	 * 单例化对象
	 * @return
	 * @author julong
	 * @date 2016年5月11日 上午11:50:48
	 */
	public static synchronized CaptchaUtils getInstance() {
		if (null == instance) {
			instance = new CaptchaUtils();
		}
		return instance;
	}
	
	
	private CaptchaUtils() {
		
	}
	
	/**
	 * 生成验证码的方法
	 * @param width 宽度
	 * @param height 高度
	 * @return BufferedImage
	 * @author julong
	 * @date 2018-5-31 下午5:33:46
	 */
	public BufferedImage createImage(int width,int height){
		logger.info("【验证码】-开始生成验证码");
		StringBuffer sb = new StringBuffer();  
		Random random = new Random();  
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);  
		//创建画布
		Graphics graphic = image.getGraphics();  
		graphic.setColor(Color.getColor("FFF8DC"));  
		graphic.fillRect(0, 0, width, height);  
		Color[] colors = new Color[] { Color.BLUE, Color.GRAY, Color.GREEN, Color.RED, Color.BLACK, Color.ORANGE,Color.CYAN }; 
		for (int i = 0; i < 50; i++) {  
			graphic.setColor(colors[random.nextInt(colors.length)]);  
			final int x = random.nextInt(100);  
			final int y = random.nextInt(80);  
			final int w = random.nextInt(20);  
			final int h = random.nextInt(20);  
			final int signA = random.nextBoolean() ? 1 : -1;  
			final int signB = random.nextBoolean() ? 1 : -1;  
			graphic.drawLine(x, y, x + w * signA, y + h * signB);  
		}  
		// 在 "画板"上绘制字母  
		graphic.setFont(new Font("楷体", Font.BOLD, 30));  
		for (int i = 0; i < 4; i++) {  
			String rand = String.valueOf(CaptchaUtils.CODE[random.nextInt(CaptchaUtils.CODE.length-1)]); 
			sb.append(rand);  
			graphic.setColor(colors[random.nextInt(colors.length)]);  
			graphic.drawString(rand, i * (width / 4), height - (height / 3));  
		}  
		graphic.dispose();  
		this.captcha = sb.toString();
		logger.info("【验证码】-生成验证码结束:{}",sb.toString());
		return image;
	}
	


	public String getCaptcha() {
		return captcha;
	}


	public void setCaptcha(String captcha) {
		this.captcha = captcha;
	}


	public static void main(String[] args){
		
		BufferedImage image = CaptchaUtils.getInstance().createImage(120,60);
		try {
			ImageIO.write(image, "JPEG", new File("D:/111.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
