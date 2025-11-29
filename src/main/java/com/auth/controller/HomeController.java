package com.auth.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;

@RestController
public class HomeController {
	
@GetMapping("/s")
public String hello(HttpServletRequest request) {
	return "Hello Browsers"+request.getSession().getId();
}
  
}
