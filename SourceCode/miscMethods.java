import java.awt.GraphicsEnvironment;
import java.awt.GraphicsDevice;
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

public class miscMethods{

   //returns the a times in a time since string.
   public static String timeSinceMethod(long tmEntrd) {

      long currentTime = System.currentTimeMillis();
      long diffInSeconds = (currentTime - tmEntrd) / 1000;

      long diff[] = new long[] { 0, 0, 0, 0 };
      /* sec */diff[3] = (diffInSeconds >= 60 ? diffInSeconds % 60 : diffInSeconds);
      /* min */diff[2] = (diffInSeconds = (diffInSeconds / 60)) >= 60 ? diffInSeconds % 60 : diffInSeconds;
      /* hours */diff[1] = (diffInSeconds = (diffInSeconds / 60)) >= 24 ? diffInSeconds % 24 : diffInSeconds;
      /* days */diff[0] = (diffInSeconds = (diffInSeconds / 24));

      if (diff[0] >= 1) {
         return String.format(
                   "%d day%s",
                   diff[0],
                   diff[0] > 1 ? "s" : ""
                );
      }
      if (diff[1] >= 1) {
         return String.format(
                   "%d hour%s",
                   diff[1],
                   diff[1] > 1 ? "s" : ""
                );
      }
      if (diff[2] >= 1) {
         return String.format(
                   "%d minute%s",
                   diff[2],
                   diff[2] > 1 ? "s" : ""
                );
      } else  {
         return String.format(
                   "%d second%s",
                   diff[3],
                   diff[3] > 1 ? "s" : ""
                );
      }
   }
   
   	//method to centre the window on the main screen
	public static void setWindowPosition(JFrame window, int screen)
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

