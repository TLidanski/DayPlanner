package ui.console.menu;

import event.repository.IEventsRepository;
import io.utils.IOParser;
import ui.console.menu.submenu.PrintAllEventsListItem;
import ui.console.menu.submenu.PrintAllEventsThisMonthItem;
import ui.console.menu.submenu.PrintEventsForADayItem;
import ui.console.menu.submenu.PrintEventsForAWeekItem;
import ui.console.menu.submenu.PrintEventsInRangeItem;

public class GetEventsItem implements IMenuItem {
	private String title;
	private IEventsRepository repo;
	
	private static final String TITLE = "2 - Get Events\n";
	
	public GetEventsItem(IEventsRepository repo) {
		title = TITLE;
		this.repo = repo;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void execute() {
		Menu subMenu = new Menu(
								new PrintAllEventsListItem(repo),
								new PrintEventsForADayItem(repo),
								new PrintEventsForAWeekItem(repo),
								new PrintAllEventsThisMonthItem(repo),
								new PrintEventsInRangeItem(repo)
						);
		subMenu.printItems();
		
		int t = IOParser.readSubMenu(System.in, System.out, "");
		subMenu.execute(t);
	}
}