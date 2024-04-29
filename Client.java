import javax.swing.*;

import java.awt.*;
import java.io.*;
import java.net.*;
import java.util.*;
/**
 * Client.java
 *
 * Client that connects to the social media server. The client writes input
 * to the Server class where it will calculate and return the user's experience on
 * the social media platform.
 *
 * <p>Purdue University -- CS18000 -- Spring 2024 -- Team Project
 *
 * @author LO4-Team 2
 * @version Mon April 15th, 2024
 */
public class Client implements ClientInterface {
    private static String host = "localhost";
    private static int port = 620;

    private static BufferedReader reader;
    private static PrintWriter writer;
    GUI GUI;

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        try (Socket socket = new Socket(host, port)) {
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            writer = new PrintWriter(socket.getOutputStream());
            Client testClient = new Client();
            //(Sean) the following code is for the user to sign in or sign up
            //before being able to access any of the features of the social media platform.
            //If this messes with your code, just comment this all out.
            String inOrUp;
            String[] loginOptions = {"Sign In", "Sign Up"};
            String username = null;
            int result = JOptionPane.showOptionDialog(null,
                    "                                        Welcome to HELLo!" +
                            "\nBefore you can access our platform, you either need to sign in or sign up." +
                            "\n                                   Please choose an option.",
                    "HELLo", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,
                    null, loginOptions, loginOptions[1]);
            if (result == JOptionPane.YES_OPTION) {
                inOrUp = "in";
                writer.write(inOrUp);
                writer.println();
                writer.flush();
                username = testClient.signIn(scan, reader, writer);
            } else if (result == JOptionPane.NO_OPTION) {
                inOrUp = "up";
                writer.write(inOrUp);
                writer.println();
                writer.flush();
                username = testClient.createUser(scan, reader, writer);
            }
            if (username != null) {
                String finalUsername = username;
                SwingUtilities.invokeLater(() -> {
                    // Create an instance of the GUI class
                    GUI gui = new GUI(finalUsername, testClient);

                    // Create a JScrollPane with all test posts using the GUI instance
                    JScrollPane postsPanel = gui.AllPostsPanel();

                    // Assign the postsPanel to the panel property of the GUI instance
                    gui.panel = postsPanel;

                    // Add the panel to the homeScreen frame
                    gui.homeScreen.add(gui.panel, BorderLayout.CENTER);

                    // Set the default close operation
                    gui.homeScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

                    // Make the homeScreen frame visible
                    gui.homeScreen.setVisible(true);
                    
                });

                /*while (true) {
                    String commandResponse = null;
                    switch (commandResponse) {
                        case "createUser":
                            testClient.createUser(scan, reader, writer);
                            break;
                        case "signIn":
                            testClient.signIn(scan, reader, writer);
                            break;
                        case "userSearch":
                            testClient.userSearch(scan, reader, writer);
                            break;
                        case "getFeed":
                            testClient.getFeed(scan, reader, writer);
                            break;
                        case "viewProfile":
                            testClient.viewProfile(scan, reader, writer);
                            break;
                        case "friendUser":
                            testClient.friendUser(scan, reader, writer);
                            break;
                        case "unfriendUser":
                            testClient.unfriendUser(scan, reader, writer);
                            break;
                        case "blockUser":
                            testClient.blockUser(scan, reader, writer);
                            break;
                        case "unblockUser":
                            testClient.unblockUser(scan, reader, writer);
                            break;
                        case "createPost":
                            testClient.createPost(scan, reader, writer);
                            break;
                        case "likePost":
                        case "likeComment":
                            testClient.like(scan, reader, writer);
                            break;
                        case "dislikePost":
                        case "dislikeComment":
                            testClient.dislike(scan, reader, writer);
                            break;
                        case "hidePost":
                        case "hideComment":
                            testClient.hide(scan, reader, writer);
                            break;
                        case "editPost":
                        case "editComment":
                            testClient.edit(scan, reader, writer);
                            break;
                        case "createComment":
                            testClient.createComment(scan, reader, writer);
                            break;
                    }
                    //Forward-thinking is strongly encouraged
                    //you WILL add a GUI
                    //you WILL redo the client
                    //you WILL code sober
                } */
            }
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String createUser(Scanner scan, BufferedReader reader, PrintWriter writer) {
        try {
            int yesOrNo = JOptionPane.YES_OPTION;
            String usernameResponse = null;
            String passwordResponse;
            while (yesOrNo == JOptionPane.YES_OPTION) {
                String usernamePrompt = reader.readLine();
                do {
                    usernameResponse = JOptionPane.showInputDialog(null,
                            usernamePrompt, "HELLo",
                            JOptionPane.QUESTION_MESSAGE);
                    if (usernameResponse.isEmpty()) {
                        JOptionPane.showMessageDialog(null,
                                "Enter a valid username", "HELLo",
                                JOptionPane.ERROR_MESSAGE);
                    }
                } while (usernameResponse.isEmpty());
                writer.write(usernameResponse);
                writer.println();
                writer.flush();
                String passwordPrompt = reader.readLine();
                if (passwordPrompt.equals("Sorry, that username is already taken.")) {
                    usernameResponse = null;
                    JOptionPane.showMessageDialog(null,
                            passwordPrompt, "HELLo",
                            JOptionPane.ERROR_MESSAGE);
                    yesOrNo = JOptionPane.showConfirmDialog(null,
                            reader.readLine(),
                            "HELLo", JOptionPane.YES_NO_OPTION);
                    if (yesOrNo == JOptionPane.YES_OPTION) {
                        writer.write("Yes");
                        writer.println();
                        writer.flush();
                    } else {
                        writer.write("No");
                        writer.println();
                        writer.flush();
                    }
                } else {
                    do {
                        passwordResponse = JOptionPane.showInputDialog(null,
                                passwordPrompt, "HELLo",
                                JOptionPane.QUESTION_MESSAGE);
                        if (passwordResponse.isEmpty()) {
                            JOptionPane.showMessageDialog(null,
                                    "Enter a valid password", "HELLo",
                                    JOptionPane.ERROR_MESSAGE);
                        }
                    } while (passwordResponse.isEmpty());
                    writer.write(passwordResponse);
                    writer.println();
                    writer.flush();
                    String accountCreation = reader.readLine();
                    if (!accountCreation.contains("Account created.")) {
                        JOptionPane.showMessageDialog(null,
                                accountCreation, "HELLo",
                                JOptionPane.ERROR_MESSAGE);
                        yesOrNo = JOptionPane.showConfirmDialog(null,
                                reader.readLine(),
                                "HELLo", JOptionPane.YES_NO_OPTION);
                        if (yesOrNo == JOptionPane.YES_OPTION) {
                            writer.write("Yes");
                            writer.println();
                            writer.flush();
                        } else {
                            writer.write("No");
                            writer.println();
                            writer.flush();
                        }
                    } else {
                        JOptionPane.showMessageDialog(null,
                                accountCreation, "HELLo",
                                JOptionPane.INFORMATION_MESSAGE);
                        yesOrNo = JOptionPane.NO_OPTION;
                    }
                }
            }
            return usernameResponse;
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (NullPointerException e) {
            throw new NullPointerException("User canceled or exited panel.");
        }
    }

    public String signIn(Scanner scan, BufferedReader reader, PrintWriter writer) {
        try {
            int yesOrNo = JOptionPane.YES_OPTION;
            String usernameResponse = null;
            String passwordResponse;
            while (yesOrNo == JOptionPane.YES_OPTION) {
                String usernamePrompt = reader.readLine();
                do {
                    usernameResponse = JOptionPane.showInputDialog(null,
                            usernamePrompt, "HELLo",
                            JOptionPane.QUESTION_MESSAGE);
                    if (usernameResponse.isEmpty()) {
                        JOptionPane.showMessageDialog(null,
                                "Enter a valid username", "HELLo",
                                JOptionPane.ERROR_MESSAGE);
                    }
                } while (usernameResponse.isEmpty());
                writer.write(usernameResponse);
                writer.println();
                writer.flush();
                String passwordPrompt = reader.readLine();
                do {
                    passwordResponse = JOptionPane.showInputDialog(null,
                            passwordPrompt, "HELLo",
                            JOptionPane.QUESTION_MESSAGE);
                    if (passwordResponse.isEmpty()) {
                        JOptionPane.showMessageDialog(null,
                                "Enter a valid password", "HELLo",
                                JOptionPane.ERROR_MESSAGE);
                    }
                } while (passwordResponse.isEmpty());
                writer.write(passwordResponse);
                writer.println();
                writer.flush();
                String loginAttempt = reader.readLine();
                if (!loginAttempt.contains("Login successful.")) {
                    JOptionPane.showMessageDialog(null,
                            loginAttempt, "HELLo",
                            JOptionPane.ERROR_MESSAGE);
                    yesOrNo = JOptionPane.showConfirmDialog(null,
                            reader.readLine(),
                            "HELLo", JOptionPane.YES_NO_OPTION);
                    if (yesOrNo == JOptionPane.YES_OPTION) {
                        writer.write("Yes");
                        writer.println();
                        writer.flush();
                    } else {
                        writer.write("No");
                        writer.println();
                        writer.flush();
                        usernameResponse = null;
                    }
                } else {
                    JOptionPane.showMessageDialog(null,
                            loginAttempt, "HELLo",
                            JOptionPane.INFORMATION_MESSAGE);
                    yesOrNo = JOptionPane.NO_OPTION;
                }
            }
            return usernameResponse;
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (NullPointerException e) {
            throw new NullPointerException("User canceled or exited panel.");
        }
    }

    public void userSearch(Scanner scan, BufferedReader reader, PrintWriter writer) {
        try {
            int yesOrNo = JOptionPane.YES_OPTION;
            String usernameResponse;
            while (yesOrNo == JOptionPane.YES_OPTION) {
                String usernamePrompt = reader.readLine();
                do {
                    usernameResponse = JOptionPane.showInputDialog(null,
                            usernamePrompt, "HELLo",
                            JOptionPane.QUESTION_MESSAGE);
                    if (usernameResponse == null || usernameResponse.isEmpty()) {
                        JOptionPane.showMessageDialog(null,
                                "Enter a valid username", "HELLo",
                                JOptionPane.ERROR_MESSAGE);
                    }
                } while (usernameResponse == null || usernameResponse.isEmpty());
                writer.write(usernameResponse);
                writer.println();
                writer.flush();
                String searchAttempt = reader.readLine();
                if (!searchAttempt.contains("The user has")) {
                    JOptionPane.showMessageDialog(null,
                            searchAttempt, "HELLo",
                            JOptionPane.ERROR_MESSAGE);
                    yesOrNo = JOptionPane.showConfirmDialog(null,
                            reader.readLine(),
                            "HELLo", JOptionPane.YES_NO_OPTION);
                    if (yesOrNo == JOptionPane.YES_OPTION) {
                        writer.write("Yes");
                        writer.println();
                        writer.flush();
                    } else {
                        writer.write("No");
                        writer.println();
                        writer.flush();
                    }
                } else {
                    JOptionPane.showMessageDialog(null,
                            searchAttempt, "HELLo",
                            JOptionPane.INFORMATION_MESSAGE);
                    yesOrNo = JOptionPane.showConfirmDialog(null,
                            reader.readLine(),
                            "HELLo", JOptionPane.YES_NO_OPTION);
                    if (yesOrNo == JOptionPane.YES_OPTION) {
                        writer.write("Yes");
                        writer.println();
                        writer.flush();
                        //(Sean) If this loop is entered, the user would like to
                        //view the profile of the User whose username is the
                        //string variable "searchAttempt" in this method.
                        viewProfile(scan, reader, writer);
                        yesOrNo = JOptionPane.NO_OPTION;
                    } else {
                        writer.write("No");
                        writer.println();
                        writer.flush();
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void viewProfile(Scanner scan, BufferedReader reader, PrintWriter writer) {
        try {
            String username = reader.readLine();
            System.out.println("Profile: " + username);
            int friendsCount = Integer.parseInt(reader.readLine());
            System.out.println("Friends: " + friendsCount);
            String line;
            ArrayList<String[]> allPosts = new ArrayList<>();
            while ((line = reader.readLine()) != null) {
                allPosts.add(readPost(scan, reader, writer));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public boolean friendUser(Scanner scan, BufferedReader reader, PrintWriter writer) {
        System.out.println("Who would you like to friend?");
        String username = scan.nextLine();
        writer.write(username);
        try {
            switch (reader.readLine()) {
                case "REPEAT" :
                    System.out.println("You are already friends with " + username);
                    break;
                case "BLOCKED" :
                    System.out.println(username + " is blocked!");
                    break;
                case "SUCCESS" :
                    System.out.println("You have added " + username + " as a friend!");
                    break;
                case "FAILED" :
                    System.out.println("Error friending " + username);
                    break;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
    public boolean unfriendUser(Scanner scan, BufferedReader reader, PrintWriter writer) {
        System.out.println("Who would you like to unfriend?");
        String username = scan.nextLine();
        writer.write(username);
        try {
            if (reader.readLine().equals("SUCCESS")) {
                System.out.println("You have removed " + username + " as a friend.");
            } else {
                System.out.println("There was an error removing " + username + " as a friend.");
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
    public boolean blockUser(Scanner scan, BufferedReader reader, PrintWriter writer) {
        System.out.println("Who would you like to block?");
        String username = scan.nextLine();
        writer.write(username);
        try {
            switch (reader.readLine()) {
                case "REPEAT" :
                    System.out.println("You have already blocked " + username);
                case "FRIEND" :
                    System.out.println("You cannot block " + username + " while they are your friend!");
                case "SUCCESS" :
                    System.out.println("You have blocked " + username);
                case "FAILED" :
                    System.out.println("Error blocking " + username);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;    }

    public boolean createPost(String username, String text) {
        System.out.println("aeioru");
        writer.write("createPost");
        writer.println();
        writer.flush();
        writer.write(text);
        writer.println();
        writer.flush();
        try {
            return Boolean.parseBoolean(reader.readLine());
        } catch (IOException e) {
            return false;
        }
    }

    public boolean unblockUser(Scanner scan, BufferedReader reader, PrintWriter writer) {
        System.out.println("Who would you like to unblock?");
        String username = scan.nextLine();
        writer.write(username);
        try {
            if (reader.readLine().equals("SUCCESS")) {
                System.out.println("You have unblocked " + username);
            } else {
                System.out.println("There was an error unblocking " + username);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }




    public ArrayList<String[]> getFeed(Scanner scan, BufferedReader reader, PrintWriter writer) {
        ArrayList<String[]> allPosts = new ArrayList<>();

        try {
            String response = reader.readLine();
            if (response.equals("get feed")) {
                // Output the response directly if it's "get feed"
                writer.println(response);
                writer.flush();

            } else {
                // Parse the number of posts and handle accordingly
                int numOfPosts = Integer.parseInt(response);

                // Read information for each post and add an array of this info to allPosts
                for (int p = 0; p < numOfPosts; p++) {
                    allPosts.add(readPost(scan, reader, writer));
                }

                // Output the response
                writer.println(response);
                writer.flush();

                // Output all posts if necessary (not included in this code snippet
            }
        } catch (IOException e) {
            System.out.println("Failed to retrieve feed");
            e.printStackTrace();
        } catch (NumberFormatException e) {
            System.out.println("Invalid response format: " + e.getMessage());
            e.printStackTrace();
        }
        return allPosts;
    }

    private String[] readPost (Scanner scan, BufferedReader reader, PrintWriter writer) {
        String[] postInfo = new String[4];
        try {
            postInfo[0] = reader.readLine(); // Username
            postInfo[1] = reader.readLine(); // PostID
            return postInfo;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }


    public boolean like(Scanner scan, BufferedReader reader, PrintWriter writer) {
        try {
            String filename = scan.nextLine();
            writer.write(filename);
            writer.println();
            writer.flush();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean dislike(Scanner scan, BufferedReader reader, PrintWriter writer) {
        try {
            String filename = scan.nextLine();
            writer.write(filename);
            writer.println();
            writer.flush();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean hide(Scanner scan, BufferedReader reader, PrintWriter writer) {
        try {
            String filename = scan.nextLine();
            writer.write(filename); //Will probably be a button that has filename in GUI;
            writer.println();
            writer.flush();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean edit(Scanner scan, BufferedReader reader, PrintWriter writer) {
        String fileName = scan.nextLine(); //ig just enter the file name lmao
        writer.write(fileName);
        String text = scan.nextLine();
        writer.write(text);
        writer.write("END");
        try {
            return Boolean.parseBoolean(reader.readLine());
        } catch (IOException e) {
            return false;
        }
    }

    public boolean createComment(Scanner scan, BufferedReader reader, PrintWriter writer) {
        String text = scan.nextLine(); //it's just going to have to be a single line for now. when we make the gui it can be multiple.
        writer.write(text);//THIS IS NOT HOW IT WILL WORK IN THE FINAL VERSION it will use a while loop like the server side
        String parent = scan.nextLine();
        writer.write(parent);
        writer.write("END");
        try {
            return Boolean.parseBoolean(reader.readLine());
        } catch (IOException e) {
            return false;
        }
    }
}
