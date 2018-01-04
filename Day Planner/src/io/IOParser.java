package io;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import event.Event;

public class IOParser {
	
	public LocalDate readEventDate() {
		LocalDate date = null;
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
				
		String dateStr = new String();
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		
		try {
			System.out.println("Enter the event's date [Format: dd-MM-yyyy]\n");
			dateStr = input.readLine(); // Validate the date
			
			date = LocalDate.parse(dateStr, formatter);
		} catch(IOException e) {
			e.printStackTrace();
		}
		
		return date;
	}
	
	public String readEventTime() {
		String time = new String();
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		
		try {
			System.out.println("Enter the event's start time\n");
			time = input.readLine(); // Validate the time
		} catch(IOException e) {
			e.printStackTrace();
		}
		
		return time;
	}
	
	public String readEventDescription() {
		String description = new String();
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		
		try {
			System.out.println("Enter the event's description\n");
			description = input.readLine(); 
		} catch(IOException e) {
			e.printStackTrace();
		}
		
		return description;
	}
	
	public String readEventId() {
		String id = null;
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		
		try {
			System.out.println("Enter the event's id\n");
			id = input.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return id;
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