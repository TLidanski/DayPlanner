package io.utils;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import event.Event;

public class IOParser {
	
	private static final String SUBMENU = "1-All events in a list\n2-All Events for a given day\n"
			+ "3-All Events for a given week\n4-All events this month\n5-Events between two dates";

	public static ZonedDateTime readDateTime(InputStream inStream, PrintStream outStream, String prompt) {
		ZonedDateTime date = null;
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
				
		String dateStr = new String();
		BufferedReader input = new BufferedReader(new InputStreamReader(inStream));
		
		try {
			outStream.println(prompt);
			dateStr = input.readLine(); // Validate the date
			
			LocalDateTime tempDate = LocalDateTime.parse(dateStr, formatter);
			date = ZonedDateTime.of(tempDate, ZoneId.systemDefault());
		} catch(IOException e) {
			e.printStackTrace();
		}
		
		return date;
	}
	
	public static String readEventDescription(InputStream inStream, PrintStream outStream, String prompt) {
		String description = new String();
		BufferedReader input = new BufferedReader(new InputStreamReader(inStream));
		
		try {
			outStream.println(prompt);
			description = input.readLine(); 
		} catch(IOException e) {
			e.printStackTrace();
		}
		
		return description;
	}
	
	public static String readEventId(InputStream inStream, PrintStream outStream, String prompt) {
		String id = null;
		BufferedReader input = new BufferedReader(new InputStreamReader(inStream));
		
		try {
			outStream.println(prompt);
			id = input.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return id;
	}
	
	public static int readSubMenu() {
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
	
	public static int readWeekNumber(InputStream inStream, PrintStream outStream, String prompt) {
		int weekNum = 0;
		BufferedReader input = new BufferedReader(new InputStreamReader(inStream));
		
		try {
			outStream.println(prompt);
			weekNum = Integer.parseInt(input.readLine());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return weekNum;
	}
	
	public static void printEvents(Iterable<Event> events) {
		for (Event event : events) {
			ZonedDateTime date = event.getDate();
			
			System.out.println("Event Date: " + date.toLocalDate() + " Event Start: " + date.toLocalTime() + 
					" Event Description: " + event.getDescription() + "\n");
		}
	}
	
}