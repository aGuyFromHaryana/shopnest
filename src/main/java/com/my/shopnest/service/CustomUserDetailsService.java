package com.my.shopnest.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.my.shopnest.entity.User;
import com.my.shopnest.repo.UserRepository;


@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    public Collection<? extends GrantedAuthority> mapRolesToAuthorities(Set<String> roles){
		return roles.stream().map(role -> new SimpleGrantedAuthority(role)).collect(Collectors.toList());
	}


    @Override
    public UserDetails loadUserByUsername(String user1) throws UsernameNotFoundException {
       
    	User user = userRepository.findByUsername(user1);
    	if(user==null)
    		System.out.println("nullll");
        System.out.println(user.getUsername());

        System.out.println(user.getPassword());
        Set<String> roles = new HashSet<>();
        roles.add("ROLE_ADMI");
        UserDetails userDetails = new org.springframework.security.core.userdetails.User(user.getUsername(),
        		user.getPassword(), mapRolesToAuthorities(roles));
        		

        return userDetails;
    }
}
