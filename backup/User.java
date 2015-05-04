import java.util.*;
public class User extends Person
{
	private int userID;

	//Constructor//
	public User(int uID, int pid, String title, String forename, String surname, String email, String phoneN, String password, String salt){
		super(pid, title, forename, surname, email, phoneN, password, salt);
		userID=uID;
	}
	
	/////Methods to get variables/////
	public int getUserID()
	{
		return userID;
	}
	
	/////Methods to set variables/////
	private void setUserID(int userID)
	{
		this.userID = userID;
	}
}