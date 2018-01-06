package io;

import java.time.LocalDate;

import event.Event;

public class TableRenderer {
	public static void renderDayTable(LocalDate date, Iterable<Event> events) {
		System.out.println(date.getMonth().name() + "\n");
		System.out.println(date.getDayOfWeek().name() + "\t" + date.getDayOfMonth());
		System.out.println("---------------------------");
		System.out.printf("%-10s %-20s\n", "Time", "Description");
		System.out.println("---------------------------");
		for (Event event : events) {
			System.out.printf("%-10s %-20s\n", event.getTime(), event.getDescription());
			System.out.println();
		}
		System.out.println("---------------------------");
	}
	
	public void renderWeekTable() {}
	
	public void renderMonthTable() {}
}