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

        // Test likes and dislikes for comments
        assertTrue(comment1.like("user1"));
        assertTrue(comment1.dislike("user2"));
    }
}
