package com.toyproject.backend.config;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

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
        // static ���͸��� ���� ���� ����� ���� ���� ( = �׻���� )
        web.ignoring().antMatchers("/css/**", "/js/**", "/img/**", "/lib/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests() //  // ������ ���� ����     
	        	.antMatchers("/api/user/**/**").permitAll()
	            .antMatchers("/admin/**").hasRole("ADMIN")
	            .antMatchers("/user/myinfo").hasRole("MEMBER")
	            .antMatchers("/api/user/login/kakao").hasAuthority("KAKAO")
	        .and() // �α��� ����
	            .oauth2Login()
	            .defaultSuccessUrl("/api/user/loginSuccess")
            .and() // �α׾ƿ� ����
	            .logout()
	            .logoutRequestMatcher(new AntPathRequestMatcher("/user/logout"))
	            .logoutSuccessUrl("/user/logout/result")
	            .invalidateHttpSession(true)
	        .and()
            .exceptionHandling().accessDeniedPage("/user/denied"); // // 403 ����ó�� �ڵ鸵
    }
    
    @Bean
    public ClientRegistrationRepository clientRegistrationRepository(
      @Value("${spring.security.oauth2.client.registration.kakao.client-id}") String kakaoClientId,
      @Value("${spring.security.oauth2.client.registration.kakao.client-secret}") String kakaoClientSecret) {
    	System.out.println("ClientRegistrationRepository init");
    	ArrayList<ClientRegistration> registrations = new ArrayList<>();
    	registrations.add(CustomOAuth2Provider.KAKAO.getBuilder("kakao").clientId(kakaoClientId).clientSecret(kakaoClientSecret).jwkSetUri("temp").build());
		return new InMemoryClientRegistrationRepository(registrations);
    }
}