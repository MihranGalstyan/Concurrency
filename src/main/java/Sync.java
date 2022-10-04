/**
 * Created by Mihran Galstyan
 * All rights reserved
 */
public class Sync {
    public static void main(final String[] args) {
        Counter counter = new Counter();
        int barrier = 100_000_000;
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


        Thread thread3 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < barrier; i++) {
                    counter.increment2();
                }
            }
        });

        Thread thread4 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < barrier; i++) {
                    counter.decrement2();
                }
            }
        });
        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
        try {
            thread1.join();
            thread2.join();
            thread3.join();
            thread4.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println(counter.getValue());
        System.out.println(counter.getValue2());
        long after = System.currentTimeMillis();
        System.out.println("Thread 4 time is: " + (after - before));
    }
}
