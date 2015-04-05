
import com.sendgrid.*;

//https://github.com/sendgrid/sendgrid-java#usage
//java -cp .;sendgridjar.jar emailExample.java
//java -cp repo/com/github/scottmotte/0.0.1/scottmotte-0.0.1-jar.jar com.github.scottmotte.SendGridJavaExample
//javac -classpath .;sendgrid.jar Emailexample.java
//java -classpath .;sendgrid.jar Emailexample

public class Emailexample {

	String emailTo;





	public Emailexample(String passedEmail) {
		emailTo = passedEmail;
	}


	public void sendEmail() {
		SendGrid sendgrid = new SendGrid("jacks-co", "michaelclayson1");
		SendGrid.Email email = new SendGrid.Email();
		email.addTo(emailTo);
		email.setFrom("your.friendly.tech.support@jacksco.com");
		email.setSubject("YoYoYo");
		email.setText("sent from a class within a class baby!");
		try {
			SendGrid.Response response = sendgrid.send(email);
			System.out.println(response.getMessage());
		} catch (SendGridException e) {
			System.err.println(e);
		}
	}










}