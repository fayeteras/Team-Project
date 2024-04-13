import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.io.StringReader;
import java.io.StringWriter;

import org.junit.Test;

public class ClientGetFeedTest {

    @Test(timeout = 1000)
    public void testGetFeed() {
        // Create a mock BufferedReader with server response
        String serverResponse = "get feed\n";
        BufferedReader mockReader = new BufferedReader(new StringReader(serverResponse));

        // Create a StringWriter to capture client output
        StringWriter stringWriter = new StringWriter();
        PrintWriter mockWriter = new PrintWriter(stringWriter);

        try {
            // Call the getFeed method
            boolean result = Client.getFeed(null, mockReader, mockWriter);

            // Verify that the method returns true (indicating success)
            assertTrue(result);

            // Verify the output matches the expected output
            assertEquals(serverResponse, stringWriter.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
