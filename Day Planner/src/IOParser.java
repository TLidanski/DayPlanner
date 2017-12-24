import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class IOParser {
	
	public String[] parseEventData()  /*throws EmptyEventDataException*/ {
		String[] data = new String[2];
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		String date, description;
		
		try {
			System.out.println("Enter the event's date\n");
			date = input.readLine();
			System.out.println("Enter the event's description\n");
			description = input.readLine();
		
			data[0] = date;
			data[1] = description;
		} catch(IOException e) {
			e.printStackTrace();
		}
		
		return data;
	}
	
}