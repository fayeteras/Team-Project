//(savni)
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.*;
/**
 * DatabaseTest.java
 *
 * Contains test cases/methods for testing Database.java
 *
 * <p>Purdue University -- CS18000 -- Spring 2024 -- Team Project
 *
 * @author LO4-Team 2
 * @version Mon April 1st, 2024
 */
public class DatabaseTest {

    @Test(timeout = 1000)
    public void testAddUser() {
        Database database = new Database();
        assertTrue(database.addUser("TestUser", "TestPassword"));
        //Only true on first run, otherwise will try to make duplicate in file
        //and so returns false and prevents that - have to clear "allUsers" first
    }
    
    @Test(timeout = 1000)
    public void testUserExists() {
        Database database = new Database();
        database.addUser("TestUser", "TestPassword");
        assertTrue(database.userExists("TestUser"));
        assertFalse(database.userExists("NonExistentUser"));
    }
    
    @Test(timeout = 1000)
    public void testAuthenticateUser() {
        Database database = new Database();
        database.addUser("TestUser", "TestPassword");
        assertTrue(database.authenticateUser("TestUser", "TestPassword"));
        assertFalse(database.authenticateUser("TestUser", "WrongPassword"));
    }

    @Test(timeout = 1000)
    public void testWriteFile() {
        ArrayList<String> Bob = new ArrayList<String>(
                Arrays.asList("|Bob;", "Li@kes!", "His bananas."));
        File file = new File("allUsers.txt");
        Database database = new Database();
        database.addUser("TestUser", "TestPassword");
        assertTrue(database.writeFile(file, Bob));
    }
}
