package com.julongtech.system.service.dto;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * 缓存类
 * @author julong
 * @date 2017-12-13 下午5:28:44
 */
public class RedisCacheDTO {

	/**
	 * 缓存的名称
	 * @author julong
	 * @date 2017-12-13 下午5:28:56
	 */
	private String key;
	
	/**缓存的值
	 * @author julong
	 * @date 2017-12-13 下午5:29:07
	 */
	private String value;
	
	/**
	 * 长度
	 * @author julong
	 * @date 2018-6-10 下午2:27:55
	 */
	private String size;
	
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
	
	@Override
	public String toString() {
		return "RedisCacheDTO [key=" + key + ", value=" + value + ", size="
				+ size + "]";
	}
	
	/**
	 * 数据重组
	 * @param set
	 * @return
	 * @author julong
	 * @date 2017-12-13 下午5:33:32
	 */
	public static List<RedisCacheDTO> initCache(Map<String,String> maps){
		List<RedisCacheDTO> redisCacheDTOList = new ArrayList<RedisCacheDTO>();
		Iterator<String> iterator = maps.keySet().iterator();
		RedisCacheDTO redisCacheDTO = null;
		while (iterator.hasNext()) {
			String value = iterator.next();
			redisCacheDTO = new RedisCacheDTO();
			redisCacheDTO.setKey("key");
			redisCacheDTO.setValue(value);
			redisCacheDTO.setSize(maps.get(value));
			redisCacheDTOList.add(redisCacheDTO);
		}
		return redisCacheDTOList;
	}
}
