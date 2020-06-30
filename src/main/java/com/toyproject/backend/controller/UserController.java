package com.toyproject.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.toyproject.backend.service.AccountService;
import com.toyproject.backend.vo.Account;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/user")
public class UserController {
	
	@Autowired
	private AccountService accountService;
	
	@RequestMapping("/create")
	public Account createAccount() {
		Account account = new Account();
		account.setEmail("test@gmail.com");
		account.setPassword("1234");
		account.setAuthority("ROLE_USER");
		return accountService.save(account);
	}
	
	@RequestMapping("/login")
	public void login() {
		accountService.loadUserByUsername("test@gmail.com");
	}
	
	
	@RequestMapping("/login/kakao")
	public void kakaoLogin() {
		System.out.println("login/kakao");
	}
}
