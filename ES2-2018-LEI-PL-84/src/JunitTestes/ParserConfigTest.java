package JunitTestes;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;

import javax.xml.soap.Node;

import org.junit.jupiter.api.Test;

import extras.ParserConfig;

class ParserConfigTest {

	@Test
	public void contructerTest() {
		ParserConfig pConfig = new ParserConfig();
		assertNotNull(pConfig.getDoc());
		assertNotNull(pConfig.getRootElement());
		assertEquals("config", pConfig.getRootElement().getNodeName());
	}
	
	@Test
	public void addContentTest() {
		ParserConfig pConfig = new ParserConfig();
		String[] algs = {"NSGAII"};
		pConfig.add_Content("Admin", "admin@gmail.pt", algs);
		assertEquals("admin@gmail.ptAdmin", pConfig.getRootElement().getFirstChild().getTextContent());
		assertEquals("NSGAII", pConfig.getRootElement().getLastChild().getTextContent());
	}
	
	@Test
	public void writeXMLTest() {
		ParserConfig pConfig = new ParserConfig();
		pConfig.write_ConfigXML();
		File fXML = new File("C:\\Users\\Sergio-PC\\Desktop\\config.xml");
		assertNotNull(fXML);
	}
	
	@Test
	public void readXMLTest() {
		ParserConfig pConfig = new ParserConfig();
		pConfig.read_ConfigXML();
	}

}
