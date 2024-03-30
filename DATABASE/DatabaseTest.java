//(savni)
import org.junit.Test;
import static org.junit.Assert.*;

public class DatabaseTest {
    
    @Test(timeout = 1000)
    public void testAddUser() {
        Database database = new Database();
        assertTrue(database.addUser("TestUser", "TestPassword"));
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
}
