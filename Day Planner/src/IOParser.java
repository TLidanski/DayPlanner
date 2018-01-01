import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class IOParser {
	
	public String readEventDate() {
		String date = new String();;
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		
		try {
			System.out.println("Enter the event's date\n");
			date = input.readLine(); // Validate the date
		} catch(IOException e) {
			e.printStackTrace();
		}
		
		return date;
	}
	
	public String readEventTime() {
		String time = new String();;
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		
		try {
			System.out.println("Enter the event's start time\n");
			time = input.readLine(); // Validate the date
		} catch(IOException e) {
			e.printStackTrace();
		}
		
		return time;
	}
	
	public String readEventDescription() {
		String description = new String();;
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		
		try {
			System.out.println("Enter the event's description\n");
			description = input.readLine(); // Validate the date
		} catch(IOException e) {
			e.printStackTrace();
		}
		
		return description;
	}
	
//	public int readEventsMenu(int menuNum) {
//		int select = 0;
//		try {
//			if(menuNum == 1) {
//				System.out.println("1 - Print all events in a list\n2 - Print all the events in a table");
//			} else {
//				System.out.println("1-All Events for a given day\n2-All Events for a given week\n3-All events this month\n4-Events between two dates");
//			}
//				
//			BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
//			String inputString = input.readLine();
//			
//			select = Integer.parseInt(inputString);
//				
//		} catch(IOException e) {
//			e.printStackTrace();
//		}
//		
//		return select;
//	}
	
	public void printEvents(Iterable<Event> events) {
		for (Event event : events) {
			System.out.println("Event Date: " + event.getDate() + 
					" Event Start: " + event.getTime() + " Event Description: " + event.getDescription() + "\n");
		}
	}
	
}