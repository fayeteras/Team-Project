import java.io.*;
import java.net.*;
import java.util.*;
import java.util.Scanner;

public class Client {
    Scanner scan = new Scanner(System.in);
    static String host = "localhost";
    static int port = 620;
    public static void main(String[] args) {
        try (Socket socket = new Socket(host, port)) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter writer = new PrintWriter(socket.getOutputStream());
            //Forward-thinking is strongly encouraged
            //you WILL add a GUI
            //you WILL redo the client
            //you WILL code sober

            //(Faye) For now just imput commands to terminal -- Will change to listeners later
            //Every time you write to the server also include the username
            Scanner input = new Scanner(System.in);
            String command = input.nextLine() + username;
            writer.write(command);
            writer.println();

        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
