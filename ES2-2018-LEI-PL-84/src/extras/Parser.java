package extras;
import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

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


public class Parser {
	
	Document doc;
	Element rootElement;
	Element variables;
	Element paths;
	int variaveis;
	
	public Parser() {
		try {
			//-----File Creation-----//
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder(); 
			doc = docBuilder.newDocument();
			rootElement = doc.createElement("Caracterização");
			doc.appendChild(rootElement);
			//-----Main Node 1 Creation-----//
			variables = doc.createElement("Variables");
			rootElement.appendChild(variables);
			//-----Main Node 2 Creation-----//
			paths = doc.createElement("Paths");
			rootElement.appendChild(paths);
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	//ALTERAR INFOPESSOAL E INFOPROBLEMA PARA ROOTELEMENT
	
	//-----Adds Pessoais-----//
	
	public void addEmail(String e_mail) {
		Element email = doc.createElement("Email");
		email.appendChild(doc.createTextNode(e_mail));
		rootElement.appendChild(email);
	}
	
	public void addDescription(String Description) {
		Element description = doc.createElement("Descrição");
		description.appendChild(doc.createTextNode(Description));
		rootElement.appendChild(description);
	}
	
	//-----Adds Problema-----//
	
	public void addWaitingTime(String time) {
		Element waitingTime = doc.createElement("Tempo_de_Espera");
		waitingTime.appendChild(doc.createTextNode(time));
		rootElement.appendChild(waitingTime);
	}
	
	public void addLimitations(String Limitation) {
		Element limitations = doc.createElement("Limitações");
		limitations.appendChild(doc.createTextNode(Limitation));
		rootElement.appendChild(limitations);
	}
	
	public void addVariables(int Id, String Name, String Type, String LimInf, String LimSup) {
		Element variable = doc.createElement("Variavel");
		variable.setAttribute("id", ""+Id);
		variables.appendChild(variable);
			
			Element name = doc.createElement("Name");
			name.appendChild(doc.createTextNode(Name));
			variable.appendChild(name);
			
			Element type = doc.createElement("Type");
			type.appendChild(doc.createTextNode(Type));
			variable.appendChild(type);
			
			Element limInf = doc.createElement("Limite_Inferior");
			limInf.appendChild(doc.createTextNode(""+LimInf));
			variable.appendChild(limInf);
			
			Element limSup = doc.createElement("Limite_Superior");
			limSup.appendChild(doc.createTextNode(""+LimSup));
			variable.appendChild(limSup);
	}
	
	public void addPaths(int variableID , String Path) {
		Element path = doc.createElement("Path");
		path.setAttribute("id", ""+variableID);
		paths.appendChild(path);
			
			Element name = doc.createElement("Path_Name");
			name.appendChild(doc.createTextNode(Path));
			paths.appendChild(name);
		
	}
	
	public void addChosenAlgorithm(int number, String algorithmName) {
		Element chosenAlgorithm = doc.createElement("Variavel");
		chosenAlgorithm.setAttribute("id", ""+number);
		rootElement.appendChild(chosenAlgorithm);
			
		Element name = doc.createElement("Name");
		name.appendChild(doc.createTextNode(algorithmName));
		chosenAlgorithm.appendChild(name);
	}
	
	//-----------------------//
	
	public void write_XML() {
		try {
			// write the content into xml file
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			//-------//
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
			Date date = new Date();
			String fileName = "Problem_Name "+dateFormat.format(date)+".xml";
			StreamResult result = new StreamResult(new File("C:\\Users\\Sergio-PC\\Desktop\\Test\\"+fileName));
			//-------//
			transformer.transform(source, result);
			System.out.println("File saved!");
		} catch (TransformerException tfe) {
				tfe.printStackTrace();
		}
	}
	
}
