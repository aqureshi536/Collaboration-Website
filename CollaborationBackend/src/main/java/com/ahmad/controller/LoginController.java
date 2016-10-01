package com.ahmad.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ahmad.DAO.UserDetailDAO;
import com.ahmad.model.UserDetail;
import com.ahmad.utility.IdGenerator;
import com.ahmad.utility.MessageSender;

@RestController
public class LoginController {

	@Autowired
	UserDetailDAO userDetailDAO;
	
	
	@PostMapping("/register")
	public ResponseEntity<Void> registerUser(@RequestBody UserDetail userDetail){
		userDetail.setUserId(IdGenerator.generateId("USR"));
		userDetail.setStatus('P');
		userDetailDAO.saveOrUpdateUserDetail(userDetail);
		String message = "Hello"+userDetail.getName()+" registration successfull on BeOne";
		MessageSender.sendEmail(userDetail.getEmail(),"Registration Successfull",message);
		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}
}
