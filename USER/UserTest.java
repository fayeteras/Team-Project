import static org.junit.Assert.*;
import org.junit.Test;
import java.util.ArrayList;
import java.io.*;
/**
 * UserTest.java
 *
 * <p>Purdue University -- CS18000 -- Spring 2024 -- Team Project
 *
 * @author Team 2
 * @version Mon April 1st, 2024
 */
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
    public void testIsFriend() {
        User user = new User("testUser1");
        user.addFriend("friend1");
        assertTrue(user.isFriend("friend1"));
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
    @Test
    public void testReadFile() {
        User user = new User("testUser");
        ArrayList<String> testList = new ArrayList<>();
        testList.add("friend1");
        testList.add("friend2");
        testList.add("friend3");

        // Write test data to file
        File testFile = new File("test_readFile.txt");
        try (PrintWriter writer = new PrintWriter(testFile)) {
            for (String item : testList) {
                writer.println(item);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Read file and compare contents
        assertTrue(user.readFile(testFile, user.getFriendList()));
        assertEquals(testList, user.getFriendList());

        // Clean up
        testFile.delete();
    }

    @Test
    public void testWriteFile() {
        User user = new User("testUser");
        ArrayList<String> testList = new ArrayList<>();
        testList.add("friend1");
        testList.add("friend2");
        testList.add("friend3");

        // Write test data to file
        File testFile = new File("test_writeFile.txt");
        assertTrue(user.writeFile(testFile, testList));

        // Read file and compare contents
        ArrayList<String> resultList = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(testFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                resultList.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        assertEquals(testList, resultList);

        // Clean up
        testFile.delete();
    }
    @Test
    public void testIsBlocked() {
        User user = new User("testUser");
        user.blockUser("blockedUser");
        assertTrue(user.isBlocked("blockedUser"));
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
