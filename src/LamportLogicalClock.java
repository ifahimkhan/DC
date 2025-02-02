import java.util.*;

class Process {
    int processId;
    int logicalClock;

    public Process(int id) {
        this.processId = id;
        this.logicalClock = 0;
    }

    // Internal Event (increments the logical clock)
    public void internalEvent() {
        logicalClock++;
        System.out.println("Process " + processId + " executed an internal event. Logical Clock: " + logicalClock);
    }

    // Send Event (increments clock before sending message)
    public int sendEvent() {
        logicalClock++;
        System.out.println("Process " + processId + " sent a message. Logical Clock: " + logicalClock);
        return logicalClock;
    }

    // Receive Event (updates clock based on sender's timestamp)
    public void receiveEvent(int senderClock) {
        logicalClock = Math.max(logicalClock, senderClock) + 1;
        System.out.println("Process " + processId + " received a message. Updated Logical Clock: " + logicalClock);
    }
}

public class LamportLogicalClock {
    public static void main(String[] args) {
        // Creating processes
        Process p1 = new Process(1);
        Process p2 = new Process(2);
        Process p3 = new Process(3);

        System.out.println("\n--- Simulating Events ---\n");

        // Internal event in P1
        p1.internalEvent();

        // P1 sends a message to P2
        int timestampP1 = p1.sendEvent();
        p2.receiveEvent(timestampP1);

        // P2 sends a message to P3
        int timestampP2 = p2.sendEvent();
        p3.receiveEvent(timestampP2);

        // Internal event in P3
        p3.internalEvent();

        // P3 sends a message to P1
        int timestampP3 = p3.sendEvent();
        p1.receiveEvent(timestampP3);

        System.out.println("\nFinal Logical Clocks:");
        System.out.println("Process 1: " + p1.logicalClock);
        System.out.println("Process 2: " + p2.logicalClock);
        System.out.println("Process 3: " + p3.logicalClock);
    }
}
