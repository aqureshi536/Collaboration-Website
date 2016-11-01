package com.ahmad.DAO;

import java.util.List;

import com.ahmad.model.Event;

public interface EventDAO {

	void saveOrUpdateEvent(Event event);
	
	void deleteEvent(String eventId);
	
	Event getEvent(String eventId);
	
	List<Event> listEvents();
	
	List<Event> listEventByEventAt();
}
