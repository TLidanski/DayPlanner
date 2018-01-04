package storage;
import java.util.LinkedList;

import event.Event;

public interface IEventStorage {
	public boolean isStorageFileEmpty();
	
	public void writeEvents(LinkedList<Event> eventsList);
	
	public LinkedList<Event> readEvents();
}