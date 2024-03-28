public interface UserInterface {
    //(Sean) the getters. If there are more variables you think
    //are needed for the class, don't forget to add the getter.
    public String getUsername();
    public String getPassword();
    public String[] getFriendList();
    public String[] getBlockList();

    //(Sean) **FILE USED**
    //creates a new user by appending the allUsers file
    //to include the new user.
    public void createUser();

    //(Sean) **FILE USED**
    //Uses the username parameter to compare to every entry in the
    //allUsers file, returning the entire user string if found.
    //Of course the entire user string is too much, but providing too
    //much is better than too little.
    //OPTIONAL COMPLEXITY - HW-11 showed how to fetch a line based on a
    //substring of its content. Maybe this method doesn't require a full username,
    //rather a substring.. like "fay" grabs "fayeteras"?
    //Edit: I'm so awesome that I implemented this complexity
    //Please don't duplicate it on your HW-11 or I will find you.
    public String[] searchUsers(String searchText);

    //Returns true or false based on whether or not the username and
    //password parameters match the values of the User object
    public boolean authenticateUser(String username, String password);

    //(Sean) adds a friend String to the friendList array in the user class
    public void addFriend(String friendUsername);

    //(Sean) removes a friend string from the friendList.
    public void removeFriend(String friendUsername);

    //(Sean) Adds a user String to the blockList array in the user class
    //This functions almost the exact same way as addFriend() btw
    public void blockUser(String friendUsername);

    //(Sean) Removes a user string from the blockList.
    //This functions almost the exact same way as removeFriend() btw
    public void unblockUser(String friendUsername);

    //(Sean) Stringafies the fields of the User class
    //Formatting can be seen in allUsers.txt
    public String toString();
}
