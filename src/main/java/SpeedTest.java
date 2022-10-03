/**
 * Created by Mihran Galstyan
 * All rights reserved
 */
public class SpeedTest {
    public static final int SIZE = 50_000_000;
    public static final int HALF = SIZE / 2;

    public static void main(final String[] args) {
        SpeedTest test = new SpeedTest();
        SpeedTest.timer();
        test.withoutConcurrency();
        test.withConcurrency();
    }

    private static void timer() {
        Thread timer = new Thread(new Runnable() {
            @Override
            public void run() {
                int time = 0;

                try {
                    while (true) {
                        System.out.print(time + " ");
                        time++;
                        Thread.sleep(1000);
                    }
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        timer.setDaemon(true);
        timer.start();
    }

    void withConcurrency() {
        Float[] array = new Float[SIZE];

        for (int i = 0; i < SIZE; i++) {
            array[i] = 1.0f;
        }
        long before = System.currentTimeMillis();
        Float[] array1 = new Float[HALF];
        Float[] array2 = new Float[HALF];
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.arraycopy(array, 0, array1, 0, HALF);
                for (int i = 0; i < array1.length; i++) {
                    float f = (float) i;
                    array1[i] = (float) (array1[i] * Math.sin(0.2f + f / 5) * Math.cos(0.2f + f / 5));
                }
            }
        });

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {

                System.arraycopy(array, HALF, array2, 0, HALF);
                for (int i = 0; i < array2.length; i++) {
                    float f = (float) i;
                    array2[i] = (float) (array2[i] * Math.sin(0.2f + f / 5) * Math.cos(0.2f + f / 5));
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
        System.arraycopy(array1, 0, array, 0, HALF);
        System.arraycopy(array2, 0, array, HALF, HALF);

        long after = System.currentTimeMillis();

        System.out.println("Duration with concurrency: " + (after - before));

    }

    void withoutConcurrency() {
        Float[] array = new Float[SIZE];
        for (int i = 0; i < SIZE; i++) {
            array[i] = 1.0f;
        }

        long before = System.currentTimeMillis();
        for (int i = 0; i < SIZE; i++) {
            float f = (float) i;
            array[i] = (float) (array[i] * Math.sin(0.2f + f / 5) * Math.cos(0.2f + f / 5));
        }
        long after = System.currentTimeMillis();

        System.out.println("Duration without concurrency: " + (after - before));
    }
}
