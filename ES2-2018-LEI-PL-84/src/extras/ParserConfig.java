package extras;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
public class ParserConfig {
	
	Document doc;
	Element rootElement;
	
	public ParserConfig() {
		try {
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder(); 
			doc = docBuilder.newDocument();
			rootElement = doc.createElement("config");
			doc.appendChild(rootElement);
			
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	     
	public void add_Content(String admin_Name, String admin_Email, String[] algorithms) {
		Element admin = doc.createElement("Admin");
		rootElement.appendChild(admin);
		Element algorithm = doc.createElement("Algorithms");
		rootElement.appendChild(algorithm);
		Element adminEmail = doc.createElement("email");
		adminEmail.appendChild(doc.createTextNode(admin_Email));
		admin.appendChild(adminEmail);
		Element adminName = doc.createElement("name");
		adminName.appendChild(doc.createTextNode(admin_Name));
		admin.appendChild(adminName);
		for( int i=0;i<algorithms.length;i++) {
			System.out.println(algorithms[i]);
			Element it = doc.createElement("Algorithm");
//			it.setAttribute("id", ""+i);
			it.appendChild(doc.createTextNode(algorithms[i]));
			algorithm.appendChild(it);

		}
	}
	
	
	
	public void write_ConfigXML() {
		try {
			// write the content into xml file
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new File("/Users/mohammadmudassir/Desktop/config.xml"));
			//-------//
			transformer.transform(source, result);
			System.out.println("File saved!");
		} catch (TransformerException tfe) {
				tfe.printStackTrace();
		}
	}
	public void read_ConfigXML() {
		try {
			File fXML = new File("/Users/mohammadmudassir/Desktop/config.xml");
//			Scanner s = new Scanner(fXML);
//			System.out.println(s.nextLine());
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXML);
			NodeList nodeList = doc.getDocumentElement().getChildNodes();
			for(int i=0;i<nodeList.getLength();i++) {
				Node node = nodeList.item(i); 
				System.out.println(node.getNodeName());
				NodeList nodeList2 = node.getChildNodes();
				for(int i2=0;i2<nodeList2.getLength();i2++) {
					Node node2 = nodeList2.item(i2);
					System.out.println(node2.getTextContent());
				}
		
				
			}

		}
		catch (Exception e) {
			System.out.println("catch");
		}
	}
}
