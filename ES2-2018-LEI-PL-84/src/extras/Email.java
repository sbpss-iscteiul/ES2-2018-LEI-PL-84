package extras;


import java.io.File;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;

public class Email {
	

	private Session session;
	private MimeMessage message;
	private Multipart multipart;

	public MimeMessage getMessage() {
		return message;
	}

	public Multipart getMultipart() {
		return multipart;
	}

	public Email(){
	      // Get system properties
	      Properties props = new Properties();
	        props.put("mail.smtp.starttls.enable", "true");
	        props.put("mail.smtp.auth", "true");
	        props.put("mail.smtp.host", "smtp.gmail.com");
	        props.put("mail.smtp.port", "587");
	        multipart= new MimeMultipart();
	      // Get the default Session object.
        	session = Session.getInstance(props,
             new javax.mail.Authenticator() {
               protected PasswordAuthentication getPasswordAuthentication() {
                   return new PasswordAuthentication("ES2.iscte.84.2018@gmail.com","qazwsxedc123456789");
               }
             });
	}
	
	public void createMessage(String Titulo, String mensagem) {
		
        // Create a default MimeMessage object.
        message = new MimeMessage(session);
        MimeBodyPart messagePart = new MimeBodyPart(); 
        
        try {
	        // Set From: header field of the header.
	        message.setFrom(new InternetAddress("ES2.iscte.84.2018@gmail.com"));
	        // Set Subject: header field assunto
	        message.setSubject(Titulo);
	        // Now set the actual message
	        messagePart.setText(mensagem);
	        multipart.addBodyPart(messagePart);
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
	
	public void CC(String to) {
		 try {
			message.addRecipient(Message.RecipientType.CC, new InternetAddress(to));
		} catch (AddressException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}

	public void anexo(String file) {
		MimeBodyPart attachPart = new MimeBodyPart(); 
		FileDataSource data= new FileDataSource(file);
		try {
			attachPart.setDataHandler(new DataHandler(data));
			attachPart.setFileName(data.getName());
			multipart.addBodyPart(attachPart);
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}
		
	public void send() {
		try {
			message.setContent(multipart);
			Transport.send(message);
			System.out.println("Sent message successfully....");
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}

	


}
