package com.auth.controller;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.auth.DTO.OrderRequest;
import com.auth.model.Order;
import com.auth.model.Users;
import com.auth.repo.UserRepo;
import com.auth.service.OrderService;

@RestController
@CrossOrigin(origins="http://localhost:4200")
@RequestMapping("orders")
public class OrderController {
	@Autowired
	private OrderService orderService;
	@Autowired
private UserRepo userRepo;
@PostMapping("/place")
public ResponseEntity<Order>placeOrder(@RequestBody OrderRequest request,@RequestParam Integer userId) throws RuntimeException{
	Users users=userRepo.findById(userId).orElseThrow(()->new RuntimeException("User Not Found"));
	Order order=orderService.placedOrder(request,users);
	return ResponseEntity.ok(order);
}
}
