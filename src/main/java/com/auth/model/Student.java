package com.auth.model;

//import java.security.Identity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Student {
	@Id
private int id;
private String name;
private String marks;
public Student(int id, String name, String marks) {
	super();
	this.id = id;
	this.name = name;
	this.marks = marks;
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
public String getMarks() {
	return marks;
}
public void setMarks(String marks) {
	this.marks = marks;
}

}
