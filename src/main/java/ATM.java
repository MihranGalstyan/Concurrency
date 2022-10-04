/**
 * Created by Mihran Galstyan
 * All rights reserved
 */
public class ATM {
    private static int amount = 500;
    private static final Object MONITOR = new Object();

    public static int getAmount() {
        return amount;
    }

    public static void withdraw(String name, int amount) {
        synchronized (MONITOR) { // synchronization by using monitor
            System.out.println(name + " went to the ATM");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            if (ATM.amount >= amount) {
                ATM.amount -= amount;
                System.out.printf("%s withdrew %s$. \nLeft %s$", name, amount, ATM.amount);
                System.out.println();
            } else {
                System.out.printf("There in ot enough money in the account for %s$", name);
                System.out.println();
            }
        }
    }
}
