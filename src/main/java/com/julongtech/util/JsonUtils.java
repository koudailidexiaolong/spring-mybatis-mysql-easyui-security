package com.julongtech.util;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * json处理类
 * @author julong
 * @date 2016-6-23 上午10:20:55
 */
public class JsonUtils {
	private static final Logger logger = LoggerFactory.getLogger(JsonUtils.class);
	private final static ObjectMapper objectMapper = new ObjectMapper();
	static {
		objectMapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
	}
	/**
	 * 格式化对象为json字符串
	 * @param obj
	 * @return
	 * @throws Exception
	 * @author julong
	 * @throws IOException 
	 * @date 2016-6-23 上午11:23:56
	 */
	public static String formatObjectToJson(Object obj) throws IOException{
		String result = null;
		logger.debug("【JsonUtil】格式化Object为string字符串操作");
		result = objectMapper.writeValueAsString(obj);
		return result;
	}
	/**
	 * 格式化对象为byte[]
	 * @param obj
	 * @return
	 * @throws Exception
	 * @author julong
	 * @throws IOException 
	 * @date 2016-6-23 上午11:25:29
	 */
	public static byte[] formatObjectToBytes(Object obj) throws IOException{
		byte[] result = null;
		logger.debug("【JsonUtil】格式化Object为string字符串操作");
		result = objectMapper.writeValueAsBytes(obj);
		return result;
	}
	
	
	/**
	 * 格式化参数为map对象的方法
	 * @param data
	 * @return
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 * @throws IOException
	 * @author julong
	 * @date 2016-7-5 上午10:47:05
	 */
	@SuppressWarnings("unchecked")
	public static Map<String,Object> formatDataToMap(String data) throws JsonParseException, JsonMappingException, IOException{
		logger.debug("【JsonUtil】格式化string为Map操作");
		Map<String,Object> maps = objectMapper.readValue(data, Map.class);
		return maps;
	}
	

	/**
	 * 转换json为List<T> 对象
	 * @param data json数据
	 * @param clazz 需要转换的类
	 * @return <T> List<Object>
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 * @throws IOException
	 * @author julong
	 * @date 2016-7-29 上午10:00:44
	 */
	public static <T> List<T> formatDataToList(String data,Class<?> clazz) throws JsonParseException, JsonMappingException, IOException{
		logger.debug("【JsonUtil】格式化string为List<Object>操作");
		JavaType javaType  = objectMapper.getTypeFactory().constructParametricType(List.class, clazz);
		List<T> list = objectMapper.readValue(data, javaType);
		return list;
	}
	
	
	/**
	 * @param args
	 * @author julong
	 * @date 2016-6-23 上午10:20:52
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	}

}
