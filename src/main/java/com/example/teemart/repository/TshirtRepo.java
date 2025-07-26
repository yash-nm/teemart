package com.example.teemart.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.teemart.entity.Tshirt;

@Repository
public interface TshirtRepo extends JpaRepository<Tshirt, Integer>{

}
