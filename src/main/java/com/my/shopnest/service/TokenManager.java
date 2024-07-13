package com.my.shopnest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.my.shopnest.entity.Token;
import com.my.shopnest.entity.User;
import com.my.shopnest.repo.TokenRepo;


@Component
public class TokenManager {
	
	
	@Autowired
	TokenRepo tokenRepo;
	
	public void associateTokenWithUser(User u,String token)
	{
		Token token1= new Token(u.getUsername(),token);
		
		tokenRepo.save(token1);
		
	}

}
