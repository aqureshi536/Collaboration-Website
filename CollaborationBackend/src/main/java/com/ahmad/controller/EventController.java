package com.ahmad.controller;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ahmad.DAO.EventDAO;
import com.ahmad.model.Event;
import com.ahmad.utility.IdGenerator;

@RestController
public class EventController {

	@Autowired
	Event event;
	
	@Autowired 
	EventDAO eventDAO;
	
	@GetMapping("/events/")
	public ResponseEntity<List<Event>> listEvents(){
		List<Event> listOfEvents = eventDAO.listEventByEventAt();
		if(listOfEvents==null || listOfEvents.isEmpty())
		{
			return new ResponseEntity<List<Event>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Event>>(listOfEvents,HttpStatus.OK);
	}
	
	@PostMapping("/events/")
	public ResponseEntity<Event> createEvent(@RequestBody Event event){
		event.setEventId(IdGenerator.generateId("EVN"));
		Date date = new Date();
		long time = date.getTime();
		Timestamp timestamp = new Timestamp(time);
		event.setPostedAt(timestamp);
		eventDAO.saveOrUpdateEvent(event);
		return new ResponseEntity<Event>(HttpStatus.OK);
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
