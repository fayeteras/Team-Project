//(savni)
public class DatabaseTest {

    public static void main(String[] args) {
        // Create a Database object
        Database database = new Database();

        // Test adding a user
        System.out.println("Adding user: " + database.addUser("user1", "password")); // Should return true

        // Test checking if a user exists
        System.out.println("User exists: " + database.userExists("user1")); // Should return true

        // Test authenticating a user
        System.out.println("User authentication: " + database.authenticateUser("user1", "password")); // Should return true - but is returning false
        System.out.println("User authentication: " + database.authenticateUser("user1", "wrongpassword")); // Should return false
    }
}
