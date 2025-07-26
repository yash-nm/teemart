package com.example.teemart.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.teemart.entity.User;
import com.example.teemart.repository.UserRepo;



@Service
public class UserService {

	@Autowired
	private UserRepo userrepo;

	public List<User> viewAllUsers() {
		// TODO Auto-generated method stub
		return userrepo.findAll();
	}

	public String addUser(User user) {
		
		int id = userrepo.save(user).getId();
		if(id != 0)
		{
			return "\n New User has been registered on ID "+id;
		}
		
		return "\n Unable to Register User! Please try again...";
	}

	public User getUserByID(int id) {
		
		return userrepo.findById(id).orElse(null);
	}

	public String deleteUser(int id) {
		
		if(userrepo.findById(id).isPresent())
		{
			User user = userrepo.findById(id).get();
			userrepo.deleteById(id);
			String message = "The user with ID "+user.getId()+" and Name "+user.getName()+" has been successfully deleted.";
			return message;
		}
		return "\nUser with ID "+id+" not found! Please try again...";
		
	}

	public String updateUser(User user) {
		
			if(user.getId()!=0)
			{
				if(userrepo.findById(user.getId()).isPresent())
				{
					User updatedUser = userrepo.findById(user.getId()).get();
					Set<String> updatelist = new HashSet<String>();
					
						if(user.getName() != null)
						{
							updatedUser.setName(user.getName()); 
							updatelist.add("Name");
						}
							
						if(user.getPassword() != null) 
						{	
							updatedUser.setPassword(user.getPassword());
							updatelist.add("Password");
						}
							
						if(user.getType() != null)   
						{
							updatedUser.setType(user.getType());
							updatelist.add("Type");
						}
							
						if(user.getAddress() != null)
						{
							updatedUser.setAddress(user.getAddress());
							updatelist.add("Address");
						}
						userrepo.save(updatedUser);
						
						String updatemessage = String.join(",", updatelist);
						
				return "User details has been updated for ID "+ updatedUser.getId() +" successfully: "+"(Updated Fields: "+updatemessage+")"; // Binary Values set for Type 
				}
				else 
				{
					return "\n User not found for the given ID.";
				}
			}
	return "\n Please provide a valid User ID in the JSON request body.";
	}
	
	
	
	
	
}
