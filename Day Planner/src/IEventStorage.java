import java.util.LinkedList;

public interface IEventStorage {
	public boolean isStorageFileEmpty();
	
	public void writeEvents(LinkedList<Event> eventsList);
	
	public LinkedList<Event> readEvents();
}