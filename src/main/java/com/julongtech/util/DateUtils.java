package com.julongtech.util;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
/**
 * 日期处理类
 * @author julong
 * @date 2016-7-25 上午11:41:25
 */
public class DateUtils {

	//设置时区
	static{
		TimeZone zone = TimeZone.getTimeZone("Asia/Shanghai");
		TimeZone.setDefault(zone);
	}

	/**
	 * 获取当前时间
	 * @return Timestamp
	 * @author julong
	 * @date 2016-7-13 上午10:52:33
	 */
	public static Timestamp getTimestamp(){
		Date date = Calendar.getInstance().getTime();
		return new Timestamp(date.getTime());
	}
	/**
	 * 获取时间字符串
	 * @return yyyyMMddhhmmss
	 * @author julong
	 * @date 2016-7-5 上午10:38:34
	 */
	public static String getYYYYMMDDHHMISS(){
		return DateFormatUtils.format(Calendar.getInstance(), "yyyyMMddHHmmss");
	}
	/**
	 * 获取时间字符串
	 * @return yyyymmdd
	 * @author julong
	 * @date 2018-6-3 下午5:47:08
	 */
	public static String getYYYYMMDD(){
		return DateFormatUtils.format(Calendar.getInstance(), "yyyyMMdd");
	}
	/** 
	 * 获取时间字符串
	 * @return yyyy-MM-dd
	 * @author julong
	 * @date 2018-6-3 下午5:55:31
	 */
	public static String getYYYY_MM_DD(){
		return DateFormatUtils.format(Calendar.getInstance(), "yyyy-MM-dd");
	}
	/**
	 * 获取时间字符串
	 * @return yyyy-MM-dd HH:mm:ss
	 * @author julong
	 * @date 2018-6-3 下午5:55:42
	 */
	public static String getYYYY_MM_DD_HH_MI_SS(){
		return DateFormatUtils.format(Calendar.getInstance(), "yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * 格式化开始时间
	 * @param begin
	 * @return 2018-06-06 00:00:00
	 * @throws ParseException
	 * @author julong
	 * @date 2018-6-3 下午6:20:53
	 */
	public static Timestamp parseBeginTime(String begin) throws ParseException{
		begin = begin + " 00:00:00";
		Date dates = org.apache.commons.lang3.time.DateUtils.parseDate(begin, "yyyy-MM-dd HH:mm:ss");
		return new Timestamp(dates.getTime());
	}

	/**
	 * 格式化结束时间
	 * @param end
	 * @return 2018-06-06 23:59:59
	 * @throws ParseException
	 * @author julong
	 * @date 2018-6-3 下午6:21:06
	 */
	public static Timestamp parseEndTime(String end) throws ParseException{
		end = end + " 23:59:59";
		Date dates = org.apache.commons.lang3.time.DateUtils.parseDate(end, "yyyy-MM-dd HH:mm:ss");
		return new Timestamp(dates.getTime());
	}


	/**
	 * @param args
	 * @author julong
	 * @date 2016-7-5 上午10:34:23
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//		simpleDateFormat.applyPattern("yyyy-MM-dd HH:mm:ss SSS");
		//		String str = simpleDateFormat.format(new Date());
		//		System.out.println(str);
		//		System.out.println(DateUtils.getTimestamp());
		//		
//		Timestamp T = DateUtils.getTimestamp();
//		System.out.println(T);
//
//		System.out.println(DateUtils.getYYYYMMDDHHMISS());
//		System.out.println(DateUtils.getYYYYMMDD());
//
//		System.out.println(DateFormatUtils.format(Calendar.getInstance(), "yyyy-MM-dd"));
//		System.out.println(DateFormatUtils.format(Calendar.getInstance(), "yyyy-MM-dd HH:mm:ss"));
//		try {
//			System.out.println(org.apache.commons.lang3.time.DateUtils.parseDate("2018-09-09",  "yyyy-MM-dd"));
//		} catch (ParseException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//
//		String begin = "2018-06-06";
//		begin = begin+" 00:00:00";
//		try {
//			Date dates = org.apache.commons.lang3.time.DateUtils.parseDate(begin, "yyyy-MM-dd HH:mm:ss");
//			System.out.println(new Timestamp(dates.getTime()));
//		} catch (ParseException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		String a = StringEscapeUtils.unescapeHtml4("<html>window.alert()</html>");
		System.out.println(a);
		String b = StringEscapeUtils.escapeHtml4("<html>window.alert()javascript:alert(123); window.onload=alert(1123);</html>");
		System.out.println(b);
	}


	/** 
	 * 
	 * 将日期转换成格式为yyyy-MM-dd hh:mm:ss的日期字符串 
	 * @return yyyy-MM-dd hh:mm:ss
	 * @author zhangpei
	 * @date 2016-7-12 上午10:38:34
	 */ 

	public static String formatFullDateToString(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String strDate = sdf.format(date);
		return strDate;

	}
}
