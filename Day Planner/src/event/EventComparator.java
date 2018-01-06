package event;

import java.util.Comparator;

public class EventComparator implements Comparator<Event> {
	@Override
	public int compare(Event e1, Event e2) {
		int dateResult = e1.getDate().compareTo(e2.getDate());
		if(dateResult != 0)
			return dateResult;
		
		return e1.getTime().compareTo(e2.getTime());
	}
}