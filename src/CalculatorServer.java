import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

public class CalculatorServer {
    public static void main(String[] args) {
        try {
            LocateRegistry.createRegistry(1099); // Start the RMI registry on port 1099
            Calculator calculator = new CalculatorImpl();
            Naming.rebind("rmi://localhost/CalculatorService", calculator);
            System.out.println("Calculator Service is running...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
