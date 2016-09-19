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
		Test test = (Test)context.getBean("test");
		Date date= new Date();
		long time1=date.getTime();
		Timestamp time =new Timestamp(time1);
		test.setTestId("T002");
		test.setTestName("Updated name");
		test.setTestDate(time);
		
		/*testd.saveOrUpdateTest(test);*/
		
		testd.delete("T002");
	}
}
