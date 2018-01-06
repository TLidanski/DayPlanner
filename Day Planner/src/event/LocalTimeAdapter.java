package event;

import java.time.LocalTime;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class LocalTimeAdapter extends XmlAdapter<String, LocalTime>{
	public LocalTime unmarshal(String v) throws Exception {
        return LocalTime.parse(v);
    }

    public String marshal(LocalTime v) throws Exception {
        return v.toString();
    }
}