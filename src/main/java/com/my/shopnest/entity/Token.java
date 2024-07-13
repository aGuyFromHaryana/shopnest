
package com.my.shopnest.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tokens")
@NoArgsConstructor
@Data
public class Token {

	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer tokenId;

	String username;
	String accessToken;
	
	public Token(String username, String accessToken) {
		super();
		this.username = username;
		this.accessToken = accessToken;
	}


}
