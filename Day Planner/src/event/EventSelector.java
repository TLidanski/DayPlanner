package event;

import java.time.LocalDate;
import java.util.LinkedList;

public class EventSelector {
	public LinkedList<Event> getEventsByDay(LocalDate day, Iterable<Event> events) {
		LinkedList<Event> tempEvents = new LinkedList<Event>();
		
		for (Event event : events) {
			if(event.getDate().isEqual(day))
				tempEvents.add(event);
		}
		
		return tempEvents;
	}

}