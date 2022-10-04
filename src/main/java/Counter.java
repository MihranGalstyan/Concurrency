import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Mihran Galstyan
 * All rights reserved
 */
public class Counter {
    private AtomicInteger value = new AtomicInteger();
    // keyword volatile forbids cashing of variable. it doesn't synchronizing threads

    public int getValue() {
        return value.intValue();
    }

    public void increment() {
        value.getAndIncrement();
    }

    public void decrement() {
        value.getAndDecrement();
    }
}
