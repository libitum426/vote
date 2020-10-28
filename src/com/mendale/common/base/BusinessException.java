package com.mendale.common.base;

/**
 * <p> Title: BusinessException</p>
 * <p> Description: </p>
 * @作者 liliang
 * @创建时间 2014-09-26
 * @版本 1.00
 * @修改记录
 * <pre>
 * 版本   修改人    修改时间    修改内容描述
 * </pre>
 */
public class BusinessException extends RuntimeException {
	private static final long serialVersionUID = -8377971941509579126L;
	
	private Throwable cause;
	
	public BusinessException(String msg) {
		super(msg);
	}
	
	public BusinessException(String msg, Throwable ex) {
		super(msg);
		this.cause = ex;
	}
	
	public BusinessException(Throwable ex) {
		this.cause = ex;
	}
	
	public Throwable getCause(){   
		return (this.cause == null ? this :this.cause);   
	}
}
