// Class that contains a shared counter
class SharedCounter {
    public static int counter = 0;
    /**
     * Synchronized method to increment the counter
     */
    public static synchronized void increment() {
        counter++;
    }
    /**
     * Get the current value of the counter
     * @return the current value of the counter
     */
    public static int getCounter() {
        return counter;
    }
}

class CounterThread extends Thread {
    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            SharedCounter.increment();
        }
    }
}

public class task2 {
    public static void main(String[] args) {
        // Create three threads
        CounterThread thread1 = new CounterThread();
        CounterThread thread2 = new CounterThread();
        CounterThread thread3 = new CounterThread();
        // Start all threads
        thread1.start();
        thread2.start();
        thread3.start();
        // Wait for threads to finish
        try {
            thread1.join();
            thread2.join();
            thread3.join();
        } catch (InterruptedException e) {
            System.out.println("Main thread interrupted");
        }
        // Display the final counter value
        System.out.println("Final Counter Value: " + SharedCounter.getCounter());
    }
}
