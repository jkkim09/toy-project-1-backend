package com.toyproject.backend.config.security;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AccessToken;

import com.toyproject.backend.entity.User;

import lombok.Builder;
import lombok.Getter;

@Getter
public class OAuthAttributes {
    private Map<String, Object> attributes;
    private String nameAttributeKey;
    private String name;
    private String email;
    private String picture;
    private String accessToken;
    private String tokenType;

    @Builder
    public OAuthAttributes(
    		Map<String, Object> attributes, 
    		String nameAttributeKey,
    		String name,
    		String email,
    		String picture,
    		String accessToken,
    		String tokenType) {
        this.attributes = attributes;
        this.nameAttributeKey = nameAttributeKey;
        this.name = name;
        this.email = email;
        this.picture = picture;
        this.accessToken = accessToken;
        this.tokenType = tokenType;
    }

    public static OAuthAttributes of(String registrationId, String userNameAttributeName, Map<String, Object> attributes, OAuth2AccessToken accessToken) {
    	OAuthAttributes returnOAuth;
    	switch(registrationId) {
    		case "kakao":
    			returnOAuth = ofKakao(userNameAttributeName, attributes, accessToken);
    			break;
    		case "google":
    			returnOAuth = ofGoogle(userNameAttributeName, attributes);
    			break;
    		default :
    			returnOAuth = ofGoogle(userNameAttributeName, attributes);
    			break;
    	}
    	return returnOAuth;
    }

    public static OAuthAttributes ofKakao( String userNameAttributeName, Map<String, Object> attributes, OAuth2AccessToken accessToken) {
        LinkedHashMap<String, Object> kakaoAccount = (LinkedHashMap<String, Object>) attributes.get("kakao_account");
        LinkedHashMap<String, Object> profile = (LinkedHashMap<String, Object>) kakaoAccount.get("profile");
        
    	return OAuthAttributes.builder()
                .name((String) profile.get("nickname"))
                .email((String) kakaoAccount.get("email"))
                .picture((String) profile.get("profile_image_url"))
                .attributes(attributes)
                .nameAttributeKey(userNameAttributeName)
                .accessToken(accessToken.getTokenValue())
                .tokenType(accessToken.getTokenType().getValue())
                .build();
    }
    
    
    public static OAuthAttributes ofGoogle( String userNameAttributeName, Map<String, Object> attributes) {
        return OAuthAttributes.builder()
                .name((String) attributes.get("name"))
                .email((String) attributes.get("email"))
                .picture((String) attributes.get("picture"))
                .attributes(attributes)
                .nameAttributeKey(userNameAttributeName)
                .build();
    }

    public User toEntity(String role) {
    	// todo role case 로 수정 필요
        return User.builder()
                .name(name)
                .email(email)
                .picture(picture)
                .role(SocialType.valueOf(role.toUpperCase()))
                .accessToken(accessToken)
                .tokenType(tokenType)
                .build();
    }
}