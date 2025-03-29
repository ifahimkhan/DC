import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MutexExample {
    private static int counterWithMutex = 0;
    private static int counterNoMutex = 0;
    private static final Lock mutex = new ReentrantLock();

    public static void main(String[] args) throws InterruptedException {
        // Create tasks
        Runnable noMutexTask = () -> {
            for (int i = 0; i < 500; i++) {
                incrementCounterByTenNoMutex();
            }
        };

        Runnable withMutexTask = () -> {
            for (int i = 0; i < 500; i++) {
                incrementCounterByTenWithMutex();
            }
        };

        // Create threads
        Thread t1 = new Thread(noMutexTask);
        Thread t2 = new Thread(noMutexTask);
        Thread t3 = new Thread(withMutexTask);
        Thread t4 = new Thread(withMutexTask);

        // Start threads
        t1.start();
        t2.start();
        t3.start();
        t4.start();

        // Wait for all threads to finish
        t1.join();
        t2.join();
        t3.join();
        t4.join();

        // Print results
        System.out.println("  No Mutex Tally: " + counterNoMutex);
        System.out.println("With Mutex Tally: " + counterWithMutex);
    }

    private static void incrementCounterByTenWithMutex() {
        mutex.lock();
        try {
            for (int i = 0; i < 10; i++) {
                counterWithMutex++;
            }
        } finally {
            mutex.unlock();
        }
    }

    private static void incrementCounterByTenNoMutex() {
        for (int i = 0; i < 10; i++) {
            counterNoMutex++;
        }
    }
}