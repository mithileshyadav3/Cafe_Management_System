package com.auth.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.stereotype.Service;

import com.auth.DTO.LoginRequest;
import com.auth.model.Address;


import com.auth.model.Users;
import com.auth.repo.UserRepo;
@Service
public class UserService {
	@Autowired
	JwtService jwtService;
	@Autowired
	AuthenticationManager authenticationManager;
	@Autowired
	UserRepo userRepo;
	@Autowired
	PasswordEncoder passwordEncoder;
	
	public Users addUsers( Users users) {
		String encodedpasswordString=passwordEncoder.encode(users.getPassword());
		   // very important: link each address with this user
	    if (users.getAddress() != null) {
	        users.getAddress().forEach(a -> a.setUsers(users));
	    }
		users.setPassword(encodedpasswordString); 
	  return userRepo.save(users);
	}
	public String Verify(LoginRequest login) {
		Authentication authentication=authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(login.getUsername(), login.getPassword()));
		if(  authentication.isAuthenticated())
		{   
			Users user=userRepo.findByUsername(login.getUsername());
			String role=user.getRole().toUpperCase();
			Integer user_id=user.getId();
			
			return jwtService.GenerateToken(login.getUsername(),user_id,role);
		}
		return "Fail";
	}
	public List<Users>allUsers(){
		return userRepo.findAll();
	}
	public Users getUserId(Integer userid) {
return	userRepo.findById(userid).orElseThrow(()->new RuntimeException("User id not found"));
		
	}
//	update the users
	public Users updateUsers(Integer userId, Users updatedUser) {

	    Users existingUser = userRepo.findById(userId)
	            .orElseThrow(() -> new RuntimeException("User not found"));

	    // Update user fields
	    existingUser.setFullname(updatedUser.getFullname());
	    existingUser.setPhone(updatedUser.getPhone());

	    if (updatedUser.getAddress() != null) {

	        for (Address incomingAddr : updatedUser.getAddress()) {

	            // 🔹 UPDATE EXISTING ADDRESS
	            if (incomingAddr.getId() != null) {

	                Address existingAddr = existingUser.getAddress().stream()
	                        .filter(a -> a.getId() != null &&
	                                     a.getId().equals(incomingAddr.getId()))
	                        .findFirst()
	                        .orElseThrow(() -> new RuntimeException("Address not found"));

	                existingAddr.setBuilding_name(incomingAddr.getBuilding_name());
	                existingAddr.setRoad_no(incomingAddr.getRoad_no());
	                existingAddr.setCity(incomingAddr.getCity());
	                existingAddr.setPincode(incomingAddr.getPincode());
	                existingAddr.setState(incomingAddr.getState());

	            }
	            // 🔹 ADD NEW ADDRESS
	            else {
	                incomingAddr.setUsers(existingUser);
	                existingUser.getAddress().add(incomingAddr);
	            }
	        }
	    }

	    return userRepo.save(existingUser);
	}

}
