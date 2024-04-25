import java.io.*;
import java.util.*;
/**
 * ClientInterface.java
 *
 * This interface is for Client.java. Outlines the methods to be used in the Client class.
 *
 * <p>Purdue University -- CS18000 -- Spring 2024 -- Team Project
 *
 * @author LO4-Team 2
 * @version Sun Mar 31st, 2024
 */
public interface ClientInterface {
    public String createUser(Scanner scan, BufferedReader reader, PrintWriter writer);

    //Sign in -- will authenticate through database and create a new serversocket for the user
    public String signIn(Scanner scan, BufferedReader reader, PrintWriter writer);

    //Search Methods
    public void userSearch(Scanner scan, BufferedReader reader, PrintWriter writer);

    //User Methods - sends username to server
    public void viewProfile(Scanner scan, BufferedReader reader, PrintWriter writer);
    public boolean friendUser(Scanner scan, BufferedReader reader, PrintWriter writer);
    public boolean unfriendUser(Scanner scan, BufferedReader reader, PrintWriter writer);
    public boolean blockUser(Scanner scan, BufferedReader reader, PrintWriter writer);
    public boolean unblockUser(Scanner scan, BufferedReader reader, PrintWriter writer);

    //Feed Methods
    public boolean getFeed(Scanner scan, BufferedReader reader, PrintWriter writer);

    //Post Methods - Post Class
    public boolean createPost(Scanner scan, BufferedReader reader, PrintWriter writer);

    //Comment Methods - Comment Class
    public boolean createComment(Scanner scan, BufferedReader reader, PrintWriter writer);


    //Comment + Post interaction methods
    public boolean like(Scanner scan, BufferedReader reader, PrintWriter writer);
    public boolean dislike(Scanner scan, BufferedReader reader, PrintWriter writer);
    public boolean hide(Scanner scan, BufferedReader reader, PrintWriter writer);
    public boolean edit(Scanner scan, BufferedReader reader, PrintWriter writer);

}
