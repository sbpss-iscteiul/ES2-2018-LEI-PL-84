package extras;


import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Email {
	

	private Session session;
	private MimeMessage message;

	public Email(){
	      // Get system properties
	      Properties props = new Properties();
	        props.put("mail.smtp.starttls.enable", "true");
	        props.put("mail.smtp.auth", "true");
	        props.put("mail.smtp.host", "smtp.gmail.com");
	        props.put("mail.smtp.port", "587");

	      // Get the default Session object.
        	session = Session.getInstance(props,
             new javax.mail.Authenticator() {
               protected PasswordAuthentication getPasswordAuthentication() {
                   return new PasswordAuthentication("ES2.iscte.84.2018@gmail.com","qazwsxedc123456789");
               }
             });
	}
	
	public void createMessage() {
		
        // Create a default MimeMessage object.
        message = new MimeMessage(session);
         
        try {
	        // Set From: header field of the header.
	        message.setFrom(new InternetAddress("ES2.iscte.84.2018@gmail.com"));
	
	
	        // Set Subject: header field
	        message.setSubject("ES2 Primeira Entrega");
	
	        // Now set the actual message
	        message.setText("Esta é a mensagem da primeira entrega");
        }catch (MessagingException mex) {
        	mex.printStackTrace();
        }
	}
	
	public void adddestination(String to) {
		 try {
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
		} catch (AddressException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}

	public void send() {
		try {
			Transport.send(message);
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}



}
