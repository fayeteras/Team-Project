import org.junit.Test;
import static org.junit.Assert.*;

//test for the helper methods in GUI only - we don't need to test the GUI but if we are using any helper methods we do need to test them
public class GUITest {

    @Test
    public void testCreateComment() {
        GUI gui = new GUI("testUser");
        boolean result = gui.createComment("Test comment", "testUser");
        assertTrue(result); // Check if comment is successfully added
    }

    @Test
    public void testDeleteComment() {
        GUI gui = new GUI("testUser");
        gui.createComment("Test comment", "testUser"); // Create a comment
        boolean result = gui.deleteComment("testUser: Test comment");
        assertTrue(result); // Check if comment is successfully deleted
    }

    @Test
    public void testViewComment() {
        // Assuming there are comments in the file
        GUI gui = new GUI("testUser");
        gui.viewComment(); // Trigger the method
        // Manually verify if the comments are displayed correctly in the dialog
    }

    @Test
    public void testRecordLikeDislike() {
        GUI gui = new GUI("testUser");
        gui.recordLikeDislike("Test comment", "like"); // Record a like
        // Manually verify if the action is recorded correctly in the file
    }
}
