import java.rmi.Naming;
import java.util.Scanner;

public class CalculatorClient {
    public static void main(String[] args) {
        try {
            Calculator calculator = (Calculator) Naming.lookup("rmi://localhost/CalculatorService");
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter first number: ");
            int num1 = scanner.nextInt();

            System.out.println("Enter second number: ");
            int num2 = scanner.nextInt();

            System.out.println("Choose an operation:");
            System.out.println("1. Addition");
            System.out.println("2. Subtraction");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("Result (Addition): " + calculator.add(num1, num2));
                    break;
                case 2:
                    System.out.println("Result (Subtraction): " + calculator.subtract(num1, num2));
                    break;
                default:
                    System.out.println("Invalid choice. Please choose either 1 or 2.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
