package com.ahmad.utility;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

public class MessageSender {

	@Autowired
	private static  JavaMailSender  javaMailService;
	
	
	public static void  sendEmail(String to,String subject,String message){
		
		SimpleMailMessage simpleMailMessage= new SimpleMailMessage();
		simpleMailMessage.setTo(to);
		simpleMailMessage.setSubject(subject);
		simpleMailMessage.setText(message);
		javaMailService.send(simpleMailMessage);
	}
}
