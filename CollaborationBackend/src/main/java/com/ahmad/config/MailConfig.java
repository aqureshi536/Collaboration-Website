package com.ahmad.config;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Configuration
public class MailConfig {
	@Value("${email.host}")
	private String host;

	@Value("${email.port}")
	private int port;

	@Value("${email.from}")
	private String from;

	@Value("${email.subject}")
	private String subject;

	public JavaMailSender javaMailService() {
		JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();

		javaMailSender.setHost(host);
		javaMailSender.setPort(port);
		javaMailSender.setJavaMailProperties(getMailProperties());
		return javaMailSender;

	}

	private Properties getMailProperties() {
		Properties properties = new Properties();
		properties.setProperty("mail.transport.protocol", "smtp");
		properties.setProperty("mail.smtp.auth", "false");
		properties.setProperty("mail.smtp.starttls.enable", "false");
		properties.setProperty("mail.debug","false");
		return properties;
	}
}
