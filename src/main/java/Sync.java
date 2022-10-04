/**
 * Created by Mihran Galstyan
 * All rights reserved
 */
public class Sync {
    public static void main(final String[] args) {
        Counter counter = new Counter();
        int barrier = 1000;
        long before = System.currentTimeMillis();

        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < barrier; i++) {
                    counter.increment();
                }
            }
        });

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < barrier; i++) {
                    counter.decrement();
                }
            }
        });

        thread1.start();
        thread2.start();
        try {
            thread1.join();
            thread2.join();

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        long after = System.currentTimeMillis();
        System.out.println(counter.getValue());
        System.out.println("Time is: " + (after - before));
    }
}
