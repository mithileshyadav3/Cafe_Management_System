package com.auth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.auth.DTO.LoginRequest;
import com.auth.model.Users;
//import com.auth.repo.UserRepo;
import com.auth.service.UserService;
@CrossOrigin(origins="http://localhost:4200")
@RestController
public class UserController {
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

}
