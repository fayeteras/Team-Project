import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server implements Runnable {

    private static final int PORT = 0;

    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(PORT);
            System.out.println("Server started and listening on port " + PORT);
            while (true) {
                Socket clientSocket = serverSocket.accept();
            }
        
    }

    @Override
    public void run() {
        
    }
    

    
}
