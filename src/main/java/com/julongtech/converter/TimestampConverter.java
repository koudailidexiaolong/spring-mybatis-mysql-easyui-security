package com.julongtech.converter;

import java.sql.Timestamp;

import org.apache.commons.lang3.StringUtils;
import org.springframework.core.convert.converter.Converter;

/**
 * Timestamp日期类型转换
 * 解决spring中接收参数的时候时间类型不带时分秒
 * @author julong
 * @date 2016-7-13 上午09:47:20
 */
public class TimestampConverter implements Converter<String,Timestamp>{
	
	@Override
	public Timestamp convert(String arg0) {
		// TODO Auto-generated method stub
		if(StringUtils.isNotEmpty(arg0.trim())){
			if(arg0.length() == 10){
				arg0 += " 00:00:00";
			}
			Timestamp timestamp = Timestamp.valueOf(arg0);
			return timestamp;
		}
		return null;

	}
}
