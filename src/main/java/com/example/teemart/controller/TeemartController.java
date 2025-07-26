package com.example.teemart.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.teemart.entity.CartItem;
import com.example.teemart.entity.Tshirt;
import com.example.teemart.entity.User;

import com.example.teemart.service.CartService;
import com.example.teemart.service.TeemartService;

import jakarta.servlet.http.HttpSession; 

@Controller
@RequestMapping("/teemart")
public class TeemartController {
	
	@Autowired
	private TeemartService teemartservice;
	@Autowired
	private CartService cartservice;
	

	@SuppressWarnings("unchecked")
	@GetMapping("/login")
	public String login(Model model, HttpSession session)
	{
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
			cartitems =  cartservice.viewCart(user);
			session.setAttribute("cartitems", cartitems);
		}
		model.addAttribute("cartitems", cartitems);
		
		return "login";
	}
	
	
	@SuppressWarnings("unchecked")
	@GetMapping("/index")
	public String indexpage(Model model, HttpSession session)
	{
		List<Tshirt> tshirts =  teemartservice.viewAllTshirts();
		model.addAttribute("tshirts" , tshirts);
			//[ Check User Session? ]
		User user = (User) session.getAttribute("loggeduser");
		if(user!=null)
		{
			model.addAttribute("user", user);
		}	
			//[ Check Cart Items? ]
		List<CartItem> cartitems ;
		if(session.getAttribute("cartitems")!= null) {
			
			cartitems =  (List<CartItem>) session.getAttribute("cartitems");
		}else {
			cartitems =  cartservice.viewCart(user);
			session.setAttribute("cartitems", cartitems);
		}
		model.addAttribute("cartitems", cartitems);
		
		return "index"; 
	}
	
	@SuppressWarnings("unchecked")
	@GetMapping("/tshirtdetail/{id}")
	public String viewTshirtDetail(@PathVariable int id, Model model, HttpSession session)
	{
		User user = (User) session.getAttribute("loggeduser");
		if(user!=null)
		{
			model.addAttribute("user", user);
		}
		
		Tshirt thsirt = teemartservice.findTshirt(id);
		model.addAttribute("tshirt",thsirt);	
		
//		[ Check Cart Items? ]
		List<CartItem> cartitems ;
		if(session.getAttribute("cartitems")!= null) {
			
			cartitems =  (List<CartItem>) session.getAttribute("cartitems");
		}else {
			cartitems =  cartservice.viewCart(user);
			session.setAttribute("cartitems", cartitems);
		}
		model.addAttribute("cartitems", cartitems);
		
		return "tshirtdetail";
	}
	
	@GetMapping("/addtshirttocart/tshirtid-{id}")
	public String addToCart(@RequestParam("color")String color, @RequestParam("size") String size, @PathVariable("id") int tshirtid, Model model, HttpSession session)
	{
		User user = (User) session.getAttribute("loggeduser");
		if(user!=null)
		{
			model.addAttribute("user", user);
		}
		else
		{
			return "redirect:/teemart/login";
		}
		boolean cartadded = cartservice.addToCart(user,tshirtid,color,size);
		
		List<CartItem> cartitems =  cartservice.viewCart(user);
		session.setAttribute("cartitems", cartitems);
		
		if(cartadded)
		{
			return "redirect:/teemart/bag";
		}
		return "redirect:/teemart/index";
	}
	
	
	@GetMapping("/bag")
	public String viewCart(Model model , HttpSession session)
	{
		User user = (User) session.getAttribute("loggeduser");
		if(user!=null)
		{
			model.addAttribute("user", user);
		}
		
		List<CartItem> cartitems ;
		
		cartitems =  cartservice.viewCart(user);
		session.setAttribute("cartitems", cartitems);
		
		double carttotal = cartitems.stream()
                .mapToDouble(cartItem -> (cartItem.getTshirt().getPrice() * cartItem.getQuantity()))
                .sum();
		
		model.addAttribute("carttotal", carttotal);
		model.addAttribute("cartitems", cartitems); 
	
		return "viewcart";
	}
	
	@GetMapping("/removecartitem-{id}")
	public String removeCartItem(@PathVariable("id") int cartid, HttpSession session,Model model)
	{
		User user = (User) session.getAttribute("loggeduser");
		if(user!=null)
		{
			model.addAttribute("user", user);
		}
		else {
			return "redirect:/teemart/login";
		}
		
		cartservice.removeCartItem(cartid);
		
		List<CartItem> cartitems =  cartservice.viewCart(user);
		session.setAttribute("cartitems", cartitems);
	
		double carttotal = cartitems.stream()
	            .mapToDouble(cartItem -> cartItem.getTshirt().getPrice())
	            .sum();
		
		model.addAttribute("carttotal", carttotal);
		model.addAttribute("cartitems", cartitems); 
		
		return "redirect:/teemart/bag";
	}
	
	
	
}