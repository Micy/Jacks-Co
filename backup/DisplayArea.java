import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;

public class DisplayArea
  extends JPanel
{
  private final String title;
  private final String prefix;
  private final JTextArea display;
  
  public DisplayArea(String paramString1, String paramString2, int paramInt1, int paramInt2)
  {
    this.title = paramString1;
    this.prefix = paramString2;
    this.display = new JTextArea(paramInt1, paramInt2);
    this.display.setEditable(false);
    JScrollPane localJScrollPane = new JScrollPane(this.display);
    localJScrollPane.setHorizontalScrollBarPolicy(30);
    
    localJScrollPane.setVerticalScrollBarPolicy(22);
    
    setLayout(new BorderLayout());
    add(localJScrollPane, "Center");
    setBorder(new TitledBorder(paramString1));
  }
  
  public void clear()
  {
    this.display.setText("");
  }
  
  public void addLine(String paramString)
  {
    this.display.append(this.prefix + paramString + "\n");
    this.display.setCaretPosition(this.display.getText().length());
  }
  
  public void addChar(char paramChar)
  {
    this.display.append("" + paramChar);
  }
  
  public static void main(String[] paramArrayOfString)
  {
    JFrame localJFrame = new JFrame("Display Test");
    localJFrame.setDefaultCloseOperation(3);
    DisplayArea localDisplayArea = new DisplayArea("test", "", 15, 30);
    localDisplayArea.addLine("Mummy was an Asteroid, Daddy was a Small Non-stick Kitchen Utensil");
    localJFrame.add(localDisplayArea, "Center");
    localJFrame.pack();
    localJFrame.setVisible(true);
  }
}