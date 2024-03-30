//(Noah) for future reference, i made this. anyone who makes big changes add your name in a comment.

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Database implements DatabaseInterface{
	private final String filename;
	
	public Database() {
		filename = "allUsers.txt";
	}

	public boolean addUser(String username, String password) {
		try {
			File f = new File(filename);
			FileOutputStream fos = new FileOutputStream(f);
			PrintWriter pw = new PrintWriter(fos);
	
			pw.println(username + "," + password);
			new User(username);
	
			pw.close();
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
				line = bfr.readLine();
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

	public boolean authenticateUser(String username, String password) {
		try {
			File f = new File(filename);
			FileReader fr = new FileReader(f);
			BufferedReader bfr = new BufferedReader(fr);

			String line;
			while(true) {
				line = bfr.readLine();
				if (line == null)
					break;

				if (line.substring(0, line.indexOf(",")).equals(username)) {
					return line.substring(line.indexOf("," + 1)).equals(password);
				}
			}
			bfr.close();
			return true;
		} catch (Exception ex) {
			return false;
		}
	}

	//(Noah) Faye wrote this i just moved it here because it makes more sense here with it being used in both user and post.
	//(Faye) this makes it much more difficult to use in User. For now I'm going to have it in user as well, but we can talk about it.
	public static boolean writeFile (File filename, ArrayList<String> array) {
        try (FileOutputStream fos = new FileOutputStream(filename, false);
             PrintWriter writer = new PrintWriter(fos)) {
            for (int i = 0; i < array.size(); i++) {
                writer.write(array.get(i));
                writer.println();
            }
            writer.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
	
}
