package com.main;

import java.util.ArrayList;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendEmail {

	private String host = "smtp.gmail.com";
	private String subject;
	private String message;
	private int choice;
	private final String fromAddress = ""; //TODO create new account for CBD
	
	public SendEmail() {

	}
	
	public SendEmail(int choice) {
		/* 1 for invitation of event.
		 * 2. for cancelling event(todo)
		 * 3. for adding user to group.
		 * 4. for removing user from group.(todo)
		 */
		this.choice = choice;
	}

	private String getSubject(int choice) {
		
		switch(choice){
		case 1: 
			this.subject ="Invitation from EventManager";
			break;
		case 3:
			this.subject = "Added to group";
		}
		return this.subject;
	
	}

	private String getMessage(int choice) {
		
		switch (choice) {
		case 1:
			this.message = "You have been invited for the event.";
			break;
		case 2:
			this.message = "You have been added to the group.";
			break;
		}
		
		return this.message;
	}

	public void sendEmail(ArrayList<String> receiverList){

		String[] receivers = (String[]) receiverList.toArray();
		try{
		
			Properties props = new Properties();
			props.put("mail.smtp.host",host);
			props.put("mail.smtp.user", this.fromAddress);
			
			Session session = Session.getDefaultInstance(props);
			MimeMessage mimeMessage = new MimeMessage(session);
			
			mimeMessage.setFrom(new InternetAddress(this.fromAddress));
			
			InternetAddress[] receiversAddress = new InternetAddress[receivers.length];
			for(int i=0;i<receivers.length;i++){
				receiversAddress[i] = new InternetAddress(receivers[i]);
			}
			
			mimeMessage.setRecipients(Message.RecipientType.TO, receiversAddress);
			mimeMessage.setSubject(this.getSubject(this.choice));
			mimeMessage.setContent(this.getMessage(this.choice),"text/plain");
			
			Transport.send(mimeMessage);

		} catch (MessagingException e) {
			
			System.out.println("email not sent");
			e.printStackTrace();
		}
				
	}
}
