package ui.console;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

import event.repository.IEventsRepository;
import event.repository.XmlEventsRepository;
import ui.console.menu.AddEventItem;
import ui.console.menu.DeleteEventItem;
import ui.console.menu.GetEventsItem;
import ui.console.menu.IMenuItem;
import ui.console.menu.Menu;
import ui.console.menu.UpdateEventItem;

public class ProcessManager {
	private static final String FILENAME = "events.xml";

	public static void main(String[] args) throws Exception {
		Path currentRelativePath = Paths.get("");
		String filePath = currentRelativePath.toAbsolutePath().resolve(FILENAME).toString();
		
		int i;
		try(
			IEventsRepository repo = new XmlEventsRepository(filePath); 
			Scanner sc = new Scanner(System.in)
		) {
			IMenuItem add = new AddEventItem(repo);
			IMenuItem get = new GetEventsItem(repo);
			IMenuItem update = new UpdateEventItem(repo);
			IMenuItem delete = new DeleteEventItem(repo);
			
			
			Menu menu = new Menu(add, get, update, delete);
			do {				
				menu.printItems();
				
				i = sc.nextInt();
				menu.execute(i);
			} while(i != 5);
		}
	}

}