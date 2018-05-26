package JunitTestes;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import extras.Parser;

class ParserTest {

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void test() {
		Parser x=new Parser();
		Parser y=new Parser();
		x.addEmail("email");
		y.addEmail("email1");
		y.addEmail("email2");
		x.addName("a");
		x.addDescription("Descriçao");
		y.addDescription("Descriçao1");
		y.addDescription("Descriçao2");
		x.addWaitingTime("tempo");
		y.addWaitingTime("tempo1");
		y.addWaitingTime("tempo2");
		x.addVariables("X", "S", "1", "5", "3");
		x.addPaths("Nome", "Path");
		x.addChosenAlgorithm(2, "algorithmName");
		//x.addLimitations(q);
		x.write_XML("problemName", "dir");
		x.read_XML("C:\\Users\\Ruben\\git\\ES2-2018-LEI-PL-84\\ES2-2018-LEI-PL-84\\dir_problemName 2018-05-26 18-48-46.xml");
		y.read_XML("dir");
		assertEquals("email", x.getEmails().getFirstChild().getTextContent());
		assertEquals("a",x.getName().getFirstChild().getTextContent());
		assertEquals("Descriçao",x.getDescriptions().getFirstChild().getTextContent());
		assertEquals("tempo",x.getWaiting_times().getFirstChild().getTextContent());
		assertEquals("XS153", x.getVariables().getFirstChild().getTextContent());
		assertEquals("NomePath", x.getPaths().getFirstChild().getTextContent());
		assertEquals("algorithmName", x.getAlgorithms().getFirstChild().getTextContent());
		
	}

}
