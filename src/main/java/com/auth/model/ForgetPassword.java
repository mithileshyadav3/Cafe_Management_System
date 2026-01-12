package com.auth.model;

import java.util.Date;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
//import lombok.Data;

@Entity


public class ForgetPassword {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
private Integer fid;
	@Column(nullable = false)
private Integer otp;
	@Column(nullable = false)
private Date expirationDate;
	 @OneToOne
	    @JoinColumn(name = "user_id", nullable = false, unique = true)
	    private Users users;

public ForgetPassword() {
	super();
	// TODO Auto-generated constructor stub
}
public Integer getFid() {
	return fid;
}
public void setFid(Integer fid) {
	this.fid = fid;
}
public Integer getOtp() {
	return otp;
}
public void setOtp(Integer otp) {
	this.otp = otp;
}
public Date getExpirationDate() {
	return expirationDate;
}
public void setExpirationDate(Date expirationDate) {
	this.expirationDate = expirationDate;
}
public Users getUsers() {
	return users;
}
public void setUsers(Users users) {
	this.users = users;
}

}
