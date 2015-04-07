import java.util.*;
public class Person
{
	public int personID;
	public String title;
	public String forename;
	public String surname;
	public String email;
	public String phoneNumber;
	protected String password;
	protected String salt;
	
	/////Methods to get variables/////
	public int getPersonID()
	{
		return personID;
	}
	
	public Object getTitle()
	{
		return title;
	}
	
	public Object getForename()
	{
		return forename ;
	}
	
	public Object getSurname()
	{
		return surname;
	}
	
	public Object getEmail()
	{
		return email;
	}
	
	public Object getPhoneNumber()
	{
		return phoneNumber;
	}
	
	public Object getPassword()
	{
		return password;
	}
	
	public Object getSalt()
	{
		return salt;
	}
	
	
	/////Methods to set variables/////
	private void setPersonID(int personID)
	{
		this.personID = personID;
	}
	
	private void setTitle(String title)
	{
		this.title = title;
	}
	
	private void setForename(String forename)
	{
		this.forename = forename;
	}
	
	private void setSurname(String surname)
	{
		this.surname = surname;
	}
	
	private void setEmail(String email)
	{
		this.email = email;
	}
	
	private void setPhoneNumber(String phoneNumber)
	{
		this.phoneNumber = phoneNumber;
	}
	
	private void setPassword(String password)
	{
		this.password = password;
	}
	
	private void setSalt(String salt)
	{
		this.salt = salt;
	}
}