package com.toyproject.backend.vo;

import lombok.Data;

@Data
public class Account {
	private Integer id;
	private String email;
	private String password;
	private String authority;
}
