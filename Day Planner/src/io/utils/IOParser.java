package io.utils;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.time.ZonedDateTime;
import java.util.UUID;

import event.Event;
import io.utils.transformers.ITransformer;
import io.utils.transformers.IntegerTransformer;
import io.utils.transformers.UUIDTransformer;
import io.utils.transformers.ZonedDateTimeTransformer;

public class IOParser {

	public static ZonedDateTime readDateTime(InputStream inStream, PrintStream outStream, String prompt) {
		ITransformer<ZonedDateTime> transformer = new ZonedDateTimeTransformer();
		
		return transformer.transform(readInput(inStream, outStream, prompt));
	}
	
	public static String readEventDescription(InputStream inStream, PrintStream outStream, String prompt) {
		return readInput(inStream, outStream, prompt);
	}
	
	public static UUID readEventId(InputStream inStream, PrintStream outStream, String prompt) {
		ITransformer<UUID> transformer = new UUIDTransformer();
		
		return transformer.transform(readInput(inStream, outStream, prompt));
	}
	
	public static int readSubMenu(InputStream inStream, PrintStream outStream, String prompt) {
		ITransformer<Integer> transformer = new IntegerTransformer();
		
		return transformer.transform(readInput(inStream, outStream, prompt));
	}
	
	public static int readWeekNumber(InputStream inStream, PrintStream outStream, String prompt) {
		ITransformer<Integer> transformer = new IntegerTransformer();
		
		return transformer.transform(readInput(inStream, outStream, prompt));		
	}
	
	public static void printEvents(Iterable<Event> events) {
		for (Event event : events) {
			ZonedDateTime date = event.getDate();
			
			System.out.println("Event Date: " + date.toLocalDate() + " Event Start: " + date.toLocalTime() + 
					" Event Description: " + event.getDescription() + "\n");
		}
	}
	
	private static String readInput(InputStream inStream, PrintStream outStream, String prompt) {
		BufferedReader input = new BufferedReader(new InputStreamReader(inStream));
		
		try {
			outStream.println(prompt);
			return input.readLine();
		} catch (IOException e) {
			throw new RuntimeException(e.getMessage());
		}
	}
	
}