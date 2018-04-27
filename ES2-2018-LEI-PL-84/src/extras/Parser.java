package extras;
import java.io.File;
import java.lang.reflect.Array;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


public class Parser {
	
	private Document doc;
	//Elements (Strutcture Nodes)
	private Element rootElement;
	private Element variables;
	private Element paths;
	private Element limitations;
	private Element	emails;
	private Element descriptions;
	private Element algorithms;
	private Element waiting_times;
	//counter de variaveis
	private int variable_count;
	//unique email, unique description
	private boolean e_Mail = false;
	private boolean problem_description = false;
	private boolean wating_time = false;
	private boolean chosen_algorithm = false;
	
	public Parser() {
		try {
			//-----File Creation-----//
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder(); 
			doc = docBuilder.newDocument();
			rootElement = doc.createElement("Caracterizacao");
			doc.appendChild(rootElement);
			//-----Variables Node Creation-----//
				variables = doc.createElement("Variables");
				rootElement.appendChild(variables);
			//-----Paths Node Creation-----//
				paths = doc.createElement("Paths");
				rootElement.appendChild(paths);
			//-----Limitations Node Creation-----//
				limitations = doc.createElement("Limitations");
				rootElement.appendChild(limitations);
			//-----Emails Node Creation-----//
				 emails = doc.createElement("Emails");
				 rootElement.appendChild(emails);
			//-----Descriptions Node Creation-----//
				 descriptions = doc.createElement("Descriptions");
				 rootElement.appendChild(descriptions);
			//-----Algorithms Node Creation-----//
				 algorithms = doc.createElement("Algorithms");
				 rootElement.appendChild(algorithms);
			//-----Algorithms Node Creation-----//
				 waiting_times = doc.createElement("Waiting_Times");
				 rootElement.appendChild(waiting_times);
				 
			variable_count=0;
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void addEmail(String e_mail) {
		if(e_Mail== false) {
			Element email = doc.createElement("Email");
			email.appendChild(doc.createTextNode(e_mail));
			emails.appendChild(email);
			e_Mail = true;
		}else {
			Node tmp = rootElement.getChildNodes().item(3).getChildNodes().item(0);
			System.out.println("Email Alterado de '"+tmp.getTextContent()+"' para '"+e_mail+"'");
			tmp.setTextContent(e_mail);
		}
	}
	
	public void addDescription(String Description) {
		if(problem_description== false) {
			Element description = doc.createElement("Descricao");
			description.appendChild(doc.createTextNode(Description));
			descriptions.appendChild(description);
			problem_description = true;
		}else {
			Node tmp = rootElement.getChildNodes().item(4).getChildNodes().item(0);
			System.out.println("Description Alterado de '"+tmp.getTextContent()+"' para '"+Description+"'");
			tmp.setTextContent(Description);
		}
	}

	public void addWaitingTime(String time) {
		if(wating_time==false) {
			Element waitingTime = doc.createElement("Tempo_de_Espera");
			waitingTime.appendChild(doc.createTextNode(time));
			waiting_times.appendChild(waitingTime);
			wating_time = true;
		}else {
			Node tmp = rootElement.getChildNodes().item(6).getChildNodes().item(0);
			System.out.println("Tempo de Espera Alterado de "+tmp.getTextContent()+" para "+time);
			tmp.setTextContent(time);		
		}
	}
	
	public void addLimitations(String Limitation) {
		Element Limitations = doc.createElement("Limitacoes");
		Limitations.appendChild(doc.createTextNode(Limitation));
		limitations.appendChild(Limitations);
	}
	
	public void addVariables(String Name, String Type, String LimInf, String LimSup) {
		Element variable = doc.createElement("Variavel");
		variable.setAttribute("id", ""+variable_count);
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
			
		variable_count++;
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
		if(chosen_algorithm==false) {
			Element chosenAlgorithm = doc.createElement("Algorithm");
			chosenAlgorithm.setAttribute("id", ""+number);
			algorithms.appendChild(chosenAlgorithm);
				
			Element name = doc.createElement("Name");
			name.appendChild(doc.createTextNode(algorithmName));
			chosenAlgorithm.appendChild(name);
			
			chosen_algorithm = true;
		}else {
			Node tmp = rootElement.getChildNodes().item(5).getChildNodes().item(0);
			System.out.println("Algoritmo Selecionado Alterado de "+tmp.getTextContent()+" para "+algorithmName);
			tmp.setTextContent(algorithmName);
		}
	}
	
	
	public void write_XML(String problemName, String dir) {
		System.out.println(dir);
		try {
			// write the content into xml file
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			//-------//
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
			Date date = new Date();
			String fileName = problemName+" "+dateFormat.format(date)+".xml";
			StreamResult result = new StreamResult(new File(dir+fileName));
			//-------//
			transformer.transform(source, result);
			System.out.println("File saved! "+dateFormat.format(date));
		} catch (TransformerException tfe) {
				tfe.printStackTrace();
		}
	}
	public void read_XML(String dir) {
		String email="";
		ArrayList<String[]> variaveis= new ArrayList<String[]>();
		String descricao="";
		try {
			File fXML = new File(dir);
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
					if(node2.getNodeName().equals("Email")) {
						email=node2.getTextContent();
					}
					else if(node2.getNodeName().equals("Descricao")) {
						descricao=node2.getTextContent();
					}
					else if(node2.getNodeName().equals("Variavel")) {
						NodeList nList =node2.getChildNodes();
						String[] variable= new String[4];
						for(int i3=0;i3<nList.getLength();i3++) {
							if(nList.item(i3).getNodeName().equals("Name")){
								variable[0]=nList.item(i3).getTextContent();
							}
							else if(nList.item(i3).getNodeName().equals("Type")){
								variable[1]=nList.item(i3).getTextContent();
							}
							else if(nList.item(i3).getNodeName().equals("Limite_Superior")){
								variable[2]=nList.item(i3).getTextContent();
							}
							else if(nList.item(i3).getNodeName().equals("Limite_Inferior")){
								variable[3]=nList.item(i3).getTextContent();
							}
						}
						variaveis.add(variable);
					}
				}
			}
					
		}
		catch(Exception e) {
			
		}
		
			
		
	}
	
	/*Notes
	 * 
	
	//Notes
	/*
	 * Alterar Path dentro do metodo write_XML, para pasta onde vao ser depositados os ficheiros XML
	 * Criar JUnit Tests
	 * 
	 * Adicionar atributo nome ao elemento Path
	 * 
	 * */
	
}
