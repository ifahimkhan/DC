import java.util.*;

class Process {
    private int id;
    private int timestamp;
    private boolean inCriticalSection;
    private Set<Integer> repliesReceived;
    private int totalProcesses;
    private List<Process> allProcesses;

    public Process(int id, int totalProcesses) {
        this.id = id;
        this.timestamp = 0;
        this.inCriticalSection = false;
        this.repliesReceived = new HashSet<>();
        this.totalProcesses = totalProcesses;
    }

    public void setAllProcesses(List<Process> processes) {
        this.allProcesses = processes;
    }

    // Requesting access to Critical Section
    public void requestCriticalSection() {
        timestamp++; // Increment timestamp for request
        repliesReceived.clear(); // Reset replies
        System.out.println("Process " + id + " is requesting Critical Section at time " + timestamp);

        // Send request to all other processes
        for (Process p : allProcesses) {
            if (p.id != this.id) {
                p.receiveRequest(id, timestamp);
            }
        }
    }

    // Receiving a request from another process
    public void receiveRequest(int senderId, int senderTimestamp) {
        System.out.println("Process " + id + " received request from Process " + senderId + " at time " + senderTimestamp);

        // Check if we are in the critical section or our request is earlier
        if (inCriticalSection || (timestamp != 0 && senderTimestamp > timestamp)) {
            System.out.println("Process " + id + " is delaying REPLY to Process " + senderId);
            return;
        }

        // Send reply immediately
        for (Process p : allProcesses) {
            if (p.id == senderId) {
                p.receiveReply(id);
            }
        }
    }

    // Receiving a reply from another process
    public void receiveReply(int senderId) {
        System.out.println("Process " + id + " received REPLY from Process " + senderId);
        repliesReceived.add(senderId);

        // Enter CS only when all other processes have sent replies
        if (repliesReceived.size() == totalProcesses - 1) {
            enterCriticalSection();
        }
    }

    // Entering the critical section
    private void enterCriticalSection() {
        System.out.println("Process " + id + " is ENTERING the Critical Section.");
        inCriticalSection = true;

        try {
            Thread.sleep(2000); // Simulate work in CS
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        exitCriticalSection();
    }

    // Exiting the critical section
    private void exitCriticalSection() {
        System.out.println("Process " + id + " is EXITING the Critical Section.");
        inCriticalSection = false;
        timestamp = 0; // Reset timestamp after exiting
    }
}

public class RicartAgrawalaSimulation {
    public static void main(String[] args) {
        int totalProcesses = 3;
        List<Process> processes = new ArrayList<>();

        // Create processes
        for (int i = 0; i < totalProcesses; i++) {
            processes.add(new Process(i, totalProcesses));
        }

        // Set up connections between processes
        for (Process p : processes) {
            p.setAllProcesses(processes);
        }

        // Simulate processes requesting CS
        processes.get(0).requestCriticalSection();
        processes.get(1).requestCriticalSection();
        processes.get(2).requestCriticalSection();
    }
}
