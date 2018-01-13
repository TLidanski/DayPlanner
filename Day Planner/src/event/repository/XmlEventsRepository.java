package event.repository;

import java.io.File;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
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
	private Collection<Event> events;
	
	public XmlEventsRepository(String filePath) {
		this.filePath = filePath;
		xmlFile = new File(this.filePath);
		events = this.getAll();
	}
	
	public Collection<Event> getEvents() {
		return events;
	}
	
	public void create(ZonedDateTime date, String description) {
		UUID id = UUID.randomUUID();		
		Event event = new Event(id, date, description);
		
		events.add(event);
	}
	
	public void writeEvents(Collection<Event> events) {
		try {
			JAXBContext context = JAXBContext.newInstance(EventsXmlWrapper.class);
			Marshaller marshaller = context.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			
			EventsXmlWrapper eventsXmlWrapper = new EventsXmlWrapper(events);

			marshaller.marshal(eventsXmlWrapper, xmlFile);
		} catch(JAXBException e) {
			e.printStackTrace();
		}
	}
	
	public void update(UUID id, ZonedDateTime date, String description) {
		Optional<Event> value = events.stream().filter(o -> o.getId().equals(id)).findFirst();
		Event event = value.get();
		
		if(event != null) {
			event.setDate(date);
			event.setDescription(description);
		} else {
			//throw new ;
		}
	}
	
	public void delete(UUID id) {
		events.removeIf(e -> e.getId().equals(id));
	}
	
	public Collection<Event> getAll() {
		Collection<Event> eventsList = new ArrayList<Event>();
		
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
	
	public Collection<Event> getInRange(LocalDate fromDate, LocalDate toDate) {
		List<Event> eventsList = new ArrayList<Event>();
		for (Event event : events) {
			LocalDate date = event.getDate().toLocalDate();
			if(date.isAfter(fromDate.minusDays(1)) && date.isBefore(toDate.plusDays(1)))
				eventsList.add(event);
		}
		
		eventsList = Collections.unmodifiableList(eventsList);
		return eventsList;
	}

	@Override
	public void close() throws Exception {
		this.writeEvents(this.events);
	}
}