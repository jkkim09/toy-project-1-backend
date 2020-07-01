package com.toyproject.backend.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Builder;
import lombok.Data;

@Entity
@Table(name = "user")
@Data
public class UserInfo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_number")
	private long userNumber;
	
	@Column(nullable = false, columnDefinition = "varchar(50) default 'ROLE_USER'")
	private String authority;

	@Column(nullable = false)
	private String email;
	
	@Column(nullable = false)
	private String password;
	
	@Builder
	public void userEntity(String authority, String email, String password) {
		this.authority = authority;
		this.email = email;
		this.password = password;
	}
	
}
