package io.utils;

import java.io.PrintStream;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoField;

import event.Event;

public class TableRenderer {
	public static void renderDayTable(LocalDate date, Iterable<Event> events, PrintStream outStream) {
		outStream.println(date.getMonth().name() + "\n");
		outStream.println(date.getDayOfWeek().name() + "\t" + date.getDayOfMonth());
		outStream.println("---------------------------");
		outStream.printf("%-10s %-20s\n", "Time", "Description");
		outStream.println("---------------------------");
		for (Event event : events) {
			if(event.getDate().toLocalDate().isEqual(date)) {
				outStream.printf("%-10s %-20s\n", event.getDate().toLocalTime(), event.getDescription());
				outStream.println();
			}
		}
		outStream.println("===========================");
		outStream.println("===========================");
	}
	
	public static void renderWeekTable(int weekNum, Iterable<Event> events, PrintStream outStream) {
		ZoneId zone = ZoneId.systemDefault();
		ZonedDateTime week = ZonedDateTime.now(zone).with(ChronoField.ALIGNED_WEEK_OF_MONTH, weekNum);
		ZonedDateTime start = week.with(DayOfWeek.MONDAY);
		
		for(int i = 0; i < 6; i++) {
			LocalDate currDate = start.plusDays(i).toLocalDate();
			renderDayTable(currDate, events, outStream);
		}
	}
	
	public static void renderMonthTable(Iterable<Event> events, PrintStream outStream) {
		ZoneId zone = ZoneId.systemDefault();
		ZonedDateTime now = ZonedDateTime.now(zone);
		LocalDate firstOfTheMonth = now.withDayOfMonth(1).toLocalDate();
		
		for(int i = 0; i < now.toLocalDate().lengthOfMonth(); i++) {
			LocalDate currDate = firstOfTheMonth.plusDays(i);
			renderDayTable(currDate, events, outStream);
		}
	}
}