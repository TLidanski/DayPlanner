import java.io.File;
import java.util.LinkedList;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

public class XMLEventStorage implements IEventStorage {
	private final String filePath;
	private final File xmlFile;
	
	public XMLEventStorage(String filePath) {
		this.filePath = filePath;
		xmlFile = new File(this.filePath);
	}
	
	public String getFilePath() {
		return filePath;
	}
	
	public boolean isStorageFileEmpty() {
		return (xmlFile.length() == 0);
	}
	
	public void writeEvents(LinkedList<Event> eventsList) {
		try {
			JAXBContext context = JAXBContext.newInstance(EventDTO.class);
			Marshaller marshaller = context.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			
			EventDTO eventDTO = new EventDTO(eventsList);


			marshaller.marshal(eventDTO, xmlFile);
		} catch(JAXBException e) {
			e.printStackTrace();
		}
	};
	
	public LinkedList<Event> readEvents() {
		LinkedList<Event> eventsList = new LinkedList<Event>();
		try {
			JAXBContext context = JAXBContext.newInstance(EventDTO.class);
			
			Unmarshaller unmarshaller = context.createUnmarshaller();
			EventDTO events = (EventDTO) unmarshaller.unmarshal(xmlFile);
			
			eventsList = events.getEventsList();
		} catch(JAXBException e) {
			e.printStackTrace();
		}
		
		return eventsList;
	}
}