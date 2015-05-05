

import javax.swing.*;
import java.util.*;
import java.awt.Color;
import java.awt.event.*;
import java.awt.Image;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.FlowLayout;

//MigLayout
import net.miginfocom.layout.Grid;
import net.miginfocom.swing.MigLayout;

public class openTicketDetailsWindow extends JFrame{
	

	protected Ticket ticket;
	protected User user;

	protected JButton completeTicket;
	protected JButton refer;
	protected JButton close;
	protected JButton viewImages;



	//Ticket Details Side
	protected JTextArea timeEntered;
	protected JTextArea issueName;
	protected JTextArea description;
	protected JTextArea priority;
	protected JTextArea screenShots;
	protected JTextArea noOfReferals;
	protected JTextArea timeEnteredt;
	protected JTextArea issueNamet;
	protected JTextArea descriptiont;
	protected JTextArea priorityt;
	protected JTextArea screenShotst;

	//User Details Side
	protected JTextArea name;
	protected JTextArea email;
	protected JTextArea phonenumber;
	protected JTextArea namet;
	protected JTextArea emailt;
	protected JTextArea phonenumbert;

	protected JTextArea noOfReferalst;

	protected Color defaultGrey = new Color(238, 238, 238);


	public openTicketDetailsWindow(Ticket t){

		super("Ticket Number: "+t.getTicketID()+" Details");
		ticket = t;
		user = mySqlConnector.getUserbyID(ticket.getUserID());

		
		setLayout(new MigLayout("wrap 2"));
		add(new buttons(),"dock south");
		add(new ticketDetails());
		add(new personDetails(),"growy");



		completeTicket.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e){
					int selectedOption = JOptionPane.showConfirmDialog(null, 
                                  "Are you sure you want to complete this ticket?", 
                                  "Are you sure?", 
                                  JOptionPane.YES_NO_OPTION); 
						if (selectedOption == JOptionPane.YES_OPTION) {
							if(mySqlConnector.completeTicket(ticket.getTicketID())){
								JOptionPane.showMessageDialog(null,"Ticket Successfully Completed","Ticket Successfully Completed",JOptionPane.PLAIN_MESSAGE);



								//Email TO DO	

								setVisible(false);
								dispose();

								EngineerApplication.refreshTable();

							}

						}
				}
			});

		close.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				setVisible(false);
				dispose();
				EngineerApplication.refreshTable();
			}


		});

		refer.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				

				List<Engineer> engineers = mySqlConnector.getAllEngineers();

				int[] ids = new int[engineers.size()];
				String[] names = new String[engineers.size()];

				for(int i =0; i< engineers.size() ; i++){
					ids[i]=engineers.get(i).getEngineerID();
					names[i]=engineers.get(i).getForename()+" "+engineers.get(i).getSurname();
				}

				String refereng = (String) JOptionPane.showInputDialog(null, 
        "Who do you want to refer the ticket to?",
        "Refer Ticket",
        JOptionPane.QUESTION_MESSAGE, 
        null, 
        names, 
        names[0]);
				int i=0;
				
				while(!names[i].equals(refereng)){
					i++;
				}


				int engineerID = ids[i];



				if(mySqlConnector.referTicket(ticket.getTicketID(), engineerID, ticket.getNoOfReferrals()+1, ticket.getReferralHistory()+engineerID+",")){
					JOptionPane.showMessageDialog(null,"Ticket Successfully Referred","Ticket Successfully Referred",JOptionPane.PLAIN_MESSAGE);
					}
					else{
					JOptionPane.showMessageDialog(null,"Error Referring Ticket","Error Referring Ticket",JOptionPane.ERROR_MESSAGE);
					}



				setVisible(false);
				dispose();
				EngineerApplication.refreshTable();

			}
		});
		
		viewImages.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){

				BufferedImage images[] = miscMethods.getTicketImages(t);
				for(int i = 0 ; i<images.length; i++){
					PhotoViewer p = new PhotoViewer(images[i]);
				}
				
			}


		});



		setVisible(true);
		pack();




	}
	public class ticketDetails extends JPanel{
		public ticketDetails(){
			setLayout(new MigLayout("wrap 2"));


			timeEnteredt = new JTextArea("Time Submitted: ");
			timeEnteredt.setBackground(defaultGrey);
			add(timeEnteredt);
			timeEntered = new JTextArea(miscMethods.convertTime(ticket.getTimeEntered()),1,25);
			add(timeEntered);


			issueNamet = new JTextArea("Issue Type: ");
			issueNamet.setBackground(defaultGrey);
			add(issueNamet);
			issueName = new JTextArea(ticket.getIssueName(),1,25);
			add(issueName);


			descriptiont = new JTextArea("Description: ");
			descriptiont.setBackground(defaultGrey);
			add(descriptiont);
			description = new JTextArea(ticket.getProblemDesc(),4,25);
			description.setLineWrap(true);
			add(description);


			priorityt = new JTextArea("Priority: ");
			priorityt.setBackground(defaultGrey);
			add(priorityt);
			priority = new JTextArea(Integer.toString(ticket.getPriority()));
			add(priority);

			//PUT PHOTOS IN HERE!
			
			

			noOfReferalst = new JTextArea("Number of Referals: ");
			noOfReferalst.setBackground(defaultGrey);
			add(noOfReferalst);
			noOfReferals = new JTextArea(Integer.toString(ticket.getNoOfReferrals()));
			add(noOfReferals);

			timeEnteredt.setEditable(false);
			timeEntered.setEditable(false);
			issueNamet.setEditable(false);
			issueName.setEditable(false);
			descriptiont.setEditable(false);
			description.setEditable(false);
			priorityt.setEditable(false);
			priority.setEditable(false);
			noOfReferalst.setEditable(false);
			noOfReferals.setEditable(false);


		}
	}
	public class personDetails extends JPanel{
		public personDetails(){
			setLayout(new MigLayout("wrap 2"));

			namet = new JTextArea("Name of User: ");
			namet.setBackground(defaultGrey);
			add(namet);
			name = new JTextArea(user.getTitle()+" "+user.getForename()+" "+user.getSurname(),1, 20);
			add(name);

			emailt = new JTextArea("E-Mail Address: ");
			emailt.setBackground(defaultGrey);
			add(emailt);
			email = new JTextArea(user.getEmail());
			add(email);

			phonenumbert = new JTextArea("Phone Number: ");
			phonenumbert.setBackground(defaultGrey);
			add(phonenumbert);
			phonenumber = new JTextArea(user.getPhoneNumber());
			add(phonenumber);

			namet.setEditable(false);
			name.setEditable(false);
			emailt.setEditable(false);
			email.setEditable(false);
			phonenumbert.setEditable(false);
			phonenumber.setEditable(false);


		}

	}
	public class buttons extends JPanel{
		public buttons(){
			completeTicket = new JButton("Complete");
			viewImages = new JButton("View Images");
			refer = new JButton("Refer Ticket");	
			close = new JButton("Close");


			add(completeTicket);
			add(viewImages);
			add(refer);
			add(close);
		}

	}




}