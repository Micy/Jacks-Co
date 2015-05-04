
import com.sendgrid.*;
import java.util.*;

//https://github.com/sendgrid/sendgrid-java#usage
//java -cp .;sendgridjar.jar emailExample.java
//java -cp repo/com/github/scottmotte/0.0.1/scottmotte-0.0.1-jar.jar com.github.scottmotte.SendGridJavaExample
//javac -classpath .;sendgrid.jar Emailexample.java
//java -classpath .;sendgrid.jar Emailexample

public class Emailer {

	private final static String FROMADDRESS = "Support@jacks-co.me"; 

	public static boolean ticketAccepted(Ticket ticket, User user){
		SendGrid sendgrid = new SendGrid("jacks-co", "michaelclayson1");
		SendGrid.Email email = new SendGrid.Email();
		
		Engineer engineer = mySqlConnector.getEngineer(EngineerApplication.getCurrentEngineerID());
		
		email.addTo(user.getEmail());
		email.setFrom(FROMADDRESS);
		email.setSubject("Ticket - "+ticket.getTicketID()+" has been accepted");
		email.setText(	"Dear "+user.getTitle()+" "+user.getForename()+" "+user.getSurname()+",\n\n"+
						"Your Ticket - "+ticket.getTicketID()+" has been accepted by a Support Engineer.\n"+
						"Your allocated Engineer is "+engineer.getForename()+" "+engineer.getSurname()+"\n"+
						"Email - "+engineer.getEmail()+"\n"+
						"Telephone - "+engineer.getPhoneNumber()+"\n"+
						"Your Ticket will be completed as soon as possible\n\n"+
						"Thank you for using Jacks-Co Support!"
			);
		try {
			SendGrid.Response response = sendgrid.send(email);
			System.out.println(response.getMessage());
			return true;
		} catch (SendGridException e) {
			System.err.println(e);
			return false;
		}		


	}
	
		public static boolean ticketCompleted(Ticket ticket, User user){
		SendGrid sendgrid = new SendGrid("jacks-co", "michaelclayson1");
		SendGrid.Email email = new SendGrid.Email();
		
		Engineer engineer = mySqlConnector.getEngineer(EngineerApplication.getCurrentEngineerID());
		
		email.addTo(user.getEmail());
		email.setFrom(FROMADDRESS);
		email.setSubject("Ticket - "+ticket.getTicketID()+" has been completed!");
		email.setText(	"Dear "+user.getTitle()+" "+user.getForename()+" "+user.getSurname()+",\n\n"+
						"Your Ticket - "+ticket.getTicketID()+" has been marked as completed.\n"+
						"Your allocated Engineer was "+engineer.getForename()+" "+engineer.getSurname()+"\n"+
						"Email - "+engineer.getEmail()+"\n"+
						"Telephone - "+engineer.getPhoneNumber()+"\n"+
						"If your issue has not been resolved do not hesitate to contact the above Support Engineer for further assistance\n\n"+
						"Thank you for using Jacks-Co Support!"
			);
		try {
			SendGrid.Response response = sendgrid.send(email);
			System.out.println(response.getMessage());
			return true;
		} catch (SendGridException e) {
			System.err.println(e);
			return false;
		}		


	}










}