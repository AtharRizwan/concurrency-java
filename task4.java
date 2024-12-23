import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

// BankAccount class with deposit, withdraw, and getBalance methods
class BankAccount {
    private static AtomicInteger balance = new AtomicInteger(1000);
    /**
     * Perform a deposit operation.
     * @param amount the amount to deposit
     */
    public static void deposit(int amount) {
        balance.addAndGet(amount);
        System.out.println(Thread.currentThread().getName() + " deposited: " + amount);
    }
    /**
     * Perform a withdrawal operation.
     * @param amount the amount to withdraw
     */
    public static void withdraw(int amount) {
        if (amount > 0 && balance.get() >= amount) {
            balance.addAndGet(-amount);
            System.out.println(Thread.currentThread().getName() + " withdrew: " + amount);
        } else {
            System.out.println(Thread.currentThread().getName() + " attempted to withdraw: " + amount + " but insufficient funds. Current Balance: " + balance.get());
        }
    }
    /**
     * Get the current balance.
     * @return the current balance
     */
    public static int getBalance() {
        return balance.get();
    }
}

// Client class that performs random transactions
class Client implements Runnable {
    private final Random random = new Random();
    @Override
    public void run() {
        for (int i = 0; i < 2; i++) { // Perform 2 random transactions
            int amount = random.nextInt(100) + 1; // Random amount between 1 and 100
            if (random.nextBoolean()) {
                BankAccount.deposit(amount);
            } else {
                BankAccount.withdraw(amount);
            }
            try {
                Thread.sleep(100); // Simulate processing time
            } catch (InterruptedException e) {
                System.out.println("Thread interrupted");
            }
        }
    }
}

public class task4 {
    public static void main(String[] args) {
        // Create multiple client threads
        Thread client1 = new Thread(new Client(), "Client1");
        Thread client2 = new Thread(new Client(), "Client2");
        Thread client3 = new Thread(new Client(), "Client3");
        // Start the client threads
        client1.start();
        client2.start();
        client3.start();
        // Wait for all threads to finish
        try {
            client1.join();
            client2.join();
            client3.join();
        } catch (InterruptedException e) {
            System.out.println("Main thread interrupted");
        }
        // Display the final balance
        System.out.println("Final Account Balance: " + BankAccount.getBalance());
    }
}
