// Runnable implementation to print numbers from 1 to 10
class NumberRunnable implements Runnable {
    @Override
    public void run() {
        for (int i = 1; i <= 10; i++) {
            System.out.println("Number: " + i);
            // Pause for half a second
            try {
                Thread.sleep(500); 
            } catch (InterruptedException e) {
                System.out.println("NumberRunnable interrupted");
            }
        }
    }
}

// Runnable implementation to print squares of numbers from 1 to 10
class SquareRunnable implements Runnable {
    @Override
    public void run() {
        for (int i = 1; i <= 10; i++) {
            System.out.println("Square of " + i + ": " + (i * i));
            // Pause for half a second
            try {
                Thread.sleep(500); 
            } catch (InterruptedException e) {
                System.out.println("SquareRunnable interrupted");
            }
        }
    }
}

public class MultithreadingExample {
    public static void main(String[] args) {
        // Create the two threads
        Thread numberThread = new Thread(new NumberRunnable());
        Thread squareThread = new Thread(new SquareRunnable());
        // Start both threads
        numberThread.start();
        squareThread.start();
    }
}
