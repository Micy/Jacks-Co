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
import java.awt.Font;
import java.awt.Color;
import java.awt.event.*;

//MigLayout
import net.miginfocom.layout.Grid;
import net.miginfocom.swing.MigLayout;

///MIG LAYOUT IS BEING USED <<<<>>><<><><><><><><><><>
//http://www.miglayout.com/QuickStart.pdf
//http://www.miglayout.com/cheatsheet.pdf



//
///
//  TO DO

//  RETURN A TICKET FROM MYSQL CONNECTOR USING THE BASTARD QUERY
///	PARSE THAT TICKET INTO ALL THE TEXT FIELDS
////



public class EngineerApplication extends JFrame {


	static String sqlSelectNames = "SELECT Forename, Surname FROM Person;";
	static String sqlSelectEmails = "SELECT Email FROM Person;";
	static String sqlSelectOpenTickets = "SELECT TicketID, IssueID, Priority, TimeEntered FROM Ticket WHERE StateFlag =2;";
	static String sqlSelectQueueTickets = "SELECT TicketID, IssueID, Priority, TimeEntered FROM Ticket WHERE StateFlag =1;";
	static String sqlAllTickets = "SELECT TicketID, IssueID, Priority, TimeEntered FROM Ticket;";
	static String sqlSelectFromTicketID = "SELECT Ticket.TicketID,  FROM ";
	static String sqlAllTicketsJoinIssueType = "SELECT Ticket.TicketID, IssueType.IssueName, Ticket.Priority, Ticket.TimeEntered FROM Ticket INNER JOIN IssueType ON Ticket.IssueID=IssueType.IssueID;";
	static String sqlCreateUserInfo = "SELECT Ticket.TicketID, Ticket.NoOfReferrals, Ticket.Priority, Ticket.Screenshot, Ticket.ProblemDescription, Ticket.TimeEntered, Person.Title, Person.Forename, Person.Surname, IssueType.IssueName, Person.Email, Person.PhoneNumber FROM `User` INNER JOIN Ticket ON Ticket.UserID = `User`.UserID INNER JOIN Person ON `User`.PersonID = Person.PersonID INNER JOIN IssueType ON Ticket.IssueID = IssueType.IssueID;";
	static Vector<String> ticketTableHeaders = new Vector<String>();
	static int selectedTicketID = 0;


	public static void main(String[] args) {



		EngineerApplication frame = new EngineerApplication();





	}

	EngineerApplication() {
		super("Engineer Application");
		buildGUI();

		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(800, 570);
		//pack();
		setVisible(true);


	}


	//Buttons
	private JButton openTicketsButton;
	private JButton queueTicketsButton;
	private JButton refreshButton;
	private JButton completeButton;
	private JButton referButton;

	//Text areas
	private JTextArea ticketDetailsTitle;
	private JTextArea timeSubmittedTitle;
	private JTextArea timeSubmittedContent;
	private JTextArea referralsTitle;
	private JTextArea referralsContent;
	private JTextArea priorityTitle;
	private JTextArea priorityContent;
	private JTextArea ticketIDTitle;
	private JTextArea ticketIDContent;
	private JTextArea userIDTitle;
	private JTextArea userIDContent;
	private JTextArea issueTypeTitle;
	private JTextArea issueTypeContent;
	private JTextArea detailsTitle;
	private JTextArea detailsContent;

	//Fonts
	Font headerFont = new Font("SansSerif", Font.PLAIN, 25);
	Font titleFont = new Font("SansSerif", Font.PLAIN, 15);

	//Colours
	Color defaultGrey = new Color(238, 238, 238);


	private void buildGUI() {
		setLayout(new MigLayout());

		add(new LeftSide());
		add(new RightSide());


	}

	public class LeftSide extends JPanel {
		public LeftSide() {
			setLayout(new MigLayout("wrap 1"));
			add(new ViewsButtonPanel(), "align center");
			add(new TablePanel("Table", sqlAllTicketsJoinIssueType));
		}
	}


	public class TablePanel extends JPanel {

		public TablePanel(String tableTitle, String sqlQuery) {

			MySqlConnector ticketsTable = new MySqlConnector(sqlQuery);
			ticketTableHeaders.addElement("TicketID");
			ticketTableHeaders.addElement("Issue Type");
			ticketTableHeaders.addElement("Priority");
			ticketTableHeaders.addElement("Time In System");
			//the data needs to be a vector<vector>
			final JTable leftTable = new JTable(ticketsTable.returnTicketListVector(), ticketTableHeaders);

			add(new JScrollPane(leftTable));

			leftTable.getColumnModel().getColumn(0).setPreferredWidth(53);
			leftTable.getColumnModel().getColumn(1).setPreferredWidth(130);
			leftTable.getColumnModel().getColumn(2).setPreferredWidth(50);
			leftTable.getColumnModel().getColumn(3).setPreferredWidth(130);

			setBorder(new TitledBorder(new EtchedBorder(),	tableTitle));
			setPreferredSize(new Dimension(470, 500));



			MouseListener tableMouseListener = new MouseAdapter() {

				@Override
				public void mouseClicked(MouseEvent e) {

					int row = leftTable.rowAtPoint(e.getPoint());//get mouse-selected row
					int col = leftTable.columnAtPoint(e.getPoint());//get mouse-selected col
					int[] newEntry = new int[] {row, col}; //{row,col}=selected cell
					System.out.println(leftTable.getValueAt(row,0));
					//System.out.println(row);
					

				}
			};

			leftTable.addMouseListener(tableMouseListener);
			leftTable.getTableHeader().setReorderingAllowed(false);






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

	public class RightSide extends JPanel {
		public RightSide() {
			setLayout(new MigLayout("wrap 1"));


			ticketDetailsTitle = new JTextArea("Ticket Details");
			ticketDetailsTitle.setFont(headerFont);
			ticketDetailsTitle.setEditable(false);
			ticketDetailsTitle.setBackground(defaultGrey);
			add(ticketDetailsTitle);

			add(new TicketDetailsPanel());
			setPreferredSize(new Dimension(263, 500));

		}
	}

	public class TicketDetailsPanel extends JPanel {
		public TicketDetailsPanel() {
			setLayout(new MigLayout("wrap 2", "[]20[]", "[]2[]" ));


			// ticketDetailsTitle = new JTextArea("Ticket Details");
			// ticketDetailsTitle.setFont(headerFont);

			timeSubmittedTitle = new JTextArea("Time Submitted:");
			timeSubmittedTitle.setBackground(defaultGrey);
			timeSubmittedContent = new JTextArea("blurgh");
			referralsTitle = new JTextArea("Referrals:");
			referralsTitle.setBackground(defaultGrey);
			referralsContent = new JTextArea("blargh");
			priorityTitle = new JTextArea("Ticket Priority: ");
			priorityTitle.setBackground(defaultGrey);
			priorityContent = new JTextArea("blloght");
			timeSubmittedTitle.setFont(titleFont);
			timeSubmittedContent.setFont(titleFont);
			referralsTitle.setFont(titleFont);
			referralsContent.setFont(titleFont);
			priorityTitle.setFont(titleFont);
			priorityContent.setFont(titleFont);

			// ticketDetailsTitle.setEditable(false);
			//ticketDetailsTitle.setEditable(false);
			timeSubmittedTitle.setEditable(false);
			timeSubmittedContent.setEditable(false);
			referralsTitle.setEditable(false);
			referralsContent.setEditable(false);
			priorityTitle.setEditable(false);
			priorityContent.setEditable(false);




			// add(ticketDetailsTitle, "span 2");
			add(timeSubmittedTitle);
			add(timeSubmittedContent);
			add(referralsTitle);
			add(referralsContent);
			add(priorityTitle);
			add(priorityContent);


			setBorder(new EtchedBorder());
			setPreferredSize(new Dimension(263, 100));

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







