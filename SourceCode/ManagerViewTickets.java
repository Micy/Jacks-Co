//javac -classpath .;mysqlconnector.jar;sendgrid.jar;miglayout.jar ManagerUserView.java
//java -classpath .;mysqlconnector.jar;sendgrid.jar;miglayout.jar ManagerUserView




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



public class ManagerViewTickets extends JFrame {


	static int selectedTicketID = 0;

	static session managerSession;

	public static void main(String[] args) {



		session nsess = mySqlConnector.login(1, "1234");
		ManagerViewTickets frame = new ManagerViewTickets(nsess);
		miscMethods.setWindowPosition(frame, 0);





	}

	ManagerViewTickets(session mID) {
		super("View All Tickets");
		
		managerSession = mID;

		System.out.println("Manager View Tickets Constructor");
		System.out.println("Manager ID is " + managerSession.getManagerID());

		//System.out.println("Manager ID is "+managerSession.getManagerID());


		buildGUI();
		//setDefaultCloseOperation(EXIT_ON_CLOSE);

		pack();
		setResizable(false);
		setVisible(true);

	}


	//Buttons
	//private JButton openTicketsButton;
	private JButton logOut;
	private JButton refreshButton;
	private JButton completeButton;
	private JButton referButton;
	private JButton viewDetails;
	private static JButton queuedView;

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

	//private JTextArea openTicketsTitle;

	private JTabbedPane jtp;
	private JPanel openTickets;
	private JPanel queuedTickets;
	//JPanel LeftSide;
	private JPanel buttonMenu;

	protected static TicketTable openTicketsTable;
	protected TicketTable queuedTicketsTable;

	protected static JTable table1;

	List<Ticket> tickets;

	//Fonts
	Font headerFont = new Font("SansSerif", Font.PLAIN, 25);
	Font titleFont = new Font("SansSerif", Font.PLAIN, 15);

	//Colours
	Color defaultGrey = new Color(238, 238, 238);



	private void buildGUI() {
		setLayout(new MigLayout());

		buttonMenu = new JPanel();
		add(new buttonMenu(), "dock north");
		System.out.println("qwertyui");



		openTickets = new JPanel();
		System.out.println("Left Side about to add");

		openTickets.add(new LeftFide(), "grow"); //table side
		System.out.println("Left Side added");

		openTickets.add(new RightSide(), "grow"); //info side

		add(openTickets);

		addWindowListener(new WindowAdapter() {
			  public void windowClosing(WindowEvent e) {
				ManagerApplication.queuedViewClosed();
			  }
			});

	}

	public class LeftFide extends JPanel {
		public LeftFide() {
			setLayout(new MigLayout("wrap"));
			System.out.println("Left Side ");
			if (managerSession == null) {
				System.out.println("null manviewticket");
			}
			JScrollPane jScrollPane2 = new JScrollPane();

			tickets = mySqlConnector.getAllManagersTickets(managerSession.getManagerID());
			openTicketsTable = new TicketTable(tickets);
			//openTicketsTitle = new JTextArea("Open Tickets");
			//openTicketsTitle.setBackground(defaultGrey);
			//add(openTicketsTitle,"wrap");
			if (tickets != null) {
				table1 = new JTable();
				table1.setModel(openTicketsTable);
				jScrollPane2.getViewport().add(table1, null);
				openTickets.add(jScrollPane2);//Where tickets are loaded from


				table1.addMouseListener(tableMouseListener);
				table1.getTableHeader().setReorderingAllowed(false);




				table1.addMouseListener(tableMouseListener);





			} else {
				openTickets.add(new JTextArea("No Open Tickets Available!"));
			}
		}
	}

	public class RightSide extends JPanel {
		public RightSide() {
			setLayout(new MigLayout("wrap 1"));

			//add(new ViewsButtonPanel(), "align center");
			ticketDetailsTitle = new JTextArea("Ticket Details");
			ticketDetailsTitle.setFont(headerFont);
			ticketDetailsTitle.setEditable(false);
			ticketDetailsTitle.setBackground(defaultGrey);
			add(ticketDetailsTitle);

			add(new TicketDetailsPanel(), "growx");
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
			timeSubmittedContent = new JTextArea("No Ticket Selected");
			referralsTitle = new JTextArea("Referrals:");
			referralsTitle.setBackground(defaultGrey);
			referralsContent = new JTextArea("No Ticket Selected");
			priorityTitle = new JTextArea("Ticket Priority: ");
			priorityTitle.setBackground(defaultGrey);
			priorityContent = new JTextArea("No Ticket Selected");
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

	public class buttonMenu extends JPanel {

		public buttonMenu() {			


			refreshButton = new JButton("Refresh View");
			refreshButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					refreshTable();

				}

			});

			viewDetails = new JButton("View Details");

			viewDetails.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {

					int row = table1.getSelectedRow();
					int col = table1.getSelectedColumn();
					if (col == -1 || row == -1) {
						JOptionPane.showMessageDialog(null, "Please Select a Ticket", "No Ticket Selected", JOptionPane.WARNING_MESSAGE);
					} else {
						int[] newEntry = new int[] {row, col}; //{row,col}=selected cell
						//System.out.println(table1.getValueAt(row,0));
						//System.out.println(row);
						int ticketID = Integer.valueOf(String.valueOf(table1.getValueAt(row, 0)));
						System.out.println(ticketID);
						List<Ticket> t = mySqlConnector.getAllManagersTickets(managerSession.getManagerID());
						//System.out.println(t.getTicketID());
						managerOpenTicketDetailsWindow frame3 = new managerOpenTicketDetailsWindow(miscMethods.findTicketByID(ticketID, t));
						miscMethods.setWindowPosition(frame3, 0);
						System.out.println("Manager View Details Pressed!  " + ticketID);

					}

				}

			});



			add(refreshButton);
			add(viewDetails);
			//add(queuedView);
			//add(logOut);
		}
	}

	MouseListener tableMouseListener = new MouseAdapter() {



		@Override
		public void mouseClicked(MouseEvent e) {

			int row = table1.rowAtPoint(e.getPoint());//get mouse-selected row
			int col = table1.columnAtPoint(e.getPoint());//get mouse-selected col
			int[] newEntry = new int[] {row, col}; //{row,col}=selected cell
			System.out.println("Here!  " + table1.getValueAt(row, 0));
			//int id = table1.getValueAt(row,0);

			int id = Integer.valueOf(String.valueOf(table1.getValueAt(row, 0)));

			Ticket t = miscMethods.findTicketByID(id, tickets);

			timeSubmittedContent.setText(miscMethods.convertTime(t.getTimeEntered()));

			referralsContent.setText(Integer.toString(t.getNoOfReferrals()));

			priorityContent.setText(Integer.toString(t.getPriority()));


			//timeSubmittedContent


			//System.out.println(row);
			//referralsContent.setText(Integer.toString(tickets.get(leftTable.getValueAt(row,0)).getgetNoOfReferrals()));


		}
	};



	// public static int getCurrentEngineerID() {
	// 	return managerSession.getManagerID();
	// }

	public static void queuedViewClosed() {

		queuedView.setEnabled(true);
		refreshTable();


	}

	public static void refreshTable() {

		List<Ticket> tickets = mySqlConnector.getAllManagersTickets(managerSession.getManagerID());
		openTicketsTable = new TicketTable(tickets);
		table1.setModel(openTicketsTable);

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