import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mihran Galstyan
 * All rights reserved
 */
public class Main {

    public static void main(final String[] args) {
        List<Integer> list1 = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>();
        List<Integer> list3 = new ArrayList<>();

        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 1000000; i++) {
                    list1.add(i);
                }
                System.out.println("Thread 1 done");
            }
        });

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 1000000; i++) {
                    list2.add(i);
                }
                System.out.println("Thread 2 done");
            }
        });

        Thread thread3 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 1000000; i++) {
                    list3.add(i);
                }
                System.out.println("Thread 3 done");
            }
        });

        thread1.start();
        thread2.start();
        thread3.start();
//        try {
//            thread1.join();
//            thread2.join();
//            thread3.join();
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
        System.out.println(list1.size());
        System.out.println(list2.size());
        System.out.println(list3.size());


    }
}
