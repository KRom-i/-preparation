package counter;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Counter {

    private final Lock lock;

    private int value;

    public Counter () {
        this.lock = new ReentrantLock ();
    }

    public Counter (int value) {
        this.value = value;
        this.lock = new ReentrantLock ();
    }

    public void count (int value) {

        lock.lock ();

        try {

            this.value += value;

            Thread.sleep (250);

            System.out.println (getClass ().getSimpleName () + " value=" + this.value);

        } catch (Exception e) {
            e.printStackTrace ();

        } finally {
            lock.unlock ();
        }
    }


    public static void main (String[] args) {

        Counter counter = new Counter (0);

        Random random = new Random ();

        for (int i = 0; i < 15; i++) {
            new Thread (() -> counter.count (random.nextInt (10))).start ();
        }

    }

}
