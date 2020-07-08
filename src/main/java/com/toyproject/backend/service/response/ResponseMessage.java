package com.toyproject.backend.service.response;

import java.util.HashMap;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.toyproject.backend.dto.response.ResponseDto;

@Service
public class ResponseMessage {
	ResponseDto rDto;
	
	public ResponseEntity<ResponseDto> getResponseMessage (int code) {
		HashMap<String, Object> codeAndMessage = select(code);
		rDto = new ResponseDto();
		rDto.setCode((int) codeAndMessage.get("code"));
		rDto.setMessage((String) codeAndMessage.get("message"));
		return new ResponseEntity<ResponseDto>(rDto, (HttpStatus) codeAndMessage.get("http"));
	}
	
	public ResponseEntity<ResponseDto> getResponseMessage (int code, Object data) {
		HashMap<String, Object> codeAndMessage = select(code);
		rDto = new ResponseDto();
		rDto.setCode((int) codeAndMessage.get("code"));
		rDto.setMessage((String) codeAndMessage.get("message"));
		rDto.setData(data);
		return new ResponseEntity<ResponseDto>(rDto, (HttpStatus) codeAndMessage.get("http"));
	}
	
	public ResponseEntity<ResponseDto> getResponseMessage (int code, HttpHeaders header, Object data) {
		HashMap<String, Object> codeAndMessage = select(code);
		rDto = new ResponseDto();
		rDto.setCode((int) codeAndMessage.get("code"));
		rDto.setMessage((String) codeAndMessage.get("message"));
		rDto.setData(data);
		return new ResponseEntity<ResponseDto>(rDto, header, (HttpStatus) codeAndMessage.get("http"));
	}
	
	public HashMap<String, Object> select (int code) {
		HashMap<String, Object> re;
		switch (code) {
		case 200:
			re = ResponseEnum.SUCESS.get();
			re.put("http", HttpStatus.OK);
			break;
		case 400:
			re = ResponseEnum.ERROR.get();
			re.put("http", HttpStatus.BAD_REQUEST);
			break;
		default:
			re = ResponseEnum.ERROR.get();
			re.put("http", HttpStatus.BAD_REQUEST);
			break;
		}
		return re;
	}
}
