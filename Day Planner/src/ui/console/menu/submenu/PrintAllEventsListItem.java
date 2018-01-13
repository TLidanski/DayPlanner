package ui.console.menu.submenu;

import event.repository.IEventsRepository;
import io.utils.IOParser;
import ui.console.menu.IMenuItem;

public class PrintAllEventsListItem implements IMenuItem {
	private String title;
	private IEventsRepository repo;
	
	private static final String TITLE = "1-All events in a list\n";
	
	public PrintAllEventsListItem(IEventsRepository repo) {
		title = TITLE;
		this.repo = repo;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void execute() {
		IOParser.printEvents(repo.getEvents());
	}
}