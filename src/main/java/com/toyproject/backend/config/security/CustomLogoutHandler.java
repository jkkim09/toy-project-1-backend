package com.toyproject.backend.config.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import com.toyproject.backend.dto.SessionUser;
import com.toyproject.backend.service.KakaoService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CustomLogoutHandler implements LogoutSuccessHandler{

	@Autowired
	KakaoService kakaoService;
	
	@Override
	public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {
		System.out.println("test logout");
		HttpSession session = request.getSession();
		SessionUser user = (SessionUser) session.getAttribute("user");
		System.out.println(user.getAccessToken());
		session.invalidate();
		response.sendRedirect("/view/index");
	}
}
