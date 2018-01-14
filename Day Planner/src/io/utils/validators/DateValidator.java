package io.utils.validators;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateValidator implements IValidator {
	public boolean isValid(String input) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		
		try {
			LocalDate.parse(input, formatter);
			return true;
		} catch (DateTimeParseException e) {
			System.err.println("Invalid date format");
		}
		
		return false;
	}
}