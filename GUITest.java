import org.junit.Test;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import static org.junit.Assert.assertTrue;

public class GUITest {

    Client client;

    @Test
    public void testRecordLikeDislikePost() {
        // Create an instance of GUI
        GUI gui = new GUI("testuser", client);

        // Call the method under test
        gui.recordLikeDislikePost("Test post", "Like");

        // Verify that the action is correctly recorded in the file
        assertTrue("Test recording like action for post", checkFileContains("likedislikePosts.txt", "Test post,Like"));
    }

    @Test
    public void testRecordLikeDislikeComment() {
        // Create an instance of GUI
        GUI gui = new GUI("testUser", client);

        // Call the method under test
        gui.recordLikeDislike("Test comment", "Like");

        // Verify that the action is correctly recorded in the file
        assertTrue("Test recording like action for comment", checkFileContains("likedislikeComments.txt", "Test comment,Like"));
    }

    // Helper method to check if a file contains a specific string
    private boolean checkFileContains(String filePath, String searchString) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.contains(searchString)) {
                    return true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}

