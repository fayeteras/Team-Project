import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server implements Runnable {

    private static final int PORT = 620;
    private final Socket socket;

    public Server(Socket socket) {
        this.socket = socket;
    }

    public User createUser(String username) {
        User result = new User(username);
        return result;
    }
    @Override
    public void run() {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter writer = new PrintWriter(socket.getOutputStream());

             //(Faye) just added this part so my stuff will work, but change it when you need to
             String username = reader.readLine();
             User user = createUser(username);
             //Login needs to happen before anything, so i took it out of the switch

            while (true) {
                String command = reader.readLine();

                switch (command) {
                    case "logout":
                        break;
                    case "Get Feed":
                        //This will actually probably implement a getPosts and similar 
                        //methods so I'll probably have to wait until that stuff is done
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

}
