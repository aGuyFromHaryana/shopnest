package com.my.shopnest.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class ProductController {
	
	
	@GetMapping("hello")
	public String hello()
	{
		
		return "server is up";
	}
	

}
