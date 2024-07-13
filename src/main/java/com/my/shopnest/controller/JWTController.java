package com.my.shopnest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.my.shopnest.entity.User;
import com.my.shopnest.service.TokenManager;
import com.my.shopnest.utility.jwtutil;

@RestController
@RequestMapping("/authenticate")
public class JWTController {
	
	@Autowired
	private  AuthenticationManager authenticationManager;
    
	@Autowired
    private jwtutil jwtUtil;
	
	@Autowired
	TokenManager tokenManager;
	
    
    
    @ResponseBody
    
    @RequestMapping(value = "login",method = RequestMethod.POST)
    public String login(@RequestBody User loginReq)  {

     
            Authentication authentication =
                    authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginReq.getUsername(), loginReq.getPassword()));
            
//			SecurityContextHolder.getContext().setAuthentication(authentication);

            String token = jwtUtil.createToken(loginReq,  authentication);
            tokenManager.associateTokenWithUser(loginReq, token); 
            return token;
        
        
    }
	
	
	
	
	
}
