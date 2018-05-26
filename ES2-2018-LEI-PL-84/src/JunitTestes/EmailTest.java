package JunitTestes;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.util.Calendar;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import extras.Email;

class EmailTest {

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
	void test() throws MessagingException, IOException {
		Email email= new Email();
		email.createMessage("x", "p");
		email.adddestination("raoma@iscte.pt");
		email.CC("raoma@iscte.pt");
		email.anexo("C:\\Users\\Ruben\\Desktop\\JunitTest.xml");
		assertEquals("x", email.getMessage().getSubject());
		assertEquals("raoma@iscte.pt" , email.getMessage().getRecipients(Message.RecipientType.CC)[0].toString());
		assertEquals("raoma@iscte.pt" , email.getMessage().getRecipients(Message.RecipientType.TO)[0].toString());
		assertEquals("p", email.getMultipart().getBodyPart(0).getContent());
		assertEquals("JunitTest.xml", email.getMultipart().getBodyPart(1).getFileName());
		email.send();
		
		Email email1= new Email();
		email1.adddestination("");
		email1.CC("");
		email1.anexo("ola");



		}
		
	}


