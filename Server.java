import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import javax.xml.crypto.Data;

public class Server implements Runnable {

    private static final int PORT = 620;
    private final Socket socket;
    private final ArrayList<User> userArray = new ArrayList<>();

    public Server(Socket socket) {
        this.socket = socket;
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
                        yesOrNo = "No";
                        writer.write("Account creation successful. Welcome to hell " + username + "!");
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
                writer.write("Please enter a password.");
                writer.println();
                writer.flush();
                String password;
                password = reader.readLine();
                if (db.authenticateUser(username, password)) {
                    yesOrNo = "No";
                    writer.write("Login successful. Welcome to hell " + username + "!");
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
                    writer.write("Here is the User you are looking for.");
                    writer.println();
                    writer.flush();
                    //(Sean) I am unsure of what we are planning to display when
                    //a user is searched up. Their posts? Their friends and/or blocked users?
                    //I just have it returning the index of the User in the ArrayList as a placeholder.
                    for (int i = 0; i < userArray.size(); i++) {
                        if (userArray.get(i).getUsername().equals(username)) {
                            writer.write("" + i);
                            writer.println();
                            writer.flush();
                        }
                    }
                    writer.write("Look for another? (Yes or No)");
                    writer.println();
                    writer.flush();
                    yesOrNo = reader.readLine();
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

    }

    public void getFeed(Database db, BufferedReader reader, PrintWriter writer) {

    }

    @Override
    public void run() {
        try {
            Database db = new Database();
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter writer = new PrintWriter(socket.getOutputStream());
            userArray.add(new User("Bobby"));
            String command;
            while (true) {
                writer.write("What would you like to do, User?");
                writer.println();
                writer.flush();
                command = reader.readLine();
                //(Sean) I readded login as part of the switch.
                // I only saw Faye's terminal edits after finishing my part. srry Faye
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
                        getFeed(db, reader, writer, user);
                        //(Faye) This will actually probably implement a getPosts and similar
                        //methods so I'll probably have to wait until that stuff is done
                        break;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


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

    public void getFeed(Database db, BufferedReader reader, BufferedWriter writer, User user) {
        String[] friendUsernames = (String[]) user.getFriendList().toArray();
        User[] friends = new User[friendUsernames.length];
        
        //convert each friend to user so we can fetch posts, likes, etc.
        for (int u = 0; u < friendUsernames.length; u++) {
            friends[u] = new User (friendUsernames[u]);
        }

        ArrayList<String> userPosts = new ArrayList<>(); //each friend will have an ArrayList of their posts, which will include the text, likes, and dislikes
        ArrayList<String>[] allPosts = new ArrayList[friends.length];

        for (int f = 0; f < friends.length; f++) {
            while (friends[f].getPosts()) {
                
            //}
            //implement a while loop that adds each post to the ArrayList

            //Need a way to retrieve posts and likes/dislikes
        }
    }

}
