package io.utils.validators;

public class SubMenuRangeValidator implements IValidator {
	public boolean isValid(String input) {
		int selection; 
		try {
			selection = Integer.parseInt(input);
			
			return (selection >=1 && selection <= 5);
		} catch (NumberFormatException e) {
			System.err.println("The provided input can't be parsed to an integer");
		}
		
		return false;
	}
}