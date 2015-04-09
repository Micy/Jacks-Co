import java.util.*;
public class IssueType
{
	private int issueID;
	private String issueName;
	private int expertiseID;
	private int issuePriority;
	
	/////Methods to get variables/////
	public int getIssueID()
	{
		return issueID;
	}
	
	public Object getIssueName()
	{
		return issueName;
	}
	
	public int getExpertiseID()
	{
		return expertiseID;
	}
	
	public int getIssuePriority()
	{
		return issuePriority;
	}
	
	/////Methods to set variables/////
	private void setIssueID(int IssueID)
	{
		this.issueID = issueID;
	}
	
	private void setIssueName(String issueName)
	{
		this.issueName = issueName;
	}
	
	private void setExpertiseID(int expertiseID)
	{
		this.expertiseID = expertiseID;
	}
	
	private void setIssuePriority(int issuePriority)
	{
		this.issuePriority = issuePriority;
	}
	
}