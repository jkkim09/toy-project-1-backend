package com.toyproject.backend.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.toyproject.backend.config.security.SocialType;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    @Column
    private String picture;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private SocialType role;

    private String accessToken;
    
    private String tokenType;
    
    @Builder
    public User(String name, String email, String picture, SocialType role, String accessToken, String tokenType) {
        this.name = name;
        this.email = email;
        this.picture = picture;
        this.role = role;
        this.accessToken = accessToken;
        this.tokenType = tokenType;
    }

    public User update(String name, String picture, String accessToken, String tokenType) {
        this.name = name;
        this.picture = picture;
        this.accessToken = accessToken;
        this.tokenType = tokenType;
        return this;
    }

    public String getRoleKey() {
        return this.role.getRoleType();
    }
}