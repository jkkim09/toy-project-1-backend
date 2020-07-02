package com.toyproject.backend.controller;

import java.security.Principal;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserApi {
	
	@GetMapping({ "", "/" })
	public String getAuthorizationMessage() {
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
		System.out.println("loginSuccess -->" + principal.toString());
		return "kakao";
	}
}