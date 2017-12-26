import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

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
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class DataStorage {
	public DataStorage() {
		
	}
	
	
	public static void createXMLFile() {
		try {
			File xmlFile = new File("D:\\Програмиране\\DayPlanner\\Day Planner\\events.xml");
			
			if(!(xmlFile.exists())) {
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
			}
			 
		} catch (ParserConfigurationException|TransformerException e) {
			e.printStackTrace();
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
			 e.printStackTrace();
		}
	}
	
	public Map<Integer, Event> readEvents() throws NoEventsToBeReadException {
		Map<Integer, Event> events = new HashMap<Integer, Event>();
		
		try {
			File xmlFile = new File("D:\\Програмиране\\DayPlanner\\Day Planner\\events.xml");
			
			DocumentBuilderFactory dFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = dFactory.newDocumentBuilder();
			Document doc = docBuilder.parse(xmlFile);
			
			doc.getDocumentElement().normalize();
			
			NodeList nList = doc.getElementsByTagName("Event");
			
			for (int tempNum = 0; tempNum < nList.getLength(); tempNum++) {
				Node node = nList.item(tempNum);
				
				if(node.getNodeType() == Node.ELEMENT_NODE) {
					Element currentElement = (Element)node;
					Event event = new Event(currentElement.getElementsByTagName("Date").item(0).getTextContent(), 
							currentElement.getElementsByTagName("Description").item(0).getTextContent());
					
					events.put(tempNum, event);
				}
			}
			
			if(events.isEmpty())
				throw new NoEventsToBeReadException();
			
			
		} catch(IOException|ParserConfigurationException|SAXException e) {
			e.printStackTrace();
		}
		
		return events;
	}
}