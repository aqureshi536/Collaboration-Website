package com.ahmad.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ahmad.DAO.TestDAO;
import com.ahmad.model.Test;

@RestController
public class TestController {

	@Autowired
	Test test;
	@Autowired
	TestDAO testDAO;

	@RequestMapping("/test/list")
	public List<Test> list(){
		List<Test> listOfTest = testDAO.listTest();
		return listOfTest;
		
	}
}
