package org.chinaxlt.forClass;

public class WaitTest implements Runnable {

    private String value = "WaitTest";

    public void run() {
        try {
            this.finalize();
            System.out.println(this.value);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }
}
