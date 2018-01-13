package ui.console.menu;

import java.util.UUID;

import event.repository.IEventsRepository;
import io.utils.IOParser;

public class DeleteEventItem implements IMenuItem {
	private String title;
	private IEventsRepository repo;
	
	private static final String TITLE = "4 - Delete Event\n";
	private static final String ID_PROMPT = "Enter the event's id\n";
	
	public DeleteEventItem(IEventsRepository repo) {
		title = TITLE;
		this.repo = repo;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void execute() {
		final UUID idToDelete = IOParser.readEventId(System.in, System.out, ID_PROMPT);
		
		repo.delete(idToDelete);
	}
}