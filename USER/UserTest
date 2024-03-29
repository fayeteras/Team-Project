import static org.junit.Assert.*;

import java.io.File;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

public class UserTest {
    private User user;

    @Before
    public void setUp() {
        user = new User("testUser", "testPassword");
    }

    @Test
    public void testGetUsername() {
        assertEquals("testUser", user.getUsername());
    }

    @Test
    public void testAddFriend() {
        assertTrue(user.addFriend("friend1"));
        assertTrue(user.getFriendList().contains("friend1"));
    }

    @Test
    public void testRemoveFriend() {
        user.addFriend("friend2");
        assertTrue(user.removeFriend("friend2"));
        assertFalse(user.getFriendList().contains("friend2"));
    }

    @Test
    public void testBlockUser() {
        assertTrue(user.blockUser("blockedUser"));
        assertTrue(user.getBlockList().contains("blockedUser"));
    }

    @Test
    public void testUnblockUser() {
        user.blockUser("blockedUser2");
        assertTrue(user.unblockUser("blockedUser2"));
        assertFalse(user.getBlockList().contains("blockedUser2"));
    }

    @Test
    public void testCheckFriendsFile() {
        File friendsFile = user.checkFriendsFile();
        assertNotNull(friendsFile);
        assertTrue(friendsFile.exists());
    }

    @Test
    public void testCheckBlockedFile() {
        File blockedFile = user.checkBlockedFile();
        assertNotNull(blockedFile);
        assertTrue(blockedFile.exists());
    }

    @Test
    public void testReadFile() {
        ArrayList<String> arrayList = new ArrayList<>();
        user.readFile(new File("nonexistent_file.txt"), arrayList);
        assertTrue(arrayList.isEmpty());
    }

    @Test
    public void testWriteFile() {
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("testEntry");
        assertTrue(user.writeFile(new File("test_output.txt"), arrayList));
        File file = new File("test_output.txt");
        assertTrue(file.exists());
        file.delete(); // Clean up after the test
    }
}
