import java.io.*;
import java.net.*;

public class CalculatorServer {
    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(5001)) {
            System.out.println("Server is running and waiting for clients...");
            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("Client connected!");

                // Create input and output streams
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

                // Read operation from the client
                String input = in.readLine();
                System.out.println("Received: " + input);

                // Process the operation
                String result = evaluateExpression(input);

                // Send result back to client
                out.println(result);

                // Close the socket
                socket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String evaluateExpression(String input) {
        try {
            String[] tokens = input.split(" ");
            if (tokens.length != 3) {
                return "Invalid expression format! Use: number1 operator number2";
            }

            double num1 = Double.parseDouble(tokens[0]);
            double num2 = Double.parseDouble(tokens[2]);
            String operator = tokens[1];

            double result;
            switch (operator) {
                case "+":
                    result = num1 + num2;
                    break;
                case "-":
                    result = num1 - num2;
                    break;
                case "*":
                    result = num1 * num2;
                    break;
                case "/":
                    if (num2 == 0) return "Error: Division by zero!";
                    result = num1 / num2;
                    break;
                default:
                    return "Error: Invalid operator!";
            }
            return "Result: " + result;
        } catch (NumberFormatException e) {
            return "Error: Invalid number format!";
        }
    }
}
