package com.my.shopnest.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.my.shopnest.entity.User;

@Repository
public interface ProductRepo extends JpaRepository<User, Integer> {

}
