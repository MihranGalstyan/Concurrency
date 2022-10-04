/**
 * Created by Mihran Galstyan
 * All rights reserved
 */
public class Counter {
    private final Object monitor1 = new Object();
    private final Object monitor2 = new Object();

    private int value;
    private int value2;

    public int getValue() {
        return value;
    }

    public void increment() {
        synchronized (monitor1) {
            value++;
        }
    }

    public void decrement() {
        synchronized (monitor1) {
            value--;
        }
    }

    public int getValue2() {
        return value2;
    }

    public void increment2() {
        synchronized (monitor2) {
            value2++;
        }
    }

    public void decrement2() {
        synchronized (monitor2) {
            value2--;
        }
    }
}
