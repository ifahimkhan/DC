import java.io.*;
import java.net.*;

public class RPCClient {
    public static void main(String[] args) {
        try (Socket socket = new Socket("localhost", 5000)) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));

            // Send method request to server
            String request = consoleReader.readLine(); // Example: Add two numbers
            System.out.println("Sending request: " + request);
            writer.println(request);

            // Receive response from server
            String response = reader.readLine();
            System.out.println("Server response: " + response);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
