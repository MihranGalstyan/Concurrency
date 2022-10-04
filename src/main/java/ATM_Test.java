/**
 * Created by Mihran Galstyan
 * All rights reserved
 */
public class ATM_Test {
    public static void main(final String[] args) {
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                ATM.withdraw("Diana", 150);
            }
        });
        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {

//                    thread1.join();
                // threads can be synchronized by calling method join()

                ATM.withdraw("Frank", 250);
            }
        });
        Thread thread3 = new Thread(new Runnable() {
            @Override
            public void run() {
                ATM.withdraw("Luis", 450);
            }
        });

        thread1.start();
        thread2.start();
        thread3.start();

        try {
            thread1.join();
            thread2.join();
            thread3.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println(ATM.getAmount() + " left in ATM");
    }
}
