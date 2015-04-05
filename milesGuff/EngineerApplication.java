//javac -classpath .;mysqlconnector.jar;sendgrid.jar EngineerApplication.java
//java -classpath .;mysqlconnector.jar;sendgrid.jar EngineerApplication

//SELECT Forename, Surname FROM Person;
import java.util.*;

public class EngineerApplication {



	static String sqlSelectNames = "SELECT Forename, Surname FROM Person;";
	static String sqlSelectEmails = "SELECT Email FROM Person;";
	static String sqlSelectOpenTickets = "SELECT TicketID, IssueID, Priority, TImeEntered FROM Ticket WHERE StateFlag =2;";
	static String sqlSelectQueueTickets = "SELECT TicketID, IssueID, Priority, TImeEntered FROM Ticket WHERE StateFlag =1;";



	public static void main(String[] args) {

		//SQL Statements

		dbConnectOpenTickets();
		//dbConnectAcceptTicket(7);
		













	}


	public static void dbConnectOpenTickets() {
		MySqlConnector showOpenTickets = new MySqlConnector(sqlSelectOpenTickets);

		List<Ticket> openTickets = showOpenTickets.returnTicketList();
		for (int i = 0; i < openTickets.size(); i++) {
			System.out.println("Ticket ID: " + openTickets.get(i).getTicketID());
			System.out.println("Issue ID: " + openTickets.get(i).getIssueID());
			System.out.println("Priority: " + openTickets.get(i).getPriority());

			timeSinceMethod(openTickets.get(i).getTimeEntered());



			System.out.print("\n");

		}
	}

	public static void dbConnectQueueTickets() {
		MySqlConnector showQueueTickets = new MySqlConnector(sqlSelectQueueTickets);

		List<Ticket> queueTickets = showQueueTickets.returnTicketList();
		for (int i = 0; i < queueTickets.size(); i++) {
			System.out.println("Ticket ID: " + queueTickets.get(i).getTicketID());
			System.out.println("Issue ID: " + queueTickets.get(i).getIssueID());
			System.out.println("Priority: " + queueTickets.get(i).getPriority());

			timeSinceMethod(queueTickets.get(i).getTimeEntered());
			System.out.print("\n");

		}
	}


	public static void dbConnectSendEmail() {
		String user_input = "Miles";
		sqlSelectEmails = "SELECT Email FROM Person WHERE Forename =\'" + user_input + "\';";
		//System.out.println(sqlSelectEmails);

		//Being able to send an email works here
		MySqlConnector showAll = new MySqlConnector(sqlSelectEmails);
		String returnedEmail = showAll.returnEmail();
		Emailexample sendgridEmail = new Emailexample(returnedEmail);
		sendgridEmail.sendEmail();
	}







	public static void dbConnectAcceptTicket(int tID) {

		String sqlUpdateStateFlag = "UPDATE Ticket SET StateFlag=3 WHERE TicketID=\'" + tID + "\';";


		//Being able to send an email works here
		MySqlConnector setStateFlagAccept = new MySqlConnector(sqlUpdateStateFlag);
		setStateFlagAccept.acceptTicket();
		System.out.println(tID + " changed");

	}







	public static void timeSinceMethod(long tmEntrd) {

		long currentTime = System.currentTimeMillis();
		long diffInSeconds = (currentTime - tmEntrd) / 1000;

		long diff[] = new long[] { 0, 0, 0, 0 };
		/* sec */diff[3] = (diffInSeconds >= 60 ? diffInSeconds % 60 : diffInSeconds);
		/* min */diff[2] = (diffInSeconds = (diffInSeconds / 60)) >= 60 ? diffInSeconds % 60 : diffInSeconds;
		/* hours */diff[1] = (diffInSeconds = (diffInSeconds / 60)) >= 24 ? diffInSeconds % 24 : diffInSeconds;
		/* days */diff[0] = (diffInSeconds = (diffInSeconds / 24));

		System.out.println(String.format(
		                       "%d day%s, %d hour%s, %d minute%s, %d second%s ago",
		                       diff[0],
		                       diff[0] > 1 ? "s" : "",
		                       diff[1],
		                       diff[1] > 1 ? "s" : "",
		                       diff[2],
		                       diff[2] > 1 ? "s" : "",
		                       diff[3],
		                       diff[3] > 1 ? "s" : ""));
	}




}







