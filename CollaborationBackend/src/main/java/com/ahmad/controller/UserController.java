package com.ahmad.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ahmad.DAO.UserDetailDAO;
import com.ahmad.model.UserDetail;

@RestController
public class UserController {

	@Autowired
	UserDetail userDetail;
	
	@Autowired 
	UserDetailDAO userDetailDAO;
	
	
	@GetMapping("/users/")
	public ResponseEntity<List<UserDetail>> getAllUsers(){
		List<UserDetail> listOfUsers = userDetailDAO.listUserDetails();
		if(listOfUsers==null){
			return new ResponseEntity<List<UserDetail>>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<List<UserDetail>>(listOfUsers,HttpStatus.OK);
	}
	
	//@PostMapping("/")
	
	
}
