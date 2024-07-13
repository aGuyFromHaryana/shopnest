package com.my.shopnest.controller;

import javax.annotation.security.RolesAllowed;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.my.shopnest.entity.Token;
import com.my.shopnest.entity.User;
import com.my.shopnest.service.ProductService;
import com.my.shopnest.service.TokenManager;


@RestController
public class UserController {
	
	
	@Autowired
	 ProductService ProductService;
	
	
	
	@PostMapping("create/user")
    @PreAuthorize("hasRole('ROLE_USER')")
	public ResponseEntity<String> createUser(@RequestBody User user)
	{
		ProductService.createuser(user);	
		return  ResponseEntity.ok("created");
	}
	
	@PostMapping("create/user1")
    @PreAuthorize("hasAuthority('ROLE_ADMI')")
	public ResponseEntity<String> createUser1(@RequestBody User user)
	{
		ProductService.createuser(user);	
		return  ResponseEntity.ok("created");
	}

}
