package ui.console.menu.submenu;

import event.repository.IEventsRepository;
import io.utils.TableRenderer;
import ui.console.menu.IMenuItem;

public class PrintAllEventsThisMonthItem implements IMenuItem {
	private String title;
	private IEventsRepository repo;
	
	private static final String TITLE = "4-All events this month\n";
	
	public PrintAllEventsThisMonthItem(IEventsRepository repo) {
		title = TITLE;
		this.repo = repo;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void execute() {
		TableRenderer.renderMonthTable(repo.getEvents(), System.out);
	}
}