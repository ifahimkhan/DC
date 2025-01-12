import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(5000)) {
            System.out.println("Server is listening on port 5000...");
            Socket socket = serverSocket.accept();
            System.out.println("Client connected.");

            OutputStream output = socket.getOutputStream();
            PrintWriter writer = new PrintWriter(output, true);

            writer.println("Hello from Server!");

            socket.close();
            System.out.println("Connection closed.");
        } catch (IOException ex) {
            System.out.println("Server error: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
}
