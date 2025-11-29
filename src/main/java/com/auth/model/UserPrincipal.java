package com.auth.model;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class UserPrincipal implements UserDetails {
  private Users users;

  public UserPrincipal(Users user) {
//	super();
	// TODO Auto-generated constructor stub
	this.users=user;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
	// TODO Auto-generated method stub
	return null;
  }

  @Override
  public String getPassword() {
	// TODO Auto-generated method stub
	return users.getPassword();
  }

  @Override
  public String getUsername() {
	// TODO Auto-generated method stub
	return users.getUsername();
  }
  @Override
  public boolean isAccountNonExpired() {
      return true;
  }

  @Override
  public boolean isAccountNonLocked() {
      return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
      return true;
  }

  @Override
  public boolean isEnabled() {
      return true;
  }
  
}
