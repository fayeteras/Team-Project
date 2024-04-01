/**
 * DatabaseInterface.java
 *
 * Interface for Database.java
 *
 * <p>Purdue University -- CS18000 -- Spring 2024 -- Team Project
 *
 * @author LO4-Team 2 - Noah Withers
 * @version Mon April 1st, 2024
 */

public interface DatabaseInterface {
    public boolean addUser(String username, String password);

    public boolean userExists(String username);
    
    public boolean authenticateUser(String username, String password);
}
