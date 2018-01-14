package io.utils.validators;

import java.util.Calendar;

public class NumberOfWeeksValidator implements IValidator {
	public boolean isValid(String input) {
		int weekNum;
		try {
			weekNum = Integer.parseInt(input);
			Calendar calendar = Calendar.getInstance();
			int maxWeeks = calendar.getActualMaximum(Calendar.WEEK_OF_MONTH);
		
			return (weekNum >=1 && weekNum <= maxWeeks);
		} catch (NumberFormatException e) {
			System.err.println("Please enter a valid week number");
		}
	
		return false;
	}
}