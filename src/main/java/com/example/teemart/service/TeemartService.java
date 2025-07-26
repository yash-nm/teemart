package com.example.teemart.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.teemart.entity.Tshirt;
import com.example.teemart.repository.TshirtRepo;

@Service
public class TeemartService {
	
	@Autowired
	private TshirtRepo tr;

	public List<Tshirt> viewAllTshirts() {
		// TODO Auto-generated method stub
		return tr.findAll();
	}

	public Tshirt findTshirt(int id) {
		
		Tshirt tshirt = tr.findById(id).get();
		return tshirt;
	}

}
