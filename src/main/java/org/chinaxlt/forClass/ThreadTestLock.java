package org.chinaxlt.forClass;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ThreadTestLock implements Runnable {

    private static Integer counter = 0;

    public void run() {
        Lock lock = new ReentrantLock();
        lock.lock();
        counter++;
        System.out.println("ThreadTest Counter:" + counter);
        lock.unlock();
    }
}
