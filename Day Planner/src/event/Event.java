package event;
import java.time.LocalDate;
import java.util.UUID;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlRootElement
@XmlType(propOrder = {"date", "time", "description"})
public class Event {
	private UUID id;
	private LocalDate date;
	private String time;
	private String description;
	
	public Event() {
	}
	
	public Event(LocalDate date, String time, String description) {
		id = UUID.randomUUID();
		this.date = date;
		this.time = time;
		this.description = description;
	}
	
	@XmlAttribute
	public UUID getId() {
		return id;
	}

	@XmlElement(name = "Date")
	@XmlJavaTypeAdapter(value = LocalDateAdapter.class)
	public LocalDate getDate() {
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
	public void setDate(LocalDate date) {
		this.date = date;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setId(UUID id) {
		this.id = id;
	}
}