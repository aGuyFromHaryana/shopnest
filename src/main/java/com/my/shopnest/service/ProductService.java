package com.my.shopnest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.my.shopnest.entity.User;
import com.my.shopnest.repo.ProductRepo;
import com.my.shopnest.repo.UserRepository;


@Service
public class ProductService {
 
	@Autowired
	 ProductRepo productRepo;
	
	@Autowired
	UserRepository userRepository;
	
	public void createuser(User u)
	{
		
		productRepo.save(u);
		
	}
	
	public void getuser(String username)
	{
		
	  User u=	userRepository.findByUsername(username);
	  System.out.println(u.getPassword());
		
	}
	

}
