package com.toyproject.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.toyproject.backend.entity.UserInfo;

public interface UserRepository extends JpaRepository<UserInfo, Long>{
	UserInfo findUserInfoByEmail(String email);
}
