package com.toyproject.backend.controller;

import java.security.Principal;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.GetMapping;
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

	@GetMapping({ "/loginSuccess", "/hello" })
	public String loginSuccess(Principal principal) {
		System.out.println(principal.toString());
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
}