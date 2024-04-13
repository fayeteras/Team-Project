/**
 * Comment.java
 *
 * The child class of Post.java. Similar to a Post Object, except 
 * a Comment Object has an additional field to store the parent post for that comment.
 *
 * <p>Purdue University -- CS18000 -- Spring 2024 -- Team Project
 *
 * @author LO4-Team 2 - Noah Withers
 * @version Sat March 30th, 2024
 */
public class Comment extends Post implements CommentInterface {
    private final Post parent;
    private static int totalComments;

    public Comment(String username, String fileName, String text, Post parent) {
        super(username, text, fileName);
        totalComments++;
        this.parent = parent;
    }

    public Comment(String username, String fileName, Post parent) {
        super(username, fileName);
        this.parent = parent;
    }

    public Post getParent() {
        return parent;
    }
    public static int getTotalComments() {return totalComments; }
}
