package com.ahmad.DAOImpl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.ahmad.DAO.EventDAO;
import com.ahmad.model.Event;

public class EventDAOImpl implements EventDAO {
	@Autowired
	SessionFactory sessionFactory;

	public EventDAOImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Transactional
	public void saveOrUpdateEvent(Event event) {
		sessionFactory.getCurrentSession().saveOrUpdate(event);

	}

	@Transactional
	public void deleteEvent(String eventId) {
		Event eventToDelete = new Event();
		eventToDelete.setEventId(eventId);
		sessionFactory.getCurrentSession().delete(eventToDelete);
	}

	@Transactional
	public Event getEvent(String eventId) {
		String hql = "from Event where eventId=:eventId";
		Query query = sessionFactory.getCurrentSession().createQuery(hql).setParameter("eventId", eventId);
		List<Event> gotEvent = query.getResultList();
		if (gotEvent != null && !gotEvent.isEmpty())
			return gotEvent.get(0);
		return null;
	}

	@Transactional
	public List<Event> listEvents() {
		String hql = "from Event";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		List<Event> listOfEvents = query.getResultList();
		return listOfEvents;
	}

}
