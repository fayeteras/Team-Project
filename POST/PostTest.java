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
        assertTrue(post1.like("user2"));
    }

    @Test(timeout = 1000)
    public void testDislikes() {
        Post post1 = new Post("user1", "Hello World!", "post1");
        assertTrue(post1.dislike("user3"));
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

    @Test
    public void testGetText() {
        // Create a Post object with a known text
        String username = "user1";
        String text = "Hello World!";
        String fileName = "post1";
        Post post = new Post(username, text, fileName);

        // Get the text using the getText() method
        String retrievedText = post.getText();
        assertEquals("Text retrieved from post should match original text", text, retrievedText);
    }

    @Test(timeout = 1000)
    public void testHidePost() {
        Post post2 = new Post("user2", "post2");
        post2.hide("user2");
    }
}
