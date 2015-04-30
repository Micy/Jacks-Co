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



public class ManagerViewUsers extends JFrame{

	public static void main(String[] args) {

		// to test this login with 2, 1234. as it produces a list of tickets
			
			
			
		ManagerViewUsers frame = new ManagerViewUsers();
		miscMethods.setWindowPosition(frame, 0);





	}

	ManagerViewUsers() {
		pack();
		setResizable(false);
		setVisible(true);

	}



	 


	
}