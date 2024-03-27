public interface UserInterface {
    void createUser(String username, String password);

    void searchUser(String username);

    boolean authenticateUser(String username, String password);

    void addFriend(String username);

    void removeFriend(String username);

    void blockUser(String username);

    void unblockUser(String username);


}
