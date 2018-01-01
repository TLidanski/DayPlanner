import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement
@XmlType(propOrder = {"date", "time", "description"})
public class Event {
	private String date;
	private String time;
	private String description;
	
	public Event() {
	}
	
	public Event(String date, String time, String description) {
		this.date = date;
		this.time = time;
		this.description = description;
	}

	@XmlElement(name = "Date")
	public String getDate() {
		return date;
	}

	@XmlElement(name = "Description")
	public String getDescription() {
		return description;
	}

	@XmlElement(name = "Time")
	public String getTime() {
		return time;
	}

	// Adding the setters so that the unmarshalling doesn't return null values
	public void setDate(String date) {
		this.date = date;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}