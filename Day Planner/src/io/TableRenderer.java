package io;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.ChronoField;

import event.Event;

public class TableRenderer {
	public static void renderDayTable(LocalDate date, Iterable<Event> events) {
		System.out.println(date.getMonth().name() + "\n");
		System.out.println(date.getDayOfWeek().name() + "\t" + date.getDayOfMonth());
		System.out.println("---------------------------");
		System.out.printf("%-10s %-20s\n", "Time", "Description");
		System.out.println("---------------------------");
		for (Event event : events) {
			if(event.getDate().isEqual(date)) {
				System.out.printf("%-10s %-20s\n", event.getTime(), event.getDescription());
				System.out.println();
			}
		}
		System.out.println("===========================");
		System.out.println("===========================");
	}
	
	public static void renderWeekTable(int weekNum, Iterable<Event> events) {
		LocalDate week = LocalDate.now().with(ChronoField.ALIGNED_WEEK_OF_MONTH, weekNum);
		LocalDate start = week.with(DayOfWeek.MONDAY);
		
		for(int i = 0; i < 6; i++) {
			LocalDate currDate = start.plusDays(i);
			renderDayTable(currDate, events);
		}
	}
	
	public static void renderMonthTable(Iterable<Event> events) {
		LocalDate now = LocalDate.now();
		LocalDate firstOfTheMonth = now.withDayOfMonth(1);
		
		for(int i = 0; i < now.lengthOfMonth(); i++) {
			LocalDate currDate = firstOfTheMonth.plusDays(i);
			renderDayTable(currDate, events);
		}
	}
}