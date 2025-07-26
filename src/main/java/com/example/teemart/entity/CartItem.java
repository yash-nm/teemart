package com.example.teemart.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class CartItem {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int cartid;
	
	@JsonBackReference
	@ManyToOne
	@JoinColumn(name="tshirtid")
	private Tshirt tshirt ;
	
	
	@JsonBackReference
	@ManyToOne
	@JoinColumn(name="userid")
	private User user;

	private int quantity;
	
	
	private String color;
	
	private String size;
	
	
	
	
	public CartItem() {
		
	}

	public CartItem( Tshirt tshirt, int quantity) {
		
		this.tshirt = tshirt;
		this.quantity = quantity;
	}



	

	public CartItem(Tshirt tshirt, User user, int quantity) {
		
		this.tshirt = tshirt;
		this.user = user;
		this.quantity = quantity;
	}
	
	

	public CartItem( Tshirt tshirt, User user, int quantity, String color, String size) {
		
		this.tshirt = tshirt;
		this.user = user;
		this.quantity = quantity;
		this.color = color;
		this.size = size;
	}

	public int getCartid() {
		return cartid;
	}

	public void setCartid(int cartid) {
		this.cartid = cartid;
	}

	

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}


	public Tshirt getTshirt() {
		return tshirt;
	}

	public void setTshirt(Tshirt tshirt) {
		this.tshirt = tshirt;
	}

	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	
	
	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	@Override
	public String toString() {
		return "CartItem [cartid=" + cartid + ", tshirt=" + tshirt + ", user=" + user + ", quantity=" + quantity + "]";
	}
	
	

}
