import java.util.*;
public class Ticket
{
	private int ticketID;
	private int userID;
	private int engineerID;
	private int issueID;
	private int stateFlag;//Queued:1 Open:2 Completed:3 or Aborted:4
	private String problemDescription;
	private int noOfReferrals;
	private String referralHistory;
	private int priority;
	private String screenshot; 
	private long timeEntered; 
	private long timeCompleted; 
	
	public Ticket(int tID, int uID, int eID, int iID, long timeE, int sFlag, String pDesc, String screenShot, long timeC, int noOfRef, String refHist, int priority){
	
		this.ticketID=tID;
		this.userID=uID;
		this.engineerID=eID;
		this.issueID=iID;
		this.stateFlag=sFlag;
		this.problemDescription=pDesc;
		this.noOfReferrals=noOfRef;
		this.referralHistory=refHist;
		this.priority=priority;
		this.screenshot=screenShot; 
		this.timeEntered=timeE;
		this.timeCompleted=timeC;
	

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
	
	public String getProblemDesc()
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
	
	public long getTimeCompleted()
	{
		return timeCompleted;
	}
	
	public String getIssueName()
	{
		return mySqlConnector.getIssueName(issueID);
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