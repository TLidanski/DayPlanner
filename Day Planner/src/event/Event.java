package event;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.UUID;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import event.repository.ZonedDateTimeAdapter;

@XmlRootElement
@XmlType(propOrder = {"date", "description"})
public class Event {
	private UUID id;
	private ZonedDateTime date;
	private String description;
	
	public Event() {}
	
	public Event(UUID id, ZonedDateTime date, String description) {
		this.id = id;
		this.date = date;
		this.description = description;
	}
	
	@XmlAttribute
	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}
	
	@XmlElement
	@XmlJavaTypeAdapter(value = ZonedDateTimeAdapter.class)
	public ZonedDateTime getDate() {
		return date.withZoneSameInstant(ZoneId.systemDefault());
	}
	
	public void setDate(ZonedDateTime date) {
		this.date = date;
	}

	@XmlElement
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}