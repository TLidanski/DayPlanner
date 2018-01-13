package ui.console.menu;

import java.util.ArrayList;
import java.util.List;

public class Menu {
	private List<IMenuItem> items;
	private static final String INVALID = "Invalid Value";
	
	public Menu(IMenuItem ...items) {
		this.items = new ArrayList<IMenuItem>();
		
		for (IMenuItem menuItem : items) {
			this.items.add(menuItem);
		}
	}
	
	public void printItems() {
		for (IMenuItem item : items) {
			System.out.println(item.getTitle());
		}
	}
	
	public void execute(int i) {
		if(i <= items.size()) {
			items.get(i - 1).execute();
		} else {
			if(i != 5)
				System.out.println(INVALID);
		}
	}
}