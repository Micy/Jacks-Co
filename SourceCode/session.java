
//session object created upon logging in, used to return the user type, ID and name after the user has logged on
public class session{
	
	private int userType; // 0 = engineer 1 = manager 2 = user or person entered is neither a engineer or manager
	private int userID;
	private String forename;
	private String surname;

	public session(int uType, int uID, String fName, String sName){
		userType=uType;
		userID=uID;
		forename=fName;
		surname=sName;
	}

	public int getUserType(){
		return userType;
	}

	public int getUserID(){
		return userID;
	}

	public String getForename(){
		return forename;
	}
	public String getSurname(){
		return surname;
	}
	public int getEngineerID(){
		if(userType!=0){
		return -1;
		}
		else{
		 return mySqlConnector.getEngineerID(userID);
		}
	}

}