// Implementation of Bully Election Algorithm in Java

import java.util.*;

class Process {
    int id;
    boolean isActive;

    public Process(int id) {
        this.id = id;
        this.isActive = true;
    }
}

public class BullyAlgorithm {
    private List<Process> processes;
    private int leaderId;

    public BullyAlgorithm(int[] processIds) {
        processes = new ArrayList<>();
        for (int id : processIds) {
            processes.add(new Process(id));
        }
        leaderId = -1;
    }

    public void deactivateProcess(int id) {
        for (Process p : processes) {
            if (p.id == id) {
                p.isActive = false;
                System.out.println("Process " + id + " deactivated.");
                return;
            }
        }
        System.out.println("Process " + id + " not found.");
    }

    public void startElection(int initiatorId) {
        System.out.println("Process " + initiatorId + " started the election.");
        List<Integer> higherPriorityProcesses = new ArrayList<>();

        for (Process p : processes) {
            if (p.id > initiatorId && p.isActive) {
                higherPriorityProcesses.add(p.id);
            }
        }

        if (higherPriorityProcesses.isEmpty()) {
            leaderId = initiatorId;
            System.out.println("Process " + initiatorId + " becomes the leader.");
        } else {
            System.out.println("Processes with higher priority than " + initiatorId + ": " + higherPriorityProcesses);
            for (int id : higherPriorityProcesses) {
                startElection(id);
            }
        }
    }

    public void displayLeader() {
        if (leaderId != -1) {
            System.out.println("Current leader is Process " + leaderId);
        } else {
            System.out.println("No leader elected yet.");
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter process IDs separated by space: ");
        String[] input = scanner.nextLine().split(" ");
        int[] processIds = Arrays.stream(input).mapToInt(Integer::parseInt).toArray();

        BullyAlgorithm bully = new BullyAlgorithm(processIds);

        while (true) {
            System.out.println("\nMenu:\n1. Start Election\n2. Deactivate Process\n3. Display Leader\n4. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter initiator process ID: ");
                    int initiatorId = scanner.nextInt();
                    bully.startElection(initiatorId);
                    break;
                case 2:
                    System.out.print("Enter process ID to deactivate: ");
                    int deactivateId = scanner.nextInt();
                    bully.deactivateProcess(deactivateId);
                    break;
                case 3:
                    bully.displayLeader();
                    break;
                case 4:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
