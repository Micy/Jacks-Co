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

   // the user logs in using thier personID
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
         System.out.println("user not found");
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
      List<Engineer> list = new ArrayList<Engineer>();
      try{
         Class.forName("com.mysql.jdbc.Driver");
         conn = DriverManager.getConnection(DB_URL,USER,PASS);
         stmt = conn.createStatement();
         String sql;
         sql = "SELECT EngineerID, ExpertiseID, Availability, ManagerID, Person.PersonID, Title, Forename, Surname, Email, PhoneNumber, Password, Salt FROM Person, Engineer WHERE Engineer.PersonID = Person.PersonID AND Engineer.ManagerID = "+mID;
         ResultSet rs = stmt.executeQuery(sql);
         while(rs.next()){
         list.add(new Engineer(rs.getInt("EngineerID"),rs.getInt("ExpertiseID"),rs.getInt("Availability"),rs.getInt("ManagerID"),rs.getInt("PersonID"),rs.getString("Title"),rs.getString("Forename"),rs.getString("Surname"),rs.getString("Email"),rs.getString("PhoneNumber"),rs.getString("Password"),rs.getString("Salt")));
         }
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

   //main method for testing only TO BE REMOVED..
   public static void main(String[] args) {
      session nsess = login(1,"1234");
      System.out.println("USER TYPE IS:  "+nsess.getUserType());
      List<Engineer> engineers = getManagersEngineers(1);

      for (int i=0; i<engineers.size(); i++){
            System.out.println(engineers.get(i).getEngineerID()+engineers.get(i).getForename());
      }


   }//end main







}//end FirstExample
