import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JFrame;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public interface GUIInterface {
    // Method to view posts
    void viewPosts(User currentUser, Post post);

    // Method to record like or dislike for a comment
    void recordLikeDislike(String commentText, String action);

    // Method to create a comment for a post
    boolean createComment(String commentText, String username, Post post);

    // Method to delete a comment
    boolean deleteComment(String commentText, String currentUser);

    // Method to create a post
    boolean createPost(String postText, String username);

    // Method to view an individual user profile
    JFrame viewProfilePanel(User viewUser);
    
    // Method to search for a user
    void search();

    // Method to record like or dislike for a post
    void recordLikeDislikePost(String postText, String action);

    // Panel for an individual user's post
    JPanel UserPostPanel(Post post);

    // Panel to display all posts
    JScrollPane AllPostsPanel(ArrayList<String[]> allPosts, Post post);
}
