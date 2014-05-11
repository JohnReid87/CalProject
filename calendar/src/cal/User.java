package cal;

class User {

    static int personID = -1;
	boolean loggedIn = false;
	
	public User(int userId){
		User.personID = userId;
		loggedIn = true;
	}
	
	public static int getUserId(){
		return personID;
	}
	
	
}
