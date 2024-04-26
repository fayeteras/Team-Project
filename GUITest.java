import org.junit.Before;
import org.junit.Test;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import static org.junit.Assert.*;

public class GUITest {

    @Test
    public void testCreateComment() {
        GUI gui = new GUI("testUser");
        Post testPost = new Post("John Doe", "This is a test post.", "TestPost");
        boolean result = gui.createComment("Test comment", "testUser", testPost);
        assertTrue(result); // Check if comment is successfully added
    }

    @Test
    public void testDeleteComment() throws IOException {
        GUI gui = new GUI("testUser");
        Post testPost = new Post("John Doe", "This is a test post.", "TestPost");
        gui.createComment("Test comment", "testUser", testPost); // Create a comment
        boolean result = gui.deleteComment("Test comment", "testUser");
        assertTrue(result); // Check if comment is successfully deleted
    }

    @Test
    public void testViewComments() {
        // Assuming there are comments in the file
        GUI gui = new GUI("testUser");
        Post testPost = new Post("John Doe", "This is a test post.", "TestPost");
        gui.viewComments(testPost, gui.user); // Trigger the method
        // Manually verify if the comments are displayed correctly in the dialog
    }

    @Test
    public void testRecordLikeDislike() {
        GUI gui = new GUI("testUser");
        gui.recordLikeDislike("Test comment", "like"); // Record a like
        // Manually verify if the action is recorded correctly in the file
    }
}
