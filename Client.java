import java.io.*;
import java.net.*;
import java.util.*;

public class Client {
    private static String host = "localhost";
    private static int port = 620;

    public static void createUser(Scanner scan, BufferedReader reader, PrintWriter writer) {
        try {
            String yesOrNo = "Yes";
            while (yesOrNo.equals("Yes")) {
                String usernamePrompt = reader.readLine();
                System.out.println(usernamePrompt);
                String usernameResponse = scan.nextLine();
                writer.write(usernameResponse);
                writer.println();
                writer.flush();
                String passwordPrompt = reader.readLine();
                System.out.println(passwordPrompt);
                if (passwordPrompt.equals("Sorry, that username is already taken.")) {
                    String tryUsernameAgain = reader.readLine();
                    System.out.println(tryUsernameAgain);
                    yesOrNo = scan.nextLine();
                    writer.write(yesOrNo);
                    writer.println();
                    writer.flush();
                } else {
                    String passwordResponse = scan.nextLine();
                    writer.write(passwordResponse);
                    writer.println();
                    writer.flush();
                    String accountCreation = reader.readLine();
                    System.out.println(accountCreation);
                    if (!accountCreation.contains("Account creation successful.")) {
                        String tryCreationAgain = reader.readLine();
                        System.out.println(tryCreationAgain);
                        yesOrNo = scan.nextLine();
                        writer.write(yesOrNo);
                        writer.println();
                        writer.flush();
                    } else {
                        yesOrNo = "No";
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void signIn(Scanner scan, BufferedReader reader, PrintWriter writer) {
        try {
            String yesOrNo = "Yes";
            while (yesOrNo.equals("Yes")) {
                String usernamePrompt = reader.readLine();
                System.out.println(usernamePrompt);
                String usernameResponse = scan.nextLine();
                writer.write(usernameResponse);
                writer.println();
                writer.flush();
                String passwordPrompt = reader.readLine();
                System.out.println(passwordPrompt);
                String passwordResponse = scan.nextLine();
                writer.write(passwordResponse);
                writer.println();
                writer.flush();
                String loginAttempt = reader.readLine();
                System.out.println(loginAttempt);
                if (!loginAttempt.contains("Login successful.")) {
                    String tryLoginAgain = reader.readLine();
                    System.out.println(tryLoginAgain);
                    yesOrNo = scan.nextLine();
                    writer.write(yesOrNo);
                    writer.println();
                    writer.flush();
                } else {
                    yesOrNo = "No";
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void userSearch(Scanner scan, BufferedReader reader, PrintWriter writer) {
        try {
            String yesOrNo = "Yes";
            while (yesOrNo.equals("Yes")) {
                String usernamePrompt = reader.readLine();
                System.out.println(usernamePrompt);
                String usernameResponse = scan.nextLine();
                writer.write(usernameResponse);
                writer.println();
                writer.flush();
                String searchAttempt = reader.readLine();
                System.out.println(searchAttempt);
                if (!searchAttempt.contains("Here is")) {
                    String trySearchAgain = reader.readLine();
                    System.out.println(trySearchAgain);
                    yesOrNo = scan.nextLine();
                    writer.write(yesOrNo);
                    writer.println();
                    writer.flush();
                } else {
                    String returnedUser = reader.readLine();
                    System.out.println(returnedUser);
                    String searchAgain = reader.readLine();
                    System.out.println(searchAgain);
                    yesOrNo = scan.nextLine();
                    writer.write(yesOrNo);
                    writer.println();
                    writer.flush();
                    if (yesOrNo.equals("Yes")) {
                        //(Sean) If this loop is entered, the user would like to
                        //view the profile of the User whose username is the
                        //string variable "searchAttempt" in this class.
                        //please write and then call the viewProfile method below.
                        //viewProfile();
                        yesOrNo = "No";
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        try (Socket socket = new Socket(host, port)) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter writer = new PrintWriter(socket.getOutputStream());
            while (true) {
                String commandPrompt = reader.readLine();
                System.out.println(commandPrompt);
                String commandResponse = scan.nextLine();
                writer.write(commandResponse);
                writer.println();
                writer.flush();
                switch (commandResponse) {
                    case "createUser":
                        createUser(scan, reader, writer);
                        break;
                    case "signIn":
                        signIn(scan, reader, writer);
                        break;
                    case "userSearch":
                        userSearch(scan, reader, writer);
                        break;
                    case "getFeed":
                        getFeed(scan, reader, writer);
                        break;
                    case "viewProfile":
                        viewProfile(scan, reader, writer);
                        break;
                    case "friendUser":
                        friendUser(scan, reader, writer);
                        break;
                    case "unfriendUser":
                        unfriendUser(scan, reader, writer);
                        break;
                    case "blockUser":
                        blockuser(scan, reader, writer);
                        break;
                    case "unblockUser":
                        unblockUser(scan, reader, writer);
                        break;
                    case "createPost":
                        createPost(scan, reader, writer);
                        break;
                    case "likePost":
                    case "likeComment":
                        like(scan, reader, writer);
                        break;
                    case "dislikePost":
                    case "dislikeComment":
                        dislike(scan, reader, writer);
                        break;
                    case "hidePost":
                    case "hideComment":
                        hide(scan, reader, writer);
                        break;
                    case "editPost":
                    case "editComment":
                        edit(scan, reader, writer);
                        break;
                    case "createComment":
                        createComment(scan, reader, writer);
                        break;
                }
                //Forward-thinking is strongly encouraged
                //you WILL add a GUI
                //you WILL redo the client
                //you WILL code sober
            }
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void viewProfile(Scanner scan, BufferedReader reader, PrintWriter writer) {
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
    public static boolean friendUser(Scanner scan, BufferedReader reader, PrintWriter writer) {
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
    public static boolean unfriendUser(Scanner scan, BufferedReader reader, PrintWriter writer) {
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
    public static boolean blockuser(Scanner scan, BufferedReader reader, PrintWriter writer) {
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

    public static boolean unblockUser(Scanner scan, BufferedReader reader, PrintWriter writer) {
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




    public static boolean getFeed(Scanner scan, BufferedReader reader, PrintWriter writer) {
        try {
            String response = reader.readLine();
            if (response.equals("get feed")) {
                // Output the response directly if it's "get feed"
                writer.println(response);
                writer.flush();
                return true;
            } else {
                // Parse the number of posts and handle accordingly
                int numOfPosts = Integer.parseInt(response);
                ArrayList<String[]> allPosts = new ArrayList<>();

                // Read information for each post and add an array of this info to allPosts
                for (int p = 0; p < numOfPosts; p++) {
                    allPosts.add(readPost(scan, reader, writer));
                }

                // Output the response
                writer.println(response);
                writer.flush();

                // Output all posts if necessary (not included in this code snippet)
            }
        } catch (IOException e) {
            System.out.println("Failed to retrieve feed");
            e.printStackTrace();
            return false;
        } catch (NumberFormatException e) {
            System.out.println("Invalid response format: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
        return true;
    }

    private static String[] readPost (Scanner scan, BufferedReader reader, PrintWriter writer) {
        String[] postInfo = new String[4];
        try {
            postInfo[0] = reader.readLine(); // Username
            postInfo[1] = reader.readLine(); // Text
            postInfo[2] = reader.readLine(); // Likes
            postInfo[3] = reader.readLine(); // Dislikes
            return postInfo;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }


    public static boolean createPost(Scanner scan, BufferedReader reader, PrintWriter writer) {
        String text = scan.nextLine(); //it's just going to have to be a single line for now. when we make the gui it can be multiple.
        writer.write(text);//THIS IS NOT HOW IT WILL WORK IN THE FINAL VERSION it will use a while loop like the server side
        writer.write("END");
        try {
            return Boolean.parseBoolean(reader.readLine());
        } catch (IOException e) {
            return false;
        }
    }

    public static void like(Scanner scan, BufferedReader reader, PrintWriter writer) {
        try {
            String filename = scan.nextLine();
            writer.write(filename);
            writer.println();
            writer.flush();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void dislike(Scanner scan, BufferedReader reader, PrintWriter writer) {
        try {
            String filename = scan.nextLine();
            writer.write(filename);
            writer.println();
            writer.flush();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void hide(Scanner scan, BufferedReader reader, PrintWriter writer) {
        try {
            String filename = scan.nextLine();
            writer.write(filename); //Will probably be a button that has filename in GUI;
            writer.println();
            writer.flush();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean edit(Scanner scan, BufferedReader reader, PrintWriter writer) {
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

    public static boolean createComment(Scanner scan, BufferedReader reader, PrintWriter writer) {
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
