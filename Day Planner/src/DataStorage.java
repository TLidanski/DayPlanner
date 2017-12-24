import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

public class DataStorage {
	public DataStorage() {
		
	}
	
	
	public static void createXMLFile() {
		try {
			File xmlFile = new File("D:\\Програмиране\\DayPlanner\\Day Planner\\events.xml");
			
			DocumentBuilderFactory dFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = dFactory.newDocumentBuilder();
			Document doc = docBuilder.newDocument();
			 
			Element rootElement = doc.createElement("Events");
			doc.appendChild(rootElement);
			 
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			 
			StreamResult result = new StreamResult(xmlFile);
			 
			transformer.transform(source, result);
			 
		} catch (ParserConfigurationException|TransformerException e) {
			
		}
	}
	
	public void createEvent(Event event) {
		try {
			File xmlFile = new File("D:\\Програмиране\\DayPlanner\\Day Planner\\events.xml");
			
			DocumentBuilderFactory dFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = dFactory.newDocumentBuilder();
			Document doc = docBuilder.parse(xmlFile);
			 
			Element rootElement = doc.getDocumentElement();
			 
			Element eventElement = doc.createElement("Event");
			Element date = doc.createElement("Date");
			Element description = doc.createElement("Description");
			
			date.appendChild(doc.createTextNode(event.getDate()));
			description.appendChild(doc.createTextNode(event.getDescription()));
			
			eventElement.appendChild(date);
			eventElement.appendChild(description);
			
			rootElement.appendChild(eventElement); 
			
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			 
			StreamResult result = new StreamResult(xmlFile);
			 
			transformer.transform(source, result);
		} catch(ParserConfigurationException|SAXException|IOException|TransformerException e) {
			 // Catch them
		}
	}
}