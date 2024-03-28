import java.io.*;

//(Savni) not sure about this but this is one way in which it can be implemented
//(Sean) a roadblock that may emerge is that we might be appending our input files in
//these methods, which might be bad. One large output file may be needed.
public interface DatabaseInterface {
    //(Savni) The file used for this method is allUsers.txt
    //(Sean) this method will save all of the user's info to the 
    //allUsers file. Be sure to append the user's info if it already existed.
    public void saveUserInfo(String userInfo) throws IOException;
    
    //(Savni) The file used for this method is userPosts.txt
    //(Sean) this method will save all of the posts to the 
    //userPosts file. Be sure to append the post if it already existed.
    public void savePostInfo(String postInfo) throws IOException;
    
    //(Savni) The file used for this method is userComments.txt
    //(Sean) this method will save all of the comments to the 
    //userComments file. Be sure to append the comment if it already existed.
    public void saveCommentInfo(String commentInfo) throws IOException;

    //(Sean) This method comes with more assumptions than I can count on my fingers.
    //Unless the database contains an array of Users, an array of Posts, and an
    //array of Comments in which each file is read into, there will need to be a lot of 
    //file reading from multiple files to provide the feed for the user. Hope I'm wrong.
    //I put this in the database file, as I assume it fits here the most.
    public Post[] getFeed();
}
