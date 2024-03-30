import java.io.*;

//(Savni) not sure about this but this is one way in which it can be implemented
//(Sean) a roadblock that may emerge is that we might be appending our input files in
//these methods, which might be bad. One large output file may be needed.
public interface DatabaseInterface {
    //(Savni) The file used for this method is allUsers.txt
    //(Sean) this method will save all of the user's info to the 
    //allUsers file. Be sure to append the user's info if it already existed.
    //(Noah) commented. perish.
    //public void saveUserInfo(String userInfo) throws IOException;
    
    //(Savni) The file used for this method is userPosts.txt
    //(Sean) this method will save all of the posts to the 
    //userPosts file. Be sure to append the post if it already existed.
    //Posts do not go in the database. commented.
    //public void savePostInfo(String postInfo) throws IOException;
    
    //(Savni) The file used for this method is userComments.txt
    //(Sean) this method will save all of the comments to the 
    //userComments file. Be sure to append the comment if it already existed.
    //(Noah) Comments do not go in the database. commented.
    //public void saveCommentInfo(String commentInfo) throws IOException;

    //(Sean) This method comes with more assumptions than I can count on my fingers.
    //Unless the database contains an array of Users, an array of Posts, and an
    //array of Comments in which each file is read into, there will need to be a lot of 
    //file reading from multiple files to provide the feed for the user. Hope I'm wrong.
    //I put this in the database file, as I assume it fits here the most.
    //(Noah) It does not. commented out.
    //public Post[] getFeed();

    //(Noah) I made the database file. so i'm adding the methods that are in there. The three below are mine. 

    public boolean addUser(String username, String password);

    public boolean userExists(String username);

    //(Sean) you have this method listed in this interface, yet it's not implemented in the Database class
    //Idk if you meant authenticateUser instead of getPassword, but I'll leave it commented out.
    //public String getPassword(String username);

    //(Sean) added this method here bc it exists in the Database class
    public boolean authenticateUser(String username, String password);
}
