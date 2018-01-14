package io.utils.transformers;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class LocalDateTransformer implements ITransformer<LocalDate> {
	public LocalDate transform(String value) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");	
		
		return LocalDate.parse(value, formatter);
	}
}