import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.*;
import java.util.Scanner;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
public class ClientUserTest {
    //(Sean) Code from a previous HW test case that helped me with the inputs and outputs
    private final PrintStream originalOutput = System.out;
    private final InputStream originalSysin = System.in;
    @SuppressWarnings("FieldCanBeLocal")
    private ByteArrayInputStream testIn;
    @SuppressWarnings("FieldCanBeLocal")
    private ByteArrayOutputStream testOut;
    @Before
    public void outputStart() {
        testOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(testOut));
    }
    @After
    public void restoreInputAndOutput() {
        System.setIn(originalSysin);
        System.setOut(originalOutput);
    }
    private String getOutput() {
        return testOut.toString();
    }
    @SuppressWarnings("SameParameterValue")
    private void receiveInput(String str) {
        testIn = new ByteArrayInputStream(str.getBytes());
        System.setIn(testIn);
    }

    //(Sean) Each of the correct outputs for my methods
    public static final String WELCOME_MESSAGE = "What would you like to do, User?";
    public static final String ENTER_USERNAME_ONE = "Please enter a username.";
    public static final String ENTER_USERNAME_TWO = "Please enter your username.";
    public static final String ENTER_PASSWORD_ONE = "Please enter a password.";
    public static final String ENTER_PASSWORD_TWO = "Please enter your password.";
    public static final String LOGIN_SUCCESS = "Login successful. Welcome ";
    public static final String ACCOUNT_SUCCESS = "Account creation successful. Welcome ";
    public static final String USER_SEARCH_PROMPT = "Please enter the username of who you're looking for.";
    public static final String USER_SEARCH_SUCCESS = "Here is the User you are looking for.";

    @Test(timeout = 1000)
    public void testCreateUser() {
        // Create a mock BufferedReader with expected server response
        String serverResponse = WELCOME_MESSAGE + System.lineSeparator() + ENTER_USERNAME_ONE +
                System.lineSeparator() + ENTER_PASSWORD_ONE + System.lineSeparator() +
                ACCOUNT_SUCCESS + "testUser!" + System.lineSeparator();
        BufferedReader mockReader = new BufferedReader(new StringReader(serverResponse));

        // Create a StringWriter to capture client output
        StringWriter stringWriter = new StringWriter();
        PrintWriter mockWriter = new PrintWriter(stringWriter);

        //Create a mock Scanner to scan the test input;
        try {
            Client testClient = new Client();
            String input = "testUser" + System.lineSeparator() + "testPassword" + System.lineSeparator()
                    + "No" + System.lineSeparator();
            Scanner mockScanner = new Scanner(input);
            // Call the createUser method
            testClient.createUser(mockScanner, mockReader, mockWriter);
            String output = getOutput();

            // Verify the output matches the expected output
            assertEquals(serverResponse, output);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test(timeout = 1000)
    public void testSignIn() {
        // Create a mock BufferedReader with expected server response
        //The createUser method is first, then the signIn method follows to ensure that
        //the test can sign in with this newly created User.
        String serverResponse = WELCOME_MESSAGE + System.lineSeparator() + ENTER_USERNAME_ONE +
                System.lineSeparator() + ENTER_PASSWORD_ONE + System.lineSeparator() +
                ACCOUNT_SUCCESS + "testUser!" + System.lineSeparator() +
                WELCOME_MESSAGE + System.lineSeparator() + ENTER_USERNAME_TWO +
                System.lineSeparator() + ENTER_PASSWORD_TWO + System.lineSeparator() +
                LOGIN_SUCCESS + "testUser!" + System.lineSeparator();
        BufferedReader mockReader = new BufferedReader(new StringReader(serverResponse));

        // Create a StringWriter to capture client output
        StringWriter stringWriter = new StringWriter();
        PrintWriter mockWriter = new PrintWriter(stringWriter);

        //Create a mock Scanner to scan the test input;
        try {
            Client testClient = new Client();
            String input = "testUser" + System.lineSeparator() + "testPassword" + System.lineSeparator()
                    + "No" + System.lineSeparator() + "testUser" + System.lineSeparator()
                    + "testPassword" + System.lineSeparator() + "No" + System.lineSeparator();
            Scanner mockScanner = new Scanner(input);
            // Call the createUser and signIn method
            testClient.createUser(mockScanner, mockReader, mockWriter);
            testClient.signIn(mockScanner, mockReader, mockWriter);
            String output = getOutput();

            // Verify the output matches the expected output
            assertEquals(serverResponse, output);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test(timeout = 1000)
    public void testUserSearch() {
        // Create a mock BufferedReader with expected server response
        //The createUser method is first, then the userSearch method follows to ensure that
        //the test can search up the newly created User.
        String serverResponse = WELCOME_MESSAGE + System.lineSeparator() + ENTER_USERNAME_ONE +
                System.lineSeparator() + ENTER_PASSWORD_ONE + System.lineSeparator() +
                ACCOUNT_SUCCESS + "testUser!" + System.lineSeparator() +
                WELCOME_MESSAGE + System.lineSeparator() + USER_SEARCH_PROMPT +
                System.lineSeparator() + USER_SEARCH_SUCCESS + System.lineSeparator();
        BufferedReader mockReader = new BufferedReader(new StringReader(serverResponse));

        // Create a StringWriter to capture client output
        StringWriter stringWriter = new StringWriter();
        PrintWriter mockWriter = new PrintWriter(stringWriter);

        //Create a mock Scanner to scan the test input;
        try {
            Client testClient = new Client();
            String input = "testUser" + System.lineSeparator() + "testPassword" + System.lineSeparator()
                    + "No" + System.lineSeparator() + "testUser" + System.lineSeparator()
                    + "No" + System.lineSeparator();
            Scanner mockScanner = new Scanner(input);
            // Call the createUser and userSearch method
            testClient.createUser(mockScanner, mockReader, mockWriter);
            testClient.userSearch(mockScanner, mockReader, mockWriter);
            String output = getOutput();

            // Verify the output matches the expected output
            assertEquals(serverResponse, output);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test(timeout = 1000)
    public void testGetFeed() {
        // Create a mock BufferedReader with server response
        String serverResponse = "get feed" + System.lineSeparator();
        BufferedReader mockReader = new BufferedReader(new StringReader(serverResponse));

        // Create a StringWriter to capture client output
        StringWriter stringWriter = new StringWriter();
        PrintWriter mockWriter = new PrintWriter(stringWriter);

        try {
            Client testClient = new Client();
            // Call the getFeed method
            boolean result = testClient.getFeed(null, mockReader, mockWriter);

            // Verify that the method returns true (indicating success)
            assertTrue(result);

            // Verify the output matches the expected output
            assertEquals(serverResponse, stringWriter.toString());
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
}
