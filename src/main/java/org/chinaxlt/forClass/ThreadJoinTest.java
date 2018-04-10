package org.chinaxlt.forClass;

public class ThreadJoinTest {

    public static void main(String[] args) throws InterruptedException {
        MyThread d1 = new MyThread("t1", 1000);
        MyThread d2 = new MyThread("t2", 1000);
        Thread t1 = new Thread(d1);
        Thread t2 = new Thread(d2);

        t1.start();
        t2.start();
        t1.join();

        System.out.println("Main:-->Start");
    }
}
