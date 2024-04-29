import static org.junit.Assert.*;
import java.io.*;
import java.util.*;
import org.junit.*;
/**
 * ClientTest.java
 *
 * Testing class that uses the JUnit library to ensure that the many methods
 * within the client class interact correctly with a mock Server.
 *
 * <p>Purdue University -- CS18000 -- Spring 2024 -- Team Project
 *
 * @author LO4-Team 2
 * @version Sun Apr 14th, 2024
 */
public class ClientTest {
    @Test(timeout = 1000)
    public void testGetFeed() {
        // Create a mock BufferedReader with server response
        String serverResponse = "get feed" + System.lineSeparator();
        BufferedReader mockReader = new BufferedReader(new StringReader(serverResponse));
        String expectedFeed[] = new String[1];
        expectedFeed[0] = "testPost";

        // Create a StringWriter to capture client output
        StringWriter stringWriter = new StringWriter();
        PrintWriter mockWriter = new PrintWriter(stringWriter);

        try {
            Client testClient = new Client();
            // Call the getFeed method
            ArrayList<String[]> result = testClient.getFeed(null, mockReader, mockWriter);

            // Verify that the method returns true (indicating success)
            for (int i = 0; i < result.size(); i++) {
                assertTrue(result.get(i).equals(expectedFeed));
            }

            // Verify the output matches the expected output
            assertEquals(serverResponse, stringWriter.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test (timeout = 1000)
    public void testCreatePost() {
        String systemIn = "Test String";
        Scanner scan = new Scanner(systemIn);
        StringWriter sw = new StringWriter();
        PrintWriter writer = new PrintWriter(sw);
        BufferedReader br = new BufferedReader(new StringReader("True"));
        try {
            Client testClient = new Client();
            testClient.setWriter(writer);
            testClient.setReader(br);
            boolean tester = testClient.createPost("testUser", "text");
            assertTrue(tester);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test (timeout = 1000)
    public void testLike() {
        String systemIn = "filename";
        Scanner scan = new Scanner(systemIn);
        StringWriter sw = new StringWriter();
        PrintWriter writer = new PrintWriter(sw);

        try {
            Client testClient = new Client();
            boolean tester = testClient.like(scan, null, writer);
            assertTrue(tester);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test (timeout = 1000)
    public void testDislike() {
        String systemIn = "filename";
        Scanner scan = new Scanner(systemIn);
        StringWriter sw = new StringWriter();
        PrintWriter writer = new PrintWriter(sw);

        try {
            Client testClient = new Client();
            boolean tester = testClient.dislike(scan, null, writer);
            assertTrue(tester);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test (timeout = 1000)
    public void testHide() {
        String systemIn = "filename";
        Scanner scan = new Scanner(systemIn);
        StringWriter sw = new StringWriter();
        PrintWriter writer = new PrintWriter(sw);

        try {
            Client testClient = new Client();
            boolean tester = testClient.hide(scan, null, writer);
            assertTrue(tester);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test (timeout = 1000)
    public void testEdit() {
        String systemIn = "filename" + System.lineSeparator() + "Text";
        Scanner scan = new Scanner(systemIn);
        StringWriter sw = new StringWriter();
        PrintWriter writer = new PrintWriter(sw);
        BufferedReader br = new BufferedReader(new StringReader("True"));
        try {
            Client testClient = new Client();
            boolean tester = testClient.edit(scan, br, writer);
            assertTrue(tester);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test (timeout = 1000)
    public void testCreateComment() {
        String systemIn = "filename" + System.lineSeparator() + "Parent" + System.lineSeparator() + "Text";
        Scanner scan = new Scanner(systemIn);
        StringWriter sw = new StringWriter();
        PrintWriter writer = new PrintWriter(sw);
        BufferedReader br = new BufferedReader(new StringReader("True"));
        try {
            Client testClient = new Client();
            boolean tester = testClient.createPost("testUser", "text");
            //Assets that the improperly formatted comment cannot be created
            assertFalse(tester);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
