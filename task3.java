import java.util.concurrent.CopyOnWriteArrayList;

class ListTask implements Runnable {
    private final CopyOnWriteArrayList<Integer> sharedList;
    /**
     * Constructor
     * @param sharedList
    */
    public ListTask(CopyOnWriteArrayList<Integer> sharedList) {
        this.sharedList = sharedList;
    }
    @Override
    public void run() {
        // Add elements to the list
        for (int i = 0; i < 5; i++) {
            sharedList.add(i);
        }
    }
}

public class task3 {
    public static void main(String[] args) {
        // Shared thread-safe data structures
        CopyOnWriteArrayList<Integer> sharedList = new CopyOnWriteArrayList<>();
        // Create threads for CopyOnWriteArrayList
        Thread listThread1 = new Thread(new ListTask(sharedList));
        Thread listThread2 = new Thread(new ListTask(sharedList));
        // Start all threads
        listThread1.start();
        listThread2.start();
        // Wait for threads to finish
        try {
            listThread1.join();
            listThread2.join();
        } catch (InterruptedException e) {
            System.out.println("Main thread interrupted");
        }
        // Print final state of shared data structures
        System.out.println("Final List: " + sharedList);
    }
}
