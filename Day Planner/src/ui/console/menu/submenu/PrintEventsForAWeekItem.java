package ui.console.menu.submenu;

import event.repository.IEventsRepository;
import io.utils.IOParser;
import io.utils.TableRenderer;
import ui.console.menu.IMenuItem;

public class PrintEventsForAWeekItem implements IMenuItem {
	private String title;
	private IEventsRepository repo;
	
	private static final String TITLE = "3-All Events for a given week\n";
	private static final String WEEK_NUM_PROMPT = "Enter the week's number\n";
	
	public PrintEventsForAWeekItem(IEventsRepository repo) {
		title = TITLE;
		this.repo = repo;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void execute() {
		int weekNum = IOParser.readWeekNumber(System.in, System.out, WEEK_NUM_PROMPT);
		
		TableRenderer.renderWeekTable(weekNum, repo.getEvents(), System.out);
	}
}