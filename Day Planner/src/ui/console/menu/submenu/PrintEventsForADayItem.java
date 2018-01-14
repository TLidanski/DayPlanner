package ui.console.menu.submenu;

import java.time.LocalDate;

import event.repository.IEventsRepository;
import io.utils.IOParser;
import io.utils.TableRenderer;
import ui.console.menu.IMenuItem;

public class PrintEventsForADayItem implements IMenuItem {
	private String title;
	private IEventsRepository repo;
	
	private static final String TITLE = "2-All Events for a given day\n";
	private static final String DATE_PROMPT = "Enter the date [Format: dd-MM-yyyy]\n";
	
	public PrintEventsForADayItem(IEventsRepository repo) {
		title = TITLE;
		this.repo = repo;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void execute() {
		LocalDate eventDate = IOParser.readDate(System.in, System.out, DATE_PROMPT);
		
		TableRenderer.renderDayTable(eventDate, repo.getEvents(), System.out);
	}
}