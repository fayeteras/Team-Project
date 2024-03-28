import java.io.*;
import java.util.ArrayList;

//(Sean) NOTE: I finished this class really late so literally none
//of it has been tested. you're welcome.
public class User implements UserInterface {
    //fields
    private final String username;
    private String password;
    private ArrayList<String> friendList;
    private ArrayList<String> blockList;
    private File friendsFile;
    private File blockedFile;


    //constructor
    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.friendList = null;
        this.blockList = null;
        this.friendsFile = new File(username + "_Friends.txt");
        this.blockedFile = new File(username + "_Blocked.txt");
    }

    //
    public void addFriend(String friendName) {
        friendList.add(friendName);
    }

    public void writeToFile(String username, String filename, String input) {
        try (FileOutputStream fos = new FileOutputStream(filename);
             PrintWriter writer = new PrintWriter(fos)) {
            writer.println(" " + input);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getUsername() {
        return username;
    }

    public ArrayList<String> getFriendList() {
        return friendList;
    }

    public ArrayList<String> getBlockList() {
        return blockList;
    }

    public boolean isFriend(String username) {
        return friendList.contains(username)
    }

    public boolean isBlocked(String username) {
        return blockList.contains(username);
    }

    //methods

    //(Sean) I'm pretty sure createUser and searchUser interact with the user files, so
    //I accessed the allUsers file directly for these.
    //We will probably access the files differently later on, but idk.

    /* (Noah) this should be in the database class.
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
    */

    //(Sean) These other people in the computer lab I'm in won't stop yapping
    //I'm about to snap
//    public String[] searchUsers(String searchText) {
//        FileReader fr = null;
//        int count = 0;
//        String[] result = null;
//        //(Sean) attempts to read from the allUsers file
//        try {
//            fr = new FileReader("allUsers.txt");
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
//        BufferedReader bfr = new BufferedReader(fr);
//        try {
//            //(Sean) A temporary array will hold the values of users that
//            //contain the user input in their username
//            String line = bfr.readLine();
//            String[] tempArray = new String[200000];
//            boolean hasInputString;
//            while (line != null) {
//                //(Sean) line will contain the full line of the user from allUsers
//                //alteredLine will be just the username from the full line
//                //of the user.
//                String alteredLine = "";
//                //(Sean) alteredLine becomes just the username through this parsing
//                int currentIndex = 0;
//                while (!line.substring(currentIndex, currentIndex + 1).equals("|")) {
//                    alteredLine = alteredLine + line.substring(currentIndex, currentIndex + 1);
//                }
//                //(Sean) alteredLine (the current username) is searched through
//                //to see if the user's input matches a substring of alteredLine
//                hasInputString = false;
//                while (alteredLine.length() > searchText.length()) {
//                    if (alteredLine.substring(0, searchText.length()).equals(searchText)) {
//                        hasInputString = true;
//                    }
//                    alteredLine = alteredLine.substring(1);
//                }
//                //(Sean) if the current username contains the user's input,
//                //The entire user string is added to the temporary array
//                if (hasInputString) {
//                    tempArray[count] = line;
//                    count++;
//                }
//                line = bfr.readLine();
//            }
//            //(Sean) The array that will be returned is given the appropriate size
//            //and filled with the contents of tempArray
//            result = new String[count];
//            for (int i = 0; i < result.length; i++) {
//                result[i] = tempArray[i];
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            if (bfr != null) {
//                try {
//                    bfr.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//        //(Sean) Returns full user string of all matching users
//        //Will have to be changed, as passwords shouldn't be returned.
//        //or null, if none are found.
//        return result;
//    }

    //(Sean) Savni's code btw


    //(Faye) I think it makes more since to just write them directly to the file so they're stored efficiently
    //(Sean) Savni started this one, but I ruined everything she tried to do.

    //(Faye) Should be easier to just remove from an arraylist, if returns false we'll show an error that this user
    //is already added/isn't on the list
    public boolean removeFriend(String username) {
        if (friendList.contains(username)) {
            friendList.remove(username);
            return true;
        }
        return false;
    }
    public boolean blockUser(String username) {
        if (!blockList.contains(username)) {
            blockList.add(username);
            return true;
        }
        return false;
    }

    public boolean unblockUser(String username) {
        if (blockList.contains(username)) {
            blockList.remove(username);
            return true;
        }
        return false;
    }

//    public String toString() {
//        String allFriends = "";
//        String allBlocked = "";
//        //(Sean) Takes every friend in the friendList array
//        //and puts it in one single String
//        for (int i = 0; i < friendList.length; i++) {
//            allFriends = allFriends + friendList[i] + ";";
//        }
//        allFriends = allFriends.substring(0, allFriends.length() - 1);
//        //(Sean) Same thing as above, except for blockList
//        for (int i = 0; i < blockList.length; i++) {
//            allBlocked = allBlocked + blockList[i] + ";";
//        }
//        allBlocked = allBlocked.substring(0, allBlocked.length() - 1);
//
//        return String.format("%s|%s|%s|%s", username, password, allFriends, allBlocked);
//    }
    
    
}
