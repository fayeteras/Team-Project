import java.util.*;

/**
 * PostInterface.java
 *
 * This is an interface used to call methods in the Post class.
 *
 * <p>Purdue University -- CS18000 -- Spring 2024 -- Team Project
 *
 * @author LO4-Team 2
 * @version Sat March 30th, 2024
 */

public interface PostInterface {
    //(Savni) Takes a post and increments the like total of it
    public boolean like(String username);

    //(Faye) getters
    public String getUsername();
    public int getLikesCount();
    public int getDislikesCount();
    public int[] getTime();
    public ArrayList<String> getLikesList();
    public ArrayList<String> getDislikesList();
    public ArrayList<String> getHidden();
    public boolean isEdited();

    //(Savni) Takes a post and increments the dislike total of it
    public boolean dislike(String username);
    public String getText();

    //(Savni) Takes a post and deletes it
    //Note that this should only be done by the post owner
    public boolean deletePost();

    //(Savni) hides posts
    public boolean hide(String username);

    //(Faye) gets current time stamp (method written by Tyler)
    public int[] getCurrentTime();

    //(Faye) replaces old comment with new text (method written by Noah)
    public void editPost(String newText);

}
