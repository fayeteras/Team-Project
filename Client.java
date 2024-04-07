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
}
