package com.example.teemart.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.teemart.entity.User;

@Repository
public interface UserRepo extends JpaRepository<User, Integer> {

	User findByEmailAndPassword(String email, String password);
	
	
	

}
