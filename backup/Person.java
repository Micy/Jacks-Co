import java.util.*;
public class Person
{
	private int personID;
	private String title;
	private String forename;
	private String surname;
	private String email;
	private String phoneNumber;
	private String password;
	private String salt;

	Person(int pid, String title, String forename, String surname, String email, String phoneN, String password, String salt){

	this.personID=pid;
	this.title=title;
	this.forename=forename;
	this.surname=surname;
	this.email=email;
	this.phoneNumber=phoneN;
	this.password=password;
	this.salt=salt;

	}
	
	/////Methods to get variables/////
	public int getPersonID()
	{
		return personID;
	}
	
	public String getTitle()
	{
		return title;
	}
	
	public String getForename()
	{
		return forename ;
	}
	
	public String getSurname()
	{
		return surname;
	}
	
	public String getEmail()
	{
		return email;
	}
	
	public String getPhoneNumber()
	{
		return phoneNumber;
	}
	
	public String getPassword()
	{
		return password;
	}
	
	public String getSalt()
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