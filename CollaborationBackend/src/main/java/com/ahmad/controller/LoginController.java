package com.ahmad.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.ahmad.DAO.UserAuthoritiesDAO;
import com.ahmad.DAO.UserDAO;
import com.ahmad.DAO.UserDetailDAO;
import com.ahmad.model.UserAuthorities;
import com.ahmad.model.UserDetail;
import com.ahmad.model.Users;
import com.ahmad.utility.EmailSender;
import com.ahmad.utility.IdGenerator;
import com.ahmad.viewmodel.LoginModel;

@RestController
public class LoginController {

	@Autowired
	UserDetailDAO userDetailDAO;
	
	@Autowired
	UserDAO userDAO;
	
	@Autowired
	UserAuthoritiesDAO userAuthoritiesDAO;
	
	@Autowired
	UserDetail userDetail;
	
	@Autowired
	UserAuthorities userAuthorities;
	
	@Autowired
	Users users;
	
	
	private EmailSender emailsender = new EmailSender();
	
	@PostMapping("/register")
	public ResponseEntity<Void> registerUser(@RequestBody LoginModel loginModel,UriComponentsBuilder ucBuilder){
		
		
		
		if(userDetailDAO.getUserByEmail(loginModel.getRegisterEmail())!=null){
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		}
		
		userDetail.setUserId(IdGenerator.generateId("USR"));
		userDetail.setStatus('P');
		userDetail.setEmail(loginModel.getRegisterEmail());
		userDetail.setName(loginModel.getRegisterName());
		userDetail.setGender(loginModel.getRegisterGender());
		userDetail.setPassword(loginModel.getRegisterPassword());
		userDetailDAO.saveOrUpdateUserDetail(userDetail);
		
		users.setUserDetail(userDetail);
		users.setEmail(userDetail.getEmail());
		users.setPassword(userDetail.getPassword());
		users.setEnabled(true);
		userDAO.saveOrUpdateUser(users);
		
		userAuthorities.setUserDetail(userDetail);
		userAuthorities.setAuthority(loginModel.getRegisterRole());
		userAuthorities.setEmail(userDetail.getEmail());
		userAuthoritiesDAO.saveOrUpdateUserAuthority(userAuthorities);
		
		String message = "Hello "+userDetail.getName()+" you're successfully registered with us, Thanks !";
		
		emailsender.sendEmail(userDetail.getEmail(),"Registration Successfull",message);
		
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setLocation(ucBuilder.path("/register/{userId}").buildAndExpand(userDetail.getUserId()).toUri());
		return new ResponseEntity<Void>(httpHeaders,HttpStatus.CREATED);
	}
}
