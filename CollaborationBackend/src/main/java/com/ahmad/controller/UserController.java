package com.ahmad.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

public class UserController {

	@GetMapping("/login")
	public ResponseEntity<Void> loginUser(@RequestParam(value="logout",required=false)String logout){
	
		if(logout!=null)
		{
			return new ResponseEntity<Void>(HttpStatus.OK);
		}
		return new ResponseEntity<Void>(HttpStatus.UNAUTHORIZED);
	}
	
}
