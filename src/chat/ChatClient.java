package chat;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class ChatClient {
    public static void main(String[] args) {
        try (Socket socket = new Socket("localhost", 5001)) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);

            BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));

            String clientMessage, serverMessage;

            while (true) {
                System.out.print("Client: ");
                clientMessage = consoleReader.readLine();
                writer.println(clientMessage);
                if (clientMessage.equalsIgnoreCase("exit")) {
                    System.out.println("Chat ended.");
                    break;
                }

                serverMessage = reader.readLine();
                System.out.println("Server: " + serverMessage);
                if (serverMessage.equalsIgnoreCase("exit")) {
                    System.out.println("Server disconnected.");
                    break;
                }
            }

            socket.close();
        } catch (IOException ex) {
            System.out.println("Chat Client error: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
}
