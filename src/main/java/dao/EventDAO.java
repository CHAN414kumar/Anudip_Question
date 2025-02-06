package dao;

import java.util.List;

import model.Event;

public interface EventDAO {
	
	boolean  saveEvent(Event event);
	Event viewEventById(int id);
	List<Event> viewAllEvents();
	boolean updateEvent(Event event);
	boolean cancleEvent(int id);
}
