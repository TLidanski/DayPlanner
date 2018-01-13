package io.utils.transformers;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class ZonedDateTimeTransformer implements ITransformer<ZonedDateTime> {
	public ZonedDateTime transform(String value) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");	
		LocalDateTime tempDate = LocalDateTime.parse(value, formatter);
		
		return ZonedDateTime.of(tempDate, ZoneId.systemDefault());
	}
}