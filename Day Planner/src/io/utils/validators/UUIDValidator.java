package io.utils.validators;

import java.util.UUID;

public class UUIDValidator implements IValidator {
	public boolean isValid(String input) {
		try {	
			UUID.fromString(input);
			return true;
		} catch(IllegalArgumentException e) {
			System.err.println(e.getMessage());
		}
		return false;
	}
}