import java.io.*;
import java.util.*;
/**
 * User.java
 *
 * This class keeps track of the friends and blocked Users of
 * a User Object through ArrayLists and Files.
 *
 * <p>Purdue University -- CS18000 -- Spring 2024 -- Team Project
 *
 * @author L04 - Team 2
 * @version Mon April 1st, 2024
 */
public class User implements UserInterface {
    // Fields
    private final String username;
    private ArrayList<String> friendList;
    private ArrayList<String> blockList;
    private ArrayList<String> postsList;
    private File friendsFile;
    private File blockedFile;
    private File postsFile;

    // Constructor
    public User(String username) {
        this.username = username;
        this.friendList = new ArrayList<>();
        this.blockList = new ArrayList<>();
        this.postsList = new ArrayList<>();
        this.friendsFile = checkFriendsFile();
        this.blockedFile = checkBlockedFile();
        this.postsFile = checkPostsFile();
    }

    // Method to check existence of friends file
    public File checkFriendsFile() {
        File file = new File(username + "_Friends.txt");
        if (file.exists()) {
            friendsFile = file;
            if (friendList.isEmpty()) {
                readFile(friendsFile, friendList); // Initialize friendList from file
            }
        } else {
            friendsFile = createFile(file); // Create the file if it doesn't exist
        }
        return friendsFile;
    }

    // Method to check existence of blocked users file
    public File checkBlockedFile() {
        File file = new File(username + "_Blocked.txt");
        if (file.exists()) {
            blockedFile = file;
            if (blockList.isEmpty()) {
                readFile(blockedFile, blockList); // Initialize blockList from file
            }
        } else {
            blockedFile = createFile(file); // Create the file if it doesn't exist
        }
        return blockedFile;
    }

    // Method to check existence of posts file
    public File checkBlockedFile() {
        File file = new File(username + "_Posts.txt");
        if (file.exists()) {
            postsFile = file;
            if (postsList.isEmpty()) {
                readFile(postsFile, postsList); // Initialize blockList from file
            }
        } else {
            postsFile = createFile(file); // Create the file if it doesn't exist
        }
        return postsFile;
    }

    // Method to create a file
    private File createFile(File file) {
        try {
            if (file.createNewFile()) {
                return file; // Return the created file
            } else {
                System.err.println("Failed to create file: " + file.getName());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Getter for username
    public String getUsername() {
        return username;
    }

    // Getter for friendList
    public ArrayList<String> getFriendList() {
        return friendList;
    }

    // Getter for blockList
    public ArrayList<String> getBlockList() {
        return blockList;
    }

    public ArrayList<String> getPostsList() {
        return postsList;
    }

    // Method to check if a user is a friend
    public boolean isFriend(String username) {
        return friendList.contains(username);
    }

    // Method to check if a user is blocked
    public boolean isBlocked(String username) {
        return blockList.contains(username);
    }

    // Method to add a friend
    public boolean addFriend(String username) {
        if (!friendList.contains(username)) {
            friendList.add(username);
            return writeFile(friendsFile, friendList); // Write updated friendList to file
        }
        return false;
    }

    // Method to remove a friend
    public boolean removeFriend(String username) {
        if (friendList.contains(username)) {
            friendList.remove(username);
            return writeFile(friendsFile, friendList); // Write updated friendList to file
        }
        return false;
    }

    // Method to block a user
    public boolean blockUser(String username) {
        if (!blockList.contains(username)) {
            blockList.add(username);
            return writeFile(blockedFile, blockList); // Write updated blockList to file
        }
        return false;
    }

    //Method to add a post id to the posts list
    public boolean addPost(String postNumber) {
        if (!postsList.contains(postNumber)) {
            postsList.add(postNumber);
            return writeFile(postsFile, postsList); // Write updated friendList to file
        }
        return false;
    }

    // Method to unblock a user
    public boolean unblockUser(String username) {
        if (blockList.contains(username)) {
            blockList.remove(username);
            return writeFile(blockedFile, blockList); // Write updated blockList to file
        }
        return false;
    }

    // Method to read data from file and initialize an ArrayList
    protected boolean readFile(File filename, ArrayList<String> array) {
        if (filename.exists()) {
            try (FileReader fileReader = new FileReader(filename);
                 BufferedReader bufferedReader = new BufferedReader(fileReader)) {

                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    array.add(line); // Add each line from the file to the ArrayList
                }
                return true;

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    // Method to write data to file from an ArrayList
    public boolean writeFile(File filename, ArrayList<String> array) {
        try (FileOutputStream fos = new FileOutputStream(filename, false);
             PrintWriter writer = new PrintWriter(fos)) {
            for (String item : array) {
                writer.println(item); // Write each item in the ArrayList to the file
            }
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
