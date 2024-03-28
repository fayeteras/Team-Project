public class Database implements DatabaseInterface{
	private final String filename;
	
	public Database() {
		filename = "allUsers.txt";
	}

	public boolean addUser(User user) {
		//append to it
	}

	public boolean userExists(String username) {
		//return if the username is in the user list
	}

	public String getPassword(String username) {
		//get the password for the username given
	}
	
}
