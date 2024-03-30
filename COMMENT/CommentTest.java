import org.junit.Test;
import static org.junit.Assert.*;

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
        assertTrue(comment1.likes("user1"));
        assertFalse(comment1.dislikes("user2"));
        assertFalse(comment2.likes("user1"));
        assertTrue(comment2.dislikes("user2"));
    }
}
