//javac -classpath .;miglayout.jar QueuedTicketPopup.java
//javac -classpath .;mysqlconnector.jar;sendgrid.jar;miglayout.jar QueuedTicketPopup.java

import javax.swing.*;
import java.util.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.event.*;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

//MigLayout
import net.miginfocom.layout.Grid;
import net.miginfocom.swing.MigLayout;

public class QueuedTicketPopup extends JFrame{

	protected TicketTable queuedTicketsTable;
	protected JTable table1;
	
	protected JButton acceptTicket;
	protected JButton additionalDetails;
	protected JButton closeWindow;
	
	List<Ticket> tickets;
	
	public QueuedTicketPopup(List<Ticket> queue){
	
	
		
		
		super("Queued Tickets");
		int selectedRow=0;
		int selectedColumn=0;
		tickets = queue;
		setLayout(new MigLayout());
			add(new menuBar(),"dock north");
			
			JScrollPane jScrollPane = new JScrollPane();
			queuedTicketsTable = new TicketTable(tickets);
			
			if(tickets != null){
				table1 = new JTable();
				table1.setModel(queuedTicketsTable);
				jScrollPane.getViewport().add(table1, null);
				add(jScrollPane);//Where tickets are loaded from
				//add(jScrollPane);//Where tickets are loaded from
			}
			else{
				add(new JTextArea("Unable to access server, try again later"));
			}
			closeWindow.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e){
					System.out.println("CloseButtonPressed!");
					setVisible(false);
					EngineerApplication.queuedViewClosed();
					
				}
			
			});
			additionalDetails.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e){
					int row = table1.getSelectedRow();
					int col = table1.getSelectedColumn();
					if(col == -1 || row == -1){
						JOptionPane.showMessageDialog(null,"Please Select a Ticket","No Ticket Selected",JOptionPane.WARNING_MESSAGE);
					}
					else{
						int[] newEntry = new int[] {row, col}; //{row,col}=selected cell
						//System.out.println(table1.getValueAt(row,0));
						//System.out.println(row);
						int ticketID = Integer.valueOf(String.valueOf(table1.getValueAt(row,0)));
						
					}
				}
			
			});
			acceptTicket.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e){
					int row = table1.getSelectedRow();
					int col = table1.getSelectedColumn();
					if(col == -1 || row == -1){
						JOptionPane.showMessageDialog(null,"Please Select a Ticket","No Ticket Selected",JOptionPane.WARNING_MESSAGE);
					}
					else{
						int[] newEntry = new int[] {row, col}; //{row,col}=selected cell
						System.out.println(table1.getValueAt(row,0));
						
						int selectedOption = JOptionPane.showConfirmDialog(null, 
                                  "Are you sure you want to accept this ticket?", 
                                  "Are you sure?", 
                                  JOptionPane.YES_NO_OPTION); 
						if (selectedOption == JOptionPane.YES_OPTION) {
							System.out.println("Ticket Accepted");
							int ticketID = Integer.valueOf(String.valueOf(table1.getValueAt(row,0)));
							//int ticketID =(int) table1.getValueAt(row,0);
							if(mySqlConnector.acceptTicket(ticketID)){
								JOptionPane.showMessageDialog(null,"Ticket Successfully Accepted","Ticket Successfully Accepted",JOptionPane.PLAIN_MESSAGE);
								Ticket acceptedTicket = miscMethods.findTicketByID(ticketID, tickets);
								User user = mySqlConnector.getUserbyID(acceptedTicket.getUserID());
								if(Emailer.ticketAccepted(acceptedTicket,user)){
								}
								else{
									JOptionPane.showMessageDialog(null,"Error sending confirmation email","Confirmation e-mail not sent!, Please Contact user manually!",JOptionPane.ERROR_MESSAGE);	
								}
						
								
								setVisible(false);
								dispose();
								System.out.println("Queued Tickets ReLoaded");
								QueuedTicketPopup frame = new QueuedTicketPopup(mySqlConnector.getQueuedTickets(EngineerApplication.getCurrentEngineerID()));
								
								miscMethods.setWindowPosition(frame, 0);
								
							}
							else{//if there is an sql error when trying to accept a ticket
								JOptionPane.showMessageDialog(null,"Error Accepting Ticket","Error Accepting Ticket",JOptionPane.ERROR_MESSAGE);	
							}
							
						}
					}
				}
			});
			
			
			addWindowListener(new WindowAdapter() {
			  public void windowClosing(WindowEvent e) {
				EngineerApplication.queuedViewClosed();
			  }
			});
			
			setVisible(true);
			pack();
			
		
		
		
		
	}

	public class menuBar extends JPanel{
	
		public menuBar(){
			acceptTicket = new JButton("Accept Ticket");
			//additionalDetails = new JButton("View Details");	
			closeWindow = new JButton("Close");
			
		
			add(acceptTicket);
			//add(additionalDetails);
			add(closeWindow);
		}
	}
	

}