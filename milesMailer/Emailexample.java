
import com.sendgrid.*;

//https://github.com/sendgrid/sendgrid-java#usage
//java -cp .;sendgridjar.jar emailExample.java
//java -cp repo/com/github/scottmotte/0.0.1/scottmotte-0.0.1-jar.jar com.github.scottmotte.SendGridJavaExample
//javac -classpath .;sendgrid.jar Emailexample.java
//java -classpath .;sendgrid.jar Emailexample

public class Emailexample {

	public static void main(String[] args) {

		SendGrid sendgrid = new SendGrid("jacks-co", "michaelclayson1");

		SendGrid.Email email = new SendGrid.Email();
		email.addTo("miles.e.hopkins@gmail.com");
		email.setFrom("other@example.com");
		email.setSubject("Hello World");
		email.setText("My first email with SendGrid Java!");

		try {
			SendGrid.Response response = sendgrid.send(email);
			System.out.println(response.getMessage());
		}
		catch (SendGridException e) {
			System.err.println(e);
		}





	}




}