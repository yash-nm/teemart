package com.example.teemart.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToMany;

@Entity
public class Tshirt {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String type;
	private String brand;
	
	@Lob
	private	String image;
	@Lob
	private String image2;
	private double price;
	
	
	
	@JsonManagedReference
	@OneToMany(mappedBy = "tshirt" , cascade = CascadeType.ALL )
	private List<CartItem> cartitems;
	
	
	
	
	public Tshirt() 
	{
		
	}

	public Tshirt(int id, String type, String brand, String image, double price) {
		
		this.id = id;
		this.type = type;
		this.brand = brand;
		this.image = image;
		this.price = price;
	}
	
	public Tshirt(int id, String type, String brand, String image, String image2, double price) {
		super();
		this.id = id;
		this.type = type;
		this.brand = brand;
		this.image = image;
		this.image2 = image2;
		this.price = price;
		
	}


	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public String getType() {
		return type;
	}
	
	


	public void setType(String type) {
		this.type = type;
	}



	public String getBrand() {
		return brand;
	}



	public void setBrand(String brand) {
		this.brand = brand;
	}



	public double getPrice() {
		return price;
	}



	public void setPrice(double price) {
		this.price = price;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getImage2() {
		return image2;
	}

	public void setImage2(String image2) {
		this.image2 = image2;
	}
	
	

	@Override
	public String toString() {
		return "Tshirt [id=" + id + ", type=" + type + ", brand=" + brand + ", image=" + image + ", image2=" + image2
				+ ", price=" + price + ", cartitems=" + cartitems + "]";
	}
	
}
