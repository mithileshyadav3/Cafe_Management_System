package com.auth.controller;

//import java.security.PrivateKey;
import java.security.SecureRandom;
import java.util.Date;
//import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.auth.DTO.OtpverifyRequest;
import com.auth.DTO.ResetPassoword;
//import com.auth.config.SecurityConfig;
//import com.auth.DTO.MailBody;
import com.auth.model.ForgetPassword;
import com.auth.model.Users;
import com.auth.repo.ForgetRepo;
import com.auth.repo.UserRepo;
import com.auth.service.EmailService;


@RestController
@CrossOrigin(origins="http://localhost:4200")
@RequestMapping("/forgetpassword")
public class ForgetPasswordController {
	@Autowired
	private PasswordEncoder passwordEncoder;
    private static final SecureRandom random = new SecureRandom();
	@Autowired
	private EmailService emailService;
    @Autowired
private UserRepo userRepo;
    @Autowired
    private  ForgetRepo forgetRepo;
//	send mail for email verification
    public static Integer generateOtp() {
       return 100000+random.nextInt(999999);
    }
	@PostMapping("/verifymail/{email}")
	public ResponseEntity<String>verifyEmail(@PathVariable String email){
		Users users=userRepo.findByEmail(email).orElseThrow(()->new RuntimeException("User Not Found"));
		
		int otp= generateOtp();
		 emailService.sendMail(
	                email,
	                "OTP for Forget Password",
	                "Your OTP is: " + otp + "\nValid for 5 minutes."
	        );
		 ForgetPassword forgetPassword = forgetRepo.findByUsers(users).orElse(new ForgetPassword());

		 forgetPassword.setOtp(otp);
		 forgetPassword.setExpirationDate(new Date(System.currentTimeMillis() + 30 * 60 * 1000));
		 forgetPassword.setUsers(users); // owning side

		 // SAVE only the ForgetPassword entity
		 forgetRepo.save(forgetPassword);

	 
	  return ResponseEntity.ok("OTP sent successfully");
}
	@PostMapping("/verify-otp")
	public ResponseEntity<String>verifyOtp(@RequestBody OtpverifyRequest otpverifyRequest){
	Users users=userRepo.findByEmail(otpverifyRequest.getEmail()).orElseThrow(()->new RuntimeException("User not found"));
	  ForgetPassword fPassword=forgetRepo.findByUsers(users).orElseThrow(()->new RuntimeException("OTP not found"));
	  if(!fPassword.getOtp().toString().equals(otpverifyRequest.getOtp())) {
		  return ResponseEntity.badRequest().body("Invalid Otp");
		  
	  }
	  if(fPassword.getExpirationDate().before(new Date())) {
		  return ResponseEntity.badRequest().body("OTP expired");
	  }
	  return ResponseEntity.ok("OTP verified successfully");
	}
	@PostMapping("/reset-password")
	public ResponseEntity<String>resetPassword(@RequestBody ResetPassoword resetPassoword){
		Users users=userRepo.findByEmail(resetPassoword.getEmail()).orElseThrow(()->new RuntimeException("User not found"));
	
	     ForgetPassword fgpass=forgetRepo.findByUsers(users).orElseThrow(()->new RuntimeException("OTP verification is required"));
	    if(fgpass.getExpirationDate().before(new Date())) {
	    	return ResponseEntity.badRequest().body("OTP Expired");
	    }
	    users.setPassword(passwordEncoder.encode(resetPassoword.getNewpassword()));
	    userRepo.save(users);
	    forgetRepo.delete(fgpass);
	    return ResponseEntity.ok("Reset password successfully");
	}
	
	}
