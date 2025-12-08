package com.auth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.auth.model.Address;
import com.auth.service.AddressService;

@RestController
@RequestMapping("/address")
public class AddressController {
@Autowired
public AddressService addressService;
@PostMapping("/add/{user_id}")
public Address addAddress(Integer user_id,@RequestBody Address address) {
return addressService.addAddress(user_id,address);
}
}
