package com.toyproject.backend.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/user")
public class User {
	
	@RequestMapping("/test")
	public void test() {
		System.out.println("test");
	}
}
