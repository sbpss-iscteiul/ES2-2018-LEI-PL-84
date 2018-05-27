package JunitTestes;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import extras.Parser;
import objects.Restriction;

class ParserTest {

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
		Restriction restriction=new Restriction("varName", "operation", "value");
		x.addLimitations(restriction);
		x.write_XML("problemName", "C:\\Users\\Sergio-PC\\Desktop");
		x.read_XML("C:\\Users\\Sergio-PC\\Desktop\\_problemName 2018-05-27 04-13-41");
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
