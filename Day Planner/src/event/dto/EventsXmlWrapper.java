package event.dto;

import java.util.ArrayList;
import java.util.Collection;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import event.Event;

@XmlRootElement(name = "events")
public class EventsXmlWrapper {
	private final Collection<Event> eventsList;
	
	public EventsXmlWrapper() {
		eventsList = new ArrayList<Event>();
	}
	
	public EventsXmlWrapper(Collection<Event> eventList) {
		this.eventsList = eventList;
	}
	
	@XmlElement(name = "event")
	public Collection<Event> getEventsList() {
		return eventsList;
	}
}