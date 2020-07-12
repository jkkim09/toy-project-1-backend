package com.toyproject.backend.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.toyproject.backend.dto.SessionUser;
import com.toyproject.backend.enums.KakaoPath;
import com.toyproject.backend.service.KakaoService;

@RestController
@RequestMapping("/api/kakao")
public class KakaoApiController {
	
	@Autowired
	KakaoService kakaoService;
	
	@RequestMapping("/userMe")
	public void test(HttpSession httpSession){
		SessionUser user = (SessionUser) httpSession.getAttribute("user");
		kakaoService.sendApi(KakaoPath.V2_USER_ME.getUrl(), user.getAccessToken());
	}
	
	@RequestMapping("/logout")
	public void logOut (HttpSession httpSession) {
		SessionUser user = (SessionUser) httpSession.getAttribute("user");
		kakaoService.sendApi(KakaoPath.V1_USER_LOGOUT.getUrl(), user.getAccessToken());
		httpSession.removeAttribute("user");
	}
}
