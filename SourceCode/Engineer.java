import java.util.*;
public class Engineer extends Person 
{
	private int engineerID;
	private int expertiseID;
	private int managerID;
	private int availability;
	
	/////Constructor to get managerID/////

	public Engineer(int eID, int expID, int aval,  int mid, int pid, String title, String forename, String surname, String email, String phoneN, String password, String salt)
	{	
		super(pid, title, forename, surname, email, phoneN, password, salt);
		engineerID=eID;
		expertiseID=expID;
		availability = aval;
		managerID = mid;
		
	}

	
	
	/////Methods to get variables/////
	public int getEngineerID()
	{
		return engineerID;
	}
	
	public int getExpertiseID()
	{
		return expertiseID;
	}
	
	public int getManagerID()
	{
		return managerID;
	}
	
	public int getAvailability()
	{
		return availability;
	}
	
	
	/////Methods to set variables/////
	private void setEngineerID(int engineerID)
	{
		this.engineerID = engineerID;
	}
	
	private void setExpertiseID(int expertiseID)
	{
		this.expertiseID = expertiseID;
	}
	
	private void setManagerID(int managerID)
	{
		this.managerID = managerID;
	}
	
	private void setAvailability(int availability)
	{
		this.availability = availability;
	}
	
}