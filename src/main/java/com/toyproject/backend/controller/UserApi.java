package com.toyproject.backend.controller;

import java.security.Principal;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.toyproject.backend.dto.SessionUser;
import com.toyproject.backend.dto.response.ResponseDto;
import com.toyproject.backend.repository.UserRepository;
import com.toyproject.backend.service.response.ResponseMessage;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class UserApi {
	
	private final HttpSession httpSession;

	@Autowired
	ResponseMessage responseMessage;
	
	@Autowired
	UserRepository userRepository;
	

	@GetMapping("/loginFailure")
	public String loginFailure() {
		return "loginFailure";
	}
	
	@RequestMapping("/errorLogic")
	public ResponseEntity<ResponseDto> errorLogic() {
		return responseMessage.getResponseMessage(400);
	}
	
	@RequestMapping("/kakao")
	public ResponseEntity<ResponseDto> kakao(Principal principal) {
		SessionUser user = (SessionUser) httpSession.getAttribute("user");
		System.out.println(httpSession.getAttribute("user"));
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.set("X-RateLimit-Limit", "1000");
		responseHeaders.set("X-RateLimit-Remaining", "500");
		responseHeaders.set("X-RateLimit-Reset", "1457020923");
		return responseMessage.getResponseMessage(200, responseHeaders, user);
	}
	
	@RequestMapping("/naver")
	public String naver(Principal principal) {
		return "naver";
	}
}