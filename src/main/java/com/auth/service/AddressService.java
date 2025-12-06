package com.auth.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.auth.model.Address;
import com.auth.repo.AddressRepo;

@Service
public class AddressService {
  @Autowired
  public AddressRepo addressRepo;
  public Address addAddress(Address address) {
	  return addressRepo.save(address);
  }
}
