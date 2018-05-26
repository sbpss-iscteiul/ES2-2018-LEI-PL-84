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

import objects.Problem;
import objects.Restriction;
import objects.Variable;


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
	private Element name;
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
				//-----Algorithms Node Creation-----//
				 name = doc.createElement("Name");
				 rootElement.appendChild(name);
				 
				 
				 
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
	public void addName(String names) {
		Element nameP = doc.createElement("ProblemName");
		nameP.appendChild(doc.createTextNode(names));
		name.appendChild(nameP);
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
	
	public void addLimitations(Restriction lim) {
		Element Limitations = doc.createElement("Limitacoes");
//		Limitations.appendChild(doc.createTextNode(Limitation));
		limitations.appendChild(Limitations);
			Element varName = doc.createElement("VarName");
			varName.appendChild(doc.createTextNode(lim.getVarName()));
			Limitations.appendChild(varName);
		
			Element operation = doc.createElement("Operation");
			operation.appendChild(doc.createTextNode(lim.getOperation()));
			Limitations.appendChild(operation);
		
			Element value = doc.createElement("OperationValue");
			value.appendChild(doc.createTextNode(lim.getValue()+""));
			Limitations.appendChild(value);
	}
	
	public void addVariables(String Name, String Type, String LimInf, String LimSup, String NrBits) {
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
			
			Element nrBits = doc.createElement("Numero_Bits");
			nrBits.appendChild(doc.createTextNode(""+NrBits));
			variable.appendChild(nrBits);
			
		variable_count++;
	}
	
	public void addPaths(String name , String Path) {
		Element path = doc.createElement("Path");
//		path.setAttribute("id", ""+variableID);
		paths.appendChild(path);
			
			Element Name = doc.createElement("PathName");
			Name.appendChild(doc.createTextNode(name));
			path.appendChild(Name);
			
			Element pat = doc.createElement("PathPath");
			pat.appendChild(doc.createTextNode(Path));
			path.appendChild(pat);
		
	}
	
	public void addChosenAlgorithm(int number, String algorithmName) {
			Element chosenAlgorithm = doc.createElement("Algorithm");
			chosenAlgorithm.setAttribute("id", ""+number);
			algorithms.appendChild(chosenAlgorithm);
				
			Element name = doc.createElement("Name");
			name.appendChild(doc.createTextNode(algorithmName));
			chosenAlgorithm.appendChild(name);
			
			chosen_algorithm = true;
//		}else {
//			Node tmp = rootElement.getChildNodes().item(5).getChildNodes().item(0);
//			System.out.println("Algoritmo Selecionado Alterado de "+tmp.getTextContent()+" para "+algorithmName);
//			tmp.setTextContent(algorithmName);
//		}
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
			StreamResult result = new StreamResult(new File(dir+"_"+fileName));
			//-------//
			transformer.transform(source, result);
			System.out.println("File saved! "+dateFormat.format(date));
		} catch (TransformerException tfe) {
				tfe.printStackTrace();
		}
	}
	public static Problem read_XML(String dir) {
		Problem problem = new Problem();
		try {
			File fXML = new File(dir);
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXML);
			NodeList nodeList = doc.getDocumentElement().getChildNodes();
			for(int i=0;i<nodeList.getLength();i++) {
				Node node = nodeList.item(i); 
				NodeList nodeList2 = node.getChildNodes();
				for(int i2=0;i2<nodeList2.getLength();i2++) {
					Node node2 = nodeList2.item(i2);
					if(node2.getNodeName().equals("Email")) {
						problem.setEmail(node2.getTextContent());
					}
					else if(node2.getNodeName().equals("Descricao")) {
						problem.setDescription(node2.getTextContent());
					}
					else if(node2.getNodeName().equals("Variavel")) {
						NodeList nList =node2.getChildNodes();
						Variable variable = new Variable(null, null, null, null, 0);
						for(int i3=0;i3<nList.getLength();i3++) {
							if(nList.item(i3).getNodeName().equals("Name")){
								variable.setName(nList.item(i3).getTextContent());
							}
							else if(nList.item(i3).getNodeName().equals("Type")){
								variable.setType(nList.item(i3).getTextContent());
							}
							else if(nList.item(i3).getNodeName().equals("Limite_Superior")){
								variable.setMaxValue(nList.item(i3).getTextContent());
							}
							else if(nList.item(i3).getNodeName().equals("Limite_Inferior")){ 
								variable.setMinValue(nList.item(i3).getTextContent());
							}
							else if(nList.item(i3).getNodeName().equals("Numero_Bits")){ 
								variable.setNumberBits(Integer.parseInt(nList.item(i3).getTextContent()));
							}
						}						
						problem.getVars().add(variable);
					}
					else if(node2.getNodeName().equals("Path")) {
						String[] p = new String[2];
						NodeList nList =node2.getChildNodes();
						for(int i3=0;i3<nList.getLength();i3++) {
							if(nList.item(i3).getNodeName().equals("PathName")){
								p[0]=nList.item(i3).getTextContent();
							}
							else if(nList.item(i3).getNodeName().equals("PathPath")){
								p[1]=nList.item(i3).getTextContent();
							}
						}
						problem.getPaths().add(p);
						
					}
					else if(node2.getNodeName().equals("Tempo_de_Espera")) {
						problem.setTempoDeEspera(node2.getTextContent());
					}
					else if(node2.getNodeName().equals("Algorithm")) {
						NodeList nList =node2.getChildNodes();
						for(int i3=0;i3<nList.getLength();i3++) {
							if(nList.item(i3).getNodeName().equals("Name")){
								problem.getAlgorithms().add(nList.item(i3).getTextContent());
							}
						}
					}
					else if(node2.getNodeName().equals("ProblemName")) {
						problem.setProblemName(node2.getTextContent());
					}
					else if(node2.getNodeName().equals("Limitacoes")) {
						NodeList nList =node2.getChildNodes();
						Restriction a = new Restriction(null, null, null);
						for(int i3=0;i3<nList.getLength();i3++) {
							if(nList.item(i3).getNodeName().equals("Operation")){
								a.setOperation(nList.item(i3).getTextContent());
							}
							else if(nList.item(i3).getNodeName().equals("VarName")){
								a.setVarName(nList.item(i3).getTextContent());
							}
							else if(nList.item(i3).getNodeName().equals("OperationValue")){
								a.setValue(nList.item(i3).getTextContent());
							}
						}
						problem.getRestrictions().add(a);
					}
				}
			}
					
		}
		catch(Exception e) {
			
		}
		
			
		return problem;
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

	public Element getVariables() {
		return variables;
	}

	public Element getPaths() {
		return paths;
	}


	public Element getEmails() {
		return emails;
	}

	public Element getDescriptions() {
		return descriptions;
	}

	public Element getAlgorithms() {
		return algorithms;
	}

	public Element getWaiting_times() {
		return waiting_times;
	}

	public Element getName() {
		return name;
	}

}
