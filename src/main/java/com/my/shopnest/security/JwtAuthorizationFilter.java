package com.my.shopnest.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.my.shopnest.service.CustomUserDetailsService;
import com.my.shopnest.utility.jwtutil;

import io.jsonwebtoken.Claims;

@Component
public class JwtAuthorizationFilter extends OncePerRequestFilter {

	private final jwtutil jwtUtil;
	private final ObjectMapper mapper;
	
	@Autowired
    private  CustomUserDetailsService userDetailsService;

	public JwtAuthorizationFilter(jwtutil jwtUtil, ObjectMapper mapper) {
		this.jwtUtil = jwtUtil;
		this.mapper = mapper;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		Map<String, Object> errorDetails = new HashMap<>();

		try {
			String accessToken = jwtUtil.resolveToken(request);
			if (accessToken == null) {
				System.out.println("ddddddddddddddd");
				System.out.println("rrrrrrrrrrr");

				filterChain.doFilter(request, response);
				return;
			}
			System.out.println("token : " + accessToken);
			Claims claims = jwtUtil.resolveClaims(request);
			
			
			 List<GrantedAuthority> authorities =
		                Arrays.stream(claims.get("roles").toString().split(","))
		                        .map(SimpleGrantedAuthority::new)
		                        .collect(Collectors.toList());
         
			System.out.println("xxxxxxxx");
			if (claims != null & jwtUtil.validateClaims(claims)) {
				String username = claims.getSubject();
				System.out.println("username : " + username);
				UserDetails userDetails= userDetailsService.loadUserByUsername(username);
				System.out.println(userDetails.getAuthorities());
				Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, "", authorities);

				SecurityContextHolder.getContext().setAuthentication(authentication);
				
//				Establishing Authentication State: By creating and setting an
//				Authentication object, you establish the authentication state for the current request. This allows Spring Security to 
//				perform role-based access control and other security checks.
			}

		} catch (Exception e) {
			errorDetails.put("message", "Authentication Error");
			errorDetails.put("details", e.getMessage());
			response.setStatus(HttpStatus.FORBIDDEN.value());
			response.setContentType(MediaType.APPLICATION_JSON_VALUE);

			mapper.writeValue(response.getWriter(), errorDetails);

		}
		
		filterChain.doFilter(request, response);
	}
}
