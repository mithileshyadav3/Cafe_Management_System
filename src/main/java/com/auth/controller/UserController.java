package com.auth.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.auth.DTO.LoginRequest;
import com.auth.model.Users;
import com.auth.service.JwtService;
//import com.auth.repo.UserRepo;
import com.auth.service.UserService;
@CrossOrigin(origins="http://localhost:4200")
@RequestMapping("users")
@RestController
public class UserController {
	@Autowired
	private JwtService jwtService;
	@Autowired
private	UserService userService;
	@PostMapping("/adduser")
	public Users addUsers(@RequestBody Users users) {
	  return userService.addUsers(users); 
	}
@PostMapping("/login")
private String login(@RequestBody LoginRequest login) {
	
	
	return userService.Verify(login);
}
@GetMapping("/allusers")
public List<Users>allUsers(){
	return userService.allUsers();
}
@GetMapping("profile")
public Users getProfile(@RequestHeader("Authorization") String authHeader) {

    // Remove "Bearer "
    String token = authHeader.substring(7);

    // Extract userId from JWT
    Integer userId = jwtService.extractUserId(token);

    // Fetch user by ID
    return userService.getUserId(userId);
}
}
