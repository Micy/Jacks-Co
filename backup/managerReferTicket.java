

import javax.swing.*;
import java.util.*;
import java.awt.Color;
import java.awt.event.*;

//MigLayout
import net.miginfocom.layout.Grid;
import net.miginfocom.swing.MigLayout;

public class managerReferTicket extends JFrame {

	Ticket ticket;
	Engineer engineer;


	protected JTextArea newEng;
	protected JTextArea newEngInput;
	protected JButton refer;
	protected JButton close;




	public managerReferTicket(Ticket t, Engineer e) {
		super("Ticket Number: " + t.getTicketID());

		ticket = t;
		engineer = e;

		if (t == null) {
			System.out.println("null ticket passed to window");
		}
		setLayout(new MigLayout("wrap 2"));

		newEng = new JTextArea("Enter new Engineer ID");
		//newEng.setBackground(defaultGrey);
		add(newEng);
		add(newEngInput);




























		setVisible(true);
		pack();

	}







}