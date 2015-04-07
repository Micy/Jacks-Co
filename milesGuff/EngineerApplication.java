//javac -classpath .;mysqlconnector.jar;sendgrid.jar;miglayout.jar EngineerApplication.java
//java -classpath .;mysqlconnector.jar;sendgrid.jar;miglayout.jar EngineerApplication

//SELECT Forename, Surname FROM Person;
import java.util.*;
import java.util.concurrent.TimeUnit;
import javax.swing.*;
import javax.swing.BorderFactory;
import javax.swing.border.*;
import javax.swing.table.*;
import javax.swing.event.*;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Component;

//MigLayout
import net.miginfocom.layout.Grid;
import net.miginfocom.swing.MigLayout;

///MIG LAYOUT IS BEING USED <<<<>>><<><><><><><><><><>
//http://www.miglayout.com/QuickStart.pdf
//http://www.miglayout.com/cheatsheet.pdf





public class EngineerApplication extends JFrame {


	static String sqlSelectNames = "SELECT Forename, Surname FROM Person;";
	static String sqlSelectEmails = "SELECT Email FROM Person;";
	static String sqlSelectOpenTickets = "SELECT TicketID, IssueID, Priority, TimeEntered FROM Ticket WHERE StateFlag =2;";
	static String sqlSelectQueueTickets = "SELECT TicketID, IssueID, Priority, TimeEntered FROM Ticket WHERE StateFlag =1;";
	static String sqlAllTickets = "SELECT TicketID, IssueID, Priority, TimeEntered FROM Ticket;";
	static Vector<String> ticketTableHeaders = new Vector<String>();


	public static void main(String[] args) {



		EngineerApplication frame = new EngineerApplication();





	}

	EngineerApplication() {
		super("Engineer Application");
		buildGUI();

		setDefaultCloseOperation(EXIT_ON_CLOSE);
		//pack();
		setVisible(true);
		setPreferredSize(new Dimension(1000, 1000));
		setMinimumSize(new Dimension(1000, 1000));
	}


	//Buttons
	private JButton openTicketsButton;
	private JButton queueTicketsButton;
	private JButton refreshButton;




	private void buildGUI() {
		setLayout(new MigLayout());

		add(new LeftSide());


	}

	public class LeftSide extends JPanel {
		public LeftSide() {
			setLayout(new MigLayout("wrap 1"));
			add(new ViewsButtonPanel());
			add(new TablePanel("Table", sqlAllTickets));
		}
	}

	public class TablePanel extends JPanel {

		public TablePanel(String tableTitle, String sqlQuery) {

			MySqlConnector ticketsTable = new MySqlConnector(sqlQuery);
			ticketTableHeaders.addElement("TicketID");
			ticketTableHeaders.addElement("IssueID");
			ticketTableHeaders.addElement("Priority");
			ticketTableHeaders.addElement("TimeEntered");
			//the data needs to be a vector<vector>
			JTable leftTable = new JTable(ticketsTable.returnTicketListVector(), ticketTableHeaders);
			//leftTable.setPreferredSize(new Dimension(590, 400));
			leftTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
			add(new JScrollPane(leftTable));

			leftTable.getColumnModel().getColumn(0).setPreferredWidth(53);
			leftTable.getColumnModel().getColumn(1).setPreferredWidth(53);
			leftTable.getColumnModel().getColumn(2).setPreferredWidth(50);
			leftTable.getColumnModel().getColumn(3).setPreferredWidth(130);






















			setBorder(new TitledBorder(new EtchedBorder(),	tableTitle));
			setPreferredSize(new Dimension(600, 500));

		}

	}

	public class ViewsButtonPanel extends JPanel {
		public ViewsButtonPanel() {
			setLayout(new MigLayout());

			openTicketsButton = new JButton("View Active Tickets");
			queueTicketsButton = new JButton("View Queued Tickets");
			refreshButton = new JButton("Refresh View");
			add(openTicketsButton);
			add(queueTicketsButton);
			add(refreshButton);
		}
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







