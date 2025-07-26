package com.example.teemart.controller;

import java.util.Map;

//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.example.demo.Service.EmailService;
//
//@RequestMapping("/mail")
//@RestController
//public class EmailController {
//
//	
//	@Autowired
//	private EmailService emailService;
//
//	@GetMapping("/")
//	
//	public ResponseEntity<String> checkEmail()
//	{
//		emailService.sendEmail("kirtiskhade02@gmail.com", "Check", "Checking Email");
//		return new ResponseEntity<>("Message Send",HttpStatus.CREATED);
//	}
//}



import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.example.teemart.service.EmailService;



@RequestMapping("/mail")
@RestController
public class EmailController {

    @Autowired
    private EmailService emailService;

	/*
	 * @GetMapping("/sendEmail") public ResponseEntity<String> sendEmail() { //
	 * Assuming email is sent after successful payment
	 * emailService.sendEmail("yashmirashi59@gmail.com", "Payment Successful",
	 * "Your payment has been successfully processed.\nYour product is on the way. Thank you for shopping with us!"
	 * ); return new ResponseEntity<>("Email Sent", HttpStatus.OK);
	 * 
	 * }
	 */   
    
    
    
    ///@Autowired
    ///private UserRepo userRepo;


    @PostMapping("/sendEmail")
    public ResponseEntity<String> sendEmail(@RequestBody Map<String, String> payload) {
	    String email = payload.get("email");
	
	    if (email == null || email.isEmpty()) {
	    return ResponseEntity.badRequest().body("Email is missing");
	    }
	
	    try {
	    emailService.sendEmail(
	    email,
	    "Registration successful!",
	    "Hi there, \\nThank you for signing up at <strong>Teemart</strong> — your one-stop destination for stylish and trendy T-shirts!"
	    );
	    return ResponseEntity.ok("Email sent to " + email);
	    } catch (Exception e) {
	    e.printStackTrace();
	    return ResponseEntity.internalServerError().body("Failed to send email");
	    }
    }
    
}

