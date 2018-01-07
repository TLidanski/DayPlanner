import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;

import event.Event;
import event.EventComparator;
import event.EventSelector;
import io.IOParser;
import io.TableRenderer;
import storage.IEventStorage;
import storage.XMLEventStorage;

public class ProcessManager {
	
	private static final String INVALID = "Invalid Value";
	private static final String SELECTION_MENU = 
			"Select:\n1-Create Event\n2-Get Events\n3-Update Event\n4-Delete Event\n5-Exit";

	public static void main(String[] args) {
		Path currentRelativePath = Paths.get("");

		IOParser parser = new IOParser();
		EventSelector selector = new EventSelector();
		IEventStorage storage = new XMLEventStorage(currentRelativePath.toAbsolutePath().toString() + "\\events.xml");
		
		LinkedList<Event> eventsList = (storage.isStorageFileEmpty()) ?
				new LinkedList<Event>() : storage.readEvents();
			
		Event event;		
		LocalDate eventDate;
		LocalTime eventTime;
		String eventDesc;
		String id;
		
		Scanner sc = new Scanner(System.in);
		int i;
		do {
			System.out.println(SELECTION_MENU);
			Collections.sort(eventsList, new EventComparator());
			
			i = sc.nextInt();
			switch (i) {
				case 1:
					eventDate = parser.readEventDate();
					eventTime = parser.readEventTime();
					eventDesc = parser.readEventDescription();
					
					event = new Event(eventDate, eventTime, eventDesc);
					
					eventsList.add(event);
				break;
				
				case 2:
					int select = parser.readSubMenu();
					switch (select) {
						case 1:
							parser.printEvents(eventsList);
						break;
		
						case 2:
							eventDate = parser.readEventDate();
							LinkedList<Event> tempEvents = selector.getEventsByDay(eventDate, eventsList);
							
							TableRenderer.renderDayTable(eventDate, tempEvents);
						break;
						
						case 3:
							int weekNum = parser.readWeekNumber();
							TableRenderer.renderWeekTable(weekNum, eventsList);
						break;
						
						case 4:
							TableRenderer.renderMonthTable(eventsList);
						break;
						
						default:
						break;
					}
					
				break;
				case 3:
					id = parser.readEventId();
					boolean idExists = false;
					
					for (Iterator<Event> iterator = eventsList.iterator(); iterator.hasNext();) {
						Event currEvent = iterator.next();
						if(currEvent.getId().toString().equals(id)) {
							idExists = true;
							
							eventDate = parser.readEventDate();
							eventTime = parser.readEventTime();
							eventDesc = parser.readEventDescription();
							
							currEvent.setDate(eventDate);
							currEvent.setTime(eventTime);
							currEvent.setDescription(eventDesc);
						}
					}
					
					if(!idExists)
						System.out.println("The provided id doesn't exist!\n");
				break;
					
				case 4:
					final String idToDelete = parser.readEventId();
					
					eventsList.removeIf(e -> e.getId().toString().equals(idToDelete));
				break;
					
				case 5:
					storage.writeEvents(eventsList);
				break;
	
				default: 
					System.out.println(INVALID);
				break;
			}
		} while(i != 5);
		
		sc.close();
	}

}