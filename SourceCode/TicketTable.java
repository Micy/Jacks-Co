import java.util.Collection;
import java.util.Vector;
import javax.swing.table.AbstractTableModel;
import java.util.*;

 public class TicketTable extends AbstractTableModel {
     public static final int TICKET_ID = 0;
     public static final int ISSUE_TYPE = 1;
     public static final int PRIORITY = 2;
     public static final int TIME_IN_SYSTEM = 3;

     protected String[] columnNames = {"TicketID","Issue Type","Priority","Time In System"};
     protected List<Ticket> dataVector;
	
     public TicketTable(List<Ticket> tickets) {
         dataVector = tickets;
     }
	@Override
     public String getColumnName(int column) {
         return columnNames[column];
     }
	@Override
     public boolean isCellEditable(int row, int column) {
         return false;  
     }
	@Override
     public Class getColumnClass(int column) {
         switch (column) {
             case TICKET_ID:
             case ISSUE_TYPE:
             case PRIORITY:
			 case TIME_IN_SYSTEM:
                return String.class;
             default:
                return Object.class;
         }
     }
	@Override
     public Object getValueAt(int row, int column) {
         Ticket ticket = (Ticket)dataVector.get(row);
         switch (column) {
             case TICKET_ID:
                return Integer.toString(ticket.getTicketID());
             case ISSUE_TYPE:
                return ticket.getIssueName();
             case PRIORITY:
                return Integer.toString(ticket.getPriority());
			case TIME_IN_SYSTEM:
				return miscMethods.timeSinceMethod(ticket.getTimeEntered());
             default: 
                return new Object();
         }
     }
	/*
     public void setValueAt(Object value, int row, int column) {// need modification
         AudioRecord record = (AudioRecord)dataVector.get(row);
         switch (column) {
             case TITLE_INDEX:
                record.setTitle((String)value);
                break;
             case ARTIST_INDEX:
                record.setArtist((String)value);
                break;
             case ALBUM_INDEX:
                record.setAlbum((String)value);
                break;
             default:
                System.out.println("invalid index");
         }
         fireTableCellUpdated(row, column);
     }
	*/
	@Override
     public int getRowCount() {
         return dataVector.size();
     }
	@Override
     public int getColumnCount() {
         return columnNames.length;
     }
	/*
     public boolean hasEmptyRow() {
         if (dataVector.size() == 0) return false;
         AudioRecord audioRecord = (AudioRecord)dataVector.get(dataVector.size() - 1);
         if (audioRecord.getTitle().trim().equals("") &&
            audioRecord.getArtist().trim().equals("") &&
            audioRecord.getAlbum().trim().equals(""))
         {
            return true;
         }
         else return false;
     }

     public void addEmptyRow() {
         dataVector.add(new AudioRecord());
         fireTableRowsInserted(
            dataVector.size() - 1,
            dataVector.size() - 1);
     }
	 */
 }