import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.Scanner;

public class ProcessManager {
	
	private static final String INVALID = "Invalid Value";
	private static final String SELECTION_MENU = 
			"Select:\n1-Create Event\n2-Get Events\n3-Update Event\n4-Delete Event\n5-Exit";

	public static void main(String[] args) {
		Path currentRelativePath = Paths.get("");

		IOParser parser = new IOParser();
		IEventStorage storage = new XMLEventStorage(currentRelativePath.toAbsolutePath().toString() + "\\events.xml");
		
		LinkedList<Event> eventsList = (storage.isStorageFileEmpty()) ?
				new LinkedList<Event>() : storage.readEvents();
		
		Scanner sc = new Scanner(System.in);
		int i;
		do {
			System.out.println(SELECTION_MENU);
			
			i = sc.nextInt();
			switch (i) {
			case 1:
				String eventDate = parser.readEventDate();
				String eventTime = parser.readEventTime();
				String eventDesc = parser.readEventDescription();
				
				Event event = new Event(eventDate, eventTime, eventDesc);
				
				eventsList.add(event);
				break;
			
			case 2:
				parser.printEvents(eventsList);
				break;
			case 3:
				break;
				
			case 4:
				break;
				
			case 5:
				storage.writeEvents(eventsList);
				break;

			default: System.out.println(INVALID);
				break;
			}
		} while(i != 5);
		
		sc.close();
	}

}