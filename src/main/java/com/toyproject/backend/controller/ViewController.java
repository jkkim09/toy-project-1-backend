package com.toyproject.backend.controller;

import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.toyproject.backend.dto.SessionUser;

@RestController
@RequestMapping
public class ViewController {
	@RequestMapping({"/", "/view/index"})
	public ModelAndView index (ModelAndView mv) {
		mv.setViewName("index");
		return mv;
	}
	
	@RequestMapping("/view/main")
	public ModelAndView main (ModelAndView mv, HttpSession hs) {
		SessionUser user = (SessionUser) hs.getAttribute("user");
		System.out.println(user.toString());
		mv.setViewName("index");
		return mv;
	}
	
	@RequestMapping("/view/loginSuccess")
	public ModelAndView loginSuccess (ModelAndView mv, HttpSession hs) {
		mv.setViewName("index");
		return mv;
	}
}
