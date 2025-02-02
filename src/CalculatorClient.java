
import java.io.*;
import java.net.*;
import java.util.Scanner;

public class CalculatorClient {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        try (Socket socket = new Socket("localhost", 5001)) {
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            System.out.println("Connected to the server.");

            // Take user input
            System.out.print("Enter operation (e.g., 5 + 3): ");
            String operation = scanner.nextLine();

            // Send operation to server
            out.println(operation);

            // Receive and display result
            String response = in.readLine();
            System.out.println("Server Response: " + response);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            scanner.close();
        }
    }
}