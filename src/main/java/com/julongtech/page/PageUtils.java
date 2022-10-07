package com.julongtech.page;

import java.util.Hashtable;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.pagehelper.PageInfo;

/**
 * 自定义页面对象
 * @author julong
 * @date 2016-6-30 上午10:23:04
 */
public class PageUtils {
	private static final Logger logger = LoggerFactory.getLogger(PageUtils.class);

	/**
	 * 格式化分页输出结果
	 * @param page
	 * @return Map<String,Object>
	 * @author julong
	 * @date 2017-10-24 下午1:11:49
	 */
	public static Map<String,Object> formatPage(PageInfo<? extends Object> page){
		Map<String,Object> maps = new Hashtable<String, Object>();
		maps.put("rows", page.getList());
		maps.put("total", page.getTotal());
		logger.trace("【分页参数格式化返回】{}",maps);
		return maps;
	}
	
	/**
	 * 格式化输入的分页参数
	 * @param page
	 * @return
	 * @author julong
	 * @date 2017-10-24 下午3:45:55
	 */
	public static PageInfo<? extends Object> getPageHelper(PageParam<? extends Object> page){
		PageInfo<? extends Object> pageInfo = new PageInfo<Object>();
		pageInfo.setPageSize(Integer.valueOf(page.getRows()));
		pageInfo.setPageNum(Integer.valueOf(page.getPage()));
		logger.trace("【分页参数格式化接收】{}",pageInfo);
		return pageInfo;
		
	}
}
