package com.ahmad.test;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.ahmad.DAO.EventDAO;
import com.ahmad.model.Event;

public class EventTest {

	public static void main(String[] ars){
		
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		
		context.scan("com.ahmad");
		context.refresh();
		
		Event event = (Event)context.getBean("event");
		EventDAO eventDAO  = (EventDAO)context.getBean("eventDAO");
		Date date = null;
		try {
		event.setContent("Evaluation day");
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		
		
			date = dateFormat.parse("10/10/2016");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		long time = date.getTime();
		
		event.setEventAt(new Timestamp(time));
		date=new Date();
		long time1 = date.getTime();
		Timestamp timestamp = new Timestamp(time1);
		event.setPostedAt(timestamp);
		event.setPlace("NIIT");
		eventDAO.saveOrUpdateEvent(event);
		
	}
}
