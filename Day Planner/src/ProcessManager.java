import java.util.Map;
import java.util.Scanner;

public class ProcessManager {
	
	private static void renderMenu() {
		System.out.println("Select:\n1-Create Events\n2-Get Event\n3-Update Event\n4-Delete Event");
		
		IOParser parser = new IOParser();
		DataStorage storage = new DataStorage();
		
		Scanner sc = new Scanner(System.in);
		int i = sc.nextInt();
		
		switch (i) {
		case 1:
			String[] data = parser.parseEventData();
			
			Event event = new Event(data[0], data[1]);
			
			storage.createEvent(event);
			renderMenu();
			break;
		
		case 2:
			try {
				Map<Integer, Event> events = storage.readEvents();
				
				parser.printListOfEvents(events);
				
				renderMenu();
			} catch(NoEventsToBeReadException e) {
				e.printStackTrace();
			}
			break;

		default:
			break;
		}
		sc.close();	
	}
	
	public static void main(String[] args) {
		DataStorage.createXMLFile();
		
		renderMenu();
	}

}