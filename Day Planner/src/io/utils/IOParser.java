package io.utils;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.UUID;

import event.Event;
import io.utils.transformers.ITransformer;
import io.utils.transformers.IntegerTransformer;
import io.utils.transformers.LocalDateTransformer;
import io.utils.transformers.UUIDTransformer;
import io.utils.transformers.ZonedDateTimeTransformer;
import io.utils.validators.DateTimeValidator;
import io.utils.validators.DateValidator;
import io.utils.validators.IValidator;
import io.utils.validators.NumberOfWeeksValidator;
import io.utils.validators.SubMenuRangeValidator;
import io.utils.validators.UUIDValidator;

public class IOParser {

	public static ZonedDateTime readDateTime(InputStream inStream, PrintStream outStream, String prompt) {
		String date;
		ITransformer<ZonedDateTime> transformer = new ZonedDateTimeTransformer();
		IValidator validator = new DateTimeValidator();
		
		do {
			date = readInput(inStream, outStream, prompt);
		} while (!(validator.isValid(date)));
		
		return transformer.transform(date);
	}
	
	public static LocalDate readDate(InputStream inStream, PrintStream outStream, String prompt) {
		String date;
		ITransformer<LocalDate> transformer = new LocalDateTransformer();
		IValidator validator = new DateValidator();
		
		do {
			date = readInput(inStream, outStream, prompt);
		} while (!(validator.isValid(date)));
		
		return transformer.transform(date);
	}
	
	public static String readEventDescription(InputStream inStream, PrintStream outStream, String prompt) {
		return readInput(inStream, outStream, prompt);
	}
	
	public static UUID readEventId(InputStream inStream, PrintStream outStream, String prompt) {
		String id;
		ITransformer<UUID> transformer = new UUIDTransformer();
		IValidator validator = new UUIDValidator();
		
		do {
			id = readInput(inStream, outStream, prompt);
		} while (!(validator.isValid(id)));
		
		return transformer.transform(id);
	}
	
	public static int readSubMenu(InputStream inStream, PrintStream outStream, String prompt) {
		String selection;
		ITransformer<Integer> transformer = new IntegerTransformer();
		IValidator validator = new SubMenuRangeValidator();
		
		do {
			selection = readInput(inStream, outStream, prompt);
		} while (!(validator.isValid(selection)));
		
		return transformer.transform(selection);
	}
	
	public static int readWeekNumber(InputStream inStream, PrintStream outStream, String prompt) {
		String weekNum;
		ITransformer<Integer> transformer = new IntegerTransformer();
		IValidator validator = new NumberOfWeeksValidator();
		
		do {
			weekNum = readInput(inStream, outStream, prompt);
		} while (!(validator.isValid(weekNum)));
		
		return transformer.transform(weekNum);		
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