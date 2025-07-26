package com.example.teemart.rest_api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.teemart.entity.Tshirt;

import com.example.teemart.service.TeemartService;



@RestController
@RequestMapping("/teemart")
public class TeemartRestController {

	@Autowired
	private TeemartService teemartservice;
	
	
	@GetMapping("/test")
	public String test() {
		return "Welcome to TeeMart!";
	}
	
	@GetMapping("/alltshirts")
	public List<Tshirt> viewAllTshirts()
	{
		return teemartservice.viewAllTshirts();
	}
	
	
	
	
	
	
	
}
