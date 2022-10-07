package com.julongtech.page;

import java.io.Serializable;

/**
 * 分页的类
 * @author julong
 * @date 2017-10-24 下午3:35:21
 */
public class PageParam<T extends Object> implements Serializable{
	/**
	 * @author julong
	 * @date 2017-10-24 下午3:36:11
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 分页的行数
	 */
	private String rows;
	/**
	 * 当前页码
	 */
	private String page;
	
	public String getPage() {
		return page;
	}
	public void setPage(String page) {
		this.page = page;
	}
	public String getRows() {
		return rows;
	}
	public void setRows(String rows) {
		this.rows = rows;
	}
	
	@Override
	public String toString() {
		return "PageParam [rows=" + rows + ", page=" + page + "]";
	}

}
