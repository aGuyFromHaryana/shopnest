package com.my.shopnest.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.boot.context.properties.ConstructorBinding;

import lombok.Data;
import lombok.NoArgsConstructor;


@Entity(name = "user")
@Data
@NoArgsConstructor
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer id;
	

	@Column(unique = true)
	String username;
	
	String password;
	
	
	public User(String username, String password) {
		this.username = username;
		this.password = password;
	}


	
	
	
	


}
