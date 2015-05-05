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
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.FlowLayout;

public class PhotoViewer extends JFrame{
	protected JLabel holder;
	private ImageIcon image;
	
	public PhotoViewer(BufferedImage img){
			
				image = new ImageIcon(img);
				JLabel holder = new JLabel(image); 
				setLayout(new FlowLayout());
				add(holder);
				pack();
				setVisible(true);
		
		
	
	}
}