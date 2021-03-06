package com.ahmad.controller;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.ahmad.DAO.EventDAO;
import com.ahmad.model.Event;
import com.ahmad.utility.IdGenerator;
import com.ahmad.viewmodel.EventModel;

@RestController
public class EventController {

	@Autowired
	Event event;
	
	@Autowired 
	EventDAO eventDAO;
	
	@GetMapping("/events/")
	public ResponseEntity<List<Event>> listEvents(){
		//SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		//String date  = dateFormat.format(new Date());
		List<Event> listOfEvents = eventDAO.listEventByEventAt();
		if(listOfEvents==null || listOfEvents.isEmpty())
		{
			return new ResponseEntity<List<Event>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Event>>(listOfEvents,HttpStatus.OK);
	}
	
	@PostMapping("/events/")
	public ResponseEntity<Event> createEvent(@RequestBody EventModel eventModel,UriComponentsBuilder ucBuilder) throws Exception{
		event = new Event();
		event = eventModel.getEvent();
		event.setEventId(IdGenerator.generateId("EVN"));
		Date date = new Date();
		long time = date.getTime();
		Timestamp timestamp = new Timestamp(time);
		event.setPostedAt(timestamp);
		
		System.out.println(eventModel.getCalendar());		
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMMM, yyyy HH:mm");
		//String eventAt = dateFormat.format(eventModel.getCalendar());
		Date eventAt = dateFormat.parse(eventModel.getCalendar().getEventAt()+" "+eventModel.getCalendar().getHour()+":"+eventModel.getCalendar().getMin());
		System.out.println(eventAt);
		event.setEventAt(new Timestamp(eventAt.getTime()));
		System.out.println(event);
		eventDAO.saveOrUpdateEvent(event);
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setLocation(ucBuilder.path("/events/{eventId}").buildAndExpand(event.getEventId()).toUri());
		return new ResponseEntity<Event>(event,httpHeaders,HttpStatus.CREATED);
	}
	
	@PutMapping("/events/{eventId}")
	public ResponseEntity<Event> updateEvent(@RequestBody Event event ,@PathVariable("eventId") String eventId){
		this.event = eventDAO.getEvent(event.getEventId());
		if(this.event==null)
		{
			return new ResponseEntity<Event>(HttpStatus.NOT_FOUND);
		}
		eventDAO.saveOrUpdateEvent(event);
		return new ResponseEntity<Event>(event,HttpStatus.OK);

	}
	
	@DeleteMapping("/events/{eventId}")
	public ResponseEntity<Void> deleteEvent(@PathVariable("eventId")String eventId){
		event=eventDAO.getEvent(eventId);
		if(event==null){
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
		eventDAO.deleteEvent(eventId);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		
	}
	
}
