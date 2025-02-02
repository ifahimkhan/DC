import java.util.Arrays;

class Process {
    int processId;
    int[] vectorClock;

    public Process(int id, int totalProcesses) {
        this.processId = id;
        this.vectorClock = new int[totalProcesses]; // Initialize vector clock
        Arrays.fill(vectorClock, 0); // Start with all zeros
    }

    // Internal Event (increments the process's own logical clock)
    public void internalEvent() {
        vectorClock[processId]++;
        System.out.println("Process " + processId + " executed an internal event. VC: " + Arrays.toString(vectorClock));
    }

    // Send Event (increments the clock before sending message)
    public int[] sendEvent() {
        vectorClock[processId]++; // Increment before sending
        System.out.println("Process " + processId + " sent a message. VC: " + Arrays.toString(vectorClock));
        return Arrays.copyOf(vectorClock, vectorClock.length); // Send a copy
    }

    // Receive Event (updates clock based on sender's vector)
    public void receiveEvent(int[] senderVector) {
        for (int i = 0; i < vectorClock.length; i++) {
            vectorClock[i] = Math.max(vectorClock[i], senderVector[i]); // Take max for each entry
        }
        vectorClock[processId]++; // Increment own clock
        System.out.println("Process " + processId + " received a message. Updated VC: " + Arrays.toString(vectorClock));
    }
}

public class VectorClockSimulation {
    public static void main(String[] args) {
        int totalProcesses = 3;

        // Creating processes
        Process p0 = new Process(0, totalProcesses);
        Process p1 = new Process(1, totalProcesses);
        Process p2 = new Process(2, totalProcesses);

        System.out.println("\n--- Simulating Events ---\n");

        // Internal event in P0
        p0.internalEvent();

        // P0 sends a message to P1
        int[] timestampP0 = p0.sendEvent();
        p1.receiveEvent(timestampP0);

        // P1 sends a message to P2
        int[] timestampP1 = p1.sendEvent();
        p2.receiveEvent(timestampP1);

        // Internal event in P2
        p2.internalEvent();

        // P2 sends a message to P0
        int[] timestampP2 = p2.sendEvent();
        p0.receiveEvent(timestampP2);

        System.out.println("\nFinal Vector Clocks:");
        System.out.println("Process 0: " + Arrays.toString(p0.vectorClock));
        System.out.println("Process 1: " + Arrays.toString(p1.vectorClock));
        System.out.println("Process 2: " + Arrays.toString(p2.vectorClock));
    }
}
