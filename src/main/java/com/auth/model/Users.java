package com.auth.model;

import java.util.ArrayList;
import java.util.List;


import jakarta.persistence.CascadeType;

//import org.springframework.data.annotation.Id;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;


@Entity
@Table(name = "users")
public class Users {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;
	private String fullname;
  private String username;
  private String password;
  private String phone;
  private String role="USER";
  @OneToMany(mappedBy = "users",cascade = CascadeType.ALL)
//  @JoinColumn(name="address_id") 
//  private List<Address>address;
  private List<Address> address = new ArrayList<>();

  public Users() {
	super();
	// TODO Auto-generated constructor stub
  }
 
  public Integer getId() {
	return id;
}

  public void setId(Integer id) {
	this.id = id;
  }

  public String getFullname() {
	return fullname;
  }
  public void setFullname(String fullname) {
	this.fullname = fullname;
  }
  public String getUsername() {
	return username;
  }
  public void setUsername(String username) {
	this.username = username;
  }
  public String getPassword() {
	return password;
  }
  public void setPassword(String password) {
	this.password = password;
  }
  public String getPhone() {
	return phone;
  }
  public void setPhone(String phone) {
	this.phone = phone;
  }
  public String getRole() {
	return role;
  }
  public void setRole(String role) {
	this.role = role;
  }
  public List<Address> getAddress() {
	return address;
  }
  public void setAddress(List<Address> address) {
	this.address = address;
  }
  
    
  
}
