package com.toyproject.backend.config;

import static com.toyproject.backend.enums.SocialType.KAKAO;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.security.oauth2.client.OAuth2ClientProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.oauth2.client.CommonOAuth2Provider;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;

import com.toyproject.backend.service.CustomOAuth2UserService;

import lombok.AllArgsConstructor;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {
//    private MemberService memberService;		

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Override
    public void configure(WebSecurity web) throws Exception
    {
        // static 디렉터리의 하위 파일 목록은 인증 무시 ( = 항상통과 )
        web.ignoring().antMatchers("/css/**", "/js/**", "/img/**", "/lib/**");
    }

    @Override public void configure(HttpSecurity httpSecurity) throws Exception { 
    	httpSecurity.authorizeRequests()
	    	.antMatchers("/", "/oauth2/**", "/login/**", "/css/**", "/images/**", "/js/**", "/console/**", "/favicon.ico/**").permitAll()
	    	.antMatchers("/kakao").hasAuthority(KAKAO.getRoleType())
	    	.anyRequest().authenticated()
	    	.and()
	    	.oauth2Login()
	    	.userInfoEndpoint().userService(new CustomOAuth2UserService()) // 네이버 USER INFO의 응답을 처리하기 위한 설정 
    	.and()
	    	.defaultSuccessUrl("/loginSuccess")
	    	.failureUrl("/loginFailure")
    	.and()
	    	.exceptionHandling()
	    	.authenticationEntryPoint(new LoginUrlAuthenticationEntryPoint("/login"));
    }
 

    
	@Bean
	public ClientRegistrationRepository clientRegistrationRepository(OAuth2ClientProperties oAuth2ClientProperties,
			@Value("${spring.security.oauth2.client.registration.kakao.client-id}") String kakaoClientId,
			@Value("${spring.security.oauth2.client.registration.kakao.client-secret}") String kakaoClientSecret) {
		List<ClientRegistration> registrations = oAuth2ClientProperties.getRegistration().keySet().stream()
				.map(client -> getRegistration(oAuth2ClientProperties, client)).filter(Objects::nonNull)
				.collect(Collectors.toList());
		
		registrations.add(CustomOAuth2Provider.KAKAO.getBuilder("kakao")
				.clientId(kakaoClientId)
				.clientSecret(kakaoClientSecret).jwkSetUri("temp").build());
		
//		registrations.add(CustomOAuth2Provider.NAVER.getBuilder("naver")
//				.clientId(naverClientId)
//				.clientSecret(naverClientSecret).jwkSetUri("temp").build());
		
		return new InMemoryClientRegistrationRepository(registrations);
	}

	private ClientRegistration getRegistration(OAuth2ClientProperties clientProperties, String client) {
		if ("google".equals(client)) {
			OAuth2ClientProperties.Registration registration = clientProperties.getRegistration().get("google");
			return CommonOAuth2Provider.GOOGLE.getBuilder(client).clientId(registration.getClientId())
					.clientSecret(registration.getClientSecret()).scope("email", "profile").build();
		}
		if ("facebook".equals(client)) {
			OAuth2ClientProperties.Registration registration = clientProperties.getRegistration().get("facebook");
			return CommonOAuth2Provider.FACEBOOK.getBuilder(client).clientId(registration.getClientId())
					.clientSecret(registration.getClientSecret())
					.userInfoUri("https://graph.facebook.com/me?fields=id,name,email,link").scope("email").build();
		}
		return null;
	}
}