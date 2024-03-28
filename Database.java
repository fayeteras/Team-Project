//(Noah) for future reference, i made this. anyone who makes big changes add your name in a comment.
public class Database implements DatabaseInterface{
	private final String filename;
	
	public Database() {
		filename = "allUsers.txt";
	}

	public boolean addUser(String username, String password) {
		try {
			File f = new File(filename);
			FileOutputStream fos = new FileWriter(f);
			FileWriter fw = new FileWriter(fos);
	
			fw.println(username + "," + password);
	
			fw.close();
			return true;
		} catch (Exception ex) {
			return false;
		}
	}

	public boolean userExists(String username) {
		try {
			File f = new File(filename);
			FileReader fr = new FileReader(f);
			BufferedReader bfr = new BufferedReader(fr);

			String line;
			boolean found = false;
			while(true) {
				line = bfr.nextLine();
				if (line == null)
					break;

				if (line.substring(0, line.indexOf(",")).equals(username)) {
					found = true;
					break;
				}
			}
			bfr.close();
			return found;
		} catch (Exception ex) {
			return false;
		}
	}

	public String authenticateUser(String username) {
		try {
			File f = new File(filename);
			FileReader fr = new FileReader(f);
			BufferedReader bfr = new BufferedReader(fr);

			String line;
			while(true) {
				line = bfr.nextLine();
				if (line == null)
					break;

				if (line.substring(0, line.indexOf(",")).equals(username)) {
					return line.substring(line.indexOf("," + 1)).equals(password);
				}
			}
			bfr.close();
			return null;
		} catch (Exception ex) {
			return null;
		}
	}
	
}
