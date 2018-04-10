package org.chinaxlt.forClass;

class MyThread extends Thread {

    private String threadName;
    private int times;

    public MyThread(String threadName, int times) {
        this.threadName = threadName;
        this.times = times;
    }

    public void run() {
        for (int i = 0; i < times; i++) {
            System.out.println(threadName + ":-->" + i);
        }
    }
}
