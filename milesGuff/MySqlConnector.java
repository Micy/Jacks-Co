import java.sql.*;
import java.util.*;

//javac -classpath .;mysqlconnector.jar MySqlConnector.java
//java -classpath .;mysqlconnector.jar MySqlConnector

public class MySqlConnector {
   // JDBC driver name and database URL
   static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
   static final String DB_URL = "jdbc:mysql://46.101.62.246:3306/TicketServer";

   //  Database credentials
   static final String USER = "test";
   static final String PASS = "Jnc15Svr";
   String sql;
   String email;
   String test = "you what?";


   public MySqlConnector(String passedSql) {
      sql = passedSql;
   }


   public void runSQL() {

      Connection conn = null;
      Statement stmt = null;
      try {
         //STEP 2: Register JDBC driver
         Class.forName("com.mysql.jdbc.Driver");

         //STEP 3: Open a connection
         //System.out.println("Connecting to database...");
         conn = DriverManager.getConnection(DB_URL, USER, PASS);

         //STEP 4: Execute a query
         // System.out.println("Creating statement...");
         stmt = conn.createStatement();


         ResultSet rs = stmt.executeQuery(sql);

         //STEP 5: Extract data from result set
         while (rs.next()) {
            //Retrieve by column name
            String first = rs.getString("Forename");
            String last = rs.getString("Surname");

            //Display values
            System.out.println("Forename: " + first);
            System.out.println("Surname: " + last);
         }
         //STEP 6: Clean-up environment
         rs.close();
         stmt.close();
         conn.close();
      } catch (SQLException se) {
         //Handle errors for JDBC
         se.printStackTrace();
      } catch (Exception e) {
         //Handle errors for Class.forName
         e.printStackTrace();
      } finally {
         //finally block used to close resources
         try {
            if (stmt != null)
               stmt.close();
         } catch (SQLException se2) {
         }// nothing we can do
         try {
            if (conn != null)
               conn.close();
         } catch (SQLException se) {
            se.printStackTrace();
         }//end finally try
      }//end try
      System.out.println("Goodbye!");
   }





   //method to return the email address of the passed sql as a string, which
   //can then be used to send an email
   public String returnEmail() {

      Connection conn = null;
      Statement stmt = null;
      try {
         //STEP 2: Register JDBC driver
         Class.forName("com.mysql.jdbc.Driver");
         //STEP 3: Open a connection
         //System.out.println("Connecting to database...");
         conn = DriverManager.getConnection(DB_URL, USER, PASS);
         //STEP 4: Execute a query
         //System.out.println("Creating statement...");
         stmt = conn.createStatement();
         ResultSet rs = stmt.executeQuery(sql);
         while (rs.next()) {
            email = rs.getString("Email");
            System.out.println("Email: " + email);
         }
         //STEP 6: Clean-up environment
         rs.close();
         stmt.close();
         conn.close();
      } catch (SQLException se) {
         //Handle errors for JDBC
         se.printStackTrace();
      } catch (Exception e) {
         //Handle errors for Class.forName
         e.printStackTrace();
      } finally {
         //finally block used to close resources
         try {
            if (stmt != null)
               stmt.close();
         } catch (SQLException se2) {
         }// nothing we can do
         try {
            if (conn != null)
               conn.close();
         } catch (SQLException se) {
            se.printStackTrace();
         }//end finally try
         //return "I'm not sure";
      }//end try
      return email;
   }






   public List<Ticket> returnTicketList() {
      List<Ticket> open_tickets = new ArrayList<Ticket>();
      int ticketID;
      int issueID;
      int priority;
      Timestamp timeEntered;
      long timeEnteredLong;

      Connection conn = null;
      Statement stmt = null;
      try {
         //STEP 2: Register JDBC driver
         Class.forName("com.mysql.jdbc.Driver");
         //STEP 3: Open a connection
         //System.out.println("Connecting to database...");
         conn = DriverManager.getConnection(DB_URL, USER, PASS);
         //STEP 4: Execute a query
         //System.out.println("Creating statement...");
         stmt = conn.createStatement();
         ResultSet rs = stmt.executeQuery(sql);
         while (rs.next()) {


            //set variables to the values in the specified comments
            ticketID = rs.getInt("TicketID");
            issueID = rs.getInt("IssueID");
            priority = rs.getInt("Priority");
            timeEntered = rs.getTimestamp("TimeEntered");
            //convert timestamp to long
            timeEnteredLong = timeEntered.getTime();
            //create a new ticket, then add variables
            Ticket new_ticket = new Ticket();
            new_ticket.setTicketID(ticketID);
            new_ticket.setIssueID(issueID);
            new_ticket.setPriority(priority);
            new_ticket.setTimeEntered(timeEnteredLong);


            open_tickets.add(new_ticket);
         }


         //STEP 6: Clean-up environment
         rs.close();
         stmt.close();
         conn.close();
      } catch (SQLException se) {
         //Handle errors for JDBC
         se.printStackTrace();
      } catch (Exception e) {
         //Handle errors for Class.forName
         e.printStackTrace();
      } finally {
         //finally block used to close resources
         try {
            if (stmt != null)
               stmt.close();
         } catch (SQLException se2) {
         }// nothing we can do
         try {
            if (conn != null)
               conn.close();
         } catch (SQLException se) {
            se.printStackTrace();
         }//end finally try
         //return "I'm not sure";
      }//end try
      return open_tickets;
   }

















   public Vector<Vector> returnTicketListVector() {

      Vector<Vector> vector_of_tickets = new Vector<Vector>();
      Timestamp timeEntered;
      Long timeEnteredLong;


      Connection conn = null;
      Statement stmt = null;
      try {
         //STEP 2: Register JDBC driver
         Class.forName("com.mysql.jdbc.Driver");
         //STEP 3: Open a connection
         //System.out.println("Connecting to database...");
         conn = DriverManager.getConnection(DB_URL, USER, PASS);
         //STEP 4: Execute a query
         //System.out.println("Creating statement...");
         stmt = conn.createStatement();
         ResultSet rs = stmt.executeQuery(sql);

         //http://stackoverflow.com/questions/10620448/most-simple-code-to-populate-jtable-from-resultset
         //http://www.java2s.com/Code/Java/Swing-JFC/DisplayResultSetinTableJTable.htm
         //Get metadata from result set
         ResultSetMetaData metaData = rs.getMetaData();
         //get number of columns
         int columnCount = metaData.getColumnCount();
         //create array for table header
         String[] columnNames = new String[columnCount];
         //populate array with column name
         for (int h = 1; h <= columnCount; h++) {
            columnNames[h - 1] = metaData.getColumnName(h);
         }

         //for every record in resultset
         while (rs.next()) {
            //create a new string array with lenght of the number of columns
            Vector<String> individual_ticket = new Vector<String>();
            //move throught the ticket and parse everything as a string, then store it in the
            //array

            individual_ticket.addElement(rs.getString("TicketID"));
            individual_ticket.addElement(rs.getString("IssueID"));
            individual_ticket.addElement(rs.getString("Priority"));
            //get timestamp as timestamp
            timeEntered = rs.getTimestamp("TimeEntered");
            //convert timestamp to long
            timeEnteredLong = timeEntered.getTime();

            //set the long to string, then add it
            individual_ticket.addElement(timeSinceMethod(timeEnteredLong));
            //adds that array of strings to the vector
            vector_of_tickets.addElement(individual_ticket);

         }

         //STEP 6: Clean-up environment
         rs.close();
         stmt.close();
         conn.close();
      } catch (SQLException se) {
         //Handle errors for JDBC
         se.printStackTrace();
      } catch (Exception e) {
         //Handle errors for Class.forName
         e.printStackTrace();
      } finally {
         //finally block used to close resources
         try {
            if (stmt != null)
               stmt.close();
         } catch (SQLException se2) {
         }// nothing we can do
         try {
            if (conn != null)
               conn.close();
         } catch (SQLException se) {
            se.printStackTrace();
         }//end finally try
         //return "I'm not sure";
      }//end try
      return vector_of_tickets;
   }






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
                   "%d D%s, %d hr%s, %d min%s, %d sec%s",
                   diff[0],
                   diff[0] > 1 ? "s" : "",
                   diff[1],
                   diff[1] > 1 ? "s" : "",
                   diff[2],
                   diff[2] > 1 ? "s" : "",
                   diff[3],
                   diff[3] > 1 ? "s" : "");
      }
   }









   public void acceptTicket() {
      Connection conn = null;
      Statement stmt = null;
      try {
         //STEP 2: Register JDBC driver
         Class.forName("com.mysql.jdbc.Driver");
         //STEP 3: Open a connection
         //System.out.println("Connecting to database...");
         conn = DriverManager.getConnection(DB_URL, USER, PASS);
         //STEP 4: Execute a query
         //System.out.println("Creating statement...");
         stmt = conn.createStatement();
         stmt.executeUpdate(sql);

         //STEP 6: Clean-up environment

         stmt.close();
         conn.close();
      } catch (SQLException se) {
         //Handle errors for JDBC
         se.printStackTrace();
      } catch (Exception e) {
         //Handle errors for Class.forName
         e.printStackTrace();
      } finally {
         //finally block used to close resources
         try {
            if (stmt != null)
               stmt.close();
         } catch (SQLException se2) {
         }// nothing we can do
         try {
            if (conn != null)
               conn.close();
         } catch (SQLException se) {
            se.printStackTrace();
         }//end finally try
         //return "I'm not sure";
      }//end try

   }




}


