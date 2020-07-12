package com.toyproject.backend.enums;

public enum KakaoPath {
	V2_USER_ME("/v2/user/me"),
	V1_USER_LOGOUT("/v1/user/logout");
	
	private final String baseUrl = "https://kapi.kakao.com";
	private String path;
	
	KakaoPath(String path) {
		this.path = path;
	}
	
	public String getUrl() {
		return this.baseUrl + this.path;
	}
}
