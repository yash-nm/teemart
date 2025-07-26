package com.example.teemart.controller;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.teemart.entity.CartItem;
import com.example.teemart.entity.User;
import com.example.teemart.repository.UserRepo;
import com.example.teemart.service.CartService;
import com.example.teemart.service.EmailService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/teemart/user")
public class UserController {

	@Autowired
	private UserRepo userrepo;

	@Autowired
	private EmailService emailservice; 
	
	@Autowired
	private CartService cartservice;
	
	

	@SuppressWarnings("unchecked")
	@GetMapping("/register")
	public String registerform(Model model,HttpSession session) {
//		[ Check User Session? ]
		User user = (User) session.getAttribute("loggeduser");
		if(user!=null)
		{
			model.addAttribute("user", user);
		}	
//		[ Check Cart Items? ]
		List<CartItem> cartitems ;
		if(session.getAttribute("cartitems")!= null) {
			
			cartitems =  (List<CartItem>) session.getAttribute("cartitems");
		}else {
			cartitems =  cartservice.viewCart();
			session.setAttribute("cartitems", cartitems);
		}
		model.addAttribute("cartitems", cartitems);
		
		return "register";
	}

	
	
	@PostMapping("/register/save")
	public String register(@ModelAttribute User user, Model model,HttpSession session) {

		try {
			user.setType("Customer");
			userrepo.save(user);
			
			User newuser = userrepo.findByEmailAndPassword(user.getEmail(), user.getPassword());
			if (newuser != null) {
				session.setAttribute("loggeduser", newuser);
			}
			
			// ✅ Send welcome email here
			try {
				
				String htmlMessage = "<!DOCTYPE html>\r\n"
										+ "<html>\r\n"
										+ "<head>\r\n"
										+ "    <style>\r\n"
										+ "        h1 {\r\n"
										+ "            color: #555;\r\n"
										+ "        }\r\n"
										+ "        p {\r\n"
										+ "            font-size: 16px;\r\n"
										+ "            color: #555;\r\n"
										+ "        }\r\n"
										+ "    </style>\r\n"
										+ "</head>\r\n"
										+ "<body>\r\n"
										+ "    <h1>Welcome to Teemart!</h1>\r\n"
										+ "    <p>Hi there,</p>\r\n"
										+ "    <p>Thank you for signing up at <strong>Teemart</strong> — your one-stop destination for stylish and trendy T-shirts!</p>\r\n"
										+ "    <p>We're excited to have you with us. Get ready to explore a wide variety of designs that fit your vibe perfectly.</p>\r\n"
										+ "    <p>Start browsing now and find your next favorite tee:</p>\r\n"
										+ "    <p><a href=\"https://www.teemart.com\" style=\"background:  rgb(0, 0, 0); color: white; padding: 10px 20px; text-decoration: none; border-radius: 5px;\">Visit Teemart</a></p>\r\n"
										+ "    <p>Happy shopping!<br>— The Teemart Team</p>\r\n"
										+ "</body>\r\n"
										+ "</html>\r\n"
										+ "";
				
				/*
				 * ------------------
				 * Basic Mail
				 * ------------------
				 * emailservice.sendEmail( user.getEmail() , "Registration successful!" ,
				 * htmlMessage );
				 */	 
				
				 emailservice.sendHtmlEmail( user.getEmail(),
						 "Registration successful!",
						 htmlMessage ); 
				 

			} catch (Exception emailEx) {
				emailEx.printStackTrace();
				model.addAttribute("error", "Registration successful, but failed to send confirmation email.");
				return "redirect:/teemart/login"; // Optional: redirect anyway or show success
			}

			return "redirect:/teemart/index";
			// return "redirect:/main/login?success=true";
		} catch (DataIntegrityViolationException e) {
			model.addAttribute("error", "Email or username already exists");
			return "redirect:/teemart/login";
		} catch (Exception e) {
			model.addAttribute("error", "An error occurred while registering");
			return "redirect:/teemart/login";
		}
	}

	@PostMapping("/loginsubmit")
	public String loggedInUser(@RequestParam String email, @RequestParam String password, Model model,
			HttpSession session) {
		
		User user = userrepo.findByEmailAndPassword(email, password);
		if (user != null) {
			session.setAttribute("loggeduser", user);
			
			if(user!=null)
			{
				model.addAttribute("user", user);
			}
			List<CartItem> cartitems ;
			
			cartitems =  cartservice.viewCart(user);
			session.setAttribute("cartitems", cartitems);
			
			double carttotal = cartitems.stream()
	                .mapToDouble(cartItem -> cartItem.getTshirt().getPrice())
	                .sum();
			
			model.addAttribute("carttotal", carttotal);
			model.addAttribute("cartitems", cartitems);  

			return "redirect:/teemart/index";
		}
		
		return "redirect:/teemart/login";
	}

	@GetMapping("/logout")
	public String logout(HttpSession session, Model model) {
		session.invalidate();
		
		return "redirect:/teemart/login";
	}

}
