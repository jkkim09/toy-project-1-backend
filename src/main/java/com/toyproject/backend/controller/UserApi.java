package com.toyproject.backend.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.toyproject.backend.repository.UserRepository;

@RestController
public class UserApi {
	
	@Autowired
	UserRepository userRepository;
	
	@GetMapping({ "", "/" })
	public String getAuthorizationMessage() {
		System.out.println(userRepository.findByEmail("gost203@naver.com"));
		return "home";
	}

	@GetMapping("/login")
	public String login() {
		return "login";
	}

	@PreAuthorize("#oauth2.hasScope('member.info.public')")
	@GetMapping({ "/loginSuccess", "/hello" })
	public String loginSuccess(Principal principal) {
		System.out.println("loginSuccess -->" + principal.toString());
		return "loginSuccess";
	}
	
	@GetMapping("/hello")
	public String hello() {
		return "hello";
	}

	@GetMapping("/loginFailure")
	public String loginFailure() {
		return "loginFailure";
	}
	
	@RequestMapping("/test")
	public String test() {
		return "error";
	}
	
	@RequestMapping("/kakao")
	public String kakao(Principal principal) {
		System.out.println("loginSuccess kakao --> " + principal.toString());
		return "kakao";
	}
	
	@RequestMapping("/naver")
	public String naver(Principal principal) {
		System.out.println("loginSuccess naver --> " + principal.toString());
		return "naver";
	}
}