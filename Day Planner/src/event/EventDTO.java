package event;
import java.util.LinkedList;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Events")
public class EventDTO {
	final private LinkedList<Event> eventsList;
	
	public EventDTO() {
		eventsList = new LinkedList<Event>();
	}
	
	public EventDTO(LinkedList<Event> eventList) {
		this.eventsList = eventList;
	}
	
	@XmlElement(name = "Event")
	public LinkedList<Event> getEventsList() {
		return eventsList;
	}
}