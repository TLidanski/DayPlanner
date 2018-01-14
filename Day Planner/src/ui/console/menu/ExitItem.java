package ui.console.menu;

public class ExitItem implements IMenuItem {
	private String title;
	
	private static final String TITLE = "5 - Exit\n";
	
	public ExitItem() {
		title = TITLE;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void execute() {
		System.out.println("Exitting program");
	}
}