import java.io.*;
import java.net.*;

public class RPCServer {
    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(5000)) {
            System.out.println("RPC Server is listening on port 5000...");
            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("Client connected.");

                BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);

                // Read the client's method request
                String request = reader.readLine();
                System.out.println("Received request: " + request);

                String response = handleRequest(request);
                writer.println(response);

                socket.close();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private static String handleRequest(String request) {
        String[] parts = request.split(",");
        String method = parts[0].trim();

        try {
            if (method.equalsIgnoreCase("add")) {
                int a = Integer.parseInt(parts[1].trim());
                int b = Integer.parseInt(parts[2].trim());
                return "Result: " + (a + b);
            } else if (method.equalsIgnoreCase("subtract")) {
                int a = Integer.parseInt(parts[1].trim());
                int b = Integer.parseInt(parts[2].trim());
                return "Result: " + (a - b);
            } else {
                return "Error: Unknown method " + method;
            }
        } catch (Exception e) {
            return "Error processing request: " + e.getMessage();
        }
    }
}
