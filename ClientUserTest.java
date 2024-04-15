import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.*;
import java.util.Scanner;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
//This class tests Sean's methods he made
//Includes tests for userSearch, signIn, and createUser
public class ClientUserTest {
    //Code from a previous HW test case that helped me with the inputs and outputs
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

    // Each of the correct outputs
    public static final String WELCOME_MESSAGE = "What would you like to do, User?";
    public static final String ENTER_USERNAME_ONE = "Please enter a username.";
    public static final String ENTER_USERNAME_TWO = "Please enter your username.";
    public static final String ENTER_PASSWORD_ONE = "Please enter a password.";
    public static final String ENTER_PASSWORD_TWO = "Please enter your password.";
    public static final String LOGIN_SUCCESS = "Login successful. Welcome ";
    public static final String ACCOUNT_SUCCESS = "Account creation successful. Welcome ";
    public static final String INVALID_LOGIN = "Username and/or password is incorrect.\nTry login again? (Yes or No)";
    public static final String ACCOUNT_FAIL = "Your account could not be created at this time." +
            "\nTry again anyways? (Yes or No)";
    public static final String USERNAME_TAKEN = "Sorry, that username is already taken.\nTry again? (Yes or No)";
    public static final String USER_SEARCH_PROMPT = "Please enter the username of who you're looking for.";
    public static final String USER_SEARCH_SUCCESS = "Here is the User you are looking for.";
    public static final String VIEW_PROFILE_QUESTION = "Would you like to view their profile? (Yes or No)";
    public static final String INVALID_USER_SEARCH = "The user you are looking for does not exist." +
            "\nLook for another? (Yes or No)";
    public static final String EXIT_MESSAGE = "Thank you for using My Math Helper!";
    public static final String INVALID_MENU_SELECTION  = "Invalid selection!";
    public static final String INVALID_INPUT = "Invalid Input - Returning to Main Menu";

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
            String input = "testUser" + System.lineSeparator() + "testPassword" + System.lineSeparator()
                    + "No" + System.lineSeparator();
            Scanner mockScanner = new Scanner(input);
            // Call the createUser method
            Client.createUser(mockScanner, mockReader, mockWriter);
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
            String input = "testUser" + System.lineSeparator() + "testPassword" + System.lineSeparator()
                    + "No" + System.lineSeparator() + "testUser" + System.lineSeparator()
                    + "testPassword" + System.lineSeparator() + "No" + System.lineSeparator();
            Scanner mockScanner = new Scanner(input);
            // Call the createUser and signIn method
            Client.createUser(mockScanner, mockReader, mockWriter);
            Client.signIn(mockScanner, mockReader, mockWriter);
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
            String input = "testUser" + System.lineSeparator() + "testPassword" + System.lineSeparator()
                    + "No" + System.lineSeparator() + "testUser" + System.lineSeparator()
                    + "No" + System.lineSeparator();
            Scanner mockScanner = new Scanner(input);
            // Call the createUser and userSearch method
            Client.createUser(mockScanner, mockReader, mockWriter);
            Client.userSearch(mockScanner, mockReader, mockWriter);
            String output = getOutput();

            // Verify the output matches the expected output
            assertEquals(serverResponse, output);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
