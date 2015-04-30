//javac -classpath .;mysqlconnector.jar;sendgrid.jar;miglayout.jar ManagerApplication.java
//java -classpath .;mysqlconnector.jar;sendgrid.jar;miglayout.jar ManagerApplication

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


public class ManagerApplication extends JFrame {

	static session managerID;

	static int selectedTicketID = 0;




	public static void main(String[] args) {


		session nsess = mySqlConnector.login(2, "1234");
		ManagerApplication frame = new ManagerApplication(nsess);




	}

	static private JButton viewUsers;
	static private JButton viewEngs;
	static private JButton viewTickets;
	static private JButton viewIssues;
	static private JButton viewSpecs;
	String foreName;
	String surName;



	ManagerApplication(session miD) {


		super("Manager Application  -  " + miD.getForename() + " " + miD.getSurname()); //putting engineers name in the windows title
		foreName = miD.getForename();
		surName = miD.getSurname();
		managerID = miD;

		buildGUI();

		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(250, 300);
		//pack();
		setVisible(true);
		setResizable(false);
		//getRootPane().setDefaultButton(testButton);


	}









	private void buildGUI() {
		
		JPanel pane = new JPanel(new MigLayout("wrap 1"));
		viewUsers = new JButton("View & Edit Users");
		viewEngs = new JButton("View & Edit Engineers");
		viewTickets = new JButton("View All Tickets");
		viewIssues = new JButton("View & Edit Issues");
		viewSpecs = new JButton("View & Edit Specialisations");
		JSeparator separator = new JSeparator();
		






		pane.add(new JLabel("Hello " + foreName + " " + surName));
		pane.add(separator, "growx, wrap");
		pane.add(viewUsers, "growx");
		pane.add(viewTickets, "growx");
		pane.add(viewEngs, "growx");
		pane.add(viewIssues, "growx");
		pane.add(viewSpecs, "growx");
		
		
		
		
		add(pane);
		


		pack();
		
	}







}