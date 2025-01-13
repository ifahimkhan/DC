package chat;

import java.io.*;
import java.net.*;

public class ChatServer {
    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(5001)) {
            System.out.println("Chat Server is listening on port 5001...");
            Socket socket = serverSocket.accept();
            System.out.println("Client connected.");

            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter writer = new PrintWriter(socket.getOutputStream(), false);

            BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));

            String clientMessage, serverMessage;

            while (true) {
                clientMessage = reader.readLine();
                if (clientMessage.equalsIgnoreCase("exit")) {
                    System.out.println("Client disconnected.");
                    break;
                }
                System.out.println("Client: " + clientMessage);

                System.out.print("Server: ");
                serverMessage = consoleReader.readLine();
                writer.println(serverMessage);
                if (serverMessage.equalsIgnoreCase("exit")) {
                    System.out.println("Chat ended.");
                    break;
                }
            }

            socket.close();
        } catch (IOException ex) {
            System.out.println("Chat Server error: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
}
