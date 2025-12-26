package com.auth.model;

import com.fasterxml.jackson.annotation.JsonIgnore;



import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Address {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;
  private String building_name;
  private String road_no;
  private String city;
  private Long pincode;
  private String state;
  @ManyToOne
  @JoinColumn(name="user_id")
  @JsonIgnore
  private Users users;
  public Users getUsers() {
	return users;
}
  public void setUsers(Users users) {
	this.users = users;
  }
  public Address() {
	super();
	// TODO Auto-generated constructor stub
  }

  public Integer getId() {
	return id;
}
  public void setId(Integer id) {
	this.id = id;
  }
  public String getBuilding_name() {
	return building_name;
  }
  public void setBuilding_name(String building_name) {
	this.building_name = building_name;
  }
  public String getRoad_no() {
	return road_no;
  }
  public void setRoad_no(String road_no) {
	this.road_no = road_no;
  }
  public String getCity() {
	return city;
  }
  public void setCity(String city) {
	this.city = city;
  }
  public Long getPincode() {
	return pincode;
  }
  public void setPincode(Long pincode) {
	this.pincode = pincode;
  }
  public String getState() {
	return state;
  }
  public void setState(String state) {
	this.state = state;
  }
 
}
