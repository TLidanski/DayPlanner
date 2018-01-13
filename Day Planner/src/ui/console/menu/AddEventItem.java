package ui.console.menu;

import java.time.ZonedDateTime;

import event.repository.IEventsRepository;
import io.utils.IOParser;

public class AddEventItem implements IMenuItem {
	private String title;
	private IEventsRepository repo;
	
	private static final String TITLE = "1 - Add Event\n";
	private static final String DESC_PROMPT = "Enter the event's description\n";
	private static final String DATE_PROMPT = "Enter the date [Format: dd-MM-yyyy HH:mm]\n";
	
	public AddEventItem(IEventsRepository repo) {
		title = TITLE;
		this.repo = repo;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void execute() {
		ZonedDateTime eventDateTime = IOParser.readDateTime(System.in, System.out, DATE_PROMPT);
		String eventDesc = IOParser.readEventDescription(System.in, System.out, DESC_PROMPT);

		repo.create(eventDateTime, eventDesc);
	}
}