import static org.junit.Assert.*;
import org.junit.Test;

//written by Faye :)
import java.io.*;

public class UserTest {

    @Test
    public void testCreateUser() {
        User user = new User("testUser");
        assertNotNull(user);
        assertTrue(user.checkFriendsFile().exists());
        assertTrue(user.checkBlockedFile().exists());
        System.out.println("User Created - Success");
    }


    @Test
    public void testCreateFile() {
        User user = new User("testUser");
        assertNotNull(user.checkFriendsFile());
        assertNotNull(user.checkBlockedFile());
        System.out.println("File Created - Success");
    }

    @Test
    public void testAddFriend() {
        User user = new User("testUser");
        assertTrue(user.addFriend("friend1"));
        assertTrue(user.getFriendList().contains("friend1"));
        // Check if changes are reflected in the file
        assertTrue(checkFileContainsUser("testUser_Friends.txt", "friend1"));
        System.out.println("Friend added - Success");
        System.out.println(user.getFriendList());
    }

    @Test
    public void testRemoveFriend() {
        User user = new User("testUser");
        user.addFriend("friend1");
        assertTrue(user.removeFriend("friend1"));
        assertFalse(user.getFriendList().contains("friend1"));
        assertFalse(checkFileContainsUser("testUser_Friends.txt", "friend1"));
        System.out.println("Friend removed - Success");
    }

    @Test
    public void testBlockUser() {
        User user = new User("testUser");
        assertTrue(user.blockUser("blockedUser"));
        assertTrue(user.getBlockList().contains("blockedUser"));
        // Check if changes are reflected in the file
        assertTrue(checkFileContainsUser("testUser_Blocked.txt", "blockedUser"));
        System.out.println("User blocked - Success");
        System.out.println(user.getBlockList());
    }

    @Test
    public void testUnblockUser() {
        User user = new User("testUser");
        user.blockUser("blockedUser");
        assertTrue(user.unblockUser("blockedUser"));
        assertFalse(user.getBlockList().contains("blockedUser"));
        // Check if changes are reflected in the file
        assertFalse(checkFileContainsUser("testUser_Blocked.txt", "blockedUser"));
        System.out.println("User unblocked - Success");
    }

    private boolean checkFileContainsUser(String filename, String username) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.equals(username)) {
                    return true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}
