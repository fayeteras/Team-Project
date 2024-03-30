import org.junit.Test;
import static org.junit.Assert.*;

public class PostTest {

    @Test(timeout = 1000)
    public void testConstructorWithText() {
        Post post1 = new Post("user1", "Hello World!", "post1");
        assertEquals("Hello World!", post1.getText());
    }

    @Test(timeout = 1000)
    public void testEditPost() {
        Post post1 = new Post("user1", "Hello World!", "post1");
        post1.editPost("Edited post1 text");
        assertEquals("Edited post1 text", post1.getText());
    }

    @Test(timeout = 1000)
    public void testLikes() {
        Post post1 = new Post("user1", "Hello World!", "post1");
        assertTrue(post1.likes("user2"));
    }

    @Test(timeout = 1000)
    public void testDislikes() {
        Post post1 = new Post("user1", "Hello World!", "post1");
        assertTrue(post1.dislikes("user3"));
    }

    @Test(timeout = 1000)
    public void testDeletePost() {
        Post post1 = new Post("user1", "Hello World!", "post1");
        assertTrue(post1.deletePost());
    }

    @Test(timeout = 1000)
    public void testConstructorWithFile() {
        Post post2 = new Post("user2", "post2");
        assertNotNull(post2.getText());
    }

    @Test(timeout = 1000)
    public void testHidePost() {
        Post post2 = new Post("user2", "post2");
        post2.hidePost();
        assertTrue(post2.isHidden);
    }
}
