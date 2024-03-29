import org.junit.Test;
import static org.junit.Assert.*;

public class UserTest {

    @Test
    public void testAddFriend() {
        User user = new User("testUser", "testPassword");
        assertTrue(user.addFriend("friend1"));
        assertTrue(user.getFriendList().contains("friend1"));
        System.out.println("Friend added successfully");
    }

    @Test
    public void testRemoveFriend() {
        User user = new User("testUser", "testPassword");
        user.addFriend("friend1");
        assertTrue(user.removeFriend("friend1"));
        assertFalse(user.getFriendList().contains("friend1"));
        System.out.println("Friend removed successfully");
    }

    @Test
    public void testBlockUser() {
        User user = new User("testUser", "testPassword");
        assertTrue(user.blockUser("blockedUser"));
        assertTrue(user.getBlockList().contains("blockedUser"));
        System.out.println("User blocked successfully");
    }

    @Test
    public void testUnblockUser() {
        User user = new User("testUser", "testPassword");
        user.blockUser("blockedUser");
        assertTrue(user.unblockUser("blockedUser"));
        assertFalse(user.getBlockList().contains("blockedUser"));
        System.out.println("User unblocked successfully");
    }
}
