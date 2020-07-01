package com.toyproject.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
//	
//	@RequestMapping("/login")
//	public void login() {
//		accountService.loadUserByUsername("test@gmail.com");
//	}
	
	@RequestMapping("/login/kakao")
	public String kakaoLogin(@RequestParam("code") String code) {
		System.out.println("login/kakao : " + code);
		return code;
	}
	
	
	@RequestMapping("/test")
	public OAuth2AccessToken accessToken(@RegisteredOAuth2AuthorizedClient OAuth2AuthorizedClient authorizedClient) {
		return authorizedClient.getAccessToken();
	}
	
	@GetMapping({ "", "/" })
	public String getAuthorizationMessage() {
		return "home";
	}

	@GetMapping("/login")
	public String login() {
		return "login";
	}

	@GetMapping({ "/loginSuccess", "/hello" })
	public String loginSuccess() {
		return "hello";
	}

	@GetMapping("/loginFailure")
	public String loginFailure() {
		return "loginFailure";
	}

}
