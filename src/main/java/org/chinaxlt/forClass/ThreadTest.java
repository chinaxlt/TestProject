package org.chinaxlt.forClass;

public class ThreadTest implements Runnable {

    private static Integer counter = 0;

    public void run() {
        synchronized (counter) {
            counter++;
            System.out.println("ThreadTest Counter:" + counter);
        }
    }
}
