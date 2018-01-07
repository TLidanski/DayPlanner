package io;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import event.Event;

public class IOParser {
	
	private static final String SUBMENU = "1-All events in a list\n2-All Events for a given day\n3-All Events for a given week\n4-All events this month\n5-Events between two dates";

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
	
	public LocalTime readEventTime() {
		LocalTime time = null;
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
		
		String timeStr = new String();
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		
		try {
			System.out.println("Enter the event's start time\n");
			timeStr = input.readLine(); // Validate the time
			
			time = LocalTime.parse(timeStr, formatter);
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
	
	public int readSubMenu() {
		int select = 0;
		try {		
			System.out.println(SUBMENU);
				
			BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
			String inputString = input.readLine();
			
			select = Integer.parseInt(inputString);
				
		} catch(IOException e) {
			e.printStackTrace();
		}
		
		return select;
	}
	
	public int readWeekNumber() {
		int weekNum = 0;
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		
		try {
			System.out.println("Enter the week's number\n");
			weekNum = Integer.parseInt(input.readLine());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return weekNum;
	}
	
	public void printEvents(Iterable<Event> events) {
		for (Event event : events) {
			System.out.println("Event Date: " + event.getDate() + 
					" Event Start: " + event.getTime() + " Event Description: " + event.getDescription() + "\n");
		}
	}
	
}