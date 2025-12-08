package com.auth.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.auth.model.Address;
import com.auth.model.Users;
import com.auth.repo.AddressRepo;
import com.auth.repo.UserRepo;

@Service
public class AddressService {
	@Autowired
	private UserRepo userRepo;
  @Autowired
  public AddressRepo addressRepo;
  public Address addAddress( Integer user_id, Address address) {
	  Users user=userRepo.findById(user_id).orElseThrow(()->new RuntimeException("User Id Not Found"));
			  address.setUsers(user);
	  return addressRepo.save(address);
  }
}
