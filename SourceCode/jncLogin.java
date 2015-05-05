//javac -classpath .;mysqlconnector.jar;sendgrid.jar;miglayout.jar;guava-18.0.jar jncLogin.java
//java -classpath .;mysqlconnector.jar;sendgrid.jar;miglayout.jar;guava-18.0.jar jncLogin

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


public class jncLogin extends JFrame{



	public static void main(String[] args) {

		// to test this login with 2, 1234. as it produces a list of tickets
			
			
			
		jncLogin frame = new jncLogin();
		miscMethods.setWindowPosition(frame, 0);





	}
	
	JTextField name;
    JPasswordField pass;
    JButton submit;
	
	jncLogin() {
		super("Jacks-Co HelpDesk - Login");
		name = new JTextField(10);
		pass = new JPasswordField(10);
		setLayout(new GridLayout(2,1));
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		JPanel pane = new JPanel(new GridLayout(2,2));
		pane.add(new JLabel("UserID: "));
		pane.add(name);
		pane.add(new JLabel("Password: "));
		pane.add(pass);
		submit = new JButton("Submit");
		add(pane);
		add(submit);
		submit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e){
				int userID = Integer.parseInt(name.getText()); // errors thrown when non number entered needs fixing
				String password = new String(pass.getPassword());
				if((userID == 0)||(password.equals(""))){
					JOptionPane.showMessageDialog(null,"UserID or Password Missing","Login Error",JOptionPane.ERROR_MESSAGE);
				}
				else{
					 session nsess = mySqlConnector.login(userID,password);
					 if(nsess == null){ // if the user id or password is wrong 
						JOptionPane.showMessageDialog(null,"Incorrect UserID or Password","Login Error",JOptionPane.ERROR_MESSAGE);
					 }
					 else if(nsess.getUserType()==0){ //is engineer launch engineer application
						System.out.println("Engineer logged in!");
						EngineerApplication frame = new EngineerApplication(nsess);
						miscMethods.setWindowPosition(frame, 0);
						setVisible(false);
						dispose();
					 }
					 else if(nsess.getUserType()==1){ // is manager launch manager application
						System.out.println("Manager logged in!");
						ManagerApplication frame = new ManagerApplication(nsess);
						miscMethods.setWindowPosition(frame, 0);
						setVisible(false);
						dispose();
					 }
					 else{ //user type error call support!
						JOptionPane.showMessageDialog(null,"User type error, Please your manager","Login Error",JOptionPane.ERROR_MESSAGE);
					 }
				}
			
			}
			
		});
		
		pack();
		setResizable(false);
		setVisible(true);


	}
	



}



