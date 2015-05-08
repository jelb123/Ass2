package com.enterprise.mail;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.Message.RecipientType;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendMail {

	private String from;
	private String to;
	private String subject;
	private String text;

	public SendMail(String to, String subject, String text){
		this.from = "comp9321.19@gmail.com";
		this.to = to;
		this.subject = subject;
		this.text = text;
	}

	public void send(){

	 // Assuming you are sending email from localhost
     // String host = "localhost";

      // Get system properties
      Properties properties = System.getProperties();

		properties.put("mail.smtp.host", "smtp.gmail.com");
		properties.put("mail.smtp.port", "587");
		properties.put("mail.smtp.timeout", 20000);
		properties.put("mail.smtp.starttls.enable", "true");
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.user", "comp9321.19@gmail.com");
		properties.put("mail.smtp.password", "comp9321.");
		
      // Setup mail server
     // properties.setProperty("mail.smtp.host", host);
		
      // Get the default Session object.
      Session session = Session.getDefaultInstance(properties, new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("comp9321.19@gmail.com","comp9321.");
			}
		  });

      try{
         // Create a default MimeMessage object.
         MimeMessage message = new MimeMessage(session);

         // Set From: header field of the header.
         message.setFrom(new InternetAddress(from));

         // Set To: header field of the header.
         message.addRecipient(Message.RecipientType.TO,
                                  new InternetAddress(to));

         // Set Subject: header field
         message.setSubject(subject);

         // Now set the actual message
         message.setText(text);

         // Send message
         Transport.send(message);
         System.out.println("Sent message successfully....");
      }catch (MessagingException mex) {
         mex.printStackTrace();
      }
//		Properties props = new Properties();
//		props.put("mail.smtp.host", "smtp.gmail.com");
//		props.put("mail.smtp.port", "465");
//		props.put("mail.smtp.timeout", 1000);
//		
//		Session mailSession = Session.getInstance(props);
//		Message simpleMessage = new MimeMessage(mailSession);
//
//		InternetAddress fromAddress = null;
//		InternetAddress toAddress = null;
//		try {
//			fromAddress = new InternetAddress(from);
//			toAddress = new InternetAddress(to);
//		} catch (AddressException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//		try {
//			simpleMessage.setFrom(fromAddress);
//			simpleMessage.setRecipient(RecipientType.TO, toAddress);
//			simpleMessage.setSubject(subject);
//			simpleMessage.setText(text);
//
//			Transport.send(simpleMessage);
//		
//		} catch (MessagingException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}
}