package com.toyproject.backend.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseDto {

	private int code;

	private String message;
 
	private Object data = "";
	
	public ResponseDto() {};
	
    public ResponseDto(int code, String message, Object data) {
    	this.code = code;
        this.message = message;
        this.data = data;
    }
}
