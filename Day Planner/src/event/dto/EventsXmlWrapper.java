package event.dto;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import event.Event;

@XmlRootElement(name = "events")
public class EventsXmlWrapper {
	private final List<Event> eventsList;
	
	public EventsXmlWrapper() {
		eventsList = new ArrayList<Event>();
	}
	
	public EventsXmlWrapper(List<Event> eventList) {
		this.eventsList = eventList;
	}
	
	public EventsXmlWrapper(Iterable<Event> eventList) {
		this.eventsList = (List<Event>) eventList;
	}
	
	@XmlElement(name = "event")
	public List<Event> getEventsList() {
		return eventsList;
	}
}