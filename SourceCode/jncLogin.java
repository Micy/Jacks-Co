//javac -classpath .;mysqlconnector.jar;sendgrid.jar;miglayout.jar jncLogin.java
//java -classpath .;mysqlconnector.jar;sendgrid.jar;miglayout.jar jncLogin

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
import java.awt.GraphicsEnvironment;
import java.awt.GraphicsDevice;

public class jncLogin extends JFrame{



	public static void main(String[] args) {

		// to test this login with 2, 1234. as it produces a list of tickets
			
			
			
		jncLogin frame = new jncLogin();
		setWindowPosition(frame, 0);





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
						setVisible(false);
						dispose();
					 }
					 else if(nsess.getUserType()==1){ // is manager launch manager application
						System.out.println("Manager logged in!");
						/*----------
						TODO CODE TO LAUNCH MANAGER PROGRAM!
						------------*/
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
	
	//method to centre the window on the main screen
	private static void setWindowPosition(JFrame window, int screen)
	{        
		GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
		GraphicsDevice[] allDevices = env.getScreenDevices();
		int topLeftX, topLeftY, screenX, screenY, windowPosX, windowPosY;

		if (screen < allDevices.length && screen > -1)
		{
			topLeftX = allDevices[screen].getDefaultConfiguration().getBounds().x;
			topLeftY = allDevices[screen].getDefaultConfiguration().getBounds().y;

			screenX  = allDevices[screen].getDefaultConfiguration().getBounds().width;
			screenY  = allDevices[screen].getDefaultConfiguration().getBounds().height;
		}
		else
		{
			topLeftX = allDevices[0].getDefaultConfiguration().getBounds().x;
			topLeftY = allDevices[0].getDefaultConfiguration().getBounds().y;

			screenX  = allDevices[0].getDefaultConfiguration().getBounds().width;
			screenY  = allDevices[0].getDefaultConfiguration().getBounds().height;
		}

		windowPosX = ((screenX - window.getWidth())  / 2) + topLeftX;
		windowPosY = ((screenY - window.getHeight()) / 2) + topLeftY;

		window.setLocation(windowPosX, windowPosY);
	}




}



