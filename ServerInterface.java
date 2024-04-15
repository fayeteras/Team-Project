import java.io.*;
/**
 * ServerInterface.java
 *
 * This interface is for Server.java. Outlines the methods to be used in the Server class.
 *
 * <p>Purdue University -- CS18000 -- Spring 2024 -- Team Project
 *
 * @author LO4-Team 2
 * @version Sun Mar 31st, 2024
 */
public interface ServerInterface {
    public void createUser(Database db, BufferedReader reader, PrintWriter writer);

    //Sign in -- will authenticate through database and create a new serversocket for the user
    public void signIn(Database db, BufferedReader reader, PrintWriter writer);

    //Search Methods
    public void userSearch(Database db, BufferedReader reader, PrintWriter writer);

    //User Methods - sends username to server
    //viewProfile only occurs within userSearch for now
    public boolean viewProfile(Database db, BufferedReader reader, PrintWriter writer, String username);
    public boolean friendUser(Database db, BufferedReader reader, PrintWriter writer);
    public boolean unfriendUser(Database db, BufferedReader reader, PrintWriter writer);
    public boolean blockUser(Database db, BufferedReader reader, PrintWriter writer);
    public boolean unblockUser(Database db, BufferedReader reader, PrintWriter writer);

    //Feed Methods
    public void getFeed(Database db, BufferedReader reader, PrintWriter writer);

    //Post Methods - Post Class
    public boolean createPost(Database db, BufferedReader reader, PrintWriter writer);

    //Comment Methods - Comment Class
    public boolean createComment(Database db, BufferedReader reader, PrintWriter writer);

    //Comment + Post interaction methods
    public boolean likePost(Database db, BufferedReader reader, PrintWriter writer);
    public boolean dislikePost(Database db, BufferedReader reader, PrintWriter writer);
    public boolean hidePost(Database db, BufferedReader reader, PrintWriter writer);
    public boolean editPost(Database db, BufferedReader reader, PrintWriter writer);
    public boolean likeComment(Database db, BufferedReader reader, PrintWriter writer);
    public boolean dislikeComment(Database db, BufferedReader reader, PrintWriter writer);
    public boolean hideComment(Database db, BufferedReader reader, PrintWriter writer);
    public boolean editComment(Database db, BufferedReader reader, PrintWriter writer);

}
