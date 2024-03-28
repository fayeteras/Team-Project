import java.io.*;
//(Sean) NOTE: I finished this class really late so literally none
//of it has been tested. you're welcome.
public class User implements UserInterface {
    //fields
    private final String username;
    private String password;
    private String[] friendList;
    private String[] blockList;

    //constructor
    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.friendList = null;
        this.blockList = null;
    }

    //getters
    public String getUsername() {
        return username;
    }
    public String getPassword() {
        return password;
    }

    public String[] getFriendList() {
        return friendList;
    }

    public String[] getBlockList() {
        return blockList;
    }

    //methods

    //(Sean) I'm pretty sure createUser and searchUser interact with the user files, so
    //I accessed the allUsers file directly for these.
    //We will probably access the files differently later on, but idk.

    public void createUser() {
        //(Sean) Outputs toString() of current user to the allUsers file.
        try {
            File f = new File("allUsers.txt");
            FileOutputStream fos = new FileOutputStream(f, true);
            PrintWriter pw = new PrintWriter(fos);
            pw.println(this.toString());
            pw.flush();
            pw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //(Sean) These other people in the computer lab I'm in won't stop yapping
    //I'm about to snap
    public String[] searchUsers(String searchText) {
        FileReader fr = null;
        int count = 0;
        String[] result = null;
        //(Sean) attempts to read from the allUsers file
        try {
            fr = new FileReader("allUsers.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        BufferedReader bfr = new BufferedReader(fr);
        try {
            //(Sean) A temporary array will hold the values of users that
            //contain the user input in their username
            String line = bfr.readLine();
            String[] tempArray = new String[200000];
            boolean hasInputString;
            while (line != null) {
                //(Sean) line will contain the full line of the user from allUsers
                //alteredLine will be just the username from the full line
                //of the user.
                String alteredLine = "";
                //(Sean) alteredLine becomes just the username through this parsing
                int currentIndex = 0;
                while (!line.substring(currentIndex, currentIndex + 1).equals("|")) {
                    alteredLine = alteredLine + line.substring(currentIndex, currentIndex + 1);
                }
                //(Sean) alteredLine (the current username) is searched through
                //to see if the user's input matches a substring of alteredLine
                hasInputString = false;
                while (alteredLine.length() > searchText.length()) {
                    if (alteredLine.substring(0, searchText.length()).equals(searchText)) {
                        hasInputString = true;
                    }
                    alteredLine = alteredLine.substring(1);
                }
                //(Sean) if the current username contains the user's input,
                //The entire user string is added to the temporary array
                if (hasInputString) {
                    tempArray[count] = line;
                    count++;
                }
                line = bfr.readLine();
            }
            //(Sean) The array that will be returned is given the appropriate size
            //and filled with the contents of tempArray
            result = new String[count];
            for (int i = 0; i < result.length; i++) {
                result[i] = tempArray[i];
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bfr != null) {
                try {
                    bfr.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        //(Sean) Returns full user string of all matching users
        //Will have to be changed, as passwords shouldn't be returned.
        //or null, if none are found.
        return result;
    }

    //(Sean) Savni's code btw
    public boolean authenticateUser(String username, String password) {
        return this.username.equals(username) && this.password.equals(password);
    }

    //(Sean) Savni started this one, but I ruined everything she tried to do.
    public void addFriend(String friendUsername) {
        //(Sean) friendList may be null, as this could be the user's first friend.
        if (friendList == null) {
            friendList = new String[1];
            friendList[0] = friendUsername;
        }
        //(Sean) Savni smartly set a maximum for a user's friends to be 100.
        //We won't use println for the final product,
        //but some warning must appear if there is over 100 friends.
        if (friendList.length == 100) {
            System.out.println("Cannot add more friends. Friend limit reached.");
        } else {
            //(Sean) My way of appending the friendList to fit the new friend.
            //Copies the existing contents of friendList to a tempArray,
            //adding the new friend to the temp array, and making temp the new friendList.
            String[] tempArray = new String[friendList.length + 1];
            for (int i = 0; i < friendList.length; i++) {
                tempArray[i] = friendList[i];
            }
            tempArray[friendList.length + 1] = friendUsername;
            friendList = tempArray;
        }
    }

    public void removeFriend(String friendUsername) {
        String[] tempArray = new String[friendList.length - 1];
        int count = 0;
        //(Sean) goes through every String in friendList
        //every item that does not match friendUsername is added to a tempArray.
        //lastly, the friendList becomes the tempArray
        for (int i = 0; i < friendList.length; i++) {
            if (!friendList[i].equals(friendUsername)) {
                tempArray[count] = friendList[i];
            } else {
                count++;
            }
        }
        friendList = tempArray;
    }

    public void blockUser(String blockUsername) {
        //(Sean) blockList may be null, as this could be the user's first blocked user.
        if (blockList == null) {
            blockList = new String[1];
            blockList[0] = blockUsername;
        } else {
            //(Sean) My way of appending the blockList to fit the new blocked user.
            //Copies the existing contents of blockList to a tempArray,
            //adding the new blocked user to the temp array, and making temp the new blockList.
            String[] tempArray = new String[blockList.length + 1];
            for (int i = 0; i < blockList.length; i++) {
                tempArray[i] = blockList[i];
            }
            tempArray[blockList.length + 1] = blockUsername;
            blockList = tempArray;
        }
    }

    public void unblockUser(String blockUsername) {
        String[] tempArray = new String[blockList.length - 1];
        int count = 0;
        //(Sean) goes through every String in blockList
        //every item that does not match blockUsername is added to a tempArray.
        //lastly, the blockList becomes the tempArray
        for (int i = 0; i < blockList.length; i++) {
            if (!blockList[i].equals(blockUsername)) {
                tempArray[count] = blockList[i];
            } else {
                count++;
            }
        }
        blockList = tempArray;

    }

    public String toString() {
        String allFriends = "";
        String allBlocked = "";
        //(Sean) Takes every friend in the friendList array
        //and puts it in one single String
        for (int i = 0; i < friendList.length; i++) {
            allFriends = allFriends + friendList[i] + ";";
        }
        allFriends = allFriends.substring(0, allFriends.length() - 1);
        //(Sean) Same thing as above, except for blockList
        for (int i = 0; i < blockList.length; i++) {
            allBlocked = allBlocked + blockList[i] + ";";
        }
        allBlocked = allBlocked.substring(0, allBlocked.length() - 1);

        return String.format("%s|%s|%s|%s", username, password, allFriends, allBlocked);
    }
}
