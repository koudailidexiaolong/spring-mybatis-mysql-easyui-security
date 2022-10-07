package com.julongtech.exception;

/**
 * 控制层异常处理
 * @author julong
 * @date 2022年9月10日 上午11:05:34
 * @desc 
 */
public class ActionException extends RuntimeException {

	/**
	 * @author julong
	 * @date 2022年9月10日 上午11:00:20
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 异常信息
	 * @author julong
	 * @date 2022年9月10日 上午11:02:16
	 */
	private String message;


	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public ActionException(String message) {
		super();
		this.message = message;
	}
	
	public ActionException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	public ActionException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "ActionException [message=" + message + "]";
	}
	
}
