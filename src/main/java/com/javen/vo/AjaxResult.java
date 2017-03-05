package com.javen.vo;

import com.jfinal.kit.JsonKit;

/**
 * 功能描述: 封装ajax返回
 */
public class AjaxResult {

	// 标记成功失败，默认0：成功，1：失败、用于alert，2：失败、用于confirm
	private int code = 0;

	// 返回的中文消息
	private String message;

	// 成功时携带的数据
	private Object data;
	
	public void setCode(int code) {
		this.code = code;
	}
	
	public int getCode() {
		return code;
	}

	public void setMessage(String message) {
		 this.message =  message;
	}
	
	public String getMessage() {
		return message;
	}

	public void setData(Object data) {
		this.data = data;
	}
	
	public Object getData() {
		return data;
	}
	
	// 校验错误
	public boolean hasError() {
		return this.code != 0;
	}

	// 添加错误，用于alertError
	public AjaxResult addError(String message) {
		this.message = message;
		this.code = 1;
		return this;
	}
	//添加错误，自定义错误信息
	public AjaxResult addCustomError(int code,String message) {
		this.message = message;
		this.code = code;
		return this;
	}

	/**
	 * 用于Confirm的错误信息
	 * @param addConfirmError
	 * @return AjaxResult
	 */
	public AjaxResult addConfirmError(String message) {
		this.message = message;
		this.code = 2;
		return this;
	}

	/**
	 * 封装成功时的数据
	 * @param data
	 * @return AjaxResult
	 */
	public AjaxResult success(Object data) {
		this.data = data;
		return this;
	}

	@Override
	public String toString() {
		return JsonKit.toJson(this);
	}
}
