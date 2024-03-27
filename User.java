public class User implements UserInterface {
    private String username;
    private String password;
    private String[] friends;
    private boolean[] blockedUsers;
    private int friendCount;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.friends = new String[100];
        this.blockedUsers = new boolean[100];
        this.friendCount = 0;
    }


    public void createUser(String username, String password) {
//implement
    }


    public void searchUser(String username) {
        // Implementation to search for a user
    }


    public boolean authenticateUser(String username, String password) {
        return this.username.equals(username) && this.password.equals(password);
    }
    
    public void addFriend(String friendUsername) {
        if (friendCount < friends.length) {
            friends[friendCount++] = friendUsername;
        } else {
            System.out.println("Cannot add more friends. Friend limit reached.");
        }
    }


    public void removeFriend(String friendUsername) {
//implement
        }


    public void blockUser(String username) {
      //implement
    }


    public void unblockUser(String username) {
//implement
    }
    
    // other methods

    public String getUsername() {
        return username;
    }

    public String[] getFriends() {
        return friends;
    }

    public boolean[] getBlockedUsers() {
        return blockedUsers;
    }

    public int getFriendCount() {
        return friendCount;
    }
}

