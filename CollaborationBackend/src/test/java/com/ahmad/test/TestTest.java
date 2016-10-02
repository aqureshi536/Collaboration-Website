package com.ahmad.test;

import javax.ejb.EJB;

import com.ahmad.utility.EmailSender;

public class TestTest {
	
	@EJB
	EmailSender emailSender;
	
	public static void main(String[] args){
		/*AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
	context.scan("com.ahmad");
		context.refresh();*/
		
	/*	TestDAO testd= (TestDAO) context.getBean("testDAO");
		Test test = (Test)context.getBean("test");
		Date date= new Date();
		long time1=date.getTime();
		Timestamp time =new Timestamp(time1);
		test.setTestId("T002");
		test.setTestName("Updated name");
		test.setTestDate(time);
		
		testd.saveOrUpdateTest(test);
		
		testd.delete("T002");*/
	
		
		
	}
}
