import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server implements Runnable {

    private static final int PORT = 620;
    private final Socket socket;

    public Server(Socket socket) {
        this.socket = socket;
    }

    public User createUser(String username) {
        User result = new User("sugon.txt");
        return result;
    }
    @Override
    public void run() {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter writer = new PrintWriter(socket.getOutputStream());
            while (true) {
                String command = reader.readLine();
                switch (command) {
                    case "login": String username;
                    String bean = reader.readLine();
                        break;
                    case "logout":
                        break;
                    case "Get Feed":
                        String user = reader.readLine()
                        feed(user);
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

    //(Faye) Feed stuffs
    public void feed(User user) {
        
    }

}
