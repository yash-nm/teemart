package com.example.teemart.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.teemart.entity.CartItem;
import com.example.teemart.entity.Tshirt;
import com.example.teemart.entity.User;

@Repository
public interface CartRepo extends JpaRepository<CartItem, Integer>{
		
	List<CartItem>  findByUser(User user);
	
	CartItem  findByUserAndTshirt(User user, Tshirt tshirt);
	
	CartItem  findByUserAndTshirtAndColorAndSize(User user, Tshirt tshirt, String color, String size);
	
	@Query("select count(distinct user) from CartItem")
	long countDistinctUserid();
	
	@Query("SELECT SUM(c.quantity * t.price) FROM CartItem c JOIN c.tshirt t")
	Long totalCartValue();   // long is primitive dosnt take null if result is null but Wrapper can take up null values
	
}
