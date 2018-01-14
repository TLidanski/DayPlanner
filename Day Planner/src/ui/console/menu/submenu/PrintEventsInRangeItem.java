package ui.console.menu.submenu;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.time.temporal.WeekFields;
import java.util.List;
import java.util.Locale;

import event.Event;
import event.repository.IEventsRepository;
import io.utils.IOParser;
import io.utils.TableRenderer;
import ui.console.menu.IMenuItem;

public class PrintEventsInRangeItem implements IMenuItem {
	private String title;
	private IEventsRepository repo;
	
	private static final String TITLE = "5-Events between two dates";
	private static final String DATE_PROMPT = "Enter the date [Format: dd-MM-yyyy]\n";
	
	public PrintEventsInRangeItem(IEventsRepository repo) {
		title = TITLE;
		this.repo = repo;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void execute() {
		LocalDate fromDate = IOParser.readDate(System.in, System.out, DATE_PROMPT);
		LocalDate toDate = IOParser.readDate(System.in, System.out, DATE_PROMPT);
		
		List<Event> events = (List<Event>) repo.getInRange(fromDate, toDate);
		WeekFields weekFields = WeekFields.of(Locale.getDefault());
		
		if(!(events.isEmpty())) {
			fromDate = events.get(0).getDate().toLocalDate();
			toDate = events.get(events.size() - 1).getDate().toLocalDate();
			
			int week = fromDate.get(weekFields.weekOfMonth());
			long diff = ChronoUnit.DAYS.between(fromDate, toDate);
			
			if(diff <= 7) {
				TableRenderer.renderWeekTable(week, events, System.out);
			} else {
				TableRenderer.renderMonthTable(events, System.out);
			}
		}
	}
}