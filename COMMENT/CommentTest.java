//(savni)
import org.junit.Test;
import static org.junit.Assert.*;

public class CommentTest {

    @Test(timeout = 1000)
    public void testGetParent() {
        // Set up a parent post
        Post parentPost = new Post("ParentUser", "ParentText", 1);
        
        // Create a comment
        Comment comment = new Comment("CommentUser", "CommentText", 2, parentPost);

        // Test getParent method
        assertEquals(parentPost, comment.getParent());
    }
}
