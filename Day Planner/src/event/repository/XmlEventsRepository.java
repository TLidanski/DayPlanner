package event.repository;

import java.io.File;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import event.Event;
import event.dto.EventsXmlWrapper;

public class XmlEventsRepository implements IEventsRepository, AutoCloseable {
	private final String filePath;
	private final File xmlFile;
	
	public XmlEventsRepository(String filePath) {
		this.filePath = filePath;
		xmlFile = new File(this.filePath);
	}
	
	public void writeEvents(Iterable<Event> eventsList) {
		try {
			JAXBContext context = JAXBContext.newInstance(EventsXmlWrapper.class);
			Marshaller marshaller = context.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			
			EventsXmlWrapper eventsXmlWrapper = new EventsXmlWrapper(eventsList);

			marshaller.marshal(eventsXmlWrapper, xmlFile);
		} catch(JAXBException e) {
			e.printStackTrace();
		}
	}
	
	public void updateEvent(List<Event> eventsList, String id, ZonedDateTime date, String description) {
		Optional<Event> value = eventsList.stream().filter(o -> o.getId().toString().equals(id)).findFirst();
		Event event = value.get();
		
		event.setDate(date);
		event.setDescription(description);
	}
	
	public void removeEvent(UUID id, List<Event> eventsList) {
		eventsList.removeIf(e -> e.getId().equals(id));
	}
	
	public List<Event> readEvents() {
		List<Event> eventsList = new ArrayList<Event>();
		
		try {
			JAXBContext context = JAXBContext.newInstance(EventsXmlWrapper.class);
			Unmarshaller unmarshaller = context.createUnmarshaller();
			
			EventsXmlWrapper events = (EventsXmlWrapper) unmarshaller.unmarshal(xmlFile);
			
			eventsList = events.getEventsList();
		} catch(JAXBException e) {
			e.printStackTrace();
		}
		
		return eventsList;
	}
	
	public List<Event> getEventsBetweenDates(LocalDate fromDate, LocalDate toDate, Iterable<Event> eventsList) {
		List<Event> events = new ArrayList<Event>();
		for (Event event : eventsList) {
			LocalDate date = event.getDate().toLocalDate();
			if(date.isAfter(fromDate.minusDays(1)) && date.isBefore(toDate.plusDays(1)))
				events.add(event);
		}
		
		return events;
	}

	@Override
	public void close() throws Exception {
		// TODO Auto-generated method stub
	}
}