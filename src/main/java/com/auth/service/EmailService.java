package com.auth.service;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;





@Service
public class EmailService implements MailService {
	private final JavaMailSender mailSender;
	public EmailService(JavaMailSender javaMailSender) {
		this.mailSender=javaMailSender;
	}
	 @Override
	    public void sendMail(String to, String subject, String text) {

	        SimpleMailMessage message = new SimpleMailMessage();
	        message.setTo(to);
	        message.setSubject(subject);
	        message.setText(text);

	        mailSender.send(message);
	    }
	
}
