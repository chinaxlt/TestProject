package org.chinaxlt.forClass;

import java.util.concurrent.Callable;

public class ThreadReturnValue implements Callable {

    private static Integer counter = 0;

    public Object call() throws Exception {
        synchronized (counter) {
            counter++;
            System.out.println("ThreadReturnValue Counter:" + counter);
        }
        return counter;
    }
}
