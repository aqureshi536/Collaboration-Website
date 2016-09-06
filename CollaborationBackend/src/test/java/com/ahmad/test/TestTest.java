package com.ahmad.test;

import java.sql.Timestamp;
import java.util.Date;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.ahmad.DAO.TestDAO;
import com.ahmad.model.Test;

public class TestTest {

	public static void main(String[] args){
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		context.scan("com.ahmad");
		context.refresh();
		
		TestDAO testd= (TestDAO) context.getBean("testDAO");
		Test t = (Test)context.getBean("test");
		Date date= new Date();
		long time1=date.getTime();
		Timestamp time =new Timestamp(time1);
		t.setTestId("T001");
		t.setTestName("Firsst name");
		t.setTestDate(time);
		
		testd.saveOrUpdateTest(t);
	}
}
