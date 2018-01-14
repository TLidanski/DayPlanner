package io.utils.validators;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateTimeValidator implements IValidator {
	public boolean isValid(String input) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
		
		try {
			LocalDateTime.parse(input, formatter);
			return true;
		} catch (DateTimeParseException e) {
			System.err.println("Invalid date and/or time format");
		}
		
		return false;
	}
}