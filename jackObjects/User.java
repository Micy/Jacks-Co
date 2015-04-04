import java.util.*;
public class User extends Person
{
	private int userID;
	
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