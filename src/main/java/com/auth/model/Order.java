package com.auth.model;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity(name = "orders")
public class Order {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Integer order_id;
private Date date;
private String status;

@ManyToOne
private Users users;
@OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
@JsonManagedReference
private List<OrderItem> orderItems;

public Order() {
	super();
	// TODO Auto-generated constructor stub
}

public Integer getOrder_id() {
	return order_id;
}

public void setOrder_id(Integer order_id) {
	this.order_id = order_id;
}

public Date getDate() {
	return date;
}

public void setDate(Date date) {
	this.date = date;
}

public String getStatus() {
	return status;
}

public void setStatus(String status) {
	this.status = status;
}

public Users getUsers() {
	return users;
}

public void setUsers(Users users) {
	this.users = users;
}

public List<OrderItem> getOrderItems() {
	return orderItems;
}

public void setOrderItems(List<OrderItem> orderItems) {
	this.orderItems = orderItems;
}



}
