package com.toyproject.backend.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.toyproject.backend.repository.AccountRepository;
import com.toyproject.backend.vo.Account;


@Service
public class AccountService implements UserDetailsService{

	@Autowired
	private AccountRepository repository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public Account save(Account account) {
		return repository.save(account);
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Account account = repository.findByEmail(username);
		account.setPassword(passwordEncoder.encode(account.getPassword()));
		
		ArrayList<GrantedAuthority> authorities = new ArrayList<>();
		authorities.add(new SimpleGrantedAuthority(account.getAuthority()));
		
		System.out.println(account.getPassword());
		return new User(account.getEmail(), account.getPassword(), authorities);
	}

}
