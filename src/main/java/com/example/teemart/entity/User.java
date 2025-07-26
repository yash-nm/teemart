package com.example.teemart.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class User {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String name;
	private String email;
	private String type; // Normal || Admin 
	private String address;
	private String password;
	
	
	@JsonManagedReference
	@OneToMany(mappedBy = "user" , cascade = CascadeType.ALL)
	private List<CartItem> cartitems;
	
	/*
	 * @JsonManagedReference
	 * 
	 * @OneToMany(mappedBy = "user", cascade = CascadeType.ALL) private List<Order>
	 * orders;
	 */
	
	public User() {
		
	}

	public User(int id, String name, String email, String type, String address, String password) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.type = type;
		this.address = address;
		this.password = password;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", email=" + email + ", type=" + type + ", address=" + address
				+ ", password=" + password + "]";
	}
	
}
