import java.util.*;
public class Manager extends Person
{
	private int managerID;
	
	////// CONSTRUCTOR /////
	public Manager(int mID, int pid, String title, String forename, String surname, String email, String phoneN, String password, String salt){
		super(pid, title, forename, surname, email, phoneN, password, salt);
		managerID=mID;
	}
	
	/////Methods to get variables/////
	public int getManagerID()
	{
		return managerID;
	}
	
	/////Methods to set variables/////
	private void setManagerID(int managerID)
	{
		this.managerID = managerID;
	}
}