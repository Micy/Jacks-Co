import java.util.*;
public class Ticket
{
	private int ticketID;
	private int userID;
	private int engineerID;
	private int issueID;
	private int stateFlag;
	private String problemDescription;
	private int noOfReferrals;
	private String referralHistory;
	private int priority;
	private String screenshot; //wasn't sure how to store this
	private long timeEntered; //or this
	private int timeCompleted; //or this

	/////Constructor to get userID, engineerID and issueID/////
	public Ticket(int uid, int eid, int iid)
	{
		userID = uid;
		engineerID = eid;
		issueID = iid;
	}

	public Ticket(){

	}
	
	/////Methods to get variables/////
	public int getTicketID()
	{
		return ticketID;
	}
	
	public int getUserID()
	{
		return userID;
	}
	
	public int getEngineerID()
	{
		return engineerID;
	}
	
	public int getIssueID()
	{
		return issueID;
	}
	
	public int getStateFlag()
	{
		return stateFlag;
	}
	
	public Object getProblemDesc()
	{
		return problemDescription;
	}
	
	public int getNoOfReferrals()
	{
		return noOfReferrals;
	}
	
	public Object getReferralHistory()
	{
		return referralHistory;
	}
	
	public int getPriority()
	{
		return priority;
	}
	
	public  Object getScreenshot()
	{
		return screenshot;
	}
	
	public long getTimeEntered()
	{
		return timeEntered;
	}
	
	public int getTimeCompleted()
	{
		return timeCompleted;
	}
	
	/////Methods to set variables/////
	public void setTicketID(int ticketID)
	{
		this.ticketID = ticketID;
	}
	
	public void setUserID(int userID)
	{
		this.userID = userID;
	}
	
	public void  setEngineerID(int engineerID)
	{
		this.engineerID = engineerID;
	}
	
	public void setIssueID(int issueID)
	{
		this.issueID = issueID;
	}
	
	public void setStateFlag(int stateFlag)
	{
		this.stateFlag = stateFlag;
	}
	
	public void getProblemDesc(String problemDescription)
	{
		this.problemDescription = problemDescription;
	}
	
	public void setNoOfReferrals(int noOfReferrals)
	{
		this.noOfReferrals = noOfReferrals;
	}
	
	public void getReferralHistory(String referralHistory)
	{
		this.referralHistory = referralHistory;
	}
	
	public void setPriority(int priority)
	{
		this.priority = priority;
	}
	
	public void setScreenshot(String screenshot)
	{
		this.screenshot = screenshot;
	}
	
	public void setTimeEntered(long timeEntered)
	{
		this.timeEntered = timeEntered;
	}
	
	public void setTimeCompleted(int timeCompleted)
	{
		this.timeCompleted = timeCompleted;
	}
}