package com.my.shopnest.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.my.shopnest.entity.Token;

public interface TokenRepo extends JpaRepository<Token, Integer> {

}
