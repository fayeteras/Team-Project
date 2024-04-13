import java.io.*;
import java.util.*;
/**
 * UserInterface.java
 *
 * This is our interface for our User class.
 *
 * <p>Purdue University -- CS18000 -- Spring 2024 -- Team Project
 *
 * @author L04 - Team 2
 * @version Mon April 1st, 2024
 */
public interface UserInterface {
    // Method to retrieve the username of the user
    public String getUsername();

    // Method to retrieve the list of friends of the user
    public ArrayList<String> getFriendList();

    // Method to retrieve the list of blocked users of the user
    public ArrayList<String> getBlockList();

    // Method to check existence of the friends file and initialize friendList
    public File checkFriendsFile();

    // Method to check existence of the blocked users file and initialize blockList
    public File checkBlockedFile();

    // Method to add a friend to the user's friendList
    public boolean addFriend(String username);

    // Method to remove a friend from the user's friendList
    public boolean removeFriend(String username);

    // Method to block a user by adding them to the user's blockList
    public boolean blockUser(String username);

    // Method to unblock a user by removing them from the user's blockList
    public boolean unblockUser(String username);

    // Method to write data to a file
    public boolean writeFile(File filename, ArrayList<String> array);
}
