package com.toyproject.backend.repository;

import java.util.HashMap;
import java.util.Random;

import org.springframework.stereotype.Repository;

import com.toyproject.backend.vo.Account;

@Repository
public class AccountRepository {
	private HashMap<String, Account> accounts = new HashMap<String, Account>();
	
	public Account save(Account account) {
		Random random = new Random();
		account.setId(random.nextInt());
		accounts.put(account.getEmail(), account);
		return account;
	}
	
	public Account findByEmail(String username) {
		return accounts.get(username);
	}
}
