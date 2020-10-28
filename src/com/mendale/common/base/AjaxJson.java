package com.mendale.common.base;

import java.util.Map;

/**
 * <p> Title: AjaxJson</p>
 * <p> Description: $.ajax后需要接受的JSON</p>
 * @作者 liliang
 * @创建时间 2014-09-26
 * @版本 1.00
 * @修改记录
 * <pre>
 * 版本   修改人    修改时间    修改内容描述
 * </pre>
 */
public class AjaxJson {
	
	private static final String MSG_SUCCESS = "操作成功！";
	private static final String MSG_FAIL = "操作失败！";
	
	private boolean success = true;// 是否成功
	private String msg = MSG_SUCCESS;// 提示信息
	private Object obj = null;// 其他信息
	private Map<String, Object> attributes;// 其他参数

	public Map<String, Object> getAttributes() {
		return attributes;
	}

	public void setAttributes(Map<String, Object> attributes) {
		this.attributes = attributes;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getObj() {
		return obj;
	}

	public void setObj(Object obj) {
		this.obj = obj;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public void fail() {
		setSuccess(false);
		setMsg(MSG_FAIL);
	}
	
	public void fail(String msg) {
		setSuccess(false);
		setMsg(msg);
	}
}
