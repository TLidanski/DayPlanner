package event.repository;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.Collection;
import java.util.UUID;

import event.Event;

public interface IEventsRepository extends AutoCloseable {
	public Collection<Event> getEvents();
	
	void create(ZonedDateTime date, String description);
	
	void writeEvents(Collection<Event> events);
	
	void update(UUID id, ZonedDateTime date, String description);
	
	void delete(UUID id);
	
	Collection<Event> getAll();
	
	Collection<Event> getInRange(LocalDate fromDate, LocalDate toDate);
}