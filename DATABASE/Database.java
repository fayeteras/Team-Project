//(Noah) for future reference, i made this. anyone who makes big changes add your name in a comment.
//(savni_ - made a few changes to fix the methdds
import java.io.*;
import java.util.ArrayList;

public class Database implements DatabaseInterface {
    private final String filename;
    
    public Database() {
        filename = "allUsers.txt";
    }

    // Add user to the database file
    public synchronized boolean addUser(String username, String password) {
        if (userExists(username)) {
            return false;
        }
        try (FileWriter fw = new FileWriter(filename, true);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter pw = new PrintWriter(bw)) {
            pw.println(username + "," + password);
            // No need to create a new User instance here unless it performs some side effects
            return true;
        } catch (IOException ex) {
            ex.printStackTrace(); // Print error details for debugging
            return false;
        }
    }

    // Check if user exists in the database file
    public synchronized boolean userExists(String username) {
        File file = new File(filename);
        if (!file.exists()) {
            return false; // File doesn't exist, so user can't exist
        }
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.startsWith(username + ",")) {
                    return true;
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace(); // Print error details for debugging
        }
        return false;
    }

    // Authenticate user credentials
    public synchronized boolean authenticateUser(String username, String password) {
        File file = new File(filename);
        if (!file.exists()) {
            return false; // File doesn't exist, so user can't be authenticated
        }
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.startsWith(username + ",")) {
                    String storedPassword = line.substring(line.indexOf(",") + 1).trim(); // Corrected substring usage
                    return storedPassword.equals(password.trim()); // Compare trimmed passwords
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace(); // Print error details for debugging
        }
        return false;
    }

    // Utility method to write data to a file
    public synchronized static boolean writeFile(File filename, ArrayList<String> array) {
        try (FileOutputStream fos = new FileOutputStream(filename, false);
             PrintWriter writer = new PrintWriter(fos)) {
            for (String line : array) {
                writer.println(line);
            }
            return true;
        } catch (IOException e) {
            e.printStackTrace(); // Print error details for debugging
            return false;
        }
    }
}
