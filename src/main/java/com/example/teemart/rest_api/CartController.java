package com.example.teemart.rest_api;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.teemart.entity.CartItem;
import com.example.teemart.entity.User;
import com.example.teemart.service.CartService;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/teemart/cartitem")
public class CartController {

	@Autowired
	private CartService cartservice;

	@PatchMapping("/{itemid}")
	public Map<String , Double> updateQuantity(@PathVariable int itemid,
	                                             @RequestBody Map<String, Integer> requestBody,
	                                             HttpSession session) {
	    int quantity = requestBody.get("quantity");

	    User user = (User) session.getAttribute("loggeduser");
	    if (user != null) {
	        List<CartItem> cartitems = cartservice.viewCart(user);
	        session.setAttribute("cartitems", cartitems);
	        cartservice.updateQuantity(itemid, quantity);
	        
	        double carttotal = cartitems.stream()
	                .mapToDouble(cartItem -> (cartItem.getTshirt().getPrice() * cartItem.getQuantity()))
	                .sum();
	        
	        double items = cartitems.size();
	        Map<String , Double> data = new HashMap<String,Double>();
	        data.put("items", items);
	        data.put("subtotal", carttotal);
	        
	        
	        return data;
	    }else
	    {
	    	return null;
	    }
	    
	}


}
