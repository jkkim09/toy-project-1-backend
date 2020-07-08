package com.toyproject.backend.service.response;

import java.util.HashMap;

public enum ResponseEnum{
	SUCESS(200, "정상"),
	ERROR(400, "ERROR");
	
	private int code;
	private String message;
	
	HashMap<String, Object> re = new HashMap<String, Object>();
	
	ResponseEnum(int code, String message) {
		this.code = code;
		this.message = message;
	}
	
	public int getCode () {
		return this.code;
	}
	
	public String getMessage() {
		return this.message;
	}
	
	public HashMap<String, Object> get() {
		re.put("code", this.code);
		re.put("message", this.message);
		return re;
	}
}
