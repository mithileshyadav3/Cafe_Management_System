package com.auth.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.auth.model.UserPrincipal;
import com.auth.model.Users;
import com.auth.repo.UserRepo;
@Service
public class MyUserDetailService implements UserDetailsService {
	@Autowired
	private UserRepo userRepo;
	

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
	  Users users=userRepo.findByUsername(username);
	  if(users==null) {
		  System.out.print("User nof found");
		  throw new UsernameNotFoundException("User Not Found");
	  }
	  return new UserPrincipal(users);
	}

}
