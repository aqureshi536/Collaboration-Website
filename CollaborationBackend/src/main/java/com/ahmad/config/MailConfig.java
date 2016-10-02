package com.ahmad.config;

import java.util.Properties;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

public class MailConfig {
	String host = "smtp.gmail.com";

	
	public JavaMailSender javaMailService() {

		JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();

		javaMailSender.setPort(587);
		javaMailSender.setHost(host);
		javaMailSender.setUsername("onpark4@gmail.com");
		javaMailSender.setPassword("9892155183");
		javaMailSender.setJavaMailProperties(getMailProperties());
		return javaMailService();
	}

	private Properties getMailProperties() {
		Properties properties = new Properties();
		
		properties.put("mail.transport.protocol", "smtp");
		properties.put("mail.smtp.starttls.enable", "true");
		properties.put("mail.smtp.auth", "true");
		
		properties.put("mail.debug", "true");
		
		return properties;
	}

}
