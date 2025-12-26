package com.auth.model;


import jakarta.persistence.Entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
   private int id;
   private String name;
   private String category;
   private String status;
   private Double price;
   private String images;
   public Product() {
	super();
	// TODO Auto-generated constructor stub
   }
   public int getId() {
	return id;
   }
   public void setId(int id) {
	this.id = id;
   }
   public String getName() {
	return name;
   }
   public void setName(String name) {
	this.name = name;
   }
   public String getCategory() {
	return category;
   }
   public void setCategory(String category) {
	this.category = category;
   }
   public String getStatus() {
	return status;
   }
   public void setStatus(String status) {
	this.status = status;
   }
   public Double getPrice() {
	return price;
   }
   public void setPrice(Double price) {
	this.price = price;
   }
   public String getImages() {
	return images;
   }
   public void setImages(String images) {
	this.images = images;
   }
   
}
