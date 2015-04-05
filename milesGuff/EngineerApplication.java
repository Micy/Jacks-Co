//javac -classpath .;mysqlconnector.jar;sendgrid.jar EngineerApplication.java
//java -classpath .;mysqlconnector.jar;sendgrid.jar EngineerApplication

//SELECT Forename, Surname FROM Person;

public class EngineerApplication {

	public static void main(String[] args) {
		String sqlSelectNames = "SELECT Forename, Surname FROM Person;";
		String sqlSelectEmails = "SELECT Email FROM Person;";

		String user_input = "Miles";

		sqlSelectEmails = "SELECT Email FROM Person WHERE Forename =\'" + user_input + "\';";
		System.out.println(sqlSelectEmails);

		MySqlConnector showAll = new MySqlConnector(sqlSelectEmails);
		String returnedEmail = showAll.returnEmail();
		//System.out.println(returnedEmail);
		Emailexample sendgridEmail = new Emailexample(returnedEmail);
		sendgridEmail.sendEmail();
		


	}
}