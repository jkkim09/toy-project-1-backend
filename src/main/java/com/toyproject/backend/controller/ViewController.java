package com.toyproject.backend.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/view")
public class ViewController {
	@RequestMapping("/index")
	public ModelAndView index (ModelAndView mv) {
		mv.setViewName("index");
		return mv;
	}
	
	@RequestMapping("/main")
	public ModelAndView main (ModelAndView mv) {
		mv.setViewName("index");
		return mv;
	}
}
