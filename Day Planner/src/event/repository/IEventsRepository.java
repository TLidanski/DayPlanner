package event.repository;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;

import event.Event;

public interface IEventsRepository extends AutoCloseable {
	void writeEvents(Iterable<Event> eventsList);
	
	void updateEvent(List<Event> eventsList, String id, ZonedDateTime date, String description);
	
	void removeEvent(UUID id, List<Event> eventsList);
	
	List<Event> readEvents();
	
	List<Event> getEventsBetweenDates(LocalDate fromDate, LocalDate toDate, Iterable<Event> eventsList);
}