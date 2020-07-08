package com.toyproject.backend.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/kakao")
public class KakaoApiController {
	
	@RequestMapping("/test")
	public void test() {
		System.out.println("api kakao test");
	}
}
