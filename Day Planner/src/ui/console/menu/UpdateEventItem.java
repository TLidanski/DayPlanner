package ui.console.menu;

import java.time.ZonedDateTime;
import java.util.UUID;

import event.repository.IEventsRepository;
import io.utils.IOParser;

public class UpdateEventItem implements IMenuItem {
	private String title;
	private IEventsRepository repo;
	
	private static final String TITLE = "3 - Update Event\n";
	private static final String DESC_PROMPT = "Enter the event's description\n";
	private static final String DATE_PROMPT = "Enter the date [Format: dd-MM-yyyy HH:mm]\n";
	private static final String ID_PROMPT = "Enter the event's id\n";
	
	public UpdateEventItem(IEventsRepository repo) {
		title = TITLE;
		this.repo = repo;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void execute() {
		UUID eventId = IOParser.readEventId(System.in, System.out, ID_PROMPT);
		ZonedDateTime eventDateTime = IOParser.readDateTime(System.in, System.out, DATE_PROMPT);
		String eventDesc = IOParser.readEventDescription(System.in, System.out, DESC_PROMPT);
		
		repo.update(eventId, eventDateTime, eventDesc);
	}
}