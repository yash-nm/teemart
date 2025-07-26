package com.example.teemart.rest_api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.teemart.entity.User;
import com.example.teemart.service.UserService;

@RestController
@RequestMapping("/teemart/users")
public class UserRestController {
	
	@Autowired
	private UserService userservice;
	
	
	@GetMapping("/allusers")
	public List<User> viewAllUsers()
	{
		return userservice.viewAllUsers();		
	}
	
	@PostMapping("/adduser")
	public String addUser(@RequestBody User user)
	{
		return userservice.addUser(user);
	}
	
	@GetMapping("/user")
	public User getUserByID(@RequestParam("id") int id)
	{
		return userservice.getUserByID(id);
	}
	
	@DeleteMapping("/deleteUser")
	public String deleteUser(@RequestParam int id)
	{
		return userservice.deleteUser(id);
	}
	
	@PatchMapping("/updateUser")
	public String updateUser(@RequestBody User user)
	{
		return	userservice.updateUser(user);
	}
	
	

}
