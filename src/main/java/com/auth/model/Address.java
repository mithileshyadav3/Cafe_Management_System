package com.auth.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Address {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;
  private String building_name;
  private String road_no;
  private String city;
  private Long pincode;
  private String state;
  public Address() {
	super();
	// TODO Auto-generated constructor stub
  }
  public int getId() {
	return id;
  }
  public void setId(int id) {
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
