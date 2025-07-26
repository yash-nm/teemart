package com.example.teemart.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.teemart.entity.CartItem;
import com.example.teemart.entity.Tshirt;
import com.example.teemart.entity.User;
import com.example.teemart.repository.CartRepo;
import com.example.teemart.repository.TshirtRepo;


@Service
public class CartService {
	
	@Autowired
	private CartRepo cartrepo;
	
	@Autowired
	private TshirtRepo tshirtrepo ;
	
	

	public boolean addToCart(User user, int tshirtid) 
	{
		int quantity = 1;
		Tshirt tshirt = tshirtrepo.findById(tshirtid).get();
		
		if(tshirt!=null & user !=null)
		{
			CartItem cartitem = new CartItem(tshirt,user,quantity);
			cartrepo.save(cartitem);
			return true;
		}
		return false;
	}

	public List<CartItem> viewCart() {
		
		return cartrepo.findAll();
	}

	public boolean removeCartItem(int cartid) {
		if(cartrepo.findById(cartid).isPresent())
		{
			cartrepo.deleteById(cartid);
			return true;
		}
		return false;
	}

	public List<CartItem> viewCart(User user) {
		
		return cartrepo.findByUser(user);
	}

	public boolean updateQuantity(int itemid, int quantity) {
		if(cartrepo.findById(itemid).isPresent())
		{
			CartItem cartitem = cartrepo.findById(itemid).get();
			cartitem.setQuantity(quantity);
			cartrepo.save(cartitem);
			return true;
		}
		else
		{
			return false;
		}
	}

	public boolean addToCart(User user, int tshirtid, String color, String size) {
		
		Tshirt tshirt = tshirtrepo.findById(tshirtid).get();
		
		// Function declared as per the naming convetion findByXAndY()
		CartItem cartitem = cartrepo.findByUserAndTshirtAndColorAndSize(user, tshirt, color, size);
		
		boolean itemadded = false;
		
		if(cartitem!=null)
		{
			cartitem.setColor(color);
			cartitem.setSize(size);
			cartitem.setQuantity(cartitem.getQuantity()+1);
			cartrepo.save(cartitem);
			itemadded = true;
		}
		else
		{
			cartitem = new CartItem(tshirt,user,1,color,size);
			cartrepo.save(cartitem);
			itemadded = true;
		}
		
		return itemadded;
	}
	
	
	
	
}
