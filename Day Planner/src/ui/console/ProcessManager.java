package ui.console;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.time.temporal.WeekFields;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.UUID;

import event.Event;
import event.repository.IEventsRepository;
import event.repository.XmlEventsRepository;
import io.utils.IOParser;
import io.utils.TableRenderer;

public class ProcessManager {
	
	private static final String DESC_PROMPT = "Enter the event's description\n";
	private static final String DATE_PROMPT = "Enter the date [Format: dd-MM-yyyy HH:mm]\n";
	private static final String ID_PROMPT = "Enter the event's id\n";
	private static final String FILENAME = "events.xml";
	private static final String INVALID = "Invalid Value";
	private static final String WEEK_NUM_PROMPT = "Enter the week's number\n";
	private static final String SELECTION_MENU = 
			"Select:\n1-Create Event\n2-Get Events\n3-Update Event\n4-Delete Event\n5-Exit";

	public static void main(String[] args) throws Exception {
		Path currentRelativePath = Paths.get("");
		String filePath = currentRelativePath.toAbsolutePath().resolve(FILENAME).toString();
			
		Event event;		
		ZonedDateTime eventDateTime;
		String eventDesc;
		UUID id;
		
		int i;
		try(
			IEventsRepository repo = new XmlEventsRepository(filePath); 
			Scanner sc = new Scanner(System.in)
		) {
			List<Event> eventsList = repo.readEvents();
			
			do {
				System.out.println(SELECTION_MENU);
				
				i = sc.nextInt();
				switch (i) {
					case 1:
						eventDateTime = IOParser.readDateTime(System.in, System.out, DATE_PROMPT);
						eventDesc = IOParser.readEventDescription(System.in, System.out, DESC_PROMPT);
						id = UUID.randomUUID();
						
						event = new Event(id, eventDateTime, eventDesc);
						
						eventsList.add(event);
					break;
					
					case 2:
				
						int select = IOParser.readSubMenu();
						switch (select) {
							case 1:
								IOParser.printEvents(eventsList);
							break;
			
							case 2:
								eventDateTime = IOParser.readDateTime(System.in, System.out, DATE_PROMPT);
								
								TableRenderer.renderDayTable(eventDateTime.toLocalDate(), eventsList, System.out);
							break;
							
							case 3:
								int weekNum = IOParser.readWeekNumber(System.in, System.out, WEEK_NUM_PROMPT);
								TableRenderer.renderWeekTable(weekNum, eventsList, System.out);
							break;
							
							case 4:
								TableRenderer.renderMonthTable(eventsList, System.out);
							break;
							
							case 5:								
								LocalDate fromDate = IOParser.readDateTime(System.in, System.out, DATE_PROMPT).toLocalDate();
								LocalDate toDate = IOParser.readDateTime(System.in, System.out, DATE_PROMPT).toLocalDate();
								
								List<Event> events = repo.getEventsBetweenDates(fromDate, toDate, eventsList);
								WeekFields weekFields = WeekFields.of(Locale.getDefault());
								
								if(!(events.isEmpty())) {
									fromDate = events.get(0).getDate().toLocalDate();
									toDate = events.get(events.size() - 1).getDate().toLocalDate();
									
									int week = fromDate.get(weekFields.weekOfMonth());
									long diff = ChronoUnit.DAYS.between(fromDate, toDate);
									
									if(diff <= 7) {
										TableRenderer.renderWeekTable(week, events, System.out);
									} else {
										TableRenderer.renderMonthTable(events, System.out);
									}
								}
						  
							break;
							
							default:
							break;
						}
						
					break;
					case 3:
						String eventId = IOParser.readEventId(System.in, System.out, ID_PROMPT);
						eventDateTime = IOParser.readDateTime(System.in, System.out, DATE_PROMPT);
						eventDesc = IOParser.readEventDescription(System.in, System.out, DESC_PROMPT);
						
						repo.updateEvent(eventsList, eventId, eventDateTime, eventDesc);
					break;
						
					case 4:
						final UUID idToDelete = UUID.fromString(IOParser.readEventId(System.in, System.out, ID_PROMPT));
						
						repo.removeEvent(idToDelete, eventsList);
					break;
						
					case 5:
						Collections.sort(eventsList, (e1, e2) -> e1.getDate().compareTo(e2.getDate()));
						repo.writeEvents(eventsList);
					break;
		
					default: 
						System.out.println(INVALID);
					break;
				}
			} while(i != 5);
		}
	}

}