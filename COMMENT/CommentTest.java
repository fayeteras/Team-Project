import org.junit.Test;
import static org.junit.Assert.*;
/**
 * CommentTest.java
 *
 * Testing class that uses the JUnit library to ensure that the multiple 
 * Comment constructors and getParent() method function as intended.
 *
 * <p>Purdue University -- CS18000 -- Spring 2024 -- Team Project
 *
 * @author LO4-Team 2
 * @version Sat March 30th, 2024
 */
public class CommentTest {

    @Test(timeout = 1000)
    public void testComment() {
        // Create a parent post
        Post parentPost1 = new Post("user1", "Hello World!", "post1");

        // Test case for constructor with text
        Comment comment1 = new Comment("user2", "comment1", "This is a comment", parentPost1);
        assertEquals("This is a comment", comment1.getText());
        assertEquals("Hello World!", comment1.getParent().getText());

        // Test case for constructor with file
        Post parentPost2 = new Post("user1", "post1");
        Comment comment2 = new Comment("user2", "comment2", parentPost2);
        assertEquals("Text of comment2: ", comment2.getText());
        assertEquals("Parent of comment2: ", comment2.getParent().getText());

        // Test getParent method
        assertEquals("Parent of comment1: ", comment1.getParent().getText());
        assertEquals("Parent of comment2: ", comment2.getParent().getText());

        // Test likes and dislikes for comments
        assertTrue(comment1.like("user1"));
        assertFalse(comment1.dislike("user2"));
        assertFalse(comment2.like("user1"));
        assertTrue(comment2.dislike("user2"));
    }
}
