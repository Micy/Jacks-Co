import java.sql.*;
import java.util.*;

//javac -classpath .;mysqlconnector.jar mySqlConnector.java
//java -classpath .;mysqlconnector.jar mySqlConnector

public class mySqlConnector {
   // JDBC driver name and database URL
   static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
   static final String DB_URL = "jdbc:mysql://46.101.62.246:3306/TicketServer";

   //  Database credentials
   static final String USER = "test";
   static final String PASS = "Jnc15Svr";














   // the user logs in using their personID
   public static session login(int userID, String pass){
   Connection conn = null;
   Statement stmt = null;

   try{
      Class.forName("com.mysql.jdbc.Driver");
      conn = DriverManager.getConnection(DB_URL,USER,PASS);
      stmt = conn.createStatement();
      String sql;
      sql = "SELECT PersonID, Password, Forename, Surname FROM Person WHERE PersonID = "+userID+" AND Password = "+pass;
      ResultSet rs = stmt.executeQuery(sql);
      if(!rs.first()){
         //System.out.println("user not found");
         return null;
      }
      else{

         int personID=rs.getInt("PersonID");
         String fName=rs.getString("Forename");
         String sName=rs.getString("Surname");

         

         rs.close();
         stmt.close();
         conn.close();
         //int type = getUserType(personID);
         //System.out.println(personID+"  "+fName+"  "+sName+"  "+getUserType(personID));
         session s = new session(getUserType(personID), personID, fName, sName);   
         return s;
      }
   }//try
   catch(SQLException se){
      //Handle errors for JDBC
      se.printStackTrace();
   }catch(Exception e){
      //Handle errors for Class.forName
      e.printStackTrace();
   }finally{
      //finally block used to close resources
      try{
         if(stmt!=null)
            stmt.close();
      }catch(SQLException se2){
      }// nothing we can do
      try{
         if(conn!=null)
            conn.close();
      }catch(SQLException se){
         se.printStackTrace();
      }//end finally try
   }//end try


      return null;
   }//login method
   













   //returns the following values dependant on user type
   // 0 = engineer 1 = manager 2 = user or person entered is neither a engineer or manager
   private static int getUserType(int pID){
   Connection conn = null;
   Statement stmt = null;
   try{
      Class.forName("com.mysql.jdbc.Driver");
      conn = DriverManager.getConnection(DB_URL,USER,PASS);
      stmt = conn.createStatement();
      String sql;
      sql="SELECT EngineerID FROM Engineer WHERE PersonID = "+pID;
      ResultSet rs = stmt.executeQuery(sql);
      if(rs.first()){
         rs.close();
         stmt.close();
         conn.close();         
         return 0;
      }
      else{
         rs.close();

         sql="SELECT ManagerID FROM Manager WHERE PersonID = "+pID;
         rs = stmt.executeQuery(sql);
         if(rs.first()){
         rs.close();
         stmt.close();
         conn.close();         
         return 1;
         }
         else{
            rs.close();
            stmt.close();
            conn.close();         
            return 2;
         }
      }
   }//try
      catch(SQLException se){
      //Handle errors for JDBC
      se.printStackTrace();
   }catch(Exception e){
      //Handle errors for Class.forName
      e.printStackTrace();
   }finally{
      //finally block used to close resources
      try{
         if(stmt!=null)
            stmt.close();
      }catch(SQLException se2){
      }// nothing we can do
      try{
         if(conn!=null)
            conn.close();
      }catch(SQLException se){
         se.printStackTrace();
      }//end finally try
   }//end try
   return 2;
   }














   //returns a list of engineers given a manager ID
   public static List<Engineer> getManagersEngineers(int mID){
      Connection conn = null;
      Statement stmt = null;
      List<Engineer> list = new Vector<Engineer>();
      try{
         Class.forName("com.mysql.jdbc.Driver");
         conn = DriverManager.getConnection(DB_URL,USER,PASS);
         stmt = conn.createStatement();
         String sql;
         sql = "SELECT * FROM Person, Engineer WHERE Engineer.PersonID = Person.PersonID AND Engineer.ManagerID = "+mID;
         ResultSet rs = stmt.executeQuery(sql);
         while(rs.next()){
         list.add(new Engineer(rs.getInt("EngineerID"),rs.getInt("ExpertiseID"),rs.getInt("Availability"),rs.getInt("ManagerID"),rs.getInt("PersonID"),rs.getString("Title"),rs.getString("Forename"),rs.getString("Surname"),rs.getString("Email"),rs.getString("PhoneNumber"),rs.getString("Password"),rs.getString("Salt")));
         }


         rs.close();
         stmt.close();
         conn.close(); 
         return list;
      }//try
      catch(SQLException se){
      //Handle errors for JDBC
      se.printStackTrace();
   }catch(Exception e){
      //Handle errors for Class.forName
      e.printStackTrace();
   }finally{
      //finally block used to close resources
      try{
         if(stmt!=null)
            stmt.close();
      }catch(SQLException se2){
      }// nothing we can do
      try{
         if(conn!=null)
            conn.close();
      }catch(SQLException se){
         se.printStackTrace();
      }//end finally try
   }//end try
      return null;
   }












   //returns a list of all users within the system.
   public static List<User> getAllUsers(){
      Connection conn = null;
      Statement stmt = null;
      List<User> list = new Vector<User>();
      try{
         Class.forName("com.mysql.jdbc.Driver");
         conn = DriverManager.getConnection(DB_URL,USER,PASS);
         stmt = conn.createStatement();
         String sql; 
         sql = "SELECT * FROM User, Person WHERE User.PersonID=Person.PersonID";  
         ResultSet rs = stmt.executeQuery(sql);
         while(rs.next()){
            list.add(new User(rs.getInt("UserID"),rs.getInt("PersonID"),rs.getString("Title"),rs.getString("Forename"),rs.getString("Surname"),rs.getString("Email"),rs.getString("PhoneNumber"),rs.getString("Password"),rs.getString("Salt")));
         }
         rs.close();
         stmt.close();
         conn.close(); 
         return list;
      }//try
      catch(SQLException se){
      //Handle errors for JDBC
      se.printStackTrace();
   }catch(Exception e){
      //Handle errors for Class.forName
      e.printStackTrace();
   }finally{
      //finally block used to close resources
      try{
         if(stmt!=null)
            stmt.close();
      }catch(SQLException se2){
      }// nothing we can do
      try{
         if(conn!=null)
            conn.close();
      }catch(SQLException se){
         se.printStackTrace();
      }//end finally try
   }//end try
      return null;
   }


   
	//get all open tickets for a given engineerID
	
	public static List<Ticket> getOpenTickets(int eID){
		Connection conn = null;
		Statement stmt = null;
		List<Ticket> list = new Vector<Ticket>();
		try{
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(DB_URL,USER,PASS);
			stmt = conn.createStatement();
			String sql; 
			sql = "SELECT * FROM Ticket WHERE StateFlag = 2 AND EngineerID = "+eID;
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()){
				list.add(new Ticket(rs.getInt("TicketID"),rs.getInt("UserID"),rs.getInt("EngineerID"),rs.getInt("IssueID"),(rs.getTimestamp("TimeEntered").getTime()),rs.getInt("StateFlag"),rs.getString("ProblemDescription"),rs.getString("Screenshot"),(rs.getTimestamp("TimeEntered").getTime()),rs.getInt("NoOfReferrals"),rs.getString("RefferalHistory"),rs.getInt("Priority")));
			}
		rs.close();
        stmt.close();
        conn.close(); 
         return list;	
		}//try
		catch(SQLException se){
		  //Handle errors for JDBC
		  se.printStackTrace();
	   }catch(Exception e){
		  //Handle errors for Class.forName
		  e.printStackTrace();
	   }finally{
		  //finally block used to close resources
		  try{
			 if(stmt!=null)
				stmt.close();
		  }catch(SQLException se2){
		  }// nothing we can do
		  try{
			 if(conn!=null)
				conn.close();
		  }catch(SQLException se){
			 se.printStackTrace();
		  }//end finally try
	   }//end try
		  return null;
   }
   
   
   	public static List<Ticket> getQueuedTickets(int eID){
		Connection conn = null;
		Statement stmt = null;
		List<Ticket> list = new Vector<Ticket>();
		try{
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(DB_URL,USER,PASS);
			stmt = conn.createStatement();
			String sql; 
			sql = "SELECT * FROM Ticket WHERE StateFlag = 1 AND EngineerID = "+eID;
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()){
				list.add(new Ticket(rs.getInt("TicketID"),rs.getInt("UserID"),rs.getInt("EngineerID"),rs.getInt("IssueID"),(rs.getTimestamp("TimeEntered").getTime()),rs.getInt("StateFlag"),rs.getString("ProblemDescription"),rs.getString("Screenshot"),(rs.getTimestamp("TimeEntered").getTime()),rs.getInt("NoOfReferrals"),rs.getString("RefferalHistory"),rs.getInt("Priority")));
			}
		rs.close();
        stmt.close();
        conn.close(); 
         return list;	
		}//try
		catch(SQLException se){
		  //Handle errors for JDBC
		  se.printStackTrace();
	   }catch(Exception e){
		  //Handle errors for Class.forName
		  e.printStackTrace();
	   }finally{
		  //finally block used to close resources
		  try{
			 if(stmt!=null)
				stmt.close();
		  }catch(SQLException se2){
		  }// nothing we can do
		  try{
			 if(conn!=null)
				conn.close();
		  }catch(SQLException se){
			 se.printStackTrace();
		  }//end finally try
	   }//end try
		  return null;
   }
   
   
   
   
   	public static List<Ticket> getAllManagersTickets(int mID){
		Connection conn = null;
		Statement stmt = null;
		List<Ticket> list = new Vector<Ticket>();
		try{
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(DB_URL,USER,PASS);
			stmt = conn.createStatement();
			String sql; 
			sql = "SELECT * FROM Ticket WHERE (StateFlag = 2 OR StateFlag = 2) AND Engineer.ManagerID = Manager.ManagerID  AND Manager.ManagerID = "+mID;
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()){
				list.add(new Ticket(rs.getInt("TicketID"),rs.getInt("UserID"),rs.getInt("EngineerID"),rs.getInt("IssueID"),(rs.getTimestamp("TimeEntered").getTime()),rs.getInt("StateFlag"),rs.getString("ProblemDescription"),rs.getString("Screenshot"),(rs.getTimestamp("TimeEntered").getTime()),rs.getInt("NoOfReferrals"),rs.getString("RefferalHistory"),rs.getInt("Priority")));
			}
		rs.close();
        stmt.close();
        conn.close(); 
         return list;	
		}//try
		catch(SQLException se){
		  //Handle errors for JDBC
		  se.printStackTrace();
	   }catch(Exception e){
		  //Handle errors for Class.forName
		  e.printStackTrace();
	   }finally{
		  //finally block used to close resources
		  try{
			 if(stmt!=null)
				stmt.close();
		  }catch(SQLException se2){
		  }// nothing we can do
		  try{
			 if(conn!=null)
				conn.close();
		  }catch(SQLException se){
			 se.printStackTrace();
		  }//end finally try
	   }//end try
		  return null;
   }


	public static int getEngineerID(int pID){
		Connection conn = null;
		Statement stmt = null;
		int engID;
		try{
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(DB_URL,USER,PASS);
			stmt = conn.createStatement();
			String sql; 
			sql="SELECT * FROM Engineer WHERE PersonID = "+pID;
			ResultSet rs = stmt.executeQuery(sql);
			rs.first();
			engID=rs.getInt("EngineerID");
			rs.close();
			stmt.close();
			conn.close(); 
			return engID;
		}//try
		catch(SQLException se){
		  //Handle errors for JDBC
		  se.printStackTrace();
	   }catch(Exception e){
		  //Handle errors for Class.forName
		  e.printStackTrace();
	   }finally{
		  //finally block used to close resources
		  try{
			 if(stmt!=null)
				stmt.close();
		  }catch(SQLException se2){
		  }// nothing we can do
		  try{
			 if(conn!=null)
				conn.close();
		  }catch(SQLException se){
			 se.printStackTrace();
		  }//end finally try
	   }//end try
		return -1;
	}
	
	public static int getManagerID(int pID){
		Connection conn = null;
		Statement stmt = null;
		int manID;
		try{
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(DB_URL,USER,PASS);
			stmt = conn.createStatement();
			String sql; 
			sql="SELECT * FROM Manager WHERE PersonID = "+pID;
			ResultSet rs = stmt.executeQuery(sql);
			rs.first();
			manID=rs.getInt("ManagerID");
			rs.close();
			stmt.close();
			conn.close(); 
			return manID;
		}//try
		catch(SQLException se){
		  //Handle errors for JDBC
		  se.printStackTrace();
	   }catch(Exception e){
		  //Handle errors for Class.forName
		  e.printStackTrace();
	   }finally{
		  //finally block used to close resources
		  try{
			 if(stmt!=null)
				stmt.close();
		  }catch(SQLException se2){
		  }// nothing we can do
		  try{
			 if(conn!=null)
				conn.close();
		  }catch(SQLException se){
			 se.printStackTrace();
		  }//end finally try
	   }//end try
		return -1;
	}





   
	
	public static String getIssueName(int iID){
		Connection conn = null;
		Statement stmt = null;
		String name;
		try{
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(DB_URL,USER,PASS);
			stmt = conn.createStatement();
			String sql; 
			sql="SELECT * FROM IssueType WHERE IssueID = "+iID;
			ResultSet rs = stmt.executeQuery(sql);
			rs.first();
			name=rs.getString("IssueName");
			rs.close();
			stmt.close();
			conn.close(); 
			return name;
		}//try
		catch(SQLException se){
		  //Handle errors for JDBC
		  se.printStackTrace();
	   }catch(Exception e){
		  //Handle errors for Class.forName
		  e.printStackTrace();
	   }finally{
		  //finally block used to close resources
		  try{
			 if(stmt!=null)
				stmt.close();
		  }catch(SQLException se2){
		  }// nothing we can do
		  try{
			 if(conn!=null)
				conn.close();
		  }catch(SQLException se){
			 se.printStackTrace();
		  }//end finally try
	   }//end try
		return "ERROR!";
	
	
	
	}
	
	
	
	
	

	
	

   //main method for testing only TO BE REMOVED..
   public static void main(String[] args) {
      session nsess = login(1,"1234");
      System.out.println("USER TYPE IS:  "+nsess.getUserType());
      List<Engineer> engineers = getManagersEngineers(1);

      for (int i=0; i<engineers.size(); i++){
            System.out.println(engineers.get(i).getEngineerID()+engineers.get(i).getForename());
      }

      List<User> users = getAllUsers();

      System.out.println("USERS:  ");
         
      for (int i=0; i<users.size(); i++){
            System.out.println(users.get(i).getUserID()+users.get(i).getForename());
      }
	  
	  List<Ticket> tickets = getOpenTickets(1);
	  
	  
	  System.out.println("Tickets for Engineer 1:  ");
	   for (int i=0; i<tickets.size(); i++){
            System.out.println(tickets.get(i).getTicketID()+"    "+tickets.get(i).getProblemDesc());
      }
	  


   }//end main







}//end Class
