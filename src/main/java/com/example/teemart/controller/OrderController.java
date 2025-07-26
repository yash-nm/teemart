/*
 * package com.example.teemart.controller;
 * 
 * import org.springframework.beans.factory.annotation.Autowired;
 * 
 * import org.springframework.stereotype.Controller; import
 * org.springframework.web.bind.annotation.GetMapping; import
 * org.springframework.web.bind.annotation.RequestMapping;
 * 
 * import com.example.teemart.entity.User; import
 * com.example.teemart.service.OrderService;
 * 
 * //@Controller
 * 
 * @RequestMapping("/teemart/orders") public class OrderController {
 * 
 * @Autowired private OrderService orderservice;
 * 
 * 
 * @GetMapping("/placeorder") public String placeOrder( User user) {
 * 
 * boolean orderplaced = orderservice.placeOrder(user);
 * 
 * return null; }
 * 
 * 
 * }
 */