package com.julongtech.system.aspect;

/**
 * 枚举日志方法类型
 * @author julong
 * @date 2018-5-15 上午11:33:50
 */
public enum LoggerMethod {

	/**
	 * 登录
	 * @author julong
	 * @date 2018-5-15 上午11:32:15
	 */
	LOGIN,
	/**
	 * 退出
	 * @author julong
	 * @date 2018-5-15 上午11:32:17
	 */
	LOGOUT,
	/**
	 * 查询
	 * @author julong
	 * @date 2018-5-15 上午11:32:20
	 */
	SELECT,
	/**
	 * 删除
	 * @author julong
	 * @date 2018-5-15 上午11:32:38
	 */
	DELETE,
	/**
	 * 更新
	 * @author julong
	 * @date 2018-5-15 上午11:32:44
	 */
	UPDATE,
	/**
	 * 新增
	 * @author julong
	 * @date 2018-5-15 上午11:32:53
	 */
	INSERT,
	/**
	 * 导入
	 * @author julong
	 * @date 2018-5-15 上午11:33:01
	 */
	IMPORT,
	/**
	 * 导出
	 * @author julong
	 * @date 2018-5-15 上午11:33:06
	 */
	EXPORT,
	/**
	 * 批量删除
	 * @author julong
	 * @date 2018-5-15 上午11:33:10
	 */
	BATCH_DELETE,
	/**
	 * 批量更新
	 * @author julong
	 * @date 2018-5-15 上午11:33:20
	 */
	BATCH_UPDATE,
	/**
	 * 上传的方法
	 * @author julong
	 * @date 2018-5-15 下午2:51:29
	 */
	UPLOAD,
	/**
	 * 下载的方法
	 * @author julong
	 * @date 2018-5-15 下午2:51:33
	 */
	DOWNLOAD,
	/**
	 * 加载界面
	 * @author julong
	 * @date 2018-5-15 下午3:28:47
	 */
	LOAD_PAGE
}
