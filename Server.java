
import java.io.*;
import java.net.*;
import java.util.*;
/**
 * Server.java
 *
 * Server that permanently runs to accept the multiple clients that
 * will interact with the social media platform. Uses the methods from
 * the previous classes to help the user interact with the platform.
 *
 * <p>Purdue University -- CS18000 -- Spring 2024 -- Team Project
 *
 * @author LO4-Team 2
 * @version Mon April 15th, 2024
 */
public class Server implements Runnable, ServerInterface {

    private static final int PORT = 620;
    private final Socket socket;
    private final ArrayList<User> userArray = new ArrayList<>();
    private User user;
    public Server(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            System.out.println("please");
            Database db = new Database();
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter writer = new PrintWriter(socket.getOutputStream());
            //userArray.add(new User("Bobby"));
            String command;
            boolean success; //check whether or not the command worked. Idk if this is the way to do it though
            //(Sean) the following code is for the user to sign in or sign up
            //before being able to access any of the features of the social media platform.
            //If this messes with your code, just comment this all out.
            String inOrUp = reader.readLine();
            if (inOrUp.equals("in")) {
                signIn(db, reader, writer);
                System.out.println("signed in");
            } else {
                createUser(db, reader, writer);
            }
            //Everything below should be the same as Phase 2
            /* while (true) {
                writer.write("What would you like to do, User?");
                writer.println();
                writer.flush();

                command = reader.readLine();
                System.out.println("ae");
                //(Sean) I read login as part of the switch.
                // I only saw Faye's terminal edits after finishing my part. sorry Faye
                switch (command) {
                    case "createUser":
                        createUser(db, reader, writer);
                        break;
                    case "signIn":
                        signIn(db, reader, writer);
                        break;
                    case "userSearch":
                        userSearch(db, reader, writer);
                        break;
                    case "signOut":
                        signOut(db, reader, writer);
                        break;
                    case "getFeed":
                        getFeed(db, reader, writer);
                        break;
                    case "friendUser":
                        friendUser(db, reader, writer);
                        break;
                    case "unfriendUser":
                        unfriendUser(db, reader, writer);
                        break;
                    case "blockUser":
                        blockUser(db, reader, writer);
                        break;
                    case "unblockUser":
                        unblockUser(db, reader, writer);
                        break;
                    case "createPost":
                        System.out.println("aeroiu");
                        createPost(db, reader, writer);
                        break;
                    case "likePost":
                        likePost(db, reader, writer);
                        break;
                    case "dislikePost":
                        dislikePost(db, reader, writer);
                        break;
                    case "hidePost": //(Noah)note: i'm sure not all of these need the db parameter but there's a lot to add
                        hidePost(db, reader, writer);
                        break;
                    case "editPost":
                        editPost(db, reader, writer);
                        break;
                    case "createComment":
                        createComment(db, reader, writer);
                        break;
                    case "likeComment":
                        likeComment(db, reader, writer);
                        break;
                    case "dislikeComment":
                        dislikeComment(db, reader, writer);
                        break;
                    case "hideComment":
                        hideComment(db, reader, writer);
                        break;
                    case "editComment":
                        editComment(db, reader, writer);
                        break;
                }
            } */
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (NullPointerException e) {
            throw new NullPointerException("User canceled or exited panel in Client.");
        }
    }

    //(Sean) I experienced some difficulties making methods that function outside of run.
    //Because of this, each method required a Database object, PrintWriter, and BufferedReader as parameters.

    //(Sean) This method takes in the username and password entered by the user and creates an account for them.
    public void createUser(Database db, BufferedReader reader, PrintWriter writer) {
        try {
            String yesOrNo = "Yes";
            while (yesOrNo.equals("Yes")) {
                writer.write("Please enter a username.");
                writer.println();
                writer.flush();
                String username;
                username = reader.readLine();
                if (db.userExists(username)) {
                    writer.write("Sorry, that username is already taken.\nTry again? (Yes or No)");
                    writer.println();
                    writer.flush();
                    yesOrNo = reader.readLine();
                } else {
                    writer.write("Please enter a password.");
                    writer.println();
                    writer.flush();
                    String password;
                    password = reader.readLine();
                    if (db.addUser(username, password)) {
                        User newUser = new User(username);
                        //(Sean) I am unsure what to do with the new User object, so I made
                        //an ArrayList of them. Idk if that's helpful at all.
                        userArray.add(newUser);
                        user = newUser;
                        yesOrNo = "No";
                        writer.write("Account created. Welcome " + username + "! Loading our platform now...");
                        writer.println();
                        writer.flush();
                    } else {
                        writer.write("Your account could not be created at this time." +
                                "\nTry again anyways? (Yes or No)");
                        writer.println();
                        writer.flush();
                        yesOrNo = reader.readLine();
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (NullPointerException e) {
            throw new NullPointerException("User canceled or exited panel in Client.");
        }
    }

    public void signIn(Database db, BufferedReader reader, PrintWriter writer) {
        try {
            String yesOrNo = "Yes";
            while (yesOrNo.equals("Yes")) {
                writer.write("Please enter your username.");
                writer.println();
                writer.flush();
                String username;
                username = reader.readLine();
                writer.write("Please enter your password.");
                writer.println();
                writer.flush();
                String password;
                password = reader.readLine();
                if (db.authenticateUser(username, password)) {
                    yesOrNo = "No";
                    writer.write("Login successful. Welcome " + username + "! Loading our platform now...");
                    user = new User(username);
                    writer.println();
                    writer.flush();
                } else {
                    writer.write("Username and/or password is incorrect.\nTry login again? (Yes or No)");
                    writer.println();
                    writer.flush();
                    yesOrNo = reader.readLine();
                }

            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (NullPointerException e) {
            throw new NullPointerException("User canceled or exited panel in Client.");
        }
    }

    public void userSearch(Database db, BufferedReader reader, PrintWriter writer) {
        try {
            String yesOrNo = "Yes";
            while (yesOrNo.equals("Yes")) {
                writer.write("Please enter the username of who you're looking for.");
                writer.println();
                writer.flush();
                String username;
                username = reader.readLine();
                if (db.userExists(username)) {
                    writer.write("The user has been found!");
                    writer.println();
                    writer.flush();
                    writer.write("Would you like to view their profile? (Yes or No)");
                    writer.println();
                    writer.flush();
                    yesOrNo = reader.readLine();
                    if (yesOrNo.equals("Yes")) {
                        //If this loop is entered, the user would like to
                        //view the profile of the User whose username is the
                        //string variable "username" in this class.
                        viewProfile(db, reader, writer, username);
                        yesOrNo = "No";
                    }
                } else {
                    writer.write("The user you are looking for does not exist." +
                            "\nLook for another? (Yes or No)");
                    writer.println();
                    writer.flush();
                    yesOrNo = reader.readLine();
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void signOut(Database db, BufferedReader reader, PrintWriter writer) {
        this.user = null; //(Noah) yeah i think that's all that's needed here.
    }

    public boolean viewProfile(Database db, BufferedReader reader, PrintWriter writer, String username) {
        if (db.userExists(username)){
            writer.println(username);
            User viewUser = new User(username);
            int friendsCount = viewUser.getFriendList().size();
            writer.println(friendsCount);
            writer.flush();
            ArrayList<Post> posts = viewUser.getPostsList();
            for (int p = 0; p < posts.size(); p++) {
                sendPosts(db, reader, writer, posts.get(p));
            }
            writer.flush();
            return true;
        }
        return false;
    }
    public boolean friendUser(Database db, BufferedReader reader, PrintWriter writer) {
        String username;
        try {
            username = reader.readLine();
            if (db.userExists(username)) {
                if (user.isFriend(username)) {
                    writer.write("REPEAT");
                    writer.println();
                    writer.flush();
                    return false;
                }
                if (user.isBlocked(username)) {
                    writer.write("BLOCKED");
                    writer.println();
                    writer.flush();
                    return false;
                }
                user.addFriend(username);
                writer.write(username + "SUCCESS");
                writer.println();
                writer.flush();
                return true;
            } else {
                writer.write("FAILED");
                writer.println();
                writer.flush();
                return false;
            }

        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean unfriendUser(Database db, BufferedReader reader, PrintWriter writer) {
        String username;
        try {
            username = reader.readLine();
            if (db.userExists(username) && user.isFriend(username)) {
                writer.write("SUCCESS");
                writer.println();
                writer.flush();
            }
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            writer.write("FAILED");
            writer.println();
            writer.flush();
            return false;
        }

    }

    public boolean blockUser(Database db, BufferedReader reader, PrintWriter writer) {
        try {
            String username = reader.readLine();
            if (db.userExists(username)) {
                if (user.isBlocked(username)) {
                    writer.write("REPEAT");
                    writer.println();
                    writer.flush();
                    return false;
                }
                if (user.isFriend(username)) {
                    writer.write("FRIEND");
                    writer.println();
                    writer.flush();
                    return false;
                }
                user.blockUser(username);
                writer.write("SUCCESS");
                writer.println();
                writer.flush();
                return true;
            }
        } catch (IOException e) {
            e.printStackTrace();
            writer.write("FAILED");
            writer.println();
            writer.flush();
            return false;
        }
        return false;
    }

    public boolean unblockUser(Database db, BufferedReader reader, PrintWriter writer) {
        String username;
        try {
            username = reader.readLine();
            if (db.userExists(username) && user.isBlocked(username)) {
                user.unblockUser(username);
                writer.write("SUCCESS");
                writer.println();
                writer.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
            writer.write("FAILED");
            writer.println();
            writer.flush();
            return false;
        }

        return true;
    }

    public boolean createPost(Database db, BufferedReader reader, PrintWriter writer) {
        String text = null;
        try {
            text = reader.readLine();
            System.out.println("aaaaaaa");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            new Post(user.getUsername(), text, "post_" + Post.getTotalPosts());
            writer.write("True");
            writer.println();
            writer.flush();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean likePost(Database db, BufferedReader reader, PrintWriter writer) {
        try {
            String[] postInfo = new String[2];
            postInfo[0] = reader.readLine(); //Username
            postInfo[1] = reader.readLine(); //PostID
            Post post = new Post(postInfo[0], Integer.parseInt(postInfo[1]));
            boolean success = post.like(user.getUsername());
            writer.println(String.valueOf(success));
            return success;
        } catch (IOException e) {
            return false;
        }
    }

    public boolean dislikePost(Database db, BufferedReader reader, PrintWriter writer) {
        try {
            String[] postInfo = new String[2];
            postInfo[0] = reader.readLine(); //Username
            postInfo[1] = reader.readLine(); //PostID
            Post post = new Post(postInfo[0], Integer.parseInt(postInfo[1]));
            boolean success = post.dislike(user.getUsername());
            writer.println(String.valueOf(success));
            return success;
        } catch (IOException e) {
            return false;
        }
    }

    public boolean hidePost(Database db, BufferedReader reader, PrintWriter writer) {
        try {
            String[] postInfo = new String[2];
            postInfo[0] = reader.readLine(); //Username
            postInfo[1] = reader.readLine(); //PostID
            Post post = new Post(postInfo[0], Integer.parseInt(postInfo[1]));
            boolean success = post.hide(user.getUsername());
            writer.println(String.valueOf(success));
            return success;
        } catch (IOException e) {
            return false;
        }
    }

    public boolean editPost(Database db, BufferedReader reader, PrintWriter writer) {
        try {
            String toEdit = reader.readLine(); //I'm going to say that it'll give the filename
            Post post = new Post("a", toEdit);
            writer.write("Enter new text");
            String text = "";
            while(true) {
                String line = reader.readLine();
                if (!line.equals("END"))
                    text = text.concat(line);
                else
                    break;
            }
            //(Noah) you don't need the username and it's not written anywhere so it's just a placeholder.
            post.editPost(text);
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    public boolean createComment(Database db, BufferedReader reader, PrintWriter writer) {
        String text = "";
        String parentNumber;
        try {
            while(true) {
                String line = reader.readLine();
                if (!line.equals("END"))
                    text = text.concat(line);
                else
                    break;
            }
            writer.write("Enter the number for the parent post");
            parentNumber = reader.readLine();
        } catch (IOException e) {
            return false;
        }
        try {
            new Comment(user.getUsername(), text, "comment_" + parentNumber + Comment.getTotalComments(), null);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean likeComment(Database db, BufferedReader reader, PrintWriter writer) {
        try {
            String toLike = reader.readLine(); //I'm going to say that it'll give the filename
            Comment comment = new Comment("a", toLike, null);
            //(Noah) you don't need the username and it's not written anywhere so it's just a placeholder.
            return comment.like(user.getUsername());
        } catch (IOException e) {
            return false;
        }
    }

    public boolean dislikeComment(Database db, BufferedReader reader, PrintWriter writer) {
        try {
            String toDislike = reader.readLine(); //I'm going to say that it'll give the filename
            Comment comment = new Comment("a", toDislike, null);
            //(Noah) you don't need the username and it's not written anywhere so it's just a placeholder.
            return comment.dislike(user.getUsername());
        } catch (IOException e) {
            return false;
        }
    }

    public boolean hideComment(Database db, BufferedReader reader, PrintWriter writer) {
        try {
            String toHide = reader.readLine(); //I'm going to say that it'll give the filename
            Comment comment = new Comment("a", toHide, null);
            //(Noah) you don't need the username and it's not written anywhere so it's just a placeholder.
            return comment.hide(user.getUsername());
        } catch (IOException e) {
            return false;
        }
    }

    public boolean editComment(Database db, BufferedReader reader, PrintWriter writer) {
        try {
            String toEdit = reader.readLine(); //I'm going to say that it'll give the filename
            Comment comment = new Comment("a", toEdit, null);
            writer.write("Enter new text");
            String text = "";
            while(true) {
                String line = reader.readLine();
                if (!line.equals("END"))
                    text = text.concat(line);
                else
                    break;
            }
            //(Noah) you don't need the username and it's not written anywhere so it's just a placeholder.
            comment.editPost(text);
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    public void getFeed(Database db, BufferedReader reader, PrintWriter writer) {
        // Get all of user's friends
        String[] friendUsernames = user.getFriendList().toArray(new String[0]);

        // Convert each friend to a User so we can fetch posts
        User[] friends = new User[friendUsernames.length];
        for (int u = 0; u < friendUsernames.length; u++) {
            friends[u] = new User(friendUsernames[u]);
        }

        // Arraylist to hold all posts of every friend
        ArrayList<Post> allPosts = new ArrayList<>();
        for (User friend : friends) {
            ArrayList<Post> userPosts = friend.getPostsList();
            allPosts.addAll(userPosts);
        }

        // Organize posts by time posted
        Collections.sort(allPosts, new Comparator<Post>() {
            public int compare(Post post1, Post post2) {
                return compareTimestamps(post1.getCurrentTime(), post2.getCurrentTime());
            }
        });

        // Sends the number of posts to read to the client
        writer.println(allPosts.size());

        // Sends user who wrote the post, text content, likes, and dislikes to Client
        for (Post currentPost : allPosts) {
            sendPosts(db, reader, writer, currentPost);
        }
        writer.flush(); // Flush the buffer to ensure all data is sent
    }

    private void sendPosts(Database db, BufferedReader reader, PrintWriter writer, Post post) {
        writer.println(post.getUsername());
        writer.println(post.getPostID());
    }

    private int compareTimestamps(int[] timestamp1, int[] timestamp2) {
        // Compare year
        if (timestamp1[0] != timestamp2[0]) {
            return timestamp1[0] - timestamp2[0];
        }
        // Compare month
        if (timestamp1[1] != timestamp2[1]) {
            return timestamp1[1] - timestamp2[1];
        }
        // Compare day
        if (timestamp1[2] != timestamp2[2]) {
            return timestamp1[2] - timestamp2[2];
        }
        // Compare hour
        if (timestamp1[3] != timestamp2[3]) {
            return timestamp1[3] - timestamp2[3];
        }
        // Compare minute
        if (timestamp1[4] != timestamp2[4]) {
            return timestamp1[4] - timestamp2[4];
        }
        // Compare second
        if (timestamp1[5] != timestamp2[5]) {
            return timestamp1[5] - timestamp2[5];
        }
        // Compare millisecond
        return timestamp1[6] - timestamp2[6];
    }

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Server started and listening on port " + PORT);
            while (true) {
                Socket clientSocket = serverSocket.accept();
                Server server = new Server(clientSocket);
                new Thread(server).start();
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
