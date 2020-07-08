package com.toyproject.backend.dto;

import java.io.Serializable;

import com.toyproject.backend.entity.User;

import lombok.Getter;

@Getter
public class SessionUser implements Serializable {
	private long id;
    private String name;
    private String email;
    private String picture;

    public SessionUser(User user) {
    	this.id = user.getId();
        this.name = user.getName();
        this.email = user.getEmail();
        this.picture = user.getPicture();
    }
    
    @Override
    public String toString() {
    	return "[Sesstion User] => id : " + this.id + " ,name : " + this.name + " ,email : " + this.email + " ,picture : " + this.picture;
    }
}
